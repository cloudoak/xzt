/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.security.shiro.session.SessionDAO;
import com.ambition.agile.common.servlet.ValidateCodeServlet;
import com.ambition.agile.common.utils.CacheUtils;
import com.ambition.agile.common.utils.CookieUtils;
import com.ambition.agile.common.utils.IdGen;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.jzmk.entity.SysAffiche;
import com.ambition.agile.modules.jzmk.entity.SysConfig;
import com.ambition.agile.modules.jzmk.service.SysAfficheService;
import com.ambition.agile.modules.jzmk.service.SysConfigService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.entity.MessageInfo;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.PublicActivity;
import com.ambition.agile.modules.counsel.entity.Counselor;

import com.ambition.agile.modules.sys.security.FormAuthenticationFilter;
import com.ambition.agile.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.ante.service.MessageInfoService;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.PublicActivityService;
import com.ambition.agile.modules.counsel.service.CounselorService;

import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.WebUtil;
import com.google.common.collect.Maps;

/**
 * 登录Controller
 * @author harry
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private SysAfficheService sysAfficheService;
	
	@Autowired
	private PublicActivityService publicActivityService;
	
	@Autowired
	private MessageInfoService messageInfoService;
	
	@Autowired
	@Value(value = "${file.appversion.absolute.upload}")
	private String fileAbsolutePath;
	
	@Autowired
	@Value(value = "${file.appversion.port}")
	private Integer port;
	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

		Principal principal = UserUtils.getPrincipal();
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		// 如果已经登录，则跳转到管理首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath;
		}
		if(principal != null && principal.isMobileLogin()){
			return "redirect:" + "web/index";
		}
		return "modules/sys/sysLogin";
	}
	

	/**
	 * 注册初始化页面
	 */
	@RequestMapping(value = "/web/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "web/register";
	}
	
	/**
	 * 注册提交
	 */
	@RequestMapping(value = "web/register/save")
	public String registerSave(VisitorInfo visitorInfo ,Parents parents ,Counselor counselor ,Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, visitorInfo) 
				&&!beanValidator(model, parents) && !beanValidator(model, counselor)){
			return "web/register";
		}
		if(visitorInfo!=null) {
			visitorInfoService.save(visitorInfo);//来访者
			addMessage(redirectAttributes, "来访者注册成功!");
		}else if(parents!=null) {
			parentsService.save(parents);//家属
			addMessage(redirectAttributes, "家属注册成功!");
		}else {
			counselorService.save(counselor);//咨询师
			addMessage(redirectAttributes, "咨询师注册成功!");
		}
		
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}
		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
	        return renderString(response, model);
		}
		
		return "modules/sys/sysLogin";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response, 
			Model model) {
		User user = UserUtils.getUser();
		if(user==null||user.getIsAdmin()==null){
			return "modules/sys/sysLogin";
		}
		Integer orgId = null;
		if(user != null && user.getCompany() != null) {
			orgId = user.getCompany().getId();//平台管理员绑定机构
			model.addAttribute("currentUser", user);
		}

		//检测该用户是否是平台管理员
		if(user.getIsAdmin()==2){
			//判断是否已经选择管理机构
			/*Office office = user.getCompany();
			if(office==null||office.getId()==null||office.getId()==0||office.getId()==1){
				List<Office> listOffice = officeService.findAll();
				model.addAttribute("listOffice", listOffice);
				return "modules/sys/sysLogin";
			}*/
			Office office = new Office();
			office.setParentId(0);
			List<Office> listOffice = officeService.findOrgList(office);
			model.addAttribute("listOffice", listOffice);
			return "modules/sys/sysLogin";
		}
		//Integer isAdmin = user.getIsAdmin();//管理员类型（1、2可以登录平台后台） 1机构默认管理员 2平台管理员 3非管理员
		SysAffiche sysAffiche = new SysAffiche();
		sysAffiche.setOrgId(orgId);
		List<SysAffiche> sysAffiches = sysAfficheService.findTop3List(sysAffiche);
		model.addAttribute("sysAffiches", sysAffiches);
		PublicActivity publicActivity = new PublicActivity();
		publicActivity.setOrgId(orgId);
		List<PublicActivity> publicActivitys = publicActivityService.findTop3List(publicActivity);
		model.addAttribute("publicActivitys", publicActivitys);
		List<Counselor> counselors = counselorService.findTop3List(new Counselor());
		model.addAttribute("counselors", counselors);
		return "web/index";
	}
	

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}/backstage")
	public String backstage(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		if(user==null||user.getIsAdmin()==null){
			return "modules/sys/sysLogin";
		}
		Integer orgId = user.getCompany().getId();//平台管理员绑定机构
		//String userType = user.getUserType();//用户类型 0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
		Integer isAdmin = user.getIsAdmin();//管理员类型（1、2可以登录平台后台） 1机构默认管理员 2平台管理员 3非管理员
		//判断当前登录后台用户的身份是不是后台管理员，不是则不允许登录后台
//		if(isAdmin!=1&&isAdmin!=2){
//			UserUtils.getSubject().logout();
//			addMessage(model, "登录失败，登录信息不正确！");
//			return "modules/sys/sysLogin";
//		}
		
		//获取管理员管理的机构配置信息
		SysConfig sysConfig=new SysConfig();
		sysConfig.setOrgId(orgId);
		List<SysConfig> list = sysConfigService.findList(sysConfig);
		if(list==null||list.size()<1){
			sysConfig.setOrgId(0);
			list = sysConfigService.findList(sysConfig);
		}else{
			sysConfig = list.get(0);
			if(sysConfig.getSchoolName()==null){
				sysConfig.setSchoolName(UserUtils.getUser().getCompany().getName());
			}
		}
		model.addAttribute("sysConfig", sysConfig);
		
		Principal principal = UserUtils.getPrincipal();

		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login";
			}
		}
		
//		//检测该用户是否是平台管理员
//		if(user.getIsAdmin()==2){
//			//判断是否已经选择管理机构
//			/*Office office = user.getCompany();
//			if(office==null||office.getId()==null||office.getId()==0||office.getId()==1){
//				List<Office> listOffice = officeService.findAll();
//				model.addAttribute("listOffice", listOffice);
//				return "modules/sys/sysLogin";
//			}*/
//			List<Office> listOffice = officeService.findAll();
//			model.addAttribute("listOffice", listOffice);
//			return "modules/sys/sysLogin";
//		}
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setStatus("UNREAD");
		List<MessageInfo> messageList = messageInfoService.findList(messageInfo);
		model.addAttribute("msgListSize",messageList.size());
		model.addAttribute("currentUser", user);
		model.addAttribute("rediretUrl", request.getParameter("rediretUrl"));
		model.addAttribute("title", request.getParameter("title"));
		return "modules/sys/sysIndex";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
	
	/**
	 * 平台管理员，选择管理机构
	 */
	@RequestMapping(value = "${adminPath}/changeorg", method = RequestMethod.POST)
	public String changeOrg(HttpServletRequest request, HttpServletResponse response, Model model) {

		String orgId = request.getParameter("orgId");
		User user = UserUtils.getUser();
		if(orgId != null) {
			UserUtils.setOrg(Integer.valueOf(orgId));
		}
		//验证用登录信息是否正确
		systemService.updateUserInfo(user);
		//更新页面用户管理公司信息
		SysConfig sysConfig=new SysConfig();
		sysConfig.setOrgId(Integer.valueOf(orgId));
		List<SysConfig> list = sysConfigService.findList(sysConfig);
		if(list==null||list.size()<1){
			sysConfig = sysConfigService.get(1);
		}else{
			sysConfig = list.get(0);
		}
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setStatus("UNREAD");
		List<MessageInfo> messageList = messageInfoService.findList(messageInfo);
		model.addAttribute("msgListSize",messageList.size());
		model.addAttribute("sysConfig", sysConfig);
		model.addAttribute("currentUser", user);
		return "modules/sys/sysIndex";
	}
	
//	/**
//	 * 退出系统
//	 * @return
//	 */
//	@RequestMapping(value = "${adminPath}/logout")
//	public String loginout() {
//		Principal principal = UserUtils.getPrincipal();
//		Subject currentUser = SecurityUtils.getSubject();
//		if(principal != null && !principal.isMobileLogin()){
////			currentUser.getSession().removeAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
////			currentUser.getSession().removeAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
////			currentUser.getSession().removeAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
////			currentUser.getSession().removeAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
////			currentUser.getSession().removeAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
//			UserUtils.clearCache();
//			currentUser.logout();
//		}
//		return "login";
//	}
	
	/**
	 * 是否是平台管理员，用于登录前选择管理机构
	 */
	@RequestMapping(value = "${adminPath}/isadmin", method = RequestMethod.POST)
	@ResponseBody
	public String chkOrg(HttpServletRequest request, HttpServletResponse response, Model model) {

		//验证用登录信息是否正确
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = systemService.getUserByLoginName(username);
		if(user!=null){
			String pwd = user.getPassword();
			password = SystemService.entryptPassword(password);
			if(pwd.equals(password)||pwd.indexOf(password)!=-1){
				//检测该用户是否是平台管理员
				if(user.getIsAdmin()==2){
					//判断是否已经选择管理机构
					Office office = user.getCompany();
					if(office==null||office.getId()==null||office.getId()==0||office.getId()==1){
						return "true";
					}
				}
			}
		}
		return "false";
	}
	
	public String getOfficeLogoPath(){
		StringBuffer contextPath = new StringBuffer();
		if(fileAbsolutePath != null) {
			if(!fileAbsolutePath.startsWith("/")) {
				contextPath.append("/");
			}
			contextPath.append(fileAbsolutePath);
			if(!fileAbsolutePath.endsWith("/")) {
				contextPath.append("/");
			}
		}
		Office office = officeService.get(UserUtils.getUser().getCompany().getId());
		contextPath.append(office.getLogo());
		return WebUtil.getRealPath(contextPath.toString(), port);
	}
}
