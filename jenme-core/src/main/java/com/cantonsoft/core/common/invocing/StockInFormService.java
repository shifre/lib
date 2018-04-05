package com.cantonsoft.core.common.invocing;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.invocing.model.StockInForm;
import com.cantonsoft.core.common.invocing.model.StockInFormDao;
import com.cantonsoft.data.AbstractDataService;
import com.cantonsoft.vo.StockInFormVo;

@Service
@Lazy
public class StockInFormService extends AbstractDataService<StockInFormVo> {

	@Autowired
	private StockInFormDao stockInFormDao;
	
	protected  StockInFormVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		StockInFormVo vo = new StockInFormVo();
		
		vo.setStockInCode(rs.getString("stock_in_code"));
		vo.setVendorId(rs.getLong("vendor_id"));
		vo.setVendorOrderCd(String.valueOf(rs.getInt("vendor_order_cd")));
		vo.setCostPrice(rs.getBigDecimal("cost_price"));
		vo.setCredentialId(rs.getLong("credential_id"));
		vo.setIndicator(rs.getInt("indicator"));
		
		vo.setOriginFormId(rs.getLong("origin_form_id"));
		vo.setRemark(rs.getString("remark"));
		vo.setCreateBy(rs.getString("create_by"));
		vo.setCreateDate(rs.getDate("create_date"));
		vo.setUpdateBy(rs.getString("update_by"));
		vo.setUpdateZDate(rs.getDate("update_date"));
		
		return vo;
	}
	
   protected  String buildSql() {
		return "select stock_in_code,vendor_id,vendor_order_cd,cost_price,credential_id,indicator,origin_form_id,remark,"
				+ "create_by,create_date,update_by,update_date from t_stock_in_form where expired_ind = 'N'";
			
   }
	
	
	
	protected  String convertFieldName(String entityNm) {
		String fieldNm = null;
		if ("stockInCode".equalsIgnoreCase(entityNm)) {
			fieldNm = "stock_in_code";
		} else if ("code".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.code";
		} else if ("createDate".equalsIgnoreCase(entityNm)) {
			fieldNm = "create_date";
		} else if ("credentialId".equalsIgnoreCase(entityNm)) {
			fieldNm = "credential_id";
		} else if ("createBy".equalsIgnoreCase(entityNm)) {
			fieldNm = "create_by";
		} else if ("vendorOrderCd".equalsIgnoreCase(entityNm)) {
			fieldNm = "vendor_order_cd";
		} else if ("vendorId".equalsIgnoreCase(entityNm)) {
			fieldNm = "vendor_id";
		}
	   return fieldNm != null ? fieldNm : entityNm;
	   
	}

	public StockInFormVo add(StockInFormVo stockInFormVo) {
		return update(stockInFormVo);
		
		
	}
	
	
	
	public StockInFormVo update(StockInFormVo stockInFormVo) {
		StockInForm entity = new StockInForm();
		try {
		    BeanUtils.copyProperties( stockInFormVo,entity);
		} catch (Exception e) {
			throw new RuntimeException("Can't copy properties from Vo to entity");
		}		
	
		StockInForm data =  this.stockInFormDao.save(entity);
		
		StockInFormVo vo = new StockInFormVo();
		BeanUtils.copyProperties(data, vo,new String[]{"serialVersionUID"});
		
		return vo;
		
		
	}
	

	public void delete(Long id) {
	
	   //this.stockInFormDao.delete(id);
	}
}