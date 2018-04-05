package com.cantonsoft.core.common.info.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.info.vendor.model.Vendor;
import com.cantonsoft.core.common.info.vendor.model.VendorDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class VendorService extends BaseEntityService<Vendor, Long> {

	@Autowired
	private VendorDao vendorDao;
	
	@Override
	protected VendorDao getDao() {
		return vendorDao;
	}

}
