<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">权限系统</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">模块管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">角色管理</a><i class="icon-angle-right"></i></li>
		<li><a href="#">用户管理</a></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>角色信息管理
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="RoleAction!addRole" class="form-horizontal">
			<h3 class="form-section">角色信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">角色名称:</label>
							<div class="controls">
								<s:if test="#request.role == null">
									<input type="hidden" name="method" value="1" />
								</s:if>
								<s:else>
									<input type="hidden" name="method" value="4" />
								</s:else>
								<input type="hidden" name="role.id" value="${role.id}" />
								
								<input type="text" class="m-wrap span12" placeholder="" name="role.name" value="${role.name}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button type="button" id="submit" class="btn btn-primary" data-action="RoleAction!listRole">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript"/>
