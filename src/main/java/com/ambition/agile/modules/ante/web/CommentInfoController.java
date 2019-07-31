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
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.ante.entity.CommentInfo;
import com.ambition.agile.modules.ante.service.CommentInfoService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 评价管理Controller
 * @author WHQ
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/ante/commentInfo")
public class CommentInfoController extends BaseController {

	@Autowired
	private CommentInfoService commentInfoService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public CommentInfo get(@RequestParam(required=false) Integer id) {
		CommentInfo entity = null;
		if (id!=null&&id>0){
			entity = commentInfoService.get(id);
		}
		if (entity == null){
			entity = new CommentInfo();
		}
		return entity;
	}
	
	/**
	 * 评语记录查询列表
	 * @param commentInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommentInfo commentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CommentInfo> page = commentInfoService.findPage(new Page<CommentInfo>(request, response), commentInfo); 
		model.addAttribute("page", page);
		return "modules/ante/commentInfoList";
	}
	
	/**
	 * 评语记录查看列表
	 * @param commentInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = {"viewList"})
	public String viewList(CommentInfo commentInfo, String userId1, HttpServletRequest request, HttpServletResponse response, Model model) {
		commentInfo.setUserId1(userId1);
		Page<CommentInfo> page = commentInfoService.findPage(new Page<CommentInfo>(request, response), commentInfo); 
		model.addAttribute("page", page);
		return "modules/ante/commentInfoViewList";
	}
	
	/**
	 * 给我的评语记录
	 * @param commentInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = {"toMeList"})
	public String toMeList(CommentInfo commentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		commentInfo.setUserId2(user.getId().toString());
		Page<CommentInfo> page = commentInfoService.findPage(new Page<CommentInfo>(request, response), commentInfo); 
		model.addAttribute("page", page);
		return "modules/ante/commentInfoToMeList";
	}
	
	/**
	 * 给他人的评语记录
	 * @param commentInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = {"toOtherList"})
	public String toOtherList(CommentInfo commentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		commentInfo.setUserId1(user.getId().toString());
		Page<CommentInfo> page = commentInfoService.findPage(new Page<CommentInfo>(request, response), commentInfo); 
		model.addAttribute("page", page);
		return "modules/ante/commentInfoToOtherList";
	}

	/**
	 * form
	 * @param commentInfo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = "form")
	public String form(CommentInfo commentInfo, Model model) {
		model.addAttribute("commentInfo", commentInfo);
		return "modules/ante/commentInfoForm";
	}
	
	@RequiresPermissions("ante:commentInfo:view")
	@RequestMapping(value = "view")
	public String view(CommentInfo commentInfo, Model model) {
		model.addAttribute("commentInfo", commentInfo);
		return "modules/ante/commentInfoView";
	}

	/**
	 * 新增
	 * @param commentInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ante:commentInfo:edit")
	@RequestMapping(value = "save")
	public String save(CommentInfo commentInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commentInfo)){
			return form(commentInfo, model);
		}
		User user1 = UserUtils.getUser();//评价人
		User user2 = systemService.getUserById(Integer.valueOf(commentInfo.getUserId2()));//被评价人
		String userType = "" + user1.getUserType()+user2.getUserType();//用户类型 :0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
		commentInfo.setCommentType(userType);//评价种类：44-来访者评价来访者;42-来访者评价老师;32-家属评价老师;24-老师评价来访者;
		commentInfoService.save(commentInfo);
		addMessage(redirectAttributes, "保存评价管理成功");
		return "redirect:"+Global.getAdminPath()+"/ante/commentInfo/?repage";
	}
	
	@RequiresPermissions("ante:commentInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CommentInfo commentInfo, RedirectAttributes redirectAttributes) {
		commentInfoService.delete(commentInfo);
		addMessage(redirectAttributes, "删除评价管理成功");
		return "redirect:"+Global.getAdminPath()+"/ante/commentInfo/?repage";
	}

}