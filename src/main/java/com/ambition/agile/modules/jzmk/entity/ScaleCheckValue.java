/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 测评评分Entity
 * @author wyz
 * @version 2017-12-03
 */
public class ScaleCheckValue extends DataEntity<ScaleCheckValue> {
	
	private static final long serialVersionUID = 1L;
	private Integer vid;		// vid
	private Double startValue;		// start_value
	private Double endValue;		// end_value
	private String explain;		// explain
	private Integer gid;		// gid
	private Integer tid;		// tid
	
	public ScaleCheckValue() {
		super();
	}

	public ScaleCheckValue(Integer id){
		super(id);
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}
	
	public Double getStartValue() {
		return startValue;
	}

	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	
	public Double getEndValue() {
		return endValue;
	}

	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	}
	
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
}