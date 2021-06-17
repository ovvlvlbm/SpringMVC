<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${path}/">Home</a>  <%--작동--%>
<a href="${path}/memo/list.do">메모장</a>
<a href="${path}/upload/uploadForm">업로드</a>  <%--작동--%>
<a href="${path}/shop/product/list.do">상품목록</a>
<a href="${path}/shop/cart/list.do">장바구니</a>

<c:if test="${sessionScope.admin_userid != null}">
    <a href="${path}/shop/product/write.do">상품등록</a>
</c:if>

<div style="text-align:right;">
    <c:choose>
        <c:when test="${sessionScope.userid == null}">
            <a href="${path}/member/login.do">로그인</a>
            <a href="${path}/admin/login.do">관리자로그인</a>
        </c:when>
        <c:otherwise>
            ${sessionScope.name}님이 로그인중입니다.
            <a href="${path}/member/login.do">로그아웃</a>
            <a href="${path}/admin/logout.do">로그아웃(관리자)</a>
        </c:otherwise>
    </c:choose>
</div>
