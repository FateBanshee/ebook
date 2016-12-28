package com.GB.ebook.services.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GB.ebook.dao.CourseMapper;
import com.GB.ebook.entity.Course;
import com.GB.ebook.services.interfaces.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseMapper courseMapper;

	
	public String addCourse(Course course) {
		int i =courseMapper.addCourse(course);
		System.out.println(course);
		return "done";
	}

	
	public String delCourse(String courseId) {
		int i =courseMapper.delCourse(courseId);
		
		return "done";
	}

	
	public String updateCourse(Course course) {
		int i =courseMapper.updateCourse(course);
		return "done";
	}

	
	public List<Course> getCourse() {
	
		return courseMapper.getCourse();
	}

	
	public Course getCourseById(String courseId) {
		
		return courseMapper.getCourseById(courseId);
	}

	
	public List<Course> getCourseByName(String courseName) {
		
		return courseMapper.getCourseByName(courseName);
	}


	public List<Course> getPersonalCourse(String accountName) {
	
		return courseMapper.getPersonalCourse(accountName);
	}


	public Course getPersonalCourseById(String courseId, String accountName) {
	
		return courseMapper.getPersonalCourseById(courseId, accountName);
	}




	public List<Course> getPersonalCourseByName(String courseName,
			String accountName) {
		return courseMapper.getPersonalCourseByName(courseName,accountName);
	}

}
