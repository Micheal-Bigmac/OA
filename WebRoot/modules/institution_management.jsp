<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../JSP/debugFile.inc" %>
<!-- PAGE LEVEL STYLES -->
<link href="css/select2_metro.css" rel="stylesheet" type="text/css" />
<link href="css/DT_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/portlet-table.css" rel="stylesheet" type="text/css" />

    
<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">机构管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.jsp">仪表盘</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">组织管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">机构管理</a></li>
	</ul>
</div>

<!-- TABLE PORTLET-->
<div class="row-fluid">
	<div class="portlet box blue">
		<!-- PORTLET TITLE -->
		<div class="portlet-title" id="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i>机构管理表
			</div>
			<div class="tools" id="tool_group">
				<span class="hide">
					<i class="icon-plus icon-white" id="tool_add"></i>
					<i class="icon-minus icon-white disable" id="tool_remove"></i>
					<i class="icon-ok icon-white disable" id="tool_ok"></i>
					<i class="icon-remove icon-white" id="tool_cancel"></i>
				</span>
				<i class="icon-pencil icon-white" id="tool_edit"></i>
				<i class="icon-refresh icon-white" id="tool_refresh"></i>
				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#" style="padding: 0px; background-color: inherit;">
						<i class="icon-briefcase icon-white" id="tool_tools"></i>
					</a>
					<ul class="dropdown-menu pull-right">
						<li><a href="#">Print</a></li>
						<li><a href="#">Save as PDF</a></li>
						<li><a href="#">Export to Excel</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- PORTLET BODY -->
		<form id="portlet-form">
			<div class="portlet-body">
				<!-- OUR TABLE -->
				<div class="dataTables_wrapper form-inline" id="sample_1_wrapper">
					<table class="table table-striped table-bordered table-hover dataTable" id="portlet_table">
					</table>
				</div>
			</div>
		</form>
	</div>
</div>


<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="js/select2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="js/portlet-table.js"></script>
<script>

	PortletTable.init();
	
</script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- END JAVASCRIPTS -->
</body>
</html>