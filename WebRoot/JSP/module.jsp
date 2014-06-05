<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">模块管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">模块管理</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">角色管理</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">用户管理</a></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>模块信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form id="form" action="ModuleAction!addModule" class="form-horizontal">
				<h3 class="form-section">模块信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">模块编号:</label>
							<div class="controls">
								<input type="text" name='module.id' class="m-wrap span12" placeholder="" readonly value="${module.id }" > <span class="help-block">This is inline help</span>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="span6 ">
						<div class="control-group error">
							<label class="control-label">模块名称:</label>
							<div class="controls">
								<input type="text" name='module.name' class="m-wrap span12" placeholder="" value="${module.name }"> <span class="help-block">This field has error.</span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">唯一标识:</label>
							<div class="controls">
								<input type="text" name='module.sn'  class="m-wrap span12" value="${module.sn }">
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">模块路径:</label>
							<div class="controls">
								<input type="text" name='module.url'  class="m-wrap span12" value="${module.url }">
							</div>
						</div>
					</div>

				</div>
				<!--/row-->
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">主模块id:</label>
							<div class="controls">
								<input type="text" name='pid'  class="m-wrap span12" value="${param.pid }" readOnly>
							</div>
						</div>
					</div>
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
			url: 'ModuleAction!moduleList?module.id=${param.pid }', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});
</script>