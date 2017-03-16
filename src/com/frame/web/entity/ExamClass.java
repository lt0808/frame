package com.frame.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.frame.common.basis.IdEntity;
import com.frame.sys.entity.User;
import com.google.common.collect.Lists;

@Entity
@Table(name = "web_exam_class")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamClass extends IdEntity<ExamClass>{
	private String examname;//考试名称
	private Integer ispay;//是否缴费
	private Double amount;//缴费金额
	private Integer status;//状态 1开启 0不开启
	private Integer signstatus;//报名功能 1开启首次报名 2关闭首次报名 0关闭
	private Integer paystatus;//缴费功能 1开启 0关闭
	private Integer printstatus;//打印功能 1开启 0关闭
	private List<ExamUnit> unitList = Lists.newArrayList(); // 招聘单位
	private List<ExamPost> postList = Lists.newArrayList(); // 招聘岗位
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public Integer getIspay() {
		return ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
	@Column(columnDefinition="double(10,2) default '0.00'")  
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSignstatus() {
		return signstatus;
	}
	public void setSignstatus(Integer signstatus) {
		this.signstatus = signstatus;
	}
	public Integer getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	public Integer getPrintstatus() {
		return printstatus;
	}
	public void setPrintstatus(Integer printstatus) {
		this.printstatus = printstatus;
	}
	@OneToMany(mappedBy = "examclass", fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<ExamUnit> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<ExamUnit> unitList) {
		this.unitList = unitList;
	}
	@OneToMany(mappedBy = "examclass", fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<ExamPost> getPostList() {
		return postList;
	}
	public void setPostList(List<ExamPost> postList) {
		this.postList = postList;
	}


	
}
