<%--
  Created by IntelliJ IDEA.
  User: mafio
  Date: 09.10.2017
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form action="Controller" method="get">
    <input type="hidden" name="command" value="CREATEDATABASEVIEW">
    <button type="submit">Create database</button>
  </form>
  <form action="Controller" method="get">
    <input type="hidden" name="command" value="ALLDATABASES">
    <button type="submit">All Databases</button>
  </form>
  </body>
</html>
