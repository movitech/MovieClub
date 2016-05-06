<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consumer Role Management</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('Being submitted, please wait ...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("Make a mistake, please correct.");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/consumer_role/consumerRole/">Consumer Role List</a></li>
		<li class="active"><a href="${ctx}/consumer_role/consumerRole/form?id=${consumerRole.id}"><shiro:hasPermission name="consumer_role:consumerRole:edit">${not empty consumerRole.id?'Modify':'Add'}</shiro:hasPermission> Consumer Role<shiro:lacksPermission name="consumer_role:consumerRole:edit">View</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="consumerRole" action="${ctx}/consumer_role/consumerRole/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="display:none;">
			<label class="control-label">parent_role_id：</label>
			<div class="controls">
                <form:input path="parent" htmlEscape="false" maxlength="100" class="input-xlarge required" value="null"/>
				<%--<sys:treeselect id="consumerRole" name="consumerRole.id" value="${consumerRole.id}" labelName="consumerRole.name" labelValue="${consumerRole.name}"--%>
								<%--title="角色" url="/consumer_role/consumerRole/treeData" cssClass="required"/>--%>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">Role Name:</label>
			<div class="controls">
				<form:input path="roleName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Description:</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="consumer_role:consumerRole:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>