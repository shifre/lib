package com.cantonsoft.core.common.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cantonsoft.core.common.asset.AssetService;
import com.cantonsoft.core.common.goods.GoodsService;
import com.cantonsoft.core.common.goods.model.Goods;
import com.cantonsoft.core.common.template.model.Template;
import com.cantonsoft.core.common.template.model.TemplateDao;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.upload.LocalFileStore;
import com.cantonsoft.framework.util.Config;
import com.cantonsoft.framework.util.DownloadUtil;
import com.cantonsoft.framework.util.FileUtil;
import com.cantonsoft.framework.util.ZipTool;
import com.cantonsoft.vo.GoodsVo;

@Service
@Lazy
public class TemplateService extends BaseEntityService<Template, Long>{
	@Autowired
	private TemplateDao dao;
	@Autowired
	@Qualifier("uploadFileStore")
	private LocalFileStore fileStore;
	@Autowired
	private Config config;
	@Autowired
	private AssetService assetService;
	@Autowired
	private GoodsService goodsService;

	@Override
	protected BaseEntityDao<Template, Long> getDao() {
		return dao;
	}
	
	public List<Template> findAll() {
		return dao.findAll();
	}
	
	public Long findMaxId() {
		return dao.findMaxId();
	}
	
	public void changeTemplate(Long siteId, Long templateId, Long version)throws Exception
	{
		String sitePath = config.get("sitebase").toString() + "/" + siteId + "/";
		String templatePath = config.get("templatebase").toString() + templateId + "_V" + version;
		
		File siteFile = new File(sitePath);
		File templateFile = new File(templatePath);
		
		if(siteFile.exists() || java.nio.file.Files.isSymbolicLink(siteFile.toPath()))
		{
			FileUtil.delFolder(sitePath);
		}
		String isLink = config.getProperty("islink", "false");
		if("true".equals(isLink)){
			sitePath = config.get("sitebase").toString() + "/" + siteId ;
			templatePath = config.get("templatebase").toString() + "/" + templateId ;
			
			FileUtil.linkeFolder(templatePath,sitePath);
		} else {
			siteFile.mkdirs();
			
			File[] files = templateFile.listFiles();
			if(files == null)
			{
				throw new ServiceException("error.site.template.file.not.exist");
			}
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

		
		
		File file = new File(templatePath + "/data.dat");
		if (file.isFile())
		{
			InputStream inputStream = new FileInputStream(file);
			assetService.importService(siteId, inputStream);
		}
	}
	
	public void exportSiteTemplate(Long siteId, HttpServletResponse response)throws Exception
	{
		Map<String, Object> jsonMap = assetService.getDataMap(siteId);
		String sitePath = config.get("sitebase").toString() + "/" + siteId + "/";
		
		File oldfile = new File(sitePath + "data.dat");
        File newfile = new File(sitePath + "old-data.dat");
        if(newfile.exists())
        {
        	newfile.delete();
        }
       
        oldfile.renameTo(newfile);
        
		File dataFile = new File(sitePath + "data.dat");
		FileOutputStream fos = new FileOutputStream(dataFile);
		ObjectMapper objMapper = new ObjectMapper();
		byte [] bytes = objMapper.writeValueAsBytes(jsonMap);
		fos.write(bytes, 0, bytes.length);
		fos.flush();
		fos.close();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + siteId + sdf.format(new Date()) + ".zip");
		String newContentType = DownloadUtil.getContentType("zip", false);
	    response.setContentType(newContentType);
		
		ZipTool.getInstance().zip(new File(sitePath), response.getOutputStream());;
	    dataFile.delete();
	    newfile.renameTo(oldfile);
	}
	
	public List<Goods> importGoods(MultipartHttpServletRequest request) throws IOException
	{
		MultipartFile file = null;
		String fileName = "";
		List<Goods> goods = new ArrayList<Goods>();
		for (MultipartFile f : request.getFileMap().values())
		{
			file = f;
		}
		if (null == file || file.getSize() == 0)
		{
			throw new ServiceException("error.upload.empty");
		}
		if (null != file)
		{			
			fileName = file.getOriginalFilename();
			if (file.getSize() > 4 * 1024 * 1024)
			{
				throw new ServiceException("error.upload.limit", String.valueOf(4096));
			}
			
			String suffix = fileName.substring(fileName.length() - 3);
			
			if (!suffix.toUpperCase().equals("CSV")) {
				throw new ServiceException("error.upload.not.csv");
			}
			
			InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
			String str = bufferedReader.readLine();
			while((str = bufferedReader.readLine()) != null){
				String[] goodsArr = str.split(",");
				if(goodsArr.length == 4){
					Goods good = new Goods();
					good.setOrderMemo(goodsArr[0]);
					good.setCode(goodsArr[1]);
					good.setTexture(goodsArr[2]);
					good.setSpec(goodsArr[3]);
					String sql = buildGoodsSqlForSearch(good);
					List<GoodsVo> goodTemp = goodsService.findAll(sql);
					if(goodTemp.size() > 0 ){
						good.setId(goodTemp.get(0).getGoodsId());
						good.setName(goodTemp.get(0).getName());
					}
					goods.add(good);
				}
			}
			
		}
		return goods;
	}
	
	private String buildGoodsSqlForSearch(Goods good) {
		StringBuilder sql = new StringBuilder();
		sql.append("select g.*, gi.*, gid.* from t_goods g "+
			"left join t_goods_instance gi on g.id = gi.goods_id "+
			"left join t_goods_instance_detail gid on gi.id = gid.goods_instance_id "+
			"where 1=1");
		if(StringUtils.isNotEmpty(good.getOrderMemo())){
			sql.append("and g.order_memo like '%"+good.getOrderMemo()+"%'");
		}
		if(StringUtils.isNotEmpty(good.getCode())){
			sql.append("and g.code like '%"+good.getCode()+"%'");
		}
		if(StringUtils.isNotEmpty(good.getTexture())){
			sql.append("and g.texture like '%"+good.getTexture()+"%'");
		}
		if(StringUtils.isNotEmpty(good.getColor())){
			sql.append("and g.color like '%"+good.getColor()+"%' ");
		}
		if(StringUtils.isNotEmpty(good.getSpec())){
			sql.append("and g.spec like '%"+good.getSpec()+"%' ");
		}
		if(StringUtils.isNotEmpty(good.getUnit())){
			sql.append("and gid.barcode = '"+good.getUnit()+"' ");
		}
		return sql.toString();
	}

	public String uploadTemplate(MultipartHttpServletRequest request) throws IOException
	{
		MultipartFile file = null;
		String fileName = "";
		for (MultipartFile f : request.getFileMap().values())
		{
			file = f;
		}
		if (null == file || file.getSize() == 0)
		{
			throw new ServiceException("error.upload.empty");
		}
		if (null != file)
		{			
			fileName = file.getOriginalFilename();
			if (file.getSize() > 4 * 1024 * 1024)
			{
				throw new ServiceException("error.upload.limit", String.valueOf(4096));
			}
			
			String suffix = fileName.substring(fileName.length() - 3);
			
			if (!suffix.toUpperCase().equals("ZIP")) {
				throw new ServiceException("error.upload.not.zip");
			}
			
		}
		return fileStore.save(fileName, file.getInputStream(), "temp");
	}
	
	private void installTemplate(String tempUri, String context)
	{
		File ctxFile = new File(context);
		if (!ctxFile.exists() && !ctxFile.isDirectory())
		{
			ctxFile.mkdirs();
		}
		
		File tempFile = new File(tempUri);
		
		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");
		expand.setEncoding("UTF-8");

		expand.setSrc(tempFile);
		expand.setDest(ctxFile);
		expand.execute();
		
		tempFile.delete();
	}
	
	public String uploadUpdateTemplate(MultipartHttpServletRequest request, Long id, Long version) throws IOException {
		String uri = uploadTemplate(request);
		String tempUri = fileStore.getDocBase().getAbsolutePath() + uri;	
		String context = config.get("templatebase").toString() + "/" + id + "_V" + version;
		installTemplate(tempUri, context);
		String islink = config.getProperty("islink", "false");
		if("true".equals(islink)){
			String target = config.get("templatebase").toString() + "/" + id;
			File tempFile = new File(target);

			if(tempFile.exists() || java.nio.file.Files.isSymbolicLink(tempFile.toPath())){
				FileUtil.delFolder(target);
			}
			FileUtil.linkeFolder(context, target);
		}
		return uri;
	}
	
	public void addUpdateTemplate(String tempUri, Long id, int version) throws IOException {
		tempUri = fileStore.getDocBase().getAbsolutePath() + tempUri;
		String context = config.get("templatebase").toString() + "/" + id + "_V" + version;
		installTemplate(tempUri, context);
		String islink = config.getProperty("islink", "false");
		if("true".equals(islink)){
			String target = config.get("templatebase").toString() + "/" + id;
			File tempFile = new File(target);
			if(tempFile.exists() || java.nio.file.Files.isSymbolicLink(tempFile.toPath())){
				FileUtil.delFolder(target);
			}
			FileUtil.linkeFolder(context, target);
		}
	}

}
