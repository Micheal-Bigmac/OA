<select name="props[${index}].${field.type.type}">
	<#list field.fieldItems as item>
	<option value="${item.itemValues}">${item.label}</option>
	</#list>
	<input type="hidden" name="props[${index}].propertyName" value="${field.fieldName}"/>
</select>
<#assign index=index+1>


