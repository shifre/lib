package com.cantonsoft.core.cloud.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.account.user.GenericUserService;
import com.cantonsoft.core.account.user.dao.ClientDao;
import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.framework.exception.ServiceException;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class ClientService extends BaseEntityService<Client, Long> {
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private GenericUserService genericUserService;
	
	@Override
	protected BaseEntityDao<Client, Long> getDao() {
		return clientDao;
	}

	@Override
	public void add(Client entity) {
		super.add(entity);
		genericUserService.createClientUser(entity);
	}

	
	@Override
	public void validateAdd(Client entity) {
		super.validateAdd(entity);
		checkTitle(entity.getTitle(), null);
	}
	
	@Override
	public void validateUpdate(Client entity) {
		super.validateUpdate(entity);
		checkTitle(entity.getTitle(), entity.getId());
	}

	public void checkTitle(String title, Long id) {
		Client client = clientDao.findByTitle(title);
		if(null != client && !client.getId().equals(id)) {
			throw new ServiceException("error.duplication.name");
		}
	}
	
	public List<Client> findNewestClient(Long partnerId) {
		return clientDao.findTop5ByPartnerIdOrderByCreatedTimeDesc(partnerId);
	}
	
	public Map<String, Long> findTypeCount(Long partnerId) {
		
		List<Object[]> list = clientDao.countByType(partnerId);
		Map<String, Long> map = new HashMap<String, Long>();
		Long all = new Long(0);
		for(Object[] obj : list) {
			String key = (String) obj[0];
			Long value = (Long) obj[1];
			map.put(key, value);
			all += value;
		}
		map.put("all", all);
		
		return map;
	}
	 
	public Client findByTitle(String title)
	{
		return clientDao.findByTitle(title);
	}
}
