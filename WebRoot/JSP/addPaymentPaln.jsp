<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
<base href="<%=basePath%>">

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>收款计划基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="PaymentPlanAction!addPaymentPlan?method=1" class="form-horizontal">
			<h3 class="form-section">收款计划信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">客户名称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="paymentPlan.clientName" value="${paymentPlan.clientName}" check-type="required"> <span class="help-block"></span>
								<input type="hidden" name="paymentPlan.id" value="${paymentPlan.id}">
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同名称:</label>
							<div class="controls">
								<select name="paymentPlan.order.id" check-type="required">
									<s:iterator var="order" value="#session.salesAgreements">
											<option value=${order.id } ${order.id == paymentPlan.order.id ? 'selected':''}>${order.salesName }</option>
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
								<input type="text" class="m-wrap span12" placeholder="" name="paymentPlan.price" value="${paymentPlan.price}" check-type="float"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">付款提醒:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="paymentPlan.gatheringRemind" value="${paymentPlan.gatheringRemind}" readOnly check-type="date"> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="paymentPlan.toDate" value="${paymentPlan.toDate}" readOnly check-type="date"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">是否到账:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="paymentPlan.isPaid" value="${paymentPlan.isPaid}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button id="submit" type="button"  class="btn btn-primary" data-action="PaymentPlanAction!PaymentPlanList">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/myAjaxify.js" type="text/javascript"/>

