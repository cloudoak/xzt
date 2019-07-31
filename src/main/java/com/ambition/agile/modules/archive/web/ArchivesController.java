/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.archive.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.DateUtils;
import com.ambition.agile.common.utils.FreeMarkers;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.jzmk.service.ScaleCheckResultService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.DictUtils;
import com.ambition.agile.modules.sys.utils.UserUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.CounselorTypeService;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.archive.entity.Archives;
import com.ambition.agile.modules.archive.service.ArchivesService;
import com.ambition.agile.modules.counsel.entity.Counselor;

@Controller
@RequestMapping(value = "${adminPath}/archives/archive")
public class ArchivesController extends BaseController {

	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
    private ArchivesService archivesService;
	
	@Autowired
	private CounselorTypeService counselorTypeService;

    @Autowired
    private SystemService systemService;
    
    @Autowired
    private OfficeService officeService;
    
    @Autowired
    private ScaleCheckResultService scaleCheckResultService;
	
	@ModelAttribute
	public Archives get(@RequestParam(required=false) Integer id) {
		Archives entity = null;
		if (id!=null&&id>0){
			entity = archivesService.get(id);
		}
		if (entity == null){
			entity = new Archives();
		}
		return entity;
	}
	
	@RequiresPermissions("archives:archive:view")
	@RequestMapping("list")
	public String list(Archives archives, String orgInput, Integer orgId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		if(orgInput != null && orgId != null) {
			Office office = new Office();
			office.setId(orgId);
			office.setName(orgInput);
			archives.setOrg(office);
		}
		Page<Archives> page = archivesService.findPage(new Page<Archives>(request, response), archives);
        model.addAttribute("page", page);
        model.addAttribute("archives", archives);
		return "modules/archive/archivesList";
	}
	

	@RequiresPermissions("archives:archive:view")
	@RequestMapping(value = "form")
	public String form(Archives archives, Model model) {
		Archives archivesEntity = archivesService.get(archives);
		Integer authType = archivesEntity.getAuthType();
		model.addAttribute("archives", archivesEntity);
		
		if(authType.intValue() == UserConstant.USER_CONSULTANT_TYPE){
			List<CounselorType> counselorTypes = counselorTypeService.findList(new CounselorType());
			model.addAttribute("counselorTypes", counselorTypes);
			Counselor counselor = counselorService.getUserByUserId(archivesEntity.getUserId());
			model.addAttribute("counselor", counselor);
			ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
			scaleCheckResult.setTaskUserId(archivesEntity.getUserId());
			List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findList(scaleCheckResult);
			model.addAttribute("scaleCheckResultList", scaleCheckResultList);
			return "modules/archive/counselorDetails";
		}else if(authType.intValue() == UserConstant.USER_FAMILYMEMBERS_TYPE){
			Parents parents = parentsService.getUserByUserId(archivesEntity.getUserId());
			model.addAttribute("parents", parents);
			return "modules/archive/parentsDetails";
		}else if(authType.intValue() == UserConstant.USER_VISITOR_TYPE){
			VisitorInfo visitorInfo = visitorInfoService.getUserByUserId(archivesEntity.getUserId());
			model.addAttribute("visitorInfo", visitorInfo);
			return "modules/archive/visitorInfoDetails";
		}else {
			List<CounselorType> counselorTypes = counselorTypeService.findList(new CounselorType());
			model.addAttribute("counselorTypes", counselorTypes);
			Counselor counselor = counselorService.getUserByUserId(archivesEntity.getUserId());
			model.addAttribute("counselor", counselor);
			ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
			scaleCheckResult.setTaskUserId(archivesEntity.getUserId());
			List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findList(scaleCheckResult);
			model.addAttribute("scaleCheckResultList", scaleCheckResultList);
			return "modules/archive/counselorDetails";
		}
	}
	
	@RequiresPermissions("archives:archive:view")
	@RequestMapping(value = "downloadArchives")
	public void downloadArchives(Integer userId, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
        	Archives archives = new Archives();
        	archives.setUserId(userId);
    		Archives archivesEntry = archivesService.get(archives);
        	Integer uid = archivesEntry.getUserId();
        	String name = archivesEntry.getName();
        	String fileName = String.format("%s/%d%s.doc", "", uid, name);
        	Integer authType = archivesEntry.getAuthType();
    		Map<String, Object> modelMap = com.google.common.collect.Maps.newHashMap();
        	String templateName = "consultant.ftl"; 
    		Configuration cfg = FreeMarkers.buildConfiguration("classpath:/archives/");
	        if(authType.intValue() == UserConstant.USER_CONSULTANT_TYPE){
	        	Counselor counselor = counselorService.getUserByUserId(archivesEntry.getUserId());
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(uid);
				List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findUnHtmlList(scaleCheckResult);
				modelMap.put("counselor", counselor);
				modelMap.put("scaleCheckResult", scaleCheckResultList);
				templateName = "consultant.ftl";
			}else if(authType.intValue() == UserConstant.USER_FAMILYMEMBERS_TYPE){
				Parents familyMembers = parentsService.getUserByUserId(uid);
				modelMap.put("familyMembers", familyMembers);
				modelMap.put("now", DateUtils.getDateTime());
				templateName = "familyMembers.ftl";
			}else if(authType.intValue() == UserConstant.USER_VISITOR_TYPE){
				VisitorInfo visitorInfo = visitorInfoService.getUserByUserId(uid);
				modelMap.put("visitor", visitorInfo);
				templateName = "visitor.ftl";
			}else {
				Counselor counselor = counselorService.getUserByUserId(uid);
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(uid);
				List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findUnHtmlList(scaleCheckResult);
				modelMap.put("counselor", counselor);
				modelMap.put("scaleCheckResult", scaleCheckResultList);
				templateName = "consultant.ftl";
			}
	        modelMap.put("now", DateUtils.getDateTime());
	        modelMap.put("today", DateUtils.getDate());
	        Template template = cfg.getTemplate(templateName);
    		String result = FreeMarkers.renderTemplate(template, modelMap);
    		response.reset();
    		response.setContentType("application/msword;charset=UTF-8");
    		response.setHeader("Accept", "application/x-msdownload");//URLEncoder.encode(, "UTF-8")
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			OutputStream out = response.getOutputStream();
    		out.write(result.getBytes());
    		out.flush();
    		out.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequiresPermissions("archives:archive:view")
	@RequestMapping(value = "download")
	public ResponseEntity<byte[]> download(Integer userId) throws Exception {  
        HttpHeaders headers = new HttpHeaders();  
        String body = "";
        try {
        	Archives archives = new Archives();
        	archives.setUserId(userId);
    		Archives archivesEntry = archivesService.get(archives);
        	Integer uid = archivesEntry.getUserId();
        	String name = archivesEntry.getName();
        	String fileName = String.format("%s/%d%s.doc", "", uid, name);
        	Integer authType = archivesEntry.getAuthType();
    		Map<String, Object> modelMap = com.google.common.collect.Maps.newHashMap();
        	String templateName = "consultant.ftl"; 
    		Configuration cfg = FreeMarkers.buildConfiguration("classpath:/archives/");
	        if(authType.intValue() == UserConstant.USER_CONSULTANT_TYPE){
	        	Counselor counselor = counselorService.getUserByUserId(archivesEntry.getUserId());
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(uid);
				List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findUnHtmlList(scaleCheckResult);
				modelMap.put("counselor", counselor);
				modelMap.put("scaleCheckResult", scaleCheckResultList);
				templateName = "consultant.ftl";
			}else if(authType.intValue() == UserConstant.USER_FAMILYMEMBERS_TYPE){
				Parents familyMembers = parentsService.getUserByUserId(uid);
				modelMap.put("familyMembers", familyMembers);
				modelMap.put("now", DateUtils.getDateTime());
				templateName = "familyMembers.ftl";
			}else if(authType.intValue() == UserConstant.USER_VISITOR_TYPE){
				VisitorInfo visitorInfo = visitorInfoService.getUserByUserId(uid);
				modelMap.put("visitor", visitorInfo);
				templateName = "visitor.ftl";
			}else {
				Counselor counselor = counselorService.getUserByUserId(uid);
				ScaleCheckResult scaleCheckResult = new ScaleCheckResult();
				scaleCheckResult.setTaskUserId(uid);
				List<ScaleCheckResult> scaleCheckResultList = scaleCheckResultService.findUnHtmlList(scaleCheckResult);
				modelMap.put("counselor", counselor);
				modelMap.put("scaleCheckResult", scaleCheckResultList);
				templateName = "consultant.ftl";
			}
	        modelMap.put("now", DateUtils.getDateTime());
	        modelMap.put("today", DateUtils.getDate());
	        Template template = cfg.getTemplate(templateName);
    		body = FreeMarkers.renderTemplate(template, modelMap);
    		headers.setContentDispositionFormData("attachment", fileName);     
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
    	} catch (IOException e) {
			e.printStackTrace();
		}  
        return new ResponseEntity<byte[]>(body.getBytes("UTF-8"), headers, HttpStatus.CREATED);    
    }  
	
	@RequiresPermissions("archives:archive:view")
	@RequestMapping("generateList")
	public String generateList(Archives archives, String orgInput, Integer orgId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		if(orgInput != null && orgId != null) {
			Office office = new Office();
			office.setId(orgId);
			office.setName(orgInput);
			archives.setOrg(office);
		}
		model.addAttribute("archives", archives);
		Page<Archives> page = archivesService.findPage(new Page<Archives>(request, response), archives);
        List<Map<String, String>> archivesList = new ArrayList<Map<String, String>>();
        for(Archives archivesEntry : page.getList()){
        	Integer uid = archivesEntry.getUserId();
        	String name = archivesEntry.getName();
    		Map<String, String> archivesMap = new HashMap<String, String>();
    		archivesMap.put("fileName", String.format("%d%s.doc", uid, name));
    		archivesMap.put("userId", archivesEntry.getUserId().toString());
    		archivesList.add(archivesMap);
        }
        Page<Map<String, String>> pageList = new Page<Map<String, String>>();
        pageList.setCount(page.getCount());
        pageList.setPageNo(page.getPageNo());
        pageList.setPageSize(page.getPageSize());
        pageList.setList(archivesList);
        model.addAttribute("page", pageList);
		return "modules/archive/archivesGenerateList";
	}

	@RequiresPermissions("archives:archive:view")
	@RequestMapping(value = "listReport")
	public String listReport(Counselor counselorArchive, HttpServletRequest request, HttpServletResponse response,Model model) {
		/*
		if (!beanValidator(model, teacherInfo)){
			return form(teacherInfo, model);
		}
		teacherInfoService.save(teacherInfo);
		addMessage(redirectAttributes, "保存老师档案成功");
		return "redirect:"+Global.getAdminPath()+"/archive/teacherInfo/?repage";
		*/
		//ServletContext
		String realPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("Teachercontrller:"+realPath);
		//"D:\\workspaceo\\xzt\\src\\main\\webapp\\"; 
			//	request.getSession().getServletContext().getRealPath(path);
		Page<Counselor> page = counselorService.listReport(new Page<Counselor>(request, response), counselorArchive,realPath); 
		model.addAttribute("page", page);
		return "modules/archive/counselorListReport";
	}

}