package com.frame.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.common.dao.BaseDao;
import com.frame.sys.entity.User;

@Repository
public class UserDao extends BaseDao<User> {
	
	public User getUserByLoginName(String loginName){
		List<User> users=find("from User where loginname='"+loginName+"'");
		if(users.size()>0)
			return users.get(0);
		else
			return null;
	}
}
