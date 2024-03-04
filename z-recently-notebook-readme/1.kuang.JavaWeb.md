# 字符编码

```java
req.setCharacterEncoding("utf-8");
resp.setCharacterEncoding("utf-8");
resp.setContentType("text/html;charset=utf-8");
```



# 初窥jsp

```jsp
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
```

`${pageContext.request.contextPath}`是动态的，获取当前请求的路径



```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobbys");
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbys));
        req.getRequestDispatcher(req.getContextPath() + "/success.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
```



# 重定向和转发的区别

**相同：**

- 页面都会跳转

**不同：**

- 请求转发时，url不会产生变化  307
- 重定向时，url地址栏会发生变化  302







# Cookie

服务端给客户端一个cookie，客户端下次访问服务端的时候带上cookie

## **服务端响应给客户端cookie**

```java
Cookie[] cookies = req.getCookies();//获取cookie
cookie.getName();//获得cookie中的key
cookie.getvalue();//获得cookie中的value
new Cookie("lastLoginTime",System.currentTimeMil1is()+"");//新建一个cookie
cookie.setMaxAge(24*60*60);//设置cookie的有效期
resp.addcookie(cookie);//响应给客户端一个cookie
```

cookie：一般保存在本地的用户目录下appdata;

## **细节**

> 一个网站的cookie是否存在上限？
>
> 一个cookie只能保存一个信息
>
> 一个web站点可以给浏览器发送多个cookie
>
> 一个站点最多存放20个
>
> 一个cookie的大小限制4kb
>
> 一个浏览器大概300个cookie上限



## 删除cookie

- 不设置有效期，关闭浏览器自动失效；

- 设置有效期为0：

  - 在另一站点，创建一个和要删除的cookie一样的名字，设置该cookie有效期为0

    ```java
    Cookie cookie = new Cookie("lastLoginTime", "xxx");
    cookie.setMaxAge(0);
    resp.addCookie(cookie);
    ```





## Cookie存储中文

**使用URLEncoder进行编码及解码**

> - 存储时**编码**：
>
>   `Cookie cookie = new Cookie("name",URLEncoder.encode("陈多多","utf-8"));`
>
> - 获取时**解码**：
>
>   `URLDecoder.decode(cookie.getValue(),"utf-8")`



# Session（重点）

服务器登记你来过了，下次你来的时候进行匹配

**Session介绍：**

- 服务器给每个用户（浏览器）创建一个Session对象
- 一个Session独占一个浏览器，只要浏览器没有关闭，这个Session就存在
- 用户登录后，整个网站都可以访问——>保存用户的信息；保存购物车的信息...



## Session使用

### 存储

```java
// 获取Session
HttpSession session = req.getSession();
// 往Session存东西
session.setAttribute("name", "陈Session");
// 获取Session的id
String sessionId = session.getId();
if (session.isNew()){
    resp.getWriter().write("新创建的Session，id为：" + sessionId);
}else {
    resp.getWriter().write("session已存在，id为：" + sessionId);
}
```

### 获取

```java
HttpSession session = req.getSession();
String name = (String) session.getAttribute("name");
System.out.println(name);
```

### 手动注销

```java
//手动注销Session
session.invalidate();
```

### 配置自动过期

**web.xml配置**

```xml
<!--  设置Session默认的失效时间  -->
<session-config>
    <!--    以分钟为单位，1分钟后，Session失效    -->
    <session-timeout>1</session-timeout>
</session-config>
```



# JSP

## 九大内置对象

> //保存的数据只在一个页面有效
>
> pageContext.setAttribute("xxx","xxx");

> //保存的数据只在一次请求中有效，请求转发会携带
>
> request.setAttribute("xxx","xxx");

> //保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
>
> session.setAttribute("xxx","xxx");

> //保存的数据只在服务器中有效，从打开服务器到关闭服务器
>
> application.setAttribute("xxx","xxx");



## JSP标签、JSTL标签、EL表达式

```xml
<!-- JSTL表达式的依赖 -->
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<!-- standard标签库 -->
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
```

**EL表达式：**${ }

- 获取数据
- 执行运算
- 获取web开发的常用对象

`<jsp:include page="xxx.jsp">`

获取表单内的参数：

${param.参数名}



**JSP标签：**

带参数转发：

```jsp
<jsp:forward page="xxx.jsp">
	<jsp:param name="" value=""></jsp:param>
</jsp:forward>
```



取出参数：

```jsp
<%=request.getParameter("xxx")%>
```





### **JSTL表达式**

JSTL标签库的使用就是为了弥补HTML标签的不足；

**核心标签：引入JSTL核心标签**

`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`

**格式化标签：**

**SQL标签：**

**XML标签**



JSTL标签库使用步骤

- 引入对应的taglib
- 使用其中的方法
- 在Tomcat也需要引入jstl的包，否则会报错:JSTL解析错误



# JavaBean

实体类

JavaBean有特定的写法：

- 必须要有一个无参构造
- 属性必须私有化
- 必须有对应的get/set方法

一般用来和数据库的字段做映射 ORM；

ORM：对象关系映射

- 表--》类
- 字段--》属性
- 行记录--》对象



# MVC三层架构

Model View Controller 模型视图控制器

## Controller

- 控制器：Controller
- Servlet
- 接受用户请求
- 响应给客户端内容（交给业务层去做）
- 重定向或者转发（视图跳转）



## View

- 视图层：View
- JSP
- 展示数据
- 提供可以供操作的请求