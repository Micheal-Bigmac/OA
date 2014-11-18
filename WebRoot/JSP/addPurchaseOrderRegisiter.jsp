<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">


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
				<i class="icon-reorder"></i>采购订单基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="PurchaseOrderRegisiterAction!addPurchaseOrderRegisiter?method=1" class="form-horizontal" method="post">
			<h3 class="form-section">采购订单信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单名称:</label>
							<div class="controls">
								<s:if test="#request.purchaseOrderRegisiter">
									<input type="hidden" name='purchaseOrderRegisiter.id' style="width: 154px" value="${purchaseOrderRegisiter.id }" readonly />	<span class="red">*</span> 
								</s:if>
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.orderName" value="${purchaseOrderRegisiter.orderName}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单类别:</label>
							<div class="controls">
								<select class="span12" name="purchaseOrderRegisiter.orderType" check-type="required">
									<option <s:if test="#request.purchaseOrderRegisiter.orderType=='重要订单'">selected="selected"</s:if>>	重要订单</option>
									<option <s:if test="#request.purchaseOrderRegisiter.orderType=='一般订单'">selected="selected"</s:if>>	一般订单	</option>
								</select>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单编码:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.orderCode" value="${purchaseOrderRegisiter.orderCode}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">供应商:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.provider" value="${purchaseOrderRegisiter.provider}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">当前状态:</label>
							<div class="controls">
								<select class="span12" name="purchaseOrderRegisiter.currentState" check-type="required">
									<option <s:if test="#request.purchaseOrderRegisiter.currentState=='通过审核'">selected="selected"</s:if>>通过审核</option>
									<option <s:if test="#request.purchaseOrderRegisiter.currentState=='审核中'">selected="selected"</s:if>>审核中</option>
									<option <s:if test="#request.purchaseOrderRegisiter.currentState=='等待审核'">selected="selected"</s:if>>	等待审核	</option>
								</select>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同总价:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.tatalPrice" value="${purchaseOrderRegisiter.tatalPrice}" check-type="float"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">已付费用:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.paidPrice" value="${purchaseOrderRegisiter.paidPrice}" check-type="float"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">尚付费用:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.loanPrice" value="${purchaseOrderRegisiter.loanPrice}" check-type="float"> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.isPaid" value="${purchaseOrderRegisiter.isPaid}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="workFlowSelect.jsp" %> 
				<div class="form-actions">
					<button id="submit" type="button" class="btn btn-primary" data-action="PurchaseOrderRegisiterAction!PurchaseOrderRegisiterList">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript"/>

 