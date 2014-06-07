<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<base href="<%=basePath%>">
<div class="row-fluid">
	<h3 class="page-title">流程列表</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">归档管理</a></i>
		</li>
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
	<form id="fom">
		<table>
			<div class="row-fluid">
					<span class="newfont07">选择：<a href="#" id="selectAll">全选</a>-<a href="#" id="unselect">反选</a>
					</span>
					<button class="btn" type="button" id="deleteChose" data-action="DocumentAction!deleteDocument|${url }">删除所选信息</button> 
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						审批历史详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >
								审批历史编号</th>
							<th>审批时间</th>
							<th>审批意见</th>
							<th >审批的公文</th>
							<th >操作</th>
						
						</tr>
					</thead>
					<tbody>
						<s:iterator var="approveHistory" value="#request.approveHistoryList">
							<tr>
								<td ><input type="checkbox" name="delid" value="${approveHistory.id }" /></td>
								<td >${approveHistory.id}</td>
								<td >
									${approveHistory.approveTime} 
								</td>
								<td >
									${approveHistory.comments }
								</td>
								<s:set var="num" value="#approveHistory.document.doc.lastIndexOf('\\\\')" />
								<s:set var="doc" value="#approveHistory.document.doc.substring((#num+1))" />
								<td >
									${doc }
								</td>
								<td >
									 <a	href="JSP/submitDocument.jsp?id=${approveHistory.id}"
									>编辑(修改)</a>
									<a href="DocumentAction!deleteWorkFlow?delid=${approveHistory.id }">删除</a>
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
						<li class="active"><a class="ajaxify" href="${url }?index=1&document.id=${documentId }">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="${url }?index=${requestScope.currentIndex-1}&document.id=${documentId }">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url }?index=${requestScope.currentIndex+1}&document.id=${documentId }">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url }?index=${pageCount }&document.id=${documentId }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>
