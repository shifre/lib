package com.cantonsoft.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;
import com.cantonsoft.vo.UserVo;

@Named("userDataService")
@Transactional(readOnly = true)
public class UserDataService extends AbstractDataService<UserVo> {

	private static final Logger log = LoggerFactory.getLogger(UserDataService.class.getName());

	@Transactional(readOnly = true)
	public PageData<UserVo> findAll(PageFilter filter) {
		String sql = "SELECT * FROM USER_T";
		return this.getPage(sql, filter);
	}

	@Override
	protected UserVo rowMapping(ResultSet rs, int rowNum) throws SQLException {
		UserVo vo = new UserVo();
		vo.setTitle(rs.getString("title"));
		vo.setPhone("phone");

		return vo;
	}

	@Override
	protected String convertFieldName(String entityNm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String buildSql() {
		// TODO Auto-generated method stub
		return null;
	}

}
