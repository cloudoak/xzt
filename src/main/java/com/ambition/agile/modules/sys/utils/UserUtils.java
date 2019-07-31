/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ambition.agile.common.service.BaseService;
import com.ambition.agile.common.utils.CacheUtils;
import com.ambition.agile.common.utils.SpringContextHolder;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.ante.service.DepartmentService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.dao.AreaDao;
import com.ambition.agile.modules.sys.dao.MenuDao;
import com.ambition.agile.modules.sys.dao.OfficeDao;
import com.ambition.agile.modules.sys.dao.RoleDao;
import com.ambition.agile.modules.sys.dao.ThirdPartyDao;
import com.ambition.agile.modules.sys.dao.UserDao;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Menu;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.ambition.agile.modules.sys.service.OfficeService;

/**
 * 用户工具类
 * @author harry
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	private static DepartmentService departmentService = SpringContextHolder.getBean(DepartmentService.class);
	private static ThirdPartyDao thirdPartyDao = SpringContextHolder.getBean(ThirdPartyDao.class);
    private static OfficeService officeService = SpringContextHolder.getBean(OfficeService.class);
	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE = "office";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_DEPARTMENT_LIST = "departmentList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	public static final String CACHE_OFFICE_LOGO = "officeLogo";
	public static final String CACHE_CURRENT_ORG = "currentOrg";
	public static final String  CACHE_OFFICE_REFENCES = "officeRefences";
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(Integer id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null || (user != null && (user.getStatus().intValue() == UserConstant.STATUS_AUDIT 
				|| user.getStatus().intValue() == UserConstant.STATUS_REJECT))){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_AUTH_INFO);
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_DEPARTMENT_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		Session session = UserUtils.getSession();
		Object orgObj = session.getAttribute(CACHE_CURRENT_ORG);
		if(orgObj != null) {
			session.removeAttribute(CACHE_CURRENT_ORG);
		}
		UserUtils.clearCache(getUser());
	}
	
	private static void appendBuffer(List<String> refences, Integer previous) {
		Office off = new Office();
		off.setParentId(previous);
		Office children = officeService.findByParentId(off);
		if(children != null) {
			refences.add(children.getName());
			appendBuffer(refences, children.getParentId());
		}
	}
	
	public static void officeRefences() {
		Map<Integer, List<String>> officeRefences = new HashMap<Integer, List<String>>();
		List<Office> officeList = officeService.findAll();
		for(Office office : officeList) {
			officeRefences.put(office.getId(), new ArrayList<String>());
			List<String> refences = officeRefences.get(office.getId());
			refences.add(office.getName());
			appendBuffer(refences, office.getParentId());
		}
		Object obj = getCache(CACHE_OFFICE_REFENCES);
		if(obj != null) {
			removeCache(CACHE_OFFICE_REFENCES);
		}
		putCache(CACHE_OFFICE_REFENCES, officeRefences);
	}
	
	public static String getOfficeRefences(Integer orgId) {
		Object obj = getCache(CACHE_OFFICE_REFENCES);
		if(obj != null) {
			Map<Integer, List<String>> officeRefences = (Map<Integer, List<String>>) obj;
			List<String> refences = officeRefences.get(orgId);
			if(refences != null && refences.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				for(int i = refences.size() - 1; i >= 0; i--) {
					buffer.append(refences.get(i) + " > ");
				}
				return buffer.toString().substring(0, buffer.length() - 3);
			}
		}
		return null;
	}
	
	public static void setOrg(Integer orgId) {
		Office office = officeService.get(orgId);
		Object orgObj = getCache(CACHE_CURRENT_ORG);
		if(orgObj != null) {
			removeCache(CACHE_CURRENT_ORG);
		}
		putCache(CACHE_CURRENT_ORG, office);
	}
	
	public static Office getOrg() {
		Object orgObj = getCache(CACHE_CURRENT_ORG);
		if(orgObj != null) {
			return (Office) orgObj;
		}
		return null;
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户所在机构
	 * @return
	 */
	public static Office getOffice(){
		Office office = (Office)getCache(CACHE_OFFICE);
		if(office==null) {
			User user = getUser();
			Office off = new Office();
			off.setUser(user);
			office = officeDao.get(off);
			putCache(CACHE_OFFICE, office);
		}
		return office;
	}
	
	/**
	 * 获取当前用户所在机构LOGO
	 * @return
	 */
	public static String getLogo(){
		String logo = (String)getCache(CACHE_OFFICE_LOGO);
		if(logo==null) {
			User user = getUser();
			if(user != null && user.getCompany()!= null && user.getCompany().getId() != null) {
				Office of = officeService.get(user.getCompany().getId());
				if(of != null) {
					logo = officeService.getOriginalFilePath(of.getLogo());
				}
			}
			putCache(CACHE_OFFICE_LOGO, logo);
		}
		return logo;
	}
	
	/**
	 * 获取当前用户有权限访问的组织
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Department> getDepartmentList(){
		@SuppressWarnings("unchecked")
		List<Department> departmentList = (List<Department>)getCache(CACHE_DEPARTMENT_LIST);
		if (departmentList == null){
			departmentList = departmentService.findList(new Department());
			putCache(CACHE_DEPARTMENT_LIST, departmentList);
		}
		return departmentList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	/**
	 * 获取当前登录用户的完全地址
	 */
	public static String getFullAddressByAreaCode(String areaCode){
		if(areaCode==null)
		{
			return "";
		}
		Map<String,Object> resultMap = thirdPartyDao.getFullAddressByAreaCode(areaCode);
		if(resultMap.get("cityName").toString().equals("市辖区")||resultMap.get("cityName").toString().equals("县"))
		{
			return resultMap.get("provinceName").toString()+resultMap.get("areaName").toString();
		}
		return resultMap.get("provinceName").toString()+resultMap.get("cityName").toString()+resultMap.get("areaName").toString();
	}
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	public static User getOfficeAndUserType(User user) {
		return userDao.getOfficeAndUserType(user);
	}

	public static List<User> selectUserByLoginName(String loginName) {
		User user=new User(null, loginName);
		return userDao.selectUserByLoginName(user);
	}
	
	public static List<User> selectUserByAreaCode(String areaCode) {
		User user=new User();
		user.setAreaCode(areaCode);
		return userDao.selectUserByAreaCode(user);
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	
}
