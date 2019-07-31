/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.sys.web;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ambition.agile.common.config.Global;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.CacheUtils;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.entity.VisitorInfo;
import com.ambition.agile.modules.ante.service.ParentsService;
import com.ambition.agile.modules.ante.service.VisitorInfoService;
import com.ambition.agile.modules.ante.vo.UserInfoVo;
import com.ambition.agile.modules.counsel.constant.CounselorConstant;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.jzmk.entity.SysConfig;
import com.ambition.agile.modules.jzmk.service.SysConfigService;
import com.ambition.agile.modules.sys.constant.UserConstant;
import com.ambition.agile.modules.sys.entity.Dict;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.Role;
import com.ambition.agile.modules.sys.entity.ThirdParty;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.DictService;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.service.SystemService;
import com.ambition.agile.modules.sys.service.ThirdPartyService;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.ambition.agile.modules.utils.JSONSerException;
import com.ambition.agile.modules.utils.JSONUtil;
import com.ambition.agile.modules.utils.SMSUtil;
import com.google.common.collect.Lists;

/**
 * 注册Controller
 * @author OAK
 * @version 2017/12/24
 */
@Controller
@RequestMapping(value = "${adminPath}/register")
public class RegisterController extends BaseController {
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private VisitorInfoService visitorInfoService;
	
	@Autowired
	private CounselorService counselorService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	SysConfigService sysConfigService;
	
	@Autowired
	ThirdPartyService thirdPartyService;
	
	@RequestMapping(value = "form")
	public String registerForm(VisitorInfo visitorInfo, Parents parents, Counselor counselor, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Dict dict = new Dict();
		dict.setType("nation");
		List<Dict> nationList = dictService.findList(dict);
		model.addAttribute("nationList", nationList);
		Office office = new Office();
		office.setParentId(0);
		List<Office> offices = officeService.findOrgList(office);
		model.addAttribute("offices", offices);
		
		List<ThirdParty> provinces = thirdPartyService.findAllProvinces();
		model.addAttribute("provinces", provinces);
						
//		SysConfig sysConfig = new SysConfig();
//		
//		Office currentOrg = UserUtils.getOrg();
//		if(currentOrg != null) {
//			sysConfig.setOrgId(currentOrg.getId());
//		}
//		List<SysConfig> list = sysConfigService.findList(sysConfig);
//		if(list.size() > 0){
//			sysConfig = list.get(0);
//			model.addAttribute("enableSMS", sysConfig.getEnableSmsRemind());
//		}else {
//			model.addAttribute("enableSMS", 0);
//		}
		
		SysConfig sysConfig = sysConfigService.findOne();
		
		if(sysConfig == null) {
			sysConfig = new SysConfig();
			sysConfig.setEnableSmsRemind(0);
		}
		
		model.addAttribute("enableSMS", sysConfig.getEnableSmsRemind());

        return "web/register";
	}
	
	@RequestMapping(value = "forget")
	public String forgetForm(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("enableSMS", 0);
        return "web/forget";
	}
	
	@RequestMapping("findByProvinceId")
	@ResponseBody
	public String findByProvinceId(String provinceId){
		
		List<ThirdParty> cities = thirdPartyService.findByProvinceId(provinceId);
		
		String result = "";
		
		try {
			result = JSONUtil.toJSON(cities);
		} catch (JSONSerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("findByCityId")
	@ResponseBody
	public String findByCityId(String cityId){
		
		List<ThirdParty> areas = thirdPartyService.findByCityId(cityId);
		
		String result = "";
		
		try {
			result = JSONUtil.toJSON(areas);
		} catch (JSONSerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("checkAccount")
	@ResponseBody
	public int checkAccount(String account, Integer org){
		User currentUser = new User();
		currentUser.setLoginName(account);
		Office office = new Office();
		office.setId(org);
		currentUser.setOffice(office);
		User user = systemService.findExistsUserName(currentUser);
		
		if(user != null) {
			return 0;
		}
		
		return 1;
	}
	
	@RequestMapping("checkExistAccount")
	@ResponseBody
	public int checkExistAccount(String account, Integer org){
		User currentUser = new User();
		currentUser.setLoginName(account);
		Office office = new Office();
		office.setId(org);
		currentUser.setOffice(office);
		User user = systemService.findExistsUserName(currentUser);
		
		if(user == null) {
			return 0;
		}
		
		return 1;
	}
	
	@RequestMapping("checkForgetAccount")
	@ResponseBody
	public int checkForgetAccount(String account){
		User currentUser = new User();
		currentUser.setLoginName(account);
		User user = systemService.findExistsUserName(currentUser);
		
		if(user != null) {
			return 0;
		}
		
		return 1;
	}
	
	@RequestMapping("checkPhone")
	@ResponseBody
	public int checkPhone(String account,String phone){
		User currentUser = new User();
		currentUser.setLoginName(account);
		User user = systemService.findExistsUserName(currentUser);
		
		if(!user.getPhone().equals(phone)) {
			return 1;
		}
		
		return 0;
	}
	
	@RequestMapping("forgetSave")
	@ResponseBody
	public int forgetSave(String account,String password){
		User currentUser = new User();
		currentUser.setLoginName(account);
		User user = systemService.findExistsUserName(currentUser);
		user.setPassword(SystemService.entryptPassword(password));
		try{
			systemService.updatePasswordById(user.getId(), user.getLoginName(), password);
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	@RequestMapping("checkVerificationCode")
	@ResponseBody
	public int checkVerificationCode(String verificationCode, Integer enableSMS){
		
		if(enableSMS.intValue() == 0)
			return 1;
		
		String requestedSessionId = request.getRequestedSessionId();
		
		Object verificationCodeObj = CacheUtils.get("VERIFICATIONCODE", requestedSessionId);
		
		if(verificationCodeObj == null) {
			return 0;
		}
		
		if(!verificationCode.equals(verificationCodeObj)) {
			return 0;
		}
		return 1;
	}
	
	@RequestMapping(value="sendSMS")
	@ResponseBody
	public String sendSMS(UserInfoVo userInfoVo, HttpServletRequest request) throws ClientException {
		
		response.setContentType("text/html");
		
		String requestedSessionId = request.getRequestedSessionId(), verificationCode = "";
		
		Random rand = new Random();
		for(int i = 0; i < 6; i++) {
			verificationCode += rand.nextInt(9);
		}
		
		Object verificationCodeObj = CacheUtils.get("VERIFICATIONCODE", requestedSessionId);
		
		if(verificationCodeObj != null) {
			CacheUtils.remove("VERIFICATIONCODE", requestedSessionId);
		}
		
		CacheUtils.put("VERIFICATIONCODE", requestedSessionId, verificationCode);
		
		SendSmsResponse sendSmsResponse = SMSUtil.sendSMS(userInfoVo.getPhone(), verificationCode, "BIZ" + verificationCode);
		
		
		logger.warn("阿里云-状态码：" + sendSmsResponse.getCode() + ", 消息：" + sendSmsResponse.getMessage());
		
		return "BIZ" + verificationCode;
	}
	
	/**
	 * 注册(来访者、家属、咨询师)
	 * @param visitorInfo
	 * @param parents
	 * @param counselor
	 * @param user
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String registerSave(UserInfoVo userInfoVo, String visitorAccount, String visitorPhone, 
			String datamodel, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userInfoVo) ){
			return "web/register";
		}
		if(userInfoVo != null && datamodel != null) {
			User user = new User();
			user.setName(userInfoVo.getName());
			user.setPhone(userInfoVo.getPhone());
			user.setLoginName(userInfoVo.getAccount());
			user.setAreaCode(userInfoVo.getArea());
			user.setStatus(0);//审核状态：0-待审核;1-审核通过;2-审核未通过;
			user.setEmail(userInfoVo.getEmail());
			user.setLoginIp(request.getLocalAddr());
			// 角色数据有效性验证，过滤不在授权内的角色
			List<Role> roleList = Lists.newArrayList();
			List<Integer> roleIdList = user.getRoleIdList();
			for (Role r : systemService.findAllRole()){
				if (roleIdList.contains(r.getId())){
					roleList.add(r);
				}
			}
			if(datamodel.equals("consultant")) {
				user.setIsAdmin(3);
				user.setUserType(UserConstant.USER_CONSULTANT_TYPE);
				Role role = systemService.getRoleByName("咨询师");
				user.setRole(role);
				roleList.add(role);
				user.setRoleList(roleList);
			}else if(datamodel.equals("visitor")) {
				user.setIsAdmin(3);
				user.setUserType(UserConstant.USER_VISITOR_TYPE);
				Role role = systemService.getRoleByName("来访者");
				user.setRole(role);
				roleList.add(role);
				user.setRoleList(roleList);
			}else if(datamodel.equals("familyMembers")) {
				user.setIsAdmin(3);
				user.setUserType(UserConstant.USER_FAMILYMEMBERS_TYPE);
				Role role = systemService.getRoleByName("家属");
				user.setRole(role);
				roleList.add(role);
				user.setRoleList(roleList);
			}//设置用户类型  0平台管理员 1机构默认管理员 2咨询师 3家属 4来访者
			Office office = officeService.get(userInfoVo.getOrganization());
			if(office != null) {
				user.setCompany(office);
				user.setOffice(office);
				user.setOfficeId(office.getId());
			}
			//是否更新密码
			if(userInfoVo.getPassword() != null 
					&& userInfoVo.getPassword().length() != 56){
				user.setPassword(SystemService.entryptPassword(userInfoVo.getPassword()));
			}else{
				user.setPassword(null);
			}
			User u = systemService.getByLoginName("system");
			user.setCreateBy(u);
			user.setCreateDate(new Date());
			user.setUpdateBy(u);
			user.setUpdateDate(new Date());
			// 保存用户信息
			systemService.saveUser(user);
			Integer id = user.getId();
			User currentUser = systemService.getUserById(id);
			if(datamodel.equals("consultant")) {
				Counselor counselor = new Counselor();
				counselor.setSex(userInfoVo.getSex());
				if(currentUser != null) {
					counselor.setUserId(currentUser.getId());
					counselor.setOrgId(currentUser.getOfficeId());
				}
				counselor.setNation(userInfoVo.getNation());
				counselor.setBirthday(userInfoVo.getBirthday());
				counselor.setJob(userInfoVo.getPosition());
				counselor.setJobName(userInfoVo.getIntroduction());
				counselor.setStatus(CounselorConstant.STATUS_AUDIT);
				counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
				counselorService.save(counselor);
			}else if(datamodel.equals("visitor")) {
				VisitorInfo visitorInfo = new VisitorInfo();
				visitorInfo.setSex(userInfoVo.getSex());
				visitorInfo.setVisitorNo(userInfoVo.getAccount());
				visitorInfo.setAddress(userInfoVo.getAddress());
				visitorInfo.setNation(userInfoVo.getNation());
				visitorInfo.setPhoneNum(userInfoVo.getPhone());
				visitorInfo.setBirthday(userInfoVo.getBirthday());
				visitorInfo.setStatus(0);
				if(currentUser != null) {
					visitorInfo.setUser(currentUser);
					visitorInfo.setUserId(currentUser.getId());
					visitorInfo.setPassword(currentUser.getPassword());
					visitorInfo.setOrgId(currentUser.getOfficeId());
				}
				visitorInfoService.save(visitorInfo);//来访者
			}else if(datamodel.equals("familyMembers")) {
				Parents parents = new Parents();
				parents.setGender(userInfoVo.getSex());
				parents.setPhone(userInfoVo.getPhone());
				parents.setNationId(userInfoVo.getNation());
				parents.setBirthday(userInfoVo.getBirthday());
				parents.setStudentCode(visitorAccount);
				parents.setState(0);
				if(currentUser != null) {
					parents.setOrgId(currentUser.getOfficeId());
					parents.setUserId(currentUser.getId());
					parents.setPassword(currentUser.getPassword());
					parents.setAccount(currentUser.getLoginName());
				}
				parentsService.save(parents);
			}
		}
		addMessage(redirectAttributes, "注册成功!");
		return "modules/sys/sysLogin";
	}
	
	
	/**
	 * 注册提交
	 * @param user
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	public void userSave(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		String companyId = request.getParameter("company.id");
		String officeId = request.getParameter("office.id");
		if(companyId!=null&&!"".equals(companyId)){
			user.setCompany(new Office(Integer.valueOf(companyId)));
		}
		if(officeId!=null&&!"".equals(officeId)){
			user.setOffice(new Office(Integer.valueOf(officeId)));
		}
		//是否更新密码
		if(user.getPassword()!=null && user.getPassword().length()!=56){
			user.setPassword(SystemService.entryptPassword(user.getPassword()));
		}else{
			user.setPassword(null);
		}
		
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		
		if(user.getCompany()==null){
			user.setCompany(new Office(0));
		}
		if(user.getOffice()==null){
			user.setOffice(new Office(0));
		}
		User u = systemService.getByLoginName("system");
		user.setCreateBy(u);
		user.setCreateDate(new Date());
		user.setUpdateBy(u);
		user.setUpdateDate(new Date());
		if(user.getId()==null){
			User user2 = systemService.getByLoginName(user.getLoginName());
			if(user2!=null){
				addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
				return;
			}
		}else{
			User user2 = systemService.getUserById(user.getId());//获取旧登录名
			User user3 = systemService.getByLoginName(user.getLoginName());//获取已存在的登录名
			if(!user2.getLoginName().equals(user.getLoginName())&&user3!=null){
				addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
				return;
			}
			
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<Integer> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		
		Integer userType = Integer.valueOf(user.getUserType());
		
		//平台管理员
		if(userType==0){
			user.setIsAdmin(2);
			roleList=new ArrayList<Role>();
			roleList.add(new Role(1));
			user.setRoleList(roleList);
		}
		//机构管理员
		if(userType==1){
			user.setIsAdmin(1);
			roleList=new ArrayList<Role>();
			roleList.add(new Role(2));
			user.setRoleList(roleList);
		}
		//咨询师
		if(userType==2){
			user.setIsAdmin(3);
			roleList=new ArrayList<Role>();
			roleList.add(new Role(3));
			user.setRoleList(roleList);
		}
		//家长
		if(userType==3){
			user.setIsAdmin(3);
			roleList=new ArrayList<Role>();
			roleList.add(new Role(4));
			user.setRoleList(roleList);
		}
		//来访者
		if(userType==4){
			user.setIsAdmin(3);
			roleList=new ArrayList<Role>();
			roleList.add(new Role(5));
			user.setRoleList(roleList);
		}
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
//		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		addMessage(redirectAttributes, "注册成功!");
	}
	
}