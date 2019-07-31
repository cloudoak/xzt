/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author harry
 * @version 2014-05-16
 */
/**
 * @author WHQ
 *
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public List<User> selectUserByLoginName(User user);
	
	/**
	 * 根据地区码查询用户
	 * @param loginName
	 * @return
	 */
	public List<User> selectUserByAreaCode(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 根据用户名与当前组织获取用户是否存在
	 * @param user
	 * @return
	 */
	public User findExistsUserName(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	public int updateStatus(User user);

	public User getOfficeAndUserType(User user);

	/**
	 * 查询机构管理员信息
	 * @param user
	 * @return
	 */
	public List<User> findUserByOrgIdAndUserType(User user);
	
	
	/**
	 * 查询最大Id
	 * @return
	 */
	public int findMaxId();

}
