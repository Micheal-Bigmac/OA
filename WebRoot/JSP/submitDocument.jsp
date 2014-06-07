<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">流程列表</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">归档管理</a></i>
		</li>
	</ul>
</div>
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>添加流程
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form id="form" action="WorkFlowAction!addWorkFlow" class="form-horizontal" enctype="multipart/form-data" method="post">
				<h3 class="form-section">流程信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">添加流程定义:</label>
							<div class="controls">
								<input class="m-wrap span12" name="uploadFiles" id="uploadFile" type="file" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">添加流程图片${param.id }:</label>
							<div class="controls">
								<input class="m-wrap span12" name="uploadFiles"  id="uploadFile"  type="file" />
								 <input type="hidden"  id="param" name="workFlow.id" value='${param.id }'>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
		
					<input class="btn" type="button" id="submi" value="保存"></input>
					<button class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>

<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script>

$("#submi").click(function(e) {
	console.log($('#param').val());
	console.log($('#form').serialize());
	e.preventDefault();
	
	$.ajaxFileUpload({
		url:$('#form').attr('action')+"?workFlow.id="+$('#param').val(),
		secureuri:false,
		fileElementId:'uploadFiles',
		success:function(data){
			console.log("sfasdfasdfasdf"+data);
			pageContent = $('.page-content .page-content-body');
			$.ajax({
				url: 'WorkFlowAction!listWorkFlow', 
				success: function(res) {
		        	pageContent.html(res);
				}
			});
		},
		error:function(){
			alert("提交失败");
		}
	});
});
</script>