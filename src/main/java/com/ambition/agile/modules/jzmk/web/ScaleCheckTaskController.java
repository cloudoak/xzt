/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.ante.vo.ParentsVo;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.course.entity.CourseCatalog;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckTask;
import com.ambition.agile.modules.jzmk.service.ScaleCheckTaskService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 量表评测任务Controller
 * 
 * @author dortan
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleCheckTask")
public class ScaleCheckTaskController extends BaseController {

	@Autowired
	private ScaleCheckTaskService scaleCheckTaskService;

	@Autowired
	private ScaleService scaleService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private CounselorTypeService counselorTypeService;

	@Autowired
	private CounselorService counselorService;
	
	@Autowired
    private VisitorInfoService visitorInfoService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private OrganizationService organizationService;

	@ModelAttribute
	public ScaleCheckTask get(@RequestParam(required = false) Integer id) {
		ScaleCheckTask entity = null;
		if (id != null && id > 0) {
			entity = scaleCheckTaskService.get(id);
		}
		if (entity == null) {
			entity = new ScaleCheckTask();
		}
		return entity;
	}

	@RequiresPermissions("jzmk:scaleCheckTask:view")
	@RequestMapping(value = { "list", "" })
	public String list(ScaleCheckTask scaleCheckTask, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		//scaleCheckTask.setOrganization(organizationService.getOrganizationJoinString());
		scaleCheckTask.setOrgId(UserUtils.getUser().getCompany().getId());
		if(request.getParameter("batchNumber")!=null&&!request.getParameter("batchNumber").equals(""))
		{
			scaleCheckTask.setBatchNumber(request.getParameter("batchNumber").toString());
			model.addAttribute("epBatchNumber",request.getParameter("batchNumber"));
		}
		if(request.getParameter("startTime")!=null&&!request.getParameter("startTime").equals(""))
		{
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			scaleCheckTask.setStartTime(sdf.parse(request.getParameter("startTime").toString(),pos));
			model.addAttribute("epStartTime",request.getParameter("startTime"));
		}
		if(request.getParameter("endTime")!=null&&!request.getParameter("endTime").equals(""))
		{
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			scaleCheckTask.setEndTime(sdf.parse(request.getParameter("endTime").toString(),pos));
			model.addAttribute("epEndTime",request.getParameter("endTime"));
		}
		if(request.getParameter("principalName")!=null&&!request.getParameter("principalName").equals(""))
		{
			scaleCheckTask.setPrincipalName(request.getParameter("principalName").toString());
			model.addAttribute("epPrincipalName",request.getParameter("principalName"));
		}
		Page<ScaleCheckTask> page = scaleCheckTaskService.findPage(new Page<ScaleCheckTask>(request, response),
				scaleCheckTask);
		model.addAttribute("page", page);
		return "modules/jzmk/scaleCheckTaskList";
	}

	@RequiresPermissions("jzmk:scaleCheckTask:view")
	@RequestMapping(value = { "list/dc/scale", "" })
	public String listDcScale(ScaleCheckTask scaleCheckTask, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<ScaleCheckTask> page = scaleCheckTaskService.findPage(new Page<ScaleCheckTask>(request, response),
				scaleCheckTask);
		model.addAttribute("page", page);
		return "modules/jzmk/scaleCheckTaskList";
	}

	@RequiresPermissions("jzmk:scaleCheckTask:view")
	@RequestMapping(value = "form")
	public String form(ScaleCheckTask scaleCheckTask, Model model) {
		User user = UserUtils.getUser();
		// get departmentList
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		// get teacherType
		CounselorType counselorType = new CounselorType();
		counselorType.setDelFlag("0");
		counselorType.setOrganization(organizationService.getOrganizationJoinString());
		//counselorType.setOrgId(user.getCompany().getId());
		List<CounselorType> list = counselorTypeService.findList(counselorType);
		model.addAttribute("counselorTypeList", list);
		model.addAttribute("scaleCheckTask", scaleCheckTask);
		Scale scale = new Scale();
		scale.setDelFlag("0");
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		// User user=new User();
		user.setDelFlag("0");
		List<User> users = systemService.findUser(user);
		model.addAttribute("users", users);
		return "modules/jzmk/scaleCheckTaskForm";
	}

	@RequiresPermissions("jzmk:scaleCheckTask:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,ScaleCheckTask scaleCheckTask, Model model, RedirectAttributes redirectAttributes) {
		/*
		if (!beanValidator(model, scaleCheckTask)) {
			return form(scaleCheckTask, model);
		}
		scaleCheckTaskService.save(scaleCheckTask);
		addMessage(redirectAttributes, "保存量表评测任务成功");
		*/
		return "redirect:" + Global.getAdminPath() + "/jzmk/scaleChoose/form?queryType="+request.getParameter("queryType")+"&ids="+request.getParameter("ids");
	}

	@RequiresPermissions("jzmk:scaleCheckTask:edit")
	@RequestMapping(value = "delete")
	public String delete(String taskId,RedirectAttributes redirectAttributes) {
		ScaleCheckTask scaleCheckTask = scaleCheckTaskService.get(Integer.valueOf(taskId));
		scaleCheckTask.setDelFlag("1");
		scaleCheckTaskService.delete(scaleCheckTask);
		addMessage(redirectAttributes, "删除量表评测任务成功");
		return "redirect:" + Global.getAdminPath() + "/jzmk/scaleCheckTask/list";
	}

	@RequiresPermissions("jzmk:scaleCheckTask:edit")
	@RequestMapping(value = "stop")
	public String stop(String taskId,RedirectAttributes redirectAttributes) {
		ScaleCheckTask scaleCheckTask = scaleCheckTaskService.get(Integer.valueOf(taskId));
		scaleCheckTask.setEndTime(new Date());
		scaleCheckTask.setState(2);
		scaleCheckTaskService.save(scaleCheckTask);
		addMessage(redirectAttributes, "删除量表评测任务成功");
		return "redirect:" + Global.getAdminPath() + "/jzmk/scaleCheckTask/list";
	}
	
	@ResponseBody
	@RequiresPermissions("jzmk:scaleCheckTask:edit")
	@RequestMapping(value = "queryCounselor")
	public List<Map<String, Object>> queryCounselor(HttpServletRequest request, HttpServletResponse response,
			ScaleCheckTask scaleCheckTask, Model model, RedirectAttributes redirectAttributes) {
		// scaleCheckTaskService.delete(scaleCheckTask);
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = UserUtils.getUser();
		if (request.getParameter("queryType").equals("1")) {
			Counselor counselor = new Counselor();
			counselor.setOrganization(organizationService.getOrganizationJoinString());
			if (request.getParameter("userName")!=null&&!request.getParameter("userName").equals(""))
			{
				//counselor.setName(request.getParameter("userName"));
				User userQuery = new User();
				userQuery.setName(request.getParameter("userName"));
				counselor.setUser(userQuery);
			}
			//counselor.setOrgId(user.getCompany().getId());
			List<Counselor> list = counselorService.findList(counselor);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = Maps.newHashMap();
				Counselor e = list.get(i);
				map.put("id", e.getUser().getId());
				map.put("loginName", e.getUser().getLoginName());
				map.put("name", e.getUser().getName());
				map.put("sex", e.getSex());
				/*CounselorType counselorType = new CounselorType();
				counselorType = counselorTypeService.get(e.getCounselorType());*/
				map.put("type", "咨询师");
				mapList.add(map);
			}
			addMessage(redirectAttributes, "查询咨询师成功");
		}
		
		if (request.getParameter("queryType").equals("2")) {
			VisitorInfo visitorInfo = new VisitorInfo();
			//visitorInfo.setOrgId(user.getCompany().getId());
			visitorInfo.setOrganization(organizationService.getOrganizationJoinString());
			if (request.getParameter("userName")!=null&&!request.getParameter("userName").equals(""))
			{
				//visitorInfo.setUser(user);request.getParameter("userName"));
				User userQuery = new User();
				userQuery.setName(request.getParameter("userName"));
				visitorInfo.setUser(userQuery);
			}
			
			List<VisitorInfo> listVisitor = visitorInfoService.findList(visitorInfo);
			for (int i = 0; i < listVisitor.size(); i++) {
				Map<String, Object> map = Maps.newHashMap();
				VisitorInfo e = listVisitor.get(i);
				map.put("id", e.getUserId());
				map.put("loginName", e.getUser().getLoginName());
				map.put("name", e.getUser().getName());
				map.put("sex", e.getSex());
				map.put("type", "来访者");
				mapList.add(map);
			}
			addMessage(redirectAttributes, "查询咨询师成功");
		}
		
		if (request.getParameter("queryType").equals("3")) {
			Parents parentsVo = new ParentsVo();
			//parentsVo.setOrgId(user.getCompany().getId());
			parentsVo.setOrganization(organizationService.getOrganizationJoinString());
			if (request.getParameter("userName")!=null&&!request.getParameter("userName").equals(""))
			{
				parentsVo.setName(request.getParameter("userName"));
			}
			List<Parents> listParents = parentsService.findList(parentsVo);
			for (int i = 0; i < listParents.size(); i++) {
				Map<String, Object> map = Maps.newHashMap();
				Parents e = listParents.get(i);
				map.put("id", e.getUserId());
				map.put("loginName", e.getAccount());
				map.put("name", e.getParentName());
				map.put("sex", e.getGender());
				map.put("type", "家属");
				mapList.add(map);
			}
			addMessage(redirectAttributes, "查询咨询师成功");
		}

		return mapList;
		// return "modules/jzmk/scaleCheckTaskForm";
	}
}