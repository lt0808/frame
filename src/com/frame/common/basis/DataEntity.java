
package com.frame.common.basis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.common.utils.DateUtils;
import com.frame.common.utils.StringUtils;
import com.frame.sys.entity.User;
import com.frame.sys.utils.UserUtils;

/**
 * 数据Entity类
 * @author 
 * @version 
 */
@MappedSuperclass
public abstract class DataEntity<T> extends BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String remarks;	// 备注
	protected String createUserId;//创建者id
	protected String createUserName;//创建者姓名
	protected String createLoginName; //创建者用户名
	protected Date createDate;// 创建日期
	protected String updateUserId;//更新者id
	protected String updateUserName;//更新者姓名
	protected String updateLoginName;//更新者用户名
	protected Date updateDate;// 更新日期
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	@PrePersist
	public void prePersist(){
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.createUserId = user.getId();
		}
        User at = UserUtils.getUser();
        if (at != null) {
            this.createUserId = at.getId();
            this.createUserName = at.getUsername();
            this.createLoginName = at.getLoginname();


            this.updateUserId = at.getId();
            this.updateUserName = at.getUsername();
            this.updateLoginName = at.getLoginname();
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
	}
	
	@PreUpdate
	public void preUpdate(){
        User at = UserUtils.getUser();
        if (at != null) {
            this.updateUserId = at.getId();
            this.updateUserName = at.getUsername();
            this.updateLoginName = at.getLoginname();
        }
        this.updateDate = new Date();
	}
	
	@Length(min=0, max=2000)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Length(min=0, max=1)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	@Column(updatable=false)
    public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	@Column(updatable=false)
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	@Column(updatable=false)
	public String getCreateLoginName() {
		return createLoginName;
	}
	public void setCreateLoginName(String createLoginName) {
		this.createLoginName = createLoginName;
	}

	public String getUpdateLoginName() {
		return updateLoginName;
	}

	public void setUpdateLoginName(String updateLoginName) {
		this.updateLoginName = updateLoginName;
	}

	@Transient
    public void setCreateUser(DataEntity entity){
        this.createUserId = entity.getCreateUserId();
        this.createUserName = entity.getCreateUserName();
        this.createDate = entity.getCreateDate();
    }

    @Transient
    public String getCreateDateStr() {
        if (createDate == null)
            return "";
        return DateUtils.formatDate(createDate, "yyyy-MM-dd");
    }
}
