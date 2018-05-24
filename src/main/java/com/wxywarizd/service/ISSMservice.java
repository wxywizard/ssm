package com.wxywarizd.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ISSMservice {
	
	public Map<String,Object> findAll(String identity,int page, int pageSize);
	
	public Map<String,Object> findHuman(String schoolNumber);
	
	public Map<String,Object> findAllCourse(int page, int pageSize);
	
	public void inserPub(String formTitle,String formTextarea);
	
	public void updateStudent(String formPassword,String formSchoolNumber,String formAge,String formAddress);
	
	public Map<String,Object> findSystembulletin(int page, int pageSize);
	
	public Map<String,Object> findTchCourseInfo(int page, int pageSize,String schoolNumber);
	
	public void insertCourseinfo(String schoolNumber,String formTitle,String formTextarea);
	
	public Map<String,Object> findTchCourse(int page, int pageSize,String schoolNumber);
	
	public void updateTchCourseInfo(String id,String formCoursename,String formCourseinfo);
	
	public void updateAchievement(String id,String achievement);
	
	public Map<String,Object> findStuCourseInfo(int page, int pageSize,String schoolNumber);
	
	public Map<String,Object> findCourseAll(int page, int pageSize);
	
	public void insertStuAppointment(String schoolNumber,String  tchid,String  coursename,String  courseinfo);
	
	public void updateStatus(@Param("id")String id);

}
