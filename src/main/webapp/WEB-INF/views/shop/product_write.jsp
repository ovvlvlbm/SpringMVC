<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <%@ include file="../include/session_check.jsp"%>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">

    <link href="${path}/WEB-INF/views/summernote/summernote.min.css" rel="stylesheet">
    <script src="${path}/WEB-INF/views/summernote/summernote.min.js"></script>
    <script>
        $(function(){
            $("#description").summernote({
               height: 300, width: 800
            });
        });
    </script>
    <script>
        function product_write(){
            var product_name = document.form1.product_name.value;
            var price = document.form1.price.value;
            var description = document.form1.description.value;
            if(product_name===""){
                alert("상품명을 입력하세요.");
                document.form1.product_name.focus();
                return;
            }
            if(price===""){
                alert("가격을 입력하세요.");
                document.form1.price.focus();
                return;
            }
            if(description===""){
                alert("상품설명을 입력하세요.");
                document.form1.description.focus();
                return;
            }
            document.form1.action="${path}/shop/product/insert.do";
            document.form1.submit();
        }
    </script>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>상품등록</h2>
    <form id="form1" name="form1" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>상품명</td>
                <td><input name="product_name"></td>
            </tr>
            <tr>
                <td>가격</td>
                <td><input name="price"></td>
            </tr>
            <tr>
                <td>상품설명</td>
                <td><textarea rows="5" cols="60" name="description" id="description"></textarea>
                    <script>
                        CKEDITOR.replace("description");
                    </script>
                </td>
            </tr>
            <tr>
                <td>상품이미지</td>
                <td><input type="file" name="file1" multiple></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="등록" onclick="product_write()">
                    <input type="button" value="목록" onclick="location.href='${path}/shop/product/list.do';">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
