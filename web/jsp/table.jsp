<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.tableStr}</title>
    <!-- Latest compiled and minified CSS -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body>
<c:set scope="session" value="${requestScope.rows}" var="rows"/>
<p id="tableRow">
    ${requestScope.table}
</p>
<c:set scope="session" value="${requestScope.scheme}" var="scheme"/>
<form action="/Table" method="post" id="postFormRow">
    <table class="table" id="table">
        <thead>
        <tr>
            <c:forEach items="${scheme}" var="col">
                <th name="${col.key}" id="${col.key}COL">${col.key}</th>
            </c:forEach>
        </tr>
        <tbody id="tbody"></tbody>
        </thead>
    </table>
    <input type="hidden" name="database" value="${requestScope.databaseStr}">
    <input type="hidden" name="table" value="${requestScope.tableStr}">
    <c:forEach items="${scheme}" var="col">
        <input class="input-sm" type="text" name="${col.key}" placeholder="${col.key}:${col.value}">
    </c:forEach>
    <button class="button">add</button>
</form>
<script>
    $(document).on("submit", "#postFormRow", function (event) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function (response) {
            var tbody = $("#tbody");
            var tr = $("<tr>");
            tr.attr("contenteditable", true);
            $.each(response, function (index, row) {
                if (index == "UUID") {
                    $(tr).attr("id",row);
                } else {
                    tr.append($("<td>").text(row).on("outfocus", function (event) {
                        alert(event);
                    })).append($("<td>").append($("<button>").attr("class", "delete").html("Delete").attr("contenteditable", false).attr("type", "button").on("click", function (e) {
                        var uuid = $(this).parent().parent().attr("id");
                        deleteRow(uuid);
                        $(this).parent().parent().remove();
                        e.stopPropagation()
                    })));
                }
            })
            tbody.append(tr);
        });

        event.preventDefault(); // Important! Prevents submitting the form.
    });
    $(window).on("load", function () {
        var tbody = $("#tbody");
        $.get("Table", {
            database: "${requestScope.databaseStr}",
            table: "${requestScope.tableStr}"
        }, function (responseJson) {
            $.each(responseJson, function (index, value) {
                $("<tr>").attr("id", index).attr("contenteditable", true).appendTo(tbody)
                <c:forEach items="${scheme}" var="col">
                    .append($("<td>").text(value.${col.key}))
                    </c:forEach>
                    .append($("<td>").append($("<button>").attr("class", "delete").html("Delete").attr("contenteditable", false).attr("type", "button").on("click", function (e) {
                        var uuid = $(this).parent().parent().attr("id");
                        deleteRow(uuid);
                        $(this).parent().parent().remove();
                        e.stopPropagation()
                    })));
            })
        })
    });
    var valueBefore;
    var valueAfter;
    $(document).on("click", "#tbody td", function (event) {
        if ($('#selectedCell').length) {
            $td = $('#selectedCell');
            valueAfter = $td.text();
            if (valueAfter != valueBefore) {
                var thVal = $td.closest('table').find('th').eq($td.index()).text();
                var uuid = $td.closest('tr').attr('id');
//                alert(thVal+"-"+ valueBefore+":"+valueAfter);
                updateCell(thVal, valueAfter, uuid);
            }
            $('#selectedCell').removeAttr('id');
            $(this).attr('id', 'selectedCell');
            valueBefore = $(this).text();
            event.stopPropagation();
        } else {
            $('#selectedCell').removeAttr('id');
            $(this).attr('id', 'selectedCell');
            valueBefore = $(this).text();
            event.stopPropagation();
        }
    });
//    $(document).on('click', function (event) {
//        valueAfter = $('#selectedCell').text();
////        alert(valueBefore+":"+valueAfter);
//        updateCell();
//        $('#selectedCell').removeAttr('id');
//    });

    $(".delete").on("click", function (e) {
        var uuid = this.getParent().id;
        if (deleteRow(uuid)) {
            this.getParent().getParent().removeChild(this.getParent());
        }
        e.stopPropagation()
    })

    function updateCell(col, val, uuid) {
        $.ajax({
            url: "Table?database=${requestScope.databaseStr}&table=${requestScope.tableStr}&col=" + col + "&value=" + val + "&UUID=" + uuid,
            type: 'PUT',
            contentType: "application/json",
            success: function (res) {
//TODO something (MAYBE)
            }
        });
    }
    function deleteRow(uuid) {
        $.ajax({
            url: "Table?database=${requestScope.databaseStr}&table=${requestScope.tableStr}" + "&UUID=" + uuid,
            type: 'DELETE',
            contentType: "application/json",
            success: function (res) {
                return true;
            }
        });
    }


    //-----------------------------------------------------------JQuery extensions

    $.put = function (url, data, callback, type) {

        if ($.isFunction(data)) {
            type = type || callback,
                callback = data,
                data = {}
        }

        return $.ajax({
            url: url,
            type: 'PUT',
            success: callback,
            data: JSON.stringify(data),
            contentType: "application/json"
        });
    }
    $.delete = function (url, data, callback, type) {

        if ($.isFunction(data)) {
            type = type || callback,
                callback = data,
                data = {}
        }

        return $.ajax({
            url: url,
            type: 'DELETE',
            success: callback,
            data: JSON.stringify(data),
            contentType: "application/json"
        });
    }
</script>
</body>
</html>
