<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <script>
        $(function(){
            $("#btnAdd").click(function(){
                location.href="${path}/shop/product/write.do";
            });
        });
    </script>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>상품목록</h2>
    <c:if test="${sessionScope.admin_userid != null}">
        <button type="button" id="btnAdd">상품등록</button>
    </c:if>
    <table border="1" width="500px">
        <tr>
            <th>상품ID</th>
            <th>상품이미지</th>
            <th>상품명</th>
            <th>가격</th>
        </tr>
        <c:forEach var="row" items="${list}">
            <tr align="center">
                <td>${row.product_id}</td>
                <td><img src="${path}/images/${row.picture_url}" width="100px" height="100px"></td>
                <td>
                    <a href="${path}/shop/product/detail/${row.product_id}">${row.product_name}</a>
                    <c:if test="${sessionScope.admin_userid != null}"><br>
                        <a href="${path}/shop/product/edit/${row.product_id}">[편집]</a>
                    </c:if>
                </td>
                <td><fmt:formatNumber value="${row.price}" pattern="#,###"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
