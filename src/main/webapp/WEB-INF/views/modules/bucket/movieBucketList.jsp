<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Bucket管理</title>
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
		<li class="active"><a href="${ctx}/bucket/movieBucket/">Bucket列表</a></li>
		<shiro:hasPermission name="bucket:movieBucket:edit"><li><a href="${ctx}/bucket/movieBucket/form">Bucket添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="movieBucket" action="${ctx}/bucket/movieBucket/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="bucket:movieBucket:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="movieBucket">
			<tr>
				<td><a href="${ctx}/bucket/movieBucket/form?id=${movieBucket.id}">
					<fmt:formatDate value="${movieBucket.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${movieBucket.remarks}
				</td>
				<shiro:hasPermission name="bucket:movieBucket:edit"><td>
    				<a href="${ctx}/bucket/movieBucket/form?id=${movieBucket.id}">修改</a>
					<a href="${ctx}/bucket/movieBucket/delete?id=${movieBucket.id}" onclick="return confirmx('确认要删除该Bucket吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>