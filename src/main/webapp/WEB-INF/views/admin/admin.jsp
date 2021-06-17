<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <c:if test="${message=='success'}">
        <h2>${sessionScope.admin_name}(${sessionScope.admin_userid})님 환영합니다.</h2>
    </c:if>
</body>
</html>
