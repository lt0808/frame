package com.frame.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.UserDao;
import com.frame.sys.entity.Depart;
import com.frame.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<User>{
	@Autowired
	private UserDao userDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}
	

}
