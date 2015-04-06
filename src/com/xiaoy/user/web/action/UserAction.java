package com.xiaoy.user.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoy.base.action.BaseAction;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.servic.LogService;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.service.UserService;
import com.xiaoy.user.web.form.UserForm;

@SuppressWarnings("serial")
@Controller
public class UserAction extends BaseAction implements ModelDriven<UserForm>
{

	@Resource
	private UserService userService;
	
	/**
	 *数据字典 
	 */
	@Resource
	private DictionaryService dictionaryService;
	
	/**
	 * 记录日志
	 */
	@Resource
	private LogService logService;
	
	//获得输入流，用于删除
	private InputStream inputStream;
	
	public InputStream getInputStream()
	{
		return inputStream;
	}
	
	private UserForm userForm = new UserForm();
	
	@Override
	public UserForm getModel()
	{
		return userForm;
	}

	/**
	 * 用户信息列表页面
	 * @return
	 */
	public String userIndex()
	{
		List<UserForm> formList = userService.findUserByConditionWithPage(userForm);
		Integer count = userService.countUserByCondition(userForm);
		userForm.setRecordCount(count);
		request.setAttribute("users", formList);
		
		//数据类型发送到页面
		this.sendPageData();
		logService.saveLog(request, "【用户信息】", "查看用户信息列表");
		return "userIndex";
	}
	
	/**
	 * 进入用户添加页面
	 * @return
	 */
	public String toUserAdd()
	{
		//数据类型发送到页面
		this.sendPageData();
		logService.saveLog(request, "【用户信息】", "进入用户信息添加面");
		return "toUserAdd";
	}
	
	/**
	 * 保存用户信息
	 * @return	返回到用户列表页
	 */
	public String userSave()
	{
		userService.userSave(userForm);
		logService.saveLog(request, "【用户信息】", "保存“"+ userForm.getName()+ "”用户信息");
		return "userSave";
	}
	
	/**
	 * 查看用户详细信息
	 * @return
	 */
	public String userView()
	{
		userForm = userService.findUserByUuid(userForm.getUserUuid());
		ActionContext.getContext().getValueStack().push(userForm);
		logService.saveLog(request, "【用户信息】", "查看“"+ userForm.getName()+ "”用户详细信息");
		return "userView";
	}
	
	/**
	 * 进入编辑页面
	 * @return
	 */
	public String userEdit()
	{
		userForm = userService.findUserByUuid(userForm.getUserUuid());
		ActionContext.getContext().getValueStack().push(userForm);
		//数据类型发送到页面
		this.sendPageData();
		logService.saveLog(request, "【用户信息】", "编辑“"+ userForm.getName()+ "”用户信息");
		return "userEdit";
	}
	
	public String userUpdate()
	{
		userService.userUpdate(userForm);
		logService.saveLog(request, "【用户信息】", "更新“"+ userForm.getName()+ "”用户信息");
		return "userUpdate";
	}
	
	public String userDelete() throws UnsupportedEncodingException
	{
		String userUuid = request.getParameter("userUuid");
		try
		{
			userForm = userService.findUserByUuid(userUuid);
			logService.saveLog(request, "【用户信息】", "删除“"+ userForm.getName()+ "”用户信息成功");
			userService.userDelete(userUuid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			logService.saveLog(request, "【用户信息】", "删除“"+ userForm.getName()+ "”用户信息失败");
			e.printStackTrace();
		}
		return "ajax-success";
	}
	
	/**
	 * 向页面上发送类型数据
	 */
	private void sendPageData()
	{
		//数据类型发送到页面
		List<DictionaryForm> maintainType = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);
		List<DictionaryForm> sex = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.SEX_NAME);
		request.setAttribute("maintainType", maintainType);
		request.setAttribute("sex", sex);
	}
}
