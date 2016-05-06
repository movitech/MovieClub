<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>movie管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/movie/movie/">Movie List</a></li>
		<shiro:hasPermission name="movie:movie:edit"><li><a href="${ctx}/movie/movie/form">Add Movie</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="movie" action="${ctx}/movie/movie/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Name：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">Movie Genre：</label>
				<form:select path="genre" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${movieGenres}" itemLabel="genreName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Director：</label>
				<form:input path="director" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Name</th>
				<th>Movie Genre</th>
				<th>Director</th>
				<th>Update Date</th>
				<shiro:hasPermission name="movie:movie:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="movie">
			<tr>
				<td><a href="${ctx}/movie/movie/form?id=${movie.id}">
					${movie.title}
				</a></td>
				<td>
					${movie.genreName}
				</td>
				<td>
					${movie.director}
				</td>
				<td>
					<fmt:formatDate value="${movie.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="movie:movie:edit"><td>
    				<a href="${ctx}/movie/movie/form?id=${movie.id}">Modify</a>
					<a href="${ctx}/movie/movie/delete?id=${movie.id}" onclick="return confirmx('Are you sure to delete the movie？', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>