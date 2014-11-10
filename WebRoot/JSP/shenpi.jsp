<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<base href="<%=basePath%>">
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
				<i class="icon-reorder"></i>审批意见
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="DocumentAction!approve" class="form-horizontal">
			<h3 class="form-section"></h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">审批</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="idea"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<input type="hidden" name="document.id" value="${param.id }">
					<!-- <div class="span6 ">
						<div class="control-group">
							<label class="control-label">是否回退</label>
							<div class="controls">
								<label class="radio">
									<input type="radio" name="back" value="1" /> 是 
								</label>
								<label class="radio">
									<input type="radio" name="back" value="0" /> 否
								</label>
							</div>
						</div>
					</div> -->
					<!--/span-->
				</div>
				<div class="form-actions">
					<button type="button" id="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script>
$("#submit").click(function(e) {
	$.ajax({
		url: $('#form').attr('action'),
		data: $('#form').serialize(),
		success: function() {
 			e.preventDefault();
			pageContent = $('.page-content .page-content-body');
			$.ajax({
			url: 'DocumentAction!ApprovingDocumentList', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});
</script>