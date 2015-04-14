package com.xiaoy.base.entites;

import java.util.Date;

/**
 * 审核申报信息表
 *
 * @author XiaoY
 * @date 2015年4月14日
 */
public class Audit {

	/*审核uuid*/
	private String auditUuid;
	/* 审核状态 */
	private String auditStatCode;
	/* 审核时间 */
	private Date auditTime;
	/* 维护状态 */
	private String maintainStatCode;
	/* 完成时间 */
	private Date finishTime;
	/* 驳回原因 */
	private String failAccount;
	/* 备注 */
	private String remark;
	/* 申报故障信息uuid */
	private String reportingUuid;
	/* 维护人员Uuid */
	private String maintainUuid;
	/* 用户uuid */
	private String userUuid;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getMaintainUuid() {
		return maintainUuid;
	}

	public void setMaintainUuid(String maintainUuid) {
		this.maintainUuid = maintainUuid;
	}

	public String getAuditUuid() {
		return auditUuid;
	}

	public void setAuditUuid(String auditUuid) {
		this.auditUuid = auditUuid;
	}

	public String getAuditStatCode() {
		return auditStatCode;
	}

	public void setAuditStatCode(String auditStatCode) {
		this.auditStatCode = auditStatCode;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getMaintainStatCode() {
		return maintainStatCode;
	}

	public void setMaintainStatCode(String maintainStatCode) {
		this.maintainStatCode = maintainStatCode;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getFailAccount() {
		return failAccount;
	}

	public void setFailAccount(String failAccount) {
		this.failAccount = failAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReportingUuid() {
		return reportingUuid;
	}

	public void setReportingUuid(String reportingUuid) {
		this.reportingUuid = reportingUuid;
	}
}
