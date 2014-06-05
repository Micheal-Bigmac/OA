<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">供应商管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">供应商管理</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">供应商联系人</a></li>
	</ul>
</div>


<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>供应商基本信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form action="SupplierManagerAction!addSupplierManager" class="form-horizontal">
			<h3 class="form-section">供应商信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">供应商名称:</label>
							<div class="controls">
								<input type="hidden" name="supplierManager.id" value="${supplierManager.id}"/>
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.supplierName' value="${supplierManager.supplierName }"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">供应商编码:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.supplierCode' value="${supplierManager.supplierCode}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">简称:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.shortName' value="${supplierManager.shortName}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">电话:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.telephone'	value="${supplierManager.telephone }" > <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">手机号码:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.phone' value="${supplierManager.phone}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">地区:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierManager.address'	value="${supplierManager.address }" > <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>