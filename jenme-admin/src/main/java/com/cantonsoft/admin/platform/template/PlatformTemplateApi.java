package com.cantonsoft.admin.platform.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cantonsoft.core.common.goods.model.Goods;
import com.cantonsoft.core.common.template.TemplateService;
import com.cantonsoft.core.common.template.model.Template;
import com.cantonsoft.core.common.template.model.TemplateAdd;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@JsonApi
@Lazy
@RequestMapping(value = "platform/template")
@Access(value = "fn.main.template")
public class PlatformTemplateApi extends BaseEntityApi<Template, Long, TemplateAdd, Template>{
	@Autowired
	private TemplateService service;

	@Override
	protected BaseEntityService<Template, Long> getEntityService() {
		return service;
	}
	
	
	@RequestMapping(value = "add/upload.json", method = RequestMethod.POST)  
    public @ResponseBody Object addUpload(MultipartHttpServletRequest request, HttpServletResponse response) {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try
    	{    		
    		List<Goods> goods = service.importGoods(request);
//			returnMap.put("tempUri", tempUri);
    		returnMap.put("goods", goods);
    	}
    	catch (Exception ex)
    	{
    		throw new ServiceException(ex.getMessage(), ex);
    	}
    	return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), returnMap);
    }

	@Override
	public @ResponseBody Object add(@RequestBody TemplateAdd data, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try
		{
			String tempUri = data.getTempUri();
			if(StringUtils.isEmpty(tempUri))
			{
				throw new ServiceException("error.template.upload.please");
			}
			
			Template entity = service.getNewObject(data);
			entity.setVersion(Long.valueOf(1));
			service.add(entity);
			service.addUpdateTemplate(tempUri, entity.getId(), 1);	
			returnMap.put("data", entity);
		}
		catch (Exception ex)
    	{
			throw new ServiceException(ex.getMessage(),ex);
    	}
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), returnMap);
	}

	@RequestMapping(value = "update/upload.json", method = RequestMethod.POST)  
    public @ResponseBody Object updateUpload(@RequestParam Long id, MultipartHttpServletRequest request, HttpServletResponse response) {  
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try
    	{    		
    		Template template = service.find(id);
    		template.setVersion(template.getVersion() + 1);
    		service.update(template);
    		String tempUri = service.uploadUpdateTemplate(request, id, template.getVersion());
    		returnMap.put("data", template);
    		returnMap.put("tempUri", tempUri);
    	}
    	catch (Exception ex)
    	{
    		throw new ServiceException(ex.getMessage(),ex);
    	}
    	
    	return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), returnMap);
    }
}
