/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 音乐Entity
 * @author harry
 * @version 2017-06-25
 */
public class Music extends DataEntity<Music> {
	
	public enum AudioType {

		/**
		 * mpeg
		 */
		MPEG("mpeg", 0),
		/**
		 * ogg
		 */
		OGG("ogg", 1),
		/**
		 * wav
		 */
		WAV("wav", 1),
		/**
		 * mp3
		 */
		MP3("mp3", 1);
	
		private Integer val;
		
		private String name;
		
		AudioType(String name, Integer val){
			this.name = name;
			this.val = val;
		}
		
		public String type(){
			return this.name;
		}
		
		public Integer val(){
			return this.val;
		}
		
	}
	
	private static final long serialVersionUID = 1L;
	private String name;		// 音乐名称
	private Integer musicCatalogId;		// 音乐分类
	private String musicCatalogName;		// 音乐分类名称
	private String musicCatalogParentids;		// 音乐上级分类 ids 
	private String singer;					//演唱者
	private String isPublic;		// 是否公开
	private String path;		// 音乐地址
	private String intro;		// 描述
	private String orgId;		// 分校id
	private String audioType; //音乐类型
	
	public Music() {
		super();
	}

	public Music(Integer id){
		super(id);
	}

	@Length(min=0, max=32, message="音乐名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//@Length(min=0, max=11, message="音乐分类长度必须介于 0 和 11 之间")
	public Integer getMusicCatalogId() {
		return musicCatalogId;
	}

	public void setMusicCatalogId(Integer musicCatalogId) {
		this.musicCatalogId = musicCatalogId;
	}
	
	@Length(min=0, max=32, message="音乐分类名称长度必须介于 0 和 32 之间")
	public String getMusicCatalogName() {
		return musicCatalogName;
	}

	public void setMusicCatalogName(String musicCatalogName) {
		this.musicCatalogName = musicCatalogName;
	}
	
	@Length(min=0, max=1, message="是否公开长度必须介于 0 和 1 之间")
	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	
	@Length(min=0, max=64, message="音乐地址长度必须介于 0 和 64 之间")
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

	public String getMusicCatalogParentids() {
		return musicCatalogParentids;
	}

	public void setMusicCatalogParentids(String musicCatalogParentids) {
		this.musicCatalogParentids = musicCatalogParentids;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAudioType() {
		return audioType;
	}

	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}

}