<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <script>
        $(document).ready(function(){
            //when delete button is clicked.
            $("#btnReplyDelete").click(function(){
                $.ajax({
                   type:"delete",
                   url:"${path}/reply/delete/${dto.rno}",
                   success: function(result){
                       if(result==="success") {
                           alert("It has been successfully deleted.");
                           $("#modifyReply").css("visibility", "hidden");
                           listReply2("1");
                       }
                   }
                });
            });

            //when edit button is clicked.
            $("#btnReplyUpdate").click(function(){
                let replytext = $("#detail_replytext").val()
                $.ajax({
                    type:"put",
                    url:"${path}/reply/update/${dto.rno}",
                    headers: {
                        "Content-Type":"application/json"
                    },
                    date: JSON.stringify({
                        replytext:replytext
                    }),
                    dataType:"text",
                    success: function(result){
                        if(result==="success"){
                            $("#modifyReply").css("visibility", "hidden");
                            listReply2("1");
                        }
                    }
                });
            });
            $("#btnReplyClose").click(function(){
                $("#modifyReply").css("visibility", "hidden");
            });
        });
    </script>
</head>
<body>
    ${dto.rno}<br>
    <label for="detail_replytext"></label><textarea id="detail_replytext" rows="3" cols="40">${dto.replytext}</textarea>
    <div style="text-align: center;">
        <c:if test="${sessionScope.userid == dto.replyer}">
            <button id="btnReplyUpdate" type="button">Edit</button>
            <button id="btnReplyDelete" type="button">Delete</button>
        </c:if>
        <button id="btnReplyClose" type="button">Close</button>
    </div>
</body>
</html>
