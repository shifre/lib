package com.cantonsoft.admin.platform.department;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.core.common.department.DepartmentService;
import com.cantonsoft.core.common.department.model.Department;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.DepartmentVo;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/department")
@Access(value = "fn.main.department.info")
public class PlatformDepartmentApi extends BaseEntityApi<Department, Long, DepartmentVo, DepartmentVo>{
	@Autowired
	DepartmentService service;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Department,Long> getEntityService() {
		return null;
	}
	
	@Override
	public @ResponseBody Object add(@RequestBody DepartmentVo data, HttpServletRequest request, HttpServletResponse response){
		Department entity = service.add(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), entity);
	}
	
	@Override
	public @ResponseBody Object search(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		PageData<DepartmentVo> pageData = service.findAll(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), pageData);
		
	}
	
	@Override
	public @ResponseBody Object update(@RequestParam Long id, @RequestBody DepartmentVo data, HttpServletRequest request, HttpServletResponse response) {
		service.update(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
		
	}
	
	@Override
	public @ResponseBody Object delete(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
		service.delete(id);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
	
	
	
}
