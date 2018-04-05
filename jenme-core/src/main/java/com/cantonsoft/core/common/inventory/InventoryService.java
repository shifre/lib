package com.cantonsoft.core.common.inventory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.data.AbstractDataService;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.InventoryVo;

@Service
@Lazy
public class InventoryService extends AbstractDataService<InventoryVo> {

	@Override
	protected String buildSql() {
		String sql = "select t.*, g.id goodsId, g.code goodsCode, g.name goodsName, g.color, g.spec, g.texture, g.weight, g.volume, g.pur_price, g.sale_price "
				+ "from t_inventory t right join t_goods g on t.goods_id = g.id where g.expired_ind = 'N'";
		return sql;
	}

	@Override
	protected InventoryVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		InventoryVo vo = new InventoryVo();
		vo.setInventoryId(rs.getLong("id"));
		vo.setDeptId(rs.getLong("dept_id"));
		vo.setGoodsId(rs.getLong("goodsId"));
		vo.setAvailableNum(rs.getLong("available_num"));
		vo.setSampleNum(rs.getLong("sample_num"));
		vo.setExhibitNum(rs.getLong("exhibit_num"));
		vo.setBorkenNum(rs.getLong("borken_num"));
		vo.setWithdrawNum(rs.getLong("withdraw_num"));
		vo.setSalingNum(rs.getLong("saling_num"));
		vo.setSampleSaleNum(rs.getLong("sample_sale_num"));
		vo.setForSaleNum(rs.getLong("for_sale_num"));
		vo.setPurchaseNum(rs.getLong("purchase_num"));
		vo.setMoveInNum(rs.getLong("move_in_num"));
		vo.setMoveOutNum(rs.getLong("move_out_num"));
		vo.setMinAlertNum(rs.getLong("min_alert_num"));
		vo.setMaxAlertNum(rs.getLong("max_alert_num"));
		vo.setRemark(rs.getString("remark"));
		
		vo.setGoodsCode(rs.getString("goodsCode"));
		vo.setGoodsName(rs.getString("goodsName"));
		vo.setColor(rs.getString("color"));
		vo.setSpec(rs.getString("spec"));
		vo.setTexture(rs.getString("texture"));
		vo.setWeight(rs.getString("weight"));
		vo.setVolume(rs.getString("volume"));
		vo.setPurPrice(rs.getBigDecimal("pur_price"));
		vo.setSalePrice(rs.getBigDecimal("sale_price"));

		return vo;
	}

	@Override
	protected String convertFieldName(String entityNm) {
		String fieldNm = entityNm;
		if ("inventoryId".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.id";
		} else if ("deptId".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.dept_id";
		} else if ("goodsId".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.id";
		} else if ("availableNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.available_num";
		} else if ("sampleNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.sample_num";
		} else if ("exhibitNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.exhibit_num";
		} else if ("borkenNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.borken_num";
		} else if ("withdrawNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.withdraw_num";
		} else if ("salingNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.saling_num";
		} else if ("sampleSaleNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.sample_sale_num";
		} else if ("forSaleNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.for_sale_num";
		} else if ("purchaseNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.purchase_num";
		} else if ("moveInNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.move_in_num";
		} else if ("moveOutNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.move_out_num";
		} else if ("minAlertNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.min_alert_num";
		} else if ("maxAlertNum".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.max_alert_num";
		} else if ("remark".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.remark";
		} else if ("vendorId".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.vendor_id";
		} else if ("goodsCode".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.code";
		} else if ("goodsName".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.name";
		} else if ("color".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.color";
		} else if ("spec".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.spec";
		} else if ("texture".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.texture";
		} else if ("weight".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.weight";
		} else if ("volume".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.volume";
		} else if ("purPrice".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.pur_price";
		} else if ("salePrice".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.sale_price";
		} else if ("customizeInd".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.customize_ind";
		} else if ("accessoriesInd".equalsIgnoreCase(entityNm)) {
			fieldNm = "g.accessories_ind";
		}
		return fieldNm;
	}
	
	public PageData<InventoryVo> buildPageData(PageFilter filter) {
		PageData<InventoryVo> pageData = findAll(filter);
		List<InventoryVo> list = findAllWithFilterAndSort(filter);
		BigDecimal salingPurPriceSum = new BigDecimal(0);
		BigDecimal salingSalePriceSum = new BigDecimal(0);
		BigDecimal exhibitPurPriceSum = new BigDecimal(0);
		BigDecimal availablePurPriceSum = new BigDecimal(0);
		BigDecimal samplePurPriceSum = new BigDecimal(0);
		BigDecimal borkenPurPriceSum = new BigDecimal(0);
		BigDecimal withdrawPurPriceSum = new BigDecimal(0);
		for (InventoryVo vo : list) {
			vo.calculateInventory();
			salingPurPriceSum = salingPurPriceSum.add(vo.getSalingPurPriceSum());
			salingSalePriceSum = salingSalePriceSum.add(vo.getSalingSalePriceSum());
			exhibitPurPriceSum = exhibitPurPriceSum.add(vo.getExhibitPurPriceSum());
			availablePurPriceSum = availablePurPriceSum.add(vo.getAvailablePurPriceSum());
			samplePurPriceSum = samplePurPriceSum.add(vo.getSamplePurPriceSum());
			borkenPurPriceSum = borkenPurPriceSum.add(vo.getBorkenPurPriceSum());
			withdrawPurPriceSum = withdrawPurPriceSum.add(vo.getWithdrawPurPriceSum());
		}
		if (!pageData.getElements().isEmpty()) {
			pageData.getElements().get(0).setSalingPurPriceSum(salingPurPriceSum);
			pageData.getElements().get(0).setSalingSalePriceSum(salingSalePriceSum);
			pageData.getElements().get(0).setExhibitPurPriceSum(exhibitPurPriceSum);
			pageData.getElements().get(0).setAvailablePurPriceSum(availablePurPriceSum);
			pageData.getElements().get(0).setSamplePurPriceSum(samplePurPriceSum);
			pageData.getElements().get(0).setBorkenPurPriceSum(borkenPurPriceSum);
			pageData.getElements().get(0).setWithdrawPurPriceSum(withdrawPurPriceSum);
		}
		return pageData;
	}

}
