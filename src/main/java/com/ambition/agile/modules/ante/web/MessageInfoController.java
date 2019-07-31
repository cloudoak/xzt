/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ambition.agile.modules.ante.entity.MessageInfo;
import com.ambition.agile.modules.ante.service.MessageInfoService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 消息管理Controller
 * @author fengqq
 * @version 2017-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/messageInfo")
public class MessageInfoController extends BaseController {

	@Autowired
	private MessageInfoService messageInfoService;
	
	@ModelAttribute
	public MessageInfo get(@RequestParam(required=false) Integer id) {
		MessageInfo entity = null;
		if (id!=null&&id>0){
			entity = messageInfoService.get(id);
		}
		if (entity == null){
			entity = new MessageInfo();
		}
		return entity;
	}
	
	/**
	 * 收件箱
	 * @param messageInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:messageInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MessageInfo messageInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageInfo> page = messageInfoService.findPage(new Page<MessageInfo>(request, response), messageInfo); 
		model.addAttribute("page", page);
		return "modules/ante/messageInfoList";
	}
	
	/**
	 * 发件箱
	 * @param messageInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:messageInfo:view")
	@RequestMapping(value = {"sendList", ""})
	public String sendList(MessageInfo messageInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageInfo> page = messageInfoService.findPage(new Page<MessageInfo>(request, response), messageInfo); 
		model.addAttribute("page", page);
		return "modules/ante/messageInfoSendList";
	}

	@RequiresPermissions("ante:messageInfo:view")
	@RequestMapping(value = "form")
	public String form(MessageInfo messageInfo, Model model) {
		model.addAttribute("messageInfo", messageInfo);
		return "modules/ante/messageInfoForm";
	}

	/**
	 * 发送
	 * @param messageInfo
	 * @param send
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:messageInfo:edit")
	@RequestMapping(value = "save")
	public String save(MessageInfo messageInfo, boolean send, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		messageInfo.setFromUser(user.getLoginName());
		
		if (!beanValidator(model, messageInfo)){
			return form(messageInfo, model);
		}
		messageInfoService.save(messageInfo);
		addMessage(redirectAttributes, "发送消息管理成功");
		
		if(send==true) {
			return "redirect:"+Global.getAdminPath()+"/ante/messageInfo/sendList";
		}else {
			return "redirect:"+Global.getAdminPath()+"/ante/messageInfo/list";
		}
	}
	
	@RequiresPermissions("ante:messageInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MessageInfo messageInfo, boolean send, RedirectAttributes redirectAttributes) {
		messageInfoService.delete(messageInfo);
		addMessage(redirectAttributes, "删除消息管理成功");
		if(send==true) {
			return "redirect:"+Global.getAdminPath()+"/ante/messageInfo/sendList";
		}else {
			return "redirect:"+Global.getAdminPath()+"/ante/messageInfo/list";
		}
	}

	@RequiresPermissions("ante:messageInfo:edit")
	@RequestMapping(value = "read")
	public String read(MessageInfo messageInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, messageInfo)){
			return form(messageInfo, model);
		}
		messageInfo.setStatus("READ");
		messageInfoService.save(messageInfo);
		model.addAttribute("messageInfo", messageInfo);
		return "modules/ante/messageInfoRead";
	}
	
}