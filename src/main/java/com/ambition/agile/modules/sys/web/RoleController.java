/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.Collections3;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.sys.entity.Menu;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 角色Controller
 * @author harry
 * @version 2013-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) Integer id) {
		if (id!=null&&id>0){
			return systemService.getRole(id);
		}else{
			return new Role();
		}
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "form")
	public String form(Integer id, Model model) {
		User user = UserUtils.getUser();
		/*if(role==null){
			role = systemService.getRole(id);
		}*/
		if(id!=null)
		{
			Role role = systemService.getRole(id);
			if (role != null && role.getOffice() == null){
				role.setOffice(user.getOffice());
			}
			model.addAttribute("role", role);
		}
		/*if (role != null && role.getOffice() == null){
			role.setOffice(user.getOffice());
		}*/
		/*model.addAttribute("role", role);*/
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/roleForm";
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "save")
	public String save(Role role, Model model, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if (!beanValidator(model, role)){
			return form(role.getId(), model);
		}
		if(role.getOffice()==null){
			role.setOffice(new Office(0));
		}
		systemService.saveRole(role);
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	@RequestMapping(value = "checkRole")
	@ResponseBody
	public int checkRole(String name, Integer id) {
		Role role = systemService.getRoleByName(name);
		if(id!=null) {
			if(role.getName().equals(name) && role.getId().intValue() != id.intValue())
			{
				return 0;
			}
		}
		else if(role != null && role.getName().equals(name))
		{
			return 0;
		}
		return 1;
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "delete")
	public String delete(Integer id,RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin()){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
//		if (Role.isAdmin(id)){
//			addMessage(redirectAttributes, "删除角色失败, 不允许内置角色或编号空");
////		}else if (UserUtils.getUser().getRoleIdList().contains(id)){
////			addMessage(redirectAttributes, "删除角色失败, 不能删除当前用户所在角色");
//		}else{
			Role role = new Role();
			role.setId(id);
			systemService.deleteRole(role);
			addMessage(redirectAttributes, "删除角色成功");
//		}
		return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	/**
	 * 角色分配页面
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assign")
	public String assign(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("userList", userList);
		return "modules/sys/roleAssign";
	}
	
	/**
	 * 角色分配 -- 打开角色分配对话框
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "usertorole")
	public String selectUserToRole(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("role", role);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/selectUserToRole";
	}
	
	/**
	 * 打开角色分配资源
	 * @param role
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "resoure")
	public String selectResoure(Role role,Integer id, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("role", role==null?new Role(id):role);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/selectUserToRole";
	}
	
	/**
	 * 角色分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(Integer officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);			
		}
		return mapList;
	}
	
	/**
	 * 角色分配 -- 从角色中移除用户
	 * @param userId
	 * @param roleId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "outrole")
	public String outrole(Integer userId, Integer roleId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+roleId;
		}
		Role role = systemService.getRole(roleId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从角色【" + role.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			if (user.getRoleList().size() <= 1){
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
			}else{
				Boolean flag = systemService.outUserInRole(role, user);
				if (!flag) {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！");
				}else {
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除成功！");
				}
			}		
		}
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}
	
	/**
	 * 角色分配
	 * @param role
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assignrole")
	public String assignRole(Role role, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToRole(role, systemService.getUser(Integer.valueOf(idsArr[i])));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到角色【" + role.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}

	/**
	 * 验证角色名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证角色英文名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkEnname")
	public String checkEnname(String oldEnname, String enname) {
		if (enname!=null && enname.equals(oldEnname)) {
			return "true";
		} else if (enname!=null && systemService.getRoleByEnname(enname) == null) {
			return "true";
		}
		return "false";
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"list", ""})
	public String list(Role role, Model model) {
		List<Role> list = systemService.findAllRole();
		model.addAttribute("list", list);
		return "modules/sys/roleList";
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"res"})
	public String resource(Integer id, Model model) {
		//查询所有的权限资源
		List<Menu> sourcelist = systemService.findAllMenu();
		//查询已存在的所有权限资源
		List<Integer> list = systemService.selectRoleMenu(new Role(id));
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		for (int i=0; i<list.size();i++) {
			if (list.get(i)!=null) {
				buffer.append("\""+list.get(i)+"\",");
			}
		}
		String menuList = buffer.substring(0, buffer.length()-1)+"]";
		/*sysRoleGrant.setDelFlag("0");
		List<SysRoleGrant> list = sysRoleGrantService.findList(sysRoleGrant); 
		model.addAttribute("list", list);*/
		model.addAttribute("sourcelist",sourcelist);
		model.addAttribute("menuList",menuList);
		model.addAttribute("roleId",id);
		return "modules/sys/roleGrantList";
	}
	
	//设置角色权限
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = {"saveMenus"})
	@ResponseBody
	public Integer saveMenus(String menuIds, Integer roleId, Model model) {
		//删除角色权限
		systemService.deleteRoleMenu(new Role(roleId));
		if("".equals(menuIds)){
			return 1;
		}
		String[] menuIdList = menuIds.split(",");
		for (String menuId : menuIdList) {
			systemService.updateRoleMenu(roleId, Integer.valueOf(menuId));
		}
		return 1;
	}
	
}
