<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!-- BEGIN PAGE TITLE & BREADCRUMB-->
<div>
	<h3 class="page-title">
		规章制度 <small>规章制度列表</small>
	</h3>
	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.html">Home</a> 
			<i class="icon-angle-right"></i>
		</li>
		<li>
			<a href="#">规章制度</a>
		</li>
	</ul>
	<!-- END PAGE TITLE & BREADCRUMB-->
</div>

<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<s:set var="row_num" value="#request.total/3" />
<s:set var="column_num" value="#request.total%3"/>
<!-- <s:property value="#request.ruleLists.size()"/><br> -->
${row_num }
${column_num }
<%-- 
	<s:iterator var="rule" value="#request.ruleLists" status="status">
		 caption :${rule.captition }<br/>
		count :${status.count }<br/>
		index :${status.index }<br/>
		<s:set var="status.index" value=""status.index+1/>
		${rule.body } 
		
	</s:iterator> 
	<s:property value="#request.ruleLists[1].body"/>
	${requestScope.ruleLists[1] } --%>
<s:set var="num" value="0"/>

<div class="row-fluid" id="sortable_portlets">
		<div class="span4 column sortable">
			<s:iterator var="i" begin="1" step="1" end="#row_num">
				<s:set var="rule" value="#request.ruleLists[#num]"/>
				<div class=" portlet box red">
					<div class="portlet-title">
						<input type="hidden" value="${rule.id }"/>
						<div class="caption"><i class="icon-reorder"></i>${rule.captition }</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
						<div class="actions">
							<a href="RuleAction!addRuleView?id=${rule.id }" class="btn blue mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> Edit</a>
						</div>
					</div>
					<div class="portlet-body">
						${rule.body }
					</div>
				</div>
				<s:set var="num" value="#num+1"/>
			</s:iterator>
		</div>
		
	 	
		<div class="span4 column sortable">
			<s:iterator var="i" begin="1" step="1" end="#row_num">
			<s:set var="rule" value="#request.ruleLists[#num]"/>
				<div class=" portlet box red">
					<div class="portlet-title">
						<input type="hidden" value="${rule.id }"/>
						<div class="caption"><i class="icon-reorder"></i>${rule.captition }</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
						<div class="actions">
							<a href="RuleAction!addRuleView?id=${rule.id }" class="btn blue mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> Edit</a>
						</div>
					</div>
					<div class="portlet-body">
						${rule.body }
					</div>
				</div>
			<s:set var="num" value="#num+1"/>
			</s:iterator>
		</div>
		
	
		<div class="span4 column sortable">
			<s:iterator var="i" begin="1" step="1" end="#row_num">
			<s:set var="rule" value="#request.ruleLists[#num]"/>
				<div class=" portlet box red">
					<div class="portlet-title">
						<input type="hidden" value="${rule.id }"/>
						<div class="caption"><i class="icon-reorder"></i>${rule.captition }t</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
						<div class="actions">
							<a href="RuleAction!addRuleView?id=${rule.id }" class="btn blue mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> Edit</a>
						</div>
					</div>
					<div class="portlet-body">
						${rule.body }
					</div>
				</div>
			<s:set var="num" value="#num+1"/>
			</s:iterator> 
		</div>
		
</div>
<div class="row-fluid">
	<s:iterator var="j" begin="1" step="1" end="#column_num">
	<s:set var="rule" value="#request.ruleLists[#num]"/>
		<div class=" portlet box yellow">
			
			<div class="portlet-title">
				<div class="caption"><i class="icon-reorder"></i>${rule.captition }</div>
				<div class="actions">
					<a href="RuleAction!addRuleView?id=${rule.id }" class="btn blue mini" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i> Edit</a>
					<a href="modules/addRule.jsp" class="btn green mini"  data-toggle="modal" data-target="#myModal"><i class="icon-plus"></i> Add</a>
				</div>
			</div>
			<div class="portlet-body">
				${rule.body }
			</div>
		</div>
		<s:set var="num" value="#num+1"/>
	</s:iterator>
</div>

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
			<a href="#" class="btn" data-dismiss="modal">取消</a> 
			<a href="#" class="btn btn-primary" id="submit" data-dismiss="modal">确定</a>
		</div>
</div>



<script src="js/portlet-draggable.js"></script> 
<script type="text/javascript" src="js/myAjaxify.js"></script>
<script>

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
			url: 'RuleAction!rulesList', 
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

jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();
   PortletDraggable.init();
});
</script>