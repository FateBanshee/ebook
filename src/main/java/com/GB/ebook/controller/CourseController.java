package com.GB.ebook.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.GB.ebook.entity.BookItem;
import com.GB.ebook.entity.Course;
import com.GB.ebook.services.interfaces.CourseService;
import com.GB.ebook.spider.SpiderUtils;;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	SpiderUtils SpiderUtils = new SpiderUtils();
	
	
	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public ModelAndView addCourse(Course course) {
		System.out.println(course);
		String str = courseService.addCourse(course);
		System.out.println("add done" + str);

		return new ModelAndView("/function/course-info-add").addObject(
				"accountName", course.getTeacherName());
	}

	@RequestMapping(value = "/deleteCourse/{courseId}/{teacherName}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable("courseId") String courseId,
			@PathVariable("teacherName") String teacherName) {
		String str = courseService.delCourse(courseId);
		System.out.println("delete " + str);
		return GetCourseListPer(teacherName);

	}

	@RequestMapping(value = "/personalSearch", method = RequestMethod.GET)
	public ModelAndView personalSearch(String condition, String teacherName,
			String str) {
		return getSearchCoursePer(condition, teacherName, str);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(String condition, String str) {
		return getSearchCourse(condition, str);
	}

	@RequestMapping(value = "/buy/{bookName}/{bookPress}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("bookName") String bookName,@PathVariable("bookPress") String bookPress) throws ClientProtocolException, InterruptedException, ExecutionException, IOException
	{
		List<BookItem> bookItems = SpiderUtils.getItemsFromAllSite(bookName,bookPress);
		System.out.println("spider");
		Map map = new HashMap();
		map.put("bookItems", bookItems);
		ModelAndView buy = new ModelAndView("/function/course-book",map);
		return buy;
	}

	public ModelAndView GetCourseListPer(String accountName) {
		try {
			List<Course> listC = courseService.getPersonalCourse(accountName);
			Map map = new HashMap();
			map.put("listC", listC);
			return new ModelAndView("/function/course-info-list-per", map);
		} catch (DuplicateKeyException e) {
			System.out.println("GetCourseListPer error");
			return new ModelAndView("/function/course-info-list-per");
		}

	}

	public ModelAndView getSearchCoursePer(String condition,
			String teacherName, String str) {
		if (condition.equals("courseId")) {
			Course c = courseService.getPersonalCourseById(str, teacherName);
			return new ModelAndView("/function/course-info-list-per")
					.addObject(c);
		} else {
			List<Course> listC = courseService.getPersonalCourseByName(str,
					teacherName);
			Map map = new HashMap();
			map.put("listC", listC);
			return new ModelAndView("/function/course-info-list-per", map);
		}
	}

	public ModelAndView getSearchCourse(String condition, String str) {
		if (condition.equals("courseId")) {
			Course c = courseService.getCourseById(str);
			return new ModelAndView("/function/course-info-list-per")
					.addObject(c);
		} else {
			List<Course> listC = courseService.getCourseByName(str);
			Map map = new HashMap();
			map.put("listC", listC);
			return new ModelAndView("/function/course-info-list-per", map);
		}
	}

}
