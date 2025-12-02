# 事务回顾

**概念：**

事务是一组操作的集合，它是一个不可分割的工作单位

**这些操作要么同时成功，要么同时失败。**



**操作：**

- 开启事务（一组操作开始前，开启事务）：start transaction/begin;
- 提交事务（这组操作全部成功后，提交事务）：commit;
- 回滚事务（中间任何一个操作出现异常，回滚事务）：rollback;



# Spring事务管理

**案例：**

<u>解散部门</u>：删除部门，同时删除该部门下的员工



**完善一：**

```java
{
	xxx.deleteById(id);//根据id删除部门
	xxx.deleteByDeptId(id);//根据部门id删除员工
}
```

**问题：**如果此时，两个操作之间出现问题报错，那么将仅执行删除部门的操作，而不会删除部门下的员工，会造成数据的不一致。

**解决办法：**将两个操作归属到一个事务下面，如果中途报错，则回滚不造成任何影响。

​		实现了要么同时成功，要么同时失败的需求。



**实现：**使用注解**@Transactional**

**位置：**业务（service）层的方法上、类上、接口上

**作用：**将当前方法交给spring进行事务管理，方便执行前开启事务；成功执行完毕提交事务；出现异常回滚事务。

**举例：**

```java
@Transactional
@Override
public void deleteById(Integer id) {
    deptMapper.deleteById(id);
    empMapper.deleteByDeptId(id);
}
```



### spring事务管理日志开关

```yaml
#开启spring事务管理日志
logging:
  level:
    org.springframework.jdbc.support.JdbcTransactionManager: debug
```





# 事务进阶

- rollbackFor（回滚）
- propagation（传播行为）

## 事务属性-回滚（rollbackFor）

**情况分析：**如果在添加事务注解的方法内，执行的两个操作之间，**主动**抛出一个异常，那么就会回滚失败。

```java
@Transactional
@Override
public void deleteById(Integer id) {
    deptMapper.deleteById(id);
    if(true){//主动抛出异常
        throw new Exception("出错啦");
    }
    empMapper.deleteByDeptId(id);
}
```

这是因为在**默认情况**下只有出现<u>RuntimeException</u>异常才会回滚，即**运行时异常**

例如在两个操作之间定义一个`int i = 1/0;`即运行时异常

**rollbackFor**属性用于控制出现何种异常类型的时候，回滚事务。



### 使用方法

`@Transactional(rollbackFor = Exception.class)`

意为：出现所有的异常，都要执行回滚事务





## 事务属性-传播行为（propagation）

事务传播行为：指的就是当一个事务方法被另一个事务方法调用时，这个是事务方法应该如何进行事务控制

**例如：**

```java
@Translational
public void a(){
	...
	xxx.b();
}


@Translational
public void b(){
	...
}
```

|    属性值     |                             含义                             |
| :-----------: | :----------------------------------------------------------: |
|   REQUIRED    |         【默认值】需要事务，有则加入，无则创建新事务         |
| REQUIRES_NEW  |             需要新事务，无论有无，总是创建新事务             |
|   SUPPORTS    |          支持事务，有则加入，无则在无是无状态中运行          |
| NOT_SUPPORTED | 不支持事务，在无事务状态下运行，如果当前存在已有事务，则挂起当前事务 |
|   MANDATORY   |                    必须有事务，否则抛异常                    |
|     NEVER     |                    必须没事务，否则抛异常                    |
|      ...      |                             ...                              |

### 使用方法

`@Transactional(propagation = Propagation.REQUIRED)`



### 案例：解散部门时，记录操作日志

**需求：**解散部门时，无论是成功还是失败，都要记录操作日志

**步骤：**

- 解散部门：删除部门、删除部门下的员工
- 记录日志到数据库表中

**情景一：**

```java
@Translational
public void xxx{
    try{
		报错
	}finally{
		执行一个已有事务的方法（默认传播行为）：记录日志
	}
}
```

此时不会记录日志，因为报错了，要回滚全部程序，因为已有事务的方法使用的是**默认传播行为**，因此将**加入到已有的事务**，即当前调用此方法的方法（xxx）的事务。



**想要实现的需求：**

无论成功或者失败都要记录日志

因此要给**记录日志的方法**，设置其事务传播行为为自身为一个新事务，**不加入**任何事务

`@Transactional(propagation = Propagation.REQUIRES_NEW)`

```java
事务方法1{
	事务方法2（设置为REQUIRES_NEW）
}
```

当执行到事务方法2的时候，因为属性值为REQUIRES_NEW，会创建新的事务，

所以会挂起事务方法1的事务，直到事务方法2的事务执行完毕才会继续

**总结：**只会运行一个事务，有新挂老

![image-20230721232228430](D:\File\markdownPictures\image-20230721232228430.png)









# AOP基础

## AOP概述

- **AOP: ** **A**spect **O**riented **P**rogramming(面向切面编程、面向方面编程)

  ​	  其实就是面向特定方法编程。

- **场景：**

  案例部分功能运行较慢，定位执行耗时较长的业务方法，此时需要统计每一个业务方法的执行耗时

  ![image-20230724220651135](D:\File\markdownPictures\image-20230724220651135.png)

  ![image-20230724221003928](D:\File\markdownPictures\image-20230724221003928.png)

- **实现：**

  动态代理是面向切面编程最主流的实现。而SpringAOP是Spring框架的高级技术，旨在管理bean对象的过程中，主要通过底层的动态代理机制，对特定的方法进行编程。



## AOP快速入门、

### 步骤

<u>Spring AOP快速入门：</u>**统计各个业务层方法执行耗时**

- 导入依赖：pom.xml中导入AOP依赖

  ```xml
  <!-- AOP-->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-aop</artifactId>
  </dependency>
  ```

- 编写AOP程序：针对**<u>特定方法</u>**根据业务需要进行编程

  ```java
  @Component//交给IOC容器管理
  @Aspect//表示当前类不再是一个普通类，而是一个AOP类
  public class TimeAspect{
      @Around("execution(* com.tlias.tliaswebmanagement.service.*.*(..))")//切入点表达式，用于指定对哪些特定方法执行此操作
  	//编写模板
      public Object recordTime
              (ProceedingJoinPoint proceedingJoinPoint)
              throws Throwable {
          long begin = System.currentTimeMillis();
          Object object = proceedingJoinPoint.proceed();//调用原始方法运行
          long end = System.currentTimeMillis();
          log.info(proceedingJoinPoint.getSignature()
                  + "执行耗时：{}ms", end - begin);
          return object;
      }
  }
  ```

  **getSignature：** 拿到方法签名



## 场景

**面向切面编程，可以在不影响原来代码的情况下完成所需要的操作**

- 记录操作日志
- 权限控制
- 事务管理
- ...



## 优势

- 代码无侵入
- 减少重复代码
- 提高开发效率
- 维护方便





# AOP核心概念

- **连接点：**JoinPoint，可以被AOP控制的方法（暗含方法执行时的相关信息）
- **通知：**Advice，指哪些重复的逻辑，也就是**共性功能**（最终体现为一个方法）
- **切入点：**PointCut，匹配连接点的条件，通知仅会在切入点方法执行时被应用
- **@Around：**该注解表示是环绕使用通知方法的，即在连接点执行前后执行
- **切面：**Aspect，描通知与切入点的对应关系（通知+切入点）
- **@Aspect：**标注了该注解的类，称之为切面类
- **目标对象：**Target，通知所应用的对象，即**切入点表达式所对应的对象**

![image-20230725195404198](D:\File\markdownPictures\image-20230725195404198.png)



![image-20230725195803525](D:\File\markdownPictures\image-20230725195803525.png)



![image-20230725200353961](D:\File\markdownPictures\image-20230725200353961.png)

**注意事项：**

如果只需要对**一个方法**进行切入，则切入点可以写成：

`execution(* com.xxx.service.impl.xxxImpl.xxx())`

![image-20230725200829958](D:\File\markdownPictures\image-20230725200829958.png)

![image-20230725200911416](D:\File\markdownPictures\image-20230725200911416.png)





## AOP执行流程

![image-20230725201124288](D:\File\markdownPictures\image-20230725201124288.png)







# AOP进阶

## 通知类型

@Around:环绕通知，此注解标注的通知方法在目标方法前、后都被执行

@Before:前置通知，此注解标注的通知方法在目标方法前被执行

@After：后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行

@AfterReturning：返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行

@AfterThrowing:异常后通知，此注解标注的通知方法发生异常后执行



**注意事项：一个小问题**

定义多个通知，且应用于同一切点，写多个一摸一样的切点表达式过于繁琐

**解决办法：**

使用@Pointcut进行切点表达式的抽取

**例子：**

```java
@Pointcut("execution(* com.tlias.tliaswebmanagement.service.*.*(..))")
private void pt(){}

@Around("pt()")
public void around(){
	...
}
```

**ps：**也可以在别的切面类中使用此处的pt方法，只需要将pt方法改为public，且使用包名进行引用



### @Pointcut

- 该注解的作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。
- **举例在上面**



## 通知顺序

当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知方法都会被执行。



**执行顺序**

1. 不同切面类中，默认按照切面类的**类名字母排序**：

   - 目标方法前的通知方法：字母靠前**先**执行
   - 目标方法后的通知方法：字母靠前**后**执行

2. 用**@Order(数字)**加载切面类上来控制顺序

   - 目标方法前的通知方法：数字小**先**执行
   - 目标方法后的通知方法：数字小**后**执行

   ```java
   @Aspect
   @Order(2)
   @Component
   public class MyAspect2{
   	...
   }
   ```





## 切入点表达式

- 切入点表达式：描述切入点方法的一种表达式
- 作用：主要用来决定项目中的哪些方法需要加入通知
- 常见形式：
  1. `execution()`:根据方法的签名来匹配
  2. `@annotation(...)`:根据注解匹配

```java
@Before("execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer))")
public void before(Integer joinPoint){}
```

```java
@Before("@annotation(com.xxx.anno.Log)")
public void before(){}
```



### execution

execution主要根据方法的返回值、包名、类名、方法名、方法参数等信息来匹配，语法为：

> execution(<u>访问修饰符?</u> <u>返回值</u> <u>包名.类名.?方法名(方法参数)</u> <u>throws 异常?</u>)



- 其中带 **?** 的表示可以省略的部分

  - 访问修饰符：可省略（比如：public、protected)
  - 包名.类名：可省略**（不建议省略）**
  - throws异常：可省略（注意是方法上声明抛出的异常，不是实际抛出的异常）

  ```java
  @Before("execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer))")
  public void before(Integer joinPoint){}
  ```



**通配符**

- 可以使用通配符描述切入点

  - *****：单个独立的任意符号，可以通配任意返回值、包名、类名、方法名、任意类型的一个参数，也可以通配包、类、方法名的一部分

    > `execution(* com.*.service.*.update*(*))`
    >
    > **解析**
    >
    > - 第一个*：返回值
    > - 第二个*：任意包名
    > - 第三个*：任意类名
    > - 第四个*：update开头的方法名
    > - 第五个*：有且仅有一个任意参数

  - **..**：多个连续的任意符号，可以通配任意层级的包，或任意类型、任意个数的参数

    > `execution(* com.xxx..DeptService.*(..))`



**如何指定多个方法：**

```java
@Before("execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer)) || execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer)) ")
public void before(Integer joinPoint){}
```



**注意事项：**

根据业务需要，可以使用 且（&&）、或（||）、非（！）来组合比较复杂的切入点表达式



**书写建议：**

- 所有业务**方法名**在**命名**时尽量**规范**，方便切入点表达式快速匹配。

  > 如：查询类方法都是find开头，更新类方法都是update开头。

- 描述切入点方法通常**基于接口描述**，而不是直接描述实现类，**增强拓展性**。

- 在满足业务需要的前提下，**尽量缩小切入点的匹配范围**。

  > 如：包名匹配尽量不使用`..`，使用`*`匹配单个包。





### @annotation自定义注解

- @annotation切入点表达式，用于匹配标识有特定注解的方法。

  > `@annotation(com.xxx.anno.Log)`

```java
@Before("@annotation(com.itheima.anno.Log)")
public void before(){
	log.info("before ....")
}
```



**基于annotation简化复杂切面表达式**

**原方法：**

```java
@Pointcut("execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer)) || execution(public void com.xxx.service.impl.xxxServiceImpl.xxx(java.lang.Integer)) ")
private void pt(){}

@Before("pt()")
public void before(){}
```



**新方法：**

1. 自定义注解，在包下创建自定义注解<img src="D:\File\markdownPictures\image-20230802001512025.png" alt="image-20230802001512025" style="zoom: 50%;" />

2. 在创建的注解上加上两个元注解来描述当前注解

   `@Retention(RetentionPolicy.RUNTIME)`-描述该注解什么时候生效（当前为运行时生效）

   `@Target(ElementType.METHOD)`-表示当前注解可以在哪些地方生效（当前在方法中生效）

3. 实际使用

   ```java
   @Service
   public class DeptServiceImpl implements DeptService {
       @Autowired
       private DeptMapper deptMapper;
   
       @MyLog
       @Override
       public List<Dept> list() {
           return deptMapper.list();
       }
   }
   ```

   ```java
   @Pointcut("@annotation(com.xxx.xxx.MyLog)")
   private void pt(){}
   
   @Before("pt()")
   public void before(){}
   ```

   此时，哪里使用了`@MyLog`注解，哪里就被写入了切入点表达式





## 连接点

- 在Spring中用**JoinPoint**抽象了连接点，用它可以获得方法执行时的相关信息，如目标类名、方法名、方法参数等。
  - 对于@Around通知，获取连接点信息只能使用ProceedingJoinPoint
  - 对于其他四种通知，获取连接点信息只能使用JoinPoint,它是ProceedingJoinPoint的父类型









































































