<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consumer Role Management</title>
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
		<li class="active"><a href="${ctx}/consumer_role/consumerRole/">Consumer Role List</a></li>
		<shiro:hasPermission name="consumer_role:consumerRole:edit"><li><a href="${ctx}/consumer_role/consumerRole/form">Add  Consumer Role</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="consumerRole" action="${ctx}/consumer_role/consumerRole/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Role Name:</label>
				<form:input path="roleName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Role Name:</th>
				<th>Update Date</th>
				<th>Remarks</th>
				<shiro:hasPermission name="consumer_role:consumerRole:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="consumerRole">
			<tr>
				<td><a href="${ctx}/consumer_role/consumerRole/form?id=${consumerRole.id}">
					${consumerRole.roleName}
				</a></td>
				<td>
					<fmt:formatDate value="${consumerRole.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${consumerRole.remarks}
				</td>
				<shiro:hasPermission name="consumer_role:consumerRole:edit"><td>
					<a href="${ctx}/consumer_role/consumerRole/assign?id=${consumerRole.id}&roleName=${consumerRole.roleName}">分配</a>
    				<a href="${ctx}/consumer_role/consumerRole/form?id=${consumerRole.id}">Modify</a>
					<a href="${ctx}/consumer_role/consumerRole/delete?id=${consumerRole.id}" onclick="return confirmx('Are you sure to delete the consumer role?', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>