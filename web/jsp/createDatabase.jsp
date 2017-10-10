<%--
  Created by IntelliJ IDEA.
  User: mafio
  Date: 09.10.2017
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="CREATEDATABASE"/>
    <input type="text" name="name" value=""/>
    <button type="submit" >Create Database</button>
</form>
</body>
</html>
