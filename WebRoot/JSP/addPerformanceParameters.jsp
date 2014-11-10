<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<% String path = request.getContextPath(); 
String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<div class="row-fluid">
	<h3 class="page-title">奖罚记录</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">人力资源</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">奖罚记录</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效参数</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效考核</a><i class="icon-angle-right"></i></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>绩效参数
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="PerformanceParametersAction!addPerformanceParameters?method=1" class="form-horizontal" method="post">
			<h3 class="form-section">参数管理</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">参数名称:</label>
							<div class="controls">
								<input type="hidden" name="performanceParameters.id" value="${performanceParameters.id}" />								
								<input type="text" class="m-wrap span12" placeholder="" name="performanceParameters.name" value="${performanceParameters.name}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button id="submit" type="button" class="btn btn-primary" data-action="PerformanceParametersAction!find">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript"/>
