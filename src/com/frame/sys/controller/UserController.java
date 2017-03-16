package com.frame.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.common.utils.MyBeanUtils;
import com.frame.common.utils.StringUtil;
import com.frame.common.utils.StringUtils;
import com.frame.sys.entity.Depart;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.User;
import com.frame.sys.service.DepartService;
import com.frame.sys.service.RoleService;
import com.frame.sys.service.SystemService;
import com.frame.sys.service.UserService;
import com.google.common.collect.Lists;


@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DepartService departService;
	
	@ModelAttribute("userinfo")
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return userService.get(id);
		}else{
			return new User();
		}
	}
	
	@RequestMapping(value = "")
	public String user(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/sys/user";
	}
	
	@RequestMapping(value = "list")
	public String list(User user,HttpServletRequest request, HttpServletResponse response,Model model) {
		User u=userService.get("1");
		model.addAttribute("aa", u.getLoginname());
		return "pages/sys/userlist";
	}
	
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(User user,String pass,String roleids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(user!=null){
			if(StringUtil.isEmpty(user.getId())){
				List<User> users=userService.find("from User where loginname=?",user.getLoginname());
				if(users.size()>0){
					return error("保存失败，重复的登录名");
				}
				if(StringUtil.isEmpty(pass)){
					user.setPassword(systemService.entryptPassword("123"));
				}else{
					user.setPassword(systemService.entryptPassword(pass));
				}
			}else{
				List<User> users=userService.find("from User where id<>? and loginname=?",user.getId(),user.getLoginname());
				if(users.size()>0){
					return error("保存失败，重复的登录名");
				}
				if(!StringUtil.isEmpty(pass)){
					user.setPassword(systemService.entryptPassword(pass));
				}else{
					User u=userService.get(user.getId());
					user.setPassword(u.getPassword());
				}
			}
			if(StringUtil.isNotEmpty(roleids)){
				List<Role> roles=Lists.newArrayList();
				String[] rs=roleids.split(",");
				for(String r:rs){
					Role role=roleService.get(r);
					roles.add(role);
				}
				user.setRoleList(roles);
			}

			userService.merge(user);
			return success("保存成功!");
		}
		return error("操作失败");
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		User user=userService.load(id);
		if(!StringUtil.isEmpty(user.getId())){
		userService.delete(user);
		}else
		{
			return Error;
		}

		return success("删除成功!");
	}
	
	@RequestMapping(value = "deal")
	public String deal(String id,String cmd,String did, Model model) {
		User user= new User();
		String departid=did;
		String roles="";
		if(cmd.equals("edit")){
			try{
				user=userService.get(id);
				departid=user.getDepart().getId();
			}catch(Exception e){}
		}
		for(Role r:user.getRoleList()){
			if(StringUtil.isEmpty(roles)){
				roles=r.getId();
			}else{
				roles=roles+","+r.getId();
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("departid", departid);
		model.addAttribute("cmd", cmd);
		return "pages/sys/userdeal";
	}
	@ResponseBody
	@RequestMapping(value = "userlistByDepart")
	public List<Map<String, Object>> userlist(String departid, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String departlist =StringUtil.TrimEnd(departid+","+Depart.getSubDepartIds(departService.findAll(), departid),",");
		departlist="'"+departlist.replace(",", "','")+"'";
		List<User> users=userService.findObjs("from User where departid in ("+departlist+")");
		for(User u:users){
			Map<String, Object> map=MyBeanUtils.describe(u);
			map.put("departname", u.getDepart().getDepartname());
			map.put("islogin",u.getCanlogin()?"是":"否");
			mapList.add(map);
		}
		return mapList;
	}
	@RequestMapping(value = "editpass")
	public String editpass(Model model) {
		return "pages/sys/editpass";
	}
	
	@ResponseBody
	@RequestMapping(value = "saveeditpass")
	public String saveeditpass(String oldpass,String pass,HttpServletRequest request, HttpServletResponse response, Model model) {
		User user=(User) model.asMap().get("userinfo");
		if(systemService.validatePassword(oldpass,user.getPassword())){
			user.setPassword(systemService.entryptPassword(pass));
			userService.merge(user);
			return success("保存成功");
		}else{
			return error("原密码输入错误");
		}
	}
	@ResponseBody
	@RequestMapping(value = "allUserList")
	public List<Map<String, Object>> allUserList(HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> users=userService.findAll();
		for(User u:users){
			Map<String, Object> map=MyBeanUtils.describe(u);
			mapList.add(map);
		}
		return mapList;
	}
	
}
