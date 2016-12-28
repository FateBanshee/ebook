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
							<li><a href="#">个人课程信息</a></li>
							<li class="active"><a href="javascript:void(0)">个人课程查询 </a></li>

						</ol>
					</div>
				</div>
				<hr>
				<form action="/course/personalSearch" style="width: 900px;">
					<div class="col-md-offset-3 col-md-3">
						<input type="radio" name="condition" value="courseId">课程编号
						<input type="radio" name="condition" value="courseName">课程名称
					</div>
					<div style="display: none;">
						<input name="teacherName" type="text" value="${accountName}" />
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<input type="text" name="str"
								class="form-control input-sm" maxlength="10"
								placeholder="请输入查询信息"> <span class="input-group-btn">
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
						<th>教材</th>
						<th>教材出版社</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${listC}" var="c">
						<tr class="a">
							<td class="courseId"">${c.courseId}</td>
							<td class="courseName">${c.courseName}</td>
							<td class="accountName" style="display: none;">${c.teacherName}</td>
							<td class="bookName">${c.bookName}</td>
							<td class="bookPress">${c.bookPress}</td>
							<td><a class="modify" href="javascript:void(0)">修改</a>| <a
								class="del"
								href="/deleteCourse/${c.courseId}/${c.teacherName}">删除</a></td>
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

	<!-- 	新添加的地方-->
	<div id="code">
		<div class="title">
			<span>修改信息</span>
			<div class="close">
				<a href="javascript:void(0)" id="closebt"><img
					src="/assets/img/close.gif"></a>
			</div>
		</div>
		<form action="/updateCourse" method="get">
			<div class="goodtxt">

				<table class="table table-bordered table-hover">
					<input type="hidden" name="CourseInfo" class="CourseInfo" />
					<tr>
						<td>课程编号</td>
						<td><input name="courseId" class="courseId" /></td>
					</tr>
					<tr>
						<td>课程名字</td>
						<td><input name="courseName" class="courseName" /></td>
					</tr>
					<tr>
						<td>教材</td>
						<td><input name="bookName" class="bookName" type="text"
							placeholder="请输入教材名称" required /></td>
					</tr>

					<tr>
						<td>教材出版社</td>
						<td><input name="bookPress" class="bookName" type="text"
							placeholder="请输入教材出版社" required /></td>
					</tr>
				</table>
			</div>
			<button type="submit" class="btn btn-sm one mod">修改</button>
			<button id="comeback" class="btn btn-sm one">返回</button>
		</form>



	</div>

	<script>
		$(function() {
			$(".mod").click(function(e) {
				if (confirm("你确认要修改吗？")) {
					return true;
				} else {
					e.preventDefault();
				}
				;
			});

			$(".del").click(function(e) {
				if (confirm("你确认要删除吗？")) {
					return true;
				} else {
					e.preventDefault();
				}
				;
			});

			$('.a>td>.modify').click(
					function() {
						var courseId = $(this).parent().siblings(
								".courseId").text();
						var courseName = $(this).parent().siblings(
								".courseName").text();
						var bookName = $(this).parent().siblings(".brand").text();
						var bookPress = $(this).parent().siblings(
								".bookPress").text();
						$("input.courseId").val(
								courseId);
						$("input.courseName").val(courseName);
						$("input.bookName").val(bookName);
						$("input.bookPress").val(bookPress);
						$('#code').center();
						$('#goodcover').show();
						$('#code').fadeIn();
					});
			$('#closebt').click(function() {
				$('#code').hide();
				$('#goodcover').hide();
			});
			$('#goodcover').click(function() {
				$('#code').hide();
				$('#goodcover').hide();
			});
			$("#comeback").click(function(e) {
				$("#code").hide();
				e.preventDefault();
			});

			jQuery.fn.center = function(loaded) {
				var obj = this;

				if (!loaded) {

					obj.css({
						'position' : 'absolute'
					});
					obj
							.css({
								'top' : ($(window).height() - $('#code')
										.height()) * 0.2,
								'left' : 300
							/* left_position */
							});
					$(window).bind('resize', function() {
						obj.center(!loaded);
					});
					$(window).bind('scroll', function() {
						obj.center(!loaded);
					});

				} else {
					obj.stop();
					obj.css({
						'position' : 'absolute'
					});
					obj.animate({
						'top' : 100
					/* top_position */
					}, 200, 'linear');
				}
			}

		});
	</script>

</body>
</html>