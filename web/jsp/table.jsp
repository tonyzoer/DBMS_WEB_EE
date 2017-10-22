<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mafio
  Date: 18.10.2017
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.tableStr}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<c:set scope="session" value="${requestScope.rows}" var="rows"/>
<p id="tableRow">
    ${requestScope.table}
</p>
<c:set scope="session" value="${requestScope.scheme}" var="scheme"/>
<form action="/Table" method="post" id="postFormRow">

    <input type="hidden" name="database" value="${requestScope.databaseStr}">
    <input type="hidden" name="table" value="${requestScope.tableStr}">
    <c:forEach items="${scheme}" var="col">
        <input type="text" name="${col.key}" placeholder="${col.key}:${col.value}">
    </c:forEach>
    <button id="addButton">ADD</button>
    <div id="added"></div>
</form>
<script>
    $(document).on("submit", "#postFormRow", function (event) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function (response) {
            var table = document.createElement("table");
            document.getElementById("added").appendChild(table);
            $.each(response, function (index, row) {
                $("<tr>").appendTo(table)
                    .append($("<td>").text(index+":"+row));
            })
        });

        event.preventDefault(); // Important! Prevents submitting the form.
    });
</script>
</body>
</html>
