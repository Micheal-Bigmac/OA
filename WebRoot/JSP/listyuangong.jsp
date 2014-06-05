<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println(basePath);
%>

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">机构管理</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">组织管理</a> <i class="icon-angle-right"></i></li>
		<li><a href="#">人员管理</a></li>
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

<div class='row-fluid'>
	<form name="fom" id="fom" method="post" action="">
		<table class="table table-striped">
			<div class="row-fluid">
			 	<span >选择：<a href="#" id="selectAll">全选</a>-<a href="#" id="unselectAll" >反选</a> </span>
					<input name="Submit" class="btn" type="button" value="删除所选人员信息" onclick="deleteChose();"/>
					<!-- <input name="Submit" class="btn" type="button" class="btn" value="添加人员信息" data-toggle="modal" data-target="#myModal" /> -->
					 <a href="JSP/yuangong.jsp" class="btn" data-toggle="modal" data-target="#myModal">添加人员信息</a> 
					<!-- <a class="ajaxify" href="JSP/yuangong.jsp" >添加人员信息</a> -->
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
							员工详细列表 
					</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >员工编号</th>
							<th >真实姓名</th>
							<th>职位</th>
							<th>员工类型</th>
				
							<th>性别</th>
							<th>年龄</th>
							<th>出生年月</th>
							<th>联系电话</th>
							<th>部门名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator var="person" value="#request.personList">
						<tr>
							<td ><input type="checkbox" name="delid" value="${person.id }" />
							</td>
							<td ><a class="ajaxify" href="PersonAction!edit?person.id=${person.id }">${person.id }</a></td>
							<td ><a class="ajaxify" href="PersonAction!edit?person.id=${person.id }">${person.name }</a>
							</td>
							<td >${person.personPosition.name }</td>
							<td>${person.personType.name }</td>
							<td >${person.sex }</td>
							<td >${person.age }</td>
							<td >${person.birthday }</td>
							<td >${person.phone }</td>
							<td >${person.organization.name}</td>
							<td ><a class="ajaxify" href="PersonAction!edit?person.id=${person.id }">编辑</a>&nbsp;|&nbsp;
								<a class="ajaxify" href="yuangongsalary.html">发工资</a>&nbsp;|&nbsp; <a href="yuangongxiangmu.html">项目</a>&nbsp;|&nbsp;
								<a  class="ajaxify" href="PersonAction!deletePerson?person.id=${person.id}">删除</a></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</table>
		
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
			    <li class="active"><a class="ajaxify" href="PersonAction!personList?index=1">首页</a></li>
			   <s:if test='(#request.currentIndex) > 1'> 
			  		<li class="active"><a class="ajaxify" href="PersonAction!personList?index=${requestScope.currentIndex-1}">上页</a></li>
			  	</s:if>
			  	<s:else>
			  		<li class="disabled"><a href="javascript::">上页</a></li>
			  	</s:else>
			    
			   <s:if test='(#request.currentIndex) < #pageCount'> 
			  		<li class="active"><a class="ajaxify" href="PersonAction!personList?index=${requestScope.currentIndex+1}">下页</a></li>
			  	</s:if>
			  	<s:else>
			  		<li class="disabled"><a href="javascript::">下页</a></li>
			  	</s:else>
			  <li class="active"><a class="ajaxify" href="PersonAction!personList?index=${pageCount }">末页</a></li>
			  </ul>
			</div>
	</div>
	</form>
	
	<div id="myModal" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3>对话框标题</h3>
		</div>
		<div class="modal-body">
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">取消</a> 
			<a href="#" class="btn btn-primary" id="submit" data-dismiss="modal">确定</a>
		</div>
</div>

<script src="js/myAjaxify.js" type="text/javascript"></script>

<script type="text/javascript">

$("#submit").click(function(e) {
	$.ajax({
		url: $('#modal_form').attr('action'),
		data: $('#modal_form').serialize(),
		success: function() {
			$('myModal').modal('hide');
// 			e.preventDefault();
			pageContent = $('.page-content .page-content-body');
			
			$.ajax({
			url: 'PersonAction!personList', 
			success: function(res) {
	        	pageContent.html(res);
			}
		});
		}
	});
});
$("#selectAll").click(
	selectAll();
);
$("#unselectAll").click(
	unselectAll();
);
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function deleteChose(){
	   document.getElementById("fom").action="PersonAction!deletePerson";
	   document.getElementById("fom").submit();
}
</script>