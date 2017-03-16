package com.frame.sys.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.common.utils.SpringContextHolder;
import com.frame.sys.dao.ModuleDao;
import com.frame.sys.dao.ModulePermissionsDao;
import com.frame.sys.dao.RoleDao;
import com.frame.sys.dao.RolePermissionsDao;
import com.frame.sys.dao.UserDao;
import com.frame.sys.entity.Module;
import com.frame.sys.entity.ModulePermissions;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.RolePermissions;
import com.frame.sys.entity.User;
import com.frame.sys.security.SystemAuthorizingRealm.Principal;
import com.google.common.collect.Maps;

/**
 * 用户工具类
 * @author 
 * @version 
 */
public class UserUtils extends BaseService {
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static ModuleDao moduleDao = SpringContextHolder.getBean(ModuleDao.class);
	private static ModulePermissionsDao modulePermissionsDao = SpringContextHolder.getBean(ModulePermissionsDao.class);
//	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
//	private static RolePermissionsDao rolePermissionsDao = SpringContextHolder.getBean(RolePermissionsDao.class);

	public static final String CACHE_USER = "user";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	
	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userDao.get(principal.getId());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}
	
	public static HashSet<String>  getUserPermissions(){
		User user = userDao.get(getUser().getId());
		HashSet<String> set=new HashSet<String>();
		if(user.isAdmin()){
			List<Module> modules=moduleDao.findAll();
			for(Module m:modules){
				set.add("moduleview:"+m.getId());
			}
			List<ModulePermissions> mps=modulePermissionsDao.findAll();
			for(ModulePermissions mp:mps){
				set.add(mp.getPermissionskey());
			}
		}else{
			List<Role> roles=user.getRoleList();
			for(Role role:roles){
				List<RolePermissions> pms=role.getPermissList();
				for(RolePermissions pm:pms){
					if(pm.getPtype().equals("moduleview")){
						set.add("moduleview:"+pm.getMid());
					}else{
						set.add(pm.getPkey());
					}
				}
			}
		}
//		for(Object o:set.toArray()){
//			list.add(o.toString());
//		}
		return set;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}

	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
