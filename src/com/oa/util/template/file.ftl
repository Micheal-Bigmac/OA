<input type="file" name="uploadFiles"/>
<input type="hidden" name="props[${index}].propertyName" value="${field.fieldName}"/>
<input type="hidden" name="props[${index}].${field.type.type}" value="uploadFiles"/>
<#assign index=index+1>
