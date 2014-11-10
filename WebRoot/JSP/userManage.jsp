<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="debugFile.inc" %>

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
		<li><i class="icon-home"></i> <a href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">模块管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">角色管理</a><i class="icon-angle-right"></i></li>
		<li><a href="#">用户管理</a></li>
	</ul>
</div>

<div class='row-fluid'> 
<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
<table>
	<tr>
		<td>
			<select id="findCondition" name="findCondition">
				<option value="accountid">
					按登录账号
				</option>
				<option value="personName">
					按姓名
				</option>
			</select>
		</td>
		<td>
			<input id="className" name="className" type="hidden" value="Person"/>
			<input id="textfield" name="textfield" type="text"  />
		</td>
		<td>
			<input id="select" class="btn" name="Submit" type="submit" value="查 询"  style='margin-bottom: 10px;'/>
		</td>
		<td>
			<input name="Submit" class="btn" type="button" value="高级搜索" style='margin-bottom: 10px;'/>
		</td>
	</tr>
</table>
</form>
</div>
<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						用户详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >序号</th>
							<th >姓名</th>
							<th >性别</th>
							<th >所属机构</th>
							<th >登陆账号</th>
							<th >失效时间</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listObject" var="person">
							<tr bgcolor="#FFFFFF">
								<td>${person.id}</td>
								<td>${person.name}</td>
								<td>${person.sex}</td>
								<td>${person.organization.name}</td>
								<td><s:property value="#person.getFirstUser().account" /></td>
								<td><s:property value="#person.getFirstUser().expireTime" /></td>
								<td>
								<s:if test="#person.getFirstUser().account != ''">
									<a class="deleteOne" href="javascript:void(0)" data-action="UserAction!deleteAccount?user.personid.id=${person.id }&method=8|${url}?${requestScope.currentIndex}">删除账号</a>&nbsp;
									<a class="ajaxify" href="UserAction!distributeRole?user.personid.id=${person.id}">分配角色</a>&nbsp;
									<a class="ajaxify" href="UserAction!distributeUser?user.personid.id=${person.id}">用户授权</a>&nbsp;
								</s:if> 
								<s:else>
										<a class="ajaxify" href="JSP/addAccount.jsp?personid=${person.id}&method=1">分配账号</a>&nbsp;
								</s:else>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<s:set var="url" value="#request.url" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共
					<span >${requestScope.pageCount}</span>
					页 | 第
					<span >${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					  <ul>
						<li class="active"><a class="ajaxify" href="${url }&index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="${url }&index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript::">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url }&index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript::">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url }&index=${pageCount }">末页</a></li>
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
		url: $('#form_Select').attr('action'),
		data: $('#form_Select').serialize(),
		success: function(res) {
			pageContent.html(res);
		},
		error: function(){
			alert("你输入的有问题");
		}
	});
});
</SCRIPT>