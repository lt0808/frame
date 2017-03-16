package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.ExamUnitDao;
import com.frame.web.entity.ExamUnit;
@Service
@Transactional(readOnly = true)
public class ExamUnitService extends BaseService<ExamUnit> {
	@Autowired
	private ExamUnitDao examUnitDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return examUnitDao;
	}
}
