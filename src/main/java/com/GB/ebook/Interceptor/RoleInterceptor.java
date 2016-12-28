package com.GB.ebook.Interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RoleInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		System.out.println("URL = " + request.getRequestURI());
		// 账号管理
		if (request.getRequestURI().equals("/system/user") || request.getRequestURI().equals("/user-add")) {
			// 有权访问
			if ((Integer) request.getSession().getAttribute("sysAccountManage") == 1)
				return true;
			// 无权访问
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// 权限设置
		else if (request.getRequestURI().equals("/system/role-assignment")
				|| request.getRequestURI().equals("/system/role-authorization")
				|| request.getRequestURI().equals("/system/role-authorization-add")) {
			if ((Integer) request.getSession().getAttribute("sysPrivilegeSetting") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// 参数配置
		else if (request.getRequestURI().equals("/system/parameter")) {
			if ((Integer) request.getSession().getAttribute("sysParameterSetting") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		
		// ebook-account-info
		else if (request.getRequestURI().equals("/ebook/account-info")) {
			if ((Integer) request.getSession().getAttribute("personalAccontManage") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// ebook-account-password
		else if (request.getRequestURI().equals("/ebook/account-password")) {
			if ((Integer) request.getSession().getAttribute("personalPassword") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// ebook-teacher
		else if (request.getRequestURI().equals("/ebook/teacher")) {
			if ((Integer) request.getSession().getAttribute("personalTeacherApprove") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// ebook-course-info-add
		else if (request.getRequestURI().equals("ebook/course-info-add")) {
			if ((Integer) request.getSession().getAttribute("registerCourse") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// ebook-course-info-list-personal
		else if (request.getRequestURI().equals("/ebook/course-info-list-personal")) {
			if ((Integer) request.getSession().getAttribute("personalCourse") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// ebook-course-info-list
		else if (request.getRequestURI().equals("/ebook/course-info-list")) {
			if ((Integer) request.getSession().getAttribute("listCourse") == 1)
				return true;
			else
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			return false;
		}
		// 非系统功能
		else
			return true;
	}
}