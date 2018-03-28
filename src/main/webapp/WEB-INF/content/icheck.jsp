<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common.jsp"%>

<style type="text/css">
	.select_type {
	    height: 27px;
	    border-radius: 3px;
	    margin-left: 5px;
	    border: 1px solid gainsboro;
	    vertical-align: middle;
	}
	.detail_btn {
		padding: 3px;
		color: #fff!important;
		background-color: #00ffff;
	}
</style>

<div id="icheck_select">
	<br>
	<div class="row">
		<div class="col-md-5">
			<div class="input-group">
		        <span class="input-group-addon">工作流号</span>
		        <input id="icheck_id" type="text" class="form-control">
		    </div>
		</div><div class="col-md-5">
			<div class="input-group">
		    	<span class="input-group-addon">标题</span>
		        <input id="icheck_title" type="text" class="form-control">
		    </div>
		</div>
		<div class="col-md-2">
			<button id="icheck_search_btn" class="btn btn-primary pull-center">
				<i class="glyphicon glyphicon-search"></i>&nbsp;搜索
			</button>
		</div>
	</div>
	<br>
</div>

<div id="icheck_div" class="content_div">
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

<div id="icheck_paging">
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
						      +"<td>"+result.resultlist[i].jobTitle+"</td>"
						      +"<td>"+result.resultlist[i].jobContent+"</td>"
						      +"<td>"+result.resultlist[i].jobTypeName+"</td>"
						      +"<td>"+result.resultlist[i].annexName+"</td>"
						      +"<td>￥"+result.resultlist[i].money+"</td>"
						      +"<td>"+result.resultlist[i].createTimeStr+"</td>"
						      +"<td>"
						      	+"<button class='btn detail_btn'><a href='/springMaven/jobDetail.do?id="
						      			+result.resultlist[i].id+"' target='_blank'>详情</a></button>";
			var selectHtml = "<select class='select_type'><option value='0'>--选择操作--</option>";
			for (var k = 0; k < result.resultlist[i].canActionTypes.length; k++) {
				selectHtml += "<option value='"+result.resultlist[i].canActionTypes[k].typeValue+"'>"+result.resultlist[i].canActionTypes[k].typeName+"</option>";
			}
			selectHtml += "</select>";
			trHtml += selectHtml;
			trHtml += "</td></tr>";
			tableHtml += trHtml;
		}
		tableHtml += "</tbody></table>";
		$("#icheck_div").append(tableHtml);
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
		$("#icheck_paging").append(ulHtml);
		maxPage = result.maxPage;
	}

	function sendAjax(id,title,page,limit){
		$.ajax({
			url : "${ctx}/icheckList.do",
			type : "get",
			dataType: "json",
			data : {"page":page, "limit":limit,"id":id,"title":title},
			success : function(result) {
				console.log(result);
				
				//删除dom。新建dom
				loadSearch(result);
				
				$("#icheck_div").html("");
				loadTable(result);
				
				$("#icheck_paging").html("");
				loadPage(result);
			},
			error : function(e) {
				alert("icheckList.do 失败！");
			}
		});
	}
	
	// 页面加载完时，加载内容
	$(function(){
		sendAjax(null,null,currPage,5);
	});
	
	// 搜索
	$("#icheck_select").on("click","#icheck_search_btn",function(){
		var id = $("#icheck_select").find("#icheck_id").val();
		var title = $("#icheck_select").find("#icheck_title").val();
		sendAjax(id,title,1,5);
	});
	
	// 点击分页页码
	$("#icheck_paging").on("click","li:not(.msg)",function(){
		var id = $("#icheck_select").find("#icheck_id").val();
		var title = $("#icheck_select").find("#icheck_title").val();
		
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
	
	// 操作(通过，打回，等)
	$("#icheck_div").on("change",".select_type",function(){
		var typeValue = $(this).val();
		var jobId = $(this).parent().parent().find("td:eq(0)").text();
		if(isNaN(jobId)){
			return;
		}
		if(typeValue == 0){
			return;
		}
		$.ajax({
			url : "${ctx}/handleJob.do",
			type : "get",
			//dataType: "json",
			data : {"typeValue":typeValue,"jobId":jobId},
			success : function(result) {
				alert(result);
				$(".three").find("div:eq(0)").trigger("click");
			},
			error : function(e) {
				alert("handleJob.do 失败！");
			}
		});
	});
</script>