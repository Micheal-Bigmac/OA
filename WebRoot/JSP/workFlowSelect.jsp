<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<h3 class="form-section">选择流程</h3>
	<div class="row-fluid">
		<div class="span6 ">
			<div class="control-group">
				<label class="control-label">产品名称:</label>
				<div class="controls">
					<select class="span12" name="workflowId" check-type="required">
						<s:iterator var="workflow" value="#session.workFlows">
							<option value=${workflow.id }>${workflow.name }</option>
						</s:iterator>
					</select>
				</div>
			</div>
		</div>
	</div>
</body>
</html>