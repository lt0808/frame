package com.frame.sys.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.common.utils.Digests;
import com.frame.common.utils.Encodes;
import com.frame.sys.dao.UserDao;
import com.frame.sys.entity.User;
import com.frame.sys.security.SystemAuthorizingRealm;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author 
 * @version 
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private SystemAuthorizingRealm systemRealm;
	


	//-- User Service --//
	
	public User getUser(String id) {
		return userDao.get(id);
	}
	public List<User> findAllUserList(){
		return userDao.find("from User");
	}
	

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
