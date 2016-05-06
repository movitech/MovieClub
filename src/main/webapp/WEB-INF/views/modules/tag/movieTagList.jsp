<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Tag Management</title>
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
		<li class="active"><a href="${ctx}/tag/movieTag/">Tag List</a></li>
		<shiro:hasPermission name="tag:movieTag:edit"><li><a href="${ctx}/tag/movieTag/form">Add Tag</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="movieTag" action="${ctx}/tag/movieTag/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Tag Title：</label>
				<form:input path="tagTitle" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Tag Title</th>
				<th>Update Date</th>
				<th>Remarks</th>
				<shiro:hasPermission name="tag:movieTag:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="movieTag">
			<tr>
				<td><a href="${ctx}/tag/movieTag/form?id=${movieTag.id}">
					${movieTag.tagTitle}
				</a></td>
				<td>
					<fmt:formatDate value="${movieTag.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${movieTag.remarks}
				</td>
				<shiro:hasPermission name="tag:movieTag:edit"><td>
    				<a href="${ctx}/tag/movieTag/form?id=${movieTag.id}">Modify</a>
					<a href="${ctx}/tag/movieTag/delete?id=${movieTag.id}" onclick="return confirmx('Are you sure to delete the tag？', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>