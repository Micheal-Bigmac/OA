<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<%@include file="debugFile.inc" %>

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
				<i class="icon-reorder"></i>合同产品基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="ContractProductAction!addContractProduct?method=1" class="form-horizontal" method="post">
			<h3 class="form-section">合同产品信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品名称:</label>
							<div class="controls">
								<select class="span12" name="contractProductRecord.product.id" check-type="required">
									<s:iterator var="product" value="#session.products"> 
											<option value=${product.id }  ${product.id == contractProductRecord.product.id ? 'selected':''}>${product.productName }</option>
									</s:iterator>
								</select>
								<input type="hidden" name="contractProductRecord.id" value="${contractProductRecord.id}">
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同名称:</label>
							<div class="controls">
								<select class="span12" name="contractProductRecord.agreement.id" check-type="required">
									<s:iterator var="salesAgreement" value="#session.salesAgreements">
										<option value=${salesAgreement.id } ${salesAgreement.id == contractProductRecord.agreement.id ? 'selected':''}>${salesAgreement.salesName }</option>
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
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.isPaid" value="${contractProductRecord.agreement.isPaid}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">数量:</label>
							<div class="controls">
									<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.number" value="${contractProductRecord.number}" check-type="number"> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.paidPrice" value="${contractProductRecord.agreement.paidPrice}" check-type="float"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">欠款数:</label>
							<div class="controls">
									<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.loanPrice" value="${contractProductRecord.agreement.loanPrice}" check-type="float"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				
				<div class="form-actions">
					<button id="submit" type="button" class="btn btn-primary" data-action="ContractProductAction!ContractProductRecordList">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" type="text/javascript"/>