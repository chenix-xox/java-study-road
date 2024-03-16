# 数据库的概念

## 数据库的好处

①实现数据持久化

②使用完整的管理系统统一管理

### DB

**数据库（database）**：存储数据的“仓库”。它保存了一系列有组织的数据。

### DBMS

**数据库管理系统（Database Management System）**。数据库是通过DBMS创建和操作的容器。

MySQL是其中一种↑。

常见的数据库管理系统（数据库软件）：MySQL，Oracle，DB2，SqlServer等。

### SQL

**结构化查询语言（Structure Query Language）**：专门用来与数据库通信的语言。

#### SQL的优点

1.不是某个特定数据库供应商专有的语言，几乎所有的DBMS都支持SQL。

2.简单易学。

3.虽然简单，但实际上是一种强有力的语言，灵活使用其语言元素，可以进行非常复杂和高级的数据库操作。

## 数据库的特点

1.将数据放到表中，表再放到库中。

2.一个数据库中可以有多个表，每个表都有自己的名字，用来标识自己。**表名具有唯一性。**

3.表具有一些特性，这些特性定义了数据在表中如何存储，类似java中“类”的设计。

4.表由**列**组成，我们也成为**字段**。所有的表都是由一个或多个列组成的，每一列类似java中的**“属性”**。

5.表中的数据是按行存储的，每一行类似java中的“对象”。

## 数据库的安装

```
		第一步：去官网下载安装（重点）第二步：先解压，然后在mysql下创建一个my.ini文件，
		更改my.ini文件里面的两行安装目录，
		第二行加上\data，my.ini文件不能多或少一个符号，
		在path（环境变量里面）加上mysql路径（/bin）。（重点）第三步：进入命令指示符（cmd），
		输入mysqld --initialize-insecure --user=mysql，
		再输入mysqld -install，
		出现Service successfully installed.表示配置完成
		启动数据库net start mysql，
		输入mysql -u root -p，不用输入密码直接回车
		出现mysql>表示配置完成
		输入alter user user() identified by "密码";
		输入net stop mysql关闭数据库
```

## MySQL常用命令介绍

例如，要进入test数据库

```mysql
show databases;→显示当前主机下有哪些数据库
use test;→显示Database changed
show tables;→显示表
show tables from mysql;→直接显示mysql中的表，但是依旧在test库里
select database();→显示当前在哪个库
creat table [表名](
->id int,
->name varchar(20));→创建一个新表
show tables;→显示表
desc [表名];→查看表的结构
select * from [表名];→查看数据
insert into [表名] (id,name) values(1,'john');→插入数据
insert into [表名] (id,name) values(2,'rose');→插入数据
updata [表名] set named='lilei' where id=1;→修改数据
delete from [表名] where id=1;→删除数据
select version();→显示版本
exit;→退出
```

## MySQL的语法规范

1.不区分大小写，但建议关键字大写，表名、列名小写

2.每条命令最好用分号结尾

3.每条命令根据需求，可以进行缩进或者换行（建议关键字单独一行）

4.注释

​		单行注释：#注释文字

​		单行注释：-- 注释文字

​		多行注释：/*

## DQL语言

（Data Query Language）SQL的细分之一。

查询（基础查询、条件查询、排序查询）

## DML语言

操作（增、删、改语句）

## DDL语言

数据定义（库和表的管理）

## TCL语言

事务控制（事务和事务处理）

