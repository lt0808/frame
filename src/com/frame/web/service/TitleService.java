package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.TitleDao;
import com.frame.web.entity.Title;
@Service
@Transactional(readOnly = true)
public class TitleService extends BaseService<Title> {
	@Autowired
	private TitleDao titleDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return titleDao;
	}
}
