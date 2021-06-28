<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <script type="text/javascript" src="${path}/ckeditor/ckediror.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".fileDrop").on("dragenter dragover", function(e){
                e.preventDefault();
            });
            $(".fileDrop").on("drop", function(e){
                e.preventDefault();
                var files=e.originalEvent.dataTransfer.files;
                var file=files[0];
                var formData=new FormData();
                formData.append("file",file);
                $.ajax({
                    url:"${path}/upload/uploadAjax",
                    data:formData,
                    dataType: "text",
                    processData: false,
                    contentType: false,
                    type:"post",
                    success: function(data){
                        console.log(data);
                        var fileInfo=getFileInfo(data);
                        var html="<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
                        html+="<input type='hidden' name= 'files' value='"+fileInfo.fullName+"'>";
                        $("#uploadedList").append(html);
                    }
                });
            });

            $("#btnSave").click(function(){
                var title=document.form1.title.value;
                if(title==""){
                    alert("제목을 입력하세요.");
                    document.form1.title.focus();
                    return;
                }
                document.form1.submit();
            });
        });
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
    <h2>게시물 작성</h2>
    <form id="form1" name="form1" method="post" action="${path}/board/insert.do">
        <div>
            제목
            <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
        </div>
        <div style="width: 800px;">
            내용 <textarea id="content" name="content" rows="5" cols="80" placeholder="내용을 입력하세요."></textarea>
            <script>
                CKEDITOR.replace("content",{
                    filebrowserUploadUrl:"${path}/imageUpload.do"
                });
            </script>
        </div>
        <div>
            첨부파일 등록
            <div class="fileDrop"></div>
            <div id="uploadedList"></div>
        </div>
        <div style="width: 700px; text-align: center;">
            <button type="button" id="btnSave">확인</button>
        </div>
    </form>
</body>
</html>
