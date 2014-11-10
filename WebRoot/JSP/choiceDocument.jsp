<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<%@taglib uri="www.BigMac.com" prefix="oa"%>
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
				<i class="icon-reorder"></i>添加公文
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form id="form" action="DocumentAction!addDocument" class="form-horizontal" method="post" enctype="multipart/form-data" >
				<h3 class="form-section">公文信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">公文名称:</label>
							<div class="controls">
								<input type="hidden" name="document.id" value="${document.id }" /> 
								<input type="text" class="m-wrap span12" placeholder="" name="document.title" value="${document.title}" check-type="required" > <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">公文描述:</label>
							<div class="controls">
								<input type="hidden" name="workflowId" value="${param.workflowId }">
								<input type="text" class="m-wrap span12" placeholder="" name="document.description" value="${document.description}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">添加公文附件:</label>
							<div class="controls">
								<input type="file" id="uploadFiles" class="m-wrap span12" placeholder="" name="uploadFiles" check-type="doc"> <span class="help-block" check-type="required"></span>
							</div>
						</div>
					</div>
				</div>
					${oa:TemplateToString(param.workflowId)}
				<div class="form-actions">
					<button type="button" id="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script>
$(function() {
	$("#form").validation();
})
$("#submit").click(function(e) {
	console.log("asdfasd");
	console.log($('#form').serialize());
	e.preventDefault();
	if ($("#form").valid(this, '填写信息不完整。') == false) {
		return false;
	}
	$.ajaxFileUpload({
		url:$('#form').attr('action')+"?"+$('#form').serialize(),
		secureuri:false,
		fileElementId:'uploadFiles',
		success:function(data){
			console.log("sfasdfasdfasdf"+data);
			pageContent = $('.page-content .page-content-body');
			$.ajax({
				url: 'DocumentAction!listMyDocument', 
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
