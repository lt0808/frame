package com.frame.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.web.entity.Title;
import com.frame.web.service.TitleService;

@Controller
@RequestMapping(value = "${adminPath}/web/title")
public class TitleController extends BaseController {
	@Autowired
	private TitleService titleService;
	
	@RequestMapping(value = "")
	public String title(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/title/index";
	}
	@ResponseBody
	@RequestMapping(value = "list")
	public List<Map<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
	    List<Map<String, Object>> mapList=titleService.recursive("Title", "parentid", "","titlename","sortid asc");
		return mapList;
	}
	
	@RequestMapping(value = "deal")
	public String deal(String id,String cmd, Model model) {
		String pid="";
		
		Title d= new Title();
		if(cmd.equals("add")){
			pid=id;
		}
		if(cmd.equals("edit")){
			try{
			d=titleService.get(id);
			pid=d.getParent().getId();
			}catch(Exception e){}
		}
		model.addAttribute("title", d);
		model.addAttribute("pid", pid);
		model.addAttribute("cmd", cmd);
		return "pages/web/title/deal";
	}
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(Title title,HttpServletRequest request, HttpServletResponse response, Model model) {
		titleService.merge(title);
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Title d=titleService.load(id);
		if(d.getChildList().size()>0){
			return error("此栏目中还有子栏目，无法删除!");
		}else{
	        titleService.delete(d);
		}
		return success("删除成功!");
	}
}
