package com.ambition.agile.modules.jzmk.entity;

import com.ambition.agile.common.persistence.DataEntity;

public class Sections extends DataEntity<Sections> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8253419568880661543L;
	
	private String name; 
	
	private String groupSection;
	
	private String section;
	
	public Sections() {
		
	}
	
	public Sections(String name, String groupSection, String section) {
		super();
		this.name = name;
		this.groupSection = groupSection;
		this.section = section;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupSection() {
		return groupSection;
	}

	public void setGroupSection(String groupSection) {
		this.groupSection = groupSection;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
}
