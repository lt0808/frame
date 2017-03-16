package com.frame.sys.entity;

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
import com.google.common.collect.Lists;


@Entity
@Table(name = "sys_depart")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Depart extends IdEntity<Depart> {
	private static final long serialVersionUID = 1L;
	private Depart parent;
	private String departname;//部门名称
	private String address;//地址
	private String master;//负责人
	private String phone;//电话
	private String fax;//传真
	private Integer sortid;//排序
	
	private List<Depart> childList = Lists.newArrayList();// 拥有子机构列表
	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	
    public Depart() {
		super();
	}
    public Depart(String id) {
		this();
		this.id=id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parentid")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Depart getParent() {
		return parent;
	}
	public void setParent(Depart parent) {
		this.parent = parent;
	}
	@Length(max=200)
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	@Length(max=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Length(max=50)
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	@Length(max=100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Length(max=100)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public List<Depart> getChildList() {
		return childList;
	}
	public void setChildList(List<Depart> childList) {
		this.childList = childList;
	}
	
	@OneToMany(mappedBy = "depart", fetch=FetchType.LAZY)
	@OrderBy(value="sortid") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	@Transient
	public static boolean isRoot(String id){
		return id != null && id.equals("1");
	}
	
	@Transient
	public static String getSubDepartIds(List<Depart> sourcelist, String parentId){
		String ids="";
		for (int i=0; i<sourcelist.size(); i++){
			Depart e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				if(StringUtil.isEmpty(ids)){
					ids=e.getId();
				}else{
					ids+=","+e.getId();
				}
				ids+=StringUtil.TrimEnd(","+getSubDepartIds(sourcelist,e.getId()),",");
			}
		}
		return StringUtil.TrimEnd(ids, ",");
	}
}
