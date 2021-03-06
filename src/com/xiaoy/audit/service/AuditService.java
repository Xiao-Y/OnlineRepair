package com.xiaoy.audit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
 * @date: 2015年4月26日 下午5:03:02
 */
public interface AuditService
{

	/**
	 * 带分页的，条件查询出现所有符合条件的待审核申报。<br>
	 * 
	 * @param auditForm
	 *            查询条件
	 * @return List&ltAuditForm&gt
	 */
	List<AuditForm> findAuditInfoWaitList(AuditForm auditForm);

	/**
	 * 根据条件查询统计出现符合条件的待审核申报
	 * 
	 * @param auditForm
	 *            查询条件
	 * @return 记录总数
	 */
	int countAuditInfoWait(AuditForm auditForm);

	/**
	 * 通过审核的uuid查询出现审核信息及相关信息
	 * 
	 * @param auditForm
	 *            含有审核的uuid
	 * @return AuditForm
	 */
	AuditForm findAuditInfoWaitByAuditUuid(AuditForm auditForm);

	/**
	 * 保存编辑后的待审核信息<br/>
	 * 1.修改设备状态信息为异常<br/>
	 * 2.添加评论信息。<br/>
	 * 3.修改审核信息，添加审核时间、审核人。如果驳回，添加驳回信息<br/>
	 * 
	 * @param auditForm		审核信息
	 */
	void auditInfoWaitSave(AuditForm auditForm,HttpServletRequest request);

	/**
	 * 查询出现审核通过的故障申报
	 * @param auditForm		查询条件
	 * @return	List&ltAuditForm&gt
	 */
	List<AuditForm> findAuditInfoPassList(AuditForm auditForm);

	/**
	 * 根据条件统计出现审核通过的记录
	 * @param auditForm		查询条件
	 * @return	int
	 */
	int countAuditInfoPass(AuditForm auditForm);

	/**
	 * 通过审核的uuid查询出通过的审核信息及相关信息
	 * 
	 * @param auditForm
	 *            含有审核的uuid
	 * @return AuditForm
	 */
	AuditForm findAuditInfoPassByAuditUuid(AuditForm auditForm);

	/**
	 * 保存修改审核通过的数据<p/>
	 * 1.修改为待审核状态：<br/>
	 * ①评价表：删除评价信息<br/>
	 * ②设备状态表：修改设备状态为正常运行<br/>
	 * ③审核表：修改维护人员的uuid、维护状态、完成时间都为空、审核状态为待审核<p/>
	 * 
	 * 2.修改为驳回：<br/>
	 * ①评价表：删除评价信息<br/>
	 * ②设备状态表：修改设备状态为正常运行<br/>
	 * ③审核表：修改维护人员的uuid、维护状态、完成时间都为空，审核状态为驳回<p/>
	 * 
	 * 3.没有对审核进行修改<br/>
	 * 审核表：修改维护人员的uuid、维护状态为未处理、完成时间为空<p/>
	 * @return
	 */
	void auditInfoPassSave(AuditForm auditForm, HttpServletRequest request);

	/**
	 * 根据条件查询出审核未通过的申报故障
	 * @param auditForm		查询条件
	 * @return List&ltAuditForm&gt
	 */
	List<AuditForm> auditInfoRefuseList(AuditForm auditForm);

	/**
	 * 根据条件统计出现审核未通过的记录
	 * @param auditForm		查询条件
	 * @return	int
	 */
	int countAuditInfoRefuse(AuditForm auditForm);

	/**
	 * 通过审核的uuid查询出审核未通过信息及相关信息
	 * 
	 * @param auditForm
	 *            含有审核的uuid
	 * @return AuditForm
	 */
	AuditForm findAuditInfoRefuseByAuditUuid(AuditForm auditForm);

	/**
	 * 保存审核未通过的修改
	 * @param auditForm
	 * @param request
	 */
	void auditRefuseSave(AuditForm auditForm, HttpServletRequest request);
}
