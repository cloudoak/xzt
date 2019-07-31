/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.OrganizationService;
import com.ambition.agile.common.utils.DateUtils;
import com.ambition.agile.common.utils.FormatDate;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.counsel.constant.CounselorConstant;
import com.ambition.agile.modules.counsel.constant.CounselorUserBookConstant;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorSchedule;
import com.ambition.agile.modules.counsel.entity.CounselorUserBook;
import com.ambition.agile.modules.counsel.service.CounselorScheduleService;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.counsel.service.CounselorUserBookService;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 咨询师排班Controller
 * @author OAK
 * @since 2017/12/12
 * @version 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselorSchedule")
public class CounselorScheduleController extends BaseController {

	@Autowired
	private CounselorScheduleService counselorScheduleService;
	
	@Autowired
	private CounselorService  counselorService;
	
	@Autowired
	private CounselorUserBookService counselorUserBookService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@ModelAttribute
	public CounselorSchedule get(@RequestParam(required=false) Integer id) {
		CounselorSchedule entity = null;
		if (id!=null&&id>0){
			entity = counselorScheduleService.get(id);
		}
		if (entity == null){
			entity = new CounselorSchedule();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselorSchedule:view")
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response, Integer days, Model model) {
		
		String nowDate = DateUtils.getDate();
		
		User user = UserUtils.getUser();
		
		int i = 0;
		
		if(days == null || days == 0) {
			days = 7;
		}
		
		Map<String, Object> scheduleMap = new HashMap<String, Object>();
		List<String> headerList = new ArrayList<String>();
		Date beginScheduleDate = null, endScheduleDate = null;
		for( ; i < days.intValue(); i++) {
			Date date = DateUtils.addDays(FormatDate.stringToDate(nowDate + " 00:00:00"), i);
			String header = DateUtils.getWeek(date) + "(" + ((date.getMonth()) + 1) + "." + (date.getDate()) + ")";
			headerList.add(header);
			if(i == 0) {
				beginScheduleDate = date;
			}else if( i == (days.intValue() - 1)) {
				endScheduleDate = date;
			}
		}
		scheduleMap.put("headerList", headerList);
		Counselor counselor = new Counselor();
		counselor.setOrgId(user.getOfficeId());
		counselor.setStatus(CounselorConstant.STATUS_PASSABLE);
		List<Counselor> counselorList = counselorService.findList(counselor);
		CounselorSchedule csle = new CounselorSchedule();
		csle.setBeginScheduleDate(beginScheduleDate);
		csle.setEndScheduleDate(endScheduleDate);
		List<Map<String, Object>> contentBodyList = new ArrayList<Map<String, Object>>();
		for(Counselor clor : counselorList) {
			csle.setCounselorId(clor.getId());
			//csle.setOrgId(clor.getOrg().getId());
			List<CounselorSchedule> counselorScheduleList = counselorScheduleService.findList(csle);
			Map<String, Object> userBookMap = new HashMap<String, Object>();
			List<CounselorUserBook> counselorUserBooks = null;
			for(CounselorSchedule counselorSchedule : counselorScheduleList) {
				Date scheduleDate = counselorSchedule.getScheduleDate();
				String header = DateUtils.getWeek(scheduleDate) + "(" + ((scheduleDate.getMonth()) + 1) + "." + (scheduleDate.getDate()) + ")";
				CounselorUserBook cub = new CounselorUserBook();
				cub.setCounselorId(clor.getId());
				cub.setReserveDate(scheduleDate);
				cub.setBeginTime(counselorSchedule.getBeginTime());
				cub.setEndTime(counselorSchedule.getEndTime());
				List<CounselorUserBook> counselorUserBookList = counselorUserBookService.findList(cub);
				if(!userBookMap.containsKey(header)) {
					counselorUserBooks = new ArrayList<CounselorUserBook>();
					userBookMap.put(header, counselorUserBooks);
				}
				if(counselorUserBookList != null && counselorUserBookList.size() > 0) {
					CounselorUserBook counselorUserBook = counselorUserBookList.get(0);
					counselorUserBooks.add(counselorUserBook);
				}else {
					cub.setDealStatus(CounselorUserBookConstant.IDLE);
					counselorUserBooks.add(cub);
				}
			}
			userBookMap.put("name", clor.getUser().getName());
			contentBodyList.add(userBookMap);
		}
		scheduleMap.put("contentBodyList", contentBodyList);
		model.addAttribute("scheduleMap", scheduleMap);
		model.addAttribute("counselorList", counselorList);
		return "modules/counsel/counselorScheduleList";
	}
	
	@RequiresPermissions("counsel:counselorSchedule:view")
	@RequestMapping(value = "form")
	public String form(CounselorSchedule counselorSchedule, Model model) {
		model.addAttribute("counselorSchedule", counselorSchedule);
		return "modules/counsel/counselorScheduleForm";
	}
	
	@RequiresPermissions("counsel:counselorSchedule:view")
	@RequestMapping(value = "scheduleform")
	public String scheduleform(CounselorUserBook counselorUserBook, Model model) {
		User user = UserUtils.getUser();
		
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ADMIN_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ORG_ADMIN_TYPE) ) {//咨询师
			VisitorInfo visitorInfo = new VisitorInfo();
			visitorInfo.setOrganization(organizationService.getOrganizationJoinString());
			List<VisitorInfo> visitorInfoList = visitorInfoService.findList(visitorInfo);
			model.addAttribute("visitorInfoList", visitorInfoList);
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			
		}
		Counselor counselor = counselorService.get(counselorUserBook.getCounselorId());
		if(counselor != null) {
			counselorUserBook.setCounselorId(counselor.getId());
			counselorUserBook.setCounselor(counselor);
		}
		VisitorInfo visInfo = visitorInfoService.get(counselorUserBook.getVisitorId());
		if(visInfo != null) {
			counselorUserBook.setVisitorId(visInfo.getId());
			counselorUserBook.setVisitorInfo(visInfo);
		}
		
		CounselorUserBook cUserBook = counselorUserBookService.get(counselorUserBook);
		
		if(cUserBook != null) {
			counselorUserBook = cUserBook;
		}
		
		if(counselorUserBook.getIsCase() == null) {
			counselorUserBook.setIsCase(0);
		}
		
		model.addAttribute("counselorUserBook", counselorUserBook);
		
		if(counselorUserBook.getDealStatus().intValue() == CounselorUserBookConstant.NOT_PROCESSED
				|| counselorUserBook.getDealStatus().intValue() == CounselorUserBookConstant.ACCEPT
				|| counselorUserBook.getDealStatus().intValue() == CounselorUserBookConstant.CANCEL) {
			if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE)) {
				return "modules/counsel/counselorUserBookResultForm";
			}else {
				if(counselorUserBook.getDealStatus().intValue() == CounselorUserBookConstant.NOT_PROCESSED) {
					Page<CounselorUserBook> page = counselorUserBookService.findPage(new Page<CounselorUserBook>(request, response), counselorUserBook); 
					if(page.getList() != null && page.getList().size() > 0 
							&& page.getList().get(0).getIsOwner().intValue() == CounselorUserBookConstant.COUNSELOR_SUBMIT) {
						return "modules/counsel/counselorUserBookResultForm";
					}else {
						model.addAttribute("page", page);
						model.addAttribute("showTitle", true);
						model.addAttribute("counselorUserBook", counselorUserBook);
						return "modules/counsel/counselorUserBookList";
					}
				}else {
					model.addAttribute("showTitle", true);
					return "modules/counsel/counselorUserBookResultForm";
				}
			}
		}
		
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE)) {//咨询师
			return "modules/counsel/counselorBookForm";
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			return "modules/counsel/counselorUserBookForm";
		}
		
		return "modules/counsel/counselorBookForm";
	}
	
	
	@RequiresPermissions("counsel:counselorSchedule:view")
	@RequestMapping(value = "manual")
	public String manual(CounselorUserBook counselorUserBook, Model model) {
		User user = UserUtils.getUser();
		
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ADMIN_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ORG_ADMIN_TYPE) ) {//咨询师
			VisitorInfo visitorInfo = new VisitorInfo();
			visitorInfo.setOrganization(organizationService.getOrganizationJoinString());
			List<VisitorInfo> visitorInfoList = visitorInfoService.findList(visitorInfo);
			model.addAttribute("visitorInfoList", visitorInfoList);
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			
		}
		Counselor counselor = counselorService.get(counselorUserBook.getCounselorId());
		if(counselor != null) {
			counselorUserBook.setCounselorId(counselor.getId());
			counselorUserBook.setCounselor(counselor);
		}
		VisitorInfo visInfo = visitorInfoService.get(counselorUserBook.getVisitorId());
		if(visInfo != null) {
			counselorUserBook.setVisitorId(visInfo.getId());
			counselorUserBook.setVisitorInfo(visInfo);
		}
		
		CounselorUserBook cUserBook = counselorUserBookService.get(counselorUserBook);
		
		if(cUserBook != null) {
			counselorUserBook = cUserBook;
		}
		
		if(counselorUserBook.getIsCase() == null) {
			counselorUserBook.setIsCase(0);
		}
		
		model.addAttribute("counselorUserBook", counselorUserBook);
		
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE)) {//咨询师
			return "modules/counsel/counselorBookForm";
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			return "modules/counsel/counselorUserBookForm";
		}
		
		return "modules/counsel/counselorBookForm";
	}

	@RequiresPermissions("counsel:counselorSchedule:edit")
	@RequestMapping(value = "save")
	public String save(CounselorSchedule counselorSchedule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselorSchedule)){
			return form(counselorSchedule, model);
		}
		User user = UserUtils.getUser();
		if(user.getOffice() != null && user.getOffice().getId() != null) {
			counselorSchedule.setOrgId(user.getOffice().getId());
		}
		counselorScheduleService.save(counselorSchedule);
		addMessage(redirectAttributes, "保存咨询师排班成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselorSchedule/list";
	}
	
	@RequestMapping("checkExists")
	@ResponseBody
	public int checkExists(Integer counselorId, Date scheduleDate, Time beginTime, Time endTime){
		CounselorSchedule counselorSchedule = new CounselorSchedule();
		counselorSchedule.setCounselorId(counselorId);
		counselorSchedule.setScheduleDate(scheduleDate);
		counselorSchedule.setBeginTime(beginTime);
		counselorSchedule.setEndTime(endTime);
		List<CounselorSchedule> cScheduleList = counselorScheduleService.findCompareTime(counselorSchedule);
		if(cScheduleList != null && cScheduleList.size() > 0) {
			return 0;
		}
		return 1;
	}
	
	@RequiresPermissions("counsel:counselorSchedule:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselorSchedule counselorSchedule, RedirectAttributes redirectAttributes) {
		counselorSchedule = counselorScheduleService.get(counselorSchedule);
		counselorScheduleService.delete(counselorSchedule);
		addMessage(redirectAttributes, "删除咨询师排班成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselorSchedule/list";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new TimeEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	public class TimeEditor extends PropertyEditorSupport {

		public void setAsText(String text) throws IllegalArgumentException {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			try {
				setValue(new java.sql.Time(sdf.parse(text).getTime()));
			} catch (ParseException e) {
			}
		}

		public String getAsText() {
			java.sql.Time value = (java.sql.Time) getValue();
			return (value != null ? value.getHours() + ":" + value.getMinutes() + ":" + value.getSeconds() : "");
		}

	}

}