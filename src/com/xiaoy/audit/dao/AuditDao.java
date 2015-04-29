package com.xiaoy.audit.dao;

import java.util.List;

import com.xiaoy.audit.web.form.AuditForm;
import com.xiaoy.base.dao.Common;
import com.xiaoy.base.entites.Audit;

public interface AuditDao extends Common<Audit>
{

	/**
	 * 带分页的，条件查询出现所有符合条件的待审核申报。<br>
	 * 
	 * @param auditForm
	 *            查询条件
	 * @return List&ltObject[]&gt
	 */
	List<Object[]> findAuditInfoWaitList(AuditForm auditForm);

	/**
	 * 根据条件查询统计出现符合条件的待审核申报
	 * @param auditForm		查询条件
	 * @return	记录总数
	 */
	int countAuditInfoWait(AuditForm auditForm);

	/**
	 * 通过审核的uuid查询出现审核信息及相关信息
	 * @param auditForm		含有审核的uuid
	 * @return	Object[]
	 */
	Object[] findAuditInfoWaitByAuditUuid(AuditForm auditForm);

	/**
	 * 带分页的，条件查询出来所有符合条件的通过审核的申报
	 * @param auditForm		查询条件
	 * @return	List&ltObject[]&gt
	 */
	List<Object[]> findAuditInfoPassList(AuditForm auditForm);

	/**
	 * 根据条件查询统计出现符合条件的审核通过申报
	 * @param auditForm		查询条件
	 * @return	记录总数
	 */
	int countAuditInfoPass(AuditForm auditForm);

}
