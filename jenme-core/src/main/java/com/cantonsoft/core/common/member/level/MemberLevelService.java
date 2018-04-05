package com.cantonsoft.core.common.member.level;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cantonsoft.core.common.member.level.model.MemberLevelDao;
import com.cantonsoft.core.common.member.level.model.MemberLevel;
import com.cantonsoft.framework.mvc.service2.BaseEntityDao;
import com.cantonsoft.framework.mvc.service2.BaseEntityService;
@Service
@Lazy
public class MemberLevelService extends BaseEntityService<MemberLevel, Long>{
	@Autowired
	private MemberLevelDao dao;

	@Override
	protected BaseEntityDao<MemberLevel, Long> getDao() {
		return dao;
	}
	
	public List<MemberLevel> findByClientId(Long clientId){
		return dao.findByClientId(clientId);
	}

	public Long getMaxPosition(){
		Long position = dao.findMaxPosition();
		return position == null ? 0 : position;
	}
	
	public MemberLevel findBaseMemberLevel(Long clientId){
		Sort sort = new org.springframework.data.domain.Sort(org.springframework.data.domain.Sort.Direction.ASC, "level");

		return dao.findTopByClientId(clientId,sort);
	}
}
