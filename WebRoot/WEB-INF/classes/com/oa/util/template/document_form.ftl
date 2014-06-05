	<#assign i = 0>
	<#assign index=0>
    <#list form.dynamicFields as field>
    <#assign i = i + 1 >
    <#if field_index % 2 = 0>
		<div class="row-fluid">
	</#if>
		<div class="span6 ">
			<div class="control-group">
				<label class="control-label">${field.fieldLabel}</label>
					<div class="controls">
					<#include "${field.input.template}">
					</div>
			</div>
		</div>
	<#if i = 2 || !field_has_next>
	<#assign i = 0>
		</div>
	</#if>
	</#list>