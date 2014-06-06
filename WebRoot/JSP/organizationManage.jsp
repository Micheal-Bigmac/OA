<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println(basePath);
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
	<form name="fom" id="fom" method="post" action="">
		
		<table>
			<div class="row-fluid">
				<span >选择：<a href="#" id="selectAll">全选</a>-<a href="#" id="unselect">反选</a> </span>
				<input name="Submit" class="btn" type="button" value="删除所选机构信息" id="deleteChose" data-action="OrganizationAction!delete|OrganizationAction!find"/>
<!-- 				 <a href="JSP/yuangong.jsp" class="btn" data-toggle="modal" data-target="#myModal">添加机构信息</a>  -->
					<a class="btn ajaxify" href="JSP/addOrganization.jsp" >添加机构信息</a> 
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						机构详细列表 
				</div>
				<table class="table table-striped">
				<thead>
					<tr >
					<th>选择	</th>
						<th >机构序列号</th>
						<th >机构名称</th>
						<th >机构编号</th>
						<th >机构描述</th>
						<th >父机构名称</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.listOrg" var="org">
						<tr>
							<td>
									<input type="checkbox" name="delid"
										value="${org.id }" />
								</td>
							<td>${org.id}</td>
							<td><a class="ajaxify" href="OrganizationAction!listOrgChild?parentid=${org.id}">${org.name}</a></td>
							<td>${org.sn}</td>
							<td>${org.description}</td>
							<td>${org.pid.name}</td>
							<td><a class="ajaxify" href="OrganizationAction!update?updateOrgId=${org.id}">修改</a>/<a class="ajaxify"
								href="OrganizationAction!delete?delid=${org.id}">删除</a></td>
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
						<li class="active"><a class="ajaxify" href="OrganizationAction!find?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="OrganizationAction!find?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="OrganizationAction!find?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="OrganizationAction!find?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>

<script src="js/myAjaxify.js" type="text/javascript"></script>