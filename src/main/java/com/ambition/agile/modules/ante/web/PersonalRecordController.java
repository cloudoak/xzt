/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.PersonalRecord;
import com.ambition.agile.modules.ante.service.PersonalRecordService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.StringFunctionUtils;

/**
 * 个人成长记录Controller
 * @author whq
 * @version 2017-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/personalRecord")
public class PersonalRecordController extends BaseController {

	@Autowired
	private PersonalRecordService personalRecordService;
	
	@ModelAttribute
	public PersonalRecord get(@RequestParam(required=false) Integer id) {
		PersonalRecord entity = null;
		if (id!=null && id>0){
			entity = personalRecordService.get(id);
		}
		if (entity == null){
			entity = new PersonalRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("ante:personalRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PersonalRecord personalRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		personalRecord.setCreateBy(user);//创建者为本人
		Integer orgId = user.getOffice().getId();//机构号
		personalRecord.setOrgId(orgId);
		Page<PersonalRecord> page = personalRecordService.findPage(new Page<PersonalRecord>(request, response), personalRecord); 
		model.addAttribute("page", page);
		return "modules/ante/personalRecordList";
	}

	/**
	 * @param personalRecord
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value={"ante:personalRecord:view","ante:personalRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PersonalRecord personalRecord, Model model) {
		model.addAttribute("personalRecord", personalRecord);
		return "modules/ante/personalRecordForm";
	}
	
	/**
	 * 查看
	 * @param personalRecord
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:personalRecord:view")
	@RequestMapping(value = "view")
	public String view(PersonalRecord personalRecord, Model model) {
		model.addAttribute("personalRecord", personalRecord);
		return "modules/ante/personalRecordView";
	}

	@RequiresPermissions("ante:personalRecord:edit")
	@RequestMapping(value = "save")
	public String save(PersonalRecord personalRecord, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		personalRecord.setCreateBy(user);//创建者为本人
		Integer orgId = user.getOffice().getId();//机构号
		personalRecord.setOrgId(orgId);
		if (!beanValidator(model, personalRecord)){
			return form(personalRecord, model);
		}
		personalRecordService.save(personalRecord);
		addMessage(redirectAttributes, "保存个人成长记录成功");
		return "redirect:"+Global.getAdminPath()+"/ante/personalRecord/?repage";
	}
	
	/**
	 * 删除一条
	 * @param personalRecord
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:personalRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PersonalRecord personalRecord, RedirectAttributes redirectAttributes) {
		personalRecordService.delete(personalRecord);
		addMessage(redirectAttributes, "删除个人成长记录成功");
		return "redirect:"+Global.getAdminPath()+"/ante/personalRecord/?repage";
	}
	
	/**
	 * 删除多条
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:personalRecord:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		ids = StringFunctionUtils.replaceWebJsp(ids);
		String[] split = ids.substring(1, ids.length()-1).split(",");
		for (String idStr : split) {
			idStr = idStr.substring(1, idStr.length()-1);
			Integer id=Integer.valueOf(idStr);
			PersonalRecord personalRecord = new PersonalRecord();
			personalRecord.setId(id);
			personalRecordService.delete(personalRecord);
		}
		addMessage(redirectAttributes, "删除个人成长记录成功");
		return "redirect:"+Global.getAdminPath()+"/ante/personalRecord/?repage";
	}

}