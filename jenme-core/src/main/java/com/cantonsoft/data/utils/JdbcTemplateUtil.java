package com.cantonsoft.data.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUtil {

	private static JdbcTemplate jdbcTemplate;

	private static DataSource getDataSource() {
		DataSource source = null;
		try {
			Context ic = new InitialContext();
			source = (DataSource) ic.lookup("java:comp/env/jdbc/cms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source;
	}

	public synchronized static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			setJdbcTemplate(new JdbcTemplate(getDataSource()));
		}
		return jdbcTemplate;
	}

	private static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplateUtil.jdbcTemplate = jdbcTemplate;
	}

}
