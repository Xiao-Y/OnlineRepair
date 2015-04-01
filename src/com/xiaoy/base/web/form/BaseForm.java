package com.xiaoy.base.web.form;

public class BaseForm {

	// 每页要显示的记录数
	protected Integer pageSize = 12;
	
	// 页号(当前页)
	protected Integer pageNo = 1;
	
	// 总记录数
	protected Integer recordCount;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
}
