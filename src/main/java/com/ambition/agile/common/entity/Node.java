package com.ambition.agile.common.entity;

import java.io.Serializable;

/**
 * ClassName: Node
 * @Description: 机构
 * @author harry
 * @date 2016-3-31
 */
public class Node implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String code;
	
	private String name;
	
	private String parent;
	//用于  select 的方式
 	protected  boolean checked;
 	
 	protected  boolean open;
	
	public Node(){}
	
	public Node(int id, String name, String parent){
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
}
