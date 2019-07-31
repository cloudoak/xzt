package com.ambition.agile.modules.sys.entity;

public class ThirdParty implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8506953025940469045L;

	private int id;
	
	private String code;
	
	private String name;
	
	public ThirdParty() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
