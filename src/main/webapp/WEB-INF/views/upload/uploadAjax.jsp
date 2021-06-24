<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>
    <style>
        .fileDrop {
            width: 100%;
            height: 200px;
            border: 1px dotted blue;
        }
        small {
            margin-left: 3px;
            font-weight: bold;
            color: gray;
        }
    </style>
    <script>
        $(function(){
            //업로드된 파일을 클릭하면
            $(".uploadedList").on("click","span",function(event){
                var that=$(this);
                $.ajax({
                    url: "${path}/upload/deleteFile",
                    type: "post",
                    data: {fileName:$(this).attr("data-src")},
                    dataType: "text",
                    success: function(result){
                        if(result === "deleted"){ //리턴된 메시지가 deleted면
                            that.parent("div").remove(); //화면에서 제거
                        }
                    }
                });
            });
            //드래그할 때
            $(".fileDrop").on("dragenter dragover", function(event){
                event.preventDefault(); //기본효과 제거
            });
            //드롭할 때
            $(".fileDrop").on("drop", function(event){
                event.preventDefault(); //기본효과제거
                var files = event.originalEvent.dataTransfer.files; //첨부파일배열
                var file=files[0];
                console.log(file);
                var formData = new FormData(); //코드로 폼 생성
                formData.append("file", file); //폼에 첨부파일 추가
                $.ajax({
                    type:"post",
                    url: "${path}/upload/uploadAjax",
                    data: formData,
                    dataType: "text",
                    processData: false,
                    contentType: false,
                    success: function(data){
                        var str="";
                        if(checkImageType(data)){ //이미지 여부 검사
                            str = "<div><a href='${path}/upload/displayFile?fileName="+getImageLink(data)+"'>";
                            str += "<img src='${path}/upload/displayFile?fileName="+data+"'></a>";
                        }else{
                            str="<div>";
                            str += "<a href='${path}/upload/displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a>";
                        }
                        str += "<span data-src="+data+">[삭제]</span></div>";
                        $(".uploadedList").append(str);
                    }
                });
            });
        });
        function getOriginalName(fileName){
            if (checkImageType(fineName)) { //이미지파일이면 리턴
                return;
            }
            //이미지 파일이 아닌 경우
            var idx = fileName.indexOf("_")+1; //uuid를 제외한 파일 이름
            return fileName.substr(idx);
        }
        function getImageLink(fileName){
            if(!checkImageType(fileName)){
                return;
            }
            //s_ 앞 뒤 부분을 제외한 파일이름
            var front=fileName.substr(1,12);
            var end=fileName.substr(14);
            return front+end;
        }
        function checkImageType(fileName){
            //이미지 확장자의 패턴, i 대소문자 무시
            var pattern = /jpg|gif|png|jpeg/i;
            return fileName.match(pattern);
        }
    </script>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>Ajax File upload</h2>
    <div class="fileDrop"></div>
    <div class="uploadedList"></div>
</body>
</html>
