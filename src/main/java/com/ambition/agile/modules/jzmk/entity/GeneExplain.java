/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import com.ambition.agile.common.persistence.DataEntity;

/**
 * 因子解释Entity
 * @author dortan
 * @version 2017-07-01
 */
public class GeneExplain extends DataEntity<GeneExplain> {
	
	private static final long serialVersionUID = 1L;
	private Double minValue;		// 开始值
	private Double maxValue;		// 结束值
	private String geneExplain;		// 解释
	private Integer gid;		// 因子ID
	private Integer tid;		// 量表
	private Integer orgId;		// 机构ID
	private String geneName; //因子名称
	private String pt; //当前解释所占百分比
	
	public GeneExplain() {
		super();
	}

	public GeneExplain(Integer id){
		super(id);
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	
	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	public String getGeneExplain() {
		return geneExplain;
	}

	public void setGeneExplain(String geneExplain) {
		this.geneExplain = geneExplain;
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
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}
	
}