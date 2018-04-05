package com.cantonsoft.admin.partner.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.account.user.ClientUserService;
import com.cantonsoft.core.account.user.model.ClientUser;
import com.cantonsoft.core.cloud.client.ClientService;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.core.cloud.client.model.ClientUpdate;
import com.cantonsoft.core.cloud.client.service.constract.ServiceContractService;
import com.cantonsoft.core.cloud.client.service.constract.model.ServiceContract;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.site.SiteService;
import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.core.common.asset.AssetService;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.ICond;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service.EntityAccessControl;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.framework.util.Config;
import com.cantonsoft.framework.util.FileUtil;

@JsonApi
@Lazy
@RequestMapping(value = "partner/client")
@Access(value = "fn.partner.client")
public class PartnerClientApi extends BaseEntityApi<Client, Long, Client, ClientUpdate> {
	@Autowired
	private ClientService service;
	@Autowired
	private ShiroSessionHelper sessionHelper;
	@Autowired
	private ClientUserService clientUserService;
	@Autowired
	private ClientAccessControl accessControl;
	@Autowired
	private SiteService siteService;
	@Autowired
	private ServiceContractService contractService;
	@Autowired
	private Config config;
	@Autowired
	private AssetService assetService;

	@Override
	protected EntityAccessControl<Long> getControl() {
		return accessControl;
	}

	@Override
	protected BaseEntityService<Client, Long> getEntityService() {
		return service;
	}

	@PostConstruct
	private void init() {
		searchDef = new SearchDef().addField("title").addField("type").addField("partnerId").addField("clientId");
	}
	
	@Override
	protected PageData<Client> doSearch(PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		Partner partner = sessionHelper.getSession().getPartner();

		filter.getConds().add(new Cond("partnerId", Cond.OP_EQ, partner.getId().toString()));
		return super.doSearch(filter, request, response);
	}

	@Override
	protected Client doAdd(Map<String, Object> respMap, Client data, HttpServletRequest request, HttpServletResponse response) {
		Partner partner = sessionHelper.getSession().getPartner();
		data.setPartnerId(partner.getId());
		return super.doAdd(respMap, data, request, response);
	}

	@Access("search")
	@RequestMapping(value = "employment/search.json", method = RequestMethod.POST)
	public @ResponseBody Object employmentSearch(@RequestParam Long clientId, @RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) throws Exception {
		accessControl.checkRead(clientId);
		filter.getConds().add(new Cond("clientId", ICond.OPERATOR_EQ, String.valueOf(clientId)));
		PageData<ClientUser> page = clientUserService.find(filter);
		
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), page);
	}
	
	@Access(value = "add")
	@RequestMapping(value = "service/add/site.json", method = RequestMethod.POST)
	public @ResponseBody Object addSite(@RequestParam Long clientId, @RequestBody Site data, @RequestParam Long templateId, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		if (null == templateId)
		{
			throw new ServiceException("error.template.selectd.please");
		}
		
		Client client = service.find(clientId);
		accessControl.checkRead(clientId);
		
		Site site = new Site();
		site.setClientId(clientId);
		site.setTitle(data.getTitle());
		site.setStatus(Site.SITE_STATUS_OPEN);
		site.setTemplateId(templateId);
		siteService.add(site);
		
		String islink = config.getProperty("islink","false");
		String context = null;
		if("true".equals(islink)){
			context = config.get("templatebase").toString() + "/" + templateId +"/";
			String sitePath = config.get("sitebase").toString() + "/" + site.getId();
			File siteFile = new File(config.get("sitebase").toString() + "/" + site.getId() );
			if(siteFile.exists() || java.nio.file.Files.isSymbolicLink(siteFile.toPath()))
			{
				FileUtil.delFolder(sitePath);
			}
			FileUtil.linkeFolder(context, sitePath);
		} else {
			context = config.get("templatebase").toString() + "/" + templateId + "_V" + "1";

			File templateFile = new File(context);
			File siteFile = new File(config.get("sitebase").toString() + "/" + site.getId() + "/");
			if (!siteFile.exists() && !siteFile.isDirectory())
			{
				siteFile.mkdirs();
			}
			
			File[] files = templateFile.listFiles();
	        for (File file : files) 
	        {
	    	   if(file.isFile())
	    	   {
	    		   FileUtil.copyFile(new FileInputStream(file), new File(siteFile.getAbsolutePath() + "/" + file.getName()));
	    	   }
	    	   if(file.isDirectory())
	    	   {
	    		   String sorceDir= templateFile.getAbsolutePath() + "/" + File.separator + file.getName();
	    		   String targetDir = siteFile.getAbsolutePath() + "/" + File.separator + file.getName();
	    		   FileUtil.copyDirectiory(sorceDir, targetDir);
	    	   }
	        }	
		}
		
        
		File file = new File(context + "/data.dat");
		if (file.isFile())
		{
			InputStream inputStream = new FileInputStream(file);
			assetService.importService(site.getId(), inputStream);
		}

		ServiceContract contract = new ServiceContract();
		contract.setStatus(ServiceContract.STATUS_ACTIVE);
		contract.setSiteId(site.getId());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getCreatedTime());
//		calendar.add(Calendar.YEAR, serviceYear);

		contract.setExpiryTime(calendar.getTime());
		contractService.add(contract);

//		service.update(client);

		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
}
