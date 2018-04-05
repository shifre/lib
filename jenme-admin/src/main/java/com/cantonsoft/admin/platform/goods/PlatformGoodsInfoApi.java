package com.cantonsoft.admin.platform.goods;

import java.util.List;

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

import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.common.goods.GoodsService;
import com.cantonsoft.core.common.goods.model.Goods;
import com.cantonsoft.framework.api.ApiResponse;
import com.cantonsoft.framework.api.IErrors;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.GoodsDetailVo;
import com.cantonsoft.vo.GoodsVo;

@JsonApi
@Lazy
@RequestMapping(value = "/platform/goods/info")
@Access(value = "fn.main.goods.info")
public class PlatformGoodsInfoApi extends BaseEntityApi<Goods, Long, GoodsVo, GoodsVo>{
	@Autowired
	GoodsService service;
	@Autowired
	private ShiroSessionHelper shiro;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Goods,Long> getEntityService() {
		return null;
	}	
	
	@Override
	public @ResponseBody Object add(@RequestBody GoodsVo data, HttpServletRequest request, HttpServletResponse response){
		data.setUpdateBy(getCurrentUserName());
		Goods entity = service.add(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), entity);
	}
	
	@Override
	public @ResponseBody Object search(@RequestBody PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		PageData<GoodsVo> pageData = service.findAll(filter);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), pageData);
		
	}
	
	@Override
	public @ResponseBody Object update(@RequestParam Long id, @RequestBody GoodsVo data, HttpServletRequest request, HttpServletResponse response) {
		data.setUpdateBy(getCurrentUserName());
		service.update(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
		
	}
	
	@Override
	public @ResponseBody Object delete(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
		service.delete(id);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
	}
	
	@Access(value = "update")
	@RequestMapping(value = "audit.json", method = RequestMethod.POST)
	public @ResponseBody Object goodsAudit(@RequestParam Long goodsId, HttpServletRequest request, HttpServletResponse response)
	{
		service.goodsAudit(goodsId, getCurrentUserName());
		return ApiResponse.make(IErrors.NO_ERROR, "审核通过", null);
	}
	
	@Access(value = "update")
	@RequestMapping(value = "changeHiddenInd.json", method = RequestMethod.POST)
	public @ResponseBody Object goodsHide(@RequestParam Long goodsId, @RequestParam String hiddenInd, HttpServletRequest request, HttpServletResponse response)
	{
		service.goodsHide(goodsId, hiddenInd, getCurrentUserName());
		String message = msgParser.parse("info.success");
		if ("Y".equals(hiddenInd)) {
			message = "商品已隐藏";
		} else {
			message = "商品已取消隐藏";
		}
		return ApiResponse.make(IErrors.NO_ERROR, message, null);
	}
	
	@Access(value = "update")
	@RequestMapping(value = "updateGoods.json", method = RequestMethod.POST)
	public @ResponseBody Object updates(@RequestParam Long id, @RequestBody GoodsVo data, HttpServletRequest request, HttpServletResponse response) {
		data.setUpdateBy(getCurrentUserName());
		service.update(data);
		return ApiResponse.make(IErrors.NO_ERROR, msgParser.parse("info.success"), null);
		
	}
	
    private String getCurrentUserName(){
    	return shiro.getSession().getUser().getTitle();
    }
}
