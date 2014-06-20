<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

	<div id="myModal" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3>对话框标题</h3>
		</div>
		<div class="modal-body">
			<p>
			</p>
		</div>
		<div class="modal-footer">
			<a href="javascript:;" class="btn" data-dismiss="modal">取消</a> 
			<a href="javascript:;" class="btn btn-primary" id="submit" data-dismiss="modal">确定</a>
		</div>
</div>

<div class="container-fluid">

	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">
				用户手册 <small>随手笔记</small>
			</h3>
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="index.html">主页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="javascript:;">系统帮助</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="javascript:;">用户手册</a></li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>

	
	<!-- END PAGE HEADER-->
	<!-- BEGIN PAGE CONTENT-->
	<a href="modules/addUserManual.jsp" class="btn yellow mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> 添加</a>
		
		<div class="row-fluid">
			<s:set var="num" value="3"/>
			<s:iterator var="daily" value="#request.personDaily" status="status">
			
				<s:if test="#status.index/#num==1">
					</div>
					<s:set var="num" value="#num+#num"/>
					<div class="row-fluid">
				</s:if>
				
					<div class="span4 ">
						<!-- BEGIN Portlet PORTLET-->
						<div class="portlet box grey">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>${daily.title}</div>
								<div class="actions">
									<a href="PersonDailyAction!addUserManualView?id=${daily.id }&method=4" class="btn yellow mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> Edit</a>
									<a href="javascript:;" class="btn green mini"><i class="icon-plus"></i> delete</a>
								</div>
							</div>
							<div class="portlet-body">
								<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;"><div class="scroller" data-height="200px" style="overflow: hidden; width: auto; height: 200px;">
									<strong></strong><br>
									${daily.body}
								</div><div class="slimScrollBar ui-draggable" style="background-color: rgb(161, 178, 189); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; z-index: 99; right: 1px; height: 100px; background-position: initial initial; background-repeat: initial initial;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; background-color: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px; background-position: initial initial; background-repeat: initial initial;"></div></div>
							</div>
						</div>
						<!-- END Portlet PORTLET-->
					</div>
			</s:iterator>
		</div>
	
</div>

<script src="js/portlet-draggable.js"></script> 
<script type="text/javascript" src="js/myAjaxify.js"></script>

<script>

$("#myModal").on("hidden", function() {
	$(this).removeData("modal");
});

$("#submit").click(function() {
	console.log('safasf');
	console.log($('#model-form').attr('action'));
	$.ajax({
		url: $('#model-form').attr('action'),
		data: $('#model-form').serialize(),
		success: function() {
			$('myModal').modal('hide');
// 			e.preventDefault();
			pageContent = $('.page-content .page-content-body');
			
			$.ajax({
			url: 'PersonDailyAction!personDailyList', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});

$("#myModal").on("hidden", function() {
	$(this).removeData("modal");
});
</script>