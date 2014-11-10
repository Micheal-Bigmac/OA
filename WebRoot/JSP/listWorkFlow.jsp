<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc" %>
<%@taglib uri="/struts-tags" prefix="s"%>


<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">公文列表</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">归档管理</a></i></li>
	</ul>
</div>
<div class='row-fluid'> 
	<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
	<table>
		<tr>
			<td>
				<select id="findCondition" name="findCondition">
					<option value="name">
						按流程名称
					</option>
				</select>
			</td>
			<td>
				<input id="className" name="className" type="hidden" value="WorkFlow"/>
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
	<form id="fom">
		<table>
			<div class="row-fluid">
				<span class="newfont07">选择：<a href="#"  id="selectAll">全选</a>-<a href="#"  id="unselect">反选</a>
				</span> 
				<button class="btn"  id="deleteChose" data-action="WorkFlowAction!deleteWorkFlow|${url }&method=8">删除所选流程信息</button>
				<a class="ajaxify btn" href="JSP/submitDocument.jsp" class="btn ajaxify">添加流程信息</a> 
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						流程详细列表 
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >流程编号</th>
							<th >流程名称</th>
							<th >流程定义文件</th>
							<th >流程图片信息</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="workflow" value="#request.listObject">
							<tr>
								<td >
									<input type="checkbox"	name="delid" value="${workflow.id }" /></td>
								<td >
									<a class="ajaxify" href="WorkFlowAction!edit?workFlow.id=${workflow.id }">${workflow.id	}</a></td>
								<td ><a class="ajaxify" href="WorkFlowAction!edit?workFlow.id=${workflow.id}">${workflow.name} |<s:property
											value="#workflow.processDefinition.lastIndexOf('\\\\')" /></a>
								</td>
								<s:set var="num" value="#workflow.processDefinition.lastIndexOf('\\\\')" />
								<td >
								<s:set var="process"	value="#workflow.processDefinition.substring((#num+1))" />
								<a class="ajaxify" href="javascript:window.showModalDialog('upload/${process}','','dialogWidth=800px;dialogHeight=500px;location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0;');">
								${process }</a>
								</td>
								<s:set var="number" value="#workflow.processDefinition.lastIndexOf('\\\\')" />
								<td >
								<s:set var="image"	value="#workflow.processImage.substring((#num+1))" />
								<a class="ajaxify" href="javascript:window.showModalDialog('upload/${image}','','dialogWidth=800px;dialogHeight=500px;location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0;');">
									${image }
								</a>
								</td>
								<td >
									 <a class="ajaxify"	href="JSP/submitDocument.jsp?id=${workflow.id}&method=4"
									>编辑(修改)</a>
									<a class="deleteOne" href="javascript:void(0)" data-action="WorkFlowAction!deleteWorkFlow?delid=${workflow.id }&method=8|${url}index=${requestScope.currentIndex-1}">删除</a></td>
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
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url }&index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url }&index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>

 <script src="js/myAjaxify.js" type="text/javascript"></script>
 <script>
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
</script>