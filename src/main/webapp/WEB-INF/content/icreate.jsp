<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common.jsp"%>

<style type="text/css">
	.detail_btn,.rePost,.toModify {
		padding: 3px;
		color: #fff!important;
		margin-left: 2px;
	}
	.detail_btn {
		background-color: #00ffff;
	}
</style>

<div id="icreate_select">
	<br>
	<div class="row">
		<div class="col-md-5">
			<div class="input-group">
		        <span class="input-group-addon">工作流号</span>
		        <input id="icreate_id" type="text" class="form-control">
		    </div>
		</div><div class="col-md-5">
			<div class="input-group">
		    	<span class="input-group-addon">标题</span>
		        <input id="icreate_title" type="text" class="form-control">
		    </div>
		</div>
		<div class="col-md-2" style="text-align: center;">
			<button id="icreate_search_btn" class="btn btn-primary pull-center">
				<i class="glyphicon glyphicon-search"></i>&nbsp;搜索
			</button>
		</div>
	</div>
	<br>
</div>

<div id="icreate_div" class="content_div">
	<!-- <table class="table table-hover table-striped">
	  <thead>
	    <tr>
	      <th>工作流号</th>
	      <th>标题</th>
	      <th>内容</th>
	      <th>类型</th>
	      <th>附件</th>
	      <th>创建人</th>
	      <th>创建时间</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td>1</td>
	      <td>测试1</td>
	      <td>我今天请假</td>
	      <td>请假</td>
	      <td>下载a</td>
	      <td>马云老板</td>
	      <td>2018-01-01</td>
	      <td>
	      	<button class="btn btn-primary">详情</button>
	      </td>
	    </tr>
	  </tbody>
	</table> -->
</div>

<div id="icreate_paging">
	<!-- <ul class="pagination pull-right">
	    <li><a href="#">&laquo;</a></li>
	    <li><a href="#">1</a></li>
	    <li><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">5</a></li>
	    <li><a href="#">&raquo;</a></li>
	</ul> -->
</div>

<div class="modal fade" id="jobEditModal" tabindex="-1" role="dialog" aria-labelledby="jobModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="jobModalLabel">修改</h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal">
			<input type="hidden" name="jobid" id="jobid">
            <div class="form-body">
				<div class="form-group">
                    <label class="col-md-3 control-label">标题</label>
                    <div class="col-md-6">
                       <input id="job_title" type="text" class="form-control input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">内容</label>
                    <div class="col-md-6">
                       <input id="job_content" type="text" class="form-control input">
                    </div>
                </div>
            </div>
		 </form>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default md-btn" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary save md-btn">保存</button>
    </div>
        </div>
     </div>
</div>

<script type="text/javascript">
	var currPage = 1;
	var maxPage;

	// 加载搜索部分
	function loadSearch(result){
		
	}
	// 加载表格部分
	function loadTable(result){
		var tableHtml = "<table class='table table-hover table-striped table-bordered'>"
			  +"<thead>"
				  +"<tr>"
					  +"<th>工作流号</th>"
					  +"<th>标题</th>"
					  +"<th>内容</th>"
					  +"<th>类型</th>"
					  +"<th>附件</th>"
					  +"<th>金额</th>"
					  +"<th>创建时间</th>"
					  +"<th>操作</th>"
				  +"</tr>"
			  +"</thead>"
			  +"<tbody>";
		for (var i = 0; i < result.resultlist.length; i ++) {
			var trHtml = "<tr>"
						      +"<td>"+result.resultlist[i].id+"</td>"
						      +"<td class='tt'>"+result.resultlist[i].jobTitle+"</td>"
						      +"<td class='cc'>"+result.resultlist[i].jobContent+"</td>"
						      +"<td>"+result.resultlist[i].jobTypeName+"</td>"
						      +"<td>"+result.resultlist[i].annexName+"</td>"
						      +"<td>￥"+result.resultlist[i].money+"</td>"
						      +"<td>"+result.resultlist[i].createTimeStr+"</td>"
						      +"<td>"
						      	+"<button class='btn detail_btn'><a href='/springMaven/jobDetail.do?id="+
						      			result.resultlist[i].id+"' target='_blank'>详情</a></button>";
			if(result.resultlist[i].needModify == 2){
				var acHtml = "<button class='rePost btn btn-warning' data-jid='"+result.resultlist[i].id+"'>重新上报</button>"
					        +"<button class='toModify btn btn-success' data-jid='"+result.resultlist[i].id+"'>修改</button>";
				trHtml += acHtml;
			}
			trHtml += "</td></tr>";
			tableHtml += trHtml;
		}
		tableHtml += "</tbody></table>";
		$("#icreate_div").append(tableHtml);
	}
	// 加载分页部分
	function loadPage(result){
		var ulHtml = "<ul class='pagination pull-right'>"
					    +"<li><a>上一页</a></li>";
	    for (var n = 1; n <= result.maxPage; n++) {
	    	if(currPage == n){
	    		var liHtml = "<li><a style='background-color:#337AB7;color:#fff'>"+n+"</a></li>";
	    	}else{
				var liHtml = "<li><a>"+n+"</a></li>";
	    	}
			ulHtml += liHtml;
		}
	    ulHtml = ulHtml +"<li><a>下一页</a></li>"
	    +"<li class='msg disabled'><a>共"+result.maxPage+"页/共"+result.total+"条</a></li>"
				+"</ul>";
		$("#icreate_paging").append(ulHtml);
		maxPage = result.maxPage;
	}

	function sendAjax(id,title,page,limit){
		$.ajax({
			url : "${ctx}/icreateList.do",
			type : "get",
			dataType: "json",
			data : {"page":page, "limit":limit,"id":id,"title":title},
			success : function(result) {
				console.log(result);
				
				//删除dom。新建dom
				loadSearch(result);
				
				$("#icreate_div").html("");
				loadTable(result);
				
				$("#icreate_paging").html("");
				loadPage(result);
			},
			error : function(e) {
				alert("icreateList.do 失败！");
			}
		});
	}
	
	// 页面加载完时，加载内容
	$(function(){
		sendAjax(null,null,currPage,5);
	});
	
	// 搜索
	$("#icreate_select").on("click","#icreate_search_btn",function(){
		var id = $("#icreate_select").find("#icreate_id").val();
		var title = $("#icreate_select").find("#icreate_title").val();
		sendAjax(id,title,1,5);
	});
	
	// 点击分页页码
	$("#icreate_paging").on("click","li:not(.msg)",function(){
		var id = $("#icreate_select").find("#icreate_id").val();
		var title = $("#icreate_select").find("#icreate_title").val();
		
		var num = $(this).find("a").text();
		if(num === "上一页"){
			if(currPage == 1){
				alert("当前已经是第一页了");
			}else{
				currPage -- ;
			}
		} else if(num === "下一页"){
			if(currPage == maxPage){
				alert("当前已经是最后一页了");
			}else{
				currPage ++;
			}
		} else {
			currPage = num;
		}
		sendAjax(id,title,currPage,5);
	});
	
	// 重新上报
	$("#icreate_div").on("click",".rePost",function(){
		var jid = $(this).data("jid");
		$.ajax({
			url : "${ctx}/handleJob.do",
			type : "get",
			//dataType: "json",
			data : {"typeValue":5,"jobId":jid},
			success : function(result) {
				alert(result);
				// $(".three").find("div:eq(1)").trigger("click");
				window.location.reload();
			},
			error : function(e) {
				alert("handleJob.do 失败！重新上报失败");
			}
		});
	});
	
	// 修改按钮
	$("#icreate_div").on("click",".toModify",function(){
		var jid = $(this).data("jid");
		var title = $(this).parent().parent().find(".tt").text();
		var content = $(this).parent().parent().find(".cc").text();
		console.log(title,content);
		
		//弹框
		$("#jobEditModal").find("#jobid").val(jid);
		$("#jobEditModal").find("#job_title").val(title);
		$("#jobEditModal").find("#job_content").val(content);
		$("#jobEditModal").modal("show");
	});
	
	// 弹框的保存按钮
	$("#jobEditModal").on("click",".save",function(){
		
		var jid = $("#jobEditModal").find("#jobid").val();
		var title = $("#jobEditModal").find("#job_title").val();
		var content = $("#jobEditModal").find("#job_content").val();
		
		if (jid === null || jid === undefined || jid === '') {
			alert("找不到jid");
			return;
		}
		
		if (title === null || title === undefined || title === '') {
			alert("标题不能为空");
			return;
		}
		if(title.trim().length == 0){
			alert("标题不能为空");
			return;
		}
		
		if (content === null || content === undefined || content === '') {
			alert("内容不能为空");
			return;
		}
		if(content.trim().length == 0){
			alert("内容不能为空");
			return;
		}
		
		$.ajax({
			url : "${ctx}/modifyJob.do",
			type : "post",
			//dataType: "json",
			data : {"jid":jid,"title":title,"content":content},
			success : function(result) {
				alert(result);
				$("#jobEditModal").modal("hide");
				window.location.reload();
			},
			error : function(e) {
				alert("handleJob.do 失败！重新上报失败");
			}
		}); 
	});
	
	
	
</script>