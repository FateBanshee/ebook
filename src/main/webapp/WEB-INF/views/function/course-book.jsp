<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html lang="en">
<head>
<meta charset="UTF-8">
<title>ebook信息管理-购买教材</title>
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
							<li><a href="#">购买教材</a></li>

						</ol>
					</div>
				</div>
				<hr>
			</div>

			<div class="col-md-12">
				<table class="table table-bordered table-striped" id="idData"
					style="width: 1050px;">
					<h4 style="text-align: center;">教材列表</h4>
					<tr>
						<th>教材</th>
						<th>价格</th>
						<th>来源</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${bookItems}" var="b">
						<tr class="a">
							<td class="bookname"">${b.title}</td>
							<td class="price">${b.price}</td>
							<td class="site">${b.site}</td>
							<td><a class="buy" href="${b.url}" target="_blank">跳转</a>
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