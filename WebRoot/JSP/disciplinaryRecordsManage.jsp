<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

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

<div class='row-fluid'> 
<form name="formSelect" id="form_Select" method="post" action="FindAction!findByCondition">
<table>
	<tr>
		<td>
			<select id="findCondition" name="findCondition">
				<option value="name">
					按用户名字
				</option>
				<option value="awardUnit">
					按授予单位
				</option>
			</select>
		</td>
		<td>
			<input id="className" name="className" type="hidden" value="DisciplinaryRecords"/>
			<input id="textfield" name="textfield" type="text"  />
		</td>
		<td>
			<input id="select" class="btn" name="Submit" type="submit" value="查 询"  style='margin-bottom: 10px;'/>
		</td>
		<td>
			<input name="Submit" class="btn" type="button" value="高级搜索" style='margin-bottom: 10px;'/>
		</td>
	</tr>
</table>
</form>
</div>

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
		document.getElementById("fom").action = "JSP/addDisciplinaryRecords.jsp";
		document.getElementById("fom").submit();
	}
</SCRIPT>
<div class="row-fluid">
	<form>
		<table>
			<div class="row-fluid">
					<span class="newfont07">选择：</span> 
					<a href="JSP/addDisciplinaryRecords.jsp" class="btn ajaxify" >添加奖惩</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						奖惩列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>职员用户</th>
							<th>奖惩区分</th>
							<th>奖惩结果</th>
							<th>授予单位</th>
							<th>奖惩日期</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.listObject" var="dis">
							<tr bgcolor="#FFFFFF">
								<td>${dis.id}</td>
								<td>${dis.personId.name}</td>
								<td>${dis.disciplinaryDistringuish}</td>
								<td>${dis.disciplinaryResult}</td>
								<td>${dis.awardUnit}</td>
								<td>${dis.disciplinaryDate}</td>
								<td><a class="ajaxify"
									href="DisciplinaryRecordsAction!updateDis?disId=${dis.id}&method=4">修改</a>&nbsp;<a class="ajaxify"
									href="DisciplinaryRecordsAction!deleteDis?disId=${dis.id}&method=8">删除</a>
								</td>
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
						<li class="active"><a class="ajaxify" href="${url }&index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="${url}&index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="javascript:;">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="${url}&index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="javascript:;">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="${url}&index=${pageCount }">末页</a></li>
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