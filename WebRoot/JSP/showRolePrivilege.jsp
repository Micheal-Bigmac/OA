<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="debugFile.inc" %>


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
										<td height="20">请给角色【 <s:property
												value="#request.roleName" />】赋权
												
										<input type="hidden" id="aclId" name="aclId" value="${aclId}"/>
										<input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
										</td>
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
													<td width="15%">全选</td>
												</tr>
												<s:iterator value="#request.mapModules" var="modules">
													<tr bgcolor="#EEEEEE">
														<td>${modules.key.name}</td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<s:iterator value="#modules.value" var="childrens">
														<tr bgcolor="#EEEEEE">
															<td><%--<s:property value="#state[0]"></s:property>--%></td>
															<td>${childrens.key.name}</td>
															<td>
																<s:set var="state" value="#childrens.value" /> 
																<%--<input type="text" name="stasss" <s:if test="#state[0] == 1"> value="1" </s:if> />
																<input type="text" name="stasss" <s:if test="#state[0] == 0"> value="0" </s:if> />--%>
																C<input type="checkbox" value="${state[0]}" <s:if test="#state[0] == 1">checked onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=-1')" </s:if> <s:if test="#state[0] == 0">onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=1')" </s:if> name="delid0">
																R<input type="checkbox" value="${state[1]}" <s:if test="#state[1] == 2">checked onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=-2')" </s:if> <s:if test="#state[1] == 0">onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=2')" </s:if> name="delid1">
																U<input type="checkbox" value="${state[2]}" <s:if test="#state[2] == 4">checked onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=-4')" </s:if> <s:if test="#state[2] == 0">onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=4')" </s:if> name="delid2">
																D<input type="checkbox" value="${state[3]}" <s:if test="#state[3] == 8">checked onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=-8')" </s:if> <s:if test="#state[3] == 0">onclick="Box(this,'RoleAction!privilege?moduleId=${childrens.key.id}&roleId=${roleId}&boxValue=8')" </s:if> name="delid3">
															</td>
															<td><input type="checkbox" value="button" onclick="Box(this,'RoleAction!privilegeToAllChoose?moduleId=${childrens.key.id}&roleId=${roleId}')"  />
															</td>
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


<SCRIPT>

var aclid = ${aclId};
var roleid = ${roleId};
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
		url: url + "&aclId=" + aclid,
		type: "post", 
		success: function() {
			ReloadPage("RoleAction!privilegeRole?role.id="+roleid);
		}
	});
}

</SCRIPT>