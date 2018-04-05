package com.cantonsoft.admin.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service.AbstractSvcController;
import com.cantonsoft.framework.upload.IFileStore;

@JsonApi
@Lazy
@RequestMapping(value = "/upload")
public class UploadApi extends AbstractSvcController {
	
	@Autowired
	@Qualifier("uploadFileStore")
	private IFileStore fileStore;

    @RequestMapping(value = "file.json", method = RequestMethod.POST)  
    public @ResponseBody Map<String, Object> upload(MultipartHttpServletRequest request, HttpServletResponse response) {  

    	Map<String, Object> returnMap = new HashMap<String, Object>();
		try
		{    		
			MultipartFile file = null;
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
	    		String fileName = file.getOriginalFilename();
				if (file.getSize() > 1024 * 1024)
				{
					throw new ServiceException("error.upload.limit", String.valueOf(1024));
				}
	    		String uri = fileStore.save(fileName, file.getInputStream(), "common");
	    		
	    		returnMap.put("uri", uri);
	    		respHandler.handleSuccess(returnMap, "info.success");
    		}
    		
		}
		catch (Exception ex)
		{
			respHandler.handleError(returnMap, ex, request, response);
		}
		
		return returnMap;
    }
    
    @RequestMapping(value = "image.json", method = RequestMethod.POST)  
    public @ResponseBody Map<String, Object> uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {  
    	
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try
    	{    		
    		MultipartFile file = null;
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
    			String fileName = file.getOriginalFilename();
    			if (file.getSize() > 1024 * 1024)
    			{
    				throw new ServiceException("error.upload.limit", String.valueOf(1024));
    			}
    			String uri = fileStore.save(fileName, file.getInputStream(), "common");
    			
    			returnMap.put("uri", uri);
    			respHandler.handleSuccess(returnMap, "info.success");
    		}
    		
    	}
    	catch (Exception ex)
    	{
    		respHandler.handleError(returnMap, ex, request, response);
    	}
    	
    	return returnMap;
    }
    
}
