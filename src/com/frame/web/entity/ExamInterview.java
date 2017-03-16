package com.frame.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "web_exam_interview")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamInterview implements Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private Long id;
	private String username;//姓名
	private String sfz;//身份证号
	private String telephone;
//报考类别Id	
	private String bmType;//bmType 
//报考类别名称
	private String bmName;//bmName 
	private String bmDw;//报考单位
	private String bmGw;//报考岗位
	private String zkzh;//准考证号
	private String interviewType;//面试类别
	private String kaochang;//考场
	private String baodaotime;//面试报到时间
	private String interviewTime;//面试时间
	private String interviewPlace;//面试时间
	private Integer printStatus;//	打印状态
	private String addtime;//报名时间
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Length(max=50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Length(max=50)
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	@Length(max=200)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Length(max=50)
	public String getBmType() {
		return bmType;
	}
	public void setBmType(String bmType) {
		this.bmType = bmType;
	}
	@Length(max=100)
	public String getBmName() {
		return bmName;
	}
	public void setBmName(String bmName) {
		this.bmName = bmName;
	}
	@Length(max=50)
	public String getBmDw() {
		return bmDw;
	}
	public void setBmDw(String bmDw) {
		this.bmDw = bmDw;
	}
	@Length(max=50)
	public String getBmGw() {
		return bmGw;
	}
	public void setBmGw(String bmGw) {
		this.bmGw = bmGw;
	}
	@Length(max=50)
	public String getZkzh() {
		return zkzh;
	}
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	@Length(max=200)
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}
	@Length(max=50)
	public String getKaochang() {
		return kaochang;
	}
	public void setKaochang(String kaochang) {
		this.kaochang = kaochang;
	}
	@Length(max=50)
	public String getBaodaotime() {
		return baodaotime;
	}
	public void setBaodaotime(String baodaotime) {
		this.baodaotime = baodaotime;
	}
	@Length(max=50)
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	@Length(max=50)
	public String getInterviewPlace() {
		return interviewPlace;
	}
	public void setInterviewPlace(String interviewPlace) {
		this.interviewPlace = interviewPlace;
	}
	public Integer getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(Integer printStatus) {
		this.printStatus = printStatus;
	}
	@Length(max=50)
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
}
