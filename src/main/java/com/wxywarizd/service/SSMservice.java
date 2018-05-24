package com.wxywarizd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wxywarizd.dao.IUserDao;

@Service(value="iss")
@Transactional(propagation = Propagation.REQUIRED)
public class SSMservice  implements ISSMservice {

	//从SqlSessionDaoSupport 这个类的源码中可以看出，原因是mybatis-spring-1.2.0中取消了自动注入SqlSessionFactory 和 SqlSessionTemplate
	 /*   @Resource
	    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
            super.setSqlSessionFactory(sqlSessionFactory);
	     }*/
	 @Resource
	 private IUserDao iUserDao;
	    
	@Override
	public Map<String,Object> findAll(String identity,int page, int pageSize) {	
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findUser(identity,nextPage,end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findHuman(String schoolNumber) {
		
		return iUserDao.findHumanDao(schoolNumber);
	}

	@Override
	public Map<String, Object> findAllCourse(int page, int pageSize) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findAllCourse(nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public void inserPub(String formTitle, String formTextarea) {
		
		iUserDao.insertPub(formTitle, formTextarea);
	}

	@Override
	public void updateStudent(String formPassword, String formSchoolNumber, String formAge, String formAddress) {
	
		iUserDao.updateStudent(formPassword, formSchoolNumber, formAge, formAddress);
	}

	@Override
	public Map<String, Object> findSystembulletin(int page, int pageSize) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findSystembulletin(nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findTchCourseInfo(int page, int pageSize,String schoolNumber) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findTchCourseInfo(schoolNumber,nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
		
	}

	@Override
	public void insertCourseinfo(String schoolNumber, String formTitle, String formTextarea) {
		
		iUserDao.insertCourseinfo(schoolNumber, formTitle, formTextarea);
	}

	@Override
	public Map<String, Object> findTchCourse(int page, int pageSize, String schoolNumber) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findTchCourse(schoolNumber, nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public void updateTchCourseInfo(String id, String formCoursename, String formCourseinfo) {
		iUserDao.updateTchCourseInfo(id, formCoursename, formCourseinfo);
		
	}

	@Override
	public void updateAchievement(String id, String achievement) {
		iUserDao.updateAchievement(id, achievement);
	}

	@Override
	public Map<String, Object> findStuCourseInfo(int page, int pageSize, String schoolNumber) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findStuCourseInfo(schoolNumber,nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findCourseAll(int page, int pageSize) {
		int nextPage = pageSize * (page - 1);
    	int end = nextPage + pageSize;
    	List<Map<String, Object>> list = iUserDao.findCourseAll(nextPage, end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	int total = list.size();
    	map.put("total", total);
    	map.put("rows", list);
		return map;
	}

	@Override
	public void insertStuAppointment(String schoolNumber, String tchid, String coursename, String courseinfo) {
		
		iUserDao.insertStuAppointment(schoolNumber, tchid, coursename, courseinfo);
	}

	@Override
	public void updateStatus(String id) {
	
		iUserDao.updateStatus(id);
	}

}
