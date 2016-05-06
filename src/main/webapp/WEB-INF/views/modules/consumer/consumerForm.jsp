<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consumer Management</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();

			$("#inputForm").validate({
				messages: {
					confirmNewPassword: {equalTo: "Enter the same password as above"}    //输入与上面相同的密码
				},
				submitHandler: function(form){
					loading('Being submitted, please wait ...');
                    if($("#newPassword").hasClass("required")){
                        $("#defaultPassword").val($("#newPassword").val());
                    }

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
		<li><a href="${ctx}/consumer/consumer/">Consumer List</a></li>
		<li class="active"><a href="${ctx}/consumer/consumer/form?id=${consumer.id}"><shiro:hasPermission name="consumer:consumer:edit">${not empty consumer.id?'Modify':'Add'}</shiro:hasPermission> Consumer<shiro:lacksPermission name="consumer:consumer:edit">View</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="consumer" action="${ctx}/consumer/consumer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">Email：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">First Name：</label>
			<div class="controls">
				<form:input path="firstName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Last Name：</label>
			<div class="controls">
				<form:input path="lastName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">Nick Name：</label>
            <div class="controls">
                <form:input path="nickName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">Gender：</label>
			<div class="controls">
				<form:radiobuttons path="gender" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Birthday：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${consumer.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

        <div class="control-group">
            <label class="control-label">Password:</label>
            <div class="controls">
                <form:input id="defaultPassword" path="password" htmlEscape="false" maxlength="100" class="input-xlarge " style="display:none;"/>
                <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty consumer.id?'required':''}"/>
                <c:if test="${empty consumer.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
                <c:if test="${not empty consumer.id}"><span class="help-inline">Without change your password, please leave blank.</span></c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Confirm Password:</label>
            <div class="controls">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
                <c:if test="${empty consumer.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
            </div>
        </div>
        <%----%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">Password:</label>--%>
			<%--<div class="controls">--%>
				<%--<input id="password" name="password" type="password" value="" maxlength="50" minlength="3" class="${empty consumer.id?'required':''}"/>--%>
				<%--<c:if test="${empty consumer.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>--%>
				<%--<c:if test="${not empty consumer.id}"><span class="help-inline">Without change your password, please leave blank.</span></c:if>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">Confirm Password:</label>--%>
			<%--<div class="controls">--%>
				<%--<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#password"/>--%>
				<%--<c:if test="${empty consumer.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>--%>
			<%--</div>--%>
		<%--</div>	--%>
        <%----%>
        <div class="control-group">
            <label class="control-label">Consumer Role：</label>
            <div class="controls">
                <form:select path="userRoleId" class="input-xlarge required">
                    <form:option value="" label=""/>
                    <form:options items="${roles}" itemLabel="roleName" itemValue="id" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
		<%--<div class="control-group">
			<label class="control-label">access_token：</label>
			<div class="controls">
				<form:input path="accessToken" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">token_expire_date：</label>
			<div class="controls">
				<input name="tokenExpireDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${consumer.tokenExpireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">Active：</label>
			<div class="controls">
				<form:checkbox path="active" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">User_image：</label>
			<div class="controls">
				<form:hidden id="userImage" path="userImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="userImage" type="files" uploadPath="/consumer/consumer" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">Remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="consumer:consumer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>