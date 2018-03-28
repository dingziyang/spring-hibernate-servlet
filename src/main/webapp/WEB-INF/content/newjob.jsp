<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common.jsp"%>


<div class="container">
	<div class="row">
		<div id="newjob_div" class="content_div col-md-8 col-md-offset-2">
			<form role="form" action="${ctx}/commitjob.do" method="post" class="form-horizontal">
				<div class="form-body">
					 <div class="form-group">
					   <label for="job_title" class="control-label">标题</label>
					   <input name="job_title" id="job_title" type="text" class="form-control">
					 </div>
					 <div class="form-group">
					   <label for="job_content" class="control-label">内容</label>
					   <textarea name="job_content" id="job_content" class="form-control" rows="3"></textarea>
					 </div>
					 <div class="form-group">
					   <label for="money" class="control-label">金额(￥)</label>
					   <input name="money" type="number" id="money" class="form-control">
					 </div>
					 <div class="form-group">
					   <label for="job_type" class="control-label">类型</label>
					   <select name="job_type" id="job_type">
					  		<c:forEach items="${jobTypeVoList}" var="jobTypeVo">
				    			<option value="${jobTypeVo.typeNo}">${jobTypeVo.typeName }</option>
				    		</c:forEach>
					   </select>
					 </div>
					 
					 <div class="form-group">
					   <label for="job_next" class="control-label">提交给</label>
					   <input name="job_next" id="job_next" type="text" class="form-control" data-huid="${ userSession.highUserId}" value="${ userSession.highUserNickName}" disabled="disabled"/>
					 </div>
				  </div>
			 </form>
			 
			<form class="form-horizontal" method="post" action="${ctx}/upload.do" enctype="multipart/form-data">
				<div class="form-group">
			   <label for="job_annex_url" class="control-label">附件</label>
			   <br/>
			   <input type="file" name="uploadFile" id="uploadFile"/>
			   <input type="button" value="上传" id="smt" class="btn btn-primary btn-sm"/>
			   <input type="text" id="job_annex_url" disabled="disabled" class="form-control">
			   </div>
			</form>
			 
		    <button name="cmt" id="cmt" class="btn btn-primary pull-right">提交工作流</button>
			<br>
		</div>
	</div>
</div>


<script type="text/javascript" src="${ctx}/static/js/ajaxfileupload.js"></script>

<script type="text/javascript">

	// “提交工作流”按钮
	$("#newjob_div").on("click","#cmt",function(){
		var job_title = $("#newjob_div").find("#job_title").val();
		if (job_title === null || job_title === undefined || job_title === '') {
			alert("标题不能为空");
			return;
		}
		if(job_title.trim().length == 0){
			alert("标题不能为空");
			return;
		}
		
		var job_content = $("#newjob_div").find("#job_content").val();
		if (job_content === null || job_content === undefined || job_content === '') {
			alert("内容不能为空");
			return;
		}
		if(job_content.trim().length == 0){
			alert("内容不能为空");
			return;
		}
		
		var money = $("#newjob_div").find("#money").val();
		if (money === null || money === undefined || money === '') {
			alert("金额不能为空");
			return;
		}
		if (money <= 0 || money > 10000) {
			alert("金额超出范围：(0,10000)");
			return;
		}
		
		var job_type = $("#newjob_div").find("#job_type").val();
		var job_annex = $("#newjob_div").find("#job_annex_url").val();
		job_annex = job_annex.replace(/\\/g,"/");
		console.log("转hou：",job_annex);
		var job_next = $("#newjob_div").find("#job_next").data("huid");
		$.ajax({
			url : "${ctx}/commitjob.do",
			type : "POST",
			data : {"job_title":job_title, "job_content":job_content, "money":money,
				    "job_type":job_type,   "job_annex_url":job_annex,     "job_next":job_next},
			success : function(result) {
				alert(result);
				$("body").find(".three").find("div:eq(0)").trigger("click");
			},
			error : function(e) {
				alert("commitjob.do 失败！");
			}
		});
	});
	
	// 文件上传
	$("#newjob_div").on("click","#smt" ,function(e) {
		var fileName = $("#uploadFile").val();
		if (fileName === null || fileName === undefined || fileName === '') {
			alert("请选择文件");
			return;
		}
		
		$.ajaxFileUpload({
			url:'${ctx}/upload.do',//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:'uploadFile',//文件上传空间input 的id属性
			dataType: 'text',//返回值类型 一般设置为json
			success: function (data, status){
			    alert(data,status);//后台out输出的值
			    var fileUrl = data.split("|")[0];
			    $("#newjob_div").find("#job_annex_url").val(fileUrl);
			},
			error: function (data, status, e){
			     alert(data,status,e);//后台out输出的值
			}
	    });
	});
</script>
