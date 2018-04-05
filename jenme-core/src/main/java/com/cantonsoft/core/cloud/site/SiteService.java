package com.cantonsoft.core.cloud.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.cloud.site.model.Site;
import com.cantonsoft.core.cloud.site.model.SiteDao;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class SiteService extends BaseEntityService<Site,Long> {
	@Autowired
	private SiteDao dao;
	
	@Override
	protected BaseEntityDao<Site, Long> getDao() {
		return dao;
	}

	@Override
	public void validateAdd(Site entity) {
		super.validateAdd(entity);
		checkAddData(entity);
	}
	
	@Override
	public void validateUpdate(Site entity) {
		super.validateUpdate(entity);
		checkUpdateData(entity);
	}

	public void checkAddData(Site data) {
		Site site1 = dao.findByTitle(data.getTitle());
		if(null != site1) 
		{
			throw new ServiceException("error.duplication.name");
		}
		
		/*Site site2 = dao.findByDomain(data.getDomain());
		
		if(null != site2) 
		{
			throw new ServiceException("error.site.duplication.domain");
		}*/
	}
	
	public void checkUpdateData(Site data) {
		Site site1 = dao.findByTitle(data.getTitle());
		if(null != site1 && !site1.getId().equals(data.getId())) 
		{
			throw new ServiceException("error.duplication.name");
		}
		
		/*Site site2 = dao.findByDomain(data.getDomain());
		
		if(null != site2 && !site2.getId().equals(data.getId()))
		{
			throw new ServiceException("error.site.duplication.domain");
		}*/
	}
	
	public List<Site> findByClientId(Long clientId) {
		return dao.findByClientId(clientId);
	}

}