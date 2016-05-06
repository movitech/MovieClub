<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${result.title}</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div class="nav nav-tabs">
		<span>${result.message}</span>
    </div>
    <div style="text-align: center;padding-top: 30px;">
        <span style="font-weight: bold;font-size: 16px;">${result.desc}</span>
    </div>
</body>
</html>