package com.frame.sys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.common.utils.Global;
import com.frame.common.utils.StringUtil;
import com.frame.sys.entity.Module;
import com.frame.sys.entity.ModulePermissions;
import com.frame.sys.service.ModulePermissionsService;
import com.frame.sys.service.ModuleService;
import com.frame.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "${adminPath}/sys/module")
public class ModuleController extends BaseController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ModulePermissionsService modulePermissionsService;
	
	@RequestMapping(value = "menu")
	public String menu(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/sys/menu";
	}
	@RequestMapping(value = "")
	public String module(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/sys/module";
	}
	@RequestMapping(value = "deal")
	public String deal(String id,String cmd, Model model) {
		String pid="";
		Module m= new Module();
		if(cmd.equals("add")){
			pid=id;
		}
		if(cmd.equals("edit")){
			try{
			m=moduleService.get(id);
			pid=m.getParent().getId();
			}catch(Exception e){}
		}
		model.addAttribute("module", m);
		model.addAttribute("pid", pid);
		model.addAttribute("cmd", cmd);
		return "pages/sys/moduledeal";
	}
	@RequestMapping(value = "permissdeal")
	public String permissdeal(String mid,String id,String cmd, Model model) {
		ModulePermissions mp= new ModulePermissions();
		if(cmd.equals("edit")){
			try{
				mp=modulePermissionsService.get(id);
			}catch(Exception e){}
		}
		model.addAttribute("modulepermissions", mp);
		model.addAttribute("mid", mid);
		model.addAttribute("cmd", cmd);
		return "pages/sys/modulepermissdeal";
	}
	
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(Module module,HttpServletRequest request, HttpServletResponse response, Model model) {
		moduleService.merge(module);
		return success("保存成功!");
	}
	
	@ResponseBody
	@RequestMapping(value = "savepermiss")
	public String savepermiss(ModulePermissions modulePermissions,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<ModulePermissions> list = modulePermissionsService.find("from ModulePermissions where permissionskey=? and id<>?", modulePermissions.getPermissionskey(),modulePermissions.getId());
		
		if(list.size()>0){
			return error("标识重复，请确定是唯一");
		}else{
			modulePermissionsService.merge(modulePermissions);
			return success("保存成功!");
		}
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Module m=moduleService.load(id);
		List<ModulePermissions> list=modulePermissionsService.find("from ModulePermissions where moduleid=?",id);		
		modulePermissionsService.deleteList(list);
		moduleService.delete(m);
		return success("删除成功!");
	}
	@ResponseBody
	@RequestMapping(value = "delpermiss")
	public String delpermiss(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		//ModulePermissions mp=modulePermissionsService.get(id);
		modulePermissionsService.deleteByID(id);
		return success("删除成功!");
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Map<String, Object>> tree(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
	    List<Module> list=moduleService.find("from Module where parentid is not null and parentid<>'0' and parentid<>'' and isvalid=1 order by sortid asc");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		HashSet<String> set=UserUtils.getUserPermissions();
		for(Module m:list){
			
			if(set.contains("moduleview:"+m.getId())){
				String url="";
				if(StringUtil.isNotEmpty(m.getUrllink())){
					url = request.getContextPath()+ Global.getAdminPath()+m.getUrllink();
				}
		        Map<String, Object> maproot = Maps.newHashMap();
		        maproot.put("id", m.getId());
		        maproot.put("pId", m.getParent()==null?"0":m.getParent().getId());
		        maproot.put("name", m.getModulename());
		        maproot.put("urllink",url);
		        maproot.put("opentab", m.getOpentab());     
		        mapList.add(maproot);
			}
		}
		return mapList;
	}
	@ResponseBody
	@RequestMapping(value = "list")
	public List<Map<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
	    List<Map<String, Object>> mapList=moduleService.recursive("Module", "parentid", "","modulename","sortid asc");
		return mapList;
	}
	@ResponseBody
	@RequestMapping(value = "permisslist")
	public List<Map<String, Object>> permisslist(String mid, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList=modulePermissionsService.findObjs("from ModulePermissions where moduleid=?",mid);
		return mapList;
	}

	
}
