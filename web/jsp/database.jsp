<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>${requestScope.database}</title>
</head>
<body>
<c:set scope="session" value="${requestScope.tables}" var="tables"/>
<c:forEach items="${tables}" var="table">

    <form action="/Controller" method="get">
        <input type="hidden" name="command" value="TABLEVIEW">
        <input type="hidden" name="database" value="${requestScope.database}">
        <input type="hidden" name="table" value="${table}">
        <button type="button" onclick="addToListSub(this)">Substract</button>
        <button type="button" onclick="addToListInter(this)">Intersect</button>
        <button type="submit"><c:out value="View"/></button>
        <c:out value="${table}"/>
    </form>
    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="DELETEDATABASE">
        <input type="hidden" name="database" value="${requestScope.database}">
        <input type="hidden" name="table" value="${table}">
        <button type="submit">Delete</button>
    </form>

</c:forEach>>
<form action="/Controller" method="get">
    <input type="hidden" name="command" value="CREATETABLEVIEW">
    <input type="hidden" name="database" value="${requestScope.database}">
    <button type="intermit"> Create table</button>
</form>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="SUBSTRACT">
    <input type="hidden" name="database" value="${requestScope.database}">
    Substraction
    <input type="text" name="newtable" placeholder="Name">
    <ul id="sub"></ul>
</form>
<form action="/Controller" method="post">
    <input type="hidden" name="command" value="INTERSECT">
    <input type="hidden" name="database" value="${requestScope.database}">
    Intersection
    <input type="text" name="newtable" placeholder="Name">
    <ul id="inter"></ul>
</form>
<script>
    function addToListSub(button) {
        var interlist = document.getElementById("sub");
        if (interlist.childElementCount < 2) {
            var li = document.createElement("li");
            var input=document.createElement("input");
            input.setAttribute("type","hidden");
            input.setAttribute("name","table"+interlist.childElementCount);
            input.setAttribute("value",button.parentNode.lastChild.data.replace(/^\s+|\s+$/g, ''));
            li.innerHTML = button.parentNode.lastChild.data.replace(/^\s+|\s+$/g, '');
            var removeBtn = document.createElement("button");
            removeBtn.setAttribute("class", "remove");
            removeBtn.setAttribute("value", "-");
            removeBtn.innerHTML = "-";
            removeBtn.addEventListener("click", function () {
                    this.parentNode.parentNode.removeChild(this.parentNode);
                    if (interlist.childElementCount == 2) {
                        var interBut = document.getElementById("SubstractBtn");
                        interBut.parentNode.removeChild(interBut);
                    }
                }
            )
            li.appendChild(input);
            li.appendChild(removeBtn)
            interlist.appendChild(li);
            if (interlist.childElementCount == 2) {
                var interBut = document.createElement("button");
                interBut.innerHTML = "Substract";
                interBut.id = "SubstractBtn";
                interlist.appendChild(interBut);
            }
        }
    }
    ;
    function addToListInter(button) {
        var interlist = document.getElementById("inter");
        if (interlist.childElementCount < 2) {
            var li = document.createElement("li");
            var input=document.createElement("input");
            input.setAttribute("type","hidden");
            input.setAttribute("name","table"+interlist.childElementCount);
            input.setAttribute("value",button.parentNode.lastChild.data.replace(/^\s+|\s+$/g, ''));
            li.innerHTML = button.parentNode.lastChild.data.replace(/^\s+|\s+$/g, '');
            var removeBtn = document.createElement("button");
            removeBtn.setAttribute("class", "remove");
            removeBtn.setAttribute("value", "-");
            removeBtn.innerHTML = "-";
            removeBtn.addEventListener("click", function () {
                    this.parentNode.parentNode.removeChild(this.parentNode);
                    if (interlist.childElementCount == 2) {
                        var interBut = document.getElementById("IntersectBtn");
                        interBut.parentNode.removeChild(interBut);
                    }
                }
            )
            li.appendChild(input);
            li.appendChild(removeBtn);
            interlist.appendChild(li);
            if (interlist.childElementCount == 2) {
                var interBut = document.createElement("button");
                interBut.innerHTML = "Intersect";
                interBut.id = "IntersectBtn";
                interlist.appendChild(interBut);
            }
        }

    };
</script>
</body>
</html>