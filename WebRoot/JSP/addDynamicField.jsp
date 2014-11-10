<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="debugFile.inc" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">表单定义</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">归档管理</a></i></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>添加表单域信息
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="DynamicFieldAction!addField" class="form-horizontal">
			<h3 class="form-section">添加表单域 </h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">标签${param.id }:</label>
							<div class="controls">
								<input name="dynamicFormId" type="hidden" value="${param.id }">
								<input type="text" class="m-wrap span12" placeholder="" name="dynamicField.fieldLabel" value="${dynamicField.fieldLabel}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">名称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="dynamicField.fieldName" value="${dynamicField.fieldName}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">类型:</label>
							<div class="controls">
								<select name='fieldType' check-type="required"> 
									<s:iterator var="item" value="#session.fieldType" status="status">
										<s:property value="#status.index"></s:property>
										<option value="${status.index}">
										${item.name }
											</option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">输入形式:</label>
							<div class="controls">
								<select name='fieldInput' check-type="required">
									<s:iterator var="item" value="#session.fieldInput" status="status">
											<option value="${status.index}">
												${item.name }
											</option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button  id="submit" class="btn btn-primary">保存</button>
					<button  class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<s:debug></s:debug>
<script>
$(function() {
	$("#form").validation();
})

$("#submit").click(function(e) {
	e.preventDefault();
	if ($("#form").valid(this, '填写信息不完整。') == false) {
		return false;
	}
	console.log("asdfas");
	$.post($('#form').attr('action') , $('#form').serialize(), function(res) {
			console.log(res);
			$.post('DynamicFormAction!listFormField?workflowid='+res,null,function(res){
				pageContent = $('.page-content .page-content-body');
				pageContent.html(res);
			});
	});
});
</script>