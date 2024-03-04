<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1 style="text-align: center">登录</h1>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        爱好：
        <input type="checkbox" name="hobbys" value="有1">有1
        <input type="checkbox" name="hobbys" value="也有2">也有2
        <input type="checkbox" name="hobbys" value="有3">有3
        <input type="checkbox" name="hobbys" value="还有4">还有4
        <input type="submit">
    </form>
</div>
</body>
</html>
