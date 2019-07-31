/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.alibaba.fastjson.JSONArray;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.jzmk.entity.Question;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckTask;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskList;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;
import com.ambition.agile.modules.jzmk.service.QuestionService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckTaskService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskListService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskUserService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 量表条目Controller
 * @author wyz
 * @version 2017-09-10
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleChoose")
public class ScaleChooseController extends BaseController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private CounselorTypeService counselorTypeService;
	
	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private ScaleTaskUserService scaleTaskUserService;
	
	@Autowired
	private ScaleTaskListService scaleTaskListService;
	
	@Autowired
	private ScaleCheckTaskService scaleCheckTaskService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@ModelAttribute
	public Question get(@RequestParam(required=false) Integer id) {
		Question entity = null;
		if (id!=null&&id>0){
			entity = questionService.get(id);
		}
		if (entity == null){
			entity = new Question();
		}
		return entity;
	}
	
	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = {"list", ""})
	public String list(Question question, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), question); 
		model.addAttribute("page", page);
		Scale scale=new Scale();
		scale.setDelFlag("0");
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		return "modules/jzmk/questionList";
	}
	
	/**
	 * 查询未完成的量表条目
	 * @param question
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = {"qa", ""})
	public String listQuestion(Question question, Integer sid, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(question!=null&&question.getSid()==null){
			question.setSid(sid);
		}
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), question); 
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		return "modules/jzmk/qaset";
	}

	@RequiresPermissions("jzmk:question:view")
	@RequestMapping(value = "form")
	public String form(ScaleCheckTask scaleCheckTask, Model model,HttpServletRequest request) {
		model.addAttribute("scaleCheckTask", scaleCheckTask);
		User user = UserUtils.getUser();
		Scale scale=new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		/*
		String idsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("ids"));
		JSONArray jsonArray = JSONArray.parseArray(idsStr);
		StringBuffer stringBuffer=new StringBuffer();
		for (Object obj : jsonArray) {
			stringBuffer.append(obj.toString()+",");
		}
		String qids = stringBuffer.toString().substring(0, stringBuffer.toString().length()-1);
		*/
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("queryType",request.getParameter("queryType"));
		model.addAttribute("ids",request.getParameter("ids"));
		model.addAttribute("scales", scales);
		return "modules/jzmk/scaleChooseForm";
	}

	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "save")
	public String save(Question question, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		return "redirect:" + Global.getAdminPath() + "/jzmk/scaleChoose/time?queryType="+request.getParameter("queryType")+"&ids="+
	            request.getParameter("ids")+"&tids="+request.getParameter("tids");
	}
	
	/**
	 * 条目序号上下移动
	 * @param op
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "update")
	public String updateNumber(String op, Integer id, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		
		return "modules/jzmk/qaset";
		
	}
	
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "delete")
	public String delete(Question question, Integer sid, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		questionService.delete(question);
		addMessage(redirectAttributes, "删除量表条目成功");
		Question qut = new Question();
		qut.setSid(sid);
		List<Question> list = questionService.findList(qut);
		int i = 0, size = list.size();
		for(; i < size; i++) {
			Question ques = list.get(i);
			if(ques.getNumber() == (i + 1)) {
				continue;
			}
			ques.setNumber(i+1);
			questionService.save(ques);
		}
		Page<Question> page = questionService.findPage(new Page<Question>(request, response), qut);
		model.addAttribute("page", page);
		model.addAttribute("sid", sid);
		return "modules/jzmk/qaset";
	}
	
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "time")
	public String timeSet(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = UserUtils.getUser();
		Counselor counselor = new Counselor();
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		//counselor.setOrgId(user.getCompany().getId());
		List<Counselor> list = counselorService.findList(counselor);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = Maps.newHashMap();
			Counselor e = list.get(i);
			map.put("id", e.getUser().getId());
			map.put("name", e.getUser().getName());
			map.put("sex", e.getSex());
			CounselorType counselorType = new CounselorType();
			counselorType = counselorTypeService.get(e.getCounselorType());
			if(counselorType!=null)
			{
				map.put("type", counselorType.getTypeName());
			}
			mapList.add(map);
		}
		model.addAttribute("queryType",request.getParameter("queryType"));
		model.addAttribute("ids",request.getParameter("ids"));
		model.addAttribute("tids",request.getParameter("tids"));
		model.addAttribute("userList", mapList);
		model.addAttribute("batchNumber", scaleCheckTaskService.getBatchNumber()+1);
		return "modules/jzmk/scaleCheckTimeForm";
	}
	
	@ResponseBody
	@RequiresPermissions("jzmk:question:edit")
	@RequestMapping(value = "saveTime")
	public List<Map<String, Object>> timeSave(Model model, RedirectAttributes redirectAttributes, ScaleCheckTask scaleCheckTask,HttpServletRequest request, HttpServletResponse response) {
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();
		User user = UserUtils.getUser();
		Counselor counselor = new Counselor();
		counselor.setOrganization(organizationService.getOrganizationJoinString());
		//counselor.setOrgId(user.getCompany().getId());
		if (!beanValidator(model, scaleCheckTask)) {
			map.put("code", 0);
			mapList.add(map);
			return mapList;
		}
		Integer userRole = null;
		Integer userAge = null;
		Integer userSex = null;
		String userLoginName = null;
		String userName = null;
		
			
		//if (request.getParameter("queryType").equals("1")) {
			
			String idsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("ids"));
			JSONArray jsonArray1 = JSONArray.parseArray(idsStr);
			StringBuffer stringBuffer1=new StringBuffer();
			for (Object obj : jsonArray1) {
				stringBuffer1.append(obj.toString()+",");
			}
			String tidsStr = StringFunctionUtils.replaceWebJsp(request.getParameter("tids"));
			JSONArray jsonArray2 = JSONArray.parseArray(tidsStr);
			StringBuffer stringBuffer2=new StringBuffer();
			for (Object obj : jsonArray2) {
				stringBuffer2.append(obj.toString()+",");
			}
			String memberIds = stringBuffer1.toString().substring(0, stringBuffer1.toString().length()-1);
			String scaleIds = stringBuffer2.toString().substring(0, stringBuffer2.toString().length()-1);
			String [] taskUserIds = memberIds.split(",");
			String [] taskListIds = scaleIds.split(",");
			scaleCheckTask.setPrincipalId(Integer.valueOf(request.getParameter("teacherType")));
			scaleCheckTask.setPrincipalName(request.getParameter("teacherName"));
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos1 = new ParsePosition(0);
			Date startTime = formatter1.parse(request.getParameter("startTime"), pos1);
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos2 = new ParsePosition(0);
			Date endTime = formatter2.parse(request.getParameter("endTime"), pos2);
			scaleCheckTask.setStartTime(startTime);
			scaleCheckTask.setEndTime(endTime);
			scaleCheckTask.setBatchNumber(request.getParameter("checkNumber"));
			scaleCheckTask.setTaskUserIds(memberIds);
			scaleCheckTask.setTaskGeneIds(scaleIds);
			scaleCheckTask.setState(0);
			scaleCheckTask.setOrgId(user.getCompany().getId());
			scaleCheckTaskService.save(scaleCheckTask);
			Integer taskId = scaleCheckTask.getId();
			for(int i=0; i < taskListIds.length; i++) {
				ScaleTaskList scaleTaskList = new ScaleTaskList();
				scaleTaskList.setGeneId(Integer.valueOf(taskListIds[i]));
				scaleTaskList.setTaskId(taskId);
				scaleTaskList.setOrgId(user.getCompany().getId());
				scaleTaskList.setDelFlag("0");
				scaleTaskListService.save(scaleTaskList);
				for(int j=0; j < taskUserIds.length; j++) {
					if(request.getParameter("queryType").equals("1")) {
						userRole = 2;
						Counselor counselorNow = counselorService.getUserByUserId(Integer.valueOf(taskUserIds[j]));
						userSex = Integer.valueOf(counselorNow.getSex());
						userAge = counselorNow.getAge();
					}else if(request.getParameter("queryType").equals("2")){
						userRole = 0;
						VisitorInfo visitorInfoNow = visitorInfoService.getUserByUserId(Integer.valueOf(taskUserIds[j]));
						userSex = visitorInfoNow.getSex();
						userAge = visitorInfoNow.getAge();
					}else if(request.getParameter("queryType").equals("3")){
						userRole = 1;
						Parents parentsNow = parentsService.getUserByUserId(Integer.valueOf(taskUserIds[j]));
						userSex = Integer.valueOf(parentsNow.getGender());
						userAge = Integer.valueOf(parentsNow.getAge());
					}
					User userNow = UserUtils.get(Integer.valueOf(taskUserIds[j]));
					userLoginName = userNow.getLoginName();
					userName = userNow.getName();
					ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
					scaleTaskUser.setUserId(Integer.valueOf(taskUserIds[j]));
					scaleTaskUser.setRole(userRole);
					scaleTaskUser.setTid(Integer.valueOf(taskListIds[i]));
					scaleTaskUser.setTaskId(taskId);
					scaleTaskUser.setStartTime(startTime);
					scaleTaskUser.setEndTime(endTime);
					scaleTaskUser.setState(0);
					scaleTaskUser.setOrgId(user.getCompany().getId());
					scaleTaskUser.setDelFlag("0");
					scaleTaskUser.setUserLoginName(userLoginName);
					scaleTaskUser.setUserName(userName);
					scaleTaskUser.setUserSex(userSex);
					scaleTaskUser.setUserAge(userAge);
					scaleTaskUserService.save(scaleTaskUser);
				}
			}
			
			addMessage(redirectAttributes, "保存量表评测任务成功");
			
			map.put("code", 1);
			mapList.add(map);
			addMessage(redirectAttributes, "查询咨询师成功");
		//}
		model.addAttribute("userList", mapList);
		return mapList;
		//return "modules/jzmk/scaleCheckTimeForm";
	}
}