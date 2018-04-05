package com.cantonsoft.core.cloud.site.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.site.view.model.SiteView;
import com.cantonsoft.core.cloud.site.view.model.SiteViewDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class SiteViewService extends BaseEntityService<SiteView,Long> {
	@Autowired
	private SiteViewDao dao;

	@Override
	protected BaseEntityDao<SiteView, Long> getDao() {
		return dao;
	}
	
	public List<SiteView> findByPartnerId(Long partnerId){
		return dao.findByPartnerId(partnerId);
	}
}