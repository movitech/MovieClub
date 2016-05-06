<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Contact管理</title>
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
		<li class="active"><a href="${ctx}/contact/contact/">Contact列表</a></li>
		<shiro:hasPermission name="contact:contact:edit"><li><a href="${ctx}/contact/contact/form">Contact添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="contact" action="${ctx}/contact/contact/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>obj_type</th>
				<th>name</th>
				<th>company</th>
				<th>email</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="contact:contact:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="contact">
			<tr>
				<td><a href="${ctx}/contact/contact/form?id=${contact.id}">
					${fns:getDictLabel(contact.objType, 'contact_type', '')}
				</a></td>
				<td>
					${contact.name}
				</td>
				<td>
					${contact.company}
				</td>
				<td>
					${contact.email}
				</td>
				<td>
					<fmt:formatDate value="${contact.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${contact.remarks}
				</td>
				<shiro:hasPermission name="contact:contact:edit"><td>
    				<a href="${ctx}/contact/contact/form?id=${contact.id}">修改</a>
					<a href="${ctx}/contact/contact/delete?id=${contact.id}" onclick="return confirmx('确认要删除该Contact吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>