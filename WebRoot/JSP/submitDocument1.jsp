<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<body class="ContentBody">
	
	<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr></tr>
		<tr align="center">
			<td class="TablePanel">添加流程</td>
		</tr>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
					<legend>流程信息</legend>
					<s:form action="WorkFlowAction!addWorkFlow" method="post" enctype="multipart/form-data"
						id="form">
						<div class="MainDiv">
						<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
						
							<tr>
								<td align="right">添加流程定义</td>
								<td align="center"><input name="uploadFiles" type="file"
									 />
								</td>
								<td align="center">&nbsp;</td>
							</tr>
							<tr>
								<td align="right">添加流程图片${param.id }</td>
								<td align="center"><input name="uploadFiles" type="file"
									/>
									<input type="hidden" name="workFlow.id" value="${param.id }"> 
								</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
						<div > 
							<table>
								<tr >
								<td> <input type="submit" value="提交" onclick=""></td>
								<td> <input type="button" value="返回" onclick="window.history.go(-1);"></td>
								</tr>
							</table>
						</div>
						</div>
					</s:form>
					<br />
				</fieldset></TD>
		</TR>
	</TABLE>
	
</body>
</html>
