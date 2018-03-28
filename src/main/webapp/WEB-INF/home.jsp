<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="common.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8s">
		<title>主页</title>
		<link rel="shortcut icon" href="${ctx}/static/images/OA.png">
		<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/static/js/jquery-3.3.1.js" type="text/JavaScript"></script>
		<script src="${ctx}/static/js/bootstrap.js" type="text/JavaScript"></script>
		<style type="text/css">
			body {
				background-color: #fff;
			}
			table th,td{
				text-align: center;
				vertical-align: middle!important;
			}
			.three {
				display: flex;
				justify-content: space-around;
				color: #f5f5f5;
				font-size: 1.5em;
				margin: 20px;
			}
			.three>div {
				border-radius:10px;
				/* box-shadow: 5px 5px 5px #888888; */
			}
			.nav:hover {
				opacity:0.7;
				transition: 0.2s ease;
				cursor:pointer;
			}
			/*#12D3F3,#61EEC3, #D6E578 */
			.icheck {
				background: -webkit-linear-gradient(left, #12D3F3 , #61EEC3); /* Safari 5.1 - 6.0 */
			    background: -o-linear-gradient(right, #12D3F3, #61EEC3); /* Opera 11.1 - 12.0 */
			    background: -moz-linear-gradient(right, #12D3F3, #61EEC3); /* Firefox 3.6 - 15 */
			    background: linear-gradient(to right, #12D3F3 , #61EEC3); /* 标准的语法 */
				padding: 20px;
			}
			.icreate {
				/* background-color: deepskyblue; */
				background: -webkit-linear-gradient(left, #61EEC3 , #D6E578); /* Safari 5.1 - 6.0 */
			    background: -o-linear-gradient(right, #61EEC3, #D6E578); /* Opera 11.1 - 12.0 */
			    background: -moz-linear-gradient(right, #61EEC3, #D6E578); /* Firefox 3.6 - 15 */
			    background: linear-gradient(to right, #61EEC3 , #D6E578); /* 标准的语法 */
				padding: 20px;
			}
			.newjob {
				background: -webkit-linear-gradient(left, #D6E578 , #12D3F3); /* Safari 5.1 - 6.0 */
			    background: -o-linear-gradient(right, #D6E578, #12D3F3); /* Opera 11.1 - 12.0 */
			    background: -moz-linear-gradient(right, #D6E578, #12D3F3); /* Firefox 3.6 - 15 */
			    background: linear-gradient(to right, #D6E578 , #12D3F3); /* 标准的语法 */
				padding: 20px;
			}
			.choose {
				box-shadow: 10px 15px 10px #888888;
			}
		</style>
		
		<script type="text/javascript">
	        function loadContent(url) {
	            $("#frame").load("${ctx}/" + url + ".do");
	        }
    	</script>
	</head>
<body>
	<!--1.头部  -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!--2.导航部分  -->
	<div class="three">
		<c:if test="${userSession.roleLevel < 3}">
			<div class="nav icheck" onclick="loadContent('icheck');">要我审核的</div>
		</c:if>
		<c:if test="${userSession.roleLevel > 1}">
			<div class="nav icreate" onclick="loadContent('icreate');">由我发起的</div>
			<div class="nav newjob" onclick="loadContent('newjob');">创建工作流</div>
		</c:if>
	</div>
	
	<!--3.主体内容  -->
	<div class="container" id="frame">
		<!-- 3.1 要我审核的  -->
				
		<!-- 3.2 由我创建的  -->
		
		<!-- 3.3 新建工作流  -->
	</div>	
	
	<!--4.页脚  -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">

	$(".icheck").click(function(){
		$(".nav").removeClass("choose");
		$(".icheck").addClass("choose");
		//$(".container").find(".content_div").hide();
		//$(".container").find("#icheck_div").show();
	});
	
	$(".icreate").click(function(){
		$(".nav").removeClass("choose");
		$(".icreate").addClass("choose");
	});
	
	$(".newjob").click(function(){
		$(".nav").removeClass("choose");
		$(".newjob").addClass("choose");
	});
	
	// 页面加载后默认点击第一个
	$(function(){ 
		$(".three").find("div:eq(0)").trigger("click");
	}); 
</script>

</html>