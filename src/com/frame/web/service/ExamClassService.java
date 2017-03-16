package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamClassDao;
import com.frame.web.entity.ExamClass;
@Service
@Transactional(readOnly = true)
public class ExamClassService extends BaseService<ExamClass> {
	@Autowired
	private ExamClassDao examClassDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examClassDao;
	}
}
