<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>付款计划基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form class="form-horizontal" method="post">
			<h3 class="form-section">付款计划信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">客户名称:</label>
							<div class="controls">
								<input type="hidden" name="payPlan.id" value="${payPlan.id}">
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.clientName" value="${payPlan.clientName}" check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单名称:</label>
							<div class="controls">
								<select class="span12" name="payPlan.order.id" check-type="required" readOnly>
									<s:iterator var="order" value="#session.purchaseOrderRegisiter">
										<option value=${order.id } ${order.id == payPlan.order.id ? 'selected':''}>${order.orderName }</option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">金额:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.price" value="${payPlan.price}" check-type="float" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">付款提醒:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="payPlan.payRemind" value="${payPlan.payRemind}" readOnly check-type="date"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">付款日期:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="payPlan.payDate" value="${payPlan.payDate}" readOnly check-type="date"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">是否到账:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.isPaid" value="${payPlan.isPaid}" check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>

<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/myAjaxify.js" type="text/javascript"/>
