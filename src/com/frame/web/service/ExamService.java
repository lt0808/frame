package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamDao;
import com.frame.web.entity.Exam;
@Service
@Transactional(readOnly = true)
public class ExamService extends BaseService<Exam> {
	@Autowired
	private ExamDao examDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examDao;
	}
}
