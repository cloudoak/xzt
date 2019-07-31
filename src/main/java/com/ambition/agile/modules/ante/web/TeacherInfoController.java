/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Department;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.DepartmentService;
import com.ambition.agile.modules.counsel.constant.CounselorConstant;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorOffice;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.course.entity.Course;
import com.ambition.agile.modules.course.entity.CoursePermissions;
import com.ambition.agile.modules.sys.entity.Menu;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 教师信息类Controller
 * @author fengqq
 * @version 2017-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/teacherInfo")
public class TeacherInfoController extends BaseController {

	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private CounselorTypeService counselorTypeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
    private SystemService systemService;
	
	@Autowired 
	private OfficeService officeService;
	
	@Autowired
	public OrganizationService organizationService;
	
	@ModelAttribute
	public Counselor get(@RequestParam(required=false) Integer id) {
		Counselor entity = null;
		if (id!=null&&id>0){
			entity = counselorService.get(id);
		}
		if (entity == null){
			entity = new Counselor();
		}
		return entity;
	}
	
	/**
	 * 教师查询列表
	 * @param teacherInfo
	 * @param counselor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(Counselor counselor, HttpServletRequest request, HttpServletResponse response, Model model) {
		CounselorType counselorType = new CounselorType();
		counselorType.setOrganization(organizationService.getOrganizationJoinString());
		Page<CounselorType> counselorTypes = counselorTypeService.findPage(new Page<CounselorType>(request, response), counselorType);
		model.addAttribute("counselorTypes", counselorTypes);
		Department department = new Department();
		department.setOrganization(organizationService.getOrganizationJoinString());
		Page<Department> departments = departmentService.findPage(new Page<Department>(request, response), department);
		model.addAttribute("departments", departments);
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		Page<Counselor> page = counselorService.findPage(new Page<Counselor>(request, response), counselor);
		model.addAttribute("page", page);
		return "modules/ante/teacherInfoList";
	}
	
	@RequiresPermissions("ante:teacherInfo:res")
	@RequestMapping(value = {"res"})
	public String resource(Integer id, Model model) {
		//查询所有的权限资源
		List<Menu> sourcelist = systemService.findAllMenu();
		
		Counselor counselor = counselorService.get(id);
		//查询已存在的所有权限资源
		List<Integer> list = counselorService.selectCounselorMenu(counselor);
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
		model.addAttribute("counselor", counselor);
		model.addAttribute("sourcelist",sourcelist);
		model.addAttribute("menuList",menuList);
		model.addAttribute("counselorId", id);
		return "modules/ante/teacherInfoGrantList";
	}
	
	//设置角色权限
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = {"saveMenus"})
	@ResponseBody
	public Integer saveMenus(String menuIds, Integer counselorId, Model model) {
		//删除角色权限
		counselorService.deleteCounselorMenu(new Counselor(counselorId));
		if("".equals(menuIds)){
			return 1;
		}
		String[] menuIdList = menuIds.split(",");
		for (String menuId : menuIdList) {
			counselorService.updateCounselorMenu(counselorId, Integer.valueOf(menuId));
		}
		return 1;
	}
	
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "setBrowsePermissions")
	public String setBrowsePermissions(@RequestParam(required=false) Integer id,  Model model, HttpServletRequest request) {
		if (null != id  && id>0){
			Counselor counselor = counselorService.get(id);
			model.addAttribute("counselor", counselor);
			List<CounselorOffice> courseOffices = counselorService.selectCounselorOffice(id);
			model.addAttribute("courseOffices", courseOffices);
		}
		Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		return "modules/ante/teacherInfoBrowseForm";
	}
	
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "deleteCounselorOffice")
	public String deleteCounselorOffice(@RequestParam(required=false) Integer id,  Model model, HttpServletRequest request) {
		if (null != id  && id>0){
			Counselor counselor = counselorService.get(id);
			model.addAttribute("counselor", counselor);
			List<CounselorOffice> courseOffices = counselorService.selectCounselorOffice(id);
			model.addAttribute("courseOffices", courseOffices);
		}
		Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		return "redirect:"+Global.getAdminPath()+"/ante/teacherInfo/setBrowsePermissions?id" + id;
	}
	
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = "savePermissions")
	public String savePermissions(@RequestParam(required=false) Integer id, CounselorOffice counselorOffice, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		Integer officeId = counselorOffice.getOfficeId();
		Integer counselorId = counselorOffice.getCounselorId();
		if(officeId != null) {
			next(new CounselorOffice(), counselorId, officeId);
		}
		addMessage(redirectAttributes, "保存咨询师机构成功");
		return "redirect:" + Global.getAdminPath() + "/ante/teacherInfo/setBrowsePermissions?id=" + counselorId;
	}
	
	public void next(CounselorOffice counselorOffice, Integer counselorId, Integer previous) {
		Office off = new Office();
		off.setParentId(previous);
		List<Office> children = officeService.findOrgList(off);
		if(children != null && children.size() > 0) {
			for(Office of : children) {
				CounselorOffice cOffice = new CounselorOffice();
				cOffice.setCounselorId(counselorId);
				cOffice.setOfficeId(of.getId());
				next(cOffice, counselorId, of.getId());
			}
		}else {
			counselorService.insertCounselorOffice(counselorOffice);
		}
	}
	
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = "deletePermissions")
	public String deletePermissions(Integer id, Integer counselorId, RedirectAttributes redirectAttributes) {
		Counselor counselor = new Counselor();
		counselor.setId(id);
		counselorService.deleteCounselorOffice(counselor);
		addMessage(redirectAttributes, "删除咨询师机构成功");
		return "redirect:"+Global.getAdminPath()+"/ante/teacherInfo/setBrowsePermissions?id=" + counselorId;
	}

	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "form")
	public String form(Counselor counselor, Model model) {
		if(counselor.getId()!=null) {
			counselor = counselorService.get(counselor.getId());
		}
		model.addAttribute("counselor", counselor);
		return "modules/ante/teacherInfoForm";
	}
	
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "toTeacherInfoRight")
	public String toTeacherInfoRight(Counselor counselor,Model model) {
		Page<CounselorType> counselorTypes = counselorTypeService.findPage(new Page<CounselorType>(request, response), new CounselorType());
		model.addAttribute("counselorTypes", counselorTypes);
		
		Page<Department> departments = departmentService.findPage(new Page<Department>(request, response), new Department());
		model.addAttribute("departments", departments);
		if(counselor!=null && counselor.getId()!=null) {
			counselor = counselorService.get(counselor);
			User user = systemService.getUser(counselor.getUser().getId());
			counselor.setUserId(user.getId());
		}
		model.addAttribute("counselor", counselor);
		return "modules/ante/teacherInfoRightForm";
	}
	
	/**
	 * 查看
	 * @param counselor
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "view")
	public String view(Counselor counselor, Model model) {
		Page<CounselorType> counselorTypes = counselorTypeService.findPage(new Page<CounselorType>(request, response), new CounselorType());
		model.addAttribute("counselorTypes", counselorTypes);
		
		Page<Department> departments = departmentService.findPage(new Page<Department>(request, response), new Department());
		model.addAttribute("departments", departments);
		
		if(counselor.getId()!=null) {
			counselor = counselorService.get(counselor.getId());
		}
		return "modules/ante/teacherInfoView";
	}

	/**
	 * 新增
	 * @param image
	 * @param counselor
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam(value = "image", required = false) MultipartFile image,
			Counselor counselor, HttpServletRequest request, 
			Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselor)){
			return form(counselor, model);
		}
		
		String path = request.getSession().getServletContext().getRealPath("image");
		if(image!=null) {
			String fileName = image.getOriginalFilename();
			String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			path = path + "\\" +dateStr + "\\";
			System.out.println(path);
			File targetFile = new File(path, fileName);  
			if(!targetFile.exists()){  
				targetFile.mkdirs();  
			}  
			String imagePath = targetFile.getPath();
			try {
				image.transferTo(targetFile);
			}
			catch (Exception e) {
				
			} 
		}
        
		counselor.setApplyStatus(0);
		counselorService.save(counselor);
		addMessage(redirectAttributes, "保存教师基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ante/teacherInfo/list";
	}
	
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = "modiyStatus")
	public String modiyStatus(Counselor counselor, Model model, RedirectAttributes redirectAttributes) {
		counselorService.save(counselor);
		addMessage(redirectAttributes, "操作成功");
		return "modules/ante/teacherInfoViewList";
	}
	
	/**
	 * 删除(一条)
	 * @param teacherInfo
	 * @param counselor
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(Counselor counselor, boolean reviewSign, RedirectAttributes redirectAttributes) {
		counselorService.delete(counselor);
		addMessage(redirectAttributes, "删除教师基本信息成功");
		
		if(reviewSign==true) {
        	return "redirect:" + Global.getAdminPath() + "/ante/teacherInfo/reviewList";
        }else {
        	return "redirect:" + Global.getAdminPath() + "/ante/teacherInfo/list";
        }
	}
	
	/**
	 * 审核列表
	 * @param counselor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = {"reviewList"})
	public String reviewList(Counselor counselor, HttpServletRequest request, HttpServletResponse response, Model model) {
		CounselorType counselorType = new CounselorType();
		counselorType.setOrganization(organizationService.getOrganizationJoinString());
		Page<CounselorType> counselorTypes = counselorTypeService.findPage(new Page<CounselorType>(request, response), counselorType);
		model.addAttribute("counselorTypes", counselorTypes);
		Department department = new Department();
		department.setOrganization(organizationService.getOrganizationJoinString());
		Page<Department> departments = departmentService.findPage(new Page<Department>(request, response), department);
		model.addAttribute("departments", departments);
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		counselor.setApplyStatus(CounselorConstant.STATUS_AUDIT);
		Page<Counselor> page = counselorService.findPage(new Page<Counselor>(request, response), counselor);
		model.addAttribute("page", page);
		return "modules/ante/teacherInfoViewList";
	}
	
	@RequiresPermissions("ante:teacherInfo:edit")
	@RequestMapping(value = {"reviewStatus"})
	public String reviewStatus(Counselor counselor, Model model) {
		model.addAttribute("counselor", counselor);
		return "modules/ante/teacherInfoViewStatus";
	}
	
	@RequiresPermissions("ante:teacherInfo:view")
	@RequestMapping(value = "toModOffice")
	public String toModOffice() {
		return "modules/ante/teacherInfoOffice";
	}
	

}