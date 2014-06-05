<#list field.fieldItems as fieldItem>
<input type="radio" name="props[${index}].${field.type.type}" value="${fieldItem.itemValues}"/>${fieldItem.itemValues}
&nbsp;&nbsp;
</#list>
<input type="hidden" name="props[${index}].propertyName" value="${field.fieldName}"/>
<#assign index=index+1>

