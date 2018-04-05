package com.cantonsoft.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.cantonsoft.data.rowmapping.IRowMapper;
import com.cantonsoft.data.utils.JdbcTemplateUtil;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.framework.mvc.service2.filter.Sort;

public abstract class AbstractDataService<T> {
	
	final IRowMapper<T> defaultRowMapper;
	
	protected AbstractDataService() {
		this.defaultRowMapper = new IRowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rowMapping(rs, rowNum);
			}
			
			@SuppressWarnings("unused")
			public boolean isExistColumn(ResultSet rs, String columnName) {
				try {
					if (rs.findColumn(columnName) > 0) {
						return true;
					}
				} catch (SQLException e) {
					return false;
				}
				return false;
			}

		};
	}
	
	protected abstract String buildSql();
	
	protected abstract T rowMapping(ResultSet rs, int rowNum) throws SQLException;
	
	protected abstract String convertFieldName(String entityNm);

	public PageData<T> getPage(String sql, PageFilter filter) {
		PageRequest pageRequest = new PageRequest(filter.getPage(), filter.getSize());
		int totalRows = JdbcTemplateUtil.getJdbcTemplate().queryForObject("select count(*) from (" + sql + ") t9", Integer.class);
		List<T> list = JdbcTemplateUtil.getJdbcTemplate().query("select t9.* from (" + sql + ") t9 limit ?,?",
				new Object[] { (pageRequest.getPageNumber() * pageRequest.getPageSize()), pageRequest.getPageSize() }, defaultRowMapper);

		PageData<T> data = new PageData<>();
		data.setTotal(totalRows);
		data.setElements(list);
		data.setSize(pageRequest.getPageSize());

		return data;
	}
	
	@Transactional(readOnly = true)
	public T find(Long id) {
		String sql = buildSql() + " and t.id = " + id;
		return JdbcTemplateUtil.getJdbcTemplate().queryForObject(sql, defaultRowMapper);
	}
	
	@Transactional(readOnly = true)
	public PageData<T> findAll(PageFilter filter) {
		String sql = buildSql();

		sql = buildSqlWithFilterAndSort(sql, filter);
		return getPage(sql, filter);
	}
	
	public String buildSqlWithFilterAndSort(String sql, PageFilter filter) {

		if (filter.getConds() != null && !filter.getConds().isEmpty()) {
			for (Cond cond : filter.getConds()) {
				if(StringUtils.isEmpty(cond.getValue())){
					continue;
				}
				if (Cond.OP_LEFT_LIKE.equals(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " like '" + cond.getValue() + "%'";
				} else if (Cond.OP_LIKE.equals(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " like '%" + cond.getValue() + "%'";
				} else if (Cond.OP_EQ.equals(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " = '" + cond.getValue() + "'";

				} else if (Cond.OP_AFTER.equalsIgnoreCase(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " >= STR_TO_DATE(' "+ cond.getValue()  +" 00:00:00','%Y-%m-%d')" ;
				} else if (Cond.OP_BEFORE.equalsIgnoreCase(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " <= STR_TO_DATE(' "+ cond.getValue()  +" 00:00:00','%Y-%m-%d')" ;
				} else if (Cond.OP_GT.equals(cond.getOp())) {
					sql = sql + " and " + convertFieldName(cond.getName()) + " > '" + cond.getValue() + "'";

				}
				
			}
		}
		if (filter.getSorts() != null && !filter.getSorts().isEmpty()) {
			for (Sort sort : filter.getSorts()) {
				if (sort.isAsc()) {
					sql = sql + " order by " + convertFieldName(sort.getName()) + " asc";
				} else {
					sql = sql + " order by " + convertFieldName(sort.getName()) + " desc";
				}
			}
		}
		
		return sql;
	}
	
	@Transactional(readOnly = true)
	public List<T> findAll(String sql) {
		return JdbcTemplateUtil.getJdbcTemplate().query(sql, defaultRowMapper);
	}
	
	@Transactional(readOnly = true)
	public List<T> findAllWithFilterAndSort(PageFilter filter) {
		String sql = buildSql();
		sql = buildSqlWithFilterAndSort(sql, filter);
		return JdbcTemplateUtil.getJdbcTemplate().query(sql, defaultRowMapper);
	}
}
