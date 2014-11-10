<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="debugFile.inc" %>
<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">权限系统</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">模块管理</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">角色管理</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">用户管理</a></li>
	</ul>
</div>

<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
				<a class="btn ajaxify" href="JSP/addRole.jsp" class="btn ajaxify">添加角色</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
					角色详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
						<th width="20%">序列号</th>
						<th width="40%">角色名称</th>
						<th width="40%">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listRole" var="role">
							<tr >
								<td>${role.id}</td>
								<td>${role.name}</td>
								<td><a class="ajaxify" href="RoleAction!updateShowRole?role.id=${role.id}&method=4">修改</a>&nbsp; 
								<a class="deleteOne" href="javascript:void(0)" data-action="RoleAction!deleteRole?role.id=${role.id}&method=8|RoleAction!listRole?index=${requestScope.currentIndex}">删除</a>&nbsp; 
								<a class="ajaxify"	href="RoleAction!privilegeRole?role.id=${role.id}">角色授权</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共
					<span >${requestScope.pageCount}</span>
					页 | 第
					<span >${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					  <ul>
						<li class="active"><a class="ajaxify" href="RoleAction!listRole?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="RoleAction!listRole?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript::">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="RoleAction!listRole?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript::">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="RoleAction!listRole?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>