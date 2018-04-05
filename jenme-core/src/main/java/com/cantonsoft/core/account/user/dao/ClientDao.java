package com.cantonsoft.core.account.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cantonsoft.core.cloud.client.model.Client;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

public interface ClientDao extends BaseEntityDao<Client, Long> {
	Client findByTitle(String title);
	List<Client> findTop5ByPartnerIdOrderByCreatedTimeDesc(Long partnerId);
	List<Client> findByPartnerId(Long partnerId);
	@Query("SELECT a FROM Client a where a.enabled=1 AND a.partnerId = :partnerId")
	List<Client> findEnabled(Long partnerId);
	@Query("SELECT a.type, COUNT(a.type) FROM Client a WHERE a.partnerId = :partnerId")
	List<Object[]> countByType(Long partnerId);
}
