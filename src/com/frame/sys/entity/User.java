package com.frame.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frame.common.basis.IdEntity;
import com.google.common.collect.Lists;

@Entity
@Table(name = "sys_user")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity<User>{
	private static final long serialVersionUID = 1L;
	private String loginname;//登录名
	private String password;//密码
	private String username;//用户名
	private String tel;//电话
	private String mob;//手机
	private String idno;//身份证
	private String address;//住址
	private Boolean isdealline;//是否有时间限制
	private Date startdate;//有效时间 开始
	private Date enddate;//有效时间 结束
	private Boolean canlogin;//能否登录
	private String photopath;//照片路径
	private Integer sortid;//排序
	private Depart depart;//所属部门
	
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表
	
	public User() {
		super();
	}
	
	public User(String id) {
		this();
		this.id = id;
	}
	
	@Length(min = 1, max = 100)
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
    @JsonIgnore
    @Length(min = 1, max = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    @Length(max = 100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Length(max=200)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Length(max=200)
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	@Length(max=50)
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	@Length(max=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsdealline() {
		return isdealline;
	}
	public void setIsdealline(Boolean isdealline) {
		this.isdealline = isdealline;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Boolean getCanlogin() {
		return canlogin;
	}
	public void setCanlogin(Boolean canlogin) {
		this.canlogin = canlogin;
	}
	@Length(max=500)
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	
    @ManyToOne
    @JoinColumn(name = "departid")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "userid")}, inverseJoinColumns = {@JoinColumn(name = "roleid")})
    @OrderBy("id")
    @Fetch(FetchMode.SUBSELECT)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Transient
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	@Transient
	public static boolean isAdmin(String id){
		return id != null && id.equals("1");
	}
	
}
