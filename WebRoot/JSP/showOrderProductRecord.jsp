<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>订单产品基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form  class="form-horizontal" method="post">
				<h3 class="form-section">订单产品信息</h3>
				<input type="hidden" name="orderProductRecord.id" value="${orderProductRecord.id}">
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品名称:</label>
							<div class="controls">
								<select class="span12" name="orderProductRecord.product.id" check-type="required" readOnly>
									<s:iterator var="product" value="#session.products">
										<option value=${product.id } ${product.id == orderProductRecord.product.id ? 'selected':''}>${product.productName }</option>
									</s:iterator>
								</select>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单名称:</label>
							<div class="controls">
								<select class="span12" name="orderProductRecord.order.id" check-type="required" readOnly>
									<s:iterator var="order" value="#session.purchaseOrderRegisiter">
										<option value=${order.id } ${order.id == orderProductRecord.order.id ? 'selected':''}>${order.orderName }</option>
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
							<label class="control-label">是否交付:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="orderProductRecord.order.isPaid" value="${orderProductRecord.order.isPaid}" check-type="required" readOnly> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">数量:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="orderProductRecord.number" value="${orderProductRecord.number}" check-type="number" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">已付款:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="orderProductRecord.order.paidPrice" value="${orderProductRecord.order.paidPrice}" check-type="float" readOnly> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">欠款数:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="orderProductRecord.order.loanPrice" value="${orderProductRecord.order.loanPrice}" check-type="float" readOnly> <span
									class="help-block"></span>
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