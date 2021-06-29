<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${path}/">Home</a> |
<a href="${path}/memo/list.do">Memo</a> |
<a href="${path}/upload/uploadForm">Upload</a> |
<a href="${path}/shop/product/list.do">Product list</a> |
<a href="${path}/shop/cart/list.do">Cart</a> |
<c:if test="${sessionScope.admin_userid != null}">
    <a href="${path}/shop/product/write.do">Add product</a> |
</c:if>

<a href="${path}/pdf/list.do">PDF</a> |
<a href="${path}/chart/chart1.do">GoogleChart(json)</a> |
<a href="${path}/chart/chart2.do">GoogleChart(db)</a> |
<a href="${path}/jchart/chart1.do">JFreeChart(png)</a> |
<a href="${path}/jchart/chart2.do">JFreeChart(pdf)</a> |
<a href="${path}/upload/uploadAjax">File upload</a> |
<a href="${path}/email/write.do">Send Email</a> |
<a href="${path}/board/list.do">Board</a> |

<div style="text-align:right;">
    <c:choose>
        <c:when test="${sessionScope.userid == null}">
            <a href="${path}/member/login.do">Login</a>
            <a href="${path}/admin/login.do">Admin Login</a>
        </c:when>
        <c:otherwise>
            ${sessionScope.name} logined.
            <a href="${path}/member/login.do">Logout</a>
            <a href="${path}/admin/logout.do">Admin Logout</a>
        </c:otherwise>
    </c:choose>
</div>
<hr>