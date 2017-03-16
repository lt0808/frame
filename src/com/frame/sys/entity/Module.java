package com.frame.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.frame.common.basis.IdEntity;
import com.google.common.collect.Lists;


@Entity
@Table(name = "sys_module")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Module extends IdEntity<Module> {
	private static final long serialVersionUID = 1L;
	private Module parent;
	private String modulename;//模块名称
	private String urllink;//连接页面
	private String urltarget;//连接目标
	private String opentab;//打开类型  refresh 刷新，noRefresh 无刷新
	private Integer isvalid;//是否有效
	private Integer sortid;//排序
	
	private List<Module> childList = Lists.newArrayList();// 拥有子模块
	private List<ModulePermissions> permissionsList = Lists.newArrayList(); // 拥有的权限
	
    public Module() {
		super();
	}
    public Module(String id) {
		this();
		this.id=id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parentid")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	
	@Length(max=200)
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	@Length(max=500)
	public String getUrllink() {
		return urllink;
	}
	public void setUrllink(String urllink) {
		this.urllink = urllink;
	}
	@Length(max=50)
	public String getUrltarget() {
		return urltarget;
	}
	public void setUrltarget(String urltarget) {
		this.urltarget = urltarget;
	}
	@Length(max=50)
	public String getOpentab() {
		return opentab;
	}
	public void setOpentab(String opentab) {
		this.opentab = opentab;
	}
	
	public Integer getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
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
	public List<Module> getChildList() {
		return childList;
	}
	public void setChildList(List<Module> childList) {
		this.childList = childList;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="module")
	@OrderBy(value="id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<ModulePermissions> getPermissionsList() {
		return permissionsList;
	}
	public void setPermissionsList(List<ModulePermissions> permissionsList) {
		this.permissionsList = permissionsList;
	}
	
	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	

	@Transient
	public static boolean isRoot(String id){
		return id != null && id.equals("1");
	}
}
