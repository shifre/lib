package com.cantonsoft.core.common.goods.detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.goods.detail.model.GoodsDetailDao;
import com.cantonsoft.data.AbstractDataService;
import com.cantonsoft.vo.GoodsDetailVo;

@Service
@Lazy
public class GoodsDetailService extends AbstractDataService<GoodsDetailVo> {

	@Autowired
	private GoodsDetailDao goodsDetailDao;

	@Override
	protected String buildSql() {
		String sql = "select t.*, g.name goodsName from t_goods_detail t left join t_goods g on t.ref_goods_id = g.id where g.expired_ind = 'N'";
		return sql;
	}

	@Override
	protected GoodsDetailVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		GoodsDetailVo vo = new GoodsDetailVo();
		vo.setGoodsDetialId(rs.getLong("id"));
		vo.setGoodsId(rs.getLong("goods_id"));
		vo.setRefGoodsId(rs.getLong("ref_goods_id"));
		vo.setQuantity(rs.getLong("quantity"));
		vo.setGoodsName(rs.getString("goodsName"));

		return vo;
	}

	@Override
	protected String convertFieldName(String entityNm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<GoodsDetailVo> findAllByGoodsId(Long goodsId) {
		String sql = buildSql() + " and t.goods_id = " + goodsId;
		
		return findAll(sql);
	}

}
