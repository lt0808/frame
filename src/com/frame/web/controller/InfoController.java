package com.frame.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.controller.BaseController;
import com.frame.common.utils.DateUtils;
import com.frame.common.utils.StringUtil;
import com.frame.web.entity.Info;
import com.frame.web.entity.Title;
import com.frame.web.service.InfoService;
import com.frame.web.service.TitleService;

@Controller
@RequestMapping(value = "${adminPath}/web/info")
public class InfoController extends BaseController {
	@Autowired
	private TitleService titleService;
	@Autowired
	private InfoService infoService;
	@RequestMapping(value = "")
	public String title(HttpServletRequest request, HttpServletResponse response, Model model) {
//		DetachedCriteria dc = infoService.createDetachedCriteria();
//		if(getOrder(request).equals("asc")){
//		    dc.addOrder(Order.asc(getSort(request)));
//		}else if(getOrder(request).equals("desc")){
//			dc.addOrder(Order.desc(getSort(request)));
//		}
//		Pagination page = infoService.find(getPageNo(request),dc);
//		model.addAttribute("page", page);
		
		return "pages/web/info/list";
	}
	@RequestMapping(value = "infodeal")
	public String infodeal(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Info info=new Info();
		if(StringUtils.isNotEmpty(id)){
			info=infoService.get(id);
		}
		model.addAttribute("info", info);
		return "pages/web/info/infodeal";
	}
	@ResponseBody
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		String typeid=request.getParameter("typeid");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String title=request.getParameter("title");
		DetachedCriteria dc = infoService.createDetachedCriteria();
		
		if(StringUtils.isNotEmpty(typeid)){
			String ids=typeid+","+Title.getSubTitleIds(titleService.findAll(), typeid);
			dc.add(Restrictions.in("typeid", ids.split(",")));
		}
		if(StringUtils.isNotEmpty(sdate)){
			dc.add(Restrictions.ge("recdate", DateUtils.parseDate(sdate+" 00:00:00")));
		}
		if(StringUtils.isNotEmpty(edate)){
			dc.add(Restrictions.le("recdate", DateUtils.parseDate(edate+" 23:59:59")));
		}
		if(StringUtils.isNotEmpty(title)){
			dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		}
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		return infoService.findToMap(getPageNo(request), dc);
	}
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(Info info,HttpServletRequest request, HttpServletResponse response, Model model) {
		info.setTypename(reTitleDetail(info.getTypeid()));
		infoService.merge(info);
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String ids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idd=ids.split(",");
			for(String id:idd){
				infoService.deleteByID(id);
			}
		}else{
			return error("请选择删除项!");
		}
		return success("已删除!");
	}
	public String reTitleDetail(String titleid){
		String str="";
		if(StringUtils.isNotEmpty(titleid)&&!titleid.equals("1")){
			Title t=titleService.get(titleid);
			str="->"+t.getTitlename()+str;
			if(t.getParent()!=null){
				str=reTitleDetail(t.getParent().getId())+str;
			}
		}
		return StringUtil.TrimStart(str, "->");
	}
}
