<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MEMO LIST</title>
    <%@ include file="../include/header.jsp"%>
    <script>
        function memo_view(idx){
            location.href="${path}/memo/view/"+idx;
        }
    </script>
</head>
<body>
    <%@include file="../include/menu.jsp"%>
    <h2>한줄메모장</h2>
    <form method="post" action="${path}controller/memo/insert.do">
        이름: <input name="writer" size="10">
        메모: <input name="memo" size="40">
        <input type="submit" value="확인">
    </form>

    <table border="1" style="width:500px;">
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>메모</th>
            <th>날짜</th>
        </tr>
        <c:forEach var="row" items="${list}">
            <tr>
                <td>${row.idx}</td>
                <td>${row.writer}</td>
                <td><a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
                <td><fmt:formatDate value="${row.post_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
