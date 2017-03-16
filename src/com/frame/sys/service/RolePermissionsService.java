package com.frame.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.RolePermissionsDao;
import com.frame.sys.entity.RolePermissions;

@Service
@Transactional(readOnly = true)
public class RolePermissionsService extends BaseService<RolePermissions> {

	@Autowired
	private RolePermissionsDao rolePermissionsDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return rolePermissionsDao;
	}

}
