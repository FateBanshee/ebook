package com.GB.ebook.dao;


import java.util.List;

import com.GB.ebook.entity.Course;

public interface CourseMapper {
	int addCourse(Course course);
	int delCourse(String courseId);
	int updateCourse(Course course);
	List<Course> getCourse();
	List<Course>getPersonalCourse(String accountName);
	Course getCourseById(String courseId);
	List<Course> getCourseByName(String courseName);
	Course getPersonalCourseById(String courseId,String accountName);
	List<Course> getPersonalCourseByName(String courseName,String accountName);
}
