<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>Recommendation Management</title>
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
    <li class="active"><a href="${ctx}/recommendation/recommendation/">Recommendation List</a></li>
    <shiro:hasPermission name="recommendation:recommendation:edit"><li><a href="${ctx}/recommendation/recommendation/form">Add Recommendation</a></li></shiro:hasPermission>
</ul>
<%--<form:form id="searchForm" modelAttribute="recommendation" action="${ctx}/recommendation/recommendation/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>--%>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>Movie Title</th>
        <th>Sort Order</th>
        <th>Update Date</th>
        <th>Remarks</th>
        <shiro:hasPermission name="recommendation:recommendation:edit"><th>Operating</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="recommendation">
    <tr>
        <td><a href="${ctx}/recommendation/recommendation/form?id=${recommendation.id}">
            ${recommendation.movie.title}
        </a></td>
        <td>
            ${recommendation.sortOrder}
        </td>
        <td>
            <fmt:formatDate value="${recommendation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>
        <td>
            ${recommendation.remarks}
        </td>
        <shiro:hasPermission name="recommendation:recommendation:edit"><td>
        <a href="${ctx}/recommendation/recommendation/form?id=${recommendation.id}">Modify</a>
        <a href="${ctx}/recommendation/recommendation/delete?id=${recommendation.id}" onclick="return confirmx('Confirm that you want to delete the recommendation do?', this.href)">Delete</a>
        </td></shiro:hasPermission>
    </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>