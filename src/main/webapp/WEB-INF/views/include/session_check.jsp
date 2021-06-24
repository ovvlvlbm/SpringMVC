<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.admin_userid == null}">
    <script>
        alert("관리자 영역입니다.");
        location.href="${path}/admin/login.do";
    </script>
</c:if>