<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">产品信息查看</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">产品信息查看</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">销售合同登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">合同产品登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">收款计划</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">采购订单登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">订单产品登记</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">付款计划</a></li>
	</ul>
</div>

<div class='row-fluid'>
<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
	<table>
		<tr>
			<td>
				<select id="findCondition" name="findCondition">
					<option value="productName">
						按产品名称
					</option>
					<option value="salesName">
						按合同名称
					</option>
				</select>
			</td>
			<td>
				<input id="textfield" name="textfield" type="text"  />
			</td>
			<td>
				<input class="btn" id="select" type="button" value="搜索">
			</td>
			<td>
				<input name="Submit" class="btn" type="button" value="高级搜索" style='margin-bottom: 10px;'/>
			</td>
		</tr>
	</table>
	</form>
</div>
<div class="row-fluid">
	<form id="fom">
		<table>
			<div class="row-fluid">
				<span class="newfont07">选择：<a href="#"  id="selectAll">全选</a>-<a href="#"  id="unselect">反选</a>
				</span> 
				<button class="btn"  id="deleteChose" data-action="ContractProductAction!deleteContractProductRecord|${url }">删除所选合同产品</button>
				<a href="JSP/addContractProductRecord.jsp" class="btn ajaxify" >添加合同产品</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						产品合同记录详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >编号</th>
							<th >产品名称</th>
							<th >合同名称</th>
							<th >单价</th>
							<th >数量</th>
							<th >总价</th>
							<th >已付款</th>
							<th >欠款数</th>
							<th >是否交付</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="contractProductRecord" value="#request.listObject">
							<tr>
								<td ><input type="checkbox" name="delid" value="${contractProductRecord.id }" /></td>
								<td ><a class="ajaxify" href="ContractProductAction!edit?contractProductRecord.id=${contractProductRecord.id }">${contractProductRecord.id}</a></td>
								<td ><a class="ajaxify" href="ContractProductAction!edit?contractProductRecord.id=${contractProductRecord.id}">${contractProductRecord.product.productName}</a></td>
								<td >${contractProductRecord.agreement.salesName }</td>
								<td >${contractProductRecord.product.outputPrise }</td>
								<td >${contractProductRecord.number }</td>
								<td >${contractProductRecord.agreement.tatalPrice }</td>
								<td >${contractProductRecord.agreement.paidPrice }</td>
								<td >${contractProductRecord.agreement.loanPrice }</td>
								<td >${contractProductRecord.agreement.isPaid }</td>
								<td><a class="ajaxify" href="ContractProductAction!edit?contractProductRecord.id=${contractProductRecord.id}">编辑(修改)</a><a href="ContractProductAction!deleteContractProductRecord?delid=${contractProductRecord.id }">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<s:set var="url" value="#request.url" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共
					<span >${requestScope.pageCount}</span>
					页 | 第
					<span >${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					  <ul>
						<li class="active"><a class="ajaxify" href="${url}?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="${url}?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="#">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url}?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="#">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url}?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>

<script src="js/myAjaxify.js" type="text/javascript"></script>
<script>
$("#select").click(function(e) {
	e.preventDefault();
	var pageContent = $('.page-content .page-content-body');
	
	$.ajax({
		url: $('#form_Select').attr('action'),
		data: $('#form_Select').serialize(),
		success: function(res) {
			pageContent.html(res);
		},
		error: function(){
			alert("你输入的有问题");
		}
	});
});
</script>