package com.frame.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.sys.entity.Depart;
import com.frame.sys.entity.ModulePermissions;
import com.frame.sys.entity.User;
import com.frame.sys.service.DepartService;
import com.frame.sys.service.UserService;

@Controller
@RequestMapping(value = "${adminPath}/sys/depart")
public class DepartController extends BaseController {
	@Autowired
	private DepartService departService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "")
	public String module(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/sys/depart";
	}
	@ResponseBody
	@RequestMapping(value = "list")
	public List<Map<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
	    List<Map<String, Object>> mapList=departService.recursive("Depart", "parentid", "","departname","sortid asc");
		return mapList;
	}
	
	@RequestMapping(value = "deal")
	public String deal(String id,String cmd, Model model) {
		String pid="";
		
		Depart d= new Depart();
		if(cmd.equals("add")){
			pid=id;
		}
		if(cmd.equals("edit")){
			try{
			d=departService.get(id);
			pid=d.getParent().getId();
			}catch(Exception e){}
		}
		model.addAttribute("depart", d);
		model.addAttribute("pid", pid);
		model.addAttribute("cmd", cmd);
		return "pages/sys/departdeal";
	}
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(Depart depart,HttpServletRequest request, HttpServletResponse response, Model model) {
		departService.merge(depart);
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Depart d=departService.load(id);
		if(d.getUserList().size()>0){
			return error("此部门中还有人员，无法删除!");
		}else{
			departService.delete(d);
		}
		return success("删除成功!");
	}

}
