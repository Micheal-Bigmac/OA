<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); 
String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">机构管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">组织管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">人员管理</a></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>机构管理页面
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="OrganizationAction!add?method=1" class="form-horizontal">
			<h3 class="form-section">机构管理</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">机构名称:</label>
							<div class="controls">
									<input type="hidden" name="parentid" value="${param.parentid}"/>
									
									<input type="hidden" name="organization.id" value="${organization.id}" />
									<input type="text" class="m-wrap span12" placeholder="" name="organization.name" value="${organization.name}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">机构名机构描述:</label>
							<div class="controls">
								
									<input type="text" class="m-wrap span12" placeholder="" name="organization.description" value="${organization.description}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button id="submit" type="button" class="btn btn-primary" data-action="OrganizationAction!find">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript">