package com.cantonsoft.core.common.info.area.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface AreaDao extends BaseEntityDao<Area, Long>{

}
