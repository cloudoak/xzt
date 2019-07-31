/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ambition.agile.common.entity.Node;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.common.web.BaseController;
import com.ambition.agile.modules.counsel.constant.RecordMonitorConstant;
import com.ambition.agile.modules.counsel.entity.CounselRecord;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.RecordMonitor;
import com.ambition.agile.modules.counsel.service.CounselRecordService;
import com.ambition.agile.modules.counsel.service.CounselorService;
import com.ambition.agile.modules.counsel.service.RecordMonitorService;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.utils.UserUtils;
import com.google.gson.Gson;

/**
 * 咨询记录Controller
 * @author harry
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/counsel/counselRecord")
public class CounselRecordController extends BaseController {

	@Autowired
	private CounselRecordService counselRecordService;
	
	@Autowired
	private RecordMonitorService recordMonitorService;
	
	@Autowired
	private CounselorService counselorService;
	
	@ModelAttribute
	public CounselRecord get(@RequestParam(required=false) Integer id) {
		CounselRecord entity = null;
		if (null != id && id>0){
			entity = counselRecordService.get(id);
		}
		if (entity == null){
			entity = new CounselRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("counsel:counselRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(CounselRecord counselRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CounselRecord> page = counselRecordService.findPage(new Page<CounselRecord>(request, response), counselRecord); 
		model.addAttribute("page", page);
		return "modules/counsel/counselRecordList";
	}

	@RequiresPermissions("counsel:counselRecord:view")
	@RequestMapping(value = "form")
	public String form(CounselRecord counselRecord, Model model) {
		
		
		//获取本校的所有的咨询师
		Counselor  counselor = new Counselor();
		//counselor.setApplyStatus(CounselorConstant.APPLY_STATUS_NOSUBMIT);
		User user = UserUtils.getUser(); 
		if(null != user && null != user.getOffice() && null != user.getOffice().getId()){
			Integer orgId = user.getOffice().getId();
			Office org = new Office();
			org.setId(orgId);
			counselor.setOrgId(orgId);
		}
		List<Counselor> counselorList = counselorService.findList(counselor);	
		String counselors = "";
		//用于页面的树的显示
	    List listTemp = new ArrayList();
	    Node node = new Node();
	    
	    node.setId(0);
	    node.setName("全部");
	    //node.setChecked(true);
	    node.setParent("-1");
	    node.setOpen(true);
	    listTemp.add(node);
	    
	    if(null != counselorList  && counselorList.size()>0){
	    	
	    	//获取所有的本 咨询记录已经分配的咨询师
			List rmlist = null;
			if(null != counselRecord || null != counselRecord.getId()
					|| counselRecord.getId() > 0 ){
				RecordMonitor recordMonitor = new RecordMonitor();
				recordMonitor.setRecordId(counselRecord.getId());
				rmlist = recordMonitorService.findList(recordMonitor);
			}
	    	
	    	
		    for (int i = 0; i < counselorList.size(); i++) {
		    	Counselor counselorTemp = (Counselor)counselorList.get(i);
		    	Node nodeTemp = new Node();
		    	//if(null != user && null != user.getId() && user.getId()>0){
		    		
		    		if(null != rmlist  && rmlist.size()>0 ){
		    		
			    		for(int t=0;t<rmlist.size();t++){
			    			RecordMonitor recordMonitorTemp = (RecordMonitor)rmlist.get(t);
				    		if(null != counselorTemp &&  null != recordMonitorTemp && 
				    		counselorTemp.getId() == recordMonitorTemp.getRecordId()
				    		){
				    			nodeTemp.setChecked(true);
				    			counselors += counselorTemp.getId() +",";
				    			break;
				    		}
				    	}
		    		}
		    	//}
		    	nodeTemp.setId(counselorTemp.getId());
		    	nodeTemp.setName(user.getName());
		    	nodeTemp.setParent("0");
		        listTemp.add(nodeTemp);
		    }
	    }
		Gson gson = new Gson();
        //Gson gson = new GsonBuilder().create();
		String counselorStr = gson.toJson(listTemp);
		System.out.println("####################################counselorStr :"+counselorStr);
		System.out.println("####################################counselors :"+counselors);
		model.addAttribute("counselorStr", counselorStr);
		model.addAttribute("counselors", counselors);
		model.addAttribute("counselRecord", counselRecord);
		return "modules/counsel/counselRecordForm";
	}

	@RequiresPermissions("counsel:counselRecord:edit")
	@RequestMapping(value = "save")
	public String save(CounselRecord counselRecord,String counselors, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, counselRecord)){
			return form(counselRecord, model);
		}
		
		counselRecordService.save(counselRecord);
		
		if(null != counselRecord && counselRecord.getId()>0){ 
			RecordMonitor rmTemp = new RecordMonitor();
			rmTemp.setRecordId(counselRecord.getId());
			recordMonitorService.deleteByRecord(rmTemp);
		}
		
		if(StringUtils.isNotEmpty(counselors  )){
			String[] counselorArray = counselors.split(",");
			if(null != counselorArray && counselorArray.length>0){
				for(int i=0;i<counselorArray.length;i++){
					if(null == counselorArray[i] || counselorArray[i].equals("null")){
						continue;
					}
					if(StringUtils.isNotEmpty(counselorArray[i]) ){
						Integer id = Integer.parseInt(counselorArray[i]);
						
						if( id >0 ){
							RecordMonitor rm = new RecordMonitor();
							Counselor c = counselorService.get(id);
							User user = UserUtils.getUser(); 
							if(null != c && c.getId()>0){
								rm.setCounselorName(user.getName());
							}
							rm.setCounselorId(id);
							
							if(null != user && user.getId()>0){
								rm.setCreatorId(user.getId());
								rm.setCreateDate(new Date());
							}
							rm.setMonitorStatus(RecordMonitorConstant.APPLY);
							rm.setRecordId(counselRecord.getId());
							recordMonitorService.save(rm);
						}
					}
				}
			}
		}
		
		addMessage(redirectAttributes, "保存咨询记录成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselRecord/?repage";
	}
	
	@RequiresPermissions("counsel:counselRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(CounselRecord counselRecord, RedirectAttributes redirectAttributes) {
		counselRecordService.delete(counselRecord);
		addMessage(redirectAttributes, "删除咨询记录成功");
		return "redirect:"+Global.getAdminPath()+"/counsel/counselRecord/?repage";
	}

}