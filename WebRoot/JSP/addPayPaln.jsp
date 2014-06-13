<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">产品信息查看</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">产品信息查看</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">销售合同登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">合同产品登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">收款计划</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">采购订单登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">订单产品登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">付款计划</a></li>
	</ul>
</div>

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
			<form id="form" action="PayPlanAction!addPayPlan?method=1" class="form-horizontal" method="post">
			<h3 class="form-section">付款计划信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">客户名称:</label>
							<div class="controls">
								<input type="hidden" name="payPlan.id" value="${payPlan.id}">
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.clientName" value="${payPlan.clientName}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单名称:</label>
							<div class="controls">
								<select class="span12" name="payPlan.order.id">
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
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.price" value="${payPlan.price}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">付款提醒:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="payPlan.payRemind" value="${payPlan.payRemind}" readOnly> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12 datapicker" placeholder="yyyy-MM-dd" name="payPlan.payDate" value="${payPlan.payDate}" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">是否到账:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="payPlan.isPaid" value="${payPlan.isPaid}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button type="button" id="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>

<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script>
$("#submit").click(function(e) {
	$.ajax({
		url: $('#form').attr('action'),
		data: $('#form').serialize(),
		success: function() {
			pageContent = $('.page-content .page-content-body');
			
			$.ajax({
			url: 'PayPlanAction!PayPlanList', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});
$('.datapicker').datepicker({
	autoclose : true,
	format: "yyyy-mm-dd",
	todayHighlight: true,
	todayBtn: true,
	language: "zh-CN"
});
</script>