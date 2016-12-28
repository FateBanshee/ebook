<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html lang="en">
<head>
<meta charset="UTF-8">
<title>ebook信息管理-课程列表</title>
<link rel="stylesheet" href="/assets/css/bootstrap.css">
<link rel="stylesheet" href="/assets/css/system.css">
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<link rel="stylesheet" href="/assets/css/system-make.css">
<script src="/assets/js/fenye.js"></script>

</head>
<body>

	<div class="container" style="width: 1100px;">
		<div class="row">
			<div class="col-md-12 main">
				<div class="row">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a href="#">ebook管理</a></li>
							<li><a href="#">课程信息</a></li>

						</ol>
					</div>
				</div>
				<hr>
				<form action="/course/search" style="width: 900px;">
					<div class="col-md-offset-3 col-md-3">
						<input type="radio" name="condition" value="courseId">课程编号
						<input type="radio" name="condition" value="courseName">课程名称
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<input type="text" name="str" class="form-control input-sm"
								maxlength="10" placeholder="请输入查询信息"> <span
								class="input-group-btn">
								<button type="submit" class="btn  btn-sm">搜索</button>
							</span>
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-12">
				<table class="table table-bordered table-striped" id="idData"
					style="width: 1050px;">
					<h4 style="text-align: center;">课程列表</h4>
					<tr>
						<th>课程编号</th>
						<th>课程名字</th>
						<th>教师</th>
						<th>教材</th>
						<th>教材出版社</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${listC}" var="c">
						<tr class="a">
							<td class="courseId"">${c.courseId}</td>
							<td class="courseName">${c.courseName}</td>
							<td class="accountName">${c.teacherName}</td>
							<td class="bookName">${c.bookName}</td>
							<td class="bookPress">${c.bookPress}</td>
							<td><a class="buy" href="/buy/${c.bookName}/${c.bookPress}">购买</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<table width="70%" align="right">
					<tr>
						<td><div id="barcon" name="barcon"></div></td>
					</tr>
				</table>
			</div>
		</div>
	</div>




</body>
</html>