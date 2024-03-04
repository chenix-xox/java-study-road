<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2024/2/13
  Time: 5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$

  <form action="${pageContext.request.contextPath}/testInput" method="post">
    <label>
      姓名：
      <input type="text" name="name" />
    </label>
    <label>
      年龄：
      <input type="text" name="age" />
    </label>
    <label>
      性别：
      <input type="text" name="sex" />
    </label>
    <input type="submit" />
  </form>
  </body>
</html>
