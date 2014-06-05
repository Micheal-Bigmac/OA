<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJdffsp.jsp' starting page</title>
<link rel="stylesheet" rev="stylesheet"
	href="<%=basePath%>css/style.css" type="text/css" media="all" />
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
<script src="js/Calendar.js">
	
</script>
<script type="text/javascript">
	/*调取日历*/
	var c = new Calendar("c");
	document.write(c);
</script>
</head>
<body class="ContentBody">
	<form action="PerformanceExamineAction!addPerformanceExamine"
		method="post" name="fom" id="fom" target="mainFrame">

		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">绩效管理页面</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
								<td align="left"><input
									type="button" name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" />
								</td>
							</tr>
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>绩效管理</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">
											<tr>
											</tr>
											<tr>
												<td align="right" width="19%">绩效考核名称:</td>
												<td width="27%">
													<input name="listPerformanceExamine.name"
													value="${listPerformanceExamine.name}" class="text"
													style="width:154px" /></td>
											</tr>
											<tr>
												<td align="right" width="19%">简要说明:</td>
												<td width="27%"><input
													name="listPerformanceExamine.instruction"
													value="${listPerformanceExamine.instruction}" class="text"
													style="width:154px" /></td>
											</tr>
											<tr>
												<td>序号</td>
												<td>用户名</td>
												<s:iterator value="#request.listParameters" var="param">
													<td><s:property value="#param" /></td>
												</s:iterator>
											</tr>
												<s:iterator value="#request.mapData" var="map">
													<tr>
														<td>${map.key}</td>
														<s:iterator var="children" value="#map.value">
															<td>${children.key}</td>
															<s:iterator var="a" value="#request.children.value" status="stat">
																<td>															
																	<input type="text" name="score" value ="${a}" readonly/>
																</td>
																<s:set var="a" value=""/>
															</s:iterator>
														</s:iterator>
													</tr>
												</s:iterator>
										</table>
										<br />
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"> <input
						type="button" name="Submit2" value="返回" class="button"
						onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>


		</div>
	</form>
</body>
</html>
