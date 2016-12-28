package com.GB.ebook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;






import com.GB.ebook.entity.Course;
import com.GB.ebook.entity.Information;
import com.GB.ebook.entity.Role;
import com.GB.ebook.entity.User;
import com.GB.ebook.services.interfaces.CourseService;
import com.GB.ebook.services.interfaces.InfoService;
import com.GB.ebook.services.interfaces.RoleService;
import com.GB.ebook.services.interfaces.UserService;

@Controller
public class FunctionController {

	@Autowired
	private UserService userService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CourseService courseService;

	// 系统设置 跳转页面
	@RequestMapping(value = "/system/{id}", method = RequestMethod.GET)
	public ModelAndView systemUser(User user, @PathVariable("id") String id,
			HttpSession session) {
		// requestParam获取参数id
		System.out.println("id=" + id);
		if (id.equals("user")) {
			return GetUserList();
		} else if (id.equals("parameter")) {
			return GetInfo();
		} 
		else if (id.equals("role-assignment"))
			return GetUserList2();
		else if (id.equals("role-assignment-add"))
			return new ModelAndView("/function/system-role-assignment-add");
		else if (id.equals("role-authorization"))
			return GetRoleList();
		else if (id.equals("role-authorization-add"))
			return new ModelAndView("/function/system-role-authorization-add");
		else
			return new ModelAndView("forward:/");
	}

	// 角色分配跳转页面
	@RequestMapping(value = "/authorization/{id}/{roleName}", method = RequestMethod.GET)
	public ModelAndView authorizationUser(User user,@PathVariable("id") String id,
			@PathVariable("roleName") String roleName, HttpSession session) {
		if(id.equals("details"))
		return GetRoledetails(roleName);
		else 
			return null;
	}

	// ebook 跳转页面
	@RequestMapping(value = "/ebook/{id}/{accountName}", method = RequestMethod.GET)
	public ModelAndView propertyUser(User user, @PathVariable("id") String id,@PathVariable("accountName") String accountName,
			HttpSession session) {
		if(id.equals("account-info")){
			
			return new ModelAndView("/function/ebook-user-info").addObject(userService.getUserByAccountName(accountName));
		}
		if(id.equals("account-password"))
			return new ModelAndView("forward:/");
		if(id.equals("teacher"))
			return new ModelAndView("forward:/");
		if(id.equals("course-info-add"))
			return new ModelAndView("/function/course-info-add").addObject(accountName, accountName);
		if(id.equals("course-info-list-personal"))
			return  GetCourseListPer(accountName).addObject(accountName, accountName);
		if(id.equals("course-info-list"))
			return GetCourseList();
		else
			return new ModelAndView("forward:/");
	}


	
	/*
	 * 参数设置-获取提醒信息
	 */
	public ModelAndView GetInfo() {

		Information info = infoService.findbyInfoID();

		ModelAndView mac = new ModelAndView("/function/system-parameter");
		mac.addObject("topic", info.getTopic());
		mac.addObject("time", info.getTime());
		mac.addObject("content", info.getContent());
		mac.addObject("recomandRoleId", info.getRecomandRoleId());

		return mac;
	}

	/*
	 * 帐号管理-获取用户信息
	 */
	public ModelAndView GetUserList() {
		List<User> listUser = userService.listUser();
		Map map = new HashMap();
		map.put("listUser", listUser);// userlist是个Arraylist之类的
		return new ModelAndView("/function/system-user", map);
	}
	


	/*
	 * 权限设置-设置系统角色-获取用户信息以及权限信息
	 */
	public ModelAndView GetUserList2() {
		List<User> listUser = userService.listUser();
		List<Role> listRole = roleService.ListRole();
		Iterator<User> it1 = listUser.iterator();
		while (it1.hasNext()) {
			Iterator<Role> it2 = listRole.iterator();
			User tmpUser = it1.next();

			while (tmpUser.getRoleName() == null && it2.hasNext()) {
				Role tmpRole = it2.next();

				if (tmpUser.getRoleId() == tmpRole.getRoleId()) {
					tmpUser.setRoleName(tmpRole.getRoleName());
					break;
				}

			}

		}

		Map map = new HashMap();
		map.put("listUser", listUser);// userlist是个Arraylist之类的
		map.put("listRole", listRole);
		return new ModelAndView("/function/system-role-assignment", map);
	}
	/*
	 * 权限设置-角色授权-获取角色权限信息
	 */
	public ModelAndView GetRoleList() {
		List<Role> listRole = roleService.ListRole();
		Map map = new HashMap();
		map.put("listRole", listRole);
		return new ModelAndView("/function/system-role-authorization", map);
	}

	/*
	 * 权限设置-角色授权-获取角色权限详细信息
	 */
	public ModelAndView GetRoledetails(String roleName) {
		Role role = roleService.findRoleByName(roleName);
		ModelAndView mac = new ModelAndView("/function/system-role-authorization-details");
		mac.addObject("Role",role);
		return mac;
	}
	
	public ModelAndView GetCourseListPer(String accountName)
	{
		try{
			List<Course> listC = courseService.getPersonalCourse(accountName);
			Map map = new HashMap();
			map.put("listC", listC);
			return new ModelAndView("/function/course-info-list-per",map);
		}catch (DuplicateKeyException e){
			System.out.println("GetCourseListPer error");
			return new ModelAndView("/function/course-info-list-per");
		}
		
	}
	public ModelAndView GetCourseList()
	{
		try{
			List<Course> listC = courseService.getCourse();
			Map map = new HashMap();
			map.put("listC", listC);
			return new ModelAndView("/function/course-info-list",map);
		}catch (DuplicateKeyException e){
			System.out.println("GetCourseListPer error");
			return new ModelAndView("/function/course-info-list");
		}
		
	}
}
