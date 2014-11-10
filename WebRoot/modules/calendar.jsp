<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../JSP/debugFile.inc" %>
<!-- PAGE LEVEL STYLES -->
<link href="css/fullcalendar.css" rel="stylesheet" />
<link href="css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<div class="span12">
		
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			日历 <small>个人</small>
		</h3>
		<ul class="breadcrumb">
			<li><i class="icon-home"></i> <a href="index.jsp">仪表盘</a> <i class="icon-angle-right"></i>
			</li>
			<li><a href="#">其他</a> <i class="icon-angle-right"></i></li>
			<li><a href="#">日历</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid">
	<div class="portlet box blue calendar">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>日历
			</div>
		</div>
		<div class="portlet-body light-grey">
			<div class="row-fluid">
				<div class="span3 responsive" data-tablet="span12 fix-margin" data-desktop="span8">
					<!-- BEGIN DRAGGABLE EVENTS PORTLET-->
					<h3 class="event-form-title">编辑日历</h3>
					<div id="external-events">
						<form class="inline-form">
							<a href="javascript:;" id="event_add" class="btn green">添加事件</a>
						</form>
						<hr />
					</div>
					<!-- END DRAGGABLE EVENTS PORTLET-->
				</div>
				<div class="span9">
					<div id="calendar" class="has-toolbar"></div>
				</div>
			</div>
			<!-- END CALENDAR PORTLET-->
		</div>
	</div>
</div>

<div class="modal hide fade" id="dialog_eventadd"  tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<h3>添加新事件</h3>
	</div>
	
	<div class="modal-body">
		<form class="form-horizontal" id="f_addevent">
			<div class="control-group">
				<label class="control-label" for="title">标题</label>
				<div class="controls">
					<input type="text" name="title" required/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="location">地点</label>
				<div class="controls">
					<input type="text" name="location"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="allDay">全天</label>
				<div class="controls">
					<input type="checkbox" name="allDay"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="start">开始时间</label>
				<div class="controls">
					<div class="input-append">
						<input type="text" class="datetimebox" id="datetimebox_start" name="start" size="16" readonly required/>
						<span class="add-on"><i class="icon-remove"></i></span>
					</div>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="end">结束时间</label>
				<div class="controls">
					<div class="input-append">
						<input type="text" class="datetimebox" id="datetimebox_end" name="end" readonly size="16"/>
						<span class="add-on"><i class="icon-remove"></i></span>
					</div>
					<span class="help-inline"></span>
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
   		<button class="btn btn-primary" id="event_submit">添加</button>
	</div>
</div>
	
<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="js/fullcalendar.min.js" type="text/javascript"></script>
	<script src="js/bootstrap-datetimepicker.js" type="text/javascript"></script>
	<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="js/portlet-calendar.js"></script>
	<script>
		Calendar.init();
	</script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- END JAVASCRIPTS -->

