<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../include/header.jsp"%>

<%--    <script type="text/javascript" src="https://www.google.com/jsapi"></script>--%>
    <script src="https://www.gstatic.com/charts/loader.js"></script>
    <script>
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

    function drawChart(){
            var jsonData=$.ajax({
                url:"${path}/json/sampleData.json",
                dataType:"json",
                async:false
            }).responseText;
            console.log(jsonData);
            //데이터테이블
            var data=new google.visualization.DataTable(jsonData);
            var chart=new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data,{
               title: "차트 예제",
               width:600,
               height:440
            });
        }
    </script>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <div id="chart_div"></div>
    <button id="btn" type="button" onclick="drawChart()">refresh</button>
</body>
</html>
