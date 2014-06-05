<#list field.fieldItems as fieldItem>
<input type="checkbox" name="props[${index}].${field.type.type}" value="${fieldItem.itemValues}"/>${fieldItem.itemValues}
&nbsp;&nbsp;
<input type="hidden" name="props[${index}].propertyName" value="${field.fieldName}"/>
<#assign index=index+1>
</#list>

