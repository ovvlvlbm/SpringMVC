<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <script src="${path}/WEB-INF/views/include/js/common.js"></script>
    <script>

        $(function(){
            $("#btnList").click(function(){
                location.href="${path}/board/list.do";
            });

            listReply("1");
            $("#btnReply").click(function(){
                reply();
            });
            $(".fileDrop").on("dragenter dragover", function(e){
                e.preventDefault();
            });
            $(".fileDrop").on("drop", function(e){
                e.preventDefault();
                var files=e.originalEvent.dataTransfer.files;
                var file=files[0];
                var formData=new FormData();
                formData.append("file", file);
                $.ajax({
                    url: "${path}/upload/uploadAjax",
                    data: formData,
                    dataType: "text",
                    processData: false,
                    contentType: false,
                    type:"post",
                    success: function(data){
                        var fileInfo=getFileInfo(data);
                        var html="<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
                        html+="<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";
                        $("#uploadedList").append(html);
                    }
                });
            });
            listAttach();

            $("#uploadedList").on("click",".flie_del", function(e){
                var that=$(this);
                $.ajax=({
                    type:"post",
                    url:"${path}/upload/deleteFile",
                    data: {fileName:$(this).attr("data-src")},
                    dataType: "text",
                    success: function(result){
                        if(result==="deleted"){
                            that.parent("div").remove();
                        }
                    }
                });
            });
            $("#btnUpdate").click(function(){
                var str="";
                $("#uploadedList .file").each(function(i){
                    str+="<input type='hidden' name='files["+i+"]' value='"+$(this).val()+"'>";
                });
                $("#form1").append(str);
                document.form1.action="${path}/board/update.do";
                document.form1.submit();
            });
            $("#btnDelete").click(function(){
                if(confirm("삭제하시겠습니까?")){
                    document.form1.action="${path}/board/delete.do";
                    document.form1.submit();
                }
            });
        });

        function listAttach(){
            $.ajax({
                type:"post",
                url:"${path}/board/getAttach/${dto.bno}",
                success: function(list){
                    $(list).each(function(){
                        const fileInfo = getFileInfo(this)
                        let html = "<div><a href='" + fileInfo.getLink + "'>" + fileInfo.fileName + "</a>&nbsp;&nbsp;"
                        html+="<a href='#' class='file_del' data-src='"+this+"'>[삭제]</a></div>";
                        $("#uploadedList").append(html);
                    });
                }
            });
        }

        function reply(){
            const replytext = $("replytext").val();
            const bno=${dto.bno};
            const secret_reply=$("#secret_reply").is(":checked") === true ? "y" : "n";
            const param = {"replytext": replytext, "bno": bno, "secret_reply":secret_reply};
            $.ajax({
                type:"post",
                url:"${path}/reply/insert.do",
                data:param,
                success:function(){
                    alert("댓글이 등록되었습니다.");
                    listReply("1");
                }
            });
        }

        function listReply2(){
            $.ajax({
                type:"get",
                contentType: "application/json",
                url: "${path}/reply/list_json.do?bno=${dto.bno}",
                success:function(result){
                    console.log(result);
                    let output="<table>";
                    for(const i in result){
                        if(result.hasOwnProperty(i)) {
                            let repl = result[i].replytext;
                            repl = repl.replace(/\n/gi, "<br>");
                            repl = repl.replace(/  /gi, "&nbsp;&nbsp;");
                            output += "<tr>";
                            output += "<td>" + result[i].name;
                            output += "(" + changeDate(result[i].regdate) + ")";
                            output += "<br>" + repl;
                            output += "<input type='button' value='Modify' onclick='showModify(\"" + result[i].rno + "\")'>";
                            output += "</td></tr>";
                        }
                    }

                    output+="</table>";
                    $("#listReply").html(output);
                }
            });
        }

        function changeDate(date){
            date=new Date(parseInt(date));
            const year=date.getFullYear();
            const month=date.getMonth();
            const day=date.getDate();
            const hour=date.getHours();
            const minute=date.getMinutes();
            const second=date.getSeconds();
            return year + "-" + month + "-" + day + "-" + hour + ":" + minute + ":" + second; //originally strDate but it is redundant.
        }

        function listReply(num){
            $.ajax({
                type:"get",
                url:"${path}/reply/list.do?bno=${dto.bno}&curPage="+num,
                success:function(result){
                    $("#listReply").html(result);
                }
            });
        }

        function showModify(rno){
            $.ajax({
                type:"get",
                url:"${path}/reply/detail/"+rno,
                success: function(result){
                    $("#modifyReply").html(result);
                    $("#modifyReply").css("visibility", "visible");
                }
            });
        }
    </script>
    <style>
        .fileDrop{
            width: 600px;
            height: 100px;
            border: 1px dotted gray;
            background-color: gray;
        }
    </style>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>게시물 보기</h2>
    <form id="form1" name="form1" method="post">
        <div>작성일자: <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss"/> </div>
        <div>조회수: ${dto.viewcnt}</div>
        <div>이름: ${dto.name}</div>
        <div><label>제목: <input name="title" value="${dto.title}"></label></div>
        <div style="width: 80%;">
            <label for="content">내용: </label><textarea rows="3" cols="80" name="content" id="content">${dto.content}</textarea>
        </div>

        <div id="uploadedList"></div>
        <div class="fileDrop"></div>
        <div>
            <input type="hidden" name="bno" value="${dto.bno}">
            <c:if test="${sessionScope.userid==dto.writer}">
                <button type="button" id="btnUpdate">수정</button>
                <button type="button" id="btnDelete">삭제</button>
            </c:if>
            <button type="button" id="btnList">목록</button>
        </div>
    </form>
    <div style="width: 700px; text-align: center;">
        <c:if test="${sessionScope.userid!=null}">
            <label for="replytext"></label><textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성하세요"></textarea><br>
            <input type="checkbox" id="secret_reply"><label for="secret_reply">비밀댓글</label>
            <button type="button" id="btnReply">댓글쓰기</button>
        </c:if>
    </div>
    <div id="listReply"></div>
</body>
</html>
