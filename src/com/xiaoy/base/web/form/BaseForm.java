package com.xiaoy.base.web.form;

import java.io.File;

public class BaseForm {

	// 每页要显示的记录数
	protected Integer pageSize = 12;
	
	// 页号(当前页)
	protected Integer pageNo = 1;
	
	// 总记录数
	protected Integer recordCount;
	
	//图片文件
	protected File image;
	
	//图片文件名字
	protected String imageFileName;
	
	//图片文件新名字
	protected String newFileName;
	
	//上传文件类型
	protected String imageContentType;

	protected String[] ids;
	
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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
