<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="debugFile.inc"%>
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

<div class='portlet-body form'>
	<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
		<div class="row-fluid">
			<div class="span6">
				<div class="control-group">
					<div class="controls">
						<select id="findCondition" name="findCondition">
						<option value="type">按产品类型</option>
						<option value="productName">按产品名称</option>
				</select>
						<input id="className" name="className" type="hidden" value="Product" />
				 <input id="textfield" name="textfield" type="text" />
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<div class="controls">
						<button id="select" type="button" class="btn btn-primary">查 询</button>
						<button id="Submit" class="btn btn-primary" type="button">高级搜索</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div class="row-fluid">
	<form id="fom">
		<table>
			<div class="row-fluid">
				<span>选择：<a href="#" id="selectAll">全选</a>-<a href="#" id="unselect">反选</a>
				</span>
				<button type="button" class="btn" id="deleteChose" data-action="ProductAction!deletePerson?method=8|${url }">删除所选产品信息</button>
				<a href="JSP/addProduct.jsp" class="btn ajaxify">添加产品信息</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center; font-size: 20px; background-color: #EEEEEE">产品信息列表</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>选择</th>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>产品编码</th>
							<th>产品类别</th>
							<th>成本价</th>
							<th>出售价</th>
							<th>出库总量</th>
							<th>入库总量</th>
							<th>当前库存</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="product" value="#request.listObject">
							<tr>
								<td><input type="checkbox" name="delid" value="${product.id }" /></td>
								<td><a class="ajaxify" href="ProductAction!edit?product.id=${product.id }">${product.id}</a></td>
								<td><a class="ajaxify" href="ProductAction!edit?product.id=${product.id}">${product.productName}</a></td>
								<td>${product.productCode }</td>
								<td>${product.type }</td>
								<td>${product.inputPrise }</td>
								<td>${product.outputPrise }</td>
								<td>${product.totalOutbound }</td>
								<td>${product.totalInventory }</td>
								<td>${product.currentInventory }</td>
								<td><a class="ajaxify" href="ProductAction!edit?product.id=${product.id}&method=4">编辑(修改)</a> <%-- 								<a class="deleteOne"  href="ProductAction!deleteproduct?delid=${product.id }&method=8">删除</a></td>
 --%> <a class="deleteOne" href="javascript:void(0)" data-action="ProductAction!deleteproduct?delid=${product.id }&method=8|${url }">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<s:set var="url" value="#request.url" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共 <span>${requestScope.pageCount}</span> 页 | 第 <span>${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					<ul>
						<li class="active"><a class="ajaxify" href="${url }&index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'>
							<li class="active"><a class="ajaxify" href="${url }&index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						<s:if test='(#request.currentIndex) < #pageCount'>
							<li class="active"><a class="ajaxify" href="${url }&index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
						<li class="active"><a class="ajaxify" href="${url }&index=${pageCount }">末页</a></li>
					</ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>
<script>
	$("#select").click(function(e) {
		var pageContent = $('.page-content .page-content-body');

		$.ajax({
			url : $('#form_Select').attr('action'),
			data : $('#form_Select').serialize(),
			success : function(res) {
				pageContent.html(res);
			},
			error : function() {
				alert("你输入的有问题");
			}
		});
	});
</script>
