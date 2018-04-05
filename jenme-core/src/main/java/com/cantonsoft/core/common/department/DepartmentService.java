package com.cantonsoft.core.common.department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.department.model.Department;
import com.cantonsoft.core.common.department.model.DepartmentDao;
import com.cantonsoft.data.AbstractDataService;
import com.cantonsoft.vo.DepartmentVo;

@Service
@Lazy
public class DepartmentService extends AbstractDataService<DepartmentVo> {
	@Autowired
	private DepartmentDao dao;
	
//	@Transactional(readOnly = true)
//	public DepartmentVo find(Long id) {
//		String sql = "SELECT * FROM t_department where id = " + id;
//		return this.find(sql);
//	}
//	
//	@Transactional(readOnly = true)
//	public PageData<DepartmentVo> findAll(PageFilter filter) {
//		String sql = "SELECT * FROM t_department where expired_ind = 'N'";
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
	protected String convertFieldName(String entityNm){
		String fieldNm = entityNm;
		if ("deptCode".equalsIgnoreCase(entityNm)) {
			fieldNm = "dept_code";
		} else if ("deptName".equalsIgnoreCase(entityNm)) {
			fieldNm = "dept_name";
		}
		return fieldNm;
	}
	
	@Override
	protected DepartmentVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		DepartmentVo vo = new DepartmentVo();
		vo.setDeptId(rs.getLong("id"));
		vo.setDeptCode(rs.getString("dept_code"));
		vo.setDeptName(rs.getString("dept_name"));
		vo.setPhone(rs.getString("phone"));
		vo.setAddress(rs.getString("address"));
		boolean isStore = "Y".equals(rs.getString("store_ind")) ? true : false;
		vo.setStore(isStore);
		boolean isOpen = "Y".equals(rs.getString("open_ind")) ? true : false;
		vo.setOpen(isOpen);

		return vo;
	}

	@Override
	protected String buildSql() {
		String sql = "select t.* from t_department t where t.expired_ind = 'N'";
		return sql;
	}
	
	public List<DepartmentVo> findAllByStoreInd(String storeInd) {
		String sql = buildSql() + "and t.store_ind = '" + storeInd + "'";
		return findAll(sql);
	}
	
	public Department add(DepartmentVo vo) {
		Department entity = new Department();
		entity.setDeptCode(vo.getDeptCode());
		entity.setDeptName(vo.getDeptName());
		entity.setPhone(vo.getPhone());
		entity.setAddress(vo.getAddress());
		if (vo.isStore()) {
			entity.setStoreInd("Y");
		} else {
			entity.setStoreInd("N");
		}
		if (vo.isOpen()) {
			entity.setOpenInd("Y");
		} else {
			entity.setOpenInd("N");
		}
		return dao.save(entity);
	}
	
	public void update(DepartmentVo vo) {
		Department entity = dao.findOne(vo.getDeptId());
		entity.setDeptCode(vo.getDeptCode());
		entity.setDeptName(vo.getDeptName());
		entity.setPhone(vo.getPhone());
		entity.setAddress(vo.getAddress());
		if (vo.isStore()) {
			entity.setStoreInd("Y");
		} else {
			entity.setStoreInd("N");
		}
		if (vo.isOpen()) {
			entity.setOpenInd("Y");
		} else {
			entity.setOpenInd("N");
		}
		dao.save(entity);
	}
	
	public void delete(Long id) {
		Department entity = dao.findOne(id);
		entity.setExpiredInd("Y");
		dao.save(entity);
	}

}
