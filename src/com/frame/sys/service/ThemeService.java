package com.frame.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.dao.BaseDao;
import com.frame.common.service.BaseService;
import com.frame.sys.dao.ThemeDao;
import com.frame.sys.entity.Theme;
@Service
@Transactional(readOnly = true)
public class ThemeService extends BaseService<Theme> {
	@Autowired
	private ThemeDao themeDao;
	@Override
	public BaseDao getDao() {
		// TODO Auto-generated method stub
		return themeDao;
	}
	
	public Theme getThemeByUserID(String userid){
		List<Theme> themes=themeDao.find("from Theme where userid=?", userid);
		return themes.size()>0?themes.get(0):new Theme();
	}
}
