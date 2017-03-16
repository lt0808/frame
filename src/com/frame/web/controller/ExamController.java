package com.frame.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.frame.common.controller.BaseController;
import com.frame.common.page.Pagination;
import com.frame.common.utils.Collections3;
import com.frame.common.utils.ExcelUtil;
import com.frame.sys.entity.User;
import com.frame.sys.service.UserService;
import com.frame.sys.utils.UserUtils;
import com.frame.web.entity.Exam;
import com.frame.web.entity.ExamClass;
import com.frame.web.entity.ExamPost;
import com.frame.web.entity.ExamUnit;
import com.frame.web.entity.ExamUnitUser;
import com.frame.web.service.BankOrderService;
import com.frame.web.service.ExamClassService;
import com.frame.web.service.ExamPostService;
import com.frame.web.service.ExamService;
import com.frame.web.service.ExamUnitService;
import com.frame.web.service.ExamUnitUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Controller
@RequestMapping(value = "${adminPath}/web/exam")
public class ExamController extends BaseController {
	@Autowired
	private ExamClassService examClassService;
	@Autowired
	private ExamUnitService examUnitService;
	@Autowired
	private ExamPostService examPostService;
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamUnitUserService examUnitUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private BankOrderService bankOrderService;
	
	
	@RequestMapping(value = "examclass")
	public String examclass(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/examclasslist";
	}
	@RequestMapping(value = "examclassdeal")
	public String ExamClass(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		ExamClass ec=new ExamClass();
		if(StringUtils.isNotEmpty(id)){
			ec=examClassService.get(id);
		}
		model.addAttribute("ec", ec);
		return "pages/web/exam/examclassdeal";
	}
	@ResponseBody
	@RequestMapping(value = "examclasslist")
	public Map<String, Object> examclasslist(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = examClassService.createDetachedCriteria();
		
		dc.addOrder(Order.desc("createDate"));
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		return examClassService.findToMap(getPageNo(request), dc);
	}
	@ResponseBody
	@RequestMapping(value = "examclasssave")
	public String examclasssave(ExamClass ec,HttpServletRequest request, HttpServletResponse response, Model model) {
		examClassService.merge(ec);
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "deleteclass")
	public String deleteclass(String ids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idd=ids.split(",");
			for(String id:idd){
				examClassService.deleteByID(id);
			}
		}else{
			return error("请选择删除项!");
		}
		return success("已删除!");
	}
	
	@RequestMapping(value = "examunit")
	public String examunit(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/examunit";
	}
	@ResponseBody
	@RequestMapping(value = "unitlist")
	public Map<String, Object> unitlist(String examclassid,HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = examUnitService.createDetachedCriteria();
		if(StringUtils.isNotEmpty(examclassid)){
		   dc.add(Restrictions.eq("examclass.id", examclassid));
		}
		
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		
		Map<String, Object> map=examUnitService.findToMap(getPageNo(request), dc);
		for(Map<String, Object> o :(List<Map<String, Object>>)map.get("rows")){
			ExamUnit eu=examUnitService.get(o.get("id").toString());
			List<ExamUnitUser> euus=examUnitUserService.find("from ExamUnitUser where unitid=?",o.get("id").toString());
			String users="";
			for(ExamUnitUser euu:euus){
				if(StringUtils.isNotEmpty(users)){
					users+=","+euu.getUsername()+"("+euu.getLoginname()+")";
				}else{
					users=euu.getUsername()+"("+euu.getLoginname()+")";
				}
			}
			o.put("users",users);
			o.put("classname",eu.getExamclass()==null?"":eu.getExamclass().getExamname());
		}
		return map;
	}
	@RequestMapping(value = "examunitdeal")
	public String examunitdeal(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		ExamUnit eu=new ExamUnit();
		String users="";
		if(StringUtils.isNotEmpty(id)){
			eu=examUnitService.get(id);
			List<ExamUnitUser> euus=examUnitUserService.find("from ExamUnitUser where unitid=?", eu.getId());
			for(ExamUnitUser euu:euus){
				if(StringUtils.isNotEmpty(users)){
					users+=","+euu.getUserid();
				}else{
					users=euu.getUserid();
				}
			}
		}
		
		model.addAttribute("users", users);
		model.addAttribute("eu", eu);
		return "pages/web/exam/examunitdeal";
	}
	@ResponseBody
	@RequestMapping(value = "examclassselectlist")
	public List<Map<String, Object>> examclassselectlist(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		return examClassService.getSelectList("ExamClass", "examname", 1);
	}
	@ResponseBody
	@RequestMapping(value = "examunitsave")
	public String examunitsave(ExamUnit eu,String users,HttpServletRequest request, HttpServletResponse response, Model model) {
		examUnitService.merge(eu);
		if(StringUtils.isNotEmpty(users)){
		    examUnitUserService.deleteList(examUnitUserService.find("from ExamUnitUser where unitid=?",eu.getId()));
			String[] userids=users.split(",");
			for(String uid:userids){
				ExamUnitUser euu=new ExamUnitUser();
				User user=userService.get(uid);
				euu.setLoginname(user.getLoginname());
				euu.setUsername(user.getUsername());
				euu.setUserid(user.getId());
				euu.setUnitid(eu.getId());
				euu.setUnitname(eu.getUnitname());
				euu.setUnitcode(eu.getUnitcode());
				examUnitUserService.merge(euu);
			}
		}
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "deleteexamunit")
	public String deleteexamunit(String ids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idd=ids.split(",");
			for(String id:idd){
				examUnitService.deleteByID(id);
			}
		}else{
			return error("请选择删除项!");
		}
		return success("已删除!");
	}
	
	
	@RequestMapping(value = "exampost")
	public String exampost(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/exampost";
	}
	@RequestMapping(value = "dealexampost")
	public String dealexamunit(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		ExamPost ep=new ExamPost();
		if(StringUtils.isNotEmpty(id)){
			ep=examPostService.get(id);
		}
		model.addAttribute("ec", ep);
		return "pages/web/exam/exampostdeal";
	}
	@ResponseBody
	@RequestMapping(value = "examunitselectlist")
	public List<Map<String, Object>> examunitselectlist(String examclassid,HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		return examClassService.getSelectList("ExamUnit", "unitname","examclassid='"+examclassid+"'", 1);
	}
	@ResponseBody
	@RequestMapping(value = "exampostsave")
	public String exampostsave(ExamPost ep,HttpServletRequest request, HttpServletResponse response, Model model) {
		examPostService.merge(ep);
		return success("保存成功!");
	}
	
	@ResponseBody
	@RequestMapping(value = "postlist")
	public Map<String, Object> postlist(String unitid,HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = examPostService.createDetachedCriteria();
		if(StringUtils.isNotEmpty(unitid)){
		   dc.add(Restrictions.eq("examunit.id", unitid));
		}
		
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		
		Map<String, Object> map=examPostService.findToMap(getPageNo(request), dc);
		for(Map<String, Object> o :(List<Map<String, Object>>)map.get("rows")){
			ExamPost ep=examPostService.get(o.get("id").toString());
			o.put("classname",ep.getExamclass()==null?"":ep.getExamclass().getExamname());
			o.put("unitname",ep.getExamunit()==null?"":ep.getExamunit().getUnitname());
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "deleteexampost")
	public String deleteexampost(String ids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idd=ids.split(",");
			for(String id:idd){
				examPostService.deleteByID(id);
			}
		}else{
			return error("请选择删除项!");
		}
		return success("已删除!");
	}
	
	@ResponseBody
	@RequestMapping(value = "importExcel")
	public String importExcel(HttpServletRequest request,HttpServletResponse response, Model model)  {
		try{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        System.out.println("通过传统方式form表单提交方式导入excel文件！");  
          
        InputStream in =null;  
        List<List<Object>> listob = null;  
        MultipartFile file = multipartRequest.getFile("upfile");  
        if(file.isEmpty()){  
            throw new Exception("文件不存在！");  
        }  
        in = file.getInputStream();  
        listob = new ExcelUtil().getBankListByExcel(in,file.getOriginalFilename());  
        in.close();  
        
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> lo = listob.get(i);
            ExamPost ep = new ExamPost();
            ExamClass ec = new ExamClass();
            ExamUnit eu = new ExamUnit();
            eu.setId(lo.get(0).toString());
            ep.setExamunit(eu);
            ep.setPostname(lo.get(1).toString());
            ep.setIsshow(Integer.parseInt(lo.get(2).toString()));
            ep.setPostcode(lo.get(3).toString());
            ep.setWritename(lo.get(4).toString());
            ep.setWritecode(lo.get(5).toString());
            ec.setId(lo.get(6).toString());
            ep.setExamclass(ec);
            examPostService.merge(ep);
        }  
		    return success("导入成功");
		}catch(Exception e){
			return error("导入失败："+e.getMessage());
		}
	}
	
	@RequestMapping(value = "exam")
	public String examclass(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/exam";
	}
	@ResponseBody
	@RequestMapping(value = "examlist")
	public Map<String, Object> examlist(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = getExamListDc(request);
		Map<String, Object> map=examService.findToMap(getPageNo(request), dc);

		return map;
	}
	
	public DetachedCriteria getExamListDc(HttpServletRequest request){
		DetachedCriteria dc = examService.createDetachedCriteria();

		String examid=request.getParameter("examclassid");
		String unitid=request.getParameter("examunitid");
		String username=request.getParameter("username");
		String sfz=request.getParameter("sfz");
		String zkzh=request.getParameter("zkzh");
		String isjf=request.getParameter("isjf");
		String status=request.getParameter("status");
		String infoprint=request.getParameter("infoprint");
		String promiseprint=request.getParameter("promiseprint");
		String zkzprint=request.getParameter("zkzprint");
		String kc=request.getParameter("kc");
		String address=request.getParameter("examaddress");
		
		
		if(StringUtils.isNotEmpty(examid)){
			dc.add(Restrictions.eq("examid", examid));
		}
		if(StringUtils.isNotEmpty(unitid)){
			dc.add(Restrictions.eq("unitid", unitid));
		}
		if(StringUtils.isNotEmpty(username)){
			dc.add(Restrictions.like("username", username, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(sfz)){
			dc.add(Restrictions.like("sfz", sfz, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(zkzh)){
			dc.add(Restrictions.like("zkzh", zkzh, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(isjf)){
			dc.add(Restrictions.eq("isjf", Integer.valueOf(isjf)));
		}
		if(StringUtils.isNotEmpty(status)){
			dc.add(Restrictions.eq("status", status));
		}
		if(StringUtils.isNotEmpty(infoprint)){
			dc.add(Restrictions.eq("info_print", Integer.valueOf(infoprint)));
		}
		if(StringUtils.isNotEmpty(promiseprint)){
			dc.add(Restrictions.eq("promise_print", Integer.valueOf(promiseprint)));
		}
		if(StringUtils.isNotEmpty(zkzprint)){
			dc.add(Restrictions.eq("zkz_print", Integer.valueOf(zkzprint)));
		}
		if(StringUtils.isNotEmpty(kc)){
			dc.add(Restrictions.eq("kc", kc));
		}
		if(StringUtils.isNotEmpty(address)){
			dc.add(Restrictions.eq("examaddress", address));
		}
		
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		}
		
		return dc;
	}
	
	@RequestMapping(value = "examdeal")
	public String examdeal(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Exam e=new Exam();
		if(StringUtils.isNotEmpty(id)){
			e=examService.get(Long.valueOf(id));
		}
		model.addAttribute("exam", e);
		return "pages/web/exam/examdeal";
	}
	@ResponseBody
	@RequestMapping(value = "examsave")
	public String examsave(Exam exam,HttpServletRequest request, HttpServletResponse response, Model model) {
		Exam e=examService.get(exam.getId());
		e.setUsername(exam.getUsername());
		e.setSfz(exam.getSfz());
		e.setKc(exam.getKc());
		e.setZh(exam.getZh());
		e.setZkzh(exam.getZkzh());
		e.setExamaddress(exam.getExamaddress());
		e.setExamdate(exam.getExamdate());
		examService.update(e);
		return success("保存成功!");
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(String ids,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idd=ids.split(",");
			for(String id:idd){
				examService.deleteByID(Long.valueOf(id));
			}
		}else{
			return error("请选择删除项!");
		}
		return success("已删除!");
	}
	@RequestMapping(value = "check")
	public String check(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/checklist";
	}
	@ResponseBody
	@RequestMapping(value = "checklist")
	public Map<String, Object> checklist(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = examService.createDetachedCriteria();
		
		String examid=request.getParameter("examclassid");
		String username=request.getParameter("username");
		String sfz=request.getParameter("sfz");
		String status=request.getParameter("status");
		
		
		if(StringUtils.isNotEmpty(examid)){
			dc.add(Restrictions.eq("examid", examid));
		}
		if(StringUtils.isNotEmpty(username)){
			dc.add(Restrictions.like("username", username, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(sfz)){
			dc.add(Restrictions.like("sfz", sfz, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(status)){
			dc.add(Restrictions.eq("status", status));
		}
		
		dc.add(Restrictions.ne("status", "no"));
		if(!SecurityUtils.getSubject().isPermitted("web:exam:checkall")){
			List<ExamUnitUser> euus=examUnitUserService.find("from ExamUnitUser where userid=?",UserUtils.getUser().getId());
			dc.add(Restrictions.in("unitid", Collections3.extractToList(euus, "unitid")));
		}
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		Map<String, Object> map=examService.findToMap(getPageNo(request), dc);
		return map;
	}
	@RequestMapping(value = "checkdeal")
	public String checkdeal(String id,HttpServletRequest request, HttpServletResponse response, Model model) {
		Exam e=new Exam();
		if(StringUtils.isNotEmpty(id)){
			e=examService.get(Long.valueOf(id));
		}
		model.addAttribute("exam", e);
		return "pages/web/exam/checkdeal";
	}
	@ResponseBody
	@RequestMapping(value = "checksave")
	public String checksave(Exam exam,HttpServletRequest request, HttpServletResponse response, Model model) {
		Exam e=examService.get(exam.getId());
		e.setStatus(exam.getStatus());
		e.setShr(UserUtils.getUser().getLoginname());
		e.setShrname(UserUtils.getUser().getUsername());
		e.setShrdate(new Date());
		e.setRemarks(exam.getRemarks());
		if(exam.getStatus().equals("disagree")){
			if(StringUtils.isEmpty(exam.getRemarks())){
				return error("审核失败，请填写 未通过说明！");
			}
		}
		examService.update(e);
		return success("审核成功!");
	}
	@RequestMapping(value = "pay")
	public String pay(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pages/web/exam/paylist";
	}
	@ResponseBody
	@RequestMapping(value = "paylist")
	public Map<String, Object> paylist(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = bankOrderService.createDetachedCriteria();
		
		String examid=request.getParameter("examclassid");
		String userName=request.getParameter("userName");
		String userSfz=request.getParameter("userSfz");
		String userId=request.getParameter("userId");
		String orderNo=request.getParameter("orderNo");

		if(StringUtils.isNotEmpty(examid)){
			dc.add(Restrictions.eq("orderType", examid));
		}
		if(StringUtils.isNotEmpty(userName)){
			dc.add(Restrictions.like("userName", userName, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(userSfz)){
			dc.add(Restrictions.like("userSfz", userSfz, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(userId)){
			dc.add(Restrictions.like("userId", userId, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotEmpty(orderNo)){
			dc.add(Restrictions.like("orderNo", orderNo, MatchMode.ANYWHERE));
		}

		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		Map<String, Object> map=bankOrderService.findToMap(getPageNo(request), dc);
		return map;
	}
	@RequestMapping(value = "examroom")
	public String examroom(HttpServletRequest request, HttpServletResponse response, Model model) {
		DetachedCriteria dc = examService.createDetachedCriteria();
		List kcs=examService.findObjs("select kc from Exam where kc!='' and kc is not null group by kc");
		List address=examService.findObjs("select examaddress from Exam where examaddress!='' and examaddress is not null group by examaddress");
		Pagination p=examService.find(getPageNo(request),getPageSize(request), dc);
		model.addAttribute("p", p);
		model.addAttribute("kcs", kcs);
		model.addAttribute("address", address);
		return "pages/web/exam/examroom";
	}
	@RequestMapping(value = "examroomprint")
	public String examroomprint(HttpServletRequest request, HttpServletResponse response, Model model) {
		DetachedCriteria dc = examService.createDetachedCriteria();
		
		String examid=request.getParameter("examid");
		String kc=request.getParameter("kc");
		String address=request.getParameter("address");
		
		if(StringUtils.isNotEmpty(examid)){
			dc.add(Restrictions.eq("examid", examid));
		}
		if(StringUtils.isNotEmpty(kc)){
			dc.add(Restrictions.eq("kc", kc));
		}
		if(StringUtils.isNotEmpty(address)){
			dc.add(Restrictions.eq("examaddress", address));
		}
		
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		dc.addOrder(Order.asc("zh"));
		Pagination p=examService.find(getPageNo(request),getPageSize(request), dc);
		List<Exam> exams=(List<Exam>)p.getList();
		String examdate="";
		if(exams.size()>0){
			examdate=exams.get(0).getExamdate();
		}
		model.addAttribute("p", p);
		model.addAttribute("examdate",examdate);
		model.addAttribute("kc",kc);
		model.addAttribute("address",address);
		
		return "pages/web/exam/examroomprint";
	}
	
	@ResponseBody
	@RequestMapping(value = "searchprint")
	public Map<String, Object> searchprint(HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		DetachedCriteria dc = examService.createDetachedCriteria();
		
		String examid=request.getParameter("examid");
		String kc=request.getParameter("kc");
		String address=request.getParameter("address");
		
		if(StringUtils.isNotEmpty(examid)){
			dc.add(Restrictions.eq("examid", examid));
		}
		if(StringUtils.isNotEmpty(kc)){
			dc.add(Restrictions.eq("kc", kc));
		}
		if(StringUtils.isNotEmpty(address)){
			dc.add(Restrictions.eq("examaddress", address));
		}
		
		if(getOrder(request).equals("asc")){
		    dc.addOrder(Order.asc(getSort(request)));
		}else if(getOrder(request).equals("desc")){
			dc.addOrder(Order.desc(getSort(request)));
		};
		dc.addOrder(Order.asc("zh"));
		Pagination p=examService.find(getPageNo(request),getPageSize(request), dc);
		Map<String, Object> map=Maps.newHashMap();
		map.put("total", p.getTotalCount());
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "examkcselectlist")
	public List<Map<String, Object>> examkcselectlist(String examclassid,HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> relist=Lists.newArrayList();
		List list=examService.findObjs("select kc from Exam where examid=? group by kc ",examclassid);

		Map<String, Object> m=Maps.newHashMap();
		m.put("id","");
		m.put("text", "--请选择--");
		relist.add(m);

		
		for(Object o:list){
			Map<String, Object> map=Maps.newHashMap();
			map.put("id",o.toString());
			map.put("text",o.toString());
			relist.add(map);
		}
		return relist;
	}
	@ResponseBody
	@RequestMapping(value = "examaddressselectlist")
	public List<Map<String, Object>> examaddressselectlist(String examclassid,HttpServletRequest request,HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> relist=Lists.newArrayList();
		List list=examService.findObjs("select examaddress from Exam where examid=? group by examaddress ",examclassid);
		
		Map<String, Object> m=Maps.newHashMap();
		m.put("id","");
		m.put("text", "--请选择--");
		relist.add(m);
		
		
		for(Object o:list){
			Map<String, Object> map=Maps.newHashMap();
			map.put("id",o.toString());
			map.put("text",o.toString());
			relist.add(map);
		}
		return relist;
	}
	@ResponseBody
	@RequestMapping(value = "exportExcelInfo")
	public void exportExcelInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		String fileName = URLEncoder.encode("考生信息表", "UTF-8");
		response.setHeader("content-disposition", "attachment;filename="+fileName+".xls");  
		OutputStream out=response.getOutputStream();  
		LinkedHashMap<String,String> map=Maps.newLinkedHashMap();
		map.put("id","ID");
		map.put("username", "姓名");
		map.put("sex", "性别");
		map.put("sfz", "身份证");
		map.put("unitname", "报考单位");
		map.put("postname", "报考岗位");
		map.put("wirtecode", "笔试代码");
		map.put("postcode", "岗位代码");
		map.put("kc", "考场");
		map.put("zh", "座号");
		map.put("zkzh", "准考证号");
		map.put("wirtename", "笔试类别");
		map.put("examaddress", "考试地点");
		map.put("examdate", "考试时间");
		map.put("wirtename", "考试科目");
		map.put("birthdate", "出生年月");
		map.put("zhengzhi", "政治面貌");
		map.put("minzu", "民族");
		map.put("syd", "生源地");
		map.put("address", "户口所在地");
		map.put("zy", "专业");
		map.put("xl", "学历");
		map.put("xw", "学位");
		map.put("graddate", "毕业时间");
		map.put("jobdate", "参加工作时间");
		map.put("zgzs", "具备资格证书情况");
		map.put("wy", "外语");
		map.put("computer", "计算机");
		map.put("tel", "固定电话");
		map.put("phone", "手机");
		map.put("address2", "通讯地址");
		map.put("email", "电子邮箱");
		map.put("dw", "现工作单位");
		map.put("school", "毕业院校");
		
		DetachedCriteria dc = getExamListDc(request);
		Pagination p=examService.find(1, 10000000, dc);
		ExcelUtil.exportExcel(map, p.getList(),out);
		out.flush();
		out.close();
	}
	@ResponseBody
	@RequestMapping(value = "exportExcelTj")
	public void exportExcelTj(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String examid=request.getParameter("examclassid");
		response.setContentType("application/vnd.ms-excel");
		String fileName = URLEncoder.encode("考生统计表", "UTF-8");
		response.setHeader("content-disposition", "attachment;filename="+fileName+".xls");  
		OutputStream out=response.getOutputStream();  
		LinkedHashMap<String,String> map=Maps.newLinkedHashMap();
		map.put("xh", "岗位编号");
		map.put("unit","招聘单位");
		map.put("postlevel","岗位等级");
		map.put("postname","岗位名称");
		map.put("zprs","招聘人数");
		map.put("bmrs","报名人数");
		map.put("shtgrs","审核通过人数");
		map.put("jfrs","缴费人数");
		
		List<ExamPost> eps=examPostService.find("from ExamPost where examclassid=?", examid);
		List<Map<String,String>> list=Lists.newArrayList();
		for(ExamPost ep:eps){
			Map<String,String> m=Maps.newHashMap();
			m.put("xh", ep.getPostcode());
			m.put("unit",ep.getExamunit().getUnitname());
			m.put("postlevel",ep.getPostlevel());
			m.put("postname",ep.getPostname());
			m.put("zprs",ep.getPersonnum()+"");
			m.put("bmrs",examService.find("from Exam where postid=?",ep.getId()).size()+"");
			m.put("shtgrs",examService.find("from Exam where postid=? and status='agree'",ep.getId()).size()+"");
			m.put("jfrs",examService.find("from Exam where postid=? and isjf=1",ep.getId()).size()+"");
			list.add(m);
		}
		
		
		ExcelUtil.exportExcel(map, list,out);
		out.flush();
		out.close();
	}
}
