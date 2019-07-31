/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.counsel.constant.CounselorUserBookConstant;
import com.ambition.agile.modules.counsel.entity.CounselorUserBook;
import com.ambition.agile.modules.counsel.service.CounselorUserBookService;
import com.ambition.agile.modules.counsel.web.CounselorScheduleController.TimeEditor;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 咨询预约Controller
 * @author OAK
 * @version 2017/12/17
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselorUserBook")
public class CounselorUserBookController extends BaseController {

	@Autowired
	private CounselorUserBookService counselorUserBookService;
	
	@ModelAttribute
	public CounselorUserBook get(@RequestParam(required=false) Integer id) {
		CounselorUserBook entity = null;
		if (id!=null&&id>0){
			entity = counselorUserBookService.get(id);
		}
		if (entity == null){
			entity = new CounselorUserBook();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselorUserBook:view")
	@RequestMapping("list")
	public String list(CounselorUserBook counselorUserBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Date currentDate = new Date();
		counselorUserBook.setReserveDate(currentDate);
		counselorUserBook.setCurrentTime(new Time(currentDate.getTime()));
		Page<CounselorUserBook> page = counselorUserBookService.findPage(new Page<CounselorUserBook>(request, response), counselorUserBook); 
		model.addAttribute("page", page);
		return "modules/counsel/counselorUserBookList";
	}
	
	/**
	 * @param counselorUserBook
	 * @param request
	 * @param response
	 * @param model
	 * @see 查询取消的预约的咨询
	 * @return
	 */
	@RequiresPermissions("counsel:counselorUserBook:view")
	@RequestMapping("listCancel")
	public String listCancel(CounselorUserBook counselorUserBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		counselorUserBook.setDealStatus(CounselorUserBookConstant.ACCEPT);
		Date currentDate = new Date();
		counselorUserBook.setReserveDate(currentDate);
		counselorUserBook.setCurrentTime(new Time(currentDate.getTime()));
		Page<CounselorUserBook> page = counselorUserBookService.findPage(new Page<CounselorUserBook>(request, response), counselorUserBook); 
		model.addAttribute("page", page);
		return "modules/counsel/counselorUserBookListCancel";
	}

	@RequiresPermissions("counsel:counselorUserBook:view")
	@RequestMapping(value = "form")
	public String form(CounselorUserBook counselorUserBook, Model model) {
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "modules/counsel/counselorUserBookForm";
	}

	@RequiresPermissions("counsel:counselorUserBook:edit")
	@RequestMapping(value = "save")
	public String save(CounselorUserBook counselorUserBook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselorUserBook)){
			return form(counselorUserBook, model);
		}
		User user = UserUtils.getUser();
		
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE) 	
				|| user.getUserType().equals(UserConstant.USER_ADMIN_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ORG_ADMIN_TYPE) ) {//咨询师
			counselorUserBook.setIsOwner(CounselorUserBookConstant.COUNSELOR_SUBMIT);
			counselorUserBook.setDealStatus(CounselorUserBookConstant.ACCEPT);
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			counselorUserBook.setIsOwner(CounselorUserBookConstant.VISITOR_SUBMIT);
			counselorUserBook.setDealStatus(CounselorUserBookConstant.NOT_PROCESSED);
		}
		counselorUserBookService.save(counselorUserBook);
		addMessage(redirectAttributes, "保存咨询预约成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselorSchedule/list";
	}
	
	@RequiresPermissions("counsel:counselorUserBook:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselorUserBook counselorUserBook, RedirectAttributes redirectAttributes) {
		counselorUserBookService.delete(counselorUserBook);
		addMessage(redirectAttributes, "删除咨询预约成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselorSchedule/list";
	}
	
	/**
	 * @param counselorUserBook
	 * @param request
	 * @param response
	 * @param model
	 * @see 查询取消的预约的咨询
	 * @return
	 */
	@RequiresPermissions("counsel:counselorUserBook:edit")
	@RequestMapping("detailView")
	public String detailView(CounselorUserBook counselorUserBook, Integer counselorUserBookId,
			Boolean showTitle, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(counselorUserBookId != null && counselorUserBookId > 0) {
			counselorUserBook.setId(counselorUserBookId);
		}
		counselorUserBook = counselorUserBookService.get(counselorUserBook);
		model.addAttribute("showTitle", showTitle);
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "modules/counsel/counselorUserBookResultForm";
	}
	
	@RequiresPermissions("counsel:counselorUserBook:edit")
	@RequestMapping("dealWith")
	public String dealWith(Integer counselorUserBookId, Boolean showTitle,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		CounselorUserBook counselorUserBook = null;
		if(counselorUserBookId != null && counselorUserBookId > 0) {
			counselorUserBook = new CounselorUserBook();
			counselorUserBook.setId(counselorUserBookId);
			counselorUserBook = counselorUserBookService.get(counselorUserBook);
		}
		model.addAttribute("showTitle", showTitle);
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "modules/counsel/counselorDealWithForm";
	}
	
	@RequiresPermissions("counsel:counselorUserBook:edit")
	@RequestMapping("dealView")
	public String dealView(Integer counselorUserBookId, Boolean showTitle,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		CounselorUserBook counselorUserBook = null;
		if(counselorUserBookId != null && counselorUserBookId > 0) {
			counselorUserBook = new CounselorUserBook();
			counselorUserBook.setId(counselorUserBookId);
			counselorUserBook = counselorUserBookService.get(counselorUserBook);
		}
		model.addAttribute("showTitle", showTitle);
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "modules/counsel/counselorDealWithView";
	}
	
	@RequiresPermissions("counsel:counselorUserBook:view")
	@RequestMapping("dealWithAfterList")
	public String dealWithAfterList(CounselorUserBook counselorUserBook, Integer counselorUserBookId, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(counselorUserBookId != null && counselorUserBookId > 0) {
			counselorUserBook.setId(counselorUserBookId);
		}
		if(user.getUserType().equals(UserConstant.USER_CONSULTANT_TYPE) 	
				|| user.getUserType().equals(UserConstant.USER_ADMIN_TYPE) 
				|| user.getUserType().equals(UserConstant.USER_ORG_ADMIN_TYPE) ) {//咨询师
			counselorUserBook.setIsOwner(CounselorUserBookConstant.COUNSELOR_SUBMIT);
		}else if(user.getUserType().equals(UserConstant.USER_VISITOR_TYPE))  {//来访者
			counselorUserBook.setIsOwner(CounselorUserBookConstant.VISITOR_SUBMIT);
		}
		counselorUserBookService.save(counselorUserBook);
		Page<CounselorUserBook> page = counselorUserBookService.findPage(new Page<CounselorUserBook>(request, response), counselorUserBook); 
		model.addAttribute("page", page);
		model.addAttribute("showTitle", true);
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "modules/counsel/counselorUserBookList";
	}

	/**
	 * @param counselorUserBook
	 * @param request
	 * @param response
	 * @param model
	 * @see 查询取消的预约的咨询
	 * @return
	 */
	@RequiresPermissions("counsel:counselorUserBook:view")
	@RequestMapping("cancelView")
	public String cancelView(CounselorUserBook counselorUserBook, Integer counselorUserBookId, HttpServletRequest request, HttpServletResponse response, Model model) {
		String undoReason = counselorUserBook.getUndoReason();
		counselorUserBook.setId(counselorUserBookId);
		counselorUserBook = counselorUserBookService.get(counselorUserBook);
		if(counselorUserBook != null) {
			counselorUserBook.setUndoReason(undoReason);
			counselorUserBook.setDealStatus(CounselorUserBookConstant.CANCEL);
			counselorUserBookService.save(counselorUserBook);
		}
		model.addAttribute("counselorUserBook", counselorUserBook);
		return "redirect:"+Global.getAdminPath()+"/counsel/counselorUserBook/listCancel";
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