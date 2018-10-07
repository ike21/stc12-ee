<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список студентов:</title>
</head>
<body>
<table>
    <caption>Students</caption>
    <tr>
        <th>Family name</th>
        <th>Name</th>
        <th>City</th>
    </tr>
    <c:forEach var="num" items="${list}">
        <tr>
            <td>${num.getFamilyName()}</td>
            <td>${num.getName()}</td>
            <td>${num.getCity().getName()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
