<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<title>ebook信息管理 - 信息登记</title>
<link rel="stylesheet" href="/assets/css/bootstrap.css">
<link rel="stylesheet" href="/assets/css/system.css">
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/md5.js" type="text/javascript"></script>
<style type="text/css">
.star {
	color: red
}
</style>
</head>
<body>

	<div class="container" style="width: 900px;">
		<div class="row">
			<div class="col-md-12 main">
				<div class="row">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a href="#">ebook</a></li>
							<li><a href="#">课程教材信息</a></li>
							<li><a href="#">信息登记 </a></li>
						</ol>
					</div>
				</div>

				<form class="form-horizontal col-md-12" style="width: 900px;"
					action="/addCourse" method="get">
					<h4 style="text-align: center;">课程信息登记</h4>

					<div class="form-group">
						<label class="col-sm-3 control-label">课程编号</label>
						<div class="col-sm-5">
							<input name="courseId" class="form-control" type="text"
								placeholder="请输入课程编号" required />
						</div>
						<span class="star">*</span>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">课程名称</label>
						<div class="col-sm-5">
							<input name="courseName" class="form-control" type="text"
								placeholder="请输入课程名称" required />
						</div>
						<span class="star">*</span>
					</div>
					<div class="form-group" style="display: none;">
						<label class="col-sm-3 control-label">教师名字</label>
						<div class="col-sm-5">
							<input name="teacherName" class="form-control" type="text"
								placeholder="请输入教师名字" value="${accountName}" required />
						</div>
						<span class="star">*</span>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">推荐教材</label>
						<div class="col-sm-5">
							<input name="bookName" class="form-control" type="text"
								placeholder="请输入教材名称" required />
						</div>
						<span class="star">*</span>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">出版社</label>
						<div class="col-sm-5">
							<input name="bookPress" class="form-control" type="text"
								placeholder="请输入教材出版社" required />
						</div>
						<span class="star">*</span>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-5">
							<button type="submit" class="btn btn-default add">登记</button>
							<button type="reset" class="btn btn-default">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
$(function(){
	$(".add").click(function(e) {
		if (confirm("你确认要登记吗？")) {
			return true;
		} else {
			e.preventDefault();
		}
		;
	});
});
</script>

</body>
</html>