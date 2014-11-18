<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
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
								<select class="span12" name="contractProductRecord.product.id" check-type="required" readOnly>
									<s:iterator var="product" value="#session.products">
										<option value=${product.id } ${product.id == contractProductRecord.product.id ? 'selected':''}>${product.productName }</option>
									</s:iterator>
								</select> <input type="hidden" name="contractProductRecord.id" value="${contractProductRecord.id}">
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同名称:</label>
							<div class="controls">
								<select class="span12" name="contractProductRecord.agreement.id" check-type="required" readOnly>
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
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.isPaid" value="${contractProductRecord.agreement.isPaid}" check-type="required" readOnly> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">数量:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.number" value="${contractProductRecord.number}" check-type="number"> <span class="help-block" readOnly></span>
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
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.paidPrice" value="${contractProductRecord.agreement.paidPrice}" check-type="float" readOnly> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">欠款数:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="contractProductRecord.agreement.loanPrice" value="${contractProductRecord.agreement.loanPrice}" check-type="float" readOnly> <span
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