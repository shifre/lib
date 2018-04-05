package com.cantonsoft.core.common.inventory.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.cantonsoft.framework.mvc.service2.BaseEntityDao;

@Repository
@Lazy
public interface InventoryDao extends BaseEntityDao<Inventory, Long>{

}
