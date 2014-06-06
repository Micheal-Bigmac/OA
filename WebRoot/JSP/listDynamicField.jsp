<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<SCRIPT language=JavaScript>

	function link() {
		document.getElementById("fom").action = "DynamicFieldAction!AddFieldView?id=${id}";
		document.getElementById("fom").submit();
	}

	function deleteChose() {
		document.getElementById("fom").action = "DynamicFieldAction!deleteDynamicField";
		document.getElementById("fom").submit();
	}
</SCRIPT>

<base href="<%=basePath%>">

<!-- PAGE TITLE & BREADCRUMB-->
<div class="row-fluid">
	<h3 class="page-title">表单定义</h3>
	<ul class="breadcrumb">
		<li><i class="icon-home"></i> <a class="ajaxify" href="index.jsp">主页</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">流程列表</a> <i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">表单定义</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">公文列表</a><i class="icon-angle-right"></i></li>
		<li><a class="ajaxify" href="#">归档管理</a></i></li>
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
				<span class="newfont07">选择：<a href="#"  id="selectAll">全选</a>-<a href="#"  id="unselect">反选</a>
			</span>
			<a class="ajaxify btn" href="DynamicFieldAction!deleteDynamicField">删除所选表单信息</a>
			<a class="ajaxify btn" href="DynamicFieldAction!AddFieldView?id=${id}">添加表单域</a>
			</div>
			<div class="row-fluid">
				<div class="row-fluid" style="text-align: center;font-size:20px;background-color:#EEEEEE">
						表单详细列表
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th >选择</th>
							<th >
								表单编号</th>
							<th >表单标签</th>
							<th >表单名称</th>
							<th >表单输入形成</th>
	
							<th >表单类型</th>
							<th >表单模板</th>
							<th >操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="dynamicField" value="#request.dynamicFieldList">
							<s:set name="input" value="#dynamicField.input.id"/>
								<tr>
									<td ><input type="checkbox"
										name="delid" value="${dynamicField.id }"/>
									</td>
									
									<td height="20" >${dynamicField.id}
									</td>
									<td >
									${dynamicField.fieldLabel}
									</td>
									<td height="20" >
										${dynamicField.fieldName }
									</td>
									<td >${dynamicField.input.name }</td>
									<td >${dynamicField.type.name}</td>
									<td height="20" >${dynamicField.dynamicForm.template}</td>

									
									<td bgcolor="#FFFFFF" align="center"> 
									<a class="ajaxify" href="DynamicFieldAction!modifyDynamicField?id=${dynamicField.id }">修改</a>&nbsp;|&nbsp;
										<s:if test="#input ==2 || #input ==3 || #input == 4 ">
											<a class="ajaxify" href="FieldItemAction!ListItems?id=${dynamicField.id }">条目</a>&nbsp;|&nbsp;
											</s:if>
											<a href="DynamicFieldAction!deleteDynamicField?delid=${dynamicField.id }">删除</a>
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
						<li class="active"><a class="ajaxify" href="DynamicFormAction!list?index=1">首页</a></li>
						<s:if test='(#request.currentIndex) > 1'> 
							<li class="active"><a class="ajaxify" href="DynamicFormAction!list?index=${requestScope.currentIndex-1}">上页</a></li>
						</s:if>
						<s:else>
						<li class="disabled"><a href="#">上页</a></li>
						</s:else>
						
						<s:if test='(#request.currentIndex) < #pageCount'> 
							<li class="active"><a class="ajaxify" href="DynamicFormAction!list?index=${requestScope.currentIndex+1}">下页</a></li>
						</s:if>
						<s:else>
							<li class="disabled"><a href="#">下页</a></li>
						</s:else>
					 	<li class="active"><a class="ajaxify" href="DynamicFormAction!list?index=${pageCount }">末页</a></li>
					  </ul>
				</div>
			</div>
		</table>
	</form>
</div>
 <script src="js/myAjaxify.js" type="text/javascript"></script>