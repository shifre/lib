package com.cantonsoft.core.cloud.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.core.cloud.partner.model.Partner;
import com.cantonsoft.core.cloud.partner.model.PartnerDao;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class PartnerService extends BaseEntityService<Partner, Long> {
	@Autowired
	private PartnerDao partnerDao;
	@Autowired
	private GenericUserService genericUserService;
	
	@Override
	protected BaseEntityDao<Partner, Long> getDao() {
		return partnerDao;
	}
	
	@Override
	public void add(Partner entity) {
		super.add(entity);
		genericUserService.createPartnerUser(entity);
	}

	@Override
	public void validateAdd(Partner entity) {
		super.validateAdd(entity);
		checkTitle(entity.getTitle(), null);
	}

	@Override
	public void validateUpdate(Partner entity) {
		super.validateUpdate(entity);
		checkTitle(entity.getTitle(), entity.getId());
	}

	public Partner findByTitle(String title)
	{
		return partnerDao.findByTitle(title);
	}
	
	public void checkTitle(String title, Long id) {
		Partner partner = partnerDao.findByTitle(title);
		if(null != partner && !id.equals(partner.getId())) {
			throw new ServiceException("error.duplication.name");
		}
	}
}
