package com.cantonsoft.core.common.template.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
@Repository
@Lazy
public interface TemplateDao extends BaseEntityDao<Template, Long>{
	
	@Query("SELECT MAX(a.id) FROM Template a")
	Long findMaxId();

}
