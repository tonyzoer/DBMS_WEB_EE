<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>All databases</title>
</head>
<body>
<c:set scope="session" value="${requestScope.tables}" var="tables"/>
<c:forEach items="${tables}" var="table">

    <form action="/Controller" method="get">
        <input type="hidden" name="command" value="DATABASE">
        <button type=""></button>
    </form>
    <c:out value="${table}"/>

</c:forEach>>
</body>
</html>