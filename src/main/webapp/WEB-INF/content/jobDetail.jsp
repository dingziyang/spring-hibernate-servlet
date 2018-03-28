<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8s">
		<title>详情页</title>
		<link rel="shortcut icon" href="${ctx}/static/images/OA.png">
		<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/static/js/jquery-3.3.1.js" type="text/JavaScript"></script>
		<script src="${ctx}/static/js/bootstrap.js" type="text/JavaScript"></script>
		<style type="text/css">
			.col-md-3,.bigFont {
				font-size: 1.2em;
				font-weight: bold;
			}
			.detail_page {
				margin-top:20px;
				margin-bottom: 20px;
				border: 2px solid #f6f6f6;
			}
			.time,.person,.status {
				color:blue;
			}
			.printDiv {
				padding: 20px;
			}
		</style>
		
	</head>
<body>
	<div id="detail_table" class="container">
		<div class="row">
			
			<div class="col-md-6 col-md-offset-3 detail_page">
				<div class="page-header">
					<h1 align="center">《工作流详情单》</h1>
				</div>
				
				<div class="job_detail">
					<div class="row">
				      <div class="col-md-3">工作流单号</div>
				      <div class="col-md-9">${job.id}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">发起人</div>
				      <div class="col-md-9">${job.createName}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">发起时间</div>
				      <div class="col-md-9">${job.createTimeStr}</div>      
				    </div>
				    <hr>
					<div class="row">
				      <div class="col-md-3">标题</div>
				      <div class="col-md-9">${job.jobTitle}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">内容</div>
				      <div class="col-md-9">${job.jobContent}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">类型</div>
				      <div class="col-md-9">${job.jobTypeName}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">金额</div>
				      <div class="col-md-9">￥${job.money}</div>      
				    </div>
				    <hr>
				    <div class="row">
				      <div class="col-md-3">附件</div>
				      <div class="col-md-9">
				      	<span>${job.annexName}</span>
				      	<c:if test="${not empty job.jobAnnexUrl}">
					      	<button id="download" class="btn btn-primary pull-right">
					      		<i class="fa fa-download"></i>下载附件
					      	</button>
				      	</c:if>
				      </div>      
				    </div>
				    <hr>
				    
				    <div class="row">
				      <div class="col-md-2 bigFont">处理过程</div>
				      <div class="col-md-10">
				      	<c:forEach items="${pList}" var="p">
				      		<span class="time">[${p.createTimeStr}]</span>
				      		由<span class="person">[${p.prevNickName}]</span>
				      		<span class="status">[${p.processName}]</span>
				      		---><span class="person">[${p.nextNickName}]</span>
				      		<br>
				      	</c:forEach>
				      </div>      
				    </div>
				    <br>
				</div>
			</div>
			
			<div class="col-md-3 printDiv">
				<input id="print_btn" class="btn btn-success btn-lg"  
					value="打印" type="button" onclick="printThis();"/>  
			</div>
			
			<form id="downloadForm" action="${ctx}/download.do?jobId=${job.id}"
				 method="post" enctype="multipart/form-data">
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">

	$("#download").click(function(){
		$("#downloadForm").submit();
	});
	
	function printThis(){
		// 隐藏：下载文件，打印按钮
		$("#detail_table").find("#print_btn").hide();
		$("#detail_table").find("#download").hide();
		
		window.print();//打印
		window.location.reload(); // 取消打印后，刷新页面，让隐藏的按钮显示出来
	}
</script>
</html>