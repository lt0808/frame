package com.frame.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
import org.hibernate.annotations.Where;

import com.frame.common.basis.IdEntity;
import com.google.common.collect.Lists;


@Entity
@Table(name = "sys_role")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity<Role> {
	private static final long serialVersionUID = 1L;
	private String rolename;//角色名称
	private Integer sortid;//排序
	
	private List<RolePermissions> permissList = Lists.newArrayList(); // 权限
	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	
    public Role() {
		super();
	}
    public Role(String id) {
		this();
		this.id=id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="role")
	@OrderBy(value="id")
	@NotFound(action = NotFoundAction.IGNORE)
	public List<RolePermissions> getPermissList() {
		return permissList;
	}
	public void setPermissList(List<RolePermissions> permissList) {
		this.permissList = permissList;
	}
	
	@ManyToMany(mappedBy = "roleList", fetch=FetchType.LAZY)
	@OrderBy("id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
    
}
