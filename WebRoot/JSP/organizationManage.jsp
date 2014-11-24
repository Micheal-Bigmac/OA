<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
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
	<h3 class="page-title">机构管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">组织管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">人员管理</a></li>
	</ul>
</div>
<div class='portlet-body form'>
	<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
		<div class="row-fluid">
			<div class="span6">
				<div class="control-group">
					<div class="controls">
						<select id="findCondition" name="findCondition">
							<option value="name">按机构名称</option>
						</select>
						<input id="className" name="className" type="hidden" value="Organization" /> 
						<input id="textfield" name="textfield" type="text" />
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<div class="controls">
						<button id="select" type="button" class="btn btn-primary">查 询</button>
						<button id="Submit" class="btn btn-primary" type="button">高级搜索</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div class="row-fluid">
	<form name="fom" id="fom" method="post" action="">
		<table>
			<div class="row-fluid">
				<span>选择：<a href="#" id="selectAll">全选</a>-<a href="#" id="unselect">反选</a>
				</span> <input name="Submit" class="btn" type="button" value="删除所选机构信息" id="deleteChose" data-action="OrganizationAction?method=8!delete|OrganizationAction!find" />
				<!-- 				 <a href="JSP/yuangong.jsp" class="btn" data-toggle="modal" data-target="#myModal">添加机构信息</a>  -->
				<a class="btn ajaxify" href="JSP/addOrganization.jsp?parentid=${requestScope.parentid }">添加机构信息</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center; font-size: 20px; background-color: #EEEEEE">机构详细列表</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>选择</th>
							<th>机构序列号</th>
							<th>机构名称</th>
							<th>机构编号</th>
							<th>机构描述</th>
							<th>父机构名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listObject" var="org">
							<tr>
								<td><input type="checkbox" name="delid" value="${org.id }" /></td>
								<td>${org.id}</td>
								<td><a class="ajaxify" href="OrganizationAction!find?parentid=${org.id}">${org.name}</a></td>
								<td>${org.sn}</td>
								<td>${org.description}</td>
								<td>${org.pid.name}</td>
								<td><a class="ajaxify" href="OrganizationAction!update?updateOrgId=${org.id}&method=4">修改</a>/ <a class="deleteOne" href="javascript:void(0)"
									data-action="OrganizationAction!delete?delid=${org.id}&method=8|${url}">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共 <span>${requestScope.pageCount}</span> 页 | 第 <span>${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					<ul>
						<li class="active"><a class="ajaxify" href="${url}&index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'>
							<li class="active"><a class="ajaxify" href="${url}&index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						<s:if test='(#request.currentIndex) < #pageCount'>
							<li class="active"><a class="ajaxify" href="${url}&index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
						<li class="active"><a class="ajaxify" href="${url}&index=${pageCount }">末页</a></li>
					</ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>
<SCRIPT>
	$("#select").click(function(e) {
		e.preventDefault();
		var pageContent = $('.page-content .page-content-body');

		$.ajax({
			url : $('#form_Select').attr('action'),
			data : $('#form_Select').serialize(),
			success : function(res) {
				pageContent.html(res);
			},
			error : function() {
				alert("你输入的有问题");
			}
		});
	});
</SCRIPT>