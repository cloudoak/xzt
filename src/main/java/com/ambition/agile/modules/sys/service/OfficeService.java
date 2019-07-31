/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.FileService;
import com.ambition.agile.common.service.ServiceException;
import com.ambition.agile.common.utils.CacheUtils;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.dao.OfficeDao;
import com.ambition.agile.modules.sys.dao.UserDao;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Orgization;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author harry
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends FileService<OfficeDao, Office> {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OfficeDao officeDao;
	
	public Integer findMaxSort(Office office) {
		return officeDao.findMaxSort(office);
	}

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}
	
	public List<Orgization> findAllOrgsByParentId(Orgization org){
		return officeDao.findAllOrgsByParentId(org);
	}
	
	public Office findByParentId(Office office) {
		return officeDao.findByParentId(office);
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = true)
	public List<Office> findOrgList(Office office){
		if(office != null){
			return dao.findOrgList(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public Integer save(Office office) {
		Integer b = super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		return b;
	}
	
	@Transactional(readOnly = false)
	public Integer delete(Office office) {
		Integer b = super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		return b;
	}
	
	@Transactional(readOnly = false)
	public void deleteByParentId(Office office) {
		officeDao.deleteByParentId(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	/**
	 * 添加修改机构并绑定用户
	 * @param office
	 * @return 1用户存在，2成功
	 */
	@Transactional(rollbackFor=ServiceException.class)
	public Integer saveOrgAndUser(Office office) {
		
		/******************添加、修改机构*******************/
		//创建机构父节点，因为机构是顶级，设其父节点为0
		Office parent=new Office(0);
		office.setParent(parent);
		//设置机构部门
		//office.setParentIds("0,1");
		//设置机构所处地点，因为地点字段占时没用，默认为0
		office.setArea(new Area(0));
		//设置机构是否可用，0不可用，1可用，默认为1
		office.setUseable("1");
		//设置机构类型，默认为1
		office.setType("1");
		
		//保存机构信息
		super.save(office);
		
		/******************添加机构，绑定机构管理负责人*******************/
		//为机构添加默认账号
		User user = new User();
		//设置管理员所属机构，默认为当前添加的机构
		user.setCompany(office);
		//设置机构管理员的类型
		user.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);//机构默认管理员
		
		user = UserUtils.getOfficeAndUserType(user);;
		if(user==null){
			user=office.getUser();
		}
		//设置当前添加的默认用户密码
		String password = office.getUser().getPassword();
		password = SystemService.entryptPassword(password);
		user.setPassword(password);
		//设置当前添加的默认用户名
		user.setLoginName(office.getUser().getLoginName());
		//设置管理机构
		user.setCompany(office);
		//设置所在部门
		user.setOffice(office);
		//设置姓名
		user.setName(office.getMaster());
		//设置电话
		user.setPhone(office.getPhone());
		//设置用户类型  0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
		user.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		//设置是否是管理员身份  1机构默认管理员 2平台管理员 3非管理员
		user.setIsAdmin(1);
		//设置用户角色 1系统管理员 2机构管理员 3咨询师 4来访者 5家属
		List<Role> roleList=new ArrayList<Role>();
		roleList.add(new Role(2));//设置为机构管理员角色
		user.setRoleList(roleList);
		//根据用户名去查询当前用户名是否存在，如果存在，不添加行的用户信息
		List<User> list = UserUtils.selectUserByLoginName(user.getLoginName());;
		if(list.size()==1&&(user.getId()==null||user.getId()==0)||list.size()>1){
			throw new ServiceException(user.getLoginName() + "用户名已经存在！");
		}else{
			//用户名不存在，添加或者修改用户信息
//			systemService.saveUser(user);
			if (user.getId()==null || user.getId()==0){
				user.preInsert();
				userDao.insert(user);
			}else{
				// 清除原用户机构用户缓存
				User oldUser = userDao.get(user.getId());
				if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null){
					CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
				}
				// 更新用户数据
				user.preUpdate();
				userDao.update(user);
			}
			if (user.getId()!=null&&user.getId()>0){
				// 更新用户与角色关联
				userDao.deleteUserRole(user);
				if (user.getRoleList() != null && user.getRoleList().size() > 0){
					userDao.insertUserRole(user);
				}else{
					throw new ServiceException(user.getLoginName() + "没有设置角色！");
				}
				// 将当前用户同步到Activiti
				//saveActivitiUser(user);
				// 清除用户缓存
				UserUtils.clearCache(user);
//				// 清除权限缓存
//				systemRealm.clearAllCachedAuthorizationInfo();
			}
			//保存成功
			return 2;
		}
	}

	//树节点查询机构下的部门
	public List<Office> findOfficeTree(Office office) {
		return officeDao.findOfficeTree(office.getId());
	}
	
}
