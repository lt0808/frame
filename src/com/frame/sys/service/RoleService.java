package com.frame.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.RoleDao;
import com.frame.sys.entity.Role;

@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleDao roleDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return roleDao;
	}
	
}
