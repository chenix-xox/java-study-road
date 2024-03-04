<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2024/2/16
  Time: 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form action="${pageContext.request.contextPath}/login" method="get">
    <label>
        用户名：
        <input type="text" name="username" />
    </label>
    <input type="submit" value="登录" />
</form>
</body>
</html>
