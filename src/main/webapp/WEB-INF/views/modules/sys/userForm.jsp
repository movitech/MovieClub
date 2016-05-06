<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User Management</title>  <!-- 用户管理 -->
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#companyId").val('c8bf9626b0664ecba42268f7bc99dfa6');
			$("#officeId").val('e35b6ad7453c4864b9353bf0d3ade920');
			$("#roleIdList1").attr("checked","true");
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "User login name already exists"},  //用户登录名已存在
					confirmNewPassword: {equalTo: "Enter the same password as above"}    //输入与上面相同的密码
				},
				submitHandler: function(form){
					loading('Being submitted, please wait ...');  //正在提交，请稍等...
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("Make a mistake, please correct.");   //输入有误，请先更正。
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
		<li><a href="${ctx}/sys/user/list">User List</a></li>  <!-- 用户列表 -->
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}"><shiro:hasPermission name="sys:user:edit">${not empty user.id?'Modify':'Add'}</shiro:hasPermission> User<shiro:lacksPermission name="sys:user:edit">View</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group" style="display:none;">
			<label class="control-label">Head Portrait:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">Home Company:</label>
			<div class="controls">
                <sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="admin"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">Home Department:</label>
			<div class="controls">
                <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="admin-dep"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">Job Number:</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="50" class="required" value="null"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Name:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Login Name:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Password:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">Without change your password, please leave blank.</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Confirm Password:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Email:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Phone:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Mobile:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">Allow Login:</label>
			<div class="controls">
				<form:select path="loginFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> "Yes" to represent this account allowed to log in, "No" indicates that this account is allowed to log</span>   <!-- “是”代表此账号允许登录，“否”则表示此账号不允许登录 -->
			</div>
		</div>
		<%--
		<div class="control-group">
			<label class="control-label">User Type:</label>
			<div class="controls">
				<form:select path="userType" class="input-xlarge">
					<form:option value="" label="Please Select"/>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		--%>
		<div class="control-group" style="display:none;">
			<label class="control-label">User Role:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Remark:</label> <!-- 备注 -->
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label">Creation Date:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Last Online:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;Date: <fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>