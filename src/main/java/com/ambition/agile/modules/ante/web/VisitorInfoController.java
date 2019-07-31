/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.utils.DateUtils;
import com.ambition.agile.common.utils.excel.ExportExcel;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.constant.VisitorConstant;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.entity.VisitorInfoDto;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.ExcelWriteUtils;
import com.ambition.agile.modules.utils.FileUtil;
import com.ambition.agile.modules.utils.JSONSerException;
import com.ambition.agile.modules.utils.JSONUtil;
import com.ambition.agile.modules.utils.StringFunctionUtils;
import com.google.common.collect.Lists;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 来访者Controller
 *
 * @author dortan
 * @version 2017-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/visitorInfo")
public class VisitorInfoController extends BaseController {

    @Autowired
    private VisitorInfoService visitorInfoService;

    @Autowired
    private SystemService systemService;
    
    @Autowired
    private OfficeService officeService;
    
    @Autowired
	public OrganizationService organizationService;

    @ModelAttribute
    public VisitorInfo get(@RequestParam(required = false) Integer id) {
        VisitorInfo entity = null;
        if (id != null && id > 0) {
            entity = visitorInfoService.get(id);
        }
        if (entity == null) {
            entity = new VisitorInfo();
        }
        return entity;
    }

    /**
     * 查询
     * @param visitorInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = {"list", ""})
    public String list(VisitorInfo visitorInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
    	Office office = new Office();
    	office.setOrganization(organizationService.getOrganizationJoinString());
    	visitorInfo.setOrganization(organizationService.getOrganizationJoinString());
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), office); 
		model.addAttribute("offices", offices);
        Page<VisitorInfo> page = visitorInfoService.findPage(new Page<VisitorInfo>(request, response), visitorInfo);
        model.addAttribute("page", page);
        return "modules/ante/visitorInfoList";
    }
    
    /**
     * 积分查询
     * @param visitorInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = {"scoreList", ""})
    public String scoreList(VisitorInfo visitorInfo, boolean scorePlus, HttpServletRequest request, HttpServletResponse response, Model model) {
    	User user = UserUtils.getUser();
		Integer id = 0;
		if(user.getOffice() != null && user.getOffice().getId() != null) {
			id = user.getOffice().getId();
		}
		Office office = officeService.get(id);
		Integer parentId = 0;
		if(office != null) {
			parentId = office.getParentId();
		}
		model.addAttribute("parentId", parentId);
    	
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
    	model.addAttribute("offices", offices);
    	
//    	if(scorePlus==true) {//积分增加
//    		List<VisitorInfo> list = visitorInfoService.findList(visitorInfo);
//    		for (VisitorInfo vi : list) {
//    			User user = systemService.getUser(vi.getUserId());
//    			if (user != null && user.getId() != null && user.getScore() != null) {
//    				user.setScore(user.getScore()+1);//积分
//       			 	systemService.saveUser(user);
//    			}
//    		}
//    	}
    	Page<VisitorInfo> page = new Page();
//    	List<VisitorInfo> list = new ArrayList();
//		Page<VisitorInfo> pageAll = visitorInfoService.findPage(new Page<VisitorInfo>(request, response), visitorInfo);
//		List<VisitorInfo> listAll = pageAll.getList();
//		for (VisitorInfo vi : listAll) {
//			User user = vi.getUser();
//			if (user != null && user.getId() != null && user.getScore() != null) {
//				list.add(vi);
//			}
//		}
		List<VisitorInfo> list = visitorInfoService.getVisitorInfoScoreList(visitorInfo);
		page.setList(list);
		model.addAttribute("page", page);
    	
		return "modules/ante/visitorInfoScoreList";
    }
    
    /**
     * 积分增加
     * @param visitorInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = {"scorePlus", ""})
    public String scorePlus(VisitorInfo visitorInfo,  HttpServletRequest request, HttpServletResponse response, Model model) {
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
    	model.addAttribute("offices", offices);
    	
		List<VisitorInfo> list = visitorInfoService.getVisitorInfoScoreList(visitorInfo);
		for (VisitorInfo vi : list) {
			User user = systemService.getUser(vi.getUserId());
			if (user != null && user.getId() != null && user.getScore() != null) {
				user.setScore(user.getScore()+visitorInfo.getUser().getScore());//积分
   			 	systemService.saveUser(user);
			}
		}
		
    	Page<VisitorInfo> page = new Page();
		List<VisitorInfo> scoreList = visitorInfoService.getVisitorInfoScoreList(visitorInfo);
		page.setList(scoreList);
		model.addAttribute("page", page);
    	
		return "modules/ante/visitorInfoScoreList";
    }
    
    /**
     * 积分设置
     * @param visitorInfo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "scoreSave")
    public String scoreSave(VisitorInfo visitorInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, visitorInfo)) {
            return form(visitorInfo, model);
        }
        List<VisitorInfo> visitorInfoList = visitorInfoService.findList(visitorInfo);//通过编号查询记录
	   	if(visitorInfoList.size()>0) {
	   		 User user = systemService.getUser(visitorInfoList.get(0).getUserId());
	   		 if (user != null && user.getId() != null) {
	   			 user.setScore(visitorInfo.getUser().getScore());//积分
	   			 systemService.saveUser(user);
	   			 
	   			 visitorInfoService.save(visitorInfo);
	   			 addMessage(redirectAttributes, "来访者积分设置成功");
	   		 }else {
	   			 addMessage(redirectAttributes, "来访者积分设置失败");
	   		 }
	   	 }else {
	   		 addMessage(redirectAttributes, "来访者编号不存在！");
	   	 }
   	 return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/scoreList";
    }

    /**
     * 来访者form
     * @param visitorInfo
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = "form")
    public String form(VisitorInfo visitorInfo, Model model) {
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
        model.addAttribute("visitorInfo", visitorInfo);
        return "modules/ante/visitorInfoForm";
    }
    
    /**
     * 来访者积分form
     * @param visitorInfo
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = "scoreForm")
    public String scoreForm(VisitorInfo visitorInfo, Model model) {
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
        model.addAttribute("visitorInfo", visitorInfo);
        return "modules/ante/visitorInfoScoreForm";
    }

    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "go/improt")
    public String goImport(VisitorInfo visitorInfo, Model model, RedirectAttributes redirectAttributes) {
    	Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
		model.addAttribute("orgId", parentId);
        return "modules/ante/visitorInfoImportForm";
    }
    
    /**
     * 查看
     * @param visitorInfo
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:view")
    @RequestMapping(value = "view")
    public String view(VisitorInfo visitorInfo, Model model) {
    	Page<Office> offices = officeService.findPage(new Page<Office>(request, response), new Office()); 
		model.addAttribute("offices", offices);
		
        model.addAttribute("visitorInfo", visitorInfo);
        return "modules/ante/visitorInfoView";
    }

    /**
     * 导入
     * @param wirteExcel
     * @param visitorInfo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "improt")
    public String importSave(@RequestParam("wirteExcel") MultipartFile wirteExcel, Integer orgId, Model model) {
        ExcelWriteUtils excel = new ExcelWriteUtils();
        User currentUser = UserUtils.getUser();
        boolean success = false;
        try {
        	List<List<Map<String, String>>> wBooks = excel.readExcelWithoutTitle(wirteExcel.getInputStream(),
        			wirteExcel.getOriginalFilename(), 3, 0);
            List<VisitorInfo> visitorInfoList = visitorInfoService.listImpExcelVisitor(wBooks);
            for (VisitorInfo visInfo : visitorInfoList) {
            	visInfo.setOrgId(orgId);
            	User user = visInfo.getUser();
    			user.setStatus(UserConstant.STATUS_PASSABLE);//审核状态：0-待审核;1-审核通过;2-审核未通过;
    			user.setLoginIp(request.getLocalAddr());
    			// 角色数据有效性验证，过滤不在授权内的角色
    			List<Role> roleList = Lists.newArrayList();
    			List<Integer> roleIdList = user.getRoleIdList();
    			for (Role r : systemService.findAllRole()){
    				if (roleIdList.contains(r.getId())){
    					roleList.add(r);
    				}
    			}
				user.setIsAdmin(3);
				user.setUserType(UserConstant.USER_VISITOR_TYPE);
				Role role = systemService.getRoleByName("来访者");
				user.setRole(role);
				roleList.add(role);
				user.setRoleList(roleList);
    			
    			Office office = officeService.get(orgId);
    			if(office != null) {
    				user.setCompany(office);
    				user.setOffice(office);
    				user.setOfficeId(office.getId());
    			}
    			user.setCreateBy(currentUser);
    			user.setCreateDate(new Date());
    			user.setUpdateBy(currentUser);
    			user.setUpdateDate(new Date());
    			// 保存用户信息
    			systemService.saveUser(user);
    			Integer id = user.getId();
    			User u = systemService.getUserById(id);
				if(u != null) {
					visInfo.setUser(u);
					visInfo.setUserId(u.getId());
					visInfo.setPassword(u.getPassword());
					visInfo.setOrgId(u.getOfficeId());
				}
				visInfo.setStatus(VisitorConstant.STATUS_PASSABLE);
				visitorInfoService.save(visInfo);//来访者
            }
            success = true;
        } catch (Exception e) {
        	addMessage(model, "来访者资料导入失败，原因：" + e.getMessage());
            e.printStackTrace();
            success = false;
        }
        if(success) {
        	addMessage(model, "来访者资料导入成功！");
        }
        model.addAttribute("success", success);
        model.addAttribute("orgId", orgId);
    	return "modules/ante/visitorInfoImportForm";
    }
    
    /**
     * 模板下载
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "download")
    public static String downloadFile(String fileName,  HttpServletRequest request, HttpServletResponse response){
    	try {
    		String path = request.getSession().getServletContext().getRealPath("\\WEB-INF\\views\\modules\\ante\\template\\");
			FileUtil.downloadFile(response, path, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/ante/visitorInfoImportForm";
	}

    /**
     * 新增
     * @param visitorInfo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "save")
    public String save(VisitorInfo visitorInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, visitorInfo)) {
            return form(visitorInfo, model);
        }
        
        Office office = new Office();
        office.setId(visitorInfo.getOrgId());
        
		List<Role> roleList=new ArrayList<Role>();
		roleList.add(new Role(4));//设置用户角色 1系统管理员 2机构管理员 3咨询师 4来访者 5家属
    	
    	User user = new User();
    	user.setCompany(office);
    	user.setOffice(office);
    	user.setUserType(4);//设置用户类型  0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
    	user.setIsAdmin(3);//设置是否是管理员身份  1机构默认管理员 2平台管理员 3非管理员
    	user.setRoleList(roleList);
    	user.setName(visitorInfo.getUser().getName());
    	user.setLoginName(visitorInfo.getVisitorNo());
    	user.setPassword(SystemService.entryptPassword(visitorInfo.getPassword()));
    	user.setStatus(0);//审核状态：0-待审核;1-审核通过;2-审核未通过;
    	
    	visitorInfo.setStatus(0);//审核状态：0-待审核;1-审核通过;2-审核未通过;
    	try {
    		systemService.saveUser(user);
    		visitorInfo.setUserId(systemService.findMaxId());
    		visitorInfoService.save(visitorInfo);
		} catch (Exception e) {
			model.addAttribute("message", "来访者编号已存在!");
			return "modules/ante/visitorInfoForm";
		}
    	
    	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/list";
    }
    
    /**
	 * 审核(多条)
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:visitorInfo:edit")
	@RequestMapping(value = "reviewAll")
	public String reviewAll(String ids, RedirectAttributes redirectAttributes) {
		ids = StringFunctionUtils.replaceWebJsp(ids);
		String[] split = ids.substring(1, ids.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			VisitorInfo visitorInfo = visitorInfoService.get(id);
			visitorInfo.setStatus(1);//审核状态：0-待审核;1-审核通过;2-审核未通过;
			visitorInfoService.save(visitorInfo);
			
			User user = new User();
			user.setId(visitorInfo.getUserId());
			systemService.updateStatus(user, 1);
		}
	    addMessage(redirectAttributes, "审核成功");
		return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/?repage";
	}
    
    /**
     * 批量开通初始化页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "toBatch")
    public String toBatch(HttpServletRequest request, HttpServletResponse response, Model model) {
    	Integer parentId = UserUtils.getOrg().getId();
		model.addAttribute("rootParentId", parentId);
    	return "modules/ante/visitorInfoBatchForm";
    }
    
    /**
	 * 导出来访者账户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.GET)
    public String exportFile(VisitorInfo visitorInfo, Integer passwordType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {	
			String visitorNo = "";
			if(visitorInfo.getVisitorNo() != null) {
				visitorNo = visitorInfo.getVisitorNo().replace("*", "");
			}
	        Integer startNo = visitorInfo.getStartNo();//范围start
	        Integer endNo = visitorInfo.getEndNo();//范围end
	        Integer starLen = visitorInfo.getStarLen();//通配符长度
	        String prefix = "00000";
			//规则：譬如生成6001、6002、6003、 ...、6010；编号填 60*；范围 1-10；通配符长度为2,因为是01至10
	        List<Map<String, String>> visitorList = new ArrayList<Map<String, String>>();
	        for(int i = 0; i <= endNo - startNo; i++) {
	        	Map<String, String> visitorMap = new HashMap<String, String>();
	        	String currentNo = "" + (startNo + i);
	        	if(starLen < currentNo.length()) {
	        		currentNo = currentNo.substring(0, starLen);
	        	}else {
	        		currentNo = prefix.substring(0, starLen - currentNo.length()) + currentNo;
	        	}
	        	visitorMap.put("no", visitorNo + currentNo);
	        	if(passwordType == 0) {
	        		visitorMap.put("password", visitorMap.get("no"));
	        	}else {
	        		visitorMap.put("password", visitorInfo.getPassword());
	        	}
	        	User user = systemService.getUserByLoginName(visitorMap.get("no"));
	        	if(user != null) {
	        		continue;
	        	}
	        	visitorList.add(visitorMap);
	        }
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("来访者账户数据");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//设置头部信息
			String[] excelHeader = { "编号","密码"};
			for(int i=0;i<excelHeader.length;i++){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
			}
			if(visitorList.size() > 0){
				for(int i = 0;i < visitorList.size();i++){
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(visitorList.get(i).get("no") == null ? "" : visitorList.get(i).get("no"));
					row.createCell(1).setCellValue(visitorList.get(i).get("password") == null ? "" : visitorList.get(i).get("password"));
				}
			}
			String fileNameTmp = "来访者账户数据"+ DateUtils.getDate("yyyyMMddHHmmss");
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
			addMessage(redirectAttributes, "导出来访者账户数据失败！失败信息：" + e.getMessage());
		}
       return "modules/ante/visitorInfoBatchForm";
    }
    
    //@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "toBatchSaveVisitor")
	@ResponseBody
	public String toBatchSaveVisitor(VisitorInfo visitorInfo, Integer passwordType, Model model, RedirectAttributes redirectAttributes) 
			throws JSONSerException {
		response.setContentType("text/json");
		List<Map<String, String>> visitorList = new ArrayList<Map<String, String>>();
		try {
			String visitorNo = "";
			if(visitorInfo.getVisitorNo() != null) {
				visitorNo = visitorInfo.getVisitorNo().replace("*", "");
			}
	        Integer startNo = visitorInfo.getStartNo();//范围start
	        Integer endNo = visitorInfo.getEndNo();//范围end
	        Integer starLen = visitorInfo.getStarLen();//通配符长度
	        String prefix = "00000";
			//规则：譬如生成6001、6002、6003、 ...、6010；编号填 60*；范围 1-10；通配符长度为2,因为是01至10
	        for(int i = 0; i <= endNo - startNo; i++) {
	        	Map<String, String> visitorMap = new HashMap<String, String>();
	        	String currentNo = "" + (startNo + i);
	        	if(starLen < currentNo.length()) {
	        		currentNo = currentNo.substring(0, starLen);
	        	}else {
	        		currentNo = prefix.substring(0, starLen - currentNo.length()) + currentNo;
	        	}
	        	visitorMap.put("no", visitorNo + currentNo);
	        	if(passwordType == 0) {
	        		visitorMap.put("password", visitorMap.get("no"));
	        	}else {
	        		visitorMap.put("password", visitorInfo.getPassword());
	        	}
	        	User user = systemService.getUserByLoginName(visitorMap.get("no"));
	        	visitorMap.put("prompt", user != null ? String.format("不可以使用，用户名%s已经存在", visitorMap.get("no")) : "可以使用");
	        	visitorList.add(visitorMap);
	        }
		} catch (Exception e) {
			addMessage(redirectAttributes, "批量开通失败，原因: 必填数据没有填写！");
			model.addAttribute("message", "批量开通失败，原因: 必填数据没有填写！");
		}
		Map message = new HashMap();
		message.put("success", visitorList.size() > 0 ? true : false);
		message.put("data", visitorList);
		return JSONUtil.toJSON(message);
	}
    
    /**
     * 批量开通提交
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "toBatchSave")
    public String toBatchSave(VisitorInfo visitorInfo, Integer passwordType, Model model, RedirectAttributes redirectAttributes) {
    	String visitorNo = "";
		if(visitorInfo.getVisitorNo() != null) {
			visitorNo = visitorInfo.getVisitorNo().replace("*", "");
		}
        Integer startNo = visitorInfo.getStartNo();//范围start
        Integer endNo = visitorInfo.getEndNo();//范围end
        Integer starLen = visitorInfo.getStarLen();//通配符长度
        String prefix = "00000";
		//规则：譬如生成6001、6002、6003、 ...、6010；编号填 60*；范围 1-10；通配符长度为2,因为是01至10
        StringBuilder messages = new StringBuilder();
        for(int i = 0; i <= endNo - startNo; i++) {
        	String currentNo = "" + (startNo + i);
        	if(starLen < currentNo.length()) {
        		currentNo = currentNo.substring(0, starLen);
        	}else {
        		currentNo = prefix.substring(0, starLen - currentNo.length()) + currentNo;
        	}
        	User u = systemService.getUserByLoginName(visitorNo + currentNo);
        	if(u != null) {
        		messages.append((u != null ? String.format("不可以使用，用户名%s已经存在", visitorNo + currentNo) : String.format("可以使用，用户名%s", visitorNo + currentNo)) + "<br />");
        		continue;
        	}
        	String name = visitorNo + currentNo;
        	User user = new User();
        	user.setName(name);
			user.setLoginName(name);
			user.setStatus(UserConstant.STATUS_PASSABLE);//审核状态：0-待审核;1-审核通过;2-审核未通过;
			user.setLoginIp(request.getLocalAddr());
			// 角色数据有效性验证，过滤不在授权内的角色
			List<Role> roleList = Lists.newArrayList();
			List<Integer> roleIdList = user.getRoleIdList();
			for (Role r : systemService.findAllRole()){
				if (roleIdList.contains(r.getId())){
					roleList.add(r);
				}
			}
        	user.setIsAdmin(3);
			user.setUserType(UserConstant.USER_VISITOR_TYPE);
			Role role = systemService.getRoleByName("来访者");
			user.setRole(role);
			roleList.add(role);
			user.setRoleList(roleList);
			Office office = officeService.get(visitorInfo.getOrgId());
			if(office != null) {
				user.setCompany(office);
				user.setOffice(office);
				user.setOfficeId(office.getId());
			}
			//是否更新密码
        	if(passwordType == 0) {
        		user.setPassword(SystemService.entryptPassword(visitorNo + currentNo));
        	}else {
        		user.setPassword(SystemService.entryptPassword(visitorInfo.getPassword()));
        	}
        	user.setStatus(UserConstant.STATUS_PASSABLE);//审核状态：0-待审核;1-审核通过;2-审核未通过;
        	try {
        		systemService.saveUser(user);
        		Integer id = user.getId();
        		User currentUser = systemService.getUserById(id);
				visitorInfo.setSex(0);
				visitorInfo.setNation(0);
				visitorInfo.setVisitorNo(name);
	        	visitorInfo.setStatus(VisitorConstant.STATUS_PASSABLE);//审核状态：0-待审核;1-审核通过;2-审核未通过;
				if(currentUser != null) {
					visitorInfo.setUser(currentUser);
					visitorInfo.setUserId(currentUser.getId());
					visitorInfo.setPassword(currentUser.getPassword());
					visitorInfo.setOrgId(currentUser.getOfficeId());
				}
				visitorInfoService.save(visitorInfo);//来访者
			} catch (Exception e) {
				addMessage(redirectAttributes, "批量开通失败，原因:" + messages.toString());
		        model.addAttribute("message", "批量开通失败，原因:" + messages.toString());
				return "modules/ante/visitorInfoBatchForm";
			}
        }
        addMessage(redirectAttributes, "批量开通成功:" + messages.toString());
        model.addAttribute("message", "批量开通成功:" + messages.toString());
        return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/toBatch";
    }

    /**
     * 删除一条
     * @param visitorInfo
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(VisitorInfo visitorInfo, boolean reviewSign, RedirectAttributes redirectAttributes) {
        visitorInfoService.delete(visitorInfo);
        addMessage(redirectAttributes, "删除成功！");
        
        if(reviewSign==true) {
        	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/reviewList";
        }else {
        	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/list";
        }
    }
    
    /**
	 * 删除多条
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:visitorInfo:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, boolean reviewSign, RedirectAttributes redirectAttributes) {
		ids = StringFunctionUtils.replaceWebJsp(ids);
		String[] split = ids.substring(1, ids.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			VisitorInfo visitorInfo = new VisitorInfo();
			visitorInfo.setId(id);
			visitorInfoService.delete(visitorInfo);
		}
		addMessage(redirectAttributes, "删除来访者"+ids+"成功");
		if(reviewSign==true) {
        	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/reviewList";
        }else {
        	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/list";
        }
	}
    
    /**
     * 来访者自主审核初始化页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "reviewList")
    public String reviewList(VisitorInfo visitorInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
    	  visitorInfo.setStatus(VisitorConstant.STATUS_AUDIT);
    	  visitorInfo.setOrganization(organizationService.getOrganizationJoinString());
    	  Page<VisitorInfo> page = visitorInfoService.findPage(new Page<VisitorInfo>(request, response), visitorInfo);
          model.addAttribute("page", page);
    	return "modules/ante/visitorInfoReviewList";
    }
    
    /**
     * 来访者自主审核
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("ante:visitorInfo:edit")
    @RequestMapping(value = "reviewListSave")
    public String reviewListSave(VisitorInfo visitorInfo, Model model, RedirectAttributes redirectAttributes) {
    	if (!beanValidator(model, visitorInfo)) {
    		return form(visitorInfo, model);
    	}
    	visitorInfo.setStatus(1);//审核状态：0-待审核;1-审核通过;2-审核未通过;
    	visitorInfo.setIsNewRecord(false);
    	visitorInfoService.save(visitorInfo);
    	User user = new User();
		user.setId(visitorInfo.getUserId());
		systemService.updateStatus(user, 1);
    	addMessage(redirectAttributes, "审核成功！");
    	return "redirect:" + Global.getAdminPath() + "/ante/visitorInfo/reviewList";
    }
    
    @RequiresPermissions("ante:visitorInfo:edit")
	@RequestMapping(value = "exportFile")
	public String exportFile(VisitorInfo visitorInfo, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
    	int orgId = UserUtils.getUser().getOffice().getId();//机构号
  	  	visitorInfo.setOrgId(orgId);
    	try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("来访者信息");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			//设置头部信息
			String[] excelHeader = { "学号","姓名", "性别", "所属机构","年龄","最后登录时间"};
			for(int i=0;i<excelHeader.length;i++){
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
			}
//			Page<VisitorInfo> page = visitorInfoService.findPage(new Page<VisitorInfo>(request, response), visitorInfo);
//			page.setPageSize(100);
//			page.setPageNo(1);
//			List<VisitorInfo> exportList = page.getList();
			List<VisitorInfo> exportList = visitorInfoService.findList(visitorInfo);
			List<Office> offices = officeService.findAll();
			if(exportList!=null){
				for(int i = 0;i < exportList.size();i++){
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(exportList.get(i).getVisitorNo() == null ? "" : exportList.get(i).getVisitorNo().toString());
					row.createCell(1).setCellValue(exportList.get(i).getUser().getName() == null ? "" : exportList.get(i).getUser().getName().toString());
					if(exportList.get(i).getSex() != null) {
						row.createCell(2).setCellValue(exportList.get(i).getSex() == 1 ? "男" : "女");
					}else {
						row.createCell(2).setCellValue("");
					}
					for(Office office : offices) {
						if(office.getId().equals(exportList.get(i).getOrgId())) {
							row.createCell(3).setCellValue(office.getName());
						}
					}
					row.createCell(4).setCellValue(exportList.get(i).getAge() == null ? "" : exportList.get(i).getAge().toString());
					row.createCell(5).setCellValue(exportList.get(i).getUser() == null || exportList.get(i).getUser().getLoginDate() == null? "" : exportList.get(i).getUser().getLoginDate().toString());
					row.createCell(6).setCellValue(exportList.get(i).getUser() == null || exportList.get(i).getUser().getLoginDate() == null ? "" : exportList.get(i).getUser().getLoginDate().toString());
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
			addMessage(redirectAttributes, "导出任务失败！失败信息："+e.getMessage());
		}
		return "modules/ante/visitorInfoList";
	}

}