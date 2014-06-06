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
<link href="css/css.css" rel="stylesheet" type="text/css" />
<SCRIPT>

var personId = "${personId}";
function ReloadPage(url) {
	pageContent = $('.page-content .page-content-body');
	
//  	App.blockUI(pageContent, false);
	$.ajax({
		url: url,
		success: function(res) {
			App.unblockUI(pageContent);
        	pageContent.html(res);
		}
	});
}

function Box(checkbox, url) {
	console.log("Box: ", url);
	
	$.ajax({
		url: url,
		type: "post", 
		success: function() {
			ReloadPage("UserAction!distributeUser?user.personid.id="+personId);
		}
	});
}
</SCRIPT>
</head>
<body>
	<form name="fom" id="fom" method="post" action="">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30">
					<table width="93%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" >
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
								</table></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td><table id="subtree1" style="DISPLAY: " width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="94%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="20">请给用户【<s:property
												value="#request.username" />】赋权
									</tr>
									<tr>
										<td height="40" class="font42">
											<table width="92%" border="0" cellpadding="4" cellspacing="1"
												bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="7" align="center"
														style="font-size:16px">权限列表</td>
												</tr>
												<tr bgcolor="#EEEEEE">
													<td width="20%">顶级模块</td>
													<td width="20%">二级模块</td>
													<td width="20%">权限</td>
													<td width="20%">继承</td>
													<td width="15%">全选</td>
												</tr>
												<s:iterator value="#request.userPrivileges" var="modulesUser">
													<tr bgcolor="#EEEEEE">
														<td>${modulesUser.key.name}</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<s:iterator value="#modulesUser.value" var="childrensUser">
														<tr bgcolor="#EEEEEE">
														<td></td>
															<td>${childrensUser.key.name}</td>
															<td>
																<s:set var="stateUser" value="#childrensUser.value" /> 
																C<input type="checkbox" value="${stateUser[0]}" <s:if test="#stateUser[0] == 1">checked onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=-1&userId=${userId}')" </s:if> <s:if test="#stateUser[0] == 0">onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=1&userId=${userId}')" </s:if> name="deliduser0">
																R<input type="checkbox" value="${stateUser[1]}" <s:if test="#stateUser[1] == 2">checked onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=-2&userId=${userId}')" </s:if> <s:if test="#stateUser[1] == 0">onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=2&userId=${userId}')" </s:if> name="deliduser1">
																U<input type="checkbox" value="${stateUser[2]}" <s:if test="#stateUser[2] == 4">checked onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=-4&userId=${userId}')" </s:if> <s:if test="#stateUser[2] == 0">onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=4&userId=${userId}')" </s:if> name="deliduser2">
																D<input type="checkbox" value="${stateUser[3]}" <s:if test="#stateUser[3] == 8">checked onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=-8&userId=${userId}')" </s:if> <s:if test="#stateUser[3] == 0">onclick="Box(this,'UserAction!privilegeToUserPart?moduleId=${childrensUser.key.id}&boxValue=8&userId=${userId}')" </s:if> name="deliduser3">
															</td>
															<td>
															<input type="checkbox" value="button" <s:if test="#stateUser[4] == 0"> checked onclick="Box(this,'UserAction!privilegeToUser?moduleId=${childrensUser.key.id}&userId=${userId}&boxValues=10')"</s:if> <s:else> onclick="Box(this,'UserAction!privilegeToUser?moduleId=${childrensUser.key.id}&userId=${userId}')"</s:else> /> </td>
															<td><input type="checkbox" value="button" onclick="Box(this,'UserAction!privilegeToUserAllChose?moduleId=${childrensUser.key.id}&userId=${userId}')" /></td>
														</tr>
													</s:iterator>
												</s:iterator>
											</table></td>
									</tr>
									<tr>
										<td height="40" class="font42">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>
