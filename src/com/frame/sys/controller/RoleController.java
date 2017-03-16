package com.frame.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.common.utils.Global;
import com.frame.common.utils.MyBeanUtils;
import com.frame.common.utils.StringUtil;
import com.frame.sys.entity.Module;
import com.frame.sys.entity.ModulePermissions;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.RolePermissions;
import com.frame.sys.entity.User;
import com.frame.sys.service.ModulePermissionsService;
import com.frame.sys.service.ModuleService;
import com.frame.sys.service.RolePermissionsService;
import com.frame.sys.service.RoleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionsService rolePermissionsService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ModulePermissionsService modulePermissionsService;
	
	@RequestMapping(value = "")
	public String role(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/sys/role";
	}
	@RequestMapping(value = "deal")
	public String deal(String cmd,String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Role role=new Role();
		if(cmd.equals("edit")){
			role=roleService.get(id);
		}
		model.addAttribute("role",role);
		return "pages/sys/roledeal";
	}
	@ResponseBody
	@RequestMapping(value = "list")
	public List<Map<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
	    List<Map<String, Object>> mapList=roleService.findObjs("from Role");
		return mapList;
	}
	@ResponseBody
	@RequestMapping(value = "combotree")
	public List<Map<String, Object>> combotree(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Role> roles=roleService.findObjs("from Role");
		List<Map<String,Object>> mapList = Lists.newArrayList();
		for(Role r:roles){
			Map<String,Object> map = Maps.newHashMap();
			map.put("id", r.getId());
			map.put("text", r.getRolename());
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(Role role,HttpServletRequest request, HttpServletResponse response, Model model) {
		roleService.merge(role);
		return success("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value = "savepermiss")
	public String savepermiss(String modules,String ptypes,String roleid,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtil.isEmpty(roleid)){
			return error("操作失败，确认是否选择了角色");
		}else{
			Role r=roleService.get(roleid);
			List<RolePermissions> list=rolePermissionsService.find("from RolePermissions where roleid=?",roleid);
			rolePermissionsService.deleteList(list);
			if(!StringUtil.isEmpty(modules)){
				String[] module=modules.split(",");
				String[] ptype=ptypes.split(",");
				for(int i=0;i<module.length;i++){
					RolePermissions rp=new RolePermissions();
					rp.setMid(module[i]);
					rp.setRole(r);
					rp.setPkey(ptype[i]);
					if(ptype[i].equals("moduleview")){
						rp.setPtype("moduleview");
					}else{
						rp.setPtype("permiss");
					}
					rolePermissionsService.merge(rp);
				}
			}
			clearAllCachedAuthorizationInfo();
		}
		return success("保存成功");
	}
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "moduletree")
	public List<Map<String, Object>> moduletree(String roleid,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if(!StringUtil.isEmpty(roleid)){
			List<RolePermissions> rps=rolePermissionsService.find("from RolePermissions where roleid=?",roleid);
		    List<Module> list=moduleService.find("from Module where parentid is not null and parentid<>'0' and parentid<>'' order by sortid asc");
			
			for(Module m:list){
				String url="";
				if(StringUtil.isNotEmpty(m.getUrllink())){
					url = request.getContextPath()+ Global.getAdminPath()+m.getUrllink();
				}
		        Map<String, Object> maproot = Maps.newHashMap();
		        maproot.put("id", m.getId());
		        maproot.put("pId", m.getParent()==null?"0":m.getParent().getId());
		        maproot.put("name", m.getModulename());
		        maproot.put("ptype","moduleview");
		        maproot.put("open", true);
		        for(RolePermissions rp:rps){
		        	if(rp.getMid().equals(m.getId())&&rp.getPtype().equals("moduleview")){
		        		maproot.put("checked", true);
		        		break;
		        	}
		        }
		        mapList.add(maproot);
		        
		        List<ModulePermissions> mps=modulePermissionsService.find("from ModulePermissions where moduleid=?", m.getId());
		        for(ModulePermissions mp:mps){
			        Map<String, Object> map = Maps.newHashMap();
			        map.put("id", mp.getId());
			        map.put("pId", m.getId());
			        map.put("name", mp.getPermissionsdesc());
			        map.put("ptype",mp.getPermissionskey());
			        map.put("icon",request.getContextPath()+"/front/ztree/css/zTreeStyle/img/diy/page_key.gif");
			        for(RolePermissions rp:rps){
			        	if(rp.getMid().equals(m.getId())&&rp.getPkey().equals(mp.getPermissionskey())){
			        		map.put("checked", true);
			        		break;
			        	}
			        }
			        mapList.add(map);
		        }
			}
		}
		return mapList;
	}
	@ResponseBody
	@RequestMapping(value = "delrole")
	public String delrole(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Role r=roleService.get(id);
		List<RolePermissions> list=rolePermissionsService.find("from RolePermissions where roleid=?",id);
		rolePermissionsService.deleteList(list);
		roleService.delete(r);
		
		clearAllCachedAuthorizationInfo();
		
		return success("删除成功!");
	}
	@ResponseBody
	@RequestMapping(value = "usertree")
	public List<Map<String, Object>> usertree(String roleid,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if(!StringUtil.isEmpty(roleid)){
			
			Role role=roleService.get(roleid);
			for(User u:role.getUserList()){
				Map<String, Object> map=MyBeanUtils.describe(u);
				map.put("departname", u.getDepart().getDepartname());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
