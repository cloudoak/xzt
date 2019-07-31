package com.ambition.agile.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ambition.agile.modules.sys.entity.Office;
import com.ambition.agile.modules.sys.entity.User;
import com.ambition.agile.modules.sys.service.OfficeService;
import com.ambition.agile.modules.sys.utils.UserUtils;

/*
 * @author: OAK
 * @see: 
 * @parm: 
 * @date:Sep 3, 2012
 */
@Component
public class OrganizationService {

	@Autowired
	private OfficeService officeService;
	
	public void appendBuffer(StringBuffer buffer, Integer previous) {
		Office off = new Office();
		off.setParentId(previous);
		List<Office> children = officeService.findOrgList(off);
		if(children != null && children.size() > 0) {
			for(Office of : children) {
				buffer.append(of.getId());
				buffer.append(",");
				appendBuffer(buffer, of.getId());
			}
		}
	}
	
	public String getOrganizationJoinString() {
		String organizationJoinString = "";
		Integer id = 0;
		Office off = UserUtils.getOrg();
		if(off != null && off.getId() != null) {
			id = off.getId();
		}
		Office office = officeService.get(id);
		if(office != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(office.getId());
			buffer.append(",");
			appendBuffer(buffer, office.getId());
			if(buffer.length() > 0) {
				organizationJoinString =  buffer.toString();
				organizationJoinString = organizationJoinString.substring(0, organizationJoinString.length() - 1);
			}
		}
		return organizationJoinString;
	}
	
}
