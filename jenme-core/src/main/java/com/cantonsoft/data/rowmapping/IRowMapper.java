package com.cantonsoft.data.rowmapping;

import org.springframework.jdbc.core.RowMapper;

public interface IRowMapper<T> extends RowMapper<T> {

//	public static java.util.Date getDate(java.sql.Date sqlDate) {
//		if (sqlDate == null)
//			return null;
//		return new java.util.Date(sqlDate.getTime());
//	}
//
//	public static java.util.Date getDate(java.sql.Timestamp sqlDateTime) {
//		if (sqlDateTime == null)
//			return null;
//		return new java.util.Date(sqlDateTime.getTime());
//	}


}
