package com.xiaoy.audit.service;

import com.xiaoy.base.entites.Reporting;

public interface AuditService {

	/**
	 * 保存故障审核信息
	 * @param entity	申报故障信息
	 */
	void saveAudit(Reporting entity);

}
