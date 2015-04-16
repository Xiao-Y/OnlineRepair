package com.xiaoy.audit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.audit.service.AuditService;
import com.xiaoy.base.entites.Audit;
import com.xiaoy.base.entites.Reporting;

@Service
@Transactional(readOnly=true)
public class AuditServiceImpl implements AuditService {

	@Resource
	private AuditDao auditDao;
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveAudit(Reporting reporting) {
		Audit entity = this.reportingToAudit(reporting);
		auditDao.saveObject(entity);
	}

	/**
	 * 从申报故障信息类中获取申报uuid和用户uuid.
	 * 固定系统审核状为1时表示待审核。维护状态为0时表示，还未审核。
	 * @param reporting
	 * @return
	 */
	private Audit reportingToAudit(Reporting reporting) {
		Audit entity = new Audit();
		entity.setReportingUuid(reporting.getReportingUuid());
		entity.setUserUuid(reporting.getUser().getUserUuid());
		
		entity.setMaintainStatCode("0");
		entity.setAuditStatCode("1");
		
		return entity;
	}

}
