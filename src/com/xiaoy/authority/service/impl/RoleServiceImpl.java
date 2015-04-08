package com.xiaoy.authority.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.authority.dao.RolePopedomDao;
import com.xiaoy.authority.dao.UserRoleDao;
import com.xiaoy.authority.service.RoleService;
import com.xiaoy.authority.web.form.RoleForm;
import com.xiaoy.base.entites.RolePopedom;
import com.xiaoy.base.entites.UserRole;
import com.xiaoy.base.util.XmlObject;
import com.xiaoy.user.web.form.UserForm;

/**
 * @author XiaoY
 * @explain 
 * 实现RoleService
 * @date: 
 * 2014年12月22日 下午9:34:22
 */
@Service
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService
{
	/**
	 * 注入用户角色
	 */
	@Resource
	private UserRoleDao userRoleDao;
	
	/**
	 * 注入角色权限
	 */
	@Resource
	private RolePopedomDao rolePopedomDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlObject> readXml()
	{
		List<XmlObject> xmlList = new ArrayList<XmlObject>();
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realPath = servletContext.getRealPath("/WEB-INF/classes/Function.xml");
		
		File f = new File(realPath);
		//使用dom4j开始解析xml文件
		SAXReader read = new SAXReader();
		try
		{
			Document document = read.read(f);
			Element element = document.getRootElement();
			XmlObject xmlObject = null;
			for(Iterator<Element> iter = element.elementIterator("Function");iter.hasNext();)
			{
				Element xmlElement = iter.next();
				xmlObject = new XmlObject();
				xmlObject.setCode(xmlElement.elementTextTrim("FunctionCode"));
				xmlObject.setName(xmlElement.elementTextTrim("FunctionName"));
				xmlObject.setParentCode(xmlElement.elementTextTrim("ParentCode"));
				xmlObject.setParentName(xmlElement.elementTextTrim("ParentName"));
				xmlList.add(xmlObject);
			}
		} catch (DocumentException e)
		{
			new RuntimeException("解析Function.xml文件出现未知的错误！");
			e.printStackTrace();
		}
		return xmlList;
	}

	@Override
	public List<XmlObject> readEditXml(String roleId)
	{
		RolePopedom elecRolePopedom = rolePopedomDao.findObjectById(roleId);
		String popedomCode = "";
		if(elecRolePopedom != null)
		{
			popedomCode = elecRolePopedom.getPopedomCode();
		}
		
		//通过权限的code进行匹配
		List<XmlObject> xmlList = this.readXmlByPopedom(popedomCode);
		return xmlList;
	}

	/**
	 * 通过权限的code进行匹配
	 * @param popedomCode	权限的code
	 * @return	List &ltXmlObject&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月25日 下午8:53:44
	 */
	private List<XmlObject> readXmlByPopedom(String popedomCode)
	{
		List<XmlObject> xmlList = this.readXml();
		for(int i = 0; xmlList != null && i < xmlList.size(); i++)
		{
			XmlObject object = xmlList.get(i);
			if(object != null)
			{
				//包含：表示当前权限被选种
				if(popedomCode.contains(object.getCode()))
				{
					object.setFlag("1");
				}
				//不包含：表示当前权限没有选种
				else
				{
					object.setFlag("0");
				}
			}
		}
		return xmlList;
	}

	@Override
	public List<UserForm> findUserByRoleId(String roleId)
	{
		List<Object[]> userList = userRoleDao.findElecUserByRoleId(roleId);
		List<UserForm> userForm = this.elecUserRoleObjectToVo(userList);
		return userForm;
	}

	/**
	 * 将Object 类型的用户信息对象转换成	 UserForm 类型的对象
	 * @param userList	List &ltObject[]&gt
	 * @return	List &ltUserForm&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午4:20:29
	 */
	private List<UserForm> elecUserRoleObjectToVo(List<Object[]> userList)
	{
		List<UserForm> userForm = null;
		
		if(userList != null)
		{
			userForm = new ArrayList<UserForm>();
			for(Object[] object : userList)
			{
				UserForm user = new UserForm();
				user.setFlag(object[0].toString());
				user.setUserUuid(object[1].toString());
				user.setName(object[2].toString());
				user.setLoginName(object[3].toString());
				
				userForm.add(user);
			}
		}
		return userForm;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void saveRole(RoleForm roleForm)
	{
		if(roleForm != null && !StringUtils.isEmpty(roleForm.getRoleId()))
		{
			//保存角色与权限的关联
			this.saveRolePopedom(roleForm);
			//保存用户与角色的关联
			this.saveUserRole(roleForm);
		}
	}

	/**
	 * 保存角色与权限的关联
	 * @param roleForm	角色VO（角色id、权限id数组）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午7:24:40
	 */
	private void saveRolePopedom(RoleForm roleForm)
	{
		String roleId = roleForm.getRoleId();
		String[] selectoper = roleForm.getSelectoper();
		
		StringBuffer popedomCode = new StringBuffer("");
		for(int i = 0; selectoper != null && i < selectoper.length; i++)
		{
			popedomCode.append(selectoper[i]);
		}
		RolePopedom rolePopedom = rolePopedomDao.findObjectById(roleId);
		//说明数据库中角色与权限的关联表中已经存在
		if(rolePopedom != null)
		{
			rolePopedom.setPopedomCode(popedomCode.toString());
			rolePopedomDao.updateObject(rolePopedom);
		}
		else
		{
			rolePopedom = new RolePopedom();
			rolePopedom.setPopedomCode(popedomCode.toString());
			rolePopedom.setRoleId(roleId);
			rolePopedomDao.saveObject(rolePopedom);
		}
	}
	
	/**
	 * 保存用户与角色的关联
	 * 1、先删除原来的用户与角色的关联关系
	 * 2、保存新的用户与角色的关联关系
	 * @param roleForm	角色VO（角色id、用户id数组）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月26日 下午7:26:26
	 */
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	private void saveUserRole(RoleForm roleForm)
	{
		String roleId = roleForm.getRoleId();
		String[] selectuser = roleForm.getSelectuser();
		
		StringBuffer hqlWhere = new StringBuffer("");
		hqlWhere.append(" and e.roleId = :roleId");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("roleId", roleId);
		
//		List<UserRole> elecUserRole = userRoleDao.findCollectionByConditionNoPage(hqlWhere, paramsMap);
		List<UserRole> userRoles = userRoleDao.findCollectionByCondition(hqlWhere.toString(), paramsMap);
	
		//删除原来的用户与角色的关联关系
		if(userRoles != null)
		{
			userRoleDao.deleteObjectByCollection(userRoles);
		}
		
		List<UserRole> list = new ArrayList<UserRole>();
		for(int i = 0; selectuser != null && i < selectuser.length; i++)
		{
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(selectuser[i]);
			list.add(userRole);
		}
		
		if(list != null && list.size() > 0)
		{
			//保存新的用户与角色的关联关系
		//	userRoleDao.saveObjectByCollection(list);
			userRoleDao.saveObjectCollection(list);
		}
	}
}















