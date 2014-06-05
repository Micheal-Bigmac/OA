<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h3>Leave a Comment</h3>
<form action="RuleAction!addRule" id="model-form">
	 <label>制度id</label>
	<input type="text" name="rules.id" class="span5 m-wrap" value="${rule.id}" readOnly>
	<label>制度名称<span class="color-red">*</span></label>
	<input type="text" name="rules.captition" class="span5 m-wrap" value="${rule.captition }">
	<label>详细内容</label>
	<textarea class="span5 m-wrap" name="rules.body" rows="8" >${rule.body }</textarea>
</form>
