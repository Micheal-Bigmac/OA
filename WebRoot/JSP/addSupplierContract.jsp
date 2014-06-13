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
				<i class="icon-reorder"></i>供应商联系人信息录入
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="SupplierContractAction!addSupplierContact?method=1" class="form-horizontal">
			<h3 class="form-section">供应商联系人信息</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">联系人名称:</label>
							<div class="controls">
								<input type="hidden" name="supplierContract.id" value="${supplierContract.id}"/>
								<input type="text" class="m-wrap span12" placeholder="" name='supplierContract.contactName' value="${supplierContract.contactName}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">供应商名称:</label>
							<div class="controls">
								<select name="supplierContract.supplier.id">
									<s:iterator var="provider" value="#session.supplierManagers">
											<option value="${provider.id }" <s:if test="#request.supplierContract.supplier.id==provider.id">selected="selected"</s:if>>${provider.supplierName }</option>
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
							<label class="control-label">职位:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierContract.position' value="${supplierContract.position}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">性别:</label>
							<div class="controls">
								<select name="supplierContract.gender">								
									<option ${supplierContract.gender == '女'? 'selected':''}>女</option>
									<option ${supplierContract.gender == '男'? 'selected':''}>男</option>
								</select>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">工作电话:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name='supplierContract.phone' value="${supplierContract.phone}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">手机号码:</label>
							<div class="controls">
									<input type="text" class="m-wrap span12" placeholder="" name='supplierContract.telephone' value="${supplierContract.telephone}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">QQ:</label>
							<div class="controls">
									<input type="text" class="m-wrap span12" placeholder="" name='supplierContract.QQ' value="${supplierContract.QQ}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="form-actions">
					<button type="button" id="submit" class="btn btn-primary" data-action="SupplierContractAction!SupplierContactList">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/myAjaxify.js" text="text/javascript"/>