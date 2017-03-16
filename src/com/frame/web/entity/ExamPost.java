package com.frame.web.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frame.common.basis.IdEntity;
@Entity
@Table(name = "web_exam_post")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamPost extends IdEntity<ExamPost> {
	private String postname;//岗位名称
	private String postcode;//岗位代码
	private Integer isshow;//是否显示
	private String postlevel;//岗位等级
	private Integer personnum;//招聘人数
	private String writename;//笔试类别
	private String writecode;//笔试代码
    private ExamClass examclass;//所属考试
    private ExamUnit examunit;//所属单位
    
    @Length(max=50)
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	@Length(max=50)
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Integer getIsshow() {
		return isshow;
	}
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}
	@Length(max=50)
	public String getPostlevel() {
		return postlevel;
	}
	public void setPostlevel(String postlevel) {
		this.postlevel = postlevel;
	}
	public Integer getPersonnum() {
		return personnum;
	}
	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}
	@Length(max=50)
	public String getWritename() {
		return writename;
	}
	public void setWritename(String writename) {
		this.writename = writename;
	}
	@Length(max=50)
	public String getWritecode() {
		return writecode;
	}
	public void setWritecode(String writecode) {
		this.writecode = writecode;
	}
    @ManyToOne
    @JoinColumn(name = "examclassid")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
	public ExamClass getExamclass() {
		return examclass;
	}
	public void setExamclass(ExamClass examclass) {
		this.examclass = examclass;
	}
    @ManyToOne
    @JoinColumn(name = "examunitid")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
	public ExamUnit getExamunit() {
		return examunit;
	}
	public void setExamunit(ExamUnit examunit) {
		this.examunit = examunit;
	}
    
}
