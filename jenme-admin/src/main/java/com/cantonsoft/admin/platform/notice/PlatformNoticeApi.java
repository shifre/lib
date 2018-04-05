package com.cantonsoft.admin.platform.notice;

import javax.annotation.PostConstruct;

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

@JsonApi
@Lazy
@RequestMapping(value = "/platform/notice")
@Access(value = "fn.main.notice")
public class PlatformNoticeApi extends BaseEntityApi<Notice, Long, Notice, Notice>{
	@Autowired
	NoticeService noticeService;
	
	@PostConstruct
	private void init()
	{
		searchDef = new SearchDef().addField("title");
	}

	@Override
	protected BaseEntityService<Notice,Long> getEntityService() {
		// TODO Auto-generated method stub
		return noticeService;
	}
	
}
