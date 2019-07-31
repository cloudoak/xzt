/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.entity;


import java.util.List;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 因子和解释Entity
 * @author dortan
 * @version 2017-07-01
 */
public class GeneAndExplain extends DataEntity<GeneAndExplain> {
	
	private static final long serialVersionUID = 5740137405412514662L;
	
	private List<GeneExplain> list;
	private Gene gene;
	public List<GeneExplain> getList() {
		return list;
	}
	public void setList(List<GeneExplain> list) {
		this.list = list;
	}
	public Gene getGene() {
		return gene;
	}
	public void setGene(Gene gene) {
		this.gene = gene;
	}
	
}