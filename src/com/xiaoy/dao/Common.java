package com.xiaoy.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *	 公接口<br>
 *	所有的Dao接口必须extends<br>
 *	方法：<br>
 *根据id删除一个对<br>
 *	public void deleteObjectByid(Serializable id);<p>
 *
 *根据一个集合删除一组对象,可以通过hqlWhere添加查询条件，paramMapValue设置查询参数 <br>
 *	public void deleteObjectByCollectionIds(Collection&ltT&gt collection,String hqlWhere,Map<String, Object> paramsMapValue);<p>
 *
 *更新一个对象<br>
 *	public void updateObject(T entity);<p>
 *
 *根据一个集合更新一组对象<br>
 *	public void updateObjectCollection(Collection&ltT&gt entities);<p>
 *
 *保存一个对象<br>
 *	public void saveObject(T entity);<p>
 *
 *根据一个集合对象保存一组对象<br>
 *	public void saveObjectCollection(Collection&ltSerializable&gt entities);<p>
 *
 *根据一个id查询出一个对象<br>
 *	public T findObjectById(Serializable id);<p>
 *
 *根据条件查询出一个集合（不分页）,可以通过hqlWhere添加查询条件，paramMapValue设置查询参数<br>
 *	List&ltT&gt findCollectionByCondition(String hqlWhere,Map<String, Object> paramsMapValue);<p>
 *
 *----------------------废---弃---的---方---法---start：以下方法主键名只能为id，受到很在的限制------------<br>
 *根据id数组删除一组对象，可以通过重写getAppendHqlWhere方法添加查询条件，重写setParamMapValue方法设置查询参数<br>
 *	public void deleteObjectByArrayIds(Serializable[] ids)<p>
 *通过一个id数组查询出一组对象,可以通过重写getAppendHqlWhere方法添加查询条件，重写setParamMapValue方法设置查询参数<br>
 *	public List&ltT&gt findCollectionByArrayIds(Serializable[] ids);<p>
 *通过一个id集合查询出一组对象,可以通过重写getAppendHqlWhere方法添加查询条件，重写setParamMapValue方法设置查询参数<br>
 *	public List&ltT&gt findCollectionByCollectionIds(Collection&ltT&gt ids);<p>
 *------------------------废---弃---的---方---法---end------------------------<br>
 *
 *
 * @author XiaoY
 * 2014年11月5日 下午10:58:20
 */
public interface Common<T>{
	
	/**
	 * 通过id删除一个对象 
	 * @param id 任意类型的id
	 */
	public void deleteObjectByid(Serializable id);
	
//	/**
//	 * 通过一个数组删除多个对象,可以通过重写getAppendHqlWhere方法添加查询条件，
//	 * 重写setParamMapValue方法设置查询参数
//	 * 
//	 * @param id	任意类型的数组
//	 */
//	public void deleteObjectByArrayIds(Serializable[] ids);
	
	/**
	 * 通过一个集合删除多个对象,可以通过hqlWhere添加查询条件，paramMapValue设置查询参数
	 * 
	 * @param collection	集合
	 * @param hqlWhere 查询条件
	 * @param paramsMapValue	设置查询参数
	 */
	public void deleteObjectByCollectionIds(Collection<String> ids,String hqlWhere,Map<String, Object> paramsMapValue);
	
	/**
	 * 更新一个对象
	 * @param entity	泛型的
	 */
	public void updateObject(T entity);
	
	/**
	 * 根据一个集合更新一组对象
	 * @param entities	将要更新的集合
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月13日 下午5:01:24
	 */
	public void updateObjectCollection(Collection<T> entities);
	
	/**
	 * 保存一个对象 
	 * @param entity	泛型的
	 */
	public void saveObject(T entity);
	
	/**
	 * 根据一个集合对象保存一组对象
	 * @param entities
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月13日 下午5:03:47
	 */
	public void saveObjectCollection(Collection<T> entities);
	
	/**
	 * 通过id查询一个对象
	 * @param id	任意类型
	 * @return	对象（泛型）
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年11月5日 下午10:04:01
	 */
	public T findObjectById(Serializable id);
	
//	/**
//	 * 通过一个id数组查询出一组对象,可以通过重写getAppendHqlWhere方法添加查询条件，
//	 * 重写setParamMapValue方法设置查询参数
//	 * 
//	 * @param ids	数组对象
//	 * @return	集合对象（泛型）
//	 *
//	 * @author XiaoY
//	 * @date: 
//	 * 2014年12月13日 下午4:52:14
//	 */
//	public List<T> findCollectionByArrayIds(Serializable[] ids);

//	/**
//	 * 通过一个id集合查询出一组对象,可以通过重写getAppendHqlWhere方法添加查询条件，
//	 * 重写setParamMapValue方法设置查询参数
//	 * 
//	 * @param ids	集合对象
//	 * @return	集合对象（泛型）
//	 *
//	 * @author XiaoY
//	 * @date: 
//	 * 2014年12月13日 下午4:49:30
//	 */
//	public List<T> findCollectionByCollectionIds(Collection<T> ids);
	
	/**
	 *	带条件的查询，不分页。可以通过hqlWhere添加查询条件，paramMapValue设置查询参数
	 * 
	 * @param hqlWhere 查询条件
	 * @param paramsMapValue	设置查询参数
	 * @return	List&ltT&gt
	 *
	 * @author XiaoY
	 * @date: 
	 * 2014年12月13日 下午11:22:52
	 */
	public List<T> findCollectionByCondition(String hqlWhere,Map<String, Object> paramsMapValue);
	
}


















