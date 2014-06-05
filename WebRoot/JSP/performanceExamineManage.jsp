<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

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
		document.getElementById("fom").action = "PerformanceExamineAction!getAddData";
		document.getElementById("fom").submit();
	}
</SCRIPT>
<div class="row-fluid">
	<h3 class="page-title">绩效考核</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">人力资源</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">奖罚记录</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效参数</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">绩效考核</a><i class="icon-angle-right"></i></li>
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
	<form>
		<table>
			<div class="row-fluid">
				<span class="newfont07">选择：</span> 
					<a href="PerformanceExamineAction!getAddData" class="btn ajaxify">添加绩效考核</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						考核参数列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >序号</th>
							<th >绩效考核名称</th>
							<th >简要说明</th>
							<th >录入人</th>
							<th >录入时间</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listPerformanceExamine" var="pe">
							<tr bgcolor="#FFFFFF">
								<td>${pe.id}</td>
								<td><a class="ajaxify"
									href="PerformanceExamineAction!getPerformanceExamineInfo?peId=${pe.id}">${pe.name}</a></td>
								<td>${pe.instruction}</td>
								<td>${pe.recordUser}</td>
								<td>${pe.date}</td>
								<td><a class="ajaxify"
									href="#">修改</a>&nbsp;<a class="ajaxify"
									href="PerformanceExamineAction!deletePerformanceExamine?peId=${pe.id}&method=8">删除</a>
								</td>
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
						<li class="active"><a class="ajaxify" href="PerformanceExamineAction!find?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="PerformanceExamineAction!find?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javas:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="PerformanceExamineAction!find?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="PerformanceExamineAction!find?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
<script src="js/myAjaxify.js" type="text/javascript"></script>