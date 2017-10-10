<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>All databases</title>
</head>
<body>
<c:set scope="session" value="${requestScope.databases}" var="databases"/>
<c:forEach items="${databases}" var="database">
    <c:out value="${database}"/>
</c:forEach>>
</body>
</html>