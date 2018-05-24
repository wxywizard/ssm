package com.wxywarizd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IUserDao {
	

	public List<Map<String,Object>> findUser(@Param("identity")String identity,@Param("nextPage")int nextPage, @Param("end")int end);
	
	public Map<String,Object> findHumanDao(String schoolNumber);
	
	public List<Map<String,Object>> findAllCourse(@Param("nextPage")int nextPage, @Param("end")int end);
	
	public void insertPub(@Param("formTitle")String formTitle, @Param("formTextarea")String  formTextarea);
	
	public void updateStudent(@Param("formPassword")String formPassword,@Param("formSchoolNumber")String formSchoolNumber,@Param("formAge")String formAge,@Param("formAddress")String formAddress);
	
	public List<Map<String,Object>> findSystembulletin(@Param("nextPage")int nextPage, @Param("end")int end);
	
	public List<Map<String,Object>> findTchCourseInfo(@Param("schoolNumber")String schoolNumber,@Param("nextPage")int nextPage, @Param("end")int end);
	
	public void insertCourseinfo(@Param("schoolNumber")String schoolNumber,@Param("formTitle")String formTitle, @Param("formTextarea")String  formTextarea);
	
	public List<Map<String,Object>> findTchCourse(@Param("schoolNumber")String schoolNumber,@Param("nextPage")int nextPage, @Param("end")int end);
	
	public void updateTchCourseInfo(@Param("id")String id,@Param("formCoursename")String formCoursename,@Param("formCourseinfo")String formCourseinfo);
	
	public void updateAchievement(@Param("id")String id,@Param("achievement")String achievement);
	
	public List<Map<String,Object>> findStuCourseInfo(@Param("schoolNumber")String schoolNumber,@Param("nextPage")int nextPage, @Param("end")int end);
	
	public List<Map<String,Object>> findCourseAll(@Param("nextPage")int nextPage, @Param("end")int end);
	
	public void insertStuAppointment(@Param("schoolNumber")String schoolNumber, @Param("tchid")String  tchid,@Param("coursename")String  coursename,@Param("courseinfo")String  courseinfo);
	
	public void updateStatus(@Param("id")String id);
	
}
