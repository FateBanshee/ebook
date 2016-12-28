<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.List"%>
<head>
<meta charset="utf-8">
<title>ebook - 用户信息</title>
<link rel="stylesheet" href="/assets/css/bootstrap.css">
<link rel="stylesheet" href="/assets/css/system-make.css">
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/fenye.js"></script>
<script src="/assets/js/md5.js" type="text/javascript"></script>
<style>
.1 {
	display: none;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12 main">
				<div class="row">
					<div class="col-md-12">
						<ol class="breadcrumb">
							<li><a href="javascript:void(0)">用户信息</a></li>
							<li><a href="javascript:void(0)">个人信息</a></li>
						</ol>
					</div>
				</div>
				<br>



				<form action="/updateUserInfo" method="post"
					onsubmit="return check();">
					<div class="goodtxt">
						<table class="table table-bordered">

							<td style='display: none'  ><input name="userName" class="userName form-control"
								style='display: none' /></td>

							<tr>
								<td>姓名</td>
								<td><input name="accountName"
									class="accountName  form-control" maxlength="20" placeholder="${user.accountName}"/></td>
							</tr>

							<tr>
								<td>密码</td>
								<td><input id='password' name="password" type="password"
									class="password  form-control" maxlength="20"
									placeholder="请输入新密码,如无需修改,请留空" /></td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td><input name="email" type="email"
									class="email  form-control" maxlength="20" placeholder="${user.email}"/></td>
							</tr>
							<tr>
								<td>电话</td>
								<td><input name="phone" class="phone form-control"
									maxlength="20" placeholder="${user.phone}"/></td>
							</tr>
						</table>
					</div>
					<button onclick="mod()" class="btn one">修改</button>
					<button id="comeback" class="btn one">返回</button>
				</form>
			</div>
		</div>
	</div>



	<script>
		function mod() {
			if (confirm("你确认要修改吗？")) {
				return true;
			} else {
				return false;
			}
		}

		function check() {
			if ($("#password").val() != "") {
				var hash = hex_md5($("#password").val());
				document.getElementById("password").value = hash;
			}
		};

		$(function() {
			var d = '${infomation}';

			if (d.length != 0 && d != null)
				alert(d);
			console.log(d);

			$('.a>td>.modify').click(
					function() {
						var userName = $(this).parent().siblings(".userName")
								.text();
						var roleId = $(this).parent().siblings(".roleId")
								.text();

						var phone = $(this).parent().siblings(".phone").text();
						var email = $(this).parent().siblings(".email").text();
						var accountName = $(this).parent().siblings(
								".accountName").text();
						var password = $(this).parent().siblings(".password")
								.text();

						$("input.userName").val(userName);
						$("input.roleId").val(roleId);
						$("input.phone").val(phone);
						$("input.email").val(email);
						$("input.accountName").val(accountName);
						$("input.password").val(password);
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