
package com.frame.common.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frame.common.utils.BeanValidators;
import com.frame.common.utils.DateUtils;
import com.frame.common.utils.Global;
import com.frame.common.utils.StringUtil;
import com.frame.common.utils.StringUtils;
import com.frame.sys.entity.Theme;
import com.frame.sys.entity.User;
import com.frame.sys.security.SystemAuthorizingRealm;
import com.frame.sys.service.ThemeService;
import com.frame.sys.utils.UserUtils;

/**
 * 控制器支持类
 * @author 
 * @version 
 */
public abstract class BaseController {
	@Autowired
	private ThemeService themetService;

	@ModelAttribute("theme")
	public Theme setTheme() {
		User user = UserUtils.getUser();
		Theme theme=new Theme();
        if (StringUtils.isNotBlank(user.getId())){
    		theme=themetService.getThemeByUserID(user.getId());
    		if(StringUtil.isEmpty(theme.getId())){
    			theme.setTheme("default");
    			theme.setBackcolor("#718aac");
    			theme.setUserid(user.getId());
    			themetService.merge(theme);
    		}
        }
	    return theme;
	}
	/**
	 * 获取当前请求路径
	 * @param request
	 * @return
	 */
	@ModelAttribute("currentPath")
	public String getCurrentpath(HttpServletRequest request){
		return request.getRequestURL().toString();
	}
	@ModelAttribute("sort")
	public String getSort(HttpServletRequest request){
		String sort=request.getParameter("sort")==null?"":request.getParameter("sort").toString();
		return sort;
	}
	@ModelAttribute("order")
	public String getOrder(HttpServletRequest request){
		String order=request.getParameter("order")==null?"":request.getParameter("order").toString();
		return order;
	}
	
	public ThemeService getThemeService(){
	   return this.themetService;
    }
	public String Success="[{operator:'success',message:'操作成功!'}]";
	public String Error="[{operator:'error',message:'操作失败!'}]";
	public String success(String message){
		return "[{operator:'success',message:'"+message+"'}]";
	}
	public String error(String message){
		return "[{operator:'error',message:'"+message+"'}]";
	}
	
	@RequestMapping(value = "${adminPath}/importExcel")
	public String importExcel(String formAction,String tmpFileName,Model model ){
		model.addAttribute("formAction", formAction);
		model.addAttribute("tmpFileName", tmpFileName);
		return "template/importexcel";
	}
	
	/**
	 * 清除所有用户权限
	 */
	public void clearAllCachedAuthorizationInfo(){
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		SystemAuthorizingRealm shiroRealm = (SystemAuthorizingRealm)rsm.getRealms().iterator().next();
		shiroRealm.clearAllCachedAuthorizationInfo();//清除所有用户权限
	}
	
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;
	
    protected int getPageNo(HttpServletRequest request) {
        return NumberUtils.toInt(request.getParameter("page"));
    }
    @ModelAttribute("pageSize")
    protected int getPageSize(HttpServletRequest request) {
    	if(StringUtil.isEmpty(request.getParameter("rows"))){
    		return Global.getPageSize();
    	}else{
    		return NumberUtils.toInt(request.getParameter("rows"));
    	}
    }
    
    protected String page_403() {
        return "error/403";
    }

    protected String page_no_permission() {
        return "error/no_permission.jsp";
    }
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}
	
}
