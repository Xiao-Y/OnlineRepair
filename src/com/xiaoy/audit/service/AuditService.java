package com.xiaoy.audit.service;

import java.util.List;

import com.xiaoy.audit.web.form.AuditForm;


/**
 * 申报信息审核<br/>
 * 1.进入审核列表管理员修改审核状态。<br/>
 * 如果审核通过：<br/>
 * 1.分配维护人员及维护类别，<br/>
 * 2.后台修改设备状态表中的设备状态<br/>
 * 如果审核不通过：<br/>
 * 1.填写审核不通过原因
 *
 * @author XiaoY
 * @date: 
 * 2015年4月26日 下午5:03:02
 */
public interface AuditService {

	/**
	 * 带分页的，条件查询出现所有符合条件的待审核申报。<br>
	 * @param auditForm		查询条件
	 * @return	List&ltAuditForm&gt
	 */
	List<AuditForm> findAuditInfoWaitList(AuditForm auditForm);

	/**
	 * 根据条件查询统计出现符合条件的待审核申报
	 * @param auditForm		查询条件
	 * @return	记录总数
	 */
	int countAuditInfoWait(AuditForm auditForm);

	/**
	 * 通过审核的uuid查询出现审核信息及相关信息
	 * @param auditForm		含有审核的uuid
	 * @return	AuditForm
	 */
	AuditForm findAuditInfoWaitByAuditUuid(AuditForm auditForm);

//	/**
//	 * 保存故障审核信息
//	 * @param entity	申报故障信息
//	 */
//	void saveAudit(Reporting entity);

}
