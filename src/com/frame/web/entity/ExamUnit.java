package com.frame.web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import com.google.common.collect.Lists;
@Entity
@Table(name = "web_exam_unit")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamUnit extends IdEntity<ExamUnit> {
	private String unitname;//单位名称
	private String unitcode;//单位代码
	private Integer isshow;//是否显示
	private ExamClass examclass;//所属考试类别
	private List<ExamPost> postList = Lists.newArrayList(); // 招聘岗位
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	@Length(max=20)
	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public Integer getIsshow() {
		return isshow;
	}
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
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
	@OneToMany(mappedBy = "examunit", fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<ExamPost> getPostList() {
		return postList;
	}
	public void setPostList(List<ExamPost> postList) {
		this.postList = postList;
	}
	
}
