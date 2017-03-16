package com.frame.sys.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frame.common.basis.IdEntity;


@Entity
@Table(name = "sys_role_permissions")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RolePermissions extends IdEntity<RolePermissions> {
	private static final long serialVersionUID = 1L;
	private String mid;//模块权限的id，模块可见的权限用模块的id
	private String pkey;//权限值，模块可见权限用 moduleview
	private String ptype;//权限类型，模块可见权限为moduleview,正常权限permiss
	private Role role;//所属角色
	
	public RolePermissions(){
		super();
	}
	public RolePermissions(String id){
		this();
		this.id=id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	@ManyToOne
	@JoinColumn(name="roleid")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="所属角色不能为空")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
