package com.cantonsoft.core.common.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cantonsoft.core.common.notice.model.Notice;
import com.cantonsoft.core.common.notice.model.NoticeDao;
import com.cantonsoft.framework.mvc.model.HqlQuery;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Component
public class NoticeService extends BaseEntityService<Notice,Long> {
	@Autowired
	private	NoticeDao dao;
	@Autowired
	private HqlQuery query;
	
	private String FIND_BY_TYPE = "SELECT a FROM Notice a WHERE a.status = 1 and a.type = :type ORDER BY createdTime DESC";
	private String FIND_ALL = "SELECT a FROM Notice a WHERE a.status = 1 ORDER BY createdTime DESC";
	
	@Override
	protected BaseEntityDao<Notice, Long> getDao() {
		return dao;
	}
	
	public List<Notice> getPatnerNoticeList() {
		return dao.findPatnerNotice();
	}
	
	public List<Notice> getClientNoticeList() {
		return dao.findClientNotice();
	}
	
	public List<Notice> getNoticesByType(Integer type) {
		Map<String, Object> map = new HashMap<>();
		if (type == 1) 
		{
			map.put("type", type);
			return query.query(FIND_BY_TYPE, map, false);
		}
		else
		{
			return query.queryTop10(FIND_ALL, null, false);
		}
	}

}
