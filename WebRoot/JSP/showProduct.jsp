<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
<base href="<%=basePath%>">
<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>产品基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form   class="form-horizontal" method="post">
				<h3 class="form-section">产品信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品编号:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.id" value="${product.id}" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品名称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 form-control" placeholder="" name="product.productName" value="${product.productName}" check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品类别:</label>
							<div class="controls">
								<select name="product.type" check-type="required" readOnly>
									<option <s:if test="#request.product.type=='硬件产品'">selected="selected"</s:if>>硬件产品</option>
									<option <s:if test="#request.product.type=='自主产品'">selected="selected"</s:if>>自主产品</option>
								</select>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">产品编码:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.productCode" value="${product.productCode}" check-type="required" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">成本价:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.inputPrise" value="${product.inputPrise}" check-type="float" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">出售价:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.outputPrise" value="${product.outputPrise }" check-type="float" readOnly>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">出库总量:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.totalOutbound" value="${product.totalOutbound}" check-type="number" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">入库总量:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.totalInventory" value="${product.totalInventory}" check-type="number" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">当前总量:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="product.currentInventory" value="${product.currentInventory}" check-type="number" readOnly> <span class="help-block"></span>
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