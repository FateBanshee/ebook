<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ebook - 账号管理</title>
<link rel="stylesheet" href="/assets/css/bootstrap.css">
<link rel="stylesheet" href="/assets/css/system.css">
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/md5.js" type="text/javascript"></script>

<style>
.star {
	color: red;
	font-size: 20px;
	display: inline-block;
	padding-top: 8px;
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
							<li><a href="javascript:void(0)">系统设置</a></li>
							<li><a href="javascript:void(0)">账号管理</a></li>
							<li><a href="javascript:void(0)">添加角色</a></li>
						</ol>
					</div>
				</div>
				<form class="form-horizontal" style="width: 900px;"
					action="/addUser" method="post" onsubmit="return check();">
					<!-- 
					<td><input name="isExist" class="isExist" value="1"
						style='display: none' /></td> -->

					<div class="form-group">
						<label class="col-sm-4 control-label">账号</label>
						<div class="col-sm-5">
							<input name="userName" type="text" class="form-control"
								placeholder="请输入用户账号" maxlength="20">
						</div>
						<span class="star">*</span>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">密码</label> <span
							class="star">*</span>
						<div class="col-sm-5">
							<input id='password' name="password" type="password"
								class="form-control" placeholder="请输入密码" maxlength="20" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-5">
							<input name="accountName" type="text" class="form-control"
								placeholder="请输入姓名" maxlength="20">
						</div>
						<span class="star">*</span>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-5">
							<input name="email" type="email" class="form-control"
								placeholder="请输入邮箱" maxlength="20">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">电话</label>
						<div class="col-sm-5">
							<input name="phone" type="tel" class="form-control"
								placeholder="请输入电话" maxlength="20">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-5">
							<button type="submit" class="btn btn-default">新建</button>
							<button type="submit" class="btn btn-default"
								onclick="javascript:history.back(-1);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>



</body>
</html>