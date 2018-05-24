package com.wxywarizd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.wxywarizd.service.ISSMservice;

@Controller
public class ActionController {

	@Resource
	private ISSMservice iss;
	
	

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(String schoolNumber,String password,HttpServletRequest request, HttpServletResponse response) {
	    Map<String,Object> map = iss.findHuman(schoolNumber);  
		if(map != null){
			String pas = (String) map.get("password");
			if(password.equals(pas)) {
				    String userName = (String) map.get("userName");
				    String identity = (String) map.get("identity");
				    request.getSession().setAttribute("userName", userName);
				    request.getSession().setAttribute("identity", identity);
				    Cookie userCookie=new Cookie("schoolNumber",schoolNumber); 
		             userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
		             userCookie.setPath("/");
		             response.addCookie(userCookie);
				    if(identity.equals("SYS")) {
				    	try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				    	return "index";
				    }
				    if(identity.equals("STU")) {
				    	try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				    	return "stuindex";
				    }
				    if(identity.equals("TCH")) {
				    	try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				    	return "tchindex";
				    }					
				}
		}
		return "login";
	}
	
	/**
	 *@author wxy
	 *   
	   *   获取session 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/loginUser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getSession(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
/*		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		String userName = (String) request.getSession().getAttribute("userName");
		String identity = (String) request.getSession().getAttribute("identity");
/*		out.write(userName);
		out.write(identity);
		out.flush();
		out.close();*/
		Map<String,String> map = new HashMap<>();
		map.put("userName", userName);
		map.put("identity", identity);
		String str = JSON.toJSONString(map);
		return str;		
	}
	
	/**
	 * @author wxy
	   * 查询所有人员信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/findAllUser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String findAllUser(String page,String rows,String identity) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		String identitys = identity;
		Map<String, Object> map = iss.findAll(identitys,pa, ro);
		String str = JSON.toJSONString(map);
	    return str;
	}
	
	/**
	 * @author wxy
	   * 查询所有课程信息
	 * @return
	 */
	@RequestMapping(value="/findAllCourseinfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findAllCourseinfo(String page,String rows) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		Map<String, Object> map = iss.findAllCourse(pa, ro);
	    return map;
	}
	
	/**
	 * @author wxy
	 *    发布公告
	 * @return
	 */
	@RequestMapping(value="/publishText",method=RequestMethod.POST)
	@ResponseBody
	public void publishText(String formTitle,String formTextarea) {
		iss.inserPub(formTitle, formTextarea);
		
	}
	
	/**
	 * @author wxy
	  * 查询个人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findUserInfo(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String schoolNumber = "";
	    for(Cookie cookie : cookies){
	        if(cookie.getName().equals("schoolNumber")){
	            schoolNumber = cookie.getValue();
	        }
	     } 
	    Map<String,Object> map = iss.findHuman(schoolNumber);  

		return map;
	}
	
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public void updateUserInfo(String formPassword,String formSchoolNumber,String formAge,String formAddress) {

		iss.updateStudent(formPassword, formSchoolNumber, formAge, formAddress);

	}
	
	/**
	 * @author wxy
	   * 查询公告
	 * @return
	 */
	@RequestMapping(value="/findSystembulletin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findSystembulletin(String page,String rows) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		Map<String, Object> map = iss.findSystembulletin(pa, ro);
	    return map;
	}
	
	/**
	 * @author wxy
	   * 查询实验完成信息
	 * @return
	 */
	@RequestMapping(value="/findTchCourseInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findTchCourseInfo(String page,String rows,HttpServletRequest request) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		Cookie[] cookies = request.getCookies();
		String schoolNumber = "";
	    for(Cookie cookie : cookies){
	        if(cookie.getName().equals("schoolNumber")){
	            schoolNumber = cookie.getValue();
	        }
	     } 
		Map<String, Object> map = iss.findTchCourseInfo(pa, ro,schoolNumber);
	    return map;
	}
	
	/**
	 * @author wxy
	 *    发布课程
	 * @return
	 */
	@RequestMapping(value="/insertCourseinfo",method=RequestMethod.POST)
	@ResponseBody
	public void insertCourseinfo(String formTitle,String formTextarea,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String schoolNumber = "";
	    for(Cookie cookie : cookies){
	        if(cookie.getName().equals("schoolNumber")){
	            schoolNumber = cookie.getValue();
	        }
	     } 
		iss.insertCourseinfo(schoolNumber, formTitle, formTextarea);
		
	}
	
	/**
	 * @author wxy
	   * 查询发布课程
	 * @return
	 */
	@RequestMapping(value="/findTchCourse",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findTchCourse(String page,String rows,HttpServletRequest request) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		String schoolNumber = getcookie(request);
		Map<String, Object> map = iss.findTchCourse(pa, ro,schoolNumber);
	    return map;
	}
	
	/**
	 * @author wxy
	   *  修改实验课程信息
	 * @param formPassword
	 * @param formSchoolNumber
	 * @param formAge
	 * @param formAddress
	 */
	@RequestMapping(value="/updateTchCourseInfo",method=RequestMethod.POST)
	@ResponseBody
	public void updateTchCourseInfo(String id,String formCoursename,String formCourseinfo) {

		iss.updateTchCourseInfo(id, formCoursename, formCourseinfo);

	}
	
	@RequestMapping(value="/updateAchievement",method=RequestMethod.POST)
	@ResponseBody
	public void updateAchievement(String id,String achievement) {

		iss.updateAchievement(id, achievement);

	}
	
	/**
	 * @author wxy
	   * 查询学生实验完成信息
	 * @return
	 */
	@RequestMapping(value="/findStuCourseInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findStuCourseInfo(String page,String rows,HttpServletRequest request) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		String schoolNumber = getcookie(request);
		Map<String, Object> map = iss.findStuCourseInfo(pa, ro,schoolNumber);
	    return map;
	}
	
	/**
	 * @author wxy
	   * 学生查询发布课程
	 * @return
	 */
	@RequestMapping(value="/findCourseAll",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findCourseAll(String page,String rows) {
		int pa = Integer.parseInt(page);
		int ro = Integer.parseInt(rows);
		Map<String, Object> map = iss.findCourseAll(pa, ro);
	    return map;
	}
	
	
	/**
	 * @author wxy
	 *  预约实验课程
	 * @return
	 */
	@RequestMapping(value="/insertStuAppointment",method=RequestMethod.POST)
	@ResponseBody
	public void insertStuAppointment(String tchid,String coursename,String courseinfo,HttpServletRequest request) {

		String schoolNumber = getcookie(request);

		iss.insertStuAppointment(schoolNumber, tchid, coursename, courseinfo);
		
	}
	
	
	
	
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public void updateStatus(String id) {

		iss.updateStatus(id);;

	}
	
	
	 /**  
     * 文件上传功能  
     * @param file  
     * @return  
     * @throws IOException    String path = request.getSession().getServletContext().getRealPath("upload");
     */  
    @RequestMapping(value="/upload",method=RequestMethod.POST)  
    @ResponseBody  
    public void saveHeaderPic(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resMsg = "";
    try {

        long  startTime=System.currentTimeMillis();

        System.out.println("fileName："+file.getOriginalFilename());
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("path:" + path);

        File newFile=new File(path);

        //通过CommonsMultipartFile的方法直接写文件
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
        resMsg = "1";
    } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        resMsg = "0";
    }
          response.getWriter().write(resMsg);

      }
	
	/**
	 * 查询当前登录人
	 * @param request
	 * @return
	 */
	private String getcookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String schoolNumber = "";
	    for(Cookie cookie : cookies){
	        if(cookie.getName().equals("schoolNumber")){
	            schoolNumber = cookie.getValue();
	        }
	     }
	    return schoolNumber;
	}
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String index() {
		System.out.println("123");
	    return "index";
	}
	
}
