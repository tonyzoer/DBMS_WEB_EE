<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>All databases</title>
</head>
<body>
<c:set scope="session" value="${requestScope.databases}" var="databases"/>
<c:forEach items="${databases}" var="database">

    <form action="/Controller" method="get">
        <input type="hidden" name="command" value="DATABASE">
        <input type="hidden" name="database" value="<c:out value="${database}"/>">
        <button type="submit">${database}"</button>
    </form>

    <br>
</c:forEach>>
</body>
</html>