<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
    <%@ include file="../include/header.jsp"%>
</head>
<body>
    <%@ include file="../include/menu.jsp"%>
    <h2>Sending email</h2>
    <form method="post" action="${path}/email/send.do">
        Sender name: <input name="senderName"><br>
        Sender email: <input name="senderMail"><br>
        Receiver mail: <input name="receiveMail"><br>
        Subject: <input name="subject"><br>
        Content: <textarea rows="5" cols="80" name="message"></textarea><br>
        <input type="submit" value="Send">
    </form>

    <%-- result output area --%>
    <span style="color:red;">${message}</span>
</body>
</html>
