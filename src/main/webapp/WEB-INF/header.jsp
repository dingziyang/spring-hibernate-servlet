<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="common.jsp"%>

<style type="text/css">
	header {
		font-family: fantasy;
		font-size: 25px;
		background-color: #DCF1F2;
		vertical-align: center;
		padding: 15px;
		border-bottom: 1px solid #d6d6d6;
	}
	header a,span {
		font-size: 14px;
	}
	header>span>span {
		color:blue;
	}
	header>img {
		margin-left: 5%;
	}
</style>

<header>
	<img alt="" src="${ctx}/static/images/logo.png">
	<b>法制在线办公系统</b>
	<span class="pull-right">
		欢迎你，<span>${ userSession.userNickName}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="bnt btn-primary pull-right" href="${ctx}/loginOut.do">退出登录</a>
	</span>
</header>
