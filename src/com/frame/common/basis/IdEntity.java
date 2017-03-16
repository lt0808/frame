package com.frame.common.basis;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.frame.common.utils.IdGen;

/**
 * 数据Entity类
 * @author 
 * @version 
 */
@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;		// 编号
	
	public IdEntity() {
		super();
	}
	
	@PrePersist
	public void prePersist(){
		super.prePersist();
		this.id = IdGen.uuid();
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
