<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Reset Password</title>
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

                    $("#defaultPassword").val($("#password").val());

//                    if($("#password").hasClass("required")){
//                        $("#defaultPassword").val($("#password").val());
//                        alert($("#defaultPassword").val())
//                    }
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
		<li>Reset Password</li>
		<%--<li class="active"><a href="${ctx}/consumer/consumer/form?id=${consumer.id}"><shiro:hasPermission name="consumer:consumer:edit">${not empty consumer.id?'Modify':'Add'}</shiro:hasPermission> Consumer<shiro:lacksPermission name="consumer:consumer:edit">View</shiro:lacksPermission></a></li>--%></ul><br/>
	<form:form id="inputForm" modelAttribute="consumer" action="/f/consumer/resetNow" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <%--<div class="control-group">--%>
            <%--<label class="control-label">New Password：</label>--%>
            <%--<div class="controls">--%>
                <%--<form:input id="defaultPassword" path="password" maxlength="50" minlength="3"  htmlEscape="false" style="display:block;"/>--%>
                <%--<input id="password" name="password" type="password"   maxlength="50" minlength="3" value="" htmlEscape="false" class="${empty consumer.password?'required':''}"/>--%>
                <%--<span class="help-inline"><font color="red">*</font> </span>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="control-group">
            <label class="control-label">Password:</label>
            <div class="controls">
                <form:input id="defaultPassword" path="password" htmlEscape="false" maxlength="50" minlength="3" class="input-xlarge " style="display:none;"/>
                <input id="password" type="password" htmlEscape="false" maxlength="50" minlength="3" value="" class="${empty consumer.id?'required':''}" />
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Confirm Password:</label>
            <div class="controls">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#password"/>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">In_active：</label>
            <div class="controls">
                <form:input path="inActive" htmlEscape="false" maxlength="1" class="input-xlarge "/>
            </div>
        </div>--%>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="ResetPassword"/>
		</div>
	</form:form>
</body>
</html>