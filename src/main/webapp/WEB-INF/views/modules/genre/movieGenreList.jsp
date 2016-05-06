<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Movie Genre Management</title>
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
		<li class="active"><a href="${ctx}/genre/movieGenre/">Movie Genre List</a></li>
		<shiro:hasPermission name="genre:movieGenre:edit"><li><a href="${ctx}/genre/movieGenre/form">Add Movie Genre</a></li></shiro:hasPermission>
	</ul>

	<form:form id="searchForm" modelAttribute="movieGenre" action="${ctx}/genre/movieGenre/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Genre Title：</label>
				<form:input path="genreName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Genre Title</th>
				<%--<th>Filename Of Genre Poster</th>--%>
				<th>Genre Image Title</th>
				<th>Sort Order</th>
				<th>Update Date</th>
				<%--<th>Remarks</th>ßßß--%>
				<shiro:hasPermission name="genre:movieGenre:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="movieGenre">
			<tr>
				<td><a href="${ctx}/genre/movieGenre/form?id=${movieGenre.id}">
					${movieGenre.genreName}
				</a></td>
				<%--<td>--%>
					<%--${movieGenre.genreImage}--%>
				<%--</td>--%>
				<td>
					${movieGenre.genreImageTitle}
				</td>
				<td>
					${movieGenre.sortOrder}
				</td>
				<td>
					<fmt:formatDate value="${movieGenre.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${movieGenre.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="genre:movieGenre:edit"><td>
    				<a href="${ctx}/genre/movieGenre/form?id=${movieGenre.id}">Modify</a>
					<a href="${ctx}/genre/movieGenre/delete?id=${movieGenre.id}" onclick="return confirmx('Are you sure to delete the movie genre？', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>