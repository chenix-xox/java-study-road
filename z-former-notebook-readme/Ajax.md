# 1.原生AJAX

## 1.1Ajax间接

全称~~~，就是异步的JS和XML

通过Ajax可以在浏览器中向服务器发送异步请求，最大的优势：无刷新获取数据

Ajax不是新的编程语言，二十一种将现有的标准组合在一起的使用的新方式

## 1.2XML简介

XML可扩展标记语言

XML被设计用来传输和存储数据

XML和HTML类似，不同的是HTML中都是预定义标签，而XML中没有预定义标签，全是自定义变标签，用来表示一些数据

```xml
比如有一个学生数据：
	name = "孙悟空";age = 18;gender="男";
用XML表示：
	<student>
		<name>孙悟空</name>
		<age>18</age>
		<gender>男</gender>
	</student>
```

现在已经被JSON取代了

```json
用JSON表示：
{"name":"孙悟空","age":19,"gender":"男"}
```

## 1.3Ajax的特点

### 1.3.1Ajax的优点

1）可以无需刷新页面而与服务器端进行通信

2）允许你根据用户事件来更新部分页面内容

### 1.3.2Ajax的缺点

1）没看有浏览历史，不能回退

2）存在跨域问题（同源）

3）SEO不友好

## 发送内容：请求报文

重点是格式与参数

行

头

空行

体

![image-20210928191244309](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210928191244309.png)

## 收到内容：响应报文



## 1.4Ajax的使用

### 1.4.1核心对象

