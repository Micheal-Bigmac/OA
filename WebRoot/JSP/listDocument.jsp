<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">流程列表</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">归档管理</a></i></li>
	</ul>
</div>
<div class='row-fluid'> 
</div>

<div class="row-fluid">
	<form id="fom">
		<table>
			<div class="row-fluid">
					<div class="span6">
					<span class="newfont07">
					选择：
					<a href="#"  id="selectAll">全选</a>-<a href="#" id="unselect">反选</a>
				 	<button class="btn" type="button" id="deleteChose" data-action="DocumentAction!deleteDocument|${url }&method=8">删除所选公文信息</button>
					<a class="ajaxify btn" href="DocumentAction!toAddDocumentView?method=1">添加公文信息</a> 
					</div>
					
					<div class="span4 offset2">
						<a class="btn ajaxify" href="DocumentAction!listMyDocument?method=2">我的公文</a>
						<a class="btn ajaxify" href="DocumentAction!ApprovingDocumentList?method=2">待审公文</a>
						<a class="btn ajaxify" href="DocumentAction!ApprovedDocumentList?method=2">已审核公文</a>
						</div>
			</div>
			
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						归档公文详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<td >选择</td>
							<td >公文编号</td>
							<td >公文标题</td>
							<td >创建时间</td>
							<td >公文状态</td>
							<td >流程名称</td>
							<td >附件</td>
							<td >审批历史</td>
							<td >操作</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="document" value="#request.myDocumentList">
							<tr>
								<td><input type="checkbox"
									name="delid" value="${document.id }" />
								</td>
								<td >${document.id} </td>
								<td >${document.title}	</td>
								<td >${document.createTime }</td>
								<td >${document.status }</td>
								<td >${document.workFlow.name}</td>
								<s:set var="num" value="#document.doc.lastIndexOf('\\\\')" />
								<s:set var="doc" value="#document.doc.substring((#num+1))" />
								<td >
								<a class="ajaxify" href="DocumentAction!download?document.doc=${document.doc }">
								${doc}
								</a>
								</td>
								<td ><a class="ajaxify"
									href="DocumentAction!approveHistoryList?document.id=${document.id }">查看</a>
								</td>
								<td > 
									<s:if test="#request.type=='document'">
											&nbsp;&nbsp;
											<s:if test="#document.status=='新建'">
											<a class="ajaxify" href="DocumentAction!submitView?document.id=${document.id }&method=1">提交</a>&nbsp;&nbsp;
											<a class="ajaxify" href="DocumentAction!deleteDocument?delid=${document.id }&method=8">删除</a>
											</s:if> 
									</s:if>
									<s:elseif test="#request.type=='approvingDocument'">
										<a class="ajaxify" href="JSP/shenpi.jsp?id=${document.id }">审批</a>&nbsp;&nbsp;
										<a class="ajaxify" href="DocumentAction!submitView?document.id=${document.id }&method=1">提交</a>
									</s:elseif>
									<s:elseif test="#request.type == 'finishDocument'">
										<a class="ajaxify" href="DocumentAction!deleteDocument?delid=${document.id }&method=8">删除</a>
									</s:elseif>
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
						<li class="active">
						<a class="ajaxify" href="${url }?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="${url }?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url }?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url }?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
 <script src="js/myAjaxify.js" type="text/javascript"></script>