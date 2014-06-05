<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<SCRIPT language=JavaScript>
	function link() {
		document.getElementById("fom").action = "JSP/addOrganization.jsp";
		document.getElementById("fom").submit();
	}
</SCRIPT>

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


<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
				<a class="btn ajaxify" href="UserAction!listRole?userid=${userId}">给用户分配角色</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						角色列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >序号</th>
							<th >角色名称</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.usersRoles" var="userRole">
							<tr >
								<td>${userRole.id }</td>
								<td>${userRole.roleId.name}</td>
								
								<td><a class="ajaxify" href="UserAction!updateDistributeRole?usersRoles.id=${userRole.id}">修改</a>/
								<a class="ajaxify" href="UserAction!deleteDistributeRole?usersRoles.id=${userRole.id}&method=8">删除</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>