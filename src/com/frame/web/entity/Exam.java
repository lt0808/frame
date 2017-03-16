package com.frame.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.common.basis.IdEntity;
@Entity
@Table(name = "web_exam")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Exam implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;//姓名
	private String sex;//性别
	private String sfz;//身份证
	private Date birthdate;//出生日期
	private String zhengzhi;//政治面貌
	private String minzu;//民族
	private String syd;//生源地
	private String address;//户口所在地
	private String zgzs;//资格证书
	private String school;//毕业院校
	private String zy;//所学专业
	private String examid;//考试id
	private String examname;//考试名称
	private String unitid;//报考单位
	private String unitcode;//单位代码
	private String unitname;//单位名称
	private String postid;//报考岗位
	private String postcode;//岗位代码
	private String postname;//岗位名称
	private String wirtename;//笔试类别
	private String wirtecode;//笔试代码
	private String wy;//外语水平
	private String computer;//计算机水平
	private String tel;//联系电话
	private String phone;//手机
	private String address2;//通讯地址
	private String email;//email
	private String graddate;//毕业时间
	private String jobdate;//工作时间
	private String xw;//学位
	private String xl;//学历
	private String dw;//工作单位
	private String jiaoyubeijing;//教育背景
	private String gongzuojingli;//工作经历
	private Integer info_print;//信息表打印 1打印 0未打印
	private Integer promise_print;//承诺书打印 1打印 0未打印
	private Integer zkz_print;//准考证打印 1打印 0未打印
	private Integer isjf;//是否缴费 1已缴费 0未交费
	private String status;//状态  no 表示未提交 yes表示提交 agree表示审核通过 disagree表示审核未通过
	private String shr;//审核人
	private String shrname;//审核人名称
	private Date shrdate;//审核时间
	private String zkzh;//准考证号
	private String kc;//考场
	private String zh;//座号
	private String examdate;//考试时间
	private String examaddress;//考试地点
	private String picpath;//图片地址
	private String isreport;//是否重报  yes重报  no不能重报
	private Date createDate;//产生时间
	private String remarks;//备注
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment") 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Length(max=20)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Length(max=10)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Length(max=50)
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	@Length(max=50)
	public String getZhengzhi() {
		return zhengzhi;
	}
	public void setZhengzhi(String zhengzhi) {
		this.zhengzhi = zhengzhi;
	}
	@Length(max=20)
	public String getMinzu() {
		return minzu;
	}
	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}
	@Length(max=50)
	public String getSyd() {
		return syd;
	}
	public void setSyd(String syd) {
		this.syd = syd;
	}
	@Length(max=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Length(max=200)
	public String getZgzs() {
		return zgzs;
	}
	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Length(max=50)
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	@Length(max=50)
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	@Length(max=50)
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	@Length(max=50)
	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	@Length(max=50)
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	@Length(max=50)
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public String getWirtename() {
		return wirtename;
	}
	public void setWirtename(String wirtename) {
		this.wirtename = wirtename;
	}
	@Length(max=50)
	public String getWirtecode() {
		return wirtecode;
	}
	public void setWirtecode(String wirtecode) {
		this.wirtecode = wirtecode;
	}
	public String getWy() {
		return wy;
	}
	public void setWy(String wy) {
		this.wy = wy;
	}
	public String getComputer() {
		return computer;
	}
	public void setComputer(String computer) {
		this.computer = computer;
	}
	@Length(max=100)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Length(max=100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Length(max=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Length(max=50)
	public String getGraddate() {
		return graddate;
	}
	public void setGraddate(String graddate) {
		this.graddate = graddate;
	}
	@Length(max=50)
	public String getJobdate() {
		return jobdate;
	}
	public void setJobdate(String jobdate) {
		this.jobdate = jobdate;
	}
	@Length(max=50)
	public String getXw() {
		return xw;
	}
	public void setXw(String xw) {
		this.xw = xw;
	}
	@Length(max=50)
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	@Length(max=2000)
	public String getJiaoyubeijing() {
		return jiaoyubeijing;
	}
	public void setJiaoyubeijing(String jiaoyubeijing) {
		this.jiaoyubeijing = jiaoyubeijing;
	}
	@Length(max=2000)
	public String getGongzuojingli() {
		return gongzuojingli;
	}
	public void setGongzuojingli(String gongzuojingli) {
		this.gongzuojingli = gongzuojingli;
	}
	public Integer getInfo_print() {
		return info_print;
	}
	public void setInfo_print(Integer info_print) {
		this.info_print = info_print;
	}
	public Integer getPromise_print() {
		return promise_print;
	}
	public void setPromise_print(Integer promise_print) {
		this.promise_print = promise_print;
	}
	public Integer getZkz_print() {
		return zkz_print;
	}
	public void setZkz_print(Integer zkz_print) {
		this.zkz_print = zkz_print;
	}
	public Integer getIsjf() {
		return isjf;
	}
	public void setIsjf(Integer isjf) {
		this.isjf = isjf;
	}
	@Length(max=50)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Length(max=50)
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	@Length(max=50)
	public String getShrname() {
		return shrname;
	}
	public void setShrname(String shrname) {
		this.shrname = shrname;
	}
	public Date getShrdate() {
		return shrdate;
	}
	public void setShrdate(Date shrdate) {
		this.shrdate = shrdate;
	}
	@Length(max=50)
	public String getZkzh() {
		return zkzh;
	}
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	@Length(max=50)
	public String getKc() {
		return kc;
	}
	public void setKc(String kc) {
		this.kc = kc;
	}
	@Length(max=20)
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	@Length(max=200)
	public String getExamdate() {
		return examdate;
	}
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	@Length(max=500)
	public String getExamaddress() {
		return examaddress;
	}
	public void setExamaddress(String examaddress) {
		this.examaddress = examaddress;
	}
	@Length(max=500)
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	@Length(max=20)
	public String getIsreport() {
		return isreport;
	}
	public void setIsreport(String isreport) {
		this.isreport = isreport;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Length(max=200)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	
	
	
}
