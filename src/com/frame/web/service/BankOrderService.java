package com.frame.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.web.dao.BankOrderDao;
import com.frame.web.entity.BankOrder;
@Service
@Transactional(readOnly = true)
public class BankOrderService extends BaseService<BankOrder> {
	@Autowired
	private BankOrderDao bankOrderDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return bankOrderDao;
	}
}
