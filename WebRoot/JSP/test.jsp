<%@page import="com.oa.model.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<base href="<%=basePath%>">
<div class="row-fluid">
	<h3 class="page-title">流程列表</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">归档管理</a></i></li>
	</ul>
</div>
<jsp:include page="${url }" />
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>流程提交
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form id="form" action="DocumentAction!submit" class="form-horizontal" method="post">
				<h3 class="form-section">选择流程提交下一个人</h3>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<s:iterator var="transition" value="#request.transitionList" status="status">
							<label class="radio"> <input type="radio" name="transition" value="${transition }" /> ${transition}
							</label>
						</s:iterator>
					</div>
					<input type="hidden" name="document.id" value="${id }" />
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
			url : $('#form').attr('action'),
			data : $('#form').serialize(),
			success : function() {
				e.preventDefault();
				pageContent = $('.page-content .page-content-body');
				$.ajax({
					url : 'DocumentAction!listMyDocument',
					success : function(res) {
						pageContent.html(res);
					}
				});
			}
		});
	});

	
</script>