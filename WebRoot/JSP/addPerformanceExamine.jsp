<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<div class="row-fluid">
	<h3 class="page-title">奖罚记录</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">人力资源</a> <i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">奖罚记录</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">绩效参数</a><i class="icon-angle-right"></i>
		</li>
		<li><a class="ajaxify" href="#">绩效考核</a><i class="icon-angle-right"></i>
		</li>
	</ul>
</div>
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>绩效管理页面
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form id="form" action="PerformanceExamineAction!addPerformanceExamine?method=1" class="form-horizontal" method="post">
				<h3 class="form-section">绩效考核</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">绩效考核名称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="listPerformanceExamine.name" value="${listPerformanceExamine.name}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label">简要说明:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="listPerformanceExamine.instruction" value="${listPerformanceExamine.instruction}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				
				<h3 class="form-section">绩效管理</h3>
				<table>
					<div class="row-fluid">
						<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
									请打分
						</div>
						<table class="table table-striped">
							<thead>
								<tr>
								<th>序号</th>
								<th>用户名</th>
								<s:iterator value="#request.listParameters" var="param">
									<th><s:property value="#param.name" /></th>
								</s:iterator>
								</tr>
							</thead>
							<tbody>
								<s:set var="i" value="0" />
								<s:set var="item" value="12/(#request.listParameters.size()+2)"/>
								<s:iterator value="#request.listPersons" var="per" status="status">
									<tr>
										<td><s:property value="#per.id" />${item }</td>
										<td><input type="hidden" name="pername" value="<s:property value="#per.name" />"> <s:property value="#per.name" /></td>
										<s:iterator value="#request.listParameters" var="pcn" status="sta">
											<td>
												<input type="hidden" name="props[${i}].personId.id" value="${per.id}" />
												<input type="hidden" name="props[${i}].paramId.id" value="${pcn.id}" />
												<input type="text" class="span${item }" placeholder="" name="props[${i}].score" /> <span class="help-block"></span>
												<s:set var="i" value="#i+1" />
											</td>
										</s:iterator>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
			</table>
			<div class="form-actions">
				<button type="button" id="submit"  class="btn btn-primary" data-action="PerformanceExamineAction!find">保存</button>
				<button type="button" class="btn">Cancel</button>
			</div>
		</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript"/>