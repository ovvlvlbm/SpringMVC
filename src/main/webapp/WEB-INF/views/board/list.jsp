<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <script>
        $(document).ready(function(){
            $("#btnLogin").click(function(){
                location.href="${path}/board/write.do";
            });
        });
        function list(page){
            location.href="${path}/board/list.do?curPage="+page+"&search_option=${map.search_option}"+"&keyword=${map.keyword}";
        }
    </script>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>게시판</h2>
    <form name="form1" method="post" action="${path}/board/list.do">
        <select name="search_option">
            <option value="all"
                    <c:out value="${map.search_option=='name'?'selected':"}"/>  >이름+내용+제목</option>
            <option value="name"
                    <c:out value="${map.search_option=='content'?'selecte':"}"/> >이름</option>
            <option value="content"
                    <c:out value="${map.search_option=='content'?'selecte':"}"/> >내용</option>
            <option value="title"
                    <c:out value="${map.search_option=='title'?'selecte':"}"/> >제목</option>
        </select>
        <input name="keyword" value="${map.keyword}">
        <input type="submit" value="조회">
        <c:if test="${sessionScope.userid!=null}">
            <button type="button" id="btnWrite">글쓰기</button>
        </c:if>
    </form>
    ${map.count}개의 게시물이 있습니다.
    <table border="1" width="600px">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>날짜</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="row" items="${map.list}">
            <c:choose>
                <c:when test="${row.show=='Y'}">
                        <tr>
                            <td>${row.bno}</td>
                            <td>
                                <a href="${path}/board/view.do?bno=${row.bno}
                                    &curPage=#{map.pager.curPage}
                                    &search_option=${map.search_option}
                                    &keyword=${map.keyword}">${row.title}</a>
                                <c:if test="${row.cnt > 0}">
                                    <span style="color:red;">(${row.cnt})</span>
                                </c:if>
                            </td>
                            <td>${row.name}</td>
                            <td>
                                <fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>${row.viewcnt}</td>
                        </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5" align="center">
                            <c:if test="${row.cnt>0}">
                                <a href="${path}/board/view.do?bno=${row.bno}
                                   &curPage=${map.pager.curPage}
                                   &search_option=${map.search_option}
                                   &keyword=${map.keyword}">삭제된 게시물입니다.</a>
                                <span style="color:red;">(${row.cnt})</span>
                            </c:if>
                            <c:if test="${row.cnt==0}"> 삭제된 게시물입니다. </c:if>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <tr>
            <td colspan="5" align="center">
                <c:if test="${map.pager.curBlock > 1}">
                    <a href="javascript:list('1')">[처음]</a>
                </c:if>
                <c:if test="${map.pager.curBlock > 1}">
                    <a href="javascript:list('${map.pager.prefPage}')">[이전]</a>
                </c:if>
                <c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
                    <c:choose>
                        <c:when test="${num == map.pager.curPage}">
                            <span style="color:red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${map.pager.curBlock <= map.pager.totBlock}">
                    <a href="javascript:list('${map.pager.nextPage}')">[다음]</a>
                </c:if>
                <c:if test="${map.pager.curPage <= map.pager.totPage}">
                    <a href="javascript:list('${map.pager.totPage}')">[끝]</a>
                </c:if>
            </td>
        </tr>
    </table>
</body>
</html>
