package com.cantonsoft.core.common.thumbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.thumbs.model.Thumbs;
import com.cantonsoft.core.common.thumbs.model.ThumbsDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;

@Service
@Lazy
public class ThumbsService extends BaseEntityService<Thumbs, Long> {

	@Autowired
	private ThumbsDao dao;

	@Override
	protected BaseEntityDao<Thumbs, Long> getDao() {
		return dao;
	}
	
	public List<Thumbs> findByRefIdAndRefType(Long refId,String refType){
		return dao.findByRefIdAndRefType(refId,refType);
	}
	
	public Integer getSort(Long refId, String refType)
	{
		Integer sort = dao.findMaxSort(refId,refType);
		return sort == null ? 1 : sort + 1;
	}
}
