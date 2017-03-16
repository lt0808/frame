package com.frame.web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.frame.common.basis.IdEntity;
import com.frame.common.utils.StringUtil;
import com.frame.sys.entity.Depart;
import com.google.common.collect.Lists;

@Entity
@Table(name = "web_title")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Title extends IdEntity<Title>{
	private static final long serialVersionUID = 1L;
	private Title parent;
	private String titlename;
	private String titlememo;
	private Integer sortid;//排序
	private List<Title> childList = Lists.newArrayList();// 拥有子栏目列表
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parentid")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Title getParent() {
		return parent;
	}
	public void setParent(Title parent) {
		this.parent = parent;
	}
	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	@Length(max=2000)
	public String getTitlememo() {
		return titlememo;
	}
	public void setTitlememo(String titlememo) {
		this.titlememo = titlememo;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	
	@OneToMany(mappedBy = "parent", fetch=FetchType.LAZY)
	@OrderBy(value="sortid") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Title> getChildList() {
		return childList;
	}
	public void setChildList(List<Title> childList) {
		this.childList = childList;
	}
	
	@Transient
	public static String getSubTitleIds(List<Title> sourcelist, String parentId){
		String ids="";
		for (int i=0; i<sourcelist.size(); i++){
			Title e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				if(StringUtil.isEmpty(ids)){
					ids=e.getId();
				}else{
					ids+=","+e.getId();
				}
				ids+=StringUtil.TrimEnd(","+getSubTitleIds(sourcelist,e.getId()),",");
			}
		}
		return StringUtil.TrimEnd(ids, ",");
	}
	
}
