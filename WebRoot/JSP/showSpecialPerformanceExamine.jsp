<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
</head>
<body class="ContentBody">
	<form action="PerformanceExamineAction!addPerformanceExamine" method="post" name="fom" id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
				<tr>
					<th >绩效管理页面</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>绩效管理</legend>
										<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
											<tr>
												<td >绩效考核名称:</td>
												<td ><input name="listPerformanceExamine.name" value="${listPerformanceExamine.name}" type="text" readOnly/>
												</td>
												<td >简要说明:</td>
												<td ><input name="listPerformanceExamine.instruction" value="${listPerformanceExamine.instruction}" type="text"  readOnly/>
												</td>
											</tr>
											<tr>
												<td>序号</td>
												<td>用户名</td>
												<s:iterator value="#request.listParameters" var="param">
													<td><s:property value="#param" />
													</td>
												</s:iterator>
											</tr>
											<s:iterator value="#request.mapData" var="map">
												<tr>
													<td>${map.key}</td>
													<s:iterator var="children" value="#map.value">
														<td>${children.key}</td>
														<s:iterator var="a" value="#request.children.value" status="stat">
															<td><input type="text" name="score" value="${a}" readonly /></td>
															<s:set var="a" value="" />
														</s:iterator>
													</s:iterator>
												</tr>
											</s:iterator>
										</table>
										<br />
									</fieldset></TD>
							</TR>
						</TABLE></td>
				</tr>
				<TR>
				</TR>
			</TABLE>
		</div>
	</form>
</body>
</html>
