
package com.frame.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.frame.common.controller.BaseController;
import com.frame.common.utils.CacheUtils;
import com.frame.common.utils.Global;
import com.frame.common.utils.StringUtil;
import com.frame.sys.entity.Theme;
import com.frame.sys.entity.User;
import com.frame.sys.utils.UserUtils;
import com.google.common.collect.Maps;

/**
 * 登录Controller
 * @author 
 * @version 
 */
@Controller
public class LoginController extends BaseController{

	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getId() != null){
			return "redirect:"+Global.getAdminPath();
		}
		return "pages/sys/login";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		return "pages/sys/login";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("index")
	@RequiresUser
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(user.getId() == null){
			return "redirect:"+Global.getAdminPath()+"/login";
		}
//		Theme theme=themetService.getThemeByUserID(user.getId());
//		if(StringUtil.isEmpty(theme.getId())){
//			theme.setTheme("default");
//			theme.setUserid(user.getId());
//			themetService.merge(theme);
//		}
//		CookieUtils.setCookie(response, "theme", theme.getTheme());
		
		// 验证码计算器清零
		isValidateCodeLogin(user.getLoginname(), false, true);
		return "pages/sys/main";
	}
	
	/**
	 * 获取主题方案
	 */
//	@RequestMapping(value = "/theme/{theme}")
//	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
//		if (StringUtils.isNotBlank(theme)){
//			CookieUtils.setCookie(response, "theme", theme);
//		}else{
//			theme = CookieUtils.getCookie(request, "theme");
//		}
//		return "redirect:"+request.getParameter("url");
//	}
	@RequestMapping(value = "${adminPath}/theme")
	public String theme(String theme,String backcolor, HttpServletRequest request, HttpServletResponse response){
		User user = UserUtils.getUser();
		Theme t= getThemeService().getThemeByUserID(user.getId());
		t.setTheme(theme);
		t.setBackcolor("#"+backcolor);
		t.setUserid(user.getId());
		getThemeService().merge(t);
		return "redirect:"+Global.getAdminPath();
	}
	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		SecurityUtils.getSubject().logout();
		return "redirect:"+Global.getAdminPath();
	}
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
