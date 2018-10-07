<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Вы вошли как <%=request.getSession().getAttribute("login")%>,
<a href="/login?action=logout">выйти</a>
<H1>
    Dashboard
</H1>
<a href="/students">Students</a><BR><BR>
<a href="/addStudent">Add student</a><BR><BR>
</body>
</html>
