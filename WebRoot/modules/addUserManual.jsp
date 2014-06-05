<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h3>Leave a Comment</h3>
<form action="PersonDailyAction!addUserManual" id="model-form">
	 <label>id</label>
	<input type="text" name="daily.id" class="span5 m-wrap" value="${daily.id}" readOnly>
	<label>名称<span class="color-red">*</span></label>
	<input type="text" name="daily.title" class="span5 m-wrap" value="${daily.title }">
	<label>内容</label>
	<textarea class="span5 m-wrap" name="daily.body" rows="8" >${daily.body }</textarea>
</form>
