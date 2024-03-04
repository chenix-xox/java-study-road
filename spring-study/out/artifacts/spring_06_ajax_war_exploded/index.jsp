<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2024/2/15
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.7.1.js"></script>
    <script>
        <%--function send() {--%>
        <%--    $.ajax({--%>
        <%--        url: "${pageContext.request.contextPath}/ajax",--%>
        <%--        data: {--%>
        <%--            "name": $("#username").val()--%>
        <%--        },--%>
        <%--        success: function (data) {--%>
        <%--            alert(data);--%>
        <%--        }--%>
        <%--    })--%>
        <%--}--%>

        $(function () {
            $("#btn").click(function () {
                $.get("${pageContext.request.contextPath}/ajax", function (data) {
                    console.log(data);
                    let html = "";
                    for (let i = 0; i < data.length; i++) {
                        html += "<tr>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].age + "</td>" +
                            "<td>" + data[i].sex + "</td>" +
                            "</tr>"
                    }
                    $("#content").html(html);
                })
            })
        })
    </script>
</head>
<body>
<%--    <label for="username">用户名：</label>--%>
<%--    <input type="text" id="username" onblur="send()"/>--%>

<input type="button" value="加载数据" id="btn">

<table cellspacing="1">
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">

    </tbody>
</table>
</body>
</html>
