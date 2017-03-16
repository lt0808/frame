package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamInterviewDao;
import com.frame.web.entity.ExamInterview;
@Service
@Transactional(readOnly = true)
public class ExamInterviewService extends BaseService<ExamInterview> {
	@Autowired
	private ExamInterviewDao examInterviewDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examInterviewDao;
	}
}
