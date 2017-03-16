package com.frame.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.common.basis.IdEntity;

@Entity
@Table(name = "web_info")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Info extends IdEntity<Info> {
	private String typeid;//所属类别
	private String typename;//所属类别
	private String title;//标题
	private String title2;//标题2
	private String content;//内容
	private String picturepath;//图片路径
	private Integer clicknum;//点击数
	private String auther;//作者
	private String source;//来源
	private Integer topstaus;//是否置顶
	private Date recdate;//发布日期
	private String isshow;//是否发布
	
	@Length(max=50)
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	@Length(max=200)
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Length(max=300)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Length(max=300)
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Length(max=500)
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public Integer getClicknum() {
		return clicknum;
	}
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	@Length(max=100)
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	@Length(max=300)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getTopstaus() {
		return topstaus;
	}
	public void setTopstaus(Integer topstaus) {
		this.topstaus = topstaus;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRecdate() {
		return recdate;
	}
	public void setRecdate(Date recdate) {
		this.recdate = recdate;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
}
