<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consumer Management</title>
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
		<li class="active"><a href="${ctx}/consumer/consumer/">Consumer List</a></li>
		<shiro:hasPermission name="consumer:consumer:edit"><li><a href="${ctx}/consumer/consumer/form">Add Consumer</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="consumer" action="${ctx}/consumer/consumer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Email：</label>
				<form:input path="email" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>First Name：</label>
				<form:input path="firstName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>
                <th>Nick Name</th>
				<th>Active</th>
                <th>Role Name</th>
				<th>Update Date</th>
				<shiro:hasPermission name="consumer:consumer:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="consumer">
			<tr>
				<td><a href="${ctx}/consumer/consumer/form?id=${consumer.id}">
					${consumer.email}
				</a></td>
				<td>
					${consumer.firstName}
				</td>
				<td>
					${consumer.lastName}
				</td>
                <td>
                    ${consumer.nickName}
                </td>
				<td>
					${consumer.active}
				</td>
                <td>
                    ${consumer.roleName}
                </td>
				<td>
					<fmt:formatDate value="${consumer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="consumer:consumer:edit"><td>
    				<a href="${ctx}/consumer/consumer/form?id=${consumer.id}">Modify</a>
					<a href="${ctx}/consumer/consumer/delete?id=${consumer.id}" onclick="return confirmx('Are you sure to delete the consumer?', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>