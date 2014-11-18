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
				<i class="icon-reorder"></i>采购订单基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form  class="form-horizontal" method="post">
			<h3 class="form-section">采购订单信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单名称:</label>
							<div class="controls">
								<s:if test="#request.purchaseOrderRegisiter">
									<input type="hidden" name='purchaseOrderRegisiter.id' style="width: 154px" value="${purchaseOrderRegisiter.id }" readonly />
								</s:if>
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.orderName" value="${purchaseOrderRegisiter.orderName}" check-type="required" readonly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">订单类别:</label>
							<div class="controls">
								<select class="span12" name="purchaseOrderRegisiter.orderType" check-type="required" readonly>
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
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.orderCode" value="${purchaseOrderRegisiter.orderCode}" check-type="required" readonly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">供应商:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.provider" value="${purchaseOrderRegisiter.provider}" check-type="required" readonly> <span class="help-block"></span>
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
								<select class="span12" name="purchaseOrderRegisiter.currentState" check-type="required" readonly>
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
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.tatalPrice" value="${purchaseOrderRegisiter.tatalPrice}" check-type="float" readonly> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.paidPrice" value="${purchaseOrderRegisiter.paidPrice}" check-type="float" readonly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">尚付费用:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.loanPrice" value="${purchaseOrderRegisiter.loanPrice}" check-type="float" readonly> <span class="help-block"></span>
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
								<input type="text" class="m-wrap span12" placeholder="" name="purchaseOrderRegisiter.isPaid" value="${purchaseOrderRegisiter.isPaid}" check-type="required" readonly> <span class="help-block"></span>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>