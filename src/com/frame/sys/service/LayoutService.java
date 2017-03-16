package com.frame.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.LayoutDao;
import com.frame.sys.entity.Layout;
@Service
@Transactional(readOnly = true)
public class LayoutService extends BaseService<Layout> {
	@Autowired
	private LayoutDao layoutDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return layoutDao;
	}
}
