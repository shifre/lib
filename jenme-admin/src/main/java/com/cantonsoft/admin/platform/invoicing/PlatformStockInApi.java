package com.cantonsoft.admin.platform.invoicing;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.common.invocing.StockInFormService;
import com.cantonsoft.core.common.invocing.model.StockInForm;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.StockInFormVo;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/invoicing/stockinform")
@Access(value = "fn.main.invoicing.stockin")
public class PlatformStockInApi extends BaseEntityApi<StockInForm, Long, StockInFormVo, StockInFormVo>{
	@Autowired
	StockInFormService service;
	@Autowired
	private ShiroSessionHelper shiro;
	
	@PostConstruct
	private void init() {
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<StockInForm,Long> getEntityService() {
		return null;
	}	
	
	@Override
	public @ResponseBody Object add(@RequestBody StockInFormVo data, HttpServletRequest request, HttpServletResponse response){
		data.setUpdateBy(getCurrentUserName());
		StockInFormVo entity = service.add(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), entity);
	}
	
	@Override
	public @ResponseBody Object search(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("this is the search method from invoking");
		PageData<StockInFormVo> pageData = service.findAll(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), pageData);
		
	}
	
	@Override
	public @ResponseBody Object update(@RequestParam Long id, @RequestBody StockInFormVo data, HttpServletRequest request, HttpServletResponse response) {
		data.setUpdateBy(getCurrentUserName());
		service.update(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
		
	}
	
	@Override
	public @ResponseBody Object delete(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
		service.delete(id);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
	
    private String getCurrentUserName(){
    	return shiro.getSession().getUser().getTitle();
    }
}
