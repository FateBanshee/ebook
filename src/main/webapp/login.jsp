<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.List"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/assets/css/login.css" media="screen"
	type="text/css" />
<link type="text/css" href="/assets/css/style.css" rel="stylesheet" />
<script src="/assets/js/jquery-1.9.1.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/md5.js" type="text/javascript"></script>
<link rel="Shortcut Icon" href="/assets/img/favicon.ico" />
<link rel="stylesheet" href="/assets/css/bootstrap.css" />
<link rel="stylesheet" href="/assets/css/system-make.css" />
<style>

</style>
<title>ebook</title>
</head>
<body>

	<div id="level">
		<div id="content">
			<div id="img">
				<img id="cmccimg" src="/assets/img/ebook01.png">
			</div>
			<div id="window" style="display: none;">
				<div class="page page-front">
					<form action="/login" method="post" onsubmit="return check();">
						<div class="page-content">
							<div class="input-row">
								<label class="label fadeIn">账&nbsp;号</label> <input
									id="username" name="userName" type="text"
									placeholder="请输入账号或者电子邮箱或者手机号码" class="input fadeIn delay1" style="color:black;"
									required />
							</div>
							<div class="input-row">
								<label class="label fadeIn delay2">密&nbsp;码</label> <input
									id='password' type="password" name="password"
									placeholder="请输入密码" class="input fadeIn delay3" style="color:black;" required />
							</div>
							<div class="input-row perspective">
								<button id="submit" class="button load-btn fadeIn delay4"
									type="submit">
									<span class="default"><i class="ion-arrow-right-b"></i>登录</span>
								</button>
							</div>
						</div>
					</form>
					<div class="input-row perspective a">
						<a class="modify register" href="javascript:void(0)"><button
								class="button load-btn fadeIn delay4" type="button">注册</button></a>
					</div>
				</div>
			</div>
		</div>
		<div id="gears">
			<div id="gears-static"></div>
			<div id="gear-system-1">
				<div class="shadow" id="shadow15"></div>
				<div id="gear15"></div>
				<div class="shadow" id="shadow14"></div>
				<div id="gear14"></div>
				<div class="shadow" id="shadow13"></div>
				<div id="gear13"></div>
			</div>
			<div id="gear-system-2">
				<div class="shadow" id="shadow10"></div>
				<div id="gear10"></div>
				<div class="shadow" id="shadow3"></div>
				<div id="gear3"></div>
			</div>
			<div id="gear-system-3">
				<div class="shadow" id="shadow9"></div>
				<div id="gear9"></div>
				<div class="shadow" id="shadow7"></div>
				<div id="gear7"></div>
			</div>
			<div id="gear-system-4">
				<div class="shadow" id="shadow6"></div>
				<div id="gear6"></div>
				<div id="gear4"></div>
			</div>
			<div id="gear-system-5">
				<div class="shadow" id="shadow12"></div>
				<div id="gear12"></div>
				<div class="shadow" id="shadow11"></div>
				<div id="gear11"></div>
				<div class="shadow" id="shadow8"></div>
				<div id="gear8"></div>
			</div>
			<div class="shadow" id="shadow1"></div>
			<div id="gear1"></div>
			<div id="gear-system-6">
				<div class="shadow" id="shadow5"></div>
				<div id="gear5"></div>
				<div id="gear2"></div>
			</div>
			<div id="chain-circle"></div>
			<div id="chain"></div>
		</div>
	</div>
	</div>


	<div id="code">
		<div class="title">
			<span>注册信息</span>
			<div class="close">
				<a href="javascript:void(0)" id="closebt"><img
					src="/assets/img/close.gif"></a>
			</div>
		</div>
		<form action="/addUser2" method="post" onsubmit="return check();">
			<div class="goodtxt">
				<table class="table table-bordered">
					<tr>
						<td>用户名</td>
						<td><input name="userName" type="text"
							class="userName form-control" placeholder="请输入用户账号"
							maxlength="20"></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input id='password' name="password" type="password"
							class="password form-control" placeholder="请输入密码" maxlength="20"
							required></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input name="accountName" type="text"
							class="accountName form-control" placeholder="请输入姓名"
							maxlength="20"></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input name="email" type="email"
							class="email form-control" placeholder="请输入邮箱" maxlength="20"></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input name="phone" type="text"
							class="phone form-control" placeholder="请输入电话" maxlength="20"></td>
					</tr>
				</table>
			</div>
			<button onclick="mod()" class="btn one">注册</button>
			<button id="comeback" class="btn one">返回</button>
		</form>
	</div>

	<script>
		function mod() {
			if (confirm("你确认要注册吗？")) {
				return true;
			} else {
				return false;
			}
		}

		$(function() {
			var d = '${infomation}';

			if (d.length != 0 && d != null)
				alert(d);
			console.log(d);

			$(".register").click(
					function() {
						var userName = $(this).parent().siblings(".userName")
								.text();
						var phone = $(this).parent().siblings(".phone").text();
						var email = $(this).parent().siblings(".email").text();
						var accountName = $(this).parent().siblings(
								".accountName").text();
						var password = $(this).parent().siblings(".password")
								.text();
						$("input.userName").val(userName);
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
								'left' : 518
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
	<script type="text/javascript" src='/assets/js/jquery-1.9.1.js'></script>
	<script type="text/javascript" src="/assets/js/login.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#img").fadeIn(5000);
			$("#gears").fadeIn(5000);
		});

		function check() {
			if ($("#password").val() != "") {
				var hash = hex_md5($("#password").val());
				document.getElementById("password").value = hash;
			}
		};
	</script>

	<script type="text/javascript" language="javascript">
		function ale(message) {
			if (message == "user_null") {
				alert("用户不存在。");
			} else if (message == "psw_incorrect") {
				alert("密码错误。");
			} else if (message == "role_null")
				alert("该用户尚未分配角色，请联系管理员。");

		}
	</script>
	<%
		String id = (String) request.getAttribute("id");
		if (id != null) {
			out.println("<script>ale('" + id + "')</script>");
		}
	%>


</body>
</html>