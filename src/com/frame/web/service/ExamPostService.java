package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamPostDao;
import com.frame.web.entity.ExamPost;
@Service
@Transactional(readOnly = true)
public class ExamPostService extends BaseService<ExamPost> {
	@Autowired
	private ExamPostDao examPostDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examPostDao;
	}
}
