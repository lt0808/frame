package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamUnitUserDao;
import com.frame.web.entity.ExamUnitUser;
@Service
@Transactional(readOnly = true)
public class ExamUnitUserService extends BaseService<ExamUnitUser> {
	@Autowired
	private ExamUnitUserDao examUnitUserDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examUnitUserDao;
	}
}
