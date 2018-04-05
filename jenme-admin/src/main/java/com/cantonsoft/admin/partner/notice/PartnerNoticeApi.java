package com.cantonsoft.admin.partner.notice;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.common.notice.NoticeService;
import com.cantonsoft.core.common.notice.model.Notice;
import com.cantonsoft.framework.api.JsonApi;
import com.cantonsoft.framework.mvc.meta.Access;
import com.cantonsoft.framework.mvc.model.filter.SearchDef;
import com.cantonsoft.framework.mvc.service2.BaseEntityApi;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
import com.cantonsoft.framework.mvc.service2.filter.Cond;
import com.cantonsoft.framework.mvc.service2.filter.PageData;
import com.cantonsoft.framework.mvc.service2.filter.PageFilter;

@JsonApi
@Lazy
@RequestMapping(value = "/partner/notice")
@Access(value = "fn.partner.notice")
public class PartnerNoticeApi extends BaseEntityApi<Notice, Long, Notice, Notice>{
	@Autowired
	NoticeService noticeService;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Notice,Long> getEntityService() {
		return noticeService;
	}

	@Override
	protected PageData<Notice> doSearch(PageFilter filter, HttpServletRequest request, HttpServletResponse response) {
		filter.getConds().add(new Cond("type", Cond.OP_EQ, String.valueOf(1)));
		filter.getConds().add(new Cond("status", Cond.OP_EQ, String.valueOf(1)));
		return super.doSearch(filter, request, response);
	}
	
	
	
}
