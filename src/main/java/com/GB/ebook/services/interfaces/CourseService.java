package com.GB.ebook.services.interfaces;



import java.util.List;

import com.GB.ebook.entity.Course;

public interface CourseService {
	String addCourse(Course course);
	String delCourse(String courseId);
	String updateCourse(Course course);
	List<Course> getCourse();
	List<Course> getPersonalCourse(String accountName);
	Course getCourseById(String courseId);
	List<Course> getCourseByName(String courseName);
	Course getPersonalCourseById(String courseId,String accountName);
	
	List<Course> getPersonalCourseByName(String courseName,String accountName);
}
