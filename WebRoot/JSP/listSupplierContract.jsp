<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<SCRIPT language=JavaScript>
	
	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}

	function link() {
		document.getElementById("fom").action = "JSP/yuangong.jsp";
		document.getElementById("fom").submit();
	}

	function deleteChose() {
		document.getElementById("fom").action = "SupplierContractAction!deleteSupplierContact";
		document.getElementById("fom").submit();
	}
</SCRIPT>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">供应商管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">供应商管理</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">供应商联系人</a></li>
	</ul>
</div>

<div class='row-fluid'> 
<select name="select2">
	<option>
		按录入时间
	</option>
	<option>
		按注销时间
	</option>
</select>

<input name="textfield" type="text" readonly="readonly" />
<span>至</span>
<input name="textfield" type="text" readonly="readonly" />
<input class="btn" name="Submit" type="button" value="查 询" style='margin-bottom: 10px;'/>
<input name="Submit" class="btn" type="button" value="高级搜索" style='margin-bottom: 10px;'/>
</div>
<div class="row-fluid">
	<form name="fom" id="fom" method="post" action="">
		<table class="table table-striped">
			<div class="row-fluid">
				<span class="newfont07">选择：
					<a href="#"  onclick="selectAll();">全选</a>-<a href="#" class="right-font08" onclick="unselectAll();">反选</a>
				</span> 
				<input class="btn" name="Submit" type="button"  value="删除所选信息" onclick="deleteChose();" /> 
				<a class="btn ajaxify"  href="JSP/addSupplierContract.jsp" >添加供应商联系人信息</a> 
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						机构详细列表 
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >编号</th>
							<th >联系人名称</th>
							<th >供应商名称</th>
							<th >职位</th>
							<th >性别</th>
							<th >工作电话</th>
							<th >手机电话</th>
							<th >QQ</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="supplierContract" value="#request.SupplierContactList">
							<tr>
								<td><input type="checkbox" name="delid" value="${supplierContract.id }" /></td>
								<td ><a class="ajaxify" href="SupplierContractAction!edit?supplierContract.id=${supplierContract.id }">${supplierContract.id}</a></td>
								<td ><a class="ajaxify" href="SupplierContractAction!edit?supplierContract.id=${supplierContract.id}">${supplierContract.contactName}</a></td>
								<td >${supplierContract.supplier.supplierName }</td>
								<td >${supplierContract.position }</td>
								<td >${supplierContract.gender }</td>
								<td >${supplierContract.phone }</td>
								<td >${supplierContract.telephone }</td>
								<td >${supplierContract.QQ }</td>
								<td ><a class="ajaxify" href="SupplierContractAction!edit?supplierContract.id=${supplierContract.id}">编辑(修改)</a><a  href="SupplierContractAction!deleteSupplierContact?delid=${supplierContract.id }">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<s:set var="pageCount" value="(#request.totalSize-1)/10+1" />
				<div class="span4" style="margin: 20px 0px 20px 0px;">
					共
					<span >${requestScope.pageCount}</span>
					页 | 第
					<span >${requestScope.currentIndex}</span> 页
				</div>
				<div class="pagination pull-right">
					  <ul>
						<li class="active"><a class="ajaxify" href="SupplierContractAction!SupplierContactList?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="SupplierContractAction!SupplierContactList?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="SupplierContractAction!SupplierContactList?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="SupplierContractAction!SupplierContactList?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>