<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Movie 管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			if(${not empty movie.id}) {
				//
				//TODO: 获取 Movie Tag 列表并绑定信息
				//
			}

		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/movie/movie/">Movie List</a></li>
		<li class="active"><a href="${ctx}/movie/movie/form?id=${movie.id}"><shiro:hasPermission name="movie:movie:edit">${not empty movie.id?'Modify':'Add'}</shiro:hasPermission><shiro:lacksPermission name="movie:movie:edit">View</shiro:lacksPermission> Movie</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="movie" action="${ctx}/movie/movie/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<%-- 电影名称: movie name --%>
		<div class="control-group">
			<label class="control-label">Name：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<%-- 电影分类: movie genre --%>
		<div class="control-group">
			<label class="control-label">Movie Genre：</label>
			<div class="controls">
				<form:select path="genre" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${movieGenres}" itemLabel="genreName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<%-- 电影标签: movie tag --%>
		<div class="control-group">
			<label class="control-label">Movie Tags：</label>
			<div class="controls">
				<form:checkboxes path="tags" items="${movieTags}" itemLabel="tagTitle" itemValue="id" htmlEscape="false" class="required"/>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>

		<%-- 电影海报: movie poster(image) --%>
		<div class="control-group">
			<label class="control-label">Movie Poster：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/movie/movie" selectMultiple="true"/>
			</div>
		</div>

		<%-- 电影发布时间: movie release time --%>
		<div class="control-group">
			<label class="control-label">Release Time：</label>
			<div class="controls">
				<input name="yearRelease" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fns:parseDate(movie.yearRelease)}" pattern="yyyy"/>"
					   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			</div>
		</div>

		<%-- 电影时长: movie duration --%>
		<div class="control-group">
			<label class="control-label">Duration：</label>
			<div class="controls">
				<form:input path="videoTime" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<%--主演: starring--%>
		<div class="control-group">
			<label class="control-label">Starring：</label>
			<div class="controls">
				<form:input path="starring" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<%--导演: director--%>
		<div class="control-group">
			<label class="control-label">Director：</label>
			<div class="controls">
				<form:input path="director" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<%--编剧: Screenwriter--%>
		<div class="control-group">
			<label class="control-label">Screenwriter：</label>
			<div class="controls">
				<form:input path="screenwriter" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<%-- 制片人: producer--%>
		<div class="control-group">
			<label class="control-label">Producer：</label>
			<div class="controls">
				<form:input path="producer" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<%--剧情简介: movie profile--%>
		<div class="control-group">
			<label class="control-label">plot：</label>
			<div class="controls">
				<form:textarea path="plot" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>

		<%--演员&工作人员: Cast And Crew--%>
		<div class="control-group">
			<label class="control-label">Cast&Crew：</label>
			<div class="controls">
				<form:textarea path="castAndCrew" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>

		<%--备注: Remarks--%>
		<div class="control-group">
			<label class="control-label">Remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="movie:movie:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Go Back" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>