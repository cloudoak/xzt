/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;

import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.OrgScale;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.service.OrgScaleService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Orgization;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.FileUtils;
import com.ambition.agile.modules.utils.JSONSerException;
import com.ambition.agile.modules.utils.JSONUtil;
import com.ambition.agile.modules.utils.StringFunctionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 机构Controller
 * @author harry
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/org")
public class OrgController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private OrgScaleService orgScaleService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) Integer id) {
		if (id!=null&&id>0){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list", ""})
	public String index(Office office, Model model) {
		office.setParentId(0);
		model.addAttribute("list", officeService.findOrgList(office));
		return "modules/sys/orgList";
	}

	/*@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model) {
		office.setParentId(0);
		List<Office> officeList = officeService.findOrgList(office);
        model.addAttribute("list", officeList);
		return "modules/sys/orgList";
	}*/
	
	/**
	 * 新增&修改初始化页面
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		if(office != null) {
			model.addAttribute("fileAbsolutePath", officeService.getOriginalFilePath(office.getLogo()));
		}
		model.addAttribute("fileAbsPath", officeService.getOriginalFilePath());

		model.addAttribute("office", office);
		
		return "modules/sys/orgForm";
	}
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "findOrgList")
	@ResponseBody
	public String findOrgList(Integer id, Model model) {
		response.setContentType("text/json");
		
		String allOffices = "[]";
		
		try {
			if(id == -1) {
				Orgization parent = new Orgization();
				parent.setId(0);
				parent.setText("心知堂集团");
				parent.setChildren(true);
				List<Orgization> offices = new ArrayList<Orgization>();
				offices.add(parent);
				allOffices = JSONUtil.toJSON(offices);
			}else {	
				Orgization parent = new Orgization();
				parent.setId(id);
				parent.setDelFlag("0");
				List<Orgization> offices = officeService.findAllOrgsByParentId(parent);
				allOffices = JSONUtil.toJSON(offices);
			}
		} catch (JSONSerException e) {
			
		}
		
		return allOffices;
	}
	
	/**
	 * 添加修改回显
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "OrgUserAcountForm")
	public String OrgUserAcountForm(Integer orgId, Integer id, Model model) {
		model.addAttribute("orgId", orgId);
		User user = systemService.getUser(id);
		model.addAttribute("user", user);
		return "modules/sys/OrgUserAcountForm";
	}
	
	/**
	 * 分配机构数据
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "allot")
	public String allot(Integer id, Model model) {
		if (id==null){
			return "";
		}
		User user=new User();
		user.setCompany(new Office(id));
		user.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		//获取机构下管理员的账号
		List<User> list=systemService.findUserByOrgIdAndUserType(user);
		//获取可分配给机构的所有量表和已经分配给机构现有的量表，分配量表时，需判断是否已经分配该量表
		//可分配量表
		Scale scale=new Scale();
		scale.setIsPublic(0);
		scale.setIsLock(2);
		scale.setOrgId(id);
		List<Scale> useScaleList=scaleService.findUseScale(scale);
		//已分配量表
		OrgScale orgScale=new OrgScale();
		orgScale.setOrgId(id);
		List<OrgScale> scaleList=orgScaleService.findList(orgScale);
		
		Office office = officeService.get(id);
		
		model.addAttribute("scaleList",scaleList);
		model.addAttribute("useScaleList",useScaleList);
		model.addAttribute("office", office);
		model.addAttribute("list", list);
		return "modules/sys/orgAllot";
	}
	
	/**
	 * 添加机构管理员
	 * @param orgId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "addAcountForOrg")
	public String addAcountForOrg(Integer orgId, User user, Model model, RedirectAttributes redirectAttributes){
		
		Office office=officeService.get(orgId);
		
		//设置管理员所属机构，默认为当前添加的机构
		user.setCompany(office);
		//设置所在部门
		user.setOffice(office);
		//设置当前添加的默认用户密码
		user.setPassword(SystemService.entryptPassword(user.getPassword()));
		//设置用户类型  0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
		user.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		//设置是否是管理员身份  1机构默认管理员 2平台管理员 3非管理员
		user.setIsAdmin(1);
		//设置用户角色 1系统管理员 2机构管理员 3咨询师 4来访者 5家属
		List<Role> roleList=new ArrayList<Role>();
		roleList.add(new Role(2));//设置为机构管理员角色
		user.setRoleList(roleList);
		//根据用户名去查询当前用户名是否存在，如果存在，不添加行的用户信息
		List<User> list = systemService.selectUserByLoginName(user.getLoginName());
		if(user.getId()!=null){
			//修改
			if(list.size()==1&&list.get(0).getId()!=user.getId()||list.size()>1){
				model.addAttribute("message", "'保存机构管理员账号失败，'" + user.getLoginName() + "'账号已存在");
				model.addAttribute("orgId", orgId);
				model.addAttribute("user", user);
				return "modules/sys/OrgUserAcountForm";
			}else{
				//用户名不存在，添加或者修改用户信息
				systemService.saveUser(user);
				addMessage(redirectAttributes, "保存机构管理员账号已成功'" + user.getLoginName() + "'成功");
			}
		}else{
			//添加
			if(list.size()>0){
				model.addAttribute("message", "'保存机构管理员账号失败，'" + user.getLoginName() + "'账号已存在");
				model.addAttribute("orgId", orgId);
				model.addAttribute("user", user);
				return "modules/sys/OrgUserAcountForm";
			}else{
				//用户名不存在，添加或者修改用户信息
				systemService.saveUser(user);
				addMessage(redirectAttributes, "保存机构管理员账号已成功'" + user.getLoginName() + "'成功");
			}
		}
		
		User u=new User();
		u.setCompany(office);
		u.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		List<User> userLst=systemService.findUserByOrgIdAndUserType(u);
		
		model.addAttribute("office", office);
		model.addAttribute("list", userLst);
		return "modules/sys/orgAllot";
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "saveOrg")
	public String saveOrg(Office office, Model model, RedirectAttributes redirectAttributes) {
		office.setArea(new Area(0));
		//设置机构是否可用，0不可用，1可用，默认为1
		office.setUseable("1");
		//设置机构类型，默认为1
		office.setType("1");
		office.setMaster(UserUtils.getUser().getName());
		office.setSort(30);
		try {
			officeService.save(office);
		} catch (Exception e) {
			model.addAttribute("message", "机构名称已存在!");
			return "modules/sys/orgForm";
		}
		model.addAttribute("message", "操作成功!");
		return "redirect:" + adminPath + "/sys/org/list?parentId=0";
	}
	/**
	 * 验证机构是否存在
	 * @param office
	 * @return
	 */
	@RequestMapping(value = "checkOrg")
	@ResponseBody
	public boolean checkOrg(@RequestParam(value="name",defaultValue="") String name,@RequestParam(value="id",defaultValue="") String id,  Model model){
		List<Office> offices = officeService.findAll();
		for(Office officeDB : offices) {
			if(id!=null&&!id.equals(""))
			{
				if(officeDB.getName().equals(name)&&(Integer.valueOf(id)!=officeDB.getId())) {
					return false;
				}
			}
			else{
				if(officeDB.getName().equals(name)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 删除机构
	 * @param office
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "deleteOrg")
	public String deleteOrg(Office office, String id,RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/org/list";
		}
		//officeService.delete(office);
		office.setParentId(Integer.valueOf(id));
		officeService.deleteByParentId(office);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/org/list?parentId=0";
	}
	
	/**
	 * 删除多条机构数据
	 * @param orgIds
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String orgIds, RedirectAttributes redirectAttributes) {
		orgIds = StringFunctionUtils.replaceWebJsp(orgIds);
		String[] split = orgIds.substring(1, orgIds.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			officeService.delete(new Office(id));
		}
		/*for (Integer id : orgIds) {
			officeService.delete(new Office(id));
		}*/
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/org/list?parentId=0";
	}
	
	/**
	 * 删除机构用户
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delOrgUser")
	public String delOrgUser(Integer orgId, Integer userId, Model model, RedirectAttributes redirectAttributes) {
		User user=new User(userId);
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		
		Office office=officeService.get(orgId);
		
		User u=new User();
		u.setCompany(office);
		u.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		List<User> userLst=systemService.findUserByOrgIdAndUserType(u);
		
		//获取可分配给机构的所有量表和已经分配给机构现有的量表，分配量表时，需判断是否已经分配该量表
		//可分配量表
		Scale scale=new Scale();
		scale.setIsPublic(0);
		scale.setIsLock(2);
		scale.setOrgId(orgId);
		List<Scale> useScaleList=scaleService.findUseScale(scale);
		//已分配量表
		OrgScale orgScale=new OrgScale();
		orgScale.setOrgId(orgId);
		List<OrgScale> scaleList=orgScaleService.findList(orgScale);
		
		model.addAttribute("scaleList",scaleList);
		model.addAttribute("useScaleList",useScaleList);
		
		model.addAttribute("office", office);
		model.addAttribute("list", userLst);
		return "modules/sys/orgAllot";
	}
	
	/**
	 * 为机构分配量表数据
	 * @param orgScale
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "allotAll")
	public String saveAll(String sids, Integer orgId, Model model) {
		
		String[] sidList = sids.split(",");
		OrgScale os=new OrgScale();
		os.setOrgId(orgId);
		List<OrgScale> list = orgScaleService.findList(os);
		for (OrgScale orgScale2 : list) {
			orgScaleService.delete(orgScale2);
		}
		for (String sidStr : sidList) {
			OrgScale orgScale=new OrgScale();
			if(sidStr.equals(""))
			{	
				continue;
			}
			Integer sid = Integer.valueOf(sidStr);
			orgScale.setSid(sid);
			orgScale.setOrgId(orgId);
			orgScaleService.save(orgScale);
		}
		
		//获取可分配给机构的所有量表和已经分配给机构现有的量表，分配量表时，需判断是否已经分配该量表
		//可分配量表
		Scale scale=new Scale();
		scale.setIsPublic(0);
		scale.setIsLock(2);
		scale.setOrgId(orgId);
		List<Scale> useScaleList=scaleService.findUseScale(scale);
		//已分配量表
		OrgScale orgScale=new OrgScale();
		orgScale.setOrgId(orgId);
		List<OrgScale> scaleList=orgScaleService.findList(orgScale);
		
		Office office=new Office(orgId);
		User u=new User();
		u.setCompany(office);
		u.setUserType(UserConstant.USER_ORG_ADMIN_TYPE);
		List<User> userLst=systemService.findUserByOrgIdAndUserType(u);
		
		model.addAttribute("scaleList",scaleList);
		model.addAttribute("useScaleList",useScaleList);
		
		model.addAttribute("office", office);
		model.addAttribute("list", userLst);
		return "modules/sys/orgAllot";
	}
	
	/**
	 * 机构设置
	 * @param sids
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "setOrg")
	public String setOrg(Integer orgId, Model model) {
		User user = UserUtils.getUser();
		Integer id = 0;
		if(user.getOffice() != null && user.getOffice().getId() != null) {
			id = UserUtils.getOrg().getId();
		}
		Office office = officeService.get(id);
		Integer parentId = 0;
		if(office != null) {
			parentId = office.getParentId();
			orgId = office.getId();
		}
		model.addAttribute("parentId", parentId);
		model.addAttribute("orgId", orgId);
		return "modules/sys/orgSet";
	}
	
	/**
	 * 新增
	 * @param office
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws JSONSerException 
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(Office office, Model model) 
			throws JSONSerException {
		response.setContentType("text/json");
		User user = UserUtils.getUser();
		Office parent = officeService.get(office.getParentId());
		office.setParent(parent);
		office.setParentId(parent.getId());
		//设置机构所处地点，因为地点字段占时没用，默认为0
		office.setArea(new Area(0));
		//设置机构是否可用，0不可用，1可用，默认为1
		office.setUseable("1");
		//设置机构类型，默认为1
		office.setType("1");
		office.setMaster(user.getName());
		Integer sort = officeService.findMaxSort(parent);
		office.setSort(sort);
		Office off = null;
		officeService.save(office);
		Integer orgId = office.getId();
		if(orgId != null && orgId.intValue() > 0) {
			off = officeService.get(orgId);
		}
		Map message = new HashMap();
		message.put("success", true);
		message.put("msg", "保存机构成功！");
		message.put("data", off);
		return JSONUtil.toJSON(message);
	}
	
	/**
	 * 删除
	 * @param office
	 * @param redirectAttributes
	 * @return
	 * @throws JSONSerException 
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Office office) 
			throws JSONSerException {
		response.setContentType("text/json");
		Map message = new HashMap();
		if(Global.isDemoMode()){
			message.put("success", false);
			message.put("msg", "演示模式，不允许操作！");
		}else {
			officeService.deleteByParentId(office);
			message.put("success", true);
			message.put("msg", "删除机构成功");
			message.put("data", office);
		}
		return JSONUtil.toJSON(message);
	}
	
	@RequestMapping("checkRepeatOrgName")
	@ResponseBody
	public int checkRepeatOrgName(String name, Integer parentId, Integer orgId){
		Office office = new Office();
		office.setName(name);
		office.setParentId(parentId);
		List<Office> officeList = officeService.findOrgList(office);
		if(orgId != null && orgId.intValue() > 0) {
			if(officeList != null && officeList.size() > 0) {
				for(Office off : officeList) {
					if(off.getId().intValue() != orgId.intValue() && name.equals(off.getName())) {
						return 0;
					}
				}
			}
		}else {
			if(officeList != null && officeList.size() > 0) {
				return 0;
			}
		}
		return 1;
	}
	
	public void next(List<Map<String, Object>> root, Map<String, Object> previous) {
		Office off = new Office();
		off.setParentId(Integer.parseInt(previous.get("id").toString()));
		List<Office> children = officeService.findOrgList(off);
		if(children != null && children.size() > 0) {
			for(Office of : children) {
				Map<String, Object> child = Maps.newHashMap();
				child.put("id", of.getId());
				child.put("pId", of.getParent().getId());
				child.put("name", of.getName());
				root.add(child);
				next(root, child);
			}
		}else {
			previous.put("isParent", false);
		}
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "asynTree")
	public List<Map<String, Object>> asynTree(HttpServletResponse response, Integer id) {
		List<Map<String, Object>> root = Lists.newArrayList();
		User user = UserUtils.getUser();
		if(user.getOffice() != null && user.getOffice().getId() != null) {
			if(id == null) {
				id = UserUtils.getOrg().getId();
			}
		}
		Office office = officeService.get(id);
		if(office != null) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", office.getId());
			map.put("pId", office.getParent().getId());
			map.put("name", office.getName());
			root.add(map);
			next(root, map);
		}
		return root;
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		List<Map<String, Object>> root = Lists.newArrayList();
		User user = UserUtils.getUser();
		Integer id = 0;
		if(user.getOffice() != null && user.getOffice().getId() != null) {
			id = user.getOffice().getId();
		}
		Office office = officeService.get(id);
		if(office != null) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", office.getId());
			map.put("pId", office.getParent().getId());
			map.put("name", office.getName());
			root.add(map);
			next(root, map);
		}
		return root;
	}
	
	/**
	 * 添加机构机
	 * @param office 机构
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "setOrgSave")
	public String setOrgSave(Office office, Model model, RedirectAttributes redirectAttributes) {
		//创建机构父节点，因为机构是顶级，设其父节点为0
		Office parent = new Office(office.getParentId());
		office.setParent(parent);
		//设置机构部门
		//设置机构所处地点，因为地点字段占时没用，默认为0
		office.setArea(new Area(0));
		//设置机构是否可用，0不可用，1可用，默认为1
		office.setUseable("1");
		//设置机构类型，默认为1
		office.setType("1");
		office.setMaster(UserUtils.getUser().getName());
		
		office.setSort(officeService.findMaxSort(office) + 1);
		
		officeService.save(office);
		
		return "redirect:" + adminPath + "/sys/org/setOrg";
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "setOrgData")
	@ResponseBody
	public String setOrgAjax(Integer id, Model model) {
		response.setContentType("text/json");
		String allOffices = "[]";
		try {
			if(id == -1) {
				Office company = UserUtils.getUser().getCompany();
				Orgization orgOffice = new Orgization();
				orgOffice.setId(company.getId());
				orgOffice.setText(company.getName());
				List<Office> list = officeService.findOfficeTree(new Office(id));
				if (list.size()>0) {
					orgOffice.setChildren(false);
				}else{
					orgOffice.setChildren(true);
				}
				allOffices = JSONUtil.toJSON(orgOffice);
			}else {	
				List<Office> list = officeService.findOfficeTree(new Office(id));
				List<Orgization> lists=new ArrayList<Orgization>();
				for (int i=0;i<list.size();i++) {
					Office office2 = list.get(i);
					Integer id2 = office2.getId();
					//Integer parentId = office2.getParentId();
					String name = office2.getName();
					Integer count = office2.getCount();
					Orgization orgOffice = new Orgization();
					orgOffice.setId(id2);
					orgOffice.setText(name);
					if (count!=null&&count>0) {
						orgOffice.setChildren(true);
					}else{
						orgOffice.setChildren(false);
					}
					lists.add(orgOffice);
				}
				allOffices = JSONUtil.toJSON(lists);
			}
		} catch (JSONSerException e) {
			e.printStackTrace();
		}
		
		return allOffices;
	}
	
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file) {	
		return officeService.transferTo(file);
	}
	
}
