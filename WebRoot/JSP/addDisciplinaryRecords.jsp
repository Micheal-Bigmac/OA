<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc" %>
<% String path = request.getContextPath(); 
String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />

<div class="row-fluid">
	<h3 class="page-title">奖罚记录</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">人力资源</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">奖罚记录</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效参数</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效考核</a><i class="icon-angle-right"></i></li>
	</ul>
</div>

<div class="tab-pane  active" id="tab_2">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>奖惩信息添加
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a> <a href="#portlet-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a>
			</div>
		</div>
		<div class="portlet-body form">
		<!-- BEGIN FORM-->
			<form id="form" action="DisciplinaryRecordsAction!addDis?method=1" class="form-horizontal" method="post">
			<h3 class="form-section">奖惩管理</h3>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">职员用户:</label>
							<div class="controls">
								<input type="hidden" name="disciplinaryRecords.id" value="${disciplinaryRecords.id}" />
								
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.personId.name" value="${disciplinaryRecords.personId.name}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">奖惩区分:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.disciplinaryDistringuish" value="${disciplinaryRecords.disciplinaryDistringuish}" check-type="required" > <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">奖惩结果:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.disciplinaryResult" value="${disciplinaryRecords.disciplinaryResult}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">授予单位:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.awardUnit" value="${disciplinaryRecords.awardUnit}" check-type="required"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">奖惩日期:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12 datapicker" placeholder="" name="disciplinaryRecords.disciplinaryDate" value="${disciplinaryRecords.disciplinaryDate}" readOnly> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">奖惩名目:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.disciplinaryItem" value="${disciplinaryRecords.disciplinaryItem}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">奖惩原因:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.disciplinaryReason" value="${disciplinaryRecords.disciplinaryReason}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="control-group">
							<label class="control-label">备注说明:</label>
							<div class="controls">
								<input type="text" class="m-wrap span12" placeholder="" name="disciplinaryRecords.remark" value="${disciplinaryRecords.remark}"> <span class="help-block"></span>
							</div>
						</div>
					</div>
					<!--/span-->
				</div>
				<%@ include file="workFlowSelect.jsp" %> 
				<div class="form-actions">
					<button id="submit" type="button" class="btn btn-primary" data-action="DisciplinaryRecordsAction!find">保存</button>
					<button type="button" class="btn">Cancel</button>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/myAjaxify.js" type="text/javascript"></script>