<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>movie genre management</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('Under submission, please wait a moment...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("Please correct the input.");
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
		<li><a href="${ctx}/genre/movieGenre/">Movie Genre List</a></li>
		<li class="active"><a href="${ctx}/genre/movieGenre/form?id=${movieGenre.id}"><shiro:hasPermission name="genre:movieGenre:edit">${not empty movieGenre.id?'Modify':'Add'}</shiro:hasPermission><shiro:lacksPermission name="genre:movieGenre:edit">View</shiro:lacksPermission> Movie Genre</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="movieGenre" action="${ctx}/genre/movieGenre/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">Genre Title：</label>
			<div class="controls">
				<form:input path="genreName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Genre Image：</label>
			<div class="controls">
				<form:hidden id="genreImage" path="genreImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="genreImage" type="files" uploadPath="/genre/movieGenre" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Genre Image Title：</label>
			<div class="controls">
				<form:input path="genreImageTitle" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Sort Order：</label>
			<div class="controls">
				<form:input path="sortOrder" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Status：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="genre:movieGenre:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>