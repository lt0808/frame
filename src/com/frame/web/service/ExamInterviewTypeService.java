package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamInterviewTypeDao;
import com.frame.web.entity.ExamInterviewType;
@Service
@Transactional(readOnly = true)
public class ExamInterviewTypeService extends BaseService<ExamInterviewType> {
	@Autowired
	private ExamInterviewTypeDao examInterviewTypeDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examInterviewTypeDao;
	}
}
