<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">

<div class="row-fluid">
	<h3 class="page-title">绩效参数</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">人力资源</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">奖罚记录</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效参数</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效考核</a><i class="icon-angle-right"></i></li>
	</ul>
</div>
<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
				<span class="newfont07">选择：</span> 
				<a href="JSP/addPerformanceParameters.jsp" class="btn ajaxify">添加参数</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						参数列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >序号</th>
							<th >参数名称</th>
							<th >录入人</th>
							<th >录入时间</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
							<s:iterator value="#request.listPerformanceParameters" var="pp">
								<tr bgcolor="#FFFFFF">
									<td>${pp.id}</td>
									<td>${pp.name }</td>
									<td>${pp.personId.name}</td>
									<td>${pp.date}</td>
									<td><a class="ajaxify"
										href="PerformanceParametersAction!updatePerformanceParameters?performanceParametersId=${pp.id}">修改</a>/<a class="ajaxify"
										href="PerformanceParametersAction!deletePerformanceParameters?performanceParametersId=${pp.id}&method=8">删除</a>
									</td>
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
						<li class="active"><a class="ajaxify" href="PerformanceParametersAction!find?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="PerformanceParametersAction!find?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="PerformanceParametersAction!find?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="PerformanceParametersAction!find?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>