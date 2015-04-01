package com.xiaoy.resource.servic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.resource.web.form.LogForm;

/**
 * 日志信息
 * @author XiaoY
 * @explain 
 * 
 * @date: 
 * 2015年3月28日 下午4:23:03
 */
public interface LogService
{
	/**
	 * 保存日志
	 * @param request	用户的详细信息
	 * @param model		模块名
	 * @param details	操作信息
	 */
	void saveLog(HttpServletRequest request, String model, String details);

	/**
	 * 通过条件查询出日志文件
	 * @param elecLogForm
	 * @return	List &ltElecLogForm&gt
	 */
	List<LogForm> findLogByCondition(LogForm logForm);

	/**
	 * 通过id数组删除一组日志对象
	 * @param ids	主键数组
	 */
	void deleteLogByIds(String[] ids);
	
	/**
	 * 带条件的查询，根据条件查询出现总记录数
	 * @param logForm	查询条件
	 * @return
	 */
	public Integer countByCollection(LogForm logForm);

}
