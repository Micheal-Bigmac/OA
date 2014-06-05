<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<SCRIPT language=JavaScript>
	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}

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

<div class='row-fluid'> 
	<select name="select2">
		<option>
			按录入时间
		</option>
		<option>
			按注销时间
		</option>
	</select>

	<input name="textfield" type="text" readonly="readonly" />
	<span>至</span>
	<input name="textfield" type="text" readonly="readonly" />
	<input class="btn" name="Submit" type="button" value="查 询" style='margin-bottom: 10px;'/>
	<input name="Submit" class="btn" type="button" value="高级搜索" style='margin-bottom: 10px;'/>
</div>
<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
				<span >选择：<a href="#"  onclick="selectAll();">全选</a>-<a href="#"  onclick="unselectAll();">反选</a></span>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						用户详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
						<th>选择	</th>
						
							<th >序号</th>
							<th >姓名</th>
							<th>性别</th>
							<th >所属机构</th>
							<th >登陆账号</th>
							<th >失效时间</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listPersons" var="person">
							<tr >
								<td>
									<input type="checkbox" name="delid"
										value="${person.id }" />
								</td>
								<td>${person.id}</td>
								<td>${person.name}</td>
								<td>${person.sex}</td>
								<td>${person.organization.name}</td>
								<td><s:property value="#person.getFirstUser().account" /></td>
								<td><s:property value="#person.getFirstUser().expireTime" /></td>
								<td>
								<s:if test="#person.getFirstUser().account!=null">
									<a class="ajaxify" href="UserAction!distributeRole?user.personid.id=${person.id}">分配角色</a>
									<a class="ajaxify" href="UserAction!deleteAccount?user.personid.id=${person.id }">删除账号</a>&nbsp;
									<a class="ajaxify" href="UserAction!distributeUser?user.personid.id=${person.id}">用户授权</a>&nbsp;</td>
								</s:if>
								 <s:else>
										<a class="ajaxify" href="JSP/addAccount.jsp?personid=${person.id}">分配账号</a>&nbsp;
								</s:else>
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
						<li class="active"><a class="ajaxify" href="UserAction!userList?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="UserAction!userList?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript::">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="UserAction!userList?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript::">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="UserAction!userList?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>