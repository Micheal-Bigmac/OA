<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html"%>
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

<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						请选择要添加的公文
				</div>
				<table class="table table-striped">
					<thead>
						<tr></tr>
					</thead>
					<tbody>
						<td >
							 <s:iterator var="workflow"	value="#request.workFlows">
									<a class="ajaxify" href="JSP/choiceDocument.jsp?workflowId=${workflow.id}">${workflow.name}</a> &nbsp;
							</s:iterator>
					</td>
					</tbody>
				</table>
			</div>
		</table>
	</form>
</div>
 <script src="js/myAjaxify.js" type="text/javascript"></script>