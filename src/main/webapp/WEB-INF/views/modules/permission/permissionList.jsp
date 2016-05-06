<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>permission管理</title>
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
		<li class="active"><a href="${ctx}/permission/permission/">Permission List</a></li>
		<shiro:hasPermission name="permission:permission:edit"><li><a href="${ctx}/permission/permission/form">Add Permission</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="permission" action="${ctx}/permission/permission/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width:120px !important;">Permission Name：</label>
				<form:input path="permissionName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Permission Name</th>
				<th>Update Date</th>
				<th>Remarks</th>
				<shiro:hasPermission name="permission:permission:edit"><th>Operating</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="permission">
			<tr>
				<td><a href="${ctx}/permission/permission/form?id=${permission.id}">
					${permission.permissionName}
				</a></td>
				<td>
					<fmt:formatDate value="${permission.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${permission.remarks}
				</td>
				<shiro:hasPermission name="permission:permission:edit"><td>
    				<a href="${ctx}/permission/permission/form?id=${permission.id}">Modify</a>
					<a href="${ctx}/permission/permission/delete?id=${permission.id}" onclick="return confirmx('Are you sure to delete the permission?', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>