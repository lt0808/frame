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
@Table(name = "sys_module_permissions")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModulePermissions extends IdEntity<ModulePermissions> {
	private static final long serialVersionUID = 1L;
	private String permissionskey;//权限键值
	private String permissionsdesc;//权限描述
	private Module module;//所属模块
	
	public ModulePermissions(){
		super();
	}
	public ModulePermissions(String id){
		this();
		this.id=id;
	}
	
	public String getPermissionskey() {
		return permissionskey;
	}
	public void setPermissionskey(String permissionskey) {
		this.permissionskey = permissionskey;
	}
	public String getPermissionsdesc() {
		return permissionsdesc;
	}
	public void setPermissionsdesc(String permissionsdesc) {
		this.permissionsdesc = permissionsdesc;
	}
	
	@ManyToOne
	@JoinColumn(name="moduleid")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="所属模块不能为空")
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	
}
