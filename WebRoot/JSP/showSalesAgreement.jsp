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
				<i class="icon-reorder"></i>销售合同信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form  class="form-horizontal" method="post">
			<h3 class="form-section">销售合同信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同编号:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.id" value="${salesAgreement.id}" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同名称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.salesName" value="${salesAgreement.salesName}"   check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同类别:</label>
							<div class="controls">
								<select class="span12" name="salesAgreement.contractTitle"  check-type="required" readOnly>
									<option <s:if test="#request.salesAgreement.contractTitle=='重要合同'">selected="selected"</s:if>>重要合同</option>
									<option <s:if test="#request.salesAgreement.contractTitle=='重要合同稍等'">selected="selected"</s:if>>	重要合同稍等</option>
									<option <s:if test="#request.salesAgreement.contractTitle=='一般'">selected="selected"</s:if>>一般</option>
								</select>						
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">签约客户:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.contractName" value="${salesAgreement.contractName}"  check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同编码:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.salesCode" value="${salesAgreement.salesCode}" check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">当前状态:</label>
							<div class="controls">
								<select class="span12" name="salesAgreement.currentStatus" check-type="required" readOnly>
									<option <s:if test="#request.salesAgreement.currentStatus=='完成采购'">selected="selected"</s:if>>	完成采购</option>
									<option <s:if test="#request.salesAgreement.currentStatus=='正在采购">selected="selected"</s:if>>	正在采购</option>
									<option <s:if test="#request.salesAgreement.currentStatus=='等待审核">selected="selected"</s:if>>	等待审核</option>
									<option <s:if test="#request.salesAgreement.currentStatus=='通过审核[等待采购]'">selected="selected"</s:if>>通过审核[等待采购]</option>
								</select>	
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">合同总价:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.tatalPrice" value="${salesAgreement.tatalPrice}" check-type="float" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">已付费用:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.paidPrice" value="${salesAgreement.paidPrice}" check-type="float" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">尚欠费用:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.loanPrice" value="${salesAgreement.loanPrice}" check-type="float" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">是否交付:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="salesAgreement.isPaid" value="${salesAgreement.isPaid}" check-type="required" readOnly> <span class="help-block"></span>
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