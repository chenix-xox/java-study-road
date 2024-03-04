[TOC]

# MyBatis

## 使用Mybatis

> 我现在是直接
>
> @Autowired
>
> private XxxMapper xxxMapper;
>
> xxxMapper.getList();

```java
// 测试
@Test
public void test(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    
    XxxMapper mapper = sqlSession.getMapper(XxxMapper.class)
    mapper.getList();
    
    sqlSession.close();
}
```

👇 以上是业务代码，除此之外还有mybatis-config.xml配置文件中绑定注册Mapper接口

```xml
// mybatis-config.xml
...
<mappers>
	<mapper resource="com.xxx.mapper.XxxMapper"/>
	...
</mappers>
...
```

👇 还有编写MybatisUtils工具类

```java
//MybatisUtils工具类
//SqlSessionFactory -->SqlSession
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    static {

        try {
            //使用Mybaties第一步：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
    // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
    //你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
    
    // openSession设置为true可以自动提交事务
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }

}
```



## 配置文件

### 别名

> -resources
>
> ​	-mybatis-config.xml

mybatis-config.xml里配置configuration标签，可以引入外部配置文件...配置环境<enviroments>..

**特别的点(优化)**：类型别名，可以配置**别名**

- 如：使用mybatis标签的时候，resultType参数，如果是某某实体类，则要写路径com.xxx.User。

  ```xml
  <select resultType="com.xxx.User"></select>
  ```

- 在mybatis-config.xml中使用typeAliases标签，给类起别名

  ```xml
  <typeAliases>
  	<typeAlias type="com.xxx.User" alias = "User" />
  </typeAliases>
  ```

- 配置完成后，mybatis标签里需要用到路径指定实体类的地方，可以直接使用alias配置的简写。

  ```xml
  <select resultType="User"></select>
  ```

- **也可以指定包名**

  > -pojo
  >
  > ​	-User
  >
  > ​	-Role

  ```xml
  <typeAliases>
  	<package name = 'com.pojo'>
  </typeAliases>
  ```

  Mybatis会在该包下搜索需要的JavaBean(小写)

- 在该包下的JavaBean，没有注解的时候，用Bean的小写作为别名，如果有注解，则注解值为别名

  ```java
  @Alias("xxx")
  public class Author{
  	...
  }
  ```



**默认别名**

> 我现在（2024.01.28）是如果返回类型为String
>
> 会把resultType的参数写java.lang.String
>
> **实际上**可以直接写小写string



## resultMap - 结果集映射

> 我现在使用的大多数是resultType
>
> 如：resultType为User，User里是username和password
>
> 而数据库存储的password字段为pwd
>
> 这个时候我查询就会写
>
> ```xml
> <select id="xxx" resultType="User">
> 	select username,pwd as password from xxx
> </select>
> ```

**使用resultMap**

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
	<!--column数据中的字段，property实体类中的属性-->
    <result column="pwd" property="password"/>
</resultMap>

<select id="xxx" resultMap="UserMap">
	select username,pwd as password from xxx
</select>
```

**ResultMap**的设计思想：对简单的语句不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它的关系(如上)，只需要对 数据库和实体类不同的属性名进行配置即可。



**如果世界总是这么简单就好了...？**



## 日志

- SLF4J
- LOG4J【掌握】
- JDK_LOGGING
- COMMONS_LOGGING
- STDOUT_LOGGING【掌握】
- NO_LOGGING

使用哪个日志在配置文件中配置

**配置**(mybatis-config.xml)

```xml
<settings>
	<setting name="logImpl" value="STDOUT_LOGGING" />
</settings>
```



## 复杂查询

### 环境搭建

```java
// 学生类 .. 省略lombok
public class Student{
    private int id;
    private String name;
    
    // 学生需要关联老师，在数据库中存储的是 id，name，tid ——> tid关联到老师信息
    private Teacher teacher;
}

// 教师类 .. 省略lombok
public class Teacher{
    private int id;
    private String name;
}
```



### 多对一处理

比如多个学生对应一个老师（在StudentMapper中进行操作）

#### 按照查询嵌套处理（类似子查询）

```xml
// 例如：查每个学生，并且查到每个学生对应的老师信息
// StudentMapper.xml    
// *：id，name，tid
<select id="getStudent" resultMap="StudentTeacher">
	select * from student
</select>

<resultMap id="StudentTeacher" type="Student">
	<result property="id" column="id" />
    <result property="name" column="name" />\
    // 复杂的属性，单独处理
    // 对象：association
    // 集合：collection
    <association property="teacher" column="tid" javaType="Teacher" select="getTeahcer" />
</resultMap>

<select id="getTeacher" resultType="Teacher">
	select * from teahcer where id = #{id}
</select>
```

**理解：**

association标签中，property对应Student类中的teacher对象，column对应数据库表中存储的值，并且该tid也是作为值传给select参数所对应的值（getTeacher）查询中。查询结果**property、javaType**、以及id为getTeacher中的**resultType**，均为同一值：Teacher

**注意点：**select参数所对应的getTeacher中，有且仅接收一个参数#{id}，以对应association标签中的column参数值，这个#{id}中的id可以写成任意内容。

#### 按照结果嵌套处理

（写一个SQL）

```xml
<select id="getStudent" resultMap="StudentTacher">
	select s.id as sid,s.name as sname
    t.name as tname
    from student s,teacher t
    where s.tid = t.id;
</select>

<resultMap id="StudentTeacher" type="Student">
    // 如果查询里写了as别名，那么column参数对应的值为别名
	<result property="id" column="sid" />
    <result property="name" column="sname" />\
    // 复杂的属性，单独处理
    // 对象：association
    // 集合：collection
    <association property="teacher" javaType="Teacher">
    	<result property="name" column="tname"/>
    </association>
    
    // 如果另一个表只查询了一个参数
    <result property="teacher.name" column="tname" />
</resultMap>
```

**个人理解：**Student中的teacher属性与Teacher类直接挂钩，然后将Teacher类里的name与此时查询的tname挂钩

👆以上举例中，查询teacher对象出来的id均为0，因为getStudent中没有查询t.id as tid，如若需要，还应在association标签中新增一行result标签以对应teacher中的id和查询的tid



### 一对多处理

比如：一个老师对应多个学生，在TeacherMapper里进行操作

对于老师而言就是一对多

**基于上面多对一处理中的Student类和教师类，新增**

```java
// Teacher类新增属性
private List<Student> students;
```



#### 按结果嵌套查询

**获取指定老师下的所有学生及老师信息**

```java
Teacher getTeacher(@Param("tid") int id)
```

 ```xml
<select id="getTeacher" resultMap="TeacherStudent">
	select s.id as sid,s.name as sname,t.name as tname,t.id as tid
    from studen s,teacher t
    where s.tid = t.id and t.id = #{tid}
</select>

<resultMap id="TeacherStudent" type="Teacher">
	<result property="id" column="tid" />
	<result property="name" column="tname" />
    // 对象用association，集合用collection
    // javaType指定属性类型
    // 集合中的泛型信息用ofType
	<collection property="students" ofType="Student">
    	<result property="id" column="sid" />
    	<result property="name" column="sname" />
    </collection>
</resultMap>
 ```



#### 按查询嵌套处理（类子查询

*：id，name

```xml
<select id="getTeacher" resultMap="TeacherStudent">
	select * from teacher where id = #{tid}
</select>

<resultMap id="TeacherStudent" type="Teacher">
	<collection property="students" javaType="ArrayList" ofType="Student" column="id" select="getStudent"/>
</resultMap>

// *：id，name，tid
<select id="getStudentByTeacherId" resultType="Student">
	select * from student where tid = #{id}
</select> 
```





## 动态SQL

**一个小知识点：**

**@SuppressWaranings("all")：**抑制警告，命名不规范等问题的提示... 

### 扩展

类似<where>和<set>的定制化标签<trim>

```xml
<trim prefix="WHERE" prefixOverrides="AND | OR">
	...
</trim> 

<trim prefix="SET" suffixOverrides=",">
	...
</trim> 
```

👆 以上示例分别等同于<where>标签和<Set>标签

- prefixOverrides：代表如果只有一个子元素，则需要去掉的前缀
- suffixOverrides：代表如果只有一个子元素，则需要去掉的后缀

### SQL片段

有些时候需要将一些功能抽取出来，方便复用

- 使用SQL标签抽取公共的部分
- 在需要使用的地方使用include标签引用

**举例：**

```xml
<sql id="if-title-author">
	<if test="title!=null">title = #{title}</if>
	<if test="author!=null">and author = #{author}</if>
</sql>

<select ...>
	...
    <where>
    	<include refid="if-title-author"></include>
    </where>
</select>
```

**注意事项：**

- 最好基于单表来定义SQL片段
- 不要存在where标签



## 缓存

### 简介

> 查询	：	连接数据库	-> 耗资源！
>
> ​	一次查询的结果，暂存到一个可以直接取到的地方	->	内存	：缓存
>
> 再次查询相同数据，直接走缓存，不用走数据库。

1. 什么是缓存
2. 为什么使用缓存
   - 减少与数据库的交互次数，减少系统开销，提高效率
3. 什么样的数据能使用缓存
   - 经常查询但不经常改变的数据  



### MyBatis的缓存

默认定义了两级缓存：**一级缓存**和**二级缓存**

- 默认情况只有一级缓存开启（SqlSession级别的缓存，也称为本地缓存）
- 二级缓存需要手动开启与配置，基于namespace级别的缓存
- 为了提高拓展性，MyBatis定义了缓存接口Cache。可以通过Cache接口来自定义二级缓存。



### MyBatis缓存策略

- LRU - 最近最少使用：移除最长时间不被使用的对象**（默认）**
- FIFO - 先进先出：按对象进入缓存的顺序来移除
- SOFT - 软引用：基于垃圾回收器状态和软引用规则移除对象
- WEAK - 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象



### 缓存测试

**步骤：**

1. 开启日志
2. 测试在一次SqlSession中查询两次相同的记录
3. 查看日志输出，并没有重复执行两次select语句

**缓存失效的情况：**

1. 查询不同的东西
2. 增删改操作可能会改变原来的数据，所以会刷新缓存
3. 查询不同的Mapper.xml
4. 手动清理缓存：`sqlSession.clearCache()`



### 一级缓存小结

一级缓存默认开启，只在一次SqlSession中有效，也就是拿到连接到关闭连接这个区间（getSqlSession,closeSession）



### 二级缓存

默认情况下，只启用本地的会话缓存，仅仅对一个会话中的数据进行缓存。

要启用全局的二级缓存，需要在SQL映射文件中添加一行：

`<cache />`

提示缓存只作用于cache标签所在的映射文件中的语句。

如果你混合使用Java API和XML映射文件，在共用接口中的语句将不会被默认缓存。

你需要使用@CacheNamespaceRef注解指定缓存作用域。

这些属性可以通过cache元素的属性来修改。比如：

```xml
<cache
  eviction="FIFO"
  flushInterval="6000"
  size="512"
  readOnly="true" />
```

👆 以上更高级的配置了一个FIFO缓存，每隔60s刷新，最多可以存储结果对象或列表的512个引用，而这些对象被认为是只读的，因此对其进行修改可能会在不同线程中的调用者产生冲突



- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace:级别的缓存，一个名称空间，对应一个二级缓存；
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；**但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中；**
  - 新的会话查询信息，就可以从二级缓存中获取内容：
  - 不同的mapperi查出的数据会放在自己对应的缓存(map)中



**步骤：**

1. 开启全局缓存（默认是true）

   mybatis-config.xml文件中进行setting配置

   `<setting name="cacheEnabled" value="true" />`

2. 在要使用二级缓存的mapper.xml文件中使用`<cache />`

   也可以自定义参数

   ```xml
   <cache
     eviction="FIFO"
     flushInterval="6000"
     size="512"
     readOnly="true" />
   ```

3. 局部设置：有些查询不需要使用缓存，可以写`<select ... useCache="faluse">`

4. **问题：**

   仅使用 `<cache />`时，必须将实体类序列化，即将该类实现Serializable



#### 小结：

- 只要开启了二级缓存，在同一个Mapper下就有效（namespace相同就算同一个mapper）
- 所有的数据都会先放在一级缓存中
- 只有当会话提交或关闭时（即：一级缓存消失），才会提交到二级缓存



### 缓存的顺序

1. 用户使用
2. 先进入二级缓存查找所需数据
3. 二级缓存没有就进一级缓存查找
4. 一级缓存也没有，执行SQL，在数据库查询



### 自定义缓存 - encache

1. 导包（Mybatis-encache）

2. 在二级缓存的mapper.xml文件中使用 `<cache />` 标签的时候添加参数

   `<cache type="org.mybatis.caches.encache.EncacheCache"/>`

3. resources目录中创建encache.xml文件进行配置（一创建就有内容）





# Spring

## Bean

> -resources
>
> ​	-beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="..."> (1) (2)
        <!-- 这个bean的合作者和配置在这里 -->
    </bean>

    <bean id="..." class="...">
        <!-- c这个bean的合作者和配置在这里 -->
    </bean>

    <!-- 更多bean 定义在这里 -->

</beans>
```



**以前创建对象**

```java
Hello hello = new Hello();
hello.setStr("HelloWorld");
syso(hello.getStr());
```



> bean = 对象
> class = new 的对象
> property 相当于给对象设置的值



**使用容器思想创建的对象**

```xml
<-- 在beans.xml文件中-->
<bean id="hello" class="com.xxx.pojo.Hello">
	<property name="str" value="String" />
</bean>
```

```java
// 获取Spring的上下文对象
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

// 对象已经在Spring中管理了，要用直接取出即可
Hello hello = (Hello) context.getBean("hello");

System.out.println(hello.getStr());
```

这就是控制反转。

**控制：**传统程序的对象是由对象本身控制的，使用Spring后，对象是由Spring来创建的

**反转：**程序本身不创建对象，而变成被动的接收对象

**依赖注入：**利用set方法进行注入（如果实体类的某个属性没有set方法，那么bean标签创建对象的property就无法操作）



**注意：**使用容器创建对象的时候，如果不想使用强制类型转换，可以添加反射

```java
Hello hello = context.getBean("hello", Hello.class);
```



### 实现类的bean

```xml
<!-- 创建实现类的-->
<!-- 相当于new XxxDaoAaImpl-->
<bean id="aImpl" class="com.xxx.dao.XxxDaoAaImpl" />
<!-- 相当于new XxxDaoBbImpl-->
<bean id="bImpl" class="com.xxx.dao.XxxDaoBbImpl" />
<!-- 相当于new XxxServiceImpl-->
<bean id="xxxServiceImpl" class="com.xxx.service.XxxServiceImpl">
    <!--
    ref：引用spring容器中创建好的对象
    value：具体的值，基本数据，类型
    -->
    <property name="xxxDao" ref="aImpl" />
</bean>
```

```java
// 获取Spring的上下文对象
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

XxxServiceImpl xxxServiceImpl = (XxxServiceImpl) context.getBean("xxxServiceImpl");
System.out.println(xxxServiceImpl.getStr());
```

此时，需要service实现类使用不同的dao实现类，只需要修改beans.xml配置文件中的ref值（方便用户）

**个人拙见：**基于此，诞生后续的容器思想，不需要使用bean.xml，直接使用注解，谁要放到容器，直接通过一个注解即可，然后哪里需要使用，也不需要new ClassPathXmlApplicationContext，直接用注解注入



### IOC创建对象的方式

1. 使用无参构造创建对象（默认）

2. 假如要使用有参构造

   1. 下标赋值

      ```xml
      <bean id="hello" class="com.xxx.pojo.Hello">
      	<constructor-arg index="0" value="strstr" />
      </bean>
      ```

   2. 类型赋值(不建议)

      ```xml
      <bean id="hello" class="com.xxx.pojo.Hello">
      	<constructor-arg type="java.lang.String" value="strstr" />
      </bean>
      ```

   3. 参数名赋值

      ```xml
      <bean id="hello" class="com.xxx.pojo.Hello">
      	<constructor-arg name="str" value="strstr" />
      </bean>
      ```



**小结：**配置文件加载的时候，容器中的管理对象就已经初始化了



### 别名

`<alias name="xxx" alias="other_name" />`

或直接在<bean>内使用name参数，也可以起别名，可以多个别名

`<bean id="hello" class="com.xxx.pojo.Hello" name="other_name,other_name1 other_name2;other_name2">`



### 真正的引用

> -resource
>
> ​	-applicationContext.xml

如果需要引入多个.xml文件，直接在applicationContext里使用import标签

```xml
<import resource="bean1.xml">
<import resource="bean2.xml">
    ...
```

**注意：**根据导入顺序不同，如果有相同id的，后面的会覆盖前面的



### 依赖注入

1. 构造器注入（看之前的）
2. Set方式注入
3. 扩展方式注入



#### Set注入

##### 普通注入(value)

```xml
<bean id="xxx" class="com.xxx.pojo.xxx">
    <property name="name" value="xxx" />
</bean>
```

##### Bean注入(ref)

```xml
<bean id="address" class="com.xxx.pojo.Address" />
<bean id="student" class="com.xxx.pojo.Student">
    <property name="address" ref="address" />
</bean>
```

##### 数组 / list / map / Set / null 注入

```xml
<bean id="student" class="com.xxx.pojo.Student">
    <property name="books">
    	<array>
        	<value>book1</value>
        	<value>book2</value>
        	<value>book3</value>
        </array>
    </property>
    
    <property name="hobbies">
    	<list>
        	<value>hobby1</value>
        	<value>hobby2</value>
        	<value>hobby3</value>
        </list>
    </property>
    
    <property name="hobbies">
    	<map>
        	<entry key="" value="" />
            <entry key="" value="" />
            ...
        </map>
    </property>
    
    <property name="games">
    	<set>
        	<value>game1</value>
        	<value>game2</value>
        	<value>game3</value>
        </set>
    </property>
    
    <!-- null-->
    <property name="pointer" value=""/>
    <property name="pointer">
    	<null />
    </property>
    	
    <property name="adminEmails">
        <props>
            <prop key="administrator">administrator@example.org</prop>
            <prop key="support">support@example.org</prop>
            <prop key="development">development@example.org</prop>
        </props>
    </property>
</bean>
```



##### p命名空间注入

> applicationContext.xml里添加一行
>
> `xmlns:p="http://www.springframework.org/schema/p"`
>
> ```xml
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xmlns:p="http://www.springframework.org/schema/p"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans
>         https://www.springframework.org/schema/beans/spring-beans.xsd">
> ```

```xml
<bean name="user" class="com.xxx.pojo.User" p:username="x" p:pwd="xx" />
```

**提示：**作用和property相同





##### c命名空间注入

> 导入
>
> `xmlns:c="http://www.springframework.org/schema/c"`

**原**

```xml
<bean id="hello" class="com.xxx.pojo.Hello">
	<constructor-arg name="str" value="strstr" />
</bean>
```

**使用c注入**

```xml
<bean id="hello" class="com.xxx.pojo.Hello"
      constructor-arg name="str" value="strstr" />
```

```xml
<bean name="user1" class="com.xxx.pojo.User" c:username="x" c:pwd="xx"/>
```

**提示：**和构造器注入相同



### Bean作用域

`<bean scope="" />`

1. singleton：单例模式（默认）
2. prototype：原型模式，每次从容器中get都会产生一个新对象
3. request / session / application ：仅web开发使用





### Bean的自动装配

> byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的bean id

```xml
<bean ... autowire="byName">
	...
</bean>
```

例如，id="people" class="....People"实体类中有两个对象分别为Dog和Cat

根据Set**Dog**进行匹配（猜的）

因此people使用autowire="byName" ，将在上下文寻找 `<bean id="cat">` 和`<bean id="dog">`



> byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean

```xml
<bean ... autowire="byType">
	...
</bean>
```

根据属性类型进行匹配 **Dog** dog（猜的）

在上下文寻找`<bean class="...cat">`和`<bean class="...dog">`





### 使用注解实现自动装配

**使用注解须知：**

1. 导入约束（context的约束）

2. 配置开启注解的支持 `<context:annotation-config/>`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:c="http://www.springframework.org/schema/c"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/aop
           https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <context:annotation-config />
   
   </beans>
   ```

3. 使用@Autowired

   **个人拙见：**使用之后可以省略set方法，就算不写set也可以在xml文件中使用

   ```java
   public class Student{
       @Autowired
       private String name;
   }
   ```



### 使用注解开发

#### @Component（组件）

> <!--指定要扫描的包，这个包下的注解就会生效-->
> <context:component-scan base-package="com.xxx" />

**之前**每次创建对象需要在<beans>中创建对应的<bean>

使用了包扫描后，可以直接在对应的类上加上@Component注解，说明这个类被Spring管理了，等价于写了一个<bean>





#### @Value

等同于`<bean>`下的`<property>`

例如： `<property name="username" value="a" />`

等价于

```java
@Value("a")
private String username;
```





#### @Component的衍生

> 在web开发中，会按照mvc三层架构分层

- dao：**@Respository**
- service：**@Service**
- controller：**@Controller**
- **注意：**功能相同，都代表将某个东西注册到Spring容器中，装配Bean



#### @Scope("singleton")

等同于 `<bean scope="singleton" />`



## JavaConfig

-- 使用Java配置Spring（不使用xml）

-- javaconfig是srping的子项目



### @Configuration

使用**@Component**将User类注册到Spring容器

**@Configuration**（代表是一个配置类）也会被容器托管，本身也有**@Component**注解

**@Configuration**和bean.xml一样

```java
@Component
public class User{
    private 
}

@Configuration
@ComponentScan("com.xxx.pojo")
public class JavaConfig{
    
    // getBean中的名字与此方法名一致 
    @Bean
    public User getUser(){
        return new User();
    }
}
```



### AnnotationConfigApplicationContext

**如果使用配置类（@Configuration）的方式，那么需要通过AnnotationConfigApplicationContext来获取容器**

```java
ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
context.getBean("getUser");
```



### 多个配置类 @Import

```java
@Configuration
public class config2{
	...
}
```

```java
@Configuration
@Import(config2.class)
public class config1{
	...
}
```





# 代理模式

代理模式分类：

- 静态代理
- 动态代理



## 静态代理

**原则：**不改变原有代码的情况下扩展功能，使用代理可完美实现

例如：**Service👇**

```java
public interface UserService {
    void add();
    void delete();
    void update();
    void query();
}
```

**ServiceImpl👇**

```java
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("新增");
    }

    @Override
    public void delete() {
        System.out.println("删除");

    }

    @Override
    public void update() {
        System.out.println("更新");
    }

    @Override
    public void query() {
        System.out.println("查询");
    }
}
```



**代理（这个还是好麻烦，用Proxy.newProxyInstance**

同样实现了UserService，在此基础上扩展log功能

```java
public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }

    public void log(String msg){
        System.out.println("[log] " + msg);
    }
}
```



**客户端使用**

```java
public static void main(String[] args) {
    // 获取UserServiceImpl
    UserServiceImpl userService = new UserServiceImpl();
    
    // 创建一个代理对象
    UserServiceProxy proxy = new UserServiceProxy();
    
    // 将UserServiceImpl交给代理对象
    proxy.setUserService(userService);
    
    // 调用代理对象的方法
    proxy.add();
}
```



**静态代理的最大缺点：**每需要新增扩展功能的时候，代码量就会翻倍 -- >  **动态代理**



## 动态代理

分为两大类：基于接口和基于类

- 基于接口 -- > JDK 动态代理（此处使用）
- 基于类：cglib
- 基于java字节码：javasist



了解两个类：Proxy（代理）、InvocationHandle（调用处理程序）

**创建代理工具类**

```java
public class ProxyUtil {
    public Object createProxy(Object target) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
                    {
                        System.out.println("准备执行" + method.getName() + "方法");
                        Object res = method.invoke(target, args);
                        System.out.println("执行完成");
                        return res;
                    }
                });
    }
}

```



**使用代理工具**

```java
// 创建代理工具
ProxyUtil proxyUtil = new ProxyUtil();

// 使用代理工具中的创建代理方法，把要代理的实现类放入
UserService userServiceProxy = (UserService) proxyUtil.createProxy(new UserServiceImpl());

// 使用代理执行方法
userServiceProxy.add();
```













# AOP

**个人小结**：aop切面编程，指的是在原有的开发流程中（纵向流程），添加新功能，通过横向切入，即不改变原有代码的情况下进行扩展。

即：AOP的实现机制（示例代码参考静态代理中的示例代码）

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```

- 切面（Aspect）：横切关注点 被模块化的 特殊对象。即：一个类 **--> Log**
- 通知（Advice）：切面必须要完成的工作。即：类中的一个方法 **--> Log中的方法**
- 目标（Target）：被通知的对象。即：一个接口或一个方法 
- 代理（Proxy）：向目标对象应用通知后创建的对象  **--> 生成的代理类** 
- 切入点（PointCut）：切面通知 执行的 “地点”的定义
- 连接点（JointPoint）：与切入点匹配的执行点



## AOP实现

**举例：**新建一个Log类，可能在前面，也可能在后面插入到代码中

需要在之前搞一个Log方法，就将该Log类实现**MethodBeforeAdvice**，重写invoke方法

```java
public class BeforeLog implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        
    }
}
```

- **method：**要执行的目标对象的方法
- **args（objects）：**参数
- **target（o）：**目标对象 
- **该方法会在执行真正方法前，自动调用一次**



如果是之后搞一个Log方法，实现**AfterReturnAdvice**，重写invoke方法

```java
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) 
        throws Throwable {

    }
}
```

- **returnValue：**因为是执行后的Log方法，因此可以接收returnValue参数



### 原生Spring API配置AOP（方式一）

```xml
<!-- 注册bean-->
<bean id="userService" class="service.Impl.UserServiceImpl" />
<bean id="beforeLog" class="Log.BeforeLog" />
<bean id="afterLog" class="Log.AfterLog" />

<!-- 方式一：使用原生Spring API接口-->
<!-- 配置aop-->
<aop:config>
    <!-- 切入点：expression表达式，execution(要执行的位置)-->
    <aop:pointcut id="pointcut" expression="execution(* service.Impl.UserServiceImpl.*(..))"/>
    <!-- 执行环绕-->
    <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut" />
    <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut" />
</aop:config>
```



**测试**

```java
ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
UserService userService = context.getBean("userService", UserService.class);
userService.add();
```

**小tips：**动态代理代理的是接口，因此getBean返回的是接口对象，不是实现类对象





### 自定义实现AOP（方式二）

**自定义切入点**（一个类，实现before和after两个方法，刚才分了两个类分别实现不同的类完成的before和after方法）

```java
public class DiyPointCut {
     public void before(){
         System.out.println("before");
     }
     
     public void after(){
         System.out.println("after");
     }
}
```

**xml中注册+配置**

```xml
<bean id="diy" class="Diy.DiyPointCut" />
<aop:config>
    <!-- 自定义切面：ref（要引用的类）-->
    <aop:aspect ref="diy">
        <!-- 切入点-->
        <aop:pointcut id="pointcut" expression="execution(* service.Impl.UserServiceImpl.*(..))"/>
        
        <!-- 通知-->
        <aop:before method="before" pointcut-ref="pointcut"/>
        <aop:after method="after" pointcut-ref="pointcut"/>
    </aop:aspect>
</aop:config>  
```



### 注解实现AOP（方式三）

**需要提前注册到Spring容器，并开启自动代理的切面注解**

```xml
<bean id="annotationPointCut" class="Diy.AnnotationPointCut" />
<aop:aspectj-autoproxy/>
```



#### @Aspect

标注一个类为一个切面

```java
@Aspect
public class AnnotationPointCut {

}
```

代替如下配置代码

```xml
<bean id="diy" class="Diy.DiyPointCut" />
<aop:config>
    <!-- 自定义切面：ref（要引用的类）-->
    <aop:aspect ref="diy">
        ...
    </aop:aspect>
</aop:config>  
```





#### @Before()

标注切入点

```java
@Before("execution(* service.Impl.UserServiceImpl.*(..))")
public void before() {
    System.out.println("before");
}
```

代替如下配置代码

```xml
<aop:pointcut id="pointcut" expression="execution(* service.Impl.UserServiceImpl.*(..))"/>
<aop:before method="before" pointcut-ref="pointcut"/>
```



#### @After()

标注切入点

```java
@After("execution(* service.Impl.UserServiceImpl.*(..))")
public void after() {
    System.out.println("after");
}
```

代替如下配置代码

```xml
<aop:pointcut id="pointcut" expression="execution(* service.Impl.UserServiceImpl.*(..))"/>
<aop:after method="after" pointcut-ref="pointcut"/>
```



#### 测试

```java
ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
UserService userService = context.getBean("userService", UserService.class);
userService.add();
```



#### @Around（环绕增强）

```java
@Around("execution(* service.Impl.UserServiceImpl.*(..))")
public Object around(ProceedingJoinPoint pjp) {
    System.out.println("around里的参数：" +  pjp);
    System.out.println("around前");
    try {
        pjp.proceed();
    } catch (Throwable e) {
        e.printStackTrace();
    }
    System.out.println("around后");
    return null;
}
```

- pjp是连接点，pjp.proceed就会继续执行后续代码，否则直接停下
- before，around，after的执行顺序是：    proceed前，**before**，proceed，proceed后，**after**





# Spring整合MyBatis

导入新包：mybatis-spring





# SpringMVC

MVC：模型（dao、service）、视图（jsp）、控制器（servlet）

MVC：模型（model）、视图（view）、控制器（controller）



## JSP方面使用MVC思想编写Controller

1.**不建议**使用在<u>xxxController</u>中实现<u>Controller</u>接口，重写<u>ModelView handleRequest</u>的方式编写



**步骤**

> 1.新建web项目
>
> 2.导入jar包
>
> 3.编写web.xml，注册DispatcherServlet【固定步骤，一次编写，整个项目受益】
>
> ```xml
> <servlet>
>     <servlet-name>SpringMVC</servlet-name>
>     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
>     <init-param>
>         <param-name>contextConfigLocation</param-name>
>         <param-value>classpath:springmvc-servlet.xml</param-value>
>     </init-param>
>     <load-on-startup>1</load-on-startup>
> </servlet>
> 
> <servlet-mapping>
>     <servlet-name>SpringMVC</servlet-name>
>     <url-pattern>/</url-pattern>
> </servlet-mapping>
> ```
>
> 4.编写springmvc配置文件【固定步骤，一次编写，整个项目受益】
>
> ```xml
> <context:component-scan base-package="com.xxx.controller"/>
> <mvc:default-servlet-handler />
> 
> <mvc:annotation-driven />
> 
> <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
>       id="internalResourceViewResolver">
>     <property name="prefix" value="/WEB-INF/jsp/" />
>     <property name="suffix" value=".jsp" />
> </bean>
> ```
>
> 5.创建对应多个控制类 ： Controller
>
> 6.完善前端视图和controller的对应
>
> 7.运行调试

1. 创建web项目

2. 在Project Structure - Artifacts 该maven项目下的WEB-INF下，创建lib目录，导入spring的jar包

3. 编写web.xml，注册DispatcherServlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <!--1.注册servlet-->
       <servlet>
           <servlet-name>SpringMVC</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!--通过初始化参数指定SpringMVC配置文件的位置，进行关联-->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
           <!-- 启动顺序，数字越小，启动越早 -->
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <!--所有请求都会被springmvc拦截 -->
       <servlet-mapping>
           <servlet-name>SpringMVC</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
   </web-app>
   ```

4. 编写springmvc配置文件（springmvc-servlet.xml）

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
     http://www.springframework.org/schema/beans/spring-beans.xsd
     http://www.springframework.org/schema/context
     https://www.springframework.org/schema/context/spring-context.xsd
     http://www.springframework.org/schema/mvc
     https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!-- 自动扫描包，让指定包下的注解生效,由IOC容器统一管理 -->
       <context:component-scan base-package="com.xxx.controller"/>
       <!-- 让Spring MVC不处理静态资源 -->
       <mvc:default-servlet-handler />
       <!--
       支持mvc注解驱动
       在spring中一般采用@RequestMapping注解来完成映射关系
       要想使@RequestMapping注解生效
       必须向上下文中注册DefaultAnnotationHandlerMapping
       和一个AnnotationMethodHandlerAdapter实例
       这两个实例分别在类级别和方法级别处理。
       而annotation-driven配置帮助我们自动完成上述两个实例的注入。
       -->
       <mvc:annotation-driven />
   
       <!-- 视图解析器 模板引擎：Thymeleaf Freemaker... -->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
             id="internalResourceViewResolver">
           <!-- 前缀 -->
           <property name="prefix" value="/WEB-INF/jsp/" />
           <!-- 后缀 -->
           <property name="suffix" value=".jsp" />
       </bean>
   
   </beans>
   ```

5. 编写controller层代码（com.xxx.controller.**Controller）

   ```java
   package com.xxx.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   /**
    * @author Chenix
    * @create 2024-02-12 0:50
    */
   @Controller
   public class HelloController {
       @RequestMapping("/h1")
       public String hello(Model model){
           model.addAttribute("msg","hello~~~");
           return "hello";
       }
   }
   ```

6. 运行完毕

**踩坑：**

> HTTP状态 500 - 内部服务器错误 类型 异常报告 消息 Servlet[SpringMVC]的Servlet.init（）引发异常 描述 服务器遇到一个意外的情况，阻止它完成请求。 例外情况 javax.servlet.ServletException: Servlet[SpringMVC]的Servlet.init（）引发异常 org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:543) org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:698) org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:367) org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:639) org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:882) org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1647) org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) java.base/java.lang.Thread.run(Thread.java:833) 根本原因。 org.springframework.beans.factory.BeanDefinitionStoreException: Failed to read candidate component class: file [D:\JavaProject\spring-study\out\artifacts\spring_04_mvc_Web_exploded\WEB-INF\classes\com\xxx\controller\HelloController.class]; nested exception is org.springframework.core.NestedIOException: ASM ClassReader failed to parse class file - probably due to a new Java class file version that isn't supported yet: file [D:\JavaProject\spring-study\out\artifacts\spring_04_mvc_Web_exploded\WEB-INF\classes\com\xxx\controller\HelloController.class]; nested exception is java.lang.IllegalArgumentException: Unsupported class file major version 61
>
> ...

**解决方案：**java版本与spring版本不兼容，使用的spring版本过低？java版本使用了17，修改成8即可



访问**localhost:8080/h1**，可跳转至**hello.jsp**的页面

使用了**@Controller**注解的controller层代码中

**h1**对应接口返回的字符串**“hello”**，该**hello**会被**视图解析器处理**

跳转至**hello.jsp**的页面

```java
@Controller
public class HelloController {
    @RequestMapping("/h1")
    public String hello(Model model){
        model.addAttribute("msg","hello~~~");
        return "hello";
    }
}
```



**ps：**如果是使用**@RestController**注解，那么接口的返回值会**解析为json**，而非匹配并跳转jsp页面





## @RequestMapping

如果要在@RequestMapping中遵循Restful风格

可以使用method参数 `@RequestMapping(value = "/h1",method = RequestMethod.GET)`





## 重定向和转发 - 无需视图解析器

> **无需如下代码**
>
> ```xml
> <!-- 视图解析器 -->
> <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
>       id="internalResourceViewResolver">
>     <!-- 前缀 -->
>     <property name="prefix" value="/WEB-INF/jsp/" />
>     <!-- 后缀 -->
>     <property name="suffix" value=".jsp" />
> </bean>
> ```



**通过SpringMVC实现重定向和转发**

- 转发

```java
@GetMapping("/forward")
public String forward(Model model) {
    model.addAttribute("msg", "转发");
    return "forward:/WEB-INF/jsp/hello.jsp";
}
```



- 重定向

```java
@RequestMapping("/redirect")
public String redirect(Model model) {
    model.addAttribute("msg", "重定向");
    return "redirect:/index.jsp";
}
```



## 乱码问题

使用springmvc乱码过滤器（在**web.xml**文件中配置）

```xml
<!-- 配置中文乱码过滤器 -->
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!-- 初始化过滤器 -->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <!-- 拦截所有请求 -->
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



## JSON

前言..

为了返回json数据（字符串），后端接口就不能通过视图解析器

此时就需要用到注解**@ResponseBody**，或者直接将**Controller**上的**@Controller**注解改为**@RestController**

此时访问/xxx接口，得到的就是"test"字符串，而非跳转到test.jsp页面

```java
@RequestMapping("/xxx")
@ResponseBody
public String test(){
    return "test";
}
```



### Jackson

将一个对象转换为json格式返回(om**.writeValueAsString**)

```java
@RequestMapping("/json")
@ResponseBody
public String jsonTest(){
    User user = new User("xxx", 18, "男");
    ObjectMapper om = new ObjectMapper();
    return om.writeValueAsString(user);
}
```

**解决中文乱码：**`@RequestMapping(value = "/json", produces = "application/json;charset=utf-8")`

（👆此方法过于繁琐，一般是直接在**springmvc.xml**配置文件中配置👇）

```xml
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8"/>
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```



#### 时间戳

常规返回时间代码：

```java
@RequestMapping("/json")
public String jsonTest() throws JsonProcessingException {
    Date date = new Date();
    ObjectMapper om = new ObjectMapper();
    return om.writeValueAsString(date);
}
```

👆 返回的是时间戳，需要格式化

```java
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String format = sdf.format(date);
```



**使用Jackson中ObjectMapper来格式化时间完成输出**

由于objectMapper中默认返回的时间戳，需要关闭默认返回时间戳的配置

```java
ObjectMapper om = new ObjectMapper();
om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
```

**自己设置返回的时间格式**

```java
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
ObjectMapper om = new ObjectMapper();
om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
om.setDateFormat(sdf);
```



#### 封装getJson方法

```java
public class JsonUtils {
    public static String getJson(Object object, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        om.setDateFormat(sdf);
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

👆 需要自定义时间格式的getJson，使用默认自定义格式的getJson 👇

```java
public static String getJson(Object object) {
    return getJson(object,"yyyy-MM-dd hh:mm:ss");
}
```





### FastJson

```java
@RequestMapping("/fastJson")
public String fastJason(){
    List<User> users = new ArrayList<>();
    users.add(new User("xxx1",1,"男"));
    users.add(new User("xxx2",2,"男"));
    users.add(new User("xxx3",3,"男"));
    users.add(new User("xxx4",4,"男"));
    users.add(new User("xxx5",5,"男"));
    users.add(new User("xxx6",6,"男"));
    // java数据类型转json字符串
    // list转json
    String jsonStr1 = JSON.toJSONString(users);
    System.out.println("list转json字符串：" + jsonStr1);
    // 对象转json
    String jsonStr2 = JSON.toJSONString(new User("xxx1", 1, "男"));
    System.out.println("对象转json字符串：" + jsonStr2);
    
    // json转java对象
    User user1 = JSON.parseObject(jsonStr2, User.class);
    System.out.println("json转java对象：" + user1);
    
    // java对象转JSON对象
    JSONObject jsonObject = (JSONObject) JSON.toJSON(new User("xxx1", 1, "男"));
    System.out.println("java对象转JSON对象：" + jsonObject);
    
    // json对象转java对象
    User user2 = JSON.toJavaObject(jsonObject, User.class);
    System.out.println("json对象转java对象：" + user2);
    return null;
}
```







# Ajax

搜索框关键字推荐搜索

```jsp
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
```







# 拦截器

编写拦截器配置类，实现 **HandlerInterceptor**

```java
public class MyInterceptor implements HandlerInterceptor {
	...
}
```

AOP思想的具体应用



在**preHandle**中`return true;`

执行下一个拦截器，`return false`则不执行 

```java
public class MyInterceptor implements HandlerInterceptor {
    // 处理前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    // 处理后
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 清理
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
```



**拦截器配置**

```xml
<!-- 拦截器配置-->
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <bean class="com.xxx.config.MyInterceptor" />
    </mvc:interceptor>
</mvc:interceptors>
```





































