package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.InfoDao;
import com.frame.web.entity.Info;
@Service
@Transactional(readOnly = true)
public class InfoService extends BaseService<Info> {
	@Autowired
	private InfoDao infoDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return infoDao;
	}
}
