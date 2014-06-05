<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); 
String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">用户管理</h3>
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
				<i class="icon-reorder"></i>账号管理页面
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="UserAction!addAccount" class="form-horizontal">
			<h3 class="form-section">账号管理</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">用户账号:</label>
							<input type="hidden" name="method" value="1" />
								<input type="hidden" name="person.id" value='<s:property value="#parameters.personid" />' />
							<div class="controls">
								
								<input type="text" class="m-wrap span12" placeholder="xxx"  name="user.account" value="xx"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">用户密码:</label>
							<div class="controls">
								<input type="password" class="m-wrap span12" placeholder="***"  name="user.password"  value=""> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">失效时间:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="user.expireTime" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
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

<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script>
$("#submit").click(function(e) {
	$.ajax({
		url: $('#form').attr('action'),
		data: $('#form').serialize(),
		success: function(data) {
 			e.preventDefault();
			pageContent = $('.page-content .page-content-body');
			$.ajax({
			url: 'UserAction!userList', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});
$('.datapicker').datepicker({
	autoclose : true,
	format: "yyyy-mm-dd",
	todayHighlight: true,
	todayBtn: true,
	language: "zh-CN"
});
</script>