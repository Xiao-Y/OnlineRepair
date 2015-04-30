package com.xiaoy.resource.web.form;

public class DictionaryForm
{

	/**
	 * 维护类别
	 */
	public static final String MAINTAIN_TYPE_NAME = "维护类型";

	/**
	 * 性别
	 */
	public static final String SEX_NAME = "性别";

	/**
	 * 区域
	 */
	public static final String AREA_NAME = "区域";

	/**
	 * 运行状态
	 */
	public static final String STATE_NAME = "运行状态";

	/**
	 * 安装位置
	 */
	public static final String INSTALLATION_SITE_NAME = "安装位置";

	/**
	 * 优先级别
	 */
	public static final String PRIOR = "优先级别";

	/**
	 * 维护状态
	 */
	public static final String MAINTAIN_STAT = "维护状态";

	/**
	 * 评价状态
	 */
	public static final String EVALUATE_STAT = "评价状态";

	/**
	 * 审核状态
	 */
	public static final String AUDIT_STAT = "审核状态";

	/**
	 * 设备状态：运行正常
	 */
	public static final String DEVICE_STAT_OK = "1";
	
	/**
	 * 设备状态：运行异常
	 */
	public static final String DEVICE_STAT_EXCEPTION = "2";

	/**
	 * 审核：待审核
	 */
	public static final String AUDITSTAT_WAIT = "1";

	/**
	 * 审核：通过
	 */
	public static final String AUDITSTAT_SUCCESS = "2";

	/**
	 * 审核：驳回
	 */
	public static final String AUDITSTAT_FAIL = "3";
	
	/**
	 * 维护状态：未处理，待审核时
	 */
	public static final String MAINTAIN_STAT_NO = "1";
	
	/**
	 * 维护状态：待维护，审核通过时
	 */
	public static final String MAINTAIN_STAT_WAIT = "2";
	
	/**
	 * 维护状态：已维护，审核通过时
	 */
	public static final String MAINTAIN_STAT_SUCCESS = "3";

	/* 字典id */
	private String seqID;
	/* 查询关键字 */
	private String keyWord;
	/* 数据字典Code */
	private String ddlCode;
	/* 数据字典Name */
	private String ddlName;

	// 保存关键字
	private String keywordname;

	/**
	 * 保存数据字典时的标识量<br>
	 * 值 = new 新添加一种数据类型，并添加数据项，保存<br>
	 * 值 = add 在原有的数据类型上进行修改和添加删除数据项，保存<br>
	 */
	private String typeflag;

	/**
	 * 保存数据字典的数据项的名称
	 */
	private String[] itemname;

	public String getSeqID()
	{
		return seqID;
	}

	public void setSeqID(String seqID)
	{
		this.seqID = seqID;
	}

	public String getKeyWord()
	{
		return keyWord;
	}

	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}

	public String getDdlCode()
	{
		return ddlCode;
	}

	public void setDdlCode(String ddlCode)
	{
		this.ddlCode = ddlCode;
	}

	public String getDdlName()
	{
		return ddlName;
	}

	public void setDdlName(String ddlName)
	{
		this.ddlName = ddlName;
	}

	public String getKeywordname()
	{
		return keywordname;
	}

	public void setKeywordname(String keywordname)
	{
		this.keywordname = keywordname;
	}

	public String getTypeflag()
	{
		return typeflag;
	}

	public void setTypeflag(String typeflag)
	{
		this.typeflag = typeflag;
	}

	public String[] getItemname()
	{
		return itemname;
	}

	public void setItemname(String[] itemname)
	{
		this.itemname = itemname;
	}
}
