<%--
  Created by IntelliJ IDEA.
  User: mafio
  Date: 17.10.2017
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Table</title>
</head>
<body>
<form id="form" action="/Controller" method="post">
    <input type="hidden" name="command" value="CREATETABLE">
    <input id="count" type="hidden" name="count" value="0">
    <input id="curdb" type="hidden" name="curdb" value="${requestScope.curdb}">
    <input type="text" name="tableName">
    <button type="submit"> Create</button>
    <br>
</form>
<button onclick="addField()">add</button>
</body>
<script>
    var i = 0;
    function addField() {
        var form = document.getElementById("form");
        var inputName = document.createElement("input");
        inputName.setAttribute("name","columnName" + i);
        form.appendChild(inputName);
        var selectType=document.createElement("select");
        var optionString=document.createElement("option");optionString.innerHTML="String";selectType.appendChild(optionString);
        var optionInteger=document.createElement("option");optionInteger.innerHTML="Integer";selectType.appendChild(optionInteger);
        var optionChar=document.createElement("option");optionChar.innerHTML="Char";selectType.appendChild(optionChar);
        var optionStringN=document.createElement("option");optionStringN.innerHTML="StringN";selectType.appendChild(optionStringN);
        var optionTime=document.createElement("option");optionTime.innerHTML="Time";selectType.appendChild(optionTime);
        var optionTimeInv=document.createElement("option");optionTimeInv.innerHTML="TimeInterval";selectType.appendChild(optionTimeInv);
        var optionCharInv=document.createElement("option");optionCharInv.innerHTML="CharInterval";selectType.appendChild(optionCharInv);
        var optionDouble=document.createElement("option");optionDouble.innerHTML="Double";selectType.appendChild(optionString);
        selectType.setAttribute("name","type"+i);
        form.appendChild(selectType);
        form .appendChild(document.createElement("br"));
        i++;
        var count=document.getElementById("count");
        count.setAttribute("value",i);
    }
</script>
</html>
