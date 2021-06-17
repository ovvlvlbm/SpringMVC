<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
    <%@ include file="include/header.jsp"%>
</head>
<body>
    <%@include file="include/menu.jsp"%>
    <h2>Welcome!</h2>

    <c:if test="${sessionScope.userid != null}">
        <h2>${sessionScope.name}(${sessionScope.userid})님의 방문을 환영합니다.</h2>
    </c:if>
    <p>${test}</p>
    <p>The time on the server is ${serverTime}.</p>

    ${result}
</body>
</html>
