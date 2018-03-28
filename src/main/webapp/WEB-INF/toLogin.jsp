<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户登录</title>
	<link rel="shortcut icon" href="${ctx}/static/images/OA.png">
	<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<script src="${ctx}/static/js/jquery-3.3.1.js" type="text/JavaScript"></script>
	<script src="${ctx}/static/js/bootstrap.js" type="text/JavaScript"></script>
	<style type="text/css">
		.img {
			width:100%;
			height:100vh;
			position: relative;
		}
		img {
			width:100%;
			height:100%;
		}
		.body-wrap {
			position: absolute;	
			padding:30px 80px;
			top: 50%;
			left:50%;
			transform: translate(-50% ,-50%);
			background-color: #000;
			background: rgba(0,0,0,0.6);
			border-radius:5px;
			color: #fff;
		}
		.login {
			width: 100%;
			margin-top: 20px;
		}
		p>input {
			border: 1px solid #999;
			background-color: transparent;
			border-radius:3px;
			padding: 3px 10px;
		}
		p {
			margin-bottom: 20px;
		}
		h1 {
			margin-bottom: 30px;
		}
	</style>
</head>
<body>
	<div class="img">
		<img alt="" src="${ctx}/static/images/bg3.jpg">
		<div class='body-wrap'>
			<h1 align="center">综合法制平台</h1>
			<form action="${ctx}/login.do" method="post">
				<p>用户：<input type="text" name="username"/></p>
				<p>密码：<input type="password" name="password"/></p>
				<span>${msg}</span>
				<button class="btn btn-primary login" >登录</button>
			</form>
		</div>
	</div>
</body>
</html>