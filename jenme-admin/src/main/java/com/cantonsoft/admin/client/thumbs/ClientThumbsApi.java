package com.cantonsoft.admin.client.thumbs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cantonsoft.core.common.thumbs.ThumbsService;
import com.cantonsoft.core.common.thumbs.model.Thumbs;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.service.AbstractSvcController;
import com.cantonsoft.framework.upload.IFileStore;

@JsonApi
@Lazy
@RequestMapping(value = "/client/thumbs")
@Access(value = "fn.client.thumbs")
public class ClientThumbsApi extends AbstractSvcController {

	@Autowired
	@Qualifier("uploadFileStore")
	private IFileStore fileStore;
	@Autowired
	private ThumbsService thumbsService;
	
	@Access(value = "update")
	@RequestMapping(value = "delete.json", method = RequestMethod.POST)
	public @ResponseBody Object imageDelete(@RequestParam Long picId, HttpServletRequest request, HttpServletResponse response)
	{
		thumbsService.delete(picId);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
	

	@RequestMapping(value = "upload.json", method = RequestMethod.POST)  
    public @ResponseBody Map<String, Object> uploadImage(MultipartHttpServletRequest request, @RequestParam Long refId, @RequestParam String refType, HttpServletResponse response) {  
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
    			
    			Integer sort = thumbsService.getSort(refId,refType);
    			
    			if (sort > 5) {
					throw new ServiceException("only.upload.five.pictures");
				}
    			
    			Thumbs thumbs = new Thumbs();
    			thumbs.setRefId(refId);
    			thumbs.setRefType(refType);
    			thumbs.setImage(uri);
    			thumbs.setSort(sort);
    			thumbsService.add(thumbs);
    			
    			returnMap.put("uri", uri);
    			returnMap.put("picId", thumbs.getId());
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
