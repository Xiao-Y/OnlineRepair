package com.xiaoy.base.util;


public class PageBean
{
	
	// 每页要显示的记录数
	private int pageSize = 15;
	
	// 页号
	private int pageNo = 1;
	
	// 总记录数
	private int recordCount = 0;


	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public int getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}
}
