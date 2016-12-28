<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Map"%>
<%@page import="com.GB.ebook.activiti_util.XmlParser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ebook</title>
<link href="/assets/css/index.css" rel="stylesheet" type="text/css" />
<link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/assets/css/left.css" rel="stylesheet" type="text/css" />
<link href="/assets/css/font-awesome.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div>
		<!--顶层 
			公司logo 登录人信息 登出按钮
		-->
		<div class="top">
			<div class="account_info">
				<a href="/logout2" target="_top"><img
					src="/assets/img/logout.png"
					style="width: 25px; height: 25px; display: inline-block"></a> <a
					href="javascript:void(0)"> <span class="account_name">${user.accountName}</span></a>
				<a href="javascript:void(0)"><span class="user_name">${user.userName}</span>
				</a>
			</div>
		</div>
	</div>

	<!--左边
		功能下拉框
	-->
	<div class="left">
		<ul class="menu">
			<c:if test="${sysAccountManage==1||sysPrivilegeSetting==1}">
				<li><a href="#"><i class="icon-cogs icon-2x"></i>&nbsp&nbsp<strong>系统设置</strong></a>
					<ul>
						<c:if test="${sysAccountManage==1}">
							<li><a href="javascript:void(0)">账号管理</a>
								<ul>
									<li><a href="/system/user" target="/u/main">用户列表</a></li>
									<li><a href="/user-add" target="/u/main">增加用户</a></li>
								</ul></li>
						</c:if>
						<c:if test="${sysPrivilegeSetting==1}">
							<li><a href="javascript:void(0)">权限设置</a>
								<ul>
									<li><a href="/system/role-assignment" target="/u/main">角色分配</a></li>

								</ul></li>
						</c:if>
					</ul>
			</c:if>
			<li><a href="javascript:void(0)"><i
					class="icon-bar-chart icon-2x"></i>&nbsp&nbsp<strong>ebook平台</strong></a>
				<ul>
					<li><a href="javascript:void(0)" target="/u/main">用户信息</a>
						<ul>
							<c:if test="${personalAccontManage==1}">
								<li><a href="/ebook/account-info/${user.accountName}" target="/u/main">个人信息</a></li>
							</c:if>
						</ul></li>
					<li><a href="javascript:void(0)" target="/u/main">课程教材信息</a>

						<ul>
							<c:if test="${registerCourse==1}">
								<li><a href="/ebook/course-info-add/${user.accountName}" target="/u/main">课程教材登记</a></li>
							</c:if>
							<c:if test="${personalCourse==1}">
								<li><a href="/ebook/course-info-list-personal/${user.accountName}" target="/u/main">
										个人课程列表</a></li>
							</c:if>
							<c:if test="${listCourse==1}">
								<li><a href="/ebook/course-info-list/${user.accountName}" target="/u/main">课程列表</a></li>
							</c:if>
<%-- 							<c:if test="${queryCourse==1}"> --%>
<!-- 								<li><a href="/vehicle/person-find" target="/u/main">课程查询</a></li> -->
<%-- 							</c:if> --%>
						</ul></li>
				</ul></li>
		</ul>

	</div>
	<!-- 	天气预报-->
	<div class="wea_time">
		<%!String date;
	String dayWeather;
	String nightWeather;
	String high;
	String low;
	String suggestion;%>
		<%
			String link = "http://php.weather.sina.com.cn/xml.php?city=%B9%E3%D6%DD&password=DJOYnieT8234jlsK&day=0";
			URL url;
			try {
				url = new URL(link);
				XmlParser parser = new XmlParser(url);
				String[] nodes = {"savedate_weather", "status1", "status2", "temperature1", "temperature2", "yd_s"};
				Map<String, String> map = parser.getValue(nodes);
				date = map.get(nodes[0]);
				dayWeather = map.get(nodes[1]);
				nightWeather = map.get(nodes[2]);
				high = map.get(nodes[3]);
				low = map.get(nodes[4]);
				suggestion = map.get(nodes[5]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		%>
		<%=date%>&nbsp&nbsp&nbsp&nbsp日间天气：<%=dayWeather%>&nbsp&nbsp&nbsp&nbsp夜间天气：<%=nightWeather%>&nbsp&nbsp&nbsp&nbsp最高温：<%=high%>℃&nbsp&nbsp&nbsp&nbsp最低温：<%=low%>℃&nbsp&nbsp&nbsp&nbsp出行建议：<%=suggestion%>&nbsp&nbsp&nbsp&nbsp今日通知：<%=session.getAttribute("info")%>
	</div>

	<!--
		右边主功能显示区域
	  -->
	<div class="right">
		<iframe name="/u/main" style="width: 100%; min-height: 800px;"
			scrolling="no" frameborder="0" border="0"> </iframe>
	</div>
	<div class="footer"></div>

	<script src="/assets/js/jquery-1.9.1.js"></script>
	<script src="/assets/js/bootstrap.js"></script>
	<script>
		$(document).ready(
				function() {
					$(".menu>li>a").click(
							function(e) {
								e.preventDefault();
								$(this).siblings("ul").slideToggle().parent(
										"li").siblings().children("ul")
										.slideUp();
							});
				});
	</script>
</body>
</html>