package com.cantonsoft.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cantonsoft.data.rowmapping.IRowMapper;
import com.cantonsoft.data.utils.JdbcTemplateUtil;

public abstract class AbstractMappedDataService<T> {

	final IRowMapper<T> defaultRowMapper;

	AbstractMappedDataService() {
		this.defaultRowMapper = new IRowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rowMapping(rs, rowNum);
			}

		};
	}

	AbstractMappedDataService(IRowMapper<T> defaultRowMapper) {
		this.defaultRowMapper = defaultRowMapper;
	}

	protected List<T> find(String sql) {
		return getJdbcTemplate().query(sql, defaultRowMapper);

	}

	protected List<T> find(String sql, IRowMapper<T> rowMapper) {
		return getJdbcTemplate().query(sql, rowMapper);

	}

	protected abstract T rowMapping(ResultSet rs, int rowNum) throws SQLException;

	protected java.util.Date getDate(java.sql.Date sqlDate) {
		if (sqlDate == null)
			return null;
		return new java.util.Date(sqlDate.getTime());
	}

	/**
	 * Jdbc Template from Spring
	 */
	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = JdbcTemplateUtil.getJdbcTemplate();
		}
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
