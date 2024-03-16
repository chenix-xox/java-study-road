# 多线程

## 基本概念：程序、进程、线程

**程序(program)**是为完成特定任务、用某种语言编写的一组指令的集合。即指一段静态的代码，静态对象。

**进程(process)**是程序的一次执行过程，或是正在运行的一个程序。是一个动态的过程：有它自身的产生、存在和消亡的过程。——生命周期

- 如：运行中的QQ，运行中的MP3播放器

- 程序是静态的，进程是动态的

- 进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域

**线程(thread)**，进程可进一步细化为线程，是一个程序内部的一条执行路径。

- 若一个进程同一时间并行执行多个线程，就是支持多线程的

- 线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器(pc)，线程切换的开销小 

- 一个进程中的多个线程共享相同的内存单元/内存地址空间它们从同一堆中分配对象，可以访问相同的变量和对象。这就使得线程间通信更简便、高效。但多个线程操作共享的系统资源可能就会带来安全的隐患。



**单核CPU和多核CPU的理解**

- 单核CPU，其实是一种假的多线程，因为在一个时间单元内，也只能执行一个线程的任务。例如：虽然有多车道，但是收费站只有一个工作人员在收费，只有收了费才能通过，那么CPU就好比收费人员。如果有某个人不想交钱，那么收费人员可以把他“挂起”（晾着他，等他想通了，准备好了钱，再去收费）。但是因为CPU时间单元特别短，因此感觉不出来。

- 如果是多核的话，才能更好的发挥多线程的效率。（现在的服务器都是多核的）

- 一个Java应用程序java.exe，其实至少有三个线程：main()主线程，gc()垃圾回收线程，异常处理线程。当然如果发生异常，会影响主线程。 

**并行与并发**

-  **并行：**多个CPU同时执行多个任务。比如：多个人同时做不同的事。 

-  **并发：**一个CPU(采用时间片)同时执行多个任务。比如：秒杀、多个人做同一件事。

## 线程的创建和使用

- Java语言的JVM允许程序运行多个线程，它通过java.lang.Thread类来体现

- Thread类的特性
  - 每个线程都是通过某个特定Thread对象的run()方法来完成操作的，经常把run()方法的主体称为**线程体**
  - 通过该Thread对象的start()方法来启动这个线程，而非直接调用run()

### Thread类的有关方法

void start()：启动线程，并执行对象的run()方法

run()：线程在被调度时执行的操作

String getName()：返回线程的名称

void setName(String name)：设置线程名称

static Thread currentThread()：返回当前线程。在Thread子类中就是this，通常用于主线程和Runnable实现类

### 线程的调度

[![Ikxnl6.png](https://z3.ax1x.com/2021/11/03/Ikxnl6.png)](https://imgtu.com/i/Ikxnl6)

#### 线程的优先级

##### 线程的优先级等级

MAX_PRIORITY：10

MIN_PRIORITY：1

NORM_PRIORITY：5

##### 涉及的方法

> get Priority()：返回线程优先值
>
> set Priority(int newpriority)：改变线程的优先级

##### 说明

线程创建时继承父线程的优先级

低优先级只是获得调度的概率低，并非一定是在高优先级线程之后才被调用

### 每日一考

**1.谈谈你对程序、进程、线程的理解**



**2.代码完成继承Thread的方式创建分线程，并遍历100以内的自然数**



## 线程的生命周期

### JDK中用Thread.State类定义了线程的几种状态

要想实现多线程，必须在主线程中刚创建新的线程对象。Java语言使用Thread类及其子类的对象来表示线程，在它的一个完整的生命周期中通常要经历五个状态：

新建	——》	就绪	——》	运行	——》	阻塞	——》	死亡

![image-20211104203431649](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211104203431649.png)

## 线程的同步

### 线程的死锁问题

#### 死锁

- 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁

- 出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续。

#### 解决方法

- 专门的算法、原则
- 尽量减少同步资源的定义
- 尽量避免嵌套同步

### Lock（锁）

从JDK5.0开始，Java提供了更强大的线程同步机制——通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。

### synchronized与Lock的对比

1.Lock是显式锁（手动开启和关闭锁，别忘记关闭锁），synchronized是隐式锁，出了作用域自动释放

2.Lock只有代码块锁，synchronized有代码块锁和方法锁

3.使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的拓展性（提供更多的子类）

#### **优先使用顺序：**

Lock —> 同步代码块（已经进入了方法体，分配了相应资源）—>同步方法（在方法体之外）

## 线程的通信



## JDK5.0新增线程创建方式

### 新增方式一：实现Callable接口

- 与使用Runnable相比，Callable功能更强大些

> 相比run()方法，可以有返回值
>
> 方法可以抛出异常
>
> 支持泛型的返回值
>
> 需要借助FutureTask类，比如获取返回结果

- **Future接口**

  > 可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
  >
  > FutureTask是Future接口的唯一的实现类
  >
  > FutureTask 同时实现了Runnable, Future接口。它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值

### 新增方式二：使用线程池

- **背景：**经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
- **思路：**提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
- **好处：**

> 提高响应速度（减少了创建新线程的时间）
>
> 降低资源消耗（重复利用线程池中线程，不需要每次都创建）
>
> 便于线程管理
>
> > corePoolSize：核心池的大小
> >
> > maximumPoolSize：最大线程数 
> >
> > keepAliveTime：线程没有任务时最多保持多长时间后会终止
> >
> > ...

## 每日一考

**2.同步代码块中涉及到的同步监视器和共享数据，谈谈你对同步监视器和共享数据的理解，以及注意点。**

同步监视器（synchronized）：

```java
synchronized(同步监视器){
	//操作共享数据的代码（不能包括多了，也不能包括少了）
}
```

**3.sleep()和wait()的区别**

**4.写一个线程安全的懒汉式**

**5.创建多线程有哪几种方式？4种**

继承Thread类

实现Runnable接口

实现Callable接口

线程池（响应速度提高了，提高了资源的重用率，便于管理）

## 复习：

#### 说明：

1.生命周期关注两个概念：状态、相应的方法

2.关注：状态a ——》状态b：哪些方法执行了（回调方法）

​			某个方法主动调用：状态a ——》状态b

3.阻塞：临时状态，不可以作为最终状态

   死亡：最终状态

#### 1.背景

例子：创建个窗口卖票，总票数为100张。使用实现Runnable接口的方式

#### 2.Java解决方案：同步机制

### 线程通信

#### 1.线程通信涉及到的三个方法：

wait()	一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器

notify()	一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个

notifyAll()	一旦执行此方法，就会唤醒所有被wait的线程

# 常用类

## 字符串相关的类

### String的特性

#### String对象的创建

String：字符串，使用一对""引起来表示

1. String声明为final的，不可被继承

2. String实现了Serializable接口：表示字符串是支持序列化的。
           实现了Comparable接口：表示String可以比较大小

3. String内部定义了`final char[] value`用于存储字符串数据

4. String：代表不可变的字符序列。简称：**不可变性。**
   体现：

   1. 当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值

   2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值

   3. 当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值

      ```java
      @Test
      public void test1(){
          String s4 = "abc";
          String s5 = s4.replace('a','b');
          System.out.println(s5);//bbc
      }
      ```

      

5. 通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中

6. 字符串常量池中是不会存储相同内容（使用String类的equals()比较，返回true）的字符串的

```java
String str = "hello";

//本质上this.value = new char[0];
String s1 = new String();

//this.value = original.value;
String s2 = new String(String original);

//this.value = Arrays.copyOf(value,value.length);
String s3 = new String(char[] a);

String s4 = new String(char[] a,int startIndex,int count);
```

#### Heap堆

一个JVM实例只存在一个堆内存，堆内存的大小是可以调节的。类加载器读取了类未见后，需要把类、方法、常变量放到堆内存中，保存所有应用类型的真实信息，以方便执行器执行，堆内存分为三部分：

- Young Generation Space 新生区		Young
- Tenure generation space 养老区        Old
- Permanent Space 永久存储区             Perm 

#### String的实例化方式：

方式一：通过字面量定义的方式
方式二：通过new + 构造器的方式
    面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            两个：一个是堆空间new结构，另一个是char[]对应的常量池中的数据：“abc”

```java
@Test
public void test2(){
    //通过字面量定义的方式：此时s1和s2的数据javaEE声明在方法区中的字符串常量池中
    String s1 = "javaEE";
    String s2 = "javaEE";
    //通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值
    String s3 = new String("javaEE");
    String s4 = new String("javaEE");
    System.out.println(s1 == s2);//true
    System.out.println(s1 == s3);//false
    System.out.println(s1 == s4);//false
    System.out.println(s3 == s4);//false
    System.out.println("************************************");
    Person p1 = new Person("Tom",12);
    Person p2 = new Person("Tom",12);
    System.out.println(p1.name.equals(p2.name));//true
    System.out.println(p1.name == p2.name);//true
}
```

**结论**
   1.常量与常量的拼接结果在常量池。且常量池不会存在相同内容的常量
   2.只要其中一个是变量，结果就在堆中
   3.如果拼接的结果调用intern()方法，返回值就在常量池中

```java
@Test
public void test3(){
    String s1 = "javaEE";
    String s2 = "hadoop";
    String s3 = "javaEEhadoop";
    String s4 = "javaEE" + "hadoop";
    String s5 = s1 + "hadoop";
    String s6 = "javaEE" + s2;
    String s7 = s1 + s2;
    System.out.println(s3 == s4);//true
    System.out.println(s3 == s5);//false
    System.out.println(s3 == s6);//false
    System.out.println(s3 == s7);//false
    System.out.println(s5 == s6);//false
    System.out.println(s5 == s7);//false
    System.out.println(s6 == s7);//false
    String s8 = s5.intern();//返回值得到的s8使用的常量值中已经存在的“javaEEhadoop”
    System.out.println(s8 == s3);//true
}
```

```java
@Test
public void test4(){
    String s1 = "javaEEhadoop";
    String s2 = "javaEE";
    String s3 = s2 + "hadoop";
    System.out.println(s1 == s3);
    final String s4 = "javaEE";
    String s5 = s4 + "hadoop";
    System.out.println(s5 == s1);
}
```

#### 常见方法

> int length()：返回字符串的长度： return value.length
> char charAt(int index)： 返回某索引处的字符return value[index]
> boolean isEmpty()：判断是否是空字符串：return value.length == 0
> String toLowerCase()：使用默认语言环境，将 String 中的所有字符转换为小写
> String toUpperCase()：使用默认语言环境，将 String 中的所有字符转换为大写
> String trim()：返回字符串的副本，忽略前导空白和尾部空白
> boolean equals(Object obj)：比较字符串的内容是否相同
> boolean equalsIgnoreCase(String anotherString)：与equals方法类似，忽略大小写
> String concat(String str)：将指定字符串连接到此字符串的结尾。 等价于用“+”
> int compareTo(String anotherString)：比较两个字符串的大小
> String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。
> String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串

> boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
> boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
> boolean startsWith(String prefix, int toffset)：测试此字符串从指定索引开始的子字符串是否以指定前缀开始
> boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true
> int indexOf(String str)：返回指定子字符串在此字符串中第一次出现处的索引
> int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
> int lastIndexOf(String str)：返回指定子字符串在此字符串中最右边出现处的索引
> int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索
> 注：indexOf和lastIndexOf方法如果未找到都是返回-1

> 替换：
> String replace(char oldChar, char newChar)：
> 返回一个新的字符串，它是通过用newChar替换此字符串中出现的所有oldChar得到的。
> String replace(CharSequence target, CharSequence replacement)：
> 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
> String replaceAll(String regex, String replacement) ：
> 使用给定的replacement替换此字符串所有匹配给定的正则表达式的子字符串。
> String replaceFirst(String regex, String replacement) ：
> 使用给定的replacement替换此字符串匹配给定的正则表达式的第一个子字符串。
> 匹配：
> boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。
> 切片：
> String[] split(String regex)：根据给定正则表达式的匹配拆分此字符串。
> String[] split(String regex, int limit)：
> 根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。

#### 复习

**1.String 与基本数据类型、包装类之间的转换。**
**String --> 基本数据类型、包装类：调用包装类的静态方法：parseXxx(str);**
**基本数据类型、包装类 --> String：调用String重载的valueOf(xxx)**

```java
@Test
public void test1(){
  String str1 = "123";
//  int num = (int)str1;//错误的
  int num = Integer.parseInt(str1);
  System.out.println(num);
  String str2 = String.valueOf(num);
  System.out.println(str2);
}
```

**2. String 与 char[]之间的转换**

**String --> char[]：调用String的toCharArray()**

**char[] --> String：调用String的构造器**

```java
@Test
public void test2(){
  String str1 = "abc123";//题目：a21cb3
  char[] charArray = str1.toCharArray();
  for (int i = 0;i < charArray.length;i++){
      System.out.println(charArray[i]);
  }
  char[] arr = new char[]{'h','e','l','l','o'};
  String str2 = new String(arr);
  System.out.println(str2);
}
```

**3.**

**byte:字节**

**String 与 byte[]之间的转换**

**编码：String --> byte[]：调用String的getBytes()**

**解码：byte[] --> String：调用String的构造器**

**编码：字符串 --> 字节 （看得懂-->看不懂的二进制数据）**

**解码：编码的逆过程，字节 --> 字符串（看不懂的二进制数据 --> 看得懂）**

**说明：编码时，要求解码使用的字符集必须与编码时使用的字符集一致，否则会出现乱码。**

```java
@Test
public void test3() throws UnsupportedEncodingException {
  String str1 = "abc123中国";
  byte[] bytes = str1.getBytes();//使用默认的字符集，进行转换。默认=utf-8
  System.out.println(Arrays.toString(bytes));
  byte[] gbks = str1.getBytes("gbk");//使用gbk字符集进行编码
  System.out.println(Arrays.toString(gbks));
  System.out.println("****************************************");
  String str2 = new String(bytes);//使用默认的字符集，进行解码。
  System.out.println(str2);//abc123中国
  String str3 = new String(gbks);
  System.out.println(str3);//abc123�й�,出现乱码。原因：编码集和解码集不一致！
  String str4 = new String(gbks,"gbk");
  System.out.println(str4);//没有出现乱码。原因：编码集和解码集一致！
}
```



### 常见算法题目

**1.模拟一个trim方法，去除字符串两端的空格**



**2.将一个字符串进行反转。将字符串中指定部分进行反转。比如"ab<u>cdef</u>g"反转为"ab<u>fedc</u>g"**



**3.获取一个字符串在另一个字符串中出现的次数**

比如：获取”ab“在”abkkcadkabkebfkabkskab“中出现的次数



**4.获取两个字符串中最大相同子串。比如：**

str1 = "abcwerthelloyuiodef";str2 = "cvhellobnm"

提示：将短的那个串进行长度依次递减的子串与较长的串比较。



**5.对字符串中字符进行自然顺序排序**

提示：

1）字符串变成字符数组

2）对数组排序，选择，冒泡，Arrays.sort();

3）将排序后的数组变成字符串

## JDK8之前日期事件API

### 1、java.lang.System类

System类提供的`public static long currentTimeMillis()`用来返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差

> 此方法适用于计算时间差

- 计算世界时间的主要标准有：
  - UTC
  - GMT
  - CST

### 2、java.util.Date类

表示特定的瞬间，精确到毫秒

- 构造器
  - Date()：使用无参构造器创建的对象可以获取本地当前时间
  - Date(long date)
- 常用方法
  - getTime()：返回自1970年1月1日00：00：00GMT以来此Date对象表示的毫秒数。
  - toString()：把此Date对象转换为以下的形式String：dow mon dd hh:mm:ss zzz yyyy其中：dow是一周中的某一天（Sun，Mon，Tue，Wed，Thu，Fri，Sat），zzz是时间标准
  - 其他很多方法都过时了

### 3、java.text.SimpleDateFormat类

- Date类的API不易于国际化，大部分被废弃了，java.text.SimpleDateFormat类是一个不与语言环境有关的方式来格式化和解析日期的具体类
- 它允许进行格式化：日期 —> 文本、解析：文本 —> 日期
- 格式化：
  - SimpleDateFormat()：默认的模式和语言环境创建对象
  - public SimpleDateFormat(String pattern)：该构造方法可以用参数pattern指定的格式创建一个对象，该对象调用：
  - public String format(Date date)：方法格式化时间对象date
- 解析
  - public Date parse(String  source)：从给定字符串的开始解析文本，以生成一个日期

### 4、java.util.Calendar(日历)类

- Calendar是一个抽象基类，主用用于完成日期字段之间相互操作的功能。

- 获取Calendar实例的方法
  - 使用**Calendar.getInstance()**方法
  - 调用它的子类**GregorianCalendar**的构造器。

- 一个Calendar的实例是系统时间的抽象表示，通过get(int field)方法来取得想要的时间信息。比如YEAR、MONTH、DAY_OF_WEEK、HOUR_OF_DAY 、MINUTE、SECOND
  - **public void set(int field,int value)**
  - **public void add(int field,int amount)**
  - **public final Date getTime()**
  - **public final void setTime(Date date)** 

- 注意：
  - 获取月份时：一月是0，二月是1，以此类推，12月是11
  - 获取星期时：周日是1，周二是2 ，... ... 周六是7

## 每日一考

**2.如何理解String类的不可变性**



**3.String类是否可以被继承？为什么？**

 **String s = new String("hello");在内存中创建了几个对象？请说明**

不能，声明为final

**4.String，StringBuffer，StringBuilder三者的对比**



**5.String的常用方法有哪些？（至少7个）**

> ```java
> length() / charAt() / equals() / compareTo() / startsWith() / endsWith() / contains() / indexOf() / lastIndexOf() / getBytes() / toCharArray() / valueOf() / ...
> ```

## JDK8中新日期时间API

### 新时间API出现的背景

​		如果我们可以跟别人说：“我们在1502643933071见面，别晚了！”那么就再简单不过了。但是我们希望时间与昼夜和四季有关，于是事情就变复杂了。JDK 1.0中包含了一个java.util.Date类，但是它的大多数方法已经在JDK 1.1引入Calendar类之后被弃用了。而Calendar并不比Date好多少。它们面临的问题是：

**可变性：**像日期和时间这样的类应该是不可变的。

**偏移性：**Date中的年份是从1900开始的，而月份都从0开始。

**格式化：**格式化只对Date有用，Calendar则不行。

此外，它们也不是线程安全的；不能处理闰秒等。

**总结：**对日期和时间的操作一直是Java程序员最痛苦的地方之一。

### 新时间日期API

- 第三次引入的API是成功的，并且Java 8中引入的java.time API 已经纠正了过去的缺陷，将来很长一段时间内它都会为我们服务。
- Java 8 吸收了 Joda-Time 的精华，以一个新的开始为 Java 创建优秀的 API。新的 java.time 中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）、本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类。历史悠久的 Date 类新增了 toInstant() 方法，用于把 Date 转换成新的表示形式。这些新增的本地化时间日期 API 大大简化了日期时间和本地化的管理。

### 瞬时：Instant

- Instant：时间线上的一个瞬时点。 这可能被用来记录应用程序中的事件时间戳。

- 在处理时间和日期的时候，我们通常会想到年,月,日,时,分,秒。然而，这只是时间的一个模型，是面向人类的。第二种通用模型是面向机器的，或者说是连续的。在此模型中，时间线中的一个点表示为一个很大的数，这有利于计算机处理。在UNIX中，这个数从1970年开始，以秒为的单位；同样的，在Java中，也是从1970年开始，但以毫秒为单位
-  java.time包通过值类型Instant提供机器视图，不提供处理人类意义上的时间单位。Instant表示时间线上的一点，而不需要任何上下文信息，例如，时区。概念上讲，它只是简单的表示自1970年1月1日0时0分0秒（UTC）开始的秒数。因为java.time包是基于纳秒计算的，所以Instant的精度可以达到纳秒级。
- (1 ns = 10（－9次方） s) 1秒 = 1000毫秒 =10^6微秒=10^9纳秒

### 其他API

- **ZoneId**：	该类中包含了所有的时区信息，一个时区的ID，如 Europe/Paris

- **ZonedDateTime**：一个在ISO-8601日历系统时区的日期时间，如 2007-12-03T10:15:30+01:00 Europe/Paris。 
  - 其中每个时区都对应着ID，地区ID都为“{区域}/{城市}”的格式，例如：Asia/Shanghai等 

- **Clock**：使用时区提供对当前即时、日期和时间的访问的时钟。 

- 持续时间：**Duration**，用于计算两个“时间”间隔

- 日期间隔：**Period**，用于计算两个“日期”间隔

- **TemporalAdjuster :** 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。
- **TemporalAdjusters :** 该类通过静态方法(firstDayOfXxx()/lastDayOfXxx()/nextXxx())提供了大量的常用TemporalAdjuster 的实现。

## Java比较器

- 在Java中经常会涉及到对象数组的排序问题，那么就涉及到对象之间的比较问题
- Java实现对象排序的方式有两种：
  - 自然排序：java.lang.Comparable
  - 定制排序：java.util.Comparator

### 方式一：自然排序：java.lang.Comparable

**Comparable接口的使用举例：  自然排序**

1. 像String、包装类等实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小的方式
2. 像String、包装类重写compareTo()方法以后，进行了从小到大的排列
3. **重写compareTo(obj)的规则：**
   如果当前对象this大 于形参对象obj，则返回正整数，
   如果当前对象this小于形参对象obj，则返回负整数，
   如果当前对象this等于形参对象obj，则返回零。
4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo(obj)方法，在compareTo(obj)方法中指明如何排序

### 方式二：定制排序：java.util.Comparator

## System类

- System类代表系统，系统级的很多属性和控制方法都放置在该类的内部。该类位于java.lang包。 

- 由于该类的构造器是private的，所以无法创建该类的对象，也就是无法实例化该类。其内部的成员变量和成员方法都是static的，所以也可以很方便的进行调用。 

- 成员变量
  
- System类内部包含in、out和err三个成员变量，分别代表标准输入流(键盘输入)，标准输出流(显示器)和标准错误输出流(显示器)。 
  
- 成员方法
  - **native long currentTimeMillis()**：

  该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
  - **void exit(int status)**： 

  该方法的作用是退出程序。其中status的值为0代表正常退出，非零代表异常退出。**使用该方法可以在图形界面编程中实现程序的退出功能**等

  - **void gc()**： 

  该方法的作用是请求系统进行垃圾回收。至于系统是否立刻回收，则取决于系统中垃圾回收算法的实现以及系统执行时的情况。

  - **String getProperty(String key)**：

  该方法的作用是获得系统中属性名为key的属性对应的值。系统中常见的属性名以及属性的作用如下表所示：

  ![image-20211201085357498](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211201085357498.png)

## Math类

java.lang.Math提供了一系列静态方法用于科学计算。其方法的参数和返回值类型一般为double型。

**abs** 		**绝对值**

**acos,asin,atan,cos,sin,tan**		 **三角函数**

**sqrt** 		**平方根**

**pow(double a,doble b) 		a的b次幂**

**log** 		**自然对数**

**exp 	e为底指数**

**max(double a,double b)**

**min(double a,double b)**

**random()** 		**返回0.0到1.0的随机数**

**long round(double a) 		double型数据a转换为long型（四舍五入）**

**toDegrees(double angrad) 		弧度—>角度**

**toRadians(double angdeg) 		角度—>弧度**

## BigInteger与BigDecimal

### BigInteger

![image-20211201090957139](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211201090957139.png)

**常用方法**

public BigInteger **abs**()：返回此 BigInteger 的绝对值的 BigInteger。 

BigInteger **add**(BigInteger val) ：返回其值为 (this + val) 的 BigInteger

BigInteger **subtract**(BigInteger val) ：返回其值为 (this - val) 的 BigInteger

BigInteger **multiply**(BigInteger val) ：返回其值为 (this * val) 的 BigInteger

BigInteger **divide**(BigInteger val) ：返回其值为 (this / val) 的 BigInteger。整数相除只保留整数部分。 

BigInteger **remainder**(BigInteger val) ：返回其值为 (this % val) 的 BigInteger。 

BigInteger[] **divideAndRemainder**(BigInteger val)：返回包含 (this / val) 后跟(this % val) 的两个 BigInteger 的数组。 

BigInteger **pow**(int exponent) ：返回其值为 (thisexponent) 的 BigInteger。

### BigDecimal

- 一般的Float类和Double类可以用来做科学计算或工程计算，但**在商业计算中，要求数字精度比较高，故用到java.math.BigDecimal类。**

- BigDecimal类支持不可变的、任意精度的有符号十进制定点数。 



- **构造器** 

public BigDecimal(double val) 

public BigDecimal(String val)

 

- **常用方法**

public BigDecimal **add**(BigDecimal augend)

public BigDecimal **subtract**(BigDecimal subtrahend)

public BigDecimal **multiply**(BigDecimal multiplicand)

public BigDecimal **divide**(BigDecimal divisor, int scale, int roundingMode)

## 每日一考

**1.将字符串“2017-08-16”转换为对应的java.sql.Date类的对象。（使用JDK8之前或JDK8中的API皆可）**

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

解析：java.util.Date date = sdf.parse("2017-08-16");//然后getTime

DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

new + 构造器

单例、Calendar.getInstance()

**2.解释何为编码？解码？何为日期时间的格式化？解析？**

编码：字符串 —> 字节

解码：字节 —> 字符串

格式化：日期 —> 字符串

解析：字符串 —> 日期

**3.自定义Person类如下，如何实现自然排序（按姓名从小到大排序）**

```java
//代码说明
class Person implements Comparable{
    private String name;
    private int age;

    @Override
    public int compareTo(Object o) {
        //...this.name.compareTo(s.name);从小到大，从大到小加个-
    }
}
```

**4.提供定制排序涉及到的接口的实现类对象，并按Person类的年龄从大到小排序**

```java
Comparator com =  new Comparator(){
	public int compare(Object obj1,Object obj2){
        if(){
            
        }
    }
};
```

**5.JDK8之前和JDK8中日期、时间相关的分类还有哪些？**

 `java.util.Date` 和 `java.sql.Date`	——》	`Instant`

`SimpleDateFormat `	——》	`DateTimeFormatter`

`Calendar`	——》	`LocalDate`、`LocalTime`、`LocalDateTime`

# 枚举类

**主要内容：**

- 如何自定义枚举
- 如何使用关键字enum定义枚举类
- Enum类的主要方法
- 实现接口的枚举类

## 枚举类的使用：入门

- 类的对象只有有限个，确定的。举例如下：

> 星期：Monday(星期一)、......、Sunday(星期天) 
>
> 性别：Man(男)、Woman(女) 
>
> 季节：Spring(春节)......Winter(冬天) 
>
> 支付方式：Cash（现金）、WeChatPay（微信）、Alipay(支付宝)、BankCard(银行卡)、CreditCard(信用卡) 
>
> 就职状态：Busy、Free、Vocation、Dimission
>
> 订单状态：Nonpayment（未付款）、Paid（已付款）、Delivered（已发货）、Return（退货）、Checked（已确认）Fulfilled（已配货）、
>
> 线程状态：创建、就绪、运行、阻塞、死亡

- **当需要定义一组产量时，强烈建议使用枚举类**

## Enum类

### Enum类的主要方法

values()方法：返回枚举类的对象数组。该方法可以很方便地遍历所有地枚举值

valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllgalArgumentException。

toString()：返回当前枚举类对象常量的名称。

## 注解（Annotation）

### 1.理解Annotation

- 从JDK5.0开始，Java增加了对元数据（MetaData）的支持，也就是Annotation（注解）

- Annotation其实就是代码里的特殊标记，这些标记可以编译，类加载，运行时被读取，并执行相应的处理。

  通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。代码分析工

  具、开发工具和部署工具可以通过这些补充信息进行验证或者进行部署

- Annotation可以像修饰符一样被使用，可用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声

  明，这些信息被保存在Annotation的“name=value”当中

- 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解占据了

  更重要的角色，例如用来配置应用程序的的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。

- 未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以上都是基于注解的，Hibernate3.x以后也是

  基于注解的，现在的Struts2有一部分也是基于注解的了，注解是一种趋势，一定程度上可以说：框架 = 注解 

  + 反射 + 设计模式。

### 2.常见的Annotation示例

- 使用 Annotation 时要在其前面增加 @ 符号, 并把该Annotation当成一个修饰符使用。用于修饰它支持的程序元素

- **示例一：生成文档相关的注解**

  ```java
  /**
  @author 标明开发该类模块的作者，多个作者之间使用,分割
  @version 标明该类模块的版本
  @see 参考转向，也就是相关主题
  @since 从哪个版本开始增加的
  @param 对方法中某参数的说明，如果没有参数就不能写
  @return 对方法返回值的说明，如果方法的返回值类型是void就不能写
  @exception 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写
  其中
  @param @return 和 @exception 这三个标记都是只用于方法的。
  @param的格式要求：@param 形参名 形参类型 形参说明
  @return 的格式要求：@return 返回值类型 返回值说明
  @exception的格式要求：@exception 异常类型 异常说明
  @param和@exception可以并列多个
  */
  ```

  

- **示例二：在编译时进行格式检查(JDK内置的三个基本注解)**

  - **@Override:** 限定重写父类方法, 该注解只能用于方法

  - **@Deprecated:** 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择

  - **@SuppressWarnings:** 抑制编译器警告

- **示例三：跟踪代码依赖性，实现替代配置文件功能**
  
  - Servlet3.0提供了注解(annotation),使得不再需要在web.xml文件中进行Servlet的部署

### 3.如何自定义注解：参照@SuppressWarnings定义

 ① 注解声明为：@interface

 ② 内部定义成员，通常使用value表示

 ③ 可以指定成员的默认值，使用default定义

 ④ 如果自定义注解没有成员，表明是一个标识作用。

 如果注解有成员，在使用注解时，需要指明学员的值

 自定义注解必须配上注解的信息处理流程（使用反射）才有意义。

 自定义注解通常都会指明两个元注解：Retention、Target

### 4.jdk提供的4种元注解

元注解：对现有的注解进行结束说明的注解

#### Retention：

指定所修飾的Annotation的生命周期：SOURCE\CLASS（默认行为）\RUNTIME

只有声明为RUNTIME生命周期的注解，才能通过反射获取。

#### Target：

 用于指定被修饰的Annotation能用于修饰哪些结构

({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})

枚举类与注解ppt——>29页
**==================出现频率较低=======================**

#### Documented：

表示所修饰的注解在被javadoc解析时，保留下来。

#### Inherited：

被它修饰的Annotation将具有继承性

### 5.通过反射获取注解信息 --->反射内容时系统讲解

### 6.JDK8中注解的新特性：可重复注解、类型注解

#### 6.1 可重复注解：

①在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class

②MyAnnotation的Target和Retention等元注解与MyAnnotations相同。

#### 6.2 类型注解：

ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。

ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。

## 每日一考

### **1.什么是枚举类？枚举类的对象声明的修饰符都有哪些？**

枚举类：类中的对象的个数是确定的，有限个。

private final （No）

public static final	（Yes）

### **2.什么是元注解？说说Retention和Target元注解的作用**

元注解：对现有的注解进行解释说明的注解。

Retention：指明所修饰的注解的生命周期。SOURCE CLASS RUNTIME

### **3.说说你所理解的集合框架都有哪些接口，存储数据的特点是什么**

参考集合框架（上面）

### **4.比较throw和throws的异同**

**同：**

**throw：**生成一个异常对象，并抛出。使用在方法内部< — >自动抛出异常对象

**throws：**处理异常的方式。使用在方法声明处的末尾< — >try-catch-finally

**“上有排污，下游治污”**

### **5.谈谈你对同步代码块中同步监视器和共享数据的理解及各自要求**

同步监视器：俗称锁。①任何一个类的对象都可以充当锁。②多个线程共用同一把锁

共享数据：多个线程公共操作的数据，即为共享数据。

需要使用同步机制将操作共享数据的代码包起来。不能包多了，也不能包少了。

# 集合

## 集合框架的概述

**1.集合、数组都是对多个数据进行存储操作的结构，简称为Java容器**

​     说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi,数据库中）

**2.1 数组在存储多个数据方面的特点：**

> 一旦初始化以后，其长度就确定了。
>
> 数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了。
>              比如：String[] arr; int[] arr1; Object[] arr2;

**2.2 数组在存储多个数据方面的缺点：**

> 一旦初始化以后，其长度就不可修改。
>
> 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。
>
> 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用。
>
> 数组存储数据的特点：有序、可重复。对于无序、不可重复的需求，不能满足

## 集合框架

**|----Collection接口：**单列集合，用来存储一个一个的对象

​     **|----List接口：**存储有序的、可重复的数据。   -->“动态”数组

​         	|----ArrayList、LinkedList、Vector

​     **|----Set接口：**存储无序的、不可重复的数据。   -->高中讲的“集合”

​         	|----HashSet、LinkedHashSet、TreeSet

**|----Map接口：**双列集合，用来存储一对（key - value）一对的数据    -->高中函数：y = f(x)

​        	 |----HashMap、LinkedHashMap、TreeMap、HashTable、Properties



## Collection接口中方法的使用

```java
@Test
public void test1(){
    Collection coll = new ArrayList();
    //add(Object e)：将元素e添加到集合coll中
    coll.add("AA");
    coll.add("BB");
    coll.add(123);//自动装箱
    coll.add(new Date());
    //size()：获取添加的元素的个数
    System.out.println(coll.size());//4
    //addAll(Collection coll1)：将coll1集合中的元素添加到当前的集合中
    Collection coll1 = new ArrayList();
    coll1.add(456);
    coll1.add("CC");
    coll.addAll(coll1);
    System.out.println(coll.size());//6
    System.out.println(coll);
    //clear()：清空集合元素
    coll.clear();
    //isEmpty()：判断当前集合是否为空
    System.out.println(coll.isEmpty());
}
```

## 集合中的遍历操作

集合元素的遍历操作，使用迭代器Iterator接口

1.**内部的方法：**hasNext() 和 next()

2.集合对象每次调用iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前

3.内部定义了remove()，可以在遍历的时候，删除集合中的元素。此方法不同于集合直接调用remove()

```java
public class IteratorTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry",20));

        Iterator iterator = coll.iterator();
        //方式一：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //报异常：NoSuchElementException
//        System.out.println(iterator.next());

        //方式二：不推荐
//        for (int i = 0;i < coll.size();i++){
//            System.out.println(iterator.next());
//        }

        //方式三：推荐
        //hasNext顾名思义，判断是否还有下一个元素，是否可以next
        while (iterator.hasNext()){
            //next()：①指针下移 ②将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        coll.add(new Person("Jerry",20));

        //错误方式一：NoSuchElementException，间隔输出了，每次指针都在向下移动
//        Iterator iterator = coll.iterator();
//        while (iterator.next() != null){
//            System.out.println(iterator.next());
//        }

        //错误方式二：每次判断都是一个新的迭代器对象，默认游标都在集合的第一个元素之前
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }
    }
}
```

### 测试Iterator中的remove()

```java
@Test
public void test3(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new String("Tom"));
    coll.add(false);
    coll.add(new Person("Jerry",20));

    //删除集合中"Tom"数据
    Iterator iterator = coll.iterator();
    while (iterator.hasNext()){
        Object obj = iterator.next();
        if ("Tom".equals(obj)){
            iterator.remove();
        }
    }

    //遍历集合
    iterator = coll.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
```

### （JDK5.0）新增：ForEach

**jdk5.0新增了foreach循环，用于遍历集、数组**

#### 第一种：for(集合元素的类型 局部变量 : 集合对象)

内部仍然调用了迭代器

```java
@Test
public void test1(){
    Collection coll = new ArrayList();
    coll.add(123);
    coll.add(456);
    coll.add(new String("Tom"));
    coll.add(false);
    coll.add(new Person("Jerry",20));
    
    for (Object obj:coll){
        System.out.println(obj);
    }
}
```

#### 第二种：for(数组元素的类型 局部变量 ： 数组对象)

```java
@Test
public void test2(){
    int[] arr = new int[]{1,2,3,4,5,6};
    for (int i : arr){
        System.out.println(i);
    }
}
```

#### 练习题：

```java
@Test
public void test3(){
    String[] arr= new String[]{"MM","MM","MM"};
    //方式一：普通for赋值
      for (int i = 0;i < arr.length;i++){
          arr[i] = "GG";
      }
    //方式二：增强for循环
    for (String s : arr){
        s = "GG";
    }
    for (int i = 0;i < arr.length;i++){
        System.out.println(arr[i]);
    }
}
```

## List接口

|----Collection接口：单列集合，用来存储一个一个的对象

​    |----List接口：存储有序的、可重复的数据。   -->“动态”数组，替换原有的数组

​        |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储

​        |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储

​        |----Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用Object[]存储

### 2.ArrayList的源码分析：

####      2.1 jdk7情况下

​         ArrayList list = new ArrayList();//底层创建了长度是10的Object[]数组elementData

​         list.add(123);//elementData[0] = new Integer(123);

​         ...

​         list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。

​         默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。

​         结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity)

####      2.2 jdk8中ArrayList的变化：

​         ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}，并没有创建长度为10的数组

​         list.add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数据123添加到elementData[0]伤

​         ...

​         后续的添加和扩容操作与jdk7无异。

####      2.3 小结：

​         jdk7中的ArrayList的对象的创建类似于栏里模式的饿汉式，

​         而jdk8中的ArrayList的对象的创建类似于单例模式的懒汉式，延迟了数组的创建，节省内存。

### 3.LinkedList的源码分析：

​     LinkedList list = new LinkedList();内部声明了Node类型的first和last属性，默认值为null

​     list.add(123);//将123封装到Node中，创建了Node对象。

​     其中，Node定义为：体现了LinkedList的双向链表的说法

```java
private static class Node<E>{
	E item;
	Node<E> next;
	Node<E> prev;
	
	Node(Node<E> prev,E element,Node<E> next){
		this.item = element;
		this.next = next;
		this.prev = prev;
	}
}
```

### 4.Vector的源码分析：

jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组，在扩容方面，默认扩容为原来数组的2倍

**面试题：ArrayList、LinkedList、Vector三者的异同？**

 同：三个类都实现了List接口，存储数据的特点相同：存储有序的、可重复的数据。

 不同：见上

### 5.List接口中的常用方法

void add(int index, Object ele):在index位置插入ele元素

boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来

Object get(int index):获取指定index位置的元素

```java
@Test
public void test1(){
    ArrayList list = new ArrayList();
    list.add(123);
    list.add("AA");
    list.add(456);
    list.add(new Person("Tom",12));

    System.out.println(list);
    list.add(1,"BB");//将BB插入到索引1的位置
    System.out.println(list);

    //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    list.addAll(list1);//如果使用add添加，那么添加进去的是一个数组形式的，长度只会+1，addAll是把每个元素分别加入，长度+list1.size
    System.out.println(list.size());//list1中的元素作为一个个的元素分别插入进了list中
    System.out.println(list);

    //Object get(int index):获取指定index位置的元素
    System.out.println(list.get(1));//获取索引1下面的元素内容
}
```

int indexOf(Object obj):返回obj在集合中首次出现的位置

int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置

Object remove(int index):移除指定index位置的元素，并返回此元素

Object set(int index, Object ele):设置指定index位置的元素为ele

List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex

```java
@Test
public void test2(){
    ArrayList list = new ArrayList();
    list.add(123);
    list.add("AA");
    list.add(456);
    list.add(456);
    list.add(new Person("Tom",12));
    //int indexOf(Object obj):返回obj在集合中首次出现的位置
    System.out.println(list.indexOf(456));

    //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
    System.out.println(list.lastIndexOf(456));

    //Object remove(int index):移除指定index位置的元素，并返回此元素
    System.out.println(list.remove(3));

    //Object set(int index, Object ele):设置指定index位置的元素为ele
    list.set(1,"CC");
    System.out.println(list);

    //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合
    System.out.println(list.subList(2,4));

}
```

#### **总结：常用方法**

增：add(Object obj)

删：remove(int index) / remove(Object obj)

改：set(int index,Object ele)

查：get(int index)

插：add(int index,Object ele)

长度：size()

遍历：

​	 ①Iterator迭代器方式

​     ②增强for循环

​     ③普通的循环

```java
@Test
public void test3(){
    ArrayList list = new ArrayList();
    list.add(123);
    list.add("AA");
    list.add(456);

    //方式一：Iterator迭代器方式
    Iterator iterator = list.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }

    System.out.println("********************");

    //方式二：增强for循环
    for (Object obj : list){
        System.out.println(obj);
    }

    System.out.println("********************");

    //方式三：普通for循环
    for (int i = 0;i < list.size();i++){
        System.out.println(list.get(i));
    }
}
```

### 区分List接口中的两种remove

**区分List中remove(int index)和remove(Object obj)**

```java
@Test
public void testListRemove() {
    List list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    updateList(list);
    System.out.println(list);//
}
private void updateList(List list) {
    list.remove(2);//删除索引2
    list.remove(new Integer(2));//删除元素中的2
}
```

## Set接口

**Set接口的框架：**

​     |----Collection接口：单列集合，用来存储一个一个的对象

​         |----Set接口：存储无序的、不可重复的数据。   -->高中讲的“集合”

​             |----HashSet：作为Set接口的主要实现类：线程不安全的；可以存储null值

​                 |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历

​					<u>对于频繁的遍历操作，LinkedHashSet效率高于HashSet</u>

​             |----TreeSet：可以按照添加对象的指定属性，进行排序。

**1.**Set接口中更没有定义额外的新的方法，使用的都是Collection中声明过的方法。

**2.**要求：向Set中添加的数据，其所在的类一定要重写hashCode()和equals()

  要求：重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列码

​     重写两个方法的小技巧：对象中用作equals()方法比较的Field，都应该计算hashCode值

### 一、Set：存储无序的、不可重复的数据

以HashSet为例说明：

1.无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。

2.不可重复性：保证添加的元素按照equals()判断时，不能返回true。即：相同的元素只能添加一个。

### 二、添加元素的过程：以HashSet为例：

**（1）**我们向HashSet中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，

此哈希值接着通过某种算法计算出再HashSet底层数组中的存放位置（即为：索引位置），

**（2）**判断数组此位置上是否已经有元素：

  如果此位置上没有其他元素，则元素a添加成功。  **--->情况1**

  如果此位置上有其他元素b(或以链表形式存在的多个元素)，则比较元素a与元素b的hash值：

​      如果hash值不相同，则元素a添加成功。    **--->情况2**

​      如果hash值相同，进而需要调用元素a所在类的equals()方法：

​          equals()返回true，元素a添加失败

​          equals()返回false，则元素a添加成功。   **--->情况3**

对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。

jdk7：元素a放到数组中，指向原来的元素。

jdk8：原来的元素在数组中，指向元素a。

**总结：七上八下**

**HashSet底层：数组+链表的结构**

```java
@Test
public void test1(){
    Set set = new HashSet();
    set.add(456);
    set.add(123);
    set.add("AA");
    set.add(new User("Tom",12));
    set.add(new User("Tom",12));
    set.add(129);
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```

### LinkedHashSet的使用

LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，

用于记录前一个数据和后一个数据。

**优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet**

```java
@Test
public void test2(){
    Set set = new LinkedHashSet();
    set.add(456);
    set.add(123);
    set.add("AA");
    set.add(new User("Tom",12));
    set.add(new User("Tom",12));
    set.add(129);
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```

### TreeSet

1.向TreeSet中添加的数据，要求是相同类的对象。

2.两种排序方式：自然排序（实现Comparable接口）和定制排序（Comparator）

3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0。不再是equals()

4.定制排序中，比较两个对象是否相同的标准为：compare()返回0。不再是equals()

#### 自然排序

```java
@Test
public void test1(){
    TreeSet set = new TreeSet();

    //错误的：不能添加不同类的对象
    //set.add(123);
    //set.add(456);
    //set.add("AA");
    //set.add(new User("Tom",12));

    //例子一：Integer类型
    //set.add(123);
    //set.add(13);
    //set.add(-23);
    //set.add(12);

    //例子二：String类型
    //set.add("H");
    //set.add("Li");
    //set.add("Be");
    //set.add("Ne");

    //例子三：自定义类型的对象
    set.add(new User("Tom",12));
    set.add(new User("Jack",15));
    set.add(new User("Mike",32));
    set.add(new User("Jane",21));


    Iterator iterator = set.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
```

#### 定制排序

```java
@Test
public void test2(){
    //com就是定制排序
    Comparator com = new Comparator(){
        @Override
        public int compare(Object o1, Object o2) {
            if(o1 instanceof User && o2 instanceof User){
                User u1 = (User) o1;
                User u2 = (User) o2;
                return Integer.compare(u1.getAge(),u2.getAge());
            }else{
                throw new RuntimeException("输入的数据类型不匹配");
            }
        }
    };
    TreeSet set = new TreeSet(com);
    set.add(new User("Tom",12));
    set.add(new User("Jack",11));
    set.add(new User("Mike",32));
    set.add(new User("Jane",21));
    Iterator iterator = set.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
```

## 每日一考

**1.集合Collection中存储的如果是自定义类的对象，需要自定义类重写哪个方法？为什么？**

equals()方法。

List：equals()方法。	contains()/remove()/retainsAll() ...

Set：（HashSet、LinkedHashSet为例）：equals()、hashCode()

​			（TreeSet为例）：Comparable：compareTo(Object obj)

​											Comparator：compare(Object o1,Object o2)

**2.ArrayList，LinkedList，Vector三者的相同点与不同点？【面试题】**

相同点：都是List接口的实现类，底层都是数组实现的

说明清楚ArrayList和Vector的区别、还有LinkedList和ArrayList的区别

**3.List接口的常用方法有哪些？（增、删、改、查、插、长度、遍历）**

add(Object obj);

remove(Object obj)/remove(int index);

set(int index,Object obj);

get(int index);

add(int index,Object obj);

size();

使用Iterator；foreach；普通的for循环

**4.如何使用Iterator和增强for循环遍历List。举例说明。**



**5.Set存储数据的特点是什么？常见的实现类有什么？说明一下彼此的特点。**

无序的，不可重复的；

**HashSet、LinkedHashSet、TreeSet**

HashMap、LinkedHashMap、TreeMap

## Map接口

### Map实现类的结构

**|----Map：**双列数据，存储key-value对的数据		---类似于高中的函数：y = f(x)

​		**|----HashMap：**作为Map的额主要实现类；线程不安全的，效率高。存储null的key和value

​				**|----LinkedHashMap：**保证再遍历map元素时，可以按照添加的顺序实现遍历。

​							原因：在原有的HashMap底层结构基础上，添加了一堆指针，指向前一个和后一个元素。对于频繁的遍历操作，此类执行效率高于HashMap。

​		**|----TreeMap：**保证按照添加的key-value对进行排序，实现排序遍历。

​									此时考虑key的自然排序和定制排序。

​									底层使用红黑树。	

​		**|----HashTable：**作为古老的实现类；线程安全的，效率低。不能存储null的key和value

​				**|----Properties：**常用来处理配置文件。key和value都是String类型



**HashMap的底层：**数组+链表（jdk7及之前）

​								 数组+链表+红黑树（jdk8）



### Map结构的理解

Map中的key：无序的、不可重复的，使用Set存储所有的key 	---->key所在的类要重写equals()和hashCode()（以HashMap为例）

Map中的value：无序的、可重复的，使用Collection存储所有的value	--->value所在的类要重写equals()

一个键值对：key-value构成了一个Entry对象。

Map中的entry：无序的、不可重复的，使用Set存储所有的entry



### HashMap的底层实现原理？以jdk7为例说明：

HashMap map = new HashMap()：

在实例化以后，底层创建了长度是16的一维数组Entry[] table。

...可能已经执行过多次put...

map.put(key1,value1)：

首先，计算key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置。

如果此位置上的数据为空，此时key1-value1添加成功。----情况1

如果此位置上的数据不为空，[ 意味着此位置上存在一个或多个数据（以链表形式存在）]，比较key1和已经存在的一个或多个数据的哈希值：

​		如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。----情况2

​		如果key1的哈希值和已经存在的某一个数据（key2-value2）的哈希值相同，继续比较：调用key1所在类的equals(key2)方法，比较：

​				如果equals()返回false：此时key1-value1添加成功。----情况3

​				如果equals()返回true：使用value1替换相同key的value值（value2）

**补充：**关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。



在不断的添加过程中，会涉及到扩容问题，当超出临界值（且要存放的位置非空）时，默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。



**jdk8相较于jdk7在底层实现方面的不同：**

1.new HashMap()：底层没有创建一个长度为16的数组

2.jdk8底层的数组是：Node[]，而非Entry[]

3.首次调用put()方法时，底层创建长度为16的数组。

4.jdk7底层结构只有：数组+链表。jdk8中底层结构：数组+链表+红黑树

​	当数组的某一个索引位置上的元素以链表形式存在的个数 > 8，且当前数组的长度 > 64时，

​	此时此索引位置上的所有数据改为使用红黑树存储。



**DEFAULT_INITIAL_CAPACITY :** HashMap的默认容量，16

**DEFAULT_LOAD_FACTOR：** HashMap的默认加载因子：0.75

**threshold：**扩容的临界值，= 容量 * 填充因子 ： 16 * 0.75 => 12

**TREEIFY_THRESHOLD：** Bucket中链表长度大于该默认值，转化为红黑树：8

**MIN_TREEIFY_CAPACITY：**桶中的Node被树化时最小的hash表容量：64



### LinkedHashMap的底层实现原理（了解）

源码中：

```java
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;//能够记录添加的元素的先后顺序
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```



### Map中定义的方法

**添加、删除、修改操作：** 

Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中

void putAll(Map m):将m中的所有key-value对存放到当前map中 

Object remove(Object key)：移除指定key的key-value对，并返回value

void clear()：清空当前map中的所有数据

**元素查询的操作：**

Object get(Object key)：获取指定key对应的value

boolean containsKey(Object key)：是否包含指定的key

boolean containsValue(Object value)：是否包含指定的value

int size()：返回map中key-value对的个数

boolean isEmpty()：判断当前map是否为空

boolean equals(Object obj)：判断当前map和参数对象obj是否相等

**元视图操作的方法：**

Set keySet()：返回所有key构成的Set集合

Collection values()：返回所有value构成的Collection集合

Set entrySet()：返回所有key-value对构成的Set集合

```java
/*
元视图操作的方法：
   Set keySet()：返回所有key构成的Set集合
   Collection values()：返回所有value构成的Collection集合
   Set entrySet()：返回所有key-value对构成的Set集合
*/
@Test
public void test4(){
    Map map = new HashMap();
    map.put("AA",123);
    map.put(43,123);
    map.put("BB",73);
    System.out.println("遍历所有的key集");
    //遍历所有的key集：keySet()
    Set set = map.keySet();
    Iterator iterator = set.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
    System.out.println("遍历所有的value集");
    //遍历所有的value集：values()
    Collection values = map.values();
    for(Object obj : values){
        System.out.println(obj);
    }
    System.out.println("遍历所有的key-value");
    //方式一：entrySet()
    Set entrySet = map.entrySet();
    Iterator iterator1 = entrySet.iterator();
    while (iterator1.hasNext()){
        Object obj = iterator1.next();
        Map.Entry entry = (Map.Entry) obj;
        System.out.println(entry.getKey() + "-" + entry.getValue());
    }
    //方式二：
    Set set1 = map.keySet();
    Iterator iterator2 = set.iterator();
    while (iterator2.hasNext()){
        Object key = iterator2.next();
        Object value = map.get(key);
        System.out.println(key + "--" + value);
    }
}
```

#### 总结：常用方法：

添加：put(Object key,Object value)

删除：remove(Object key)

修改：put(Object key,Object value)

查询：get(Object key)

长度：size()

遍历：keySet() | values() | entrySet()

无插入：因为是无序的，不是有序的队伍。



### 面试题

1.HashMap的底层实现原理？

2.HashMap 和 HashTable的异同？

3.CurrentHashMap与HashTable的异同？（暂时不讲）



### TreeMap

向TreeMap中添加key-value，要求key必须是由同一个类创建的对象

因为要按照key进行排序：自然排序、定制排序

#### 自然排序

```java
@Test
public void test1(){
    TreeMap map = new TreeMap();
    User u1 = new User("Jack",18);
    User u2 = new User("Jane",21);
    User u3 = new User("Que",32);
    User u4 = new User("Carry",65);
    map.put(u1,98);
    map.put(u2,34);
    map.put(u3,21);
    map.put(u4,54);
    System.out.println(map);
    Set entrySet = map.entrySet();
    Iterator iterator = entrySet.iterator();
    while (iterator.hasNext()){
        Object obj = iterator.next();
        Map.Entry entry = (Map.Entry) obj;
        System.out.println(entry.getKey() + "--->" + entry.getValue());
    }
}
```

#### 定制排序

```java
@Test
public void test2(){
    TreeMap map = new TreeMap(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof User && o2 instanceof User){
                User u1 = (User) o1;
                User u2 = (User) o2;
                return Integer.compare(u1.getAge(),u2.getAge());
            }
            throw new RuntimeException("输入的类型不匹配");
        }
    });
    User u1 = new User("Jack",18);
    User u2 = new User("Jane",21);
    User u3 = new User("Que",32);
    User u4 = new User("Carry",11);
    map.put(u1,98);
    map.put(u2,34);
    map.put(u3,21);
    map.put(u4,54);
    System.out.println(map);
    Set entrySet = map.entrySet();
    Iterator iterator = entrySet.iterator();
    while (iterator.hasNext()){
        Object obj = iterator.next();
        Map.Entry entry = (Map.Entry) obj;
        System.out.println(entry.getKey() + "--->" + entry.getValue());
    }
}
```

## Collections工具类

Ps：操作数组的工具类：Arrays

- Collections是一个操作Set、List和Map等集合的工具类。

- Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。

- 排序操作：（均为static方法）

  - **reverse(List)**：反转List中元素的顺序

  - **shuffle(List)**：对List集合元素进行随机排序

  - **sort(List)**：根据元素的自然排序对指定List集合元素按升序排序

  - **sort(List , Comparator)**：根据指定的Comparator产生的顺序对List集合元素进行排序

  - **swap(List , int , int)**：将指定list集合中的 i 处元素和 j 处元素进行交换

  - **Object max(Collection)**：根据元素的自然顺序，返回给定集合中的最大元素
  - **Object max(Collection，Comparator)**：根据 Comparator 指定的顺序，返回给定集合中的最大元素
  - **Object min(Collection)**
  - **Object min(Collection，Comparator)**
  - **int frequency(Collection，Object)**：返回指定集合中指定元素的出现次数
  - **void copy(List dest,List src)**：将src中的内容复制到dest中
  - **boolean replaceAll(List list，Object oldVal，Object newVal)**：使用新值替换List 对象的所有旧值

**Collections**：操作Collection、Map的工具类



**Collections 类**中提供了多个 **synchronizedXxx() 方法**，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。



### 面试题：Collection和Collections的区别？

Collection是创建集合的一个接口，存储单例数据的接口。常见子接口有List、Set

Collections是操作Collection的工具类



## 每日一考

#### 1.Map存储数据的特点是什么？并指明key，value,，entry存储数据的特点。

双列数据，存储key-value对。

key：无序的、不可重复的。——>Set存储

value：无序的、可重复的。——>Collection存储

key-value：无序的、不可重复。——>Set存储

#### 2.描述HashMap的底层实现原理(jdk 8版)



#### 3. Map中常用实现类有哪些?各自有什么特点？



#### 4.如何遍历Map中的key-value对，代码实现



#### 5.Collection和Collections的区别？

# 泛型

## 泛型的使用

**1.jdk5.0新增的特性**

**2.在集合中使用泛型：**

​	总结：

​	①在集合接口中或集合类在jdk5.0时都修改为带泛型的结构

​	② 在实例化集合类时，可以指明具体的泛型类型

​	③指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。

​		比如：`add(E e)`	---->	实例化以后：`add(Integer e)`

​	④注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换

​	⑤如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。

**3.如何自定义泛型结构：泛型类、泛型接口；泛型方法**，在GenericTest1.java

​	1.关于自定义泛型类、泛型接口：

### 在集合中使用泛型前的情况

```java
@Test
public void test1(){
    ArrayList list = new ArrayList();
    //需求：存放学生的成绩
    list.add(67);
    list.add(97);
    list.add(87);
    list.add(62);
    //问题一：类型不安全
    list.add("Tom");
	for (Object score : list){
	    //问题二：强转时，可能出现ClassCastException
	    int stuScore = (Integer) score;
	    System.out.println(stuScore);
	}
}    
```

### 在集合中使用泛型的情况

```java
 //在集合中使用泛型的情况
 @Test
 public void test2(){
     ArrayList<Integer> list = new ArrayList<Integer>();
     list.add(78);
     list.add(56);
     list.add(98);
     //编译时，就会进行类型检查，保证数据的安全
     //list.add("Tom");
     //方式一：
     for (Integer score : list){
         //避免了强转操作
         int stuScore = score;
         System.out.println(stuScore);
     }
     //方式二：
     Iterator<Integer> iterator = list.iterator();
     while (iterator.hasNext()){
         int score = iterator.next();
         System.out.println(score);
     }
 }
```

#### 在集合中使用泛型的情况：以HashMap为例

```java
@Test
public void test3(){
    Map<String,Integer> map = new HashMap<String,Integer>();
    map.put("Tom",87);
    map.put("Jane",97);
    map.put("Jack",47);
    map.put("Jerry",27);
    //泛型的嵌套
    Set<Map.Entry<String, Integer>> entry = map.entrySet();
    Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
    while (iterator.hasNext()){
        Map.Entry<String, Integer> next = iterator.next();
        String key = next.getKey();
        Integer value = next.getValue();
        System.out.println(key + ":" + value);
    }
}
```

 



```java
@Test
public void test1(){
    //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
    //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
    Order order = new Order();
    order.setOrderT(123);
    order.setOrderT("aA");
    //建议：实例化时指明类的泛型
    Order<String> order1 = new Order<String>("orderAA",1001,"order：AA");
    order1.setOrderT("AA：hello");
}
```

```java
@Test
public void test2(){
    SubOrder sub = new SubOrder();
    //由于子类继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
    sub.setOrderT(123);
    SubOrder1<String> sub1 = new SubOrder1<String>();
    sub1.setOrderT("AA");
}
```

### 注意点：泛型不同的引用不能相互赋值

```java
@Test
public void test3(){
    ArrayList<String> list1 = null;
    ArrayList<Integer> list2 = null;
    //泛型不同的引用不能相互赋值
//        list2 = list1;
}
```

### 泛型方法

**泛型方法：**在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。

​				换句话说，泛型方法所属的类是不是泛型类都没有关系

**泛型方法，可以声明为静态的。**因为泛型参数是在调用方法时确定的，并非在实例化类时确定的

```java
public static  <E> List<E> copyFromArrayToList(E[] arr){
    ArrayList<E> list = new ArrayList<>();
    for (E e : arr){
        list.add(e);
    }
    return list;
}
```

#### 测试泛型方法

```java
 @Test
 public void test4(){
     Order<String> order = new Order<String>();
     Integer[] arr = new Integer[]{1,2,3,4};
     //泛型方法在调用时,指明泛型参数的类型
     List<Integer> list = order.copyFromArrayToList(arr);
     System.out.println(list);
 }
```



#### 非泛型类

```java
public class SubOrder extends Order<Integer>{//SubOrder：不是泛型类
    public static  <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr){
            list.add(e);
        }
        return list;
    }
}
```



#### 泛型类

```java
public class SubOrder1<T> extends Order<T>{//SubOrder<T>：仍然是泛型类

}
```



#### 异常类不能声明为泛型类

```java
public class MyException<T> extends Exception{

}
```

## DAO

**DAO： **data(base) access object ----操作数据库的一些通用操作

```java
//操作很多张表,所以要操作很多类,不确定.不确定就泛型,给个<T>
public class DAO<T> {//表的共性操作的DAO
    //添加一条记录
    public void add(T t){

    }

    //删除一条记录
    public boolean remove(int index){
        return false;
    }

    //修改一条记录
    public void update(int index,T t){

    }

    //查询一条记录
    public T getIndex(int index){
        return null;
    }

    //查询多条记录
    public List<T> getForList(int index){
        return null;
    }

    //泛型方法
    //举例:获取表中一共有多少条记录?  获取最大的员工入职时间?
    public <E> E getValue(){
        return null;
    }
}
```

### Customer

```java
public class Customer {//此类对应数据库中的customers表
}
```

### CustomerDAO

```java
public class CustomerDAO extends DAO<Customer>{//只能操作某一个表的DAO
}
```

### Student

```java
public class Student {
}
```

### StudentDAO

```java
public class StudentDAO extends DAO<Student>{//只能操作某一个表的DAO
}
```

### DAO Test

```java
@Test
public void test1(){
    CustomerDAO dao1 = new CustomerDAO();
    dao1.add(new Customer());
    List<Customer> list = dao1.getForList(10);
    StudentDAO dao2 = new StudentDAO();
    Student student = dao2.getIndex(1);
}
```

## 泛型在继承方面的体现

类A和类B的父类，但是`G<A>` 和`G<B>`二者不具备子父类关系，二者是并列关系。

**补充：**类A是类B的父类，`A<G>` 和`B<G>`，`A<G>`是`B<G>`的父类

```java
@Test
public void test1(){
    Object obj = null;
    String str = null;
    obj = str;
    Object[] arr1 = null;
    String[] arr2 = null;
    arr1 = arr2;
    List<Object> list1 = null;
    List<String> list2 = new ArrayList<String>();
    //此时的List1和List2的类型不具有子父类关系，编译不通过
    //list1 = list12;
    /*
    * 反证法：
    * 假设list1 = list2
    * list1.add(123);   导致混入非String的数据。出错。
    * */
    show(list1);
    show1(list2);//不可以
}
```

## 通配符的使用

通配符：?

类A是类B的父类，`G<A>`和`G<B>`是没有关系的，二者共同的父类是：`G<?>`

```java
@Test
public void test3(){
    List<Object> list1 = null;
    List<String> list2 = null;
    List<?> list = null;
    list = list1;
    list = list2;
    print(list1);
    print(list2);
}
```

```java
public void print(List<?> list){
    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()){
        Object obj = iterator.next();
        System.out.println(obj);
    }
}
```

### 添加（写入）

对于` List<?> `就不能向其内部添加数据，**除了添加null之外**

```java
List<?> list = null;
List<String> list3 = new ArrayList<>();
list3.add("AA");
list3.add("BB");
list3.add("CC");
list = list3;

list.add("DD");//不可以！
list.add("?");//不可以！
list.add(null);//可以
```

### 获取（读取）

允许读取数据，读取的数据类型为**Object**

`Object o = list.get(0);`

### 有限制的通配符

#### ? extends A

`G<? extends A>` 可以作为`G<A>`和`G<B>`的父类，其中B是A的子类

#### ? super A

`G<? super A>` 可以作为`G<A>`和`G<B>`的父类，其中B是A的父类



```java
List<? extends Person> list1 = null;
List<? super Person> list2 = null;
List<Student> list3 = new ArrayList<>();
List<Person> list4 = new ArrayList<>();
List<Object> list5 = new ArrayList<>();
```

#### 读取数据

```java
list1 = list4;
Person p = list1.get(0);
Object p1 = list1.get(0);
Student s = list1.get(0);//编译不通过
```

为什么编译不通过？

因为可以给**list1**赋值的必须是**Person**的子类（包含**Person**），例如**list3**和**list4**。

定义一个变量，所使用的类型，必须是大于该值的类型。`list1.get(0)`是**Person**类型，那么只能使用**≥Person**的类型，例如**Person**和**Object**，而**Student**属于**Person**的子类，是**＜Person**的，因此不可以，编译不通过。

**--------------------------------------------------**

```java
list2 = list4;
Object obj = list2.get(0);
//Person obj1 = list2.get(0);//编译不通过
```

为什么编译不通过？

因为可以给**list2**赋值的必须是**Person**的父类（包含**Person**），例如**list4**和**list5**。

如上所述，定义一个变量，所使用的类型，必须是大于该值的类型。但既然是父类，也无法确定是哪一级的父类，如果使用本身（**Person**）的话，是无法定义在**Person**和**Object**之间的值的。因此只能直接使用**Object**来定义`list2.get(0)`。

#### 写入数据

```java
list1.add(new Student());//编译不通过
```

为什么编译不通过？

因为给**list1**赋值的必须是**Person**的子类，而这个时候添加任意一个子类，如果给**list1**赋值的子类是级别更低一层的子类，那么这个时候添加的就是更低一层子类的父类。这种情况是不允许存在的。因此，`<? extends T>`是无法使用**add**的。

**--------------------------------------------------**

```java
//编译通过
list2.add(new Person());
list2.add(new Student());
//list2.add(new Object());//编译不通过
```

为什么编译不通过？

因为给**list2**赋值的必须是**Person**的父类，而这个时候就可以把任意一个**≤Person**的子类添加到**list2**中，而`new Object()`属于**≥Person**的部分，编译是无法通过的。

# IO流

## File类的使用

1.File类的一个对象，代表一个文件或一个文件目录（俗称：文件夹）

2.File类声明在java.io包下

3.File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法。

​		并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成

4.后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的”终点“。



### 如何创建File类的实例

#### 构造器1：File(String filePath)

> 相对路径：相较于某个路径下，指明的路径。
>
> 绝对路径：包含盘符在内的文件或文件目录的路径

```java
File file1 = new File("D:\\JavaProject\\JavaSenior\\IO\\hello.txt");//绝对路径
File file2 = new File("hello.txt");//相对于当前的module的相对路径
```

#### 构造器2：File(String pathname,String childPath)

**pathname：**绝对路径

**childPath：**绝对路径下的<u>子路径</u>或<u>子文件</u>。

```java
File file4 = new File("D:\\JavaProject\\JavaSenior","IO");
```

#### 构造器3：File(File parentFile,String childPath)

**File类型**的路径下的子文件或子路径。

```java
File file5 = new File(file4,"hello.txt");
```



### 路径分隔符

> windows：\\\
>
> unix：/

**为了适配不同的系统，可以使用separator代替斜杠，它会自动适应不同的系统使用斜杠**

```java
File file3 = new File("D:" + File.separator + "JavaProject" + File.separator + "JavaSenior" + File.separator + "IO" + File.separator + "hello.txt");
```

## File的常用方法

### 适用于文件

**public String getAbsolutePath()：**获取绝对路径

**public String getPath() ：**获取路径

**public String getName() ：**获取名称

**public String getParent()：**获取上层文件目录路径。若无，返回null

**public long length() ：**获取文件长度（即：字节数）。不能获取目录的长度。

**public long lastModified() ：**获取最后一次的修改时间，毫秒值

### 适用于文件目录

**public String[] list() ：**获取指定目录下的所有文件或者文件目录的名称数组

**public File[] listFiles() ：**获取指定目录下的所有文件或者文件目录的File数组

```java
@Test
public void test3(){
    File file = new File("D:\\JavaProject\\JavaSenior");
    String[] list = file.list();
    for (String fName : list){
        System.out.println(fName);
    }
    File[] files = file.listFiles();
    for (File f : files){
        System.out.println(f);
    }
}
```

### **把文件重命名到指定的文件路径**

**public boolean renameTo(File dest)：**把文件重命名到指定的文件路径

比如：`file1.renameTo(file2)`为例

​    要想保证返回true，需要file1在硬盘中是存在的，且file2不能在硬盘中存在

就是把file1里的文件，放到file2的路径中且重命名为`“兔子1.txt”`

```java
@Test
public void test4(){
    File file1 = new File("hello.txt");
    File file2 = new File("D:\\File\\图片\\兔子1.txt");
    boolean renameTo = file1.renameTo(file2);
    System.out.println(renameTo);
}
```

### File类的判断功能

**public boolean isDirectory()：**判断是否是文件目录

**public boolean isFile() ：**判断是否是文件

**public boolean exists() ：**判断是否存在

**public boolean canRead() ：**判断是否可读

**public boolean canWrite() ：**判断是否可写

**public boolean isHidden() ：**判断是否隐藏

```java
@Test
public void test5(){
    File file1 = new File("hello.txt");
    file1 = new File("hello1.txt");
    System.out.println(file1.isDirectory());
    System.out.println(file1.isFile());
    System.out.println(file1.exists());
    System.out.println(file1.canRead());
    System.out.println(file1.canWrite());
    System.out.println(file1.isHidden());
    
    System.out.println();
    
    File file2 = new File("D:\\File");
    System.out.println(file2.isDirectory());
    System.out.println(file2.isFile());
    System.out.println(file2.exists());
    System.out.println(file2.canRead());
    System.out.println(file2.canWrite());
    System.out.println(file2.isHidden());
}
```

### File类的创建功能

**public boolean createNewFile() ：**创建文件。若文件存在，则不创建，返回false

```java
@Test
public void test6() throws IOException {
    File file1 = new File("hi.txt");
    if (!file1.exists()){
        //文件创建
        file1.createNewFile();
        System.out.println("创建成功");
    }else{//如果不存在
        file1.delete();
        System.out.println("删除成功");
    }
}
```



**public boolean mkdir() ：**创建文件目录。如果此文件目录存在，就不创建了。

 												 如果此文件目录的上层目录不存在，也不创建。

**public boolean mkdirs() ：**创建文件目录。如果上层文件目录不存在，一并创建

```java
@Test
public void test7(){
    //文件目录创建
    File file1 = new File("d:\\File\\test_test");
    boolean mkdir = file1.mkdir();
    if (mkdir){
        System.out.println("创建成功");
    }else{
        System.out.println("创建失败");
    }
    File file2 = new File("d:\\test1\\test_test");
    boolean mkdirs = file2.mkdirs();
    if (mkdirs){
        System.out.println("创建成功");
    }else{
        System.out.println("创建失败");
    }
}
```

#### 创建注意事项：

如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下。



### File类的删除功能

**public boolean delete()：**删除文件或者文件夹

#### 删除注意事项：

Java中的删除不走回收站。

要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录

## 每日一考

#### 1.如何遍历Map的key集，value集，key-value集，使用上泛型

```java
Map<String,Integer> map = new HashMap<String,Integer>();
map.put();..//存放各种数据

//遍历key    
Set<String> keySet = map.keySet();
for(String key : keySet){
    System.out.println(key);
}

//遍历value
Collection<Integer> values = map.values();
Iterator<Integer> iterator = values.iterator();
while(iterator.hasNext()){
    System.out.println(iterator.next());
}

//遍历key-value
Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
Iterator<Map.Entry<String,Integer>> iterator = entrySet.iterator();
while(iterator.hasNext()){
    Map.Entry<String,Integer> entry = iterator.next();
    String key = entry.getKey();
    Integer value = entry.getValue();
    System.out.println(key + "--->" + value);
}
```



#### 2.写出使用Iterator和增强for 循环遍历`List<String>`的代码，使用上泛型



#### 3.提供一个方法，用于遍历获取HashMap<String,String>中的所有value，并存放在List中返回。考虑上集合中泛型的使用。

```java
public List<String> getValueList(HashMap<String,String> map){
	ArrayList<String> valueList = new ArrayList();
	Collection<String> values = map.values();
	for(String value : values){
		valueList.add(value);
	}
    return valueList;
}
```



#### **4.创建一个与a.txt**文件同目录下的另外一个文件**b.txt**

```java
File file1 = new File("a.txt");
File file2 = new File(file1.getParent(),"b.txt");
file2.createNewFile();
if(file2){
    syso("创建成功！");
}else{
    syso("创建失败！")
}
```



#### 5.Map接口中的常用方法有哪些

**增：**`put(K k,V v)`

**删：**`V remove(K k)`

**改：**`put(K k,V v)`

**查：**`V get(K k)`

**长度：**`int size()`

**遍历：**



## IO流原理及流的分类

> Google I/O 寓为“开放中创新”
>
> （Innovation in the Open）
>
> 
>
> Input/Output
>
> 二进制 1，0

### IO流体系

[![qHxfz9.png](https://s1.ax1x.com/2022/04/04/qHxfz9.png)](https://imgtu.com/i/qHxfz9)

### 流的分类

1.操作数据单位：字节流、字符流

2.数据的流向：输入流、输出流

3.流的角色：节点流、处理流

### 流的体系结构

| 抽象基类     | 节点流（或文件流） | 缓冲流（处理流的一种） |
| ------------ | ------------------ | ---------------------- |
| InputStream  | FileInputStream<br>read(byte[] buffer) | BufferedInputStream<br/>read(byte[] buffer) |
| OutputStream | FileOutputStream<br/>write(byte[] buffer,0,len) | BufferedOutputStream<br>write(byte[] buffer,0,len) / flush() |
| Reader       | FileReader<br/>read(char[] cBuf) | BufferedReader<br>read(char[] cBuf) / readLine() |
| Writer       | FileWriter<br/>write(char[] cBuf,0,len) | BufferedWriter<br>write(char[] cBuf,0,len) / flush() |

### FileReader

将IO下的hello.txt文件内容读入程序中，并输出控制台

说明点：

1.read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1

2.异常处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理

3.读入的文件一定要存在，否则就会报FileNotFoundException

```java
@Test
public void testFileReader(){
    FileReader fr = null;
    try {
        //1.实例化File类的对象，指明要操作的文件
        File file = new File("hello.txt");//相较于当前Module
        //2.提供具体的流
        fr = new FileReader(file);
        //3.数据的读入
        //read()：返回读入的一个字符。如果达到文件末尾，返回-1
        //方式一：
        //int data = fr.read();
        //while (data != -1){
        //    System.out.print((char) data);
        //    data = fr.read();
        //}
        //方式二：语法上对于方式一的修改
        int data;
        while ((data = fr.read()) != -1){
            System.out.print((char) data);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //4.流的关闭操作
        //try {
        //    if(fr != null)
        //        fr.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //或
        if (fr != null){
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 对read()操作升级：使用read的重载方法

```java
@Test
public void testFileReader1(){
    FileReader fr = null;
    try {
        //1.File类的实例化
        File file = new File("hello.txt");
        //2.FileReader流的实例化
        fr = new FileReader(file);
        //3.读入的操作
        //read(char[] cBuf)：返回每次读入cBuf数组中的字符的个数。如果达到文件末尾，返回-1
        char[] cBuf = new char[5];
        int len;
        while ((len = fr.read(cBuf)) != - 1){
            //方式一：
            //错误的写法
            //for (int i = 0;i < cBuf.length;i++){
            //    System.out.print(cBuf[i]);
            //}
            //正确的写法
            //for (int i = 0;i < len;i++){
            //    System.out.print(cBuf[i]);
            //}
            //方式二：
            //错误的：
            //String str = new String(cBuf);
            //System.out.println(str);
            //正确的：
            String str = new String(cBuf, 0, len);
            System.out.println(str);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //4.资源的关闭
        if (fr != null){
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 测试FileInputStream和FileOutputStream的使用

#### **结论：**

1.对于文本文件（.txt，.java，.c，.cpp），使用字符流处理

2.对于非文本文件（.jpg，.mp3，.mp4，.avi，.doc，.ppt，... ...），使用字节流处理

**使用字节流处理文件可能出现乱码**

```java
@Test
public void testFileInputStream(){
    FileInputStream fis = null;
    try {
        //1.造文件
        File file = new File("hello.txt");
        //2.造流
        fis = new FileInputStream(file);
        //3.读数据
        byte[] buffer = new byte[5];
        int len;//记录每次读取的字节的个数
        while ((len = fis.read(buffer)) != -1){
            String str = new String(buffer,0,len);
            System.out.println(str);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fis != null){
            //4.关闭资源
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### **封装指定路径下文件复制的方法：**

```java
//指定路径下文件的复制
public void copyFile(String srcPath,String destPath){
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);
        //复制的过程
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fis != null){
            //关闭
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### **调用方法：**

```java
@Test
public void testCopyFile(){
    long start = System.currentTimeMillis();
    String srcPath = "pindao.mp4";
    String destPath = "pindao3.mp4";
    copyFile(srcPath,destPath);
    long end = System.currentTimeMillis();
    System.out.println("复制操作话费的时间为：" + (end - start));
}
```

### 缓冲流

#### 处理流之一：缓冲流的使用

**1.缓冲流：**

BufferedInputStream

BufferedOutputStream

BufferedReader

BufferedWriter

**2.作用：提高流的读取、写入速度**



##### 实现非文本文件的复制

```java
@Test
public void BufferedStreamTest(){
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
        //1.造文件
        File srcFile = new File("兔子.jpg");
        File destFile = new File("兔子2.jpg");
        //2.造流
        //2.1 造节点流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        //2.2 造缓冲流
        bis = new BufferedInputStream(fis);
        bos = new BufferedOutputStream(fos);
        //3.复制的细节：读取、写入
        byte[] buffer = new byte[10];
        int len;
        while ((len = bis.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (bis != null){
            //4.资源关闭：
            //要求：先关闭外层的流，再关闭内层的流
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bos != null){
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略。
    //fos.close();
    //fis.close();
}
```

### 练习

#### 图片的加密解密

##### 加密

```java
@Test
public void test2(){
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
        fis = new FileInputStream("兔子.jpg");
        fos = new FileOutputStream("加密兔子.jpg");
        byte[] buffer = new byte[20];
        int len;
        while ((len = fis.read(buffer)) != -1){
            //字节数据进行修改
            //错误的：
            //for (byte b : buffer){
            //    b = (byte) (b ^ 5);
            //}
            //正确的：
            for (int i = 0;i < len;i++){
                buffer[i] = (byte) (buffer[i] ^ 5);
            }
            fos.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fis != null){
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### 解密

```java
@Test
public void test3(){
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
        fis = new FileInputStream("加密兔子.jpg");
        fos = new FileOutputStream("解密兔子.jpg");
        byte[] buffer = new byte[20];
        int len;
        while ((len = fis.read(buffer)) != -1){
            //字节数据进行修改
            //错误的：
            //for (byte b : buffer){
            //    b = (byte) (b ^ 5);
            //}
            //正确的：
            for (int i = 0;i < len;i++){
                buffer[i] = (byte) (buffer[i] ^ 5);
            }
            fos.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fis != null){
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 转换流

#### **处理流之二：转换流的使用**

**1.转换流：属于字符流**

​    InputStreamReader：将一个字节的输入流转换为字符的输入流

​    OutputStreamWriter：将一个字符的输出流转换为字节的输出流

**2.作用：**提供字符流与字节流之间的转换

**3.解码：**字节、字节数组 ---->  字符数组、字符串

   **编码：**字符数组、字符串  ---->  字节、字节数组

**4.字符集：**

**ASCII：**美国标准信息交换码。

​			 用一个字节的7位可以表示。

**ISO8859-1：**拉丁码表。欧洲码表

​					  用一个字节的8位表示。

**GB2312：**中国的中文编码表。最多两个字节编码所有字符

**GBK：**中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码

**Unicode：**国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。

**UTF-8：**变长的编码方式，可用1-4个字节来表示一个字符。  



#### InputStreamReader演示

InputStreamReader的使用，实现字节的输入流到字符的输入流的转换

**注意：**此时处理异常的话，仍然应该使用try-catch-finally

```java
@Test
public void test1() throws IOException {
    FileInputStream fis = new FileInputStream("hello.txt");
    //InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符集
    //参数2指明了字符集，具体使用哪个字符集，取决于文件hello.txt保存时使用的字符集
    InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
    char[] cBuf = new char[20];
    int len;
    while ((len = isr.read(cBuf)) != -1){
        String str = new String(cBuf, 0, len);
        System.out.println(str);
    }
    isr.close();
    fis.close();
}
```

#### 综合使用InputStreamReader和OutputStreamWriter

```java
 @Test
 public void test2() throws IOException {
     //1.造文件、造流
     File file1 = new File("hello.txt");
     File file2 = new File("hello_gbk.txt");
     
     FileInputStream fis = new FileInputStream(file1);
     FileOutputStream fos = new FileOutputStream(file2);
     
     InputStreamReader isr = new InputStreamReader(fis,"utf-8");
     OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");
     //2.读写过程
     char[] cBuf = new char[20];
     int len;
     while ((len = isr.read(cBuf)) != -1){
         osw.write(cBuf,0,len);
     }
     //3.关闭资源
     isr.close();
     osw.close();
 }
```

#### 补充：字符编码

我的理解：

中文转到底层utf-8的二进制编码

在Unicode中 找到对应的中文编码 转换成十六进制再转换成二进制。

中文是占三个字节

选择1110xxxx	10xxxxxx 10xxxxxx

将得到的二进制数字，填入到x中去，就是中文编码

### 标准流输入、输出流

标准的输入、输出流

**1.1**

System.in：标准的输入流，默认从键盘输入

System.out：标准的输出流，默认从控制台输出

**1.2**

System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新制定输入和输出的流

**1.3**练习：

从键盘输入字符串，要求将读取到的整行字符串转成大写输出。

然后继续进行输入操作，直至当输入“e”或者“exit”时，退出程序。

方法一：使用Scanner实现，调用next()返回一个字符串

方法二：使用System.in实现。System.in ----->  转换流  ------>  BufferedReader的readLine()

### 打印流

**2.打印流：**PrintScream和PrintWriter

​	**2.1** 提供了一系列重载的print()和println()

​	**2.2** 练习

```java
@Test
public void test2(){
    PrintStream ps = null;
    try {
        FileOutputStream fos = new FileOutputStream(new File("newHello.txt"));
        //创建打印输出流，设置为自动刷新模式（写入换行符或字节'\n'时都会刷新输出缓冲区）
        ps = new PrintStream(fos, true);
        if (ps != null){//把标准输出流（控制台输出）改成文件
            System.setOut(ps);
        }
        for (int i = 0;i <= 255;i++){
            System.out.print((char) i);
            if (i % 50 == 0){
                System.out.println();
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } finally {
        if (ps != null){
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 数据流

3.1 DataInputStream 和 DataOutputStream

3.2 作用：用于读取或写出基本数据类型的变量或字符串

练习：将内存中的字符串、基本数据类型的变量写出到文件中。

**注意：处理异常的话，仍然应该使用try-catch-finally**

```java
@Test
public void test3() throws IOException {
    DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
    dos.writeUTF("陈多多");
    dos.flush();//刷新操作，将内存中的数据写入文件
    dos.writeInt(20);
    dos.flush();
    dos.writeBoolean(true);
    dos.flush();
    dos.close();
}
```

将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中

**注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据顺序一致**

```java
@Test
public void test4() throws IOException {
    //1.
    DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
    //2.
    String name = dis.readUTF();
    int age = dis.readInt();
    boolean isMale = dis.readBoolean();
    System.out.println("name = " + name + ",age = " + age + ",isMale = " + isMale);
    //3.
    dis.close();
}
```

### 对象流

## 每日一考

#### 1.说明流的三种分类方式

流向：输入流、输出流

数据单位：字节流、字符流

流的角色：节点流、处理流

#### 2.写出4个IO流中的抽象基类，4个文件流，4个缓冲流

InputStream						FileXxx						BufferedXxx

OutputStream

Reader

Writer



InputStreamReader：父类Reader

异常：XxxException	XxxError

#### 3.字节流与字符流的区别与使用情境

字节流：read(byte[] buffer) / read()	非文本文件

字符流：read(char[] cBuf) / read()		文本文件

#### 4.使用缓冲流实现a.jpg文件复制为b.jpg文件的操作

```java
BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("a.jpg")));
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("b.jpg")));

byte[] buffer = new byte[1024];
int len;

while((len = bis.read(buffer)) != -1){
	bos.write(buffer,0,len);
}

bos.close();
bis.close();

//此处的异常应该使用try-catch-finally处理
```

#### 5.转换流是那两个类，分别的作用是什么？请分别创建两个类的对象

InputStreamReader：将输入的字节流转换为输入的字符流。解码

OutputStreamWriter：将输出的字符流转换为输出的字节流。编码



```java
InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"),"gbk");
OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("b.txt"),"gbk"); 
```



## 对象流

### 对象流的使用

1.ObjectInputStream 和 ObjectOutputStream

2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。

3.想要一个Java对象是可序列化的，需要满足相应的要求。将Person.java

**4.序列化机制：**

对象序列化极值允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。当其它程序获取了这种二进制流，就可以恢复成原来的Java对象

### 注意注意：

**如果想要序列化一个对象Person**

Person需要满足如下的要求，方可序列化

1.需要实现接口：Serializable

2.当前类提供一个全局常量：serialVersionUID

3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的（默认情况下，基本数据类型可序列化）

补充：ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量，**反序列化的时候会是默认值**

```java
public class Person implements Serializable {
    public static final long serialVersionUID = 44124153252342L;
}
```

### 序列化过程

序列化过程：将内存中的Java对象保存到磁盘中或通过网络传输出去

使用ObjectOutputStream实现

```java
@Test
public void testObjectOutputStream(){
    ObjectOutputStream oos = null;
    try {
        //1.
        oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
        //2.
        oos.writeObject(new String("我爱中国"));
        oos.flush();
        oos.writeObject(new Person("陈多多", 21));
        oos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (oos != null){
            //3.
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 反序列化过程

反序列化：将磁盘文件中的对象还原为内存中的一个java对象

使用ObjectInputStream来实现

```java
@Test
public void testObjectInputStream(){
    ObjectInputStream ois = null;
    try {
        ois = new ObjectInputStream(new FileInputStream("object.dat"));
        Object obj = ois.readObject();
        String str = (String) obj;
        Person p = (Person) ois.readObject();
        System.out.println(str);
        System.out.println(p);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        if (ois != null){
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## 随机存取文件流

### RandomAccessFile

#### RandomAccessFile的使用

1.RandomAccessFile直接继承于java.lang.Object类，是西安了DataInput和Output接口

2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流

3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建

  如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖） 

```java
@Test
public void test1(){
    RandomAccessFile raf1 = null;
    RandomAccessFile raf2 = null;
    try {
        raf1 = new RandomAccessFile(new File("kk.png"),"r");
        raf2 = new RandomAccessFile(new File("kk2.png"), "rw");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = raf1.read(buffer)) != -1){
            raf2.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (raf1 != null){
            try {
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (raf2 != null){
            try {
                raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
@Test
public void test2() throws IOException {
    RandomAccessFile raf1 = new RandomAccessFile(new File("hello.txt"), "rw");
    raf1.write("~~~".getBytes());
    raf1.close();
}
```

# 网络编程

## 计算机网络

把分布在不同地理区域的计算机与专门的外部设备用通信线路互连成一个规模大、功能强的网络系统，从而使众多的计算机可以方便地互相传递信息、共享硬件、软件、数据信息等资源。

## **网络编程的目的：**

直接或间接地通过网络协议与其它计算机实现数据交换，进行通讯。

## 一、网络编程中有两个主要的问题：

1.如何准确地定位网络上一台或多台主机；定位主机上的特定的应用

2.找到主机后如何可靠高效地进行数据传输

## 二、网络编程中的两个要素：

1.对应问题一：IP和端口号

2.对应问题二：提供网路通信协议：TCP/IP参考模型（应用层、传输层、网络层、物理+数据链路层）

## 三、通信要素一：IP和端口号

1.IP：唯一的标识Internet上的计算机（通信实体）

2.在Java中使用InetAddress类代替IP

3.IP分类：IPv4和IPv6；万维网 和 局域网

4.域名：www.baidu.com   www.mi.com   www.sina.com   www.jd.com   www.vip.com

5.本地回路地址：127.0.0.1  对应着：localhost

6.如何实例化InetAddress：两个方法：getName(String host) 、 getLocalHost()

​	两个常用方法：getHostName() / getHostAddress()

7.端口号：正在计算机上运行的进程

​	要求：不同的进程有不同的端口号

​	范围：被规定为一个16位的这整数0~65535

8.端口号与IP地址的组合得出一个网络套接字：Socket

```java
public static void main(String[] args) {
    try {
        InetAddress inet1 = InetAddress.getByName("192.168.10.14");
        System.out.println(inet1);
        InetAddress inet2 = InetAddress.getByName("www.atguigu.com");
        System.out.println(inet2);
        InetAddress inet3 = InetAddress.getByName("127.0.0.1");
        System.out.println(inet3);
        //获取本地ip
        InetAddress inet4 = InetAddress.getLocalHost();
        System.out.println(inet4);
        //getHostName()
        System.out.println(inet2.getHostName());
        //getHostAddress()
        System.out.println(inet2.getHostAddress());
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
}
```

## TCP的网络编程

TCP必须保证建立了连接，才能发送文件。如果先执行客户端，会报错。

#### 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上

```java
//客户端
@Test
public void client(){
    //发数据
    Socket socket = null;
    OutputStream os = null;
    try {
        //1.创建Socket对象，指明服务器端的ip和端口号
        InetAddress inet = InetAddress.getByName("127.0.0.1");//对方的ip地址
        socket = new Socket(inet,8899);
        //2.获取一个输出流，用于输出数据
        os = socket.getOutputStream();
        //3.写出数据的操作
        os.write("你好，我是客户端".getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //4.资源的关闭
        if (os != null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//服务端
@Test
public void server(){
    ServerSocket ss = null;
    Socket socket = null;
    InputStream is = null;
    ByteArrayOutputStream baos = null;
    try {
        //1.创建服务器端的ServerSocket，指明自己的端口号
        ss = new ServerSocket(8899);
        //2.调用accept()表示接收来自于客户端的sockey
        socket = ss.accept();
        //3.获取输入流
        is = socket.getInputStream();
        //不建议这样写，可能会有乱码
        //byte[] buffer = new byte[1024];
        //int len;
        //while ((len = is.read(buffer)) != -1){
        //    String str = new String(buffer, 0, len);
        //    System.out.println(str);
        //}
        //4.读取输入流中的数据
        baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[5];
        int len;
        while ((len = is.read(buffer)) != -1){
            baos.write(buffer,0,len);
        }
        //先五个五个一存，最后一次性转换成一个字符串
        System.out.println(baos.toString());
        System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //5.关闭资源
        if (baos != null){
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (ss != null){
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 例题2：客户端发送文件给服务端，服务端将文件保存在本地。

```java
@Test
public void client () throws IOException{
    Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8899);
    OutputStream os = socket.getOutputStream();
    FileInputStream fis = new FileInputStream(new File("兔子.jpg"));
    byte[] buffer = new byte[1024];
    int len;
    while ((len = fis.read(buffer)) != -1){
        os.write(buffer,0,len);
    }
    fis.close();
    os.close();
    socket.close();
}
@Test
public void service(){
    ServerSocket ss = null;
    Socket socket = null;
    InputStream is = null;
    FileOutputStream fos = null;
    try {
        ss = new ServerSocket(8899);
        socket = ss.accept();
        is = socket.getInputStream();
        fos = new FileOutputStream(new File("兔子3.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (ss != null){
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 例题3：客户端发送文件给服务端，服务端将文件保存在本地。并发送“发送成功”给客户端，并关闭相应的连接

```java
@Test
public void client () throws IOException{
    Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8899);
    OutputStream os = socket.getOutputStream();
    FileInputStream fis = new FileInputStream(new File("兔子.jpg"));
    byte[] buffer = new byte[1024];
    int len;
    while ((len = fis.read(buffer)) != -1){
        os.write(buffer,0,len);
    }
    //关闭数据的输出
    socket.shutdownOutput();
    //接收来自于服务器端的数据，并显示到控制台上
    InputStream is = socket.getInputStream();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer1 = new byte[20];
    int len1;
    while ((len1 = is.read(buffer)) != -1){
        baos.write(buffer,0,len1);
    }
    System.out.println(baos.toString());
    fis.close();
    os.close();
    baos.close();
    socket.close();
}
@Test
public void service(){
    ServerSocket ss = null;
    Socket socket = null;
    InputStream is = null;
    FileOutputStream fos = null;
    OutputStream os = null;
    try {
        ss = new ServerSocket(8899);
        socket = ss.accept();
        is = socket.getInputStream();
        fos = new FileOutputStream(new File("兔子4.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        System.out.println("图片传输完成");
        //服务器端给与客户端反馈
        os = socket.getOutputStream();
        os.write("你好，世界".getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (ss != null){
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (os != null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## UDP协议的网络编程

UDP只管发，不知道接收端接收到没。因此先执行发送端也不会报错

```java
//发送端
@Test
public void sender() throws IOException {
    DatagramSocket socket = new DatagramSocket();
    String str = "我是UDP方式发送的导弹";
    byte[] data = str.getBytes();
    InetAddress inet = InetAddress.getLocalHost();
    DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
    socket.send(packet);
    socket.close();
}
//接收端
@Test
public void receiver() throws IOException {
    DatagramSocket socket = new DatagramSocket(9090);
    byte[] buffer = new byte[100];
    DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
    socket.receive(packet);
    System.out.println(new String(packet.getData(),0,packet.getLength()));
    socket.close();
}
```

## URL编程

1.URL：统一资源定位符，对应着互联网的某一资源地址

2.格式：

http://localhost:8080/examples/beauty.jpg?username=Tom

协议   主机名      端口号  资源地址                     参数列表

```java
public static void main(String[] args) {
    try {
        URL url = new URL("http://localhost:8080/examples/beauty.jpg?username=Tom");
        //public String getProtocol( ) 获取该URL的协议名
        System.out.println(url.getProtocol());
        //public String getHost( ) 获取该URL的主机名
        System.out.println(url.getHost());
        //public String getPort( ) 获取该URL的端口号
        System.out.println(url.getPort());
        //public String getPath( ) 获取该URL的文件路径
        System.out.println(url.getPath());
        //public String getFile( ) 获取该URL的文件名
        System.out.println(url.getFile());
        //public String getQuery( ) 获取该URL的查询名
        System.out.println(url.getQuery());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
}
```

### URL下载服务器资源

```java
public static void main(String[] args){
    HttpsURLConnection urlConnection = null;
    InputStream is = null;
    FileOutputStream fos = null;
    try {
        URL url = new URL("https://s1.ax1x.com/2022/04/11/LZMCGV.png");
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.connect();
        is = urlConnection.getInputStream();
        fos = new FileOutputStream("IO\\洋桔梗.png");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        System.out.println("下载完成");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //关闭资源
        if (is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (urlConnection != null){
            urlConnection.disconnect();//断开连接
        }
    }
}
```

## 每日一考

#### 第一题

**一个IP对应着哪个类的一个对象？**

InetAddress

**实例化这个类的两种方式是？**

`InetAddress.getByName(String host);`

`InetAddress.getLocalHost();`//获取本地ip

**两个常用的方法是？**

`getHostName();`

`getHostAddress();`



#### **传输层的TCP协议和UDP协议的主要区别是？**

TCP：可靠的数据传输（三次握手）；进行大数据量的传输；效率低；

UDP：不可靠；以数据报形式发送，数据包限定为64k；效率高；



#### **什么是URL，你能写一个URL吗？**

URL：统一资源定位符

`URL url = new URL("http://192.168.1.1:8080/examples/hello.txt?username=Tom");`



#### **谈谈你对对象序列化机制的理解**

序列化过程：将内存中的Java对象保存到磁盘中或通过网络传输出去

反序列化过程：将磁盘文件中的对象还原为内存中的一个java对象



#### **对象要想实现序列化，需要满足哪几个条件？**

1.实现接口：Serializable   标识接口

2.对象所在的类提供常量：序列版本号

3.要求对象的属性也必须是可序列化的。（基本是据类型、String：本身就已经是可序列化的）

# 反射

## 反射机制概述

Reflection（反射）是被视为动态语言的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。 

加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为：**反射**。

![image-20220422191558034](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20220422191558034.png)

## 动态语言 vs 静态语言

### 1、动态语言

是一类在运行时可以改变其结构的语言：例如新的函数、对象、甚至代码可以

被引进，已有的函数可以被删除或是其他结构上的变化。通俗点说就是**在运行时代码可以根据某些条件改变自身结构**。

主要动态语言：Object-C、C#、JavaScript、PHP、Python、Erlang。 

### 2、静态语言

与动态语言相对应的，运行时结构不可变的语言就是静态语言。如Java、C、C++。

> Java不是动态语言，但Java可以称之为“准动态语言”。即Java有一定的动态性，我们可以利用反射机制、字节码操作获得类似动态语言的特性。
>
> Java的动态性让编程的时候更加灵活！

## Java反射机制提供的功能

> 在运行时判断任意一个对象所属的类
>
> 在运行时构造任意一个类的对象
>
> 在运行时判断任意一个类所具有的成员变量和方法
>
> 在运行时获取泛型信息
>
> 在运行时调用任意一个对象的成员变量和方法
>
> 在运行时处理注解
>
> 生成动态代理

## 反射之前，对于Person的操作

```java
@Test
public void test1() {
    //1.创建Person类的对象
    Person p1 = new Person("Tom", 21);
    //2.通过对象，调用其内部的属性、方法
    p1.age = 19;
    System.out.println(p1.toString());
    p1.show();
    //在Person类外部，不可以通过Person类的对象调用其内部私有结构
    //比如：name，showNation()以及私有的构造器
}
```

## 反射之后，对于Person的操作

```java
@Test
public void test2() throws Exception {
    Class clazz = Person.class;//反射的源头
    //1.通过反射，创建Person类的对象
    Constructor cons = clazz.getConstructor(String.class, int.class);
    Object obj = cons.newInstance("Tom", 12);
    Person p = (Person) obj;
    System.out.println(p.toString());
    //2.通过反射，调用对象指定的属性、方法
    Field age = clazz.getDeclaredField("age");
    age.set(p, 10);
    System.out.println(p.toString());
    //调用方法
    Method show = clazz.getDeclaredMethod("show");
    show.invoke(p);
    //通过反射，可以调用Person类的私有结构。比如：私有的构造器、方法、属性
    //调用私有的构造器
    Constructor cons1 = clazz.getDeclaredConstructor(String.class);
    cons1.setAccessible(true);
    Person p1 = (Person) cons1.newInstance("Jerry");
    System.out.println(p1.toString());
    //调用私有的属性
    Field name = clazz.getDeclaredField("name");
    name.setAccessible(true);
    name.set(p1, "李华");
    System.out.println(p1);
    //调用私有的方法
    Method showNation = clazz.getDeclaredMethod("showNation", String.class);
    showNation.setAccessible(true);
    String nation = (String) showNation.invoke(p1, "中国");//相当于p1.showNation("中国")
    System.out.println(nation);
}
```

## 有关反射的疑问

#### 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，看法中到底用哪个？

建议：直接new的方式

什么时候会使用：反射的方式。反射的特征：动态性

#### 疑问2：反射极值与面向对象中的封装性是不是矛盾的？如何看待两个技术？

不矛盾。p638

## 关于java.lang.Class类的理解

1.类的加载过程：程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)，接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中，此过程就称为类的加载。加载到内存中的类，我们就称为给运行时类，此运行时类，就作为Class的一个实例。

2.换句话说，Class的实例就对应着一个运行时类。

3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。

## 获取Class的实例的方式

```java
@Test
public void test3() throws ClassNotFoundException {
    //方式一：调用运行时类的属性：.class
    Class<Person> clazz1 = Person.class;
    System.out.println(clazz1);
    //方式二：通过运行时类的对象
    Person p1 = new Person();
    Class clazz2 = p1.getClass();
    System.out.println(clazz2);
    
    //方式三：调用Class的静态方法：forName(String classPath)
    Class clazz3 = Class.forName("Reflection.Person");
    clazz3 = Class.forName("java.lang.String");
    System.out.println(clazz3);
    System.out.println(clazz1 == clazz2);
    System.out.println(clazz1 == clazz3);
    //方式四：使用类的加载器：Classloader（了解）
    ClassLoader classLoader = ReflectionTest.class.getClassLoader();
    Class clazz4 = classLoader.loadClass("Reflection.Person");
    System.out.println(clazz4);
    System.out.println(clazz1 == clazz4);
}
```

## Class实例可以是哪些结构的说明：

```java
@Test
public void test4(){
    Class c1 = Object.class;
    Class c2 = Comparable.class;
    Class c3 = String[].class;
    Class c4 = int[][].class;
    Class c5 = ElementType.class;
    Class c6 = Override.class;
    Class c7 = int.class;
    Class c8 = void.class;
    Class c9 = Class.class;
    
    int[] a = new int[10];
    int[] b = new int[100];
    Class c10 = a.getClass();
    Class c11 = b.getClass();
    // 只要数组的元素类型与维度一样，就是同一个Class
    System.out.println(c10 == c11);
}
```

## 了解类的加载器

```java
@Test
public void test1(){
    //对于自定义类，使用系统类加载器进行加载
    ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
    System.out.println(classLoader);
    //调用系统类加载器的getParent()：获取扩展类加载器
    ClassLoader classLoader1 = classLoader.getParent();
    System.out.println(classLoader1);
    //调用扩展类加载器的getParent()：无法获取引导类加载器
    //引导类加载器主要负责加载java的核心类库，无法加载自定义类的
    ClassLoader classLoader2 = classLoader1.getParent();
    System.out.println(classLoader2);
    ClassLoader classLoader3 = String.class.getClassLoader();
    System.out.println(classLoader3);
}
```

```java
/*
* Properties：用来读取配置文件。
* */
@Test
public void test2() throws Exception {
    Properties pros = new Properties();
    //此时的文件默认在当前的module下
    //读取配置文件的方式一：
    //FileInputStream fis = new FileInputStream("jdbc.properties");
    //pros.load(fis);
    //读取配置文件的方式二：使用ClassLoader
    //配置文件默认识别为：当前module的src下
    ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
    InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
    pros.load(is);
    String user = pros.getProperty("user");
    String password = pros.getProperty("password");
    System.out.println("user = " + user + ",password = " + password);
}
```

## 创建运行时类的对象

通过反射创建对应的运行时类的对象

**newInstance()：**调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参的构造器。

**要想此方法正常的创建运行时类的对象，要求：**

1.运行时类必须提供空参的构造器

2.空参的构造器的访问权限得够。通常，设置为public

**在JavaBean中要求提供一个public的空参构造器，原因：**

1.便于通过反射，创建运行时类的对象

2.便于子类继承此运行时类时，默认调用super()时，保证父类由此构造器

```java
@Test
public void test1() throws InstantiationException, IllegalAccessException {
    Class<Person> clazz = Person.class;
    /*
    * newInstance()：调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参的构造器。
    * 要想此方法正常的创建运行时类的对象，要求：
    * 1.运行时类必须提供空参的构造器
    * 2.空参的构造器的访问权限得够。通常，设置为public
    *
    * 在javabean中要求提供一个public的空参构造器，原因：
    * 1.便于通过反射，创建运行时类的对象
    * 2.便于子类继承此运行时类时，默认调用super()时，保证父类由此构造器
    * */
    Person obj = clazz.newInstance();
    System.out.println(obj);
}
```

### 体会反射的动态性

```java
@Test
public void test2(){
    for (int i = 0;i < 100;i++){
        int num = new Random().nextInt(3);//0,1,2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                //classPath = "java.sql.Date";//报错，因为sql下没有空参构造器
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "Reflection.Person";
                break;
        }
        try {
            Object obj = getInstance(classPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
* 创建一个指定类的对象。
* classPath：指定类的全类名
* */
public Object getInstance(String classPath) throws Exception{
    Class clazz = Class.forName(classPath);
    return clazz.newInstance();
}
```

### Person例子

#### 自定义类

```java
@MyAnnotation(value="hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface{
    private String name;
    int age;
    public int id;
    public Person(){}
    @MyAnnotation(value="abc")
    private Person(String name){
        this.name = name;
    }
    Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }
    public String display(String interests,int age) throws NullPointerException,ClassCastException{
        return interests + age;
    }
    @Override
    public void info() {
        System.out.println("我是个人");
    }
    @Override
    public int compareTo(String o) {
        return 0;
    }
}
```

#### 自定义接口

```java
public interface MyInterface {
    void info();
}
```

#### 自定义注解

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "hello";
}
```

#### 自定义父类

```java
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    public void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }
}
```



### 获取当前运行时类的属性结构

**getField()：获取当前运行时类及其父类中声明为public访问权限的属性**

**getDeclaredFields()：获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）**

```java
@Test
public void test1(){
    Class clazz = Person.class;
    //获取属性结构
    //getField()：获取当前运行时类及其父类中声明为public访问权限的属性
    Field[] fields = clazz.getFields();
    for (Field f : fields){
        System.out.println(f);
    }
    //getDeclaredFields()：获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field f : declaredFields){
        System.out.println(f);
    }
}
```

**getModifiers()：获取对应的权限修饰符**

​		`System.out.print(Modifier.toString(modifiers) + "\t");`：显示public、private... ...

​		`System.out.println(modifiers);`：显示1、2

**getType()：获取对应的数据类型**

​		`System.out.print(type.getName() + "\t");`：显示数据类型：java.lang.String、int

**getName()：获取对应的变量名**

```java
public void test2(){
    Class clazz = Person.class;
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field f: declaredFields){
        //1.权限修饰符
        int modifiers = f.getModifiers();
        //显示对应权限：public private...
        System.out.print(Modifier.toString(modifiers) + "\t");
        //显示对应权限：1 2 ...
        //System.out.println(modifiers);
        
        //2.数据类型
        Class type = f.getType();
        //显示数据类型：java.lang.String、int
        System.out.print(type.getName() + "\t");
        
        //3.变量名
        String fName = f.getName();
        System.out.println(fName);
    }
}
```

### 获取运行时类的方法结构

**getMethods()：获取当前运行时类及其所有父类中声明为public权限的方法**

**getDeclaredMethods()：获取当前运行时类中声明的所有方法。（不包含父类中声明的所有方法）**

```java
@Test
public void test1(){
    Class clazz = Person.class;
    //getMethods()：获取当前运行时类及其所有父类中声明为public权限的方法
    Method[] methods = clazz.getMethods();
    for (Method m : methods){
        System.out.println(m);
    }
    System.out.println();
    //getDeclaredMethods()：获取当前运行时类中声明的所有方法。（不包含父类中声明的所有方法）
    Method[] declaredMethods = clazz.getDeclaredMethods();
    for (Method m : declaredMethods){
        System.out.println(m);
    }
}
```

#### 获取方法的完整结构内容

> ```java
> @Xxxx（想要拿到注解，生命周期一定要够 ）
> 权限修饰符  返回值类型  方法名(参数类型  形参名1, ...) throws XxxException{}
> ```



```java
@Test
public void test2(){
    Class clazz = Person.class;
    Method[] declaredMethods = clazz.getDeclaredMethods();
    for (Method m : declaredMethods){
        //1.获取方法声明的注解
        Annotation[] annos = m.getAnnotations();
        for (Annotation a : annos){
            System.out.print(a + "\t");
        }
        //2.权限修饰符
        System.out.print(Modifier.toString(m.getModifiers()) + "\t");
        //3.返回值类型
        System.out.print(m.getReturnType().getName() + "\t");
        //4.方法名
        System.out.print(m.getName());
        System.out.print("(");
        //5.形参列表
        Class[] parameterTypes = m.getParameterTypes();
        if (parameterTypes.length > 0){
            for (int i = 0;i < parameterTypes.length;i++){
                if (i == parameterTypes.length - 1){
                    System.out.print(parameterTypes[i].getName() + "args_" + i);
                    break;
                }
                System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
            }
        }
        System.out.print(")");
        //6.抛出的异常
        Class[] exceptionTypes = m.getExceptionTypes();
        if (exceptionTypes.length > 0) {
            System.out.print(" throws ");
            for (int i = 0;i < exceptionTypes.length;i++){
                if (i == exceptionTypes.length - 1){
                    System.out.print(exceptionTypes[i].getName());
                    break;
                }
                System.out.print(exceptionTypes[i].getName() + ",");
            }
        }
        System.out.println();
    }
}
```

### 其他运行时类的获取

#### 获取构造器结构

```java
@Test
public void test1(){
    Class clazz = Person.class;

    //getConstructors()：获取当前运行时类中声明为public的构造器
    Constructor[] constructors = clazz.getConstructors();
    for (Constructor c : constructors){
        System.out.println(c);
    }

    System.out.println();

    //getDeclaredConstructors()：获取当前运行时类中声明的所有的构造器
    Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
    for (Constructor c : declaredConstructors){
        System.out.println(c);
    }
}
```

#### 获取运行时类的父类

```java
@Test
public void test2(){
    Class clazz = Person.class;
    Class superclass = clazz.getSuperclass();
    System.out.println(superclass);
}
```

#### 获取运行时类的带泛型的父类

```java
@Test
public void test3(){
    Class clazz = Person.class;
    Type genericSuperclass = clazz.getGenericSuperclass();
    System.out.println(genericSuperclass);
}
```

#### 获取运行时类的带泛型的父类的泛型

```java
@Test
public void test4(){
    Class clazz = Person.class;
    Type genericSuperclass = clazz.getGenericSuperclass();
    ParameterizedType paramType = (ParameterizedType) genericSuperclass;
    //获取泛型类型
    Type[] actualTypeArguments = paramType.getActualTypeArguments();//获取实例中的类型参数
    System.out.println(actualTypeArguments[0].getTypeName());
    System.out.println(((Class)actualTypeArguments[0]).getTypeName());
}
```

#### 获取运行时类实现的接口

```java
@Test
public void test5(){
    Class clazz = Person.class;
    Class[] interfaces = clazz.getInterfaces();
    for (Class c : interfaces){
        System.out.println(c);
    }
    System.out.println();
    Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
    for (Class c : interfaces1){
        System.out.println(c);
    }
}
```

#### 获取运行时类所在的包

```java
@Test
public void test6(){
    Class clazz = Person.class;
    Package pack = clazz.getPackage();
    System.out.println(pack);
}
```

#### 获取运行时类声明的注解

```java
@Test
public void test7(){
    Class clazz = Person.class;
    Annotation[] annotations = clazz.getAnnotations();
    for (Annotation a : annotations){
        System.out.println(a);
    }
}
```

### 见名知意——获取指定

**调用运行时类中的指定结构：属性、方法、构造器**

#### 操作运行时类的指定属性

```java
/*
* 不需要掌握
* */
@Test
public void testField() throws Exception {
    Class clazz = Person.class;
    //创建运行时类的对象
    Person p = (Person) clazz.newInstance();
    //获取指定的属性：要求运行时类中属性为声明为public
    //通常不采用此方法
    Field id = clazz.getField("id");
    /*
    * 设置当前属性的值
    * set()：参数1：指明设置哪个对象的属性     参数2：将此属性设置为多少
    **/
    id.set(p,1001);
    /*
    * 获取当前属性的值
    * get()：参数1：获取哪个对象的当前属性值
    * */
    int pId = (int) id.get(p);
    System.out.println(pId);
}
```

#### 如何操作运行时类中的指定的属性 --- 需要掌握

```java
@Test
public void testField1() throws Exception{
    Class clazz = Person.class;
    //创建运行时类的对象
    Person p = (Person) clazz.newInstance();
    //1.getDeclaredField(String fieldName)：获取运行时类中指定变量名的属性
    Field name = clazz.getDeclaredField("name");
    //2.设置访问，保证当前属性是可访问的
    name.setAccessible(true);
    //3.获取、设置指定对象的属性值
    name.set(p,"Jack");
    System.out.println(name.get(p));
}
```

#### 如何操作运行时类中的指定的方法 --- 需要掌握

```java
@Test
public void testMethod() throws Exception{
    Class clazz = Person.class;
    //创建运行时类的对象
    Person p = (Person) clazz.newInstance();
    /*
    * 1.获取指定的某个方法
    * getDeclaredMethod()：参数1：指明获取的方法的名称    参数2：指明获取的方法的形参列表
    * */
    Method show = clazz.getDeclaredMethod("show", String.class);
    //2.设置访问，保证当前属性是可访问的
    show.setAccessible(true);
    /*
    * 3.调用invoke()：参数1：方法的调用者   参数2：给方法形参赋值的实参
    * invoke()的返回值即为对应类中调用的方法的返回值。
    * */
    Object returnValue = show.invoke(p, "CHN");//String nation = p.show("CHN")
    System.out.println(returnValue);
    System.out.println("********如何调用静态方法**********");
    //private static void showDesc()
    Method showDesc = clazz.getDeclaredMethod("showDesc");
    showDesc.setAccessible(true);
    //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
    Object returnValue1 = showDesc.invoke(Person.class);
    System.out.println(returnValue1);
}
```

#### 如何调用运行时类中的指定的构造器

```java
@Test
public void testConstructor() throws Exception {
    Class clazz = Person.class;
    //private Person(String name)
    /*
    * 1.获取指定的构造器
    * getDeclaredConstructor()：参数： 指明构造器的参数列表
    * **/
    Constructor constructor = clazz.getDeclaredConstructor(String.class);
    //2.保证此构造器是可访问的
    constructor.setAccessible(true);
    //3.调用此构造器创建运行时类的对象
    Person per = (Person) constructor.newInstance("Tom");
    System.out.println(per  );
}
```

## 每日一考

**1.写出获取Class实例的三种常见方式**

`Class clazz1 = String.class;`

`Class clazz2 = person.getClass();`

`Class clazz3 = Class.forName(String classPath);`//体现反射的动态性



**2.谈谈你对Class类的理解**

Class实例对应着加载到内存中的一个运行时类。



**3.创建Class对应运行时类的对象的通用方法，代码实现。以及这样操作，需要对应的运行时类构造器方面满足的要求**

```java
 Object obj = clazz.newInstance();//创建了对应的运行时类的对象
```

​	1.必须有空参的构造器

​	2.权限修饰符的权限要够。通常设置为public



**4.在工程或module的src下有名为“jdbc.properties”的配置文件，文件内容为：name = Tom。如何在程序中通过代码获取Tom这个变量值。代码实现**



**5.如何调用方法show()**

类声明如下：

```java
package com.atguigu.java;
class User{
	public void show(){
		syso("我是中国人");	
	}
}
```

```java
User user = (User)clazz.newInstance();
Method show = clazz.getDeclaredMethod("show");
show.setAccessible(true);
show.invoke(user);
```

## 反射的应用：动态代理

### 静态代理

静态代理举例

**特点：**代理类和被代理类在编译期间，就确定下来了。

#### 接口

```java
interface ClothFactory{
    void produceCloth();
}
```

#### 代理类

```java
//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory factory;//用被代理对象进行实例化

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}
```

#### 被代理类

```java
//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike生产一批运动服");
    }
}
```

#### 测试

```java
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类对象
        NikeClothFactory nike = new NikeClothFactory();
        //创建代理类的对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();

    }
}
```

### 动态代理的举例

#### 接口

```java
interface Human{
    String getBelief();
    void eat(String food);
}
```

#### 被代理类

```java
//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
```

#### 要想实现动态代理，需要解决的问题？

问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象

问题二：但通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。

#### 问题一

```java
class ProxyFactory{
    //调用此方法，返回一个代理类的对象。解决问题一
    public static Object getProxyInstance(Object obj){//obj：被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(), handler);
    }
}
```

```java
class MyInvocationHandler implements InvocationHandler {
    private Object obj;//需要使用被代理类的对象进行赋值

    public void bind(Object obj){
        this.obj = obj;
    }

    /*
     * 当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
     * 将被代理类要执行的方法a的功能就声明在invoke()中
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj, args);
        //上述方法的返回值就作为当前类中的invoke()的返回值。
        return returnValue;
    }
}
```

#### 测试

```java
public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance：代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("武汉热干面");

        System.out.println("**************************************");
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }
}
```

### 动态代理与AOP（Aspect Orient Programming）

#### 通用方法

```java
class HumanUtil{
    public void method1(){
        System.out.println("******************通用方法1******************");
    }

    public void method2(){
        System.out.println("******************通用方法2******************");
    }
}
```

```java
class MyInvocationHandler implements InvocationHandler {
    private Object obj;//需要使用被代理类的对象进行赋值

    public void bind(Object obj){
        this.obj = obj;
    }

    /*
     * 当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
     * 将被代理类要执行的方法a的功能就声明在invoke()中
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();

        //method：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj, args);

        util.method2();

        //上述方法的返回值就作为当前类中的invoke()的返回值。
        return returnValue;
    }
}
```

# Java8新特性

## Lambda表达式

Lambda表达式的使用举例：

```java
@Test
public void test1(){
    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("我爱武汉长江大桥");
        }
    };

    r1.run();

    System.out.println("*************************");

    Runnable r2 = () -> System.out.println("我爱硚口古田路");

    r2.run();
}

@Test
public void test2(){
    Comparator<Integer> com1 = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1,Integer o2) {
            return Integer.compare(o1,o2);
        }
    };

    int compare1 = com1.compare(12, 21);
    System.out.println(compare1);

    System.out.println("*********************");
    //Lambda表达式的写法
    Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1,o2);

    int compare2 = com2.compare(12, 21);
    System.out.println(compare2);

    System.out.println("*********************");
    //方法引用
    Comparator<Integer> com3 = Integer::compare;

    int compare3 = com3.compare(12, 21);
    System.out.println(compare3);
}
```

**1.举例：(o1,o2) -> Integer.compare(o1,o2);**

**2.格式：**

`->`：Lambda操作符 或箭头操作符

`->`左边：Lambda形参列表（其实就是接口中的抽象方法的形参列表 ）

`->`右边：Lambda体（其实就是重写的抽象方法的方法体）

**3.Lambda表达式的使用：（分6种情况介绍）**

  总结：

  `->`左边：Lambda形参列表的参数类型可以省略（类型推断）；如果Lambda形参列表只有一个参数，其一对()也可以省略

  `->`右边：Lambda体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），可以省略这一对{}和return关键字

4.Lambda表达式的本质：作为函数式接口的实例

5.如果一个接口中，只声明了一个抽象方法，则此接口就成为函数式接口。我们可以在一个接口上使用@Functionallnterface注解，这样做可以检查它是否是一个函数式接口。

6.所以以前用匿名实现类表示的现在都可以用Lambda表达式来写

### 语法格式一：无参，无返回值

```java
@Test
public void test1() {
    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("我爱武汉长江大桥");
        }
    };
    r1.run();
    System.out.println("*************************");
    Runnable r2 = () -> {
        System.out.println("我爱硚口古田路");
    };
    r2.run();
}
```

### 语法格式二：Lambda需要一个参数，但是没有返回值

```java
@Test
public void test2() {
    Consumer<String> con = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    };
    con.accept("陈多多驾到");
    System.out.println("***************************");
    Consumer<String> con1 = (String s) -> {
        System.out.println(s);
    };
    con1.accept("陈多多成功开溜");
}
```

### 语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“ 类型推断 ”

```java
@Test
public void test3(){
    Consumer<String> con1 = (String s) -> {
        System.out.println(s);
    };
    con1.accept("陈多多成功开溜");
    System.out.println("***************************");
    Consumer<String> con2 = (s) -> {
        System.out.println(s);
    };
    con2.accept("陈多多成功开溜");
}
```

```java
@Test
public void test4(){
    ArrayList<String> list = new ArrayList<>();//类型推断
    int[] arr = {1,2,3};//类型推断
}
```

### 语法格式四：Lambda若只需要一个参数时，参数的小括号可以省略

```java
@Test
public void test5(){
    Consumer<String> con2 = (s) -> {
        System.out.println(s);
    };
    con2.accept("陈多多成功开溜");
    System.out.println("***************************");
    Consumer<String> con3 = s -> {
        System.out.println(s);
    };
    con3.accept("陈多多成功开溜");
}
```

### 语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值

```java
@Test
public void test6(){
    Comparator<Integer> com1 = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        }
    };
    System.out.println(com1.compare(12,21));
    System.out.println("**************************");
    Comparator<Integer> com2 = (o1, o2) -> {
        System.out.println(o1);
        System.out.println(o2);
        return o1.compareTo(o2);
    };
    System.out.println(com2.compare(12,6));
}
```

### 语法格式六：当Lambda体只有一条语句时，return与大括号若有，都可以省略

```java
@Test
public void test7(){
    Comparator<Integer> com1 = (o1, o2) -> {
        System.out.println(o1);
        System.out.println(o2);
        return o1.compareTo(o2);
    };
    System.out.println(com1.compare(12,6));
    System.out.println("************************");
    Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
    System.out.println(com2.compare(12,6));
}
```

```java
@Test
public void test8(){
    Consumer<String> con1 = s -> {
        System.out.println(s);
    };
    con1.accept("来咯");
    System.out.println("*************");
    Consumer<String> con2 = s -> System.out.println(s);
    con2.accept("来咯");
}
```

## 函数式（Functional）接口

- 只包含一个抽象方法的接口，称为函数式接口

- 你可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常（即：非运行时异常），那么该异常需要在目标接口的抽象方法上进行声明）

- 我们可以在一个接口上使用@Functionallnterface注解，这样做可以检查它是否是一个函数式接口。同时**Javadoc**也会包含一条声明，说明这个接口是否是一个函数式接口。
- 在`java.util.function`包下定义了Java8的丰富的函数式接口

### Java内置四大核心函数式接口

|  函数式接口   | 参数类型 | 返回类型 |                             用途                             |
| :-----------: | :------: | :------: | :----------------------------------------------------------: |
|  Consumer<T>  |    T     |   void   |     对类型为T的对象应用操作，包含方法：void accept(T t)      |
|  Supplier<T>  |    无    |    T     |             返回类型为T的对象，包含方法：T get()             |
| Function<T,R> |    T     |    R     | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t) |
| Predicate<T>  |    T     | boolean  | 确定类型为T的对象是否满足某约束，并返回boolean值。包含方法：boolean test(T t) |

```java
public void happyTime(double money, Consumer<Double> con) {
    con.accept(money);
}
```

```java
@Test
public void test1(){
    happyTime(500, new Consumer<Double>() {
        @Override
        public void accept(Double aDouble) {
            System.out.println("滴答滴答，价格为：" + aDouble);
        }
    });
    System.out.println("***********");
    happyTime(400,money -> System.out.println("又买了一次，价格为：" + money));
}
```



```java
//根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
public List<String> filterString(List<String> list, Predicate<String> pre){
    ArrayList<String> filterList = new ArrayList<>();
    for (String s : list){
        if (pre.test(s)){
            filterList.add(s);
        }
    }
    return filterList;
}
```

```java
@Test
public void test2(){
    List<String> list = Arrays.asList("北京", "南京", "天津", "西京", "东京", "普京");
    List<String> filterStrs = filterString(list, new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.contains("京");
        }
    });
    System.out.println(filterStrs);
    List<String> filterStrs1 = filterString(list,s -> s.contains("京"));
    System.out.println(filterStrs1);
}
```

## 方法引用

### 方法引用的使用

**1.使用情境：**当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！

**2.方法引用，**本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以方法引用，也是函数式接口的实例。

**3.使用格式：**   `类(或对象) :: 方法名`

**4.具体分为如下的三种情况：**

**情况1**  对象 :: 非静态方法

**情况2**  类 :: 静态方法

**情况3**  类 :: 非静态方法

**5.方法引用使用的要求：**要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同！（针对于情况1和情况2）

#### 情况一：对象 :: 实例方法

> Consumer中的void accept(T t)
> PrintStream中的void println(T t)

```java
@Test
public void test11() {
    Consumer<String> con1 = str -> System.out.println(str);
    con1.accept("北京");
    System.out.println("***********");
    PrintStream ps = System.out;
    Consumer<String> con2 = ps::println;
    con2.accept("武汉");
}
```

> Supplier中的T get()
> Employee中的String getName()

```java
@Test
public void test2() {
    Employee emp = new Employee(1001, "Tom", 23, 5600);
    Supplier<String> sup1 = () -> emp.getName();
    System.out.println(sup1.get());
    System.out.println("*******************");
    Supplier<Integer> sup2 = emp::getAge;
    System.out.println(sup2.get());
}
```

#### 情况二：类 :: 静态方法

> Comparator中的int compare(T t1,T t2)
> Integer中的int compare(T t1,T t2)

```java
@Test
public void test3() {
    Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
    System.out.println(com1.compare(12, 21));
    System.out.println("***********************");
    Comparator<Integer> com2 = Integer::compare;
    System.out.println(com2.compare(12, 3));
}
```

> Function中的R apply(T t)
> Math中的Long round(Double d)

```java
@Test
public void test4() {
    Function<Double, Long> func = new Function<Double, Long>() {
        @Override
        public Long apply(Double aDouble) {
            return Math.round(aDouble);
        }
    };
    System.out.println("********************");
    Function<Double, Long> func1 = d -> Math.round(d);
    System.out.println(func1.apply(12.3));
    System.out.println("********************");
    Function<Double, Long> func2 = Math::round;
    System.out.println(func2.apply(12.7));
}
```

#### 情况三： 类 :: 实例方法（有难度）

> Comparator中的int compare(T t1,T t2)
> String中的int t1.compareTo(t2)

```java
@Test
public void test5() {
    Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
    System.out.println(com1.compare("abc", "abb"));
    System.out.println("***********");
    Comparator<String> com2 = String::compareTo;
    System.out.println(com2.compare("abb", "acb"));
}
```

> BiPredicate中的boolean test(T t1,T t2);
> String中的boolean t1.equals(t2)

```java
@Test
public void test6() {
    BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
    System.out.println(pre1.test("abc", "abb"));
    System.out.println("****************************");
    BiPredicate<String, String> pre2 = String::equals;
    System.out.println(pre2.test("abb", "acc"));
}
```

> Function中的R apply(T t)
> Employee中的String getName()

```java
@Test
public void test7() {
    Employee tom = new Employee(1001, "Tom", 12, 3000);
    Employee Alice = new Employee(1002, "Alice", 32, 5000);
    Function<Employee, String> func1 = e -> e.getName();
    System.out.println(func1.apply(tom));
    System.out.println("************************");
    Function<Employee, String> func2 = Employee::getName;
    System.out.println(func2.apply(Alice));
}
```

### 构造器引用

#### 一、构造器引用

和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。

抽象方法的返回值类型即为构造器所属的类的类型

> Supplier中的T get()
> Employee的空参构造器：Employee()

```java
@Test
public void test1() {
    Supplier<Employee> sup = new Supplier<Employee>() {
        @Override
        public Employee get() {
            return new Employee();
        }
    };
    sup.get();
    System.out.println("***************");
    Supplier<Employee> sup1 = () -> new Employee();
    System.out.println(sup1.get());
    System.out.println("***************");
    Supplier<Employee> sup2 = Employee::new;
    System.out.println(sup2.get());
}
```

> Function中的R apply(T t)

```java
@Test
public void test2(){
    Function<Integer, Employee> func1 = id -> new Employee(id);
    Employee employee = func1.apply(1001);
    System.out.println(employee);
    System.out.println("*****************");
    Function<Integer,Employee> func2 = Employee :: new;
    Employee employee1 = func2.apply(1002);
    System.out.println(employee1);
}
```

> BiFunction中的R apply(T t,U u)

```java
@Test
public void test3(){
    BiFunction<Integer, String, Employee> func1 = (id, name) -> new Employee(id, name);
    System.out.println(func1.apply(1001, "Tom"));
    System.out.println("*********************************");
    BiFunction<Integer, String, Employee> func2 = Employee::new;
    System.out.println(func2.apply(1002, "Alice"));
}
```

#### 二、数组引用

大家可以把数组看做是一个特殊的类，则写法与构造器引用一致。

> Function中的R apply(T t)

```java
@Test
public void test4(){
    Function<Integer,String[]> func1 = length -> new String[length];
    String[] arr1 = func1.apply(5);
    System.out.println(Arrays.toString(arr1));
    System.out.println("**************");
    Function<Integer,String[]> func2 = String[]::new;
    String[] arr2 = func2.apply(1);
    System.out.println(Arrays.toString(arr2));
}
```

## 强大的Stream API

**Stream到底是什么呢？**

是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列

“集合讲的是数据，Stream讲的是计算！”

> 注意：
>
> ①Stream自己不会存储元素
>
> ②Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream
>
> ③Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。



## Optional类

为了在程序中避免出现空指针异常而创建的。

常用的方法：

`ofNullable(T t)`

`orElse(T t)`