/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 课件Entity
 * @author harry
 * @version 2017-06-24
 */
public class Course extends DataEntity<Course> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 课件名称
	private Integer courseCatalogId;		// 课件分类id
	private String courseCatalogParentids; //分类 parentids
	private String courseCatalogName;		// 课件分类名称
	private String singer;		//演唱者
	private String isPublic;		// 是否公开
	private String path;		// 课件地址
	private String intro;		// 描述
	private String orgId;		// 分校id
	private Integer owner;
	private CommonsMultipartFile file;		// 文件
	
	public Course() {
		super();
	}

	public Course(Integer id){
		super(id);
	}

	@Length(min=0, max=32, message="课件名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCourseCatalogId() {
		return courseCatalogId;
	}

	public void setCourseCatalogId(Integer courseCatalogId) {
		this.courseCatalogId = courseCatalogId;
	}
	
	@Length(min=0, max=32, message="课件分类名称长度必须介于 0 和 32 之间")
	public String getCourseCatalogName() {
		return courseCatalogName;
	}

	public void setCourseCatalogName(String courseCatalogName) {
		this.courseCatalogName = courseCatalogName;
	}
	
	@Length(min=0, max=1, message="是否公开长度必须介于 0 和 1 之间")
	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	
	@Length(min=0, max=64, message="课件地址长度必须介于 0 和 64 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=128, message="描述长度必须介于 0 和 128 之间")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Length(min=0, max=11, message="分校id长度必须介于 0 和 11 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getCourseCatalogParentids() {
		return courseCatalogParentids;
	}

	public void setCourseCatalogParentids(String courseCatalogParentids) {
		this.courseCatalogParentids = courseCatalogParentids;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

}