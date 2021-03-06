package com.frame.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.DepartDao;
import com.frame.sys.entity.Depart;

@Service
@Transactional(readOnly = true)
public class DepartService extends BaseService<Depart> {
	@Autowired
	private DepartDao departDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return departDao;
	}
}
