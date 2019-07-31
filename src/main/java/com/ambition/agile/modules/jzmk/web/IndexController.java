/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.PublicActivity;
import com.ambition.agile.modules.ante.service.PublicActivityService;
import com.ambition.agile.modules.counsel.constant.CounselorConstant;
import com.ambition.agile.modules.counsel.entity.CounselCenter;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselCenterService;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.jzmk.entity.SysAffiche;
import com.ambition.agile.modules.jzmk.service.SysAfficheService;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private SysAfficheService sysAfficheService;
	
	@Autowired
	private PublicActivityService publicActivityService;
	
	@Autowired
	private CounselCenterService counselCenterService;

	@RequestMapping("web/login")
	public String gologin(){
		return "web/login";
	}
	
	@RequestMapping("web/register")
	public String goRegister(){
		return "web/register";
	}
	
	@RequestMapping("web/circle")
	public String goCircle(){
		return "web/circle";
	}
	
	@RequestMapping("web/clroom")
	public String goClroom(){
		return "web/clroom";
	}
	
	@RequestMapping("web/mirror")
	public String goMirror(){
		return "web/mirror";
	}
	
	@RequestMapping("web/scale")
	public String goScale(){
		return "web/scale";
	}

	@RequestMapping("web/system")
	public String goSystem(){
		return "web/system";
	}
	
	@RequestMapping("web/visitor")
	public String goVisitor(){
		return "web/visitor";
	}
	
	@RequestMapping("web/afficheList")
	public String goAfficheList(SysAffiche sysAffiche,HttpServletRequest request, HttpServletResponse response, Model model){
		if(sysAffiche==null){
			sysAffiche=new SysAffiche();
		}
		sysAffiche.setOrgId(0);
		sysAffiche.setFirstDisplay("1");
		Page<SysAffiche> page = sysAfficheService.findPage(new Page<SysAffiche>(request, response), sysAffiche); 
		model.addAttribute("page", page);
		return "web/afficheList";
	}
	
	@RequestMapping("web/afficheView")
	public String goAfficheView(SysAffiche sysAffiche, Model model){
		sysAffiche = sysAfficheService.get(sysAffiche.getId());
		model.addAttribute("sysAffiche", sysAffiche);
		return "web/afficheView";
	}
	
	@RequestMapping("web/welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response, 
			Model model) {
		User user = UserUtils.getUser();
		if(user != null && user.getCompany() != null) {
			model.addAttribute("currentUser", user);
		}
		CounselCenter counselCenter = new CounselCenter();
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			if(orgId >-1 ){
				counselCenter = counselCenterService.getCounselCenterByOrgId(orgId);
			}
			if(counselCenter != null) {
				counselCenter.setOrgId(orgId);
			}
		}
		model.addAttribute("counselCenter", counselCenter);
		return "web/welcome";
	}
	
	@RequestMapping("web/index")
	public String goIndex(HttpServletRequest request, HttpServletResponse response, 
			Model model) {
		User user = UserUtils.getUser();
		Integer orgId = null;
		if(user != null && user.getCompany() != null) {
			orgId = user.getCompany().getId();//平台管理员绑定机构
			model.addAttribute("currentUser", user);
		}
		//Integer isAdmin = user.getIsAdmin();//管理员类型（1、2可以登录平台后台） 1机构默认管理员 2平台管理员 3非管理员
		SysAffiche sysAffiche = new SysAffiche();
		sysAffiche.setOrgId(orgId);
		sysAffiche.setFirstDisplay("1");
		List<SysAffiche> sysAffiches = sysAfficheService.findTop3List(sysAffiche);
		model.addAttribute("sysAffiches", sysAffiches);
		PublicActivity publicActivity = new PublicActivity();
		publicActivity.setOrgId(orgId);
		List<PublicActivity> publicActivitys = publicActivityService.findTop3List(publicActivity);
		model.addAttribute("publicActivitys", publicActivitys);
		Counselor counselor = new Counselor();
		counselor.setIndexFlag(1);
		counselor.setStatus(CounselorConstant.STATUS_PASSABLE);
		List<Counselor> counselors = counselorService.findTop3List(counselor);
		model.addAttribute("counselors", counselors);
		return "web/index";
	}
	
	//2018/3/14  杜笑飞添加以下代码
	/**
	 * 幸福镜子
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/mirror")
	public String happyMirror(Model model){
		return "modules/mirror/mirror";
	}
	
	/**
	 * 开始评估
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/testMirror")
	public String testMirror(Model model){
		return "modules/mirror/testMirror";
	}
	/**
	 * 测试结果
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/testResult")
	public String testResult(Model model){
		return "modules/mirror/testResult";
	}
	/**
	 * 圈子
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/mobileCircle")
	public String circle(Model model){
		System.out.println("123456");
		return "modules/jzmk/circleList";
	}
	
	/**
	 * 我的对比图
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/myCircle")
	public String myCircle(Model model){
		System.out.println("123456");
		return "modules/circle/myCircle";
	}
	/**
	 * 评论详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/circleView")
	public String circleView(Model model){
		return "modules/circle/circleView";
	}
	/**
	 * 我发布的
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/publish")
	public String publish(Model model){
		return "modules/circle/publish";
	}
	
	/**
	 * 酣眠支持
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/support")
	public String support(Model model){
		return "modules/music/support";
	}
	@RequestMapping(value="${adminPath}/jzmk/music")
	public String music(Model model){
		return "modules/music/music";
	}
	/**
	 * 睡眠评估
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/scale")
	public String assess(Model model){
		return "modules/jzmk/assess";
	}
	
	/**
	 * 测评任务管理列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/manage")
	public String manage(Model model){
		return "modules/assess/manageList";
	}
	/**
	 * 测评任务管理 删除
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/delete")
	public String delete (Model model){
		return "modules/assess/manageDelete";
	}
	/**
	 * 测评任务详情页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/view")
	public String view(Model model){
		return "modules/assess/manageView";
	}
	
	/**
	 * 添加测评任务选择信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/addChoose")
	public String addChoose(Model model){
		System.out.println("123456");
		return "modules/assess/addChoose";
	}
	/**
	 * 心理异常筛选
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/mentality")
	public String mentality(Model model){
		return "modules/assess/mentality";
	}
	/**
	 * 心理异常筛选详情页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/mentalityView")
	public String mentalityView(Model model){
		return "modules/assess/mentalityView";
	}
	/**
	 * 全部测评结果
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/assessResult")
	public String assessResult(Model model){
		return "modules/assess/assessResult";
	}
	/**
	 * 测评结果详情页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/assessView")
	public String assessView(Model model){
		return "modules/assess/assessResultView";
	}
	
	/**
	 * 预约咨询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/order")
	public String order(Model model){
		return "modules/counsel/order";
	}
//	/**
//	 * 咨询记录
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="${adminPath}/jzmk/counselorRecord")
//	public String counselorRecord(Model model){
//		return "modules/counsel/counselRecordList";
//	}
	/**
	 * 选择督导老师
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/choose")
	public String choose(Model model){
		return "modules/order/chooseTeacher";
	}
	/**
	 * 咨询师详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/counselorView")
	public String counselorView(Model model){
		return "modules/order/counselorView";
	}
	/**
	 * 酣眠课堂
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/course")
	public String courseIndex(Model model){
		return "modules/course/courseList";
	}
	
	/**
	 * 酣眠课堂内容页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/courseView")
	public String courseView(Model model){
		return "modules/course/courseView";
	}
	/**
	 * 酣眠课堂留言
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/message")
	public String message(Model model){
		return "modules/course/message";
	}
	
	/**
	 * 我的课件
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/myCourse")
	public String myCourse(Model model){
		return "modules/course/myCourse";
	}
	
	/**
	 *新增课件
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/addCourse")
	public String addCourse(Model model){
		return "modules/course/addCourse";
	}
	
	/**
	 * 我的
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/personal")
	public String personal(Model model){
		return "modules/personal/personal";
	}
	
	/**
	 * 系统设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/system")
	public String system(Model model){
		return "modules/personal/system";
	}
	
	/**
	 * 密码管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/password")
	public String password(Model model){
		return "modules/personal/password";
	}
	/**
	 * 使用帮助
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/useHelp")
	public String useHelp(Model model){
		return "modules/personal/useHelp";
	}
	/**
	 * 我的成长记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/group")
	public String group(Model model){
		return "modules/personal/groupRecord";
	}
	/**
	 * 我的咨询记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/counselor")
	public String counselor(Model model){
		return "modules/personal/counselorRecord";
	}
	/**
	 * 我的测评记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="${adminPath}/jzmk/appraisal")
	public String appraisal(Model model){
		return "modules/personal/appraisalRecord";
	}
}