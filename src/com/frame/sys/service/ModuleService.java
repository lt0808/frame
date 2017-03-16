package com.frame.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.ModuleDao;
import com.frame.sys.entity.Module;

@Service
@Transactional(readOnly = true)
public class ModuleService extends BaseService<Module> {
    @Autowired
    private ModuleDao moduleDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return moduleDao;
	}
	
}
