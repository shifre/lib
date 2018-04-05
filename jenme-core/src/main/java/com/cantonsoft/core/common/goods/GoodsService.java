package com.cantonsoft.core.common.goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.department.model.Department;
import com.cantonsoft.core.common.goods.category.model.GoodsCategory;
import com.cantonsoft.core.common.goods.category.model.GoodsCategoryDao;
import com.cantonsoft.core.common.goods.model.Goods;
import com.cantonsoft.core.common.goods.model.GoodsDao;
import com.cantonsoft.core.common.info.vendor.model.Vendor;
import com.cantonsoft.core.common.info.vendor.model.VendorDao;
import com.cantonsoft.data.AbstractDataService;
import com.cantonsoft.vo.GoodsVo;

@Service
@Lazy
public class GoodsService extends AbstractDataService<GoodsVo> {
	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	@Autowired
	private VendorDao vendorDao;

//	@Transactional(readOnly = true)
//	public GoodsVo find(Long id) {
//		String sql = "select g.*, gc.code categoryCode, gc.name categoryName, v.code vendorCode from t_goods g left join t_goods_category gc "
//				+ "on g.category_id = gc.id left join t_vendor v on g.vendor_id = v.id where g.id = " + id;
//		return this.find(sql);
//	}
//	
//	@Transactional(readOnly = true)
//	public PageData<GoodsVo> findAll(PageFilter filter) {
//		String sql = "select g.*, gc.code categoryCode, gc.name categoryName, v.code vendorCode from t_goods g left join t_goods_category gc "
//				+ "on g.category_id = gc.id left join t_vendor v on g.vendor_id = v.id where expired_ind = 'N'";
//		if (filter.getConds() != null && !filter.getConds().isEmpty()) {
//			for (Cond cond : filter.getConds()) {
//				if(StringUtils.isEmpty(cond.getValue())){
//					continue;
//				}
//				if (Cond.OP_LEFT_LIKE.equals(cond.getOp())) {
//					sql = sql + " and " + convertFieldName(cond.getName()) + " like '" + cond.getValue() + "%'";
//				}
//			}
//		}
//		if (filter.getSorts() != null && !filter.getSorts().isEmpty()) {
//			for (Sort sort : filter.getSorts()) {
//				if (sort.isAsc()) {
//					sql = sql + " order by " + convertFieldName(sort.getName()) + " asc";
//				} else {
//					sql = sql + " order by " + convertFieldName(sort.getName()) + " desc";
//				}
//			}
//		}
//		return this.getPage(sql, filter);
//	}
	
	@Override
	protected GoodsVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		GoodsVo vo = new GoodsVo();
		vo.setGoodsId(rs.getLong("id"));
		vo.setCode(rs.getString("code"));
		vo.setName(rs.getString("name"));
//		vo.setCategory(rs.getString("categoryCode") + " " + rs.getString("categoryName"));
		vo.setCategoryId(rs.getLong("category_id"));
		vo.setType(rs.getString("type"));
		vo.setTexture(rs.getString("texture"));
		vo.setSalePrice(rs.getBigDecimal("sale_price"));
		vo.setPurPrice(rs.getBigDecimal("pur_price"));
		vo.setUnit(rs.getString("unit"));
//		vo.setVendorCode(rs.getString("vendorCode"));
		vo.setVendorId(rs.getLong("vendor_id"));
		vo.setSaleDeliverTime(rs.getInt("sale_deliver_time"));
		vo.setPurDeliverTime(rs.getInt("pur_deliver_time"));
		vo.setOrderMemo(rs.getString("order_memo"));
		boolean isHidden = "Y".equals(rs.getString("hiden_ind")) ? true : false;
		vo.setHidden(isHidden);
		vo.setLcPrice(rs.getBigDecimal("lc_price"));
		vo.setWeight(rs.getBigDecimal("weight"));
		vo.setVolume(rs.getBigDecimal("volume"));
		vo.setColor(rs.getString("color"));
		vo.setSpec(rs.getString("spec"));
		boolean isCustomizeAudit = "Y".equals(rs.getString("customize_audit")) ? true : false;
		vo.setCustomizeAudit(isCustomizeAudit);
		boolean isCustomize = "Y".equals(rs.getString("customize_ind")) ? true : false;
		vo.setCustomize(isCustomize);
		boolean isAccessories = "Y".equals(rs.getString("accessories_ind")) ? true : false;
		vo.setAccessories(isAccessories);
		vo.setUpdateBy(rs.getString("update_by"));
		vo.setUpdateDate(rs.getTimestamp("update_date"));
		vo.setCount(rs.getLong("count"));

		return vo;
	}

	@Override
	protected String convertFieldName(String entityNm) {
		String fieldNm = entityNm;
		if ("goodsId".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.id";
		} else if ("code".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.code";
		} else if ("name".equalsIgnoreCase(entityNm)) {
			fieldNm = "t.name";
		} else if ("categoryId".equalsIgnoreCase(entityNm)) {
			fieldNm = "category_id";
		} else if ("salePrice".equalsIgnoreCase(entityNm)) {
			fieldNm = "sale_price";
		} else if ("purPrice".equalsIgnoreCase(entityNm)) {
			fieldNm = "pur_price";
		} else if ("vendorId".equalsIgnoreCase(entityNm)) {
			fieldNm = "vendor_id";
		} else if ("saleDeliverTime".equalsIgnoreCase(entityNm)) {
			fieldNm = "sale_deliver_time";
		} else if ("purDeliverTime".equalsIgnoreCase(entityNm)) {
			fieldNm = "pur_deliver_time";
		} else if ("orderMemo".equalsIgnoreCase(entityNm)) {
			fieldNm = "order_memo";
		} else if ("hidenInd".equalsIgnoreCase(entityNm)) {
			fieldNm = "hiden_ind";
		} else if ("lcPrice".equalsIgnoreCase(entityNm)) {
			fieldNm = "lc_price";
		} else if ("customizeAudit".equalsIgnoreCase(entityNm)) {
			fieldNm = "customize_audit";
		} else if ("customizeInd".equalsIgnoreCase(entityNm)) {
			fieldNm = "customize_ind";
		} else if ("accessoriesInd".equalsIgnoreCase(entityNm)) {
			fieldNm = "accessories_ind";
		} else if ("updateBy".equalsIgnoreCase(entityNm)) {
			fieldNm = "update_by";
		} else if ("updateDate".equalsIgnoreCase(entityNm)) {
			fieldNm = "update_date";
		}
		return fieldNm;
	}

	@Override
	protected String buildSql() {
		String sql = "select t.*, gc.code categoryCode, gc.name categoryName, v.code vendorCode from t_goods t left join t_goods_category gc "
		+ "on t.category_id = gc.id left join t_vendor v on t.vendor_id = v.id where t.expired_ind = 'N'";
		return sql;
	}

	public Goods add(GoodsVo vo) {		
		Goods entity = new Goods();
		entity.setCode(vo.getCode());
		entity.setName(vo.getName());
		entity.setCategoryId(vo.getCategoryId());
		entity.setType(vo.getType());
		entity.setTexture(vo.getTexture());
		entity.setSalePrice(vo.getSalePrice());
		entity.setPurPrice(vo.getPurPrice());
		entity.setUnit(vo.getUnit());
		entity.setVendorId(vo.getVendorId());
		entity.setSaleDeliverTime(vo.getSaleDeliverTime());
		entity.setPurDeliverTime(vo.getPurDeliverTime());
		entity.setOrderMemo(vo.getOrderMemo());
		entity.setLcPrice(vo.getLcPrice());
		entity.setWeight(vo.getWeight());
		entity.setVolume(vo.getVolume());
		entity.setColor(vo.getColor());
		entity.setSpec(vo.getSpec());
		entity.setUpdateBy(vo.getUpdateBy());
		entity.setUpdateDate(new Date());
		entity.setCount(vo.getCount());
		if (vo.isHidden()) {
			entity.setHidenInd("Y");
		} else {
			entity.setHidenInd("N");
		}
		if (vo.isCustomize()) {
			entity.setCustomizeAudit("N");
			entity.setCustomizeInd("Y");
		} else {
			entity.setCustomizeAudit("Y");
			entity.setCustomizeInd("N");
		}
		if (vo.isAccessories()) {
			entity.setAccessoriesInd("Y");
		} else {
			entity.setAccessoriesInd("N");
		}
		return goodsDao.save(entity);
	}

	public void update(GoodsVo vo) {
		Goods entity = goodsDao.findOne(vo.getGoodsId());
		entity.setCode(vo.getCode());
		entity.setName(vo.getName());
		entity.setCategoryId(vo.getCategoryId());
		entity.setType(vo.getType());
		entity.setTexture(vo.getTexture());
		entity.setSalePrice(vo.getSalePrice());
		entity.setPurPrice(vo.getPurPrice());
		entity.setUnit(vo.getUnit());
		entity.setVendorId(vo.getVendorId());
		entity.setSaleDeliverTime(vo.getSaleDeliverTime());
		entity.setPurDeliverTime(vo.getPurDeliverTime());
		entity.setOrderMemo(vo.getOrderMemo());
		entity.setLcPrice(vo.getLcPrice());
		entity.setWeight(vo.getWeight());
		entity.setVolume(vo.getVolume());
		entity.setColor(vo.getColor());
		entity.setSpec(vo.getSpec());
		entity.setUpdateBy(vo.getUpdateBy());
		entity.setUpdateDate(new Date());
		entity.setCount(vo.getCount());
		if (vo.isHidden()) {
			entity.setHidenInd("Y");
		} else {
			entity.setHidenInd("N");
		}
		if (vo.isCustomize()) {
			entity.setCustomizeAudit("N");
			entity.setCustomizeInd("Y");
		} else {
			entity.setCustomizeAudit("Y");
			entity.setCustomizeInd("N");
		}
		if (vo.isAccessories()) {
			entity.setAccessoriesInd("Y");
		} else {
			entity.setAccessoriesInd("N");
		}
		goodsDao.save(entity);
		
	}

	public void delete(Long id) {
		Goods entity = goodsDao.findOne(id);
		entity.setExpiredInd("Y");
		entity.setExpiredDate(new Date());
		goodsDao.save(entity);
		
	}
	
	public void goodsAudit(Long goodsId, String userName) {
		Goods goods = goodsDao.findOne(goodsId);
		goods.setCustomizeAudit("Y");
		goods.setUpdateBy(userName);
		goods.setUpdateDate(new Date());
		goodsDao.save(goods);
	}
	
	public void goodsHide(Long goodsId, String hiddenInd, String userName) {
		Goods goods = goodsDao.findOne(goodsId);
		goods.setHidenInd(hiddenInd);
		goods.setUpdateBy(userName);
		goods.setUpdateDate(new Date());
		goodsDao.save(goods);
	}

}
