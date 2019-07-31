/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.utils.DateUtils;
import com.ambition.agile.common.utils.excel.ExportExcel;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.MessageInfo;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.MessageInfoService;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.jzmk.entity.Gene;
import com.ambition.agile.modules.jzmk.entity.Scale;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckTask;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;
import com.ambition.agile.modules.jzmk.service.GeneService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckScoreService;
import com.ambition.agile.modules.jzmk.service.ScaleCheckTaskService;
import com.ambition.agile.modules.jzmk.service.ScaleService;
import com.ambition.agile.modules.jzmk.service.ScaleTaskUserService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.ComUtil;

/**
 * 评测结果Controller
 * @author dortan
 * @version 2017-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/jzmk/scaleCheckResult")
public class ScaleCheckResultController extends BaseController {

	@Autowired
	private ScaleCheckResultService scaleCheckResultService;
	
	@Autowired
	private ScaleCheckTaskService scaleCheckTaskService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private CounselorTypeService counselorTypeService;
	
	@Autowired
	private GeneService geneService;
	
	@Autowired
	private ScaleTaskUserService scaleTaskUserService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MessageInfoService messageInfoService;
	
	@Autowired
	private ScaleCheckScoreService scaleCheckScoreService;
	
	/*@ModelAttribute
	public ScaleCheckResult get(@RequestParam(required=false) Integer id) {
		ScaleCheckResult entity = null;
		if (id!=null&&id>0){
			entity = scaleCheckResultService.get(id);
		}
		if (entity == null){
			entity = new ScaleCheckResult();
		}
		return entity;
	}*/
	
	@RequiresPermissions("jzmk:scaleCheckResult:view")
	@RequestMapping(value = "list")
	public String list(ScaleCheckResult scaleCheckResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		//scaleCheckResult.setOrganization(organizationService.getOrganizationJoinString());
		scaleCheckResult.setOrgId(user.getCompany().getId());
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		/*Page<ScaleCheckResult> page = scaleCheckResultService.findPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult); 
		//Page<ScaleCheckTask> page = scaleCheckTaskService.findPage(new Page<ScaleCheckTask>(request, response), scaleCheckTask);
		model.addAttribute("page", page);*/
		model.addAttribute("loginName",user.getLoginName());
		model.addAttribute("userName",user.getName());
		model.addAttribute("userSex",user.getUserType());
		model.addAttribute("userAge",user.getUserType());
		model.addAttribute("displayStyle","display:none");
		return "modules/jzmk/scaleCheckResultList";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:view")
	@RequestMapping(value = "listQuery")
	public String listQuery(ScaleCheckResult scaleCheckResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
		scaleTaskUser.setOrgId(user.getCompany().getId());
		if(request.getParameter("taskId")!=null&&!request.getParameter("taskId").equals(""))
		{
			scaleTaskUser.setTaskId(Integer.valueOf(request.getParameter("taskId")));
			model.addAttribute("epTaskId",request.getParameter("taskId"));
		}
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			scaleTaskUser.setTid(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("epScaleId",request.getParameter("scaleId"));
		}
		if(request.getParameter("userTypeId")!=null&&!request.getParameter("userTypeId").equals(""))
		{
			scaleTaskUser.setUserType(Integer.valueOf(request.getParameter("userTypeId")));
			model.addAttribute("epUserTypeId",request.getParameter("userTypeId"));
		}
		if(request.getParameter("beginAge")!=null&&!request.getParameter("beginAge").equals(""))
		{
			scaleTaskUser.setBeginAge(Integer.valueOf(request.getParameter("beginAge")));
			model.addAttribute("epBeginAge",request.getParameter("beginAge"));
		}
		if(request.getParameter("endAge")!=null&&!request.getParameter("endAge").equals(""))
		{
			scaleTaskUser.setEndAge(Integer.valueOf(request.getParameter("endAge")));
			model.addAttribute("epEndAge",request.getParameter("endAge"));
		}
		if(request.getParameter("gender")!=null&&!request.getParameter("gender").equals(""))
		{
			scaleTaskUser.setUserSex(Integer.valueOf(request.getParameter("gender")));
			model.addAttribute("epGender",request.getParameter("gender"));
		}
		if(request.getParameter("beginTime")!=null&&!request.getParameter("beginTime").equals(""))
		{
			scaleTaskUser.setStartTime(Date.valueOf(request.getParameter("beginTime")));
			model.addAttribute("epBeginTime",request.getParameter("beginTime"));
		}
		if(request.getParameter("endTime")!=null&&!request.getParameter("endTime").equals(""))
		{
			scaleTaskUser.setEndTime(Date.valueOf(request.getParameter("endTime")));
			model.addAttribute("epEndTime",request.getParameter("endTime"));
		}
		if(request.getParameter("orgId")!=null&&!request.getParameter("orgId").equals(""))
		{
			scaleTaskUser.setOrgId(Integer.valueOf(request.getParameter("orgId")));
			model.addAttribute("epOrgId",request.getParameter("orgId"));
		}
		if(request.getParameter("userName")!=null&&!request.getParameter("userName").equals(""))
		{
			scaleTaskUser.setUserLoginName(request.getParameter("userName"));
			model.addAttribute("epUserName",request.getParameter("userName"));
		}
		if(request.getParameter("resultState")!=null&&!request.getParameter("resultState").equals(""))
		{
			scaleTaskUser.setState(Integer.valueOf(request.getParameter("resultState")));
			model.addAttribute("epResultState",request.getParameter("resultState"));
		}
		List<ScaleTaskUser> scaleTaskUserList = scaleTaskUserService.findList(scaleTaskUser);
		model.addAttribute("scaleCheckResults", scaleTaskUserList);
		Page<ScaleTaskUser> page = scaleTaskUserService.findPage(new Page<ScaleTaskUser>(request, response), scaleTaskUser); 
		/*List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		Page<ScaleCheckResult> page = scaleCheckResultService.findPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult); */
		model.addAttribute("page", page);
		/*User user = UserUtils.getUser();
		model.addAttribute("loginName",user.getLoginName());
		model.addAttribute("userName",user.getName());
		model.addAttribute("userSex",user.getUserType());
		model.addAttribute("userAge",user.getUserType());*/
		model.addAttribute("displayStyle","display:block");
		return "modules/jzmk/scaleCheckResultList";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:view")
	@RequestMapping(value = "agreeRecheck")
	public String agreeRecheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
		if(request.getParameter("id")!=null&&!request.getParameter("id").equals(""))
		{
			scaleTaskUser.setId(Integer.valueOf(request.getParameter("id")));
			scaleTaskUser.setState(4);
			scaleTaskUserService.update(scaleTaskUser);
			ScaleTaskUser scaleTaskUserNow = scaleTaskUserService.get(Integer.valueOf(request.getParameter("id")));
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setFromUser(user.getLoginName());
			messageInfo.setToUser(scaleTaskUserNow.getUserName());
			messageInfo.setTitle("同意重测审核通过");
			StringBuilder content = new StringBuilder();
			content.append("量表“"+scaleTaskUserNow.getScaleName()+"”申请重测已审核通过。请尽快点击链接，"+"<a href=\'");
			content.append(request.getContextPath()+Global.getAdminPath()+"/jzmk/scale/quiz?id="+scaleTaskUserNow.getTid());
			content.append(scaleTaskUserNow.getTaskId()!=null?("&taskId="+scaleTaskUserNow.getTaskId()):"");
			content.append(scaleTaskUserNow.getTaskNumber()!=null?("&taskNumber="+scaleTaskUserNow.getTaskNumber()):"");
			content.append("\'>进入重测</a>");
			messageInfo.setContent(content.toString());
			messageInfoService.save(messageInfo);
			model.addAttribute("epResultState",request.getParameter("resultState"));
		}
		scaleTaskUser.setState(3);
		List<ScaleTaskUser> scaleTaskUserList = scaleTaskUserService.findList(scaleTaskUser);
		model.addAttribute("scaleCheckResults", scaleTaskUserList);
		Page<ScaleTaskUser> page = scaleTaskUserService.findPage(new Page<ScaleTaskUser>(request, response), scaleTaskUser); 
		model.addAttribute("page", page);
		model.addAttribute("displayStyle","display:block");
		return "modules/jzmk/scaleCheckResultList";
	}

	@RequiresPermissions("jzmk:scaleCheckResult:view")
	@RequestMapping(value = "form")
	public String form(ScaleCheckResult scaleCheckResult, Model model) {
		model.addAttribute("scaleCheckResult", scaleCheckResult);
		return "modules/jzmk/scaleCheckResultForm";
	}

	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "save")
	public String save(ScaleCheckResult scaleCheckResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scaleCheckResult)){
			return form(scaleCheckResult, model);
		}
		scaleCheckResultService.save(scaleCheckResult);
		addMessage(redirectAttributes, "保存评测结果成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckResult/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "delete")
	public String delete(ScaleCheckResult scaleCheckResult, RedirectAttributes redirectAttributes) {
		scaleCheckResultService.delete(scaleCheckResult);
		addMessage(redirectAttributes, "删除评测结果成功");
		return "redirect:"+Global.getAdminPath()+"/jzmk/scaleCheckResult/?repage";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "statistic")
	public String statistic(ScaleCheckResult scaleCheckResult, RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		CounselorType counselorType = new CounselorType();
		counselorType.setDelFlag("0");
		List<CounselorType> counselorTypes = counselorTypeService.findList(counselorType);
		model.addAttribute("counselorTypes", counselorTypes);
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		addMessage(redirectAttributes, "测评统计");
		return "modules/jzmk/checkStatistic";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "filter")
	public String filter(ScaleCheckResult scaleCheckResult, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		Gene gene = new Gene();
		gene.setDelFlag("0");
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			gene.setTid(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("nowScaleId", request.getParameter("scaleId"));
		}else
		{
			gene.setTid(scales.get(0).getId());
			model.addAttribute("nowScaleId", scales.get(0).getId());
		}
		List<Gene> genes = geneService.findList(gene);
		model.addAttribute("genes",genes);
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		model.addAttribute("displayStyle","display:none");
		addMessage(redirectAttributes, "测评筛选");
		return "modules/jzmk/checkFilter";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "filterQuery")
	public String filterQuery(ScaleCheckResult scaleCheckResult, RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		User user = UserUtils.getUser();
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(user.getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		Gene gene = new Gene();
		gene.setDelFlag("0");
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			scaleCheckResult.setTaskScaleId(Integer.valueOf(request.getParameter("scaleId")));
			gene.setTid(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("epScaleId",request.getParameter("scaleId"));
		}else
		{
			gene.setTid(scales.get(0).getId());
			model.addAttribute("nowScaleId", scales.get(0).getId());
		}
		if(request.getParameter("geneId")!=null&&!request.getParameter("geneId").equals(""))
		{
			scaleCheckResult.setGeneId(Integer.valueOf(request.getParameter("geneId")));
			model.addAttribute("epGeneId",request.getParameter("geneId"));
		}
		if(request.getParameter("orgId")!=null&&!request.getParameter("orgId").equals(""))
		{
			scaleCheckResult.setOrgId(Integer.valueOf(request.getParameter("orgId")));
			model.addAttribute("epOrgId",request.getParameter("orgId"));
		}
		List<Gene> genes = geneService.findList(gene);
		model.addAttribute("genes",genes);
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		Page<ScaleCheckResult> page = scaleCheckResultService.findPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult);
		model.addAttribute("page", page);
		model.addAttribute("displayStyle","display:block");
		addMessage(redirectAttributes, "测评筛选");
		return "modules/jzmk/checkFilter";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "export")
	public String export(ScaleCheckResult scaleCheckResult, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(UserUtils.getUser().getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		model.addAttribute("displayStyle","display:none");
		return "modules/jzmk/checkExport";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "exportQuery")
	public String exportQuery(ScaleCheckResult scaleCheckResult,Scale scale, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		scale.setDelFlag("0");
		scale.setOrgId(UserUtils.getUser().getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		scaleCheckResult.setOrgId(UserUtils.getUser().getCompany().getId());
		if(request.getParameter("taskId")!=null&&!request.getParameter("taskId").equals(""))
		{
			scaleCheckResult.setTaskId(Integer.valueOf(request.getParameter("taskId")));
			model.addAttribute("epTaskId",request.getParameter("taskId"));
		}
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			scaleCheckResult.setTaskScaleId(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("epScaleId",request.getParameter("scaleId"));
		}
		List<ScaleCheckResult> scaleCheckResults = scaleCheckResultService.findList(scaleCheckResult);
		model.addAttribute("scaleCheckResults", scaleCheckResults);
		Page<ScaleCheckResult> scaleCheckResultPage = scaleCheckResultService.findExportPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult);
		model.addAttribute("page",scaleCheckResultPage);
		model.addAttribute("displayStyle","display:block");
		return "modules/jzmk/checkExport";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "exportFile",method=RequestMethod.POST)
	public String exportFile(ScaleCheckResult scaleCheckResult, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("测评结果");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//设置头部信息
			String[] excelHeader = { "用户名","姓名", "测评批次", "量表名称","因子名称","原始分" ,"因子分"};
			for(int i=0;i<excelHeader.length;i++){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
			}
			//Page<Map<String,Object>> page = new Page<Map<String,Object>>(request, response);
			scaleCheckResult.setOrgId(UserUtils.getUser().getCompany().getId());
			if(request.getParameter("epTaskId")!=null&&!request.getParameter("epTaskId").equals(""))
			{
				scaleCheckResult.setTaskId(Integer.valueOf(request.getParameter("epTaskId")));
			}
			if(request.getParameter("epScaleId")!=null&&!request.getParameter("epScaleId").equals(""))
			{
				scaleCheckResult.setTaskScaleId(Integer.valueOf(request.getParameter("epScaleId")));
			}
			Page<ScaleCheckResult> page = scaleCheckResultService.findExportPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult);
			page.setPageSize(100);
			page.setPageNo(1);
			//page = scaleCheckResultService.findPage(new Page<ScaleCheckResult>(request, response), scaleCheckResult);
			List<ScaleCheckResult> exportList = page.getList(); 
			if(exportList!=null){
				for(int i = 0;i < exportList.size();i++){
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(exportList.get(i).getUserLoginName() == null ? "" : exportList.get(i).getUserLoginName().toString());
					row.createCell(1).setCellValue(exportList.get(i).getUserName() == null ? "" : exportList.get(i).getUserName().toString());
					row.createCell(2).setCellValue(exportList.get(i).getBatchNumber() == null ? "" : exportList.get(i).getBatchNumber().toString());
					row.createCell(3).setCellValue(exportList.get(i).getScaleName() == null ? "" : exportList.get(i).getScaleName().toString());
					row.createCell(4).setCellValue(exportList.get(i).getGeneName() == null ? "" : exportList.get(i).getGeneName().toString());
					row.createCell(5).setCellValue(exportList.get(i).getOriginalScore() == null ? "" : exportList.get(i).getOriginalScore().toString());
					row.createCell(6).setCellValue(exportList.get(i).getGeneScore() == null ? "" : exportList.get(i).getGeneScore().toString());
				}
			}
			String fileNameTmp = DateUtils.getDate("yyyyMMddHHmmss");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
	        response.setHeader("Content-disposition", "attachment;filename="+fileNameTmp+".xls");    
	        OutputStream ouputStream = null;
			try {
				ouputStream = response.getOutputStream();
				 wb.write(ouputStream);    
			     ouputStream.flush();    
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally{
				if(ouputStream!=null)
					try {
						ouputStream.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					} 
			} 
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出测评任务失败！失败信息："+e.getMessage());
		}
		return "modules/jzmk/checkExport";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "abpsyfilter")
	public String abpsyfilter(ScaleCheckScore scaleCheckScore, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(UserUtils.getUser().getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		List<ScaleCheckScore> scaleCheckScores = scaleCheckScoreService.findList(scaleCheckScore);
		model.addAttribute("scaleCheckScores", scaleCheckScores);
		Gene gene = new Gene();
		gene.setDelFlag("0");
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			gene.setTid(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("nowScaleId", request.getParameter("scaleId"));
		}else
		{
			gene.setTid(scales.get(0).getId());
			model.addAttribute("nowScaleId", scales.get(0).getId());
		}
		List<Gene> genes = geneService.findList(gene);
		model.addAttribute("genes",genes);
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		model.addAttribute("displayStyle","display:none");
		addMessage(redirectAttributes, "异常筛选");
		return "modules/jzmk/checkAbpsyfilter";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "abpsyfilterQuery")
	public String abpsyfilterQuery(ScaleCheckScore scaleCheckScore,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		Scale scale = new Scale();
		scale.setDelFlag("0");
		scale.setOrgId(UserUtils.getUser().getCompany().getId());
		List<Scale> scales = scaleService.findList(scale);
		model.addAttribute("scales", scales);
		/*List<ScaleCheckScore> scaleCheckScores = scaleCheckScoreService.findList(scaleCheckScore);
		model.addAttribute("scaleCheckScores", scaleCheckScores);*/
		Gene gene = new Gene();
		gene.setDelFlag("0");
		scaleCheckScore.setAbnormal(1);
		if(request.getParameter("scaleId")!=null&&!request.getParameter("scaleId").equals(""))
		{
			scaleCheckScore.setRid(Integer.valueOf(request.getParameter("scaleId")));
			gene.setTid(Integer.valueOf(request.getParameter("scaleId")));
			model.addAttribute("epScaleId",request.getParameter("scaleId"));
		}else
		{
			gene.setTid(scales.get(0).getId());
			model.addAttribute("nowScaleId", scales.get(0).getId());
		}
		if(request.getParameter("geneId")!=null&&!request.getParameter("geneId").equals(""))
		{
			scaleCheckScore.setGid(Integer.valueOf(request.getParameter("geneId")));
			model.addAttribute("epGeneId",request.getParameter("geneId"));
		}
		if(request.getParameter("orgId")!=null&&!request.getParameter("orgId").equals(""))
		{
			scaleCheckScore.setOrgId(Integer.valueOf(request.getParameter("orgId")));
			model.addAttribute("epOrgId",request.getParameter("orgId"));
		}
		if(request.getParameter("userName")!=null&&!request.getParameter("userName").equals(""))
		{
			scaleCheckScore.setLoginName(request.getParameter("userName"));
			model.addAttribute("epUserName",request.getParameter("userName"));
		}
		List<Gene> genes = geneService.findList(gene);
		model.addAttribute("genes",genes);
		model.addAttribute("departmentList", UserUtils.getDepartmentList());
		Page<ScaleCheckScore> page = scaleCheckScoreService.findPage(new Page<ScaleCheckScore>(request, response), scaleCheckScore);
		model.addAttribute("scaleCheckScores", page.getList());
		model.addAttribute("page", page);
		model.addAttribute("normalCount", (scaleCheckScoreService.getTotalCheckCount(scaleCheckScore)-scaleCheckScoreService.getAbnormalCount(scaleCheckScore)));
		model.addAttribute("abnormalCount",scaleCheckScoreService.getAbnormalCount(scaleCheckScore));
		model.addAttribute("displayStyle","display:block");
		addMessage(redirectAttributes, "异常筛选");
		return "modules/jzmk/checkAbpsyfilter";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "exportAbpsyFile",method=RequestMethod.POST)
	public String exportAbpsyFile(ScaleCheckScore scaleCheckScore, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		//scaleTaskUserService.delete(scaleTaskUser);
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("用户积分排名");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//设置头部信息
			String[] excelHeader = { "用户名","姓名","身份","量表名称","因子名称" ,"因子分"};
			for(int i=0;i<excelHeader.length;i++){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
			}
			scaleCheckScore.setAbnormal(1);
			if(request.getParameter("epScaleId")!=null&&!request.getParameter("epScaleId").equals(""))
			{
				scaleCheckScore.setRid(Integer.valueOf(request.getParameter("epScaleId")));
			}
			if(request.getParameter("epGeneId")!=null&&!request.getParameter("epGeneId").equals(""))
			{
				scaleCheckScore.setGid(Integer.valueOf(request.getParameter("epGeneId")));
			}
			if(request.getParameter("epOrgId")!=null&&!request.getParameter("epOrgId").equals(""))
			{
				scaleCheckScore.setOrgId(Integer.valueOf(request.getParameter("epOrgId")));
			}
			if(request.getParameter("epUserName")!=null&&!request.getParameter("epUserName").equals(""))
			{
				scaleCheckScore.setLoginName(request.getParameter("epUserName"));
			}
			Page<ScaleCheckScore> page = scaleCheckScoreService.findPage(new Page<ScaleCheckScore>(request, response), scaleCheckScore);
			List<ScaleCheckScore> exportList = page.getList(); 
			if(exportList!=null){
				for(int i = 0;i < exportList.size();i++){
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(exportList.get(i).getLoginName() == null ? "" : exportList.get(i).getLoginName().toString());
					row.createCell(1).setCellValue(exportList.get(i).getUserName() == null ? "" : exportList.get(i).getUserName().toString());
					row.createCell(2).setCellValue(exportList.get(i).getUserType() == null ? "" : transUserTypeName(exportList.get(i).getUserType()));
					row.createCell(3).setCellValue(exportList.get(i).getScaleName() == null ? "" : exportList.get(i).getScaleName().toString());
					row.createCell(4).setCellValue(exportList.get(i).getGeneName() == null ? "" : exportList.get(i).getGeneName().toString());
					row.createCell(5).setCellValue(exportList.get(i).getScore2() == null ? "" : exportList.get(i).getScore2().toString());
				}
			}
			String fileNameTmp = DateUtils.getDate("yyyyMMddHHmmss");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
	        response.setHeader("Content-disposition", "attachment;filename="+fileNameTmp+".xls");    
	        OutputStream ouputStream = null;
			try {
				ouputStream = response.getOutputStream();
				 wb.write(ouputStream);    
			     ouputStream.flush();    
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally{
				if(ouputStream!=null)
					try {
						ouputStream.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					} 
			} 
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出测评任务失败！失败信息："+e.getMessage());
		}
		return "modules/jzmk/checkAbpsyfilter";
	}
	
	@RequiresPermissions("jzmk:scaleCheckResult:edit")
	@RequestMapping(value = "statistic/result")
	public String statisticResult(String tid,ScaleCheckResult scaleCheckResult,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,Model model) {
		//scaleTaskUserService.delete(scaleTaskUser);
		Scale scale = scaleService.get(Integer.valueOf(tid));
		model.addAttribute("scale", scale);
		ScaleTaskUser scaleTaskUser = new ScaleTaskUser();
		scaleTaskUser.setTid(Integer.valueOf(tid));
		if(request.getParameter("startTime")!=null&&!request.getParameter("startTime").equals(""))
		{
			scaleTaskUser.setStartTime(Date.valueOf(request.getParameter("startTime")));
			model.addAttribute("epBeginTime",request.getParameter("startTime"));
		}
		if(request.getParameter("endTime")!=null&&!request.getParameter("endTime").equals(""))
		{
			scaleTaskUser.setEndTime(Date.valueOf(request.getParameter("endTime")));
			model.addAttribute("epEndTime",request.getParameter("endTime"));
		}
		if(request.getParameter("orgId")!=null&&!request.getParameter("orgId").equals(""))
		{
			scaleTaskUser.setOrgId(Integer.valueOf(request.getParameter("orgId")));
			model.addAttribute("epOrgId",request.getParameter("orgId"));
		}
		ScaleTaskUser statisticResult = scaleTaskUserService.getStatisticResuslt(scaleTaskUser);
		model.addAttribute("checkTotalCount", statisticResult.getCheckTotalCount());
		model.addAttribute("checkFinishedCount",statisticResult.getCheckFinishedCount());
		model.addAttribute("checkUnfinishedCount", statisticResult.getCheckUnfinishedCount());
		model.addAttribute("checkAbnormalCount", statisticResult.getCheckAbnormalCount());
		model.addAttribute("checkInvalidCount", statisticResult.getCheckInvalidCount());
		addMessage(redirectAttributes, "测评统计");
		return "modules/jzmk/checkStatisticResult";
	}
	
	public String getExcelFile()
	{
		return null;
	}
	
	public String transUserTypeName(Integer userType)
	{
		String retStr ="";
		if(userType!=null)
		{
			switch(userType.intValue()){
			case 0: 
				retStr = "平台管理员";
				break;
			case 1:
				retStr = "机构管理员";
				break;
			case 2:
				retStr = "咨询师";
				break;
			case 3:
				retStr = "家属";
				break;
			case 4:
				retStr = "来访者";
				break;
			}
		}
		return retStr;
	}
}