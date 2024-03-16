## Java语言概述

### 概述

#### Java语言的特点：跨平台性

因为有了JVM，同一个Java程序在三个不同的操作系统（Windows，Linux，Mac）中都可以执行，这样实现了Java程序的跨平台性。

#### Java语言运行机制及运行过程

###### 核心机制-Java虚拟机

①JVM是一个虚拟的计算机，具有指令集并使用不同的存储区域。负责执行指令，管理数据、内存、寄存器。

②对于不同的平台，有不同的虚拟机。

③只有某平台提供了对应的java虚拟机，java程序才可在此平台运行。

④Java虚拟机机制屏蔽了底层运行平台的差别，实现了<u>“一次编译，到处执行”。</u>

###### 核心机制-垃圾回收

1.不再使用的内存空间应回收—垃圾回收

​	①在C/C++等语言中，由程序员负责回收无用内存

​	②Java语言清楚了程序员回收无用内存的责任，他提供一种系统级县城跟踪储存空间的分配情况，并在JVM空闲时间，检查并释放哪些可以释放的储存空间。

2.垃圾回收在Java程序运行过程中自动进行，程序员无法精确控制和干预。

3.Java程序还会出现内存泄漏和内存溢出的问题吗？

#### Java环境搭建

主讲Java8，9~11属于新加功能

#### JDK，JRE，JVM的关系

JDK：Java开发工具包

​		包括了JRE，不必再单独安装JRE。

​		编译工具（javac.exe）打包工具（jar.exe）

JRE：Java运行环境

​		包括Java虚拟机和Java程序所需的核心类库等。

​				JDK = JRE+开发工具集（例如Javac编译工具等）

​				JRE = JVM+Java SE标准类库

下载并安装JDK

​		[www.oracle.com]()

​		[java.sun.com]()

​		建议：安装路劲不要有中文或空格等特殊符号

#### Java API文档

（应用程序编程接口）是Java提供的基本编程接口。

Java语言提供了大量的基础类，因此Oracle也为这些基础类提供了相应的API文档，用于告诉开发者如何使用这些类，以及这些类里包含的方法。

下载API：

www.oracle.com/technetwork/java/javase/downloads/index.html→Additional Rresources-JavaSE 8 Documentation下载





###  常用DOS命令

dir：列出当前目录下的文件及文件夹

md：创建目录

rd：删除目录

cd：进入指定目录

cd..：返回上一级目录

cd\：返回到根目录

del：删除文件

exit：退出dos

### 开发体验

##### Hello World

```java
public class HelloWorld{
        public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
```

（.java文件）通过 [javac.exe编译] 得到（.class文件）再通过 [java.exe] 得到结果。

源文件														字节码文件

##### 注释（Comment）

​	//单行注释，/*多行注释，文档注释（Java特有）

###### 文档注释的使用

格式：

/**

​	@author 指定Java程序的作者

​	@version 指定原文件的版本

*/

注释内容可以被JDK提供的工具javadoc所解析，生成一套以网页文件形式体现的该程序的说明文件。

```java
/**
	文档注释
	@author chuge

	@version v1.0 my first 
*/
public class HelloWorld{
/**
	如下的方式是main(),作用：程序的入口。
*/
    	public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
```

使用方法：javadoc -d (文件夹名字) -author -version （文件名.java）9

注意：多行注释不能嵌套使用。

### 总结

在一个java源文件中可以声明多个class，但是，只能最多有一个类声明为public的。而且要求声明为public的类的类名必须与源文件名相同。

##### 输出语句

System.out.println():先输出数据，然后换行。

System.out.print():只输出数据。

每一行执行语句都以";"结束。

编译以后，会生成一个或多个字节码文件。字节码文件的文件名与java源文件中的类名相同。

##### 每日一考

1.JDK,JRE,JVM三者之间的关系，以及JDK,JRE包含的主要结构有哪些？

2.为什么要配置path环境变量？如何配置？	

答：希望在任何环境下都可以执行java的开发工具。

3.常用的几个命令行操作都有哪些？

## Java基本语法

### 标识符的使用

1.凡是可以自己起名字的地方都叫标识符

比如：类名、变量名、方法名、接口名、包名...

2.标识符的命名规则：

> 由26个字母大小写，0-9，_或$组成
>
> 数字不可以开头
>
> 不可以使用关键字和保留字，但能包含关键字和保留字
>
> Java中严格区分大小写，长度无限制
>
> 标识符不能包含空格

3.Java中的名称命名规范：

包名：多单词组成是所有字母都小写：xxxyyyzzz

类名、接口名：多单词组成时，所有单词的首字母大写：XxxYyyZzz（驼峰命名）

变量名、方法名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写：xxxYyyZzz

常量名：所有字母都大写，多单词时每个单词用下划线链接：XXX_YYY_ZZZ

4.起名字为了提高阅读性，要尽量有意义。

java采用unicode字符集，因此标识符也可以用汉字声明，但是不建议使用。

## Java定义的数据类型

### 基本数据类型

#### 整形

byte \ short \ int \long

#### 浮点型

float \ double

#### 字符型

char

#### 布尔型

boolean

①只能取两个值之一：true/false

②常常在条件判断、循环结构中使用 

### 基本数据类型之间的运算规则

前提：只是7种基本数据类型变量间的运算，不包含Boolean类型

#### 自动类型提升

结论：当容量小的数据类型的变量与容量大的数据类型的变量做运算时，结果自动提升为容量大的数据类型。

当byte、char、short三种类型的变量做运算时，结果为int类型

java做运算时如果操作数均在int范围内，那么一律在int的空间内运算

依次递增：byte、char、short==》int--》ong

依次递增：float--》double

注意：此处容量大小指的是，表示数的范围的大和小。比如float容量大于long

#### 强制类型转换

自动类型提升运算的逆运算。

1.使用强转符号：（）

2.可能导致精度损失

```java
double d1 = 12.9;
int i1 = (int)d1;//截断操作
//损失精度
System.out.println(i1);
```



### 引用数据类型

#### 类（class）

#### 接口（interface）

#### 数组（array）

### 字符串类型：String

①String不是基本数据类型，属于引用数据类型

②使用方式与基本数据类型一致。例如：String str = “ABCD”;

③一个字符串可以串接另一个字符串，也可以直接串接他类型的数据。例如：

str = str + "xyz";

int n = 100;

str = str + n;

④String可以和8种基本数据类型变量做运算。

⑤运算的结果仍然是String类型

## 进制

对于整数有四种表达方式：

### 二进制

0,1，满2进1,。以0b或0B开头。

### 十进制

0-9，满10进1.

### 八进制

0-7，满8进1.以数字0开头表示。

### 十六进制

0-9及A-F，满16进1.以0x或0X开头表示。此处A-F不区分大小写

如：0x21AF + 1 = 0X21B0

### 每日一考

## 运算符

### 算术运算符

+（正号），-（负号），+，-，*，/，%，前++，后++，前--，后--，+（连接运算符） 

### 赋值运算符

##### 符号：=

当“=”两侧数据类型不一致时，可以使用自动类型转换或使用强制类型转换原则进行处理。

支持连续赋值。

##### 扩展赋值运算符：+=，-=，*=，/=，%=

### 比较运算符（关系运算符）

ture or false

### 逻辑运算符

| &逻辑与  | \|逻辑或   | !逻辑非   |
| :------: | ---------- | --------- |
| &&短路与 | \|\|短路或 | ^逻辑异或 |

|   a   |   b   | a&b  | a&&b | a\|b | a\|\|b |  !a  | a^b  |
| :---: | :---: | :--: | :--: | :--: | :----: | :--: | :--: |
| true  | true  |  √   |  √   |  √   |   √    |  X   |  X   |
| true  | false |  X   |  X   |  √   |   √    |  X   |  √   |
| false | true  |  X   |  X   |  √   |   √    |  √   |  √   |
| false | false |  X   |  X   |  X   |   X    |  √   |  X   |

①逻辑运算符操作的都是Boolean类型变量

### 位运算符

操作的都是整型数据

![image-20210112211234392](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210112211234392.png)

"<<"：左移两位，即2的2次方，三位是三次方。没向左移一位，即成2。 

">>"：右移，与左移相反，是除

### 三元运算符

**1.**结构：**（条件表达式）？表达式1：表达式2；**

**2.**说明：

①条件表达式的结果为boolean类型

②根据条件表达式真或假，决定执行表达式1还是表达式2.

如果条件表达式为**true**，运算后结果是**表达式1**；

如果条件表达式为**false**，运算后结果是**表达式2**。

③**表达式1**和**表达式2**要求是一致的，必须是**同种类型**。

④三元运算符可以嵌套使用。

```java
(m>n)?2:"n大";//编译错误，无法统一类型③

String maxStr = (m>n)?"m大":"n大";//编译正确

//如果m>n，结果为false，可能是m=n。

//因此，可写成↓，进行套娃④

String maxStr = (m>n)?"m大":((m == n)?"m和n相等":"n大");
```

**3.**凡是可以使用**三元运算符**的地方，都可以改写为**if-else**

​	反之，不成立。**if-else**不一定可以改写为**三元运算符**

**4.**如果程序既可以使用**三元运算符**，又可以使用**if-else**。

​	**优先**选择**三元运算符**。

​	**原因：**简洁、执行效率高。

### 运算符的优先级

## ※程序流程控制

流程控制语句是用来控制程序中各语句执行顺序的语句，可以把语句组合成能完成一定功能的小逻辑块。

其流程控制方式采用结构化程序设计中规定的**三种基本流程结构**，即：

### **顺序结构**

程序从上到下逐行地执行，中间没有任何的判断和跳转。

### **分支结构**

①根据条件，选择性地执行某段代码。

②有if..else和switch-case两种分支语句。

### **循环结构** 

①根据循环条件，重复性的执行某段代码。

②有while、do..while、for三中循环语句。

③注：JDK1.5提供了foreach循环，方便的遍历集合、数组元素。

**if - else 举例**

```java
public class dayday1{
    public static void main(String[] args){
        int age = 300;
        if (age>250){
            System.out.println("no");
        }else if(age<80){
            System.out.println("年仅"+age+",而已啦");
        }else{
            System.out.println("yes");
        }
    }
}
```

## DAY3每日一考

1." & "和“ && ”的异同

&：逻辑与

&&：短路与

## 从键盘获取数据

如何从键盘获取不同类型的变量：需要使用Scanner类



具体实现步骤：

1.导包：import java.unti.Scanner;

→import：导入

2.Scanner的实例化：Scanner scan = new Scanner(System.in);

3.调用Scanner类的相关方法，来获取指定类型的变量

<u>对于char型的获取，Scanner没有提供相关方法。只能获取一个字符串。</u>

**注意：**如果输入的数据类型与要求的类型不匹配，会报异常：InputMisMatchExcetion导致程序终止。

```java
import java.util.Scanner;

public class dayday1{
    public static  void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println(num);
    }
}
```

## 就近原则

**if-else遵循就近原则**

```java
int a = 4;
int b = 1;
if(a>3)
    if(b>2)
        System.out.println("b大于2");
else//就近原则
    System.out.println("a = "+a);
//此时，else对应第二个if	
```

## 定义随机数

```java
int value = (int)(Math.random() * 90 + 10);
//[0.0, 1.0) --> [10.0, 100.0) --> [10, 99]
```

公式（？）

```java
[a, b] : (int)(Math.random() * (b - a + 1) + a);
```

## if..else

```java
import java.util.Scanner;
public class day {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入身高：(cm)");
        int height = scan.nextInt();
        System.out.println("请输入财富：（千万）");
        double wealth = scan.nextDouble();
        /*
        方式1：
        System.out.println("是否英俊 (true/false)");
        boolean isHandsome = scan.nextBoolean();
        */
        /*方式二：*/
        System.out.println("是否英俊：（是/否）");
        String isHandsome = scan.next();
        //String不能用 == 来比较， 要用equals
        if(height >=  180 && wealth >= 1 && isHandsome.equals("是")){
            System.out.println("冲冲冲");
        }else if(height >=  180 || wealth >= 1 || isHandsome.equals("是")){
            System.out.println("冲冲");
        }else{
            System.out.println("冲毛");
        }
    }
}

```

### 说明

1、else结构是可选的

2、针对于条件表达式：

​		①如果多个表达式之间是“互斥”关系（或没有交集的关系），哪个判断和执行语句声明在上面还是下面无所谓；

​		②如果多个条件表达式之间有交集的关系，需要根据实际情况，考虑清楚应该将哪个结构声明在上面；

​		③如果多个条件表达式之间有包含关系，通常情况下，需要将范围小的声明在范围大的上面。否则，范围小的就没机会执行了

3、if-else结构是可以相互嵌套的。

4、如果if-else结构中的执行语句只有一行时，对应的一对{ }	可以省略。但是不建议省略。

## swith..case

①根据switch表达式中的值，依次匹配各个case中的常量。一旦匹配成功，则进入相应的case结构中，调用其执行语句。当调用完执行语句以后，则仍然继续向下执行其他case结构中的执行语句，直到遇到break关键字或此switch-case结构末尾结束为止

②break，可以使用在switch-case结构中，表示一旦执行到此关键字，就跳出switch-case结构

③switch结构中的表达式，只能是如下的六种数据类型之一：

byte、short、char、int、枚举类型（JDK5.0新增）、String类型（JDK7.0新增）

④case之后只能声明常量。不能声明范围。

⑤break关键字是可选的，并非一定要加入。绝大部分情况需要加

⑥default：相当于if-else结构中的个else，default结构是可选的

### 说明

1.凡是可以使用switch-case的结构，都可以转换成if-else。反之，不成立

2.当发现既可以使用switch-case，（同时，switch中表达式的情况不太多）

又可以使用if-else的时候，优先使用switch-case

原因：switch-case执行效率稍高

## 循环结构

### 循环结构分类

for循环

while循环

do-while循环

### 循环结构的四要素

初始化条件 --> 是Boolean类型

循环条件

循环体

迭代条件

### for循环的结构

```java
for(1;2;4){
	3；
}
```

### while循环

#### while循环的结构

```java
1

while(2){

	3;

	4;

}
```



执行过程：1 — 2 — 3 — 4 — 2 — 3 — 4 ... 2



说明：

1.写while循环千万小心不要丢了迭代条件。一旦丢了，可能导致死循环

2.写程序，要避免出现死循环。

3.for循环和while循环可以相互转换

区别：for循环和while循环的初始化条件部分的作用范围不同



算法特征：有限性



#### do-while

说明：do-while循环至少会执行一次循环体



题目：从键盘读入个数不确定的整数，并判断读入的正数和负数的个数，输入0时结束程序

```java
import java.util.Scanner;
public class dowhileTest {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int sum1 = 0;
        int sum2 = 0;
        while(true){
            int n = scan.nextInt();

            if(n > 0){
                sum1++;
            }else if(n < 0){
                sum2++;
            }else{
                break;
            }
        }
        System.out.println("正数的个数是：" + sum1);
        System.out.println("负数的个数是："  +sum2);
    }
}
```

说明：

1.不在循环条件部分限制次数的结构：for(;;) 或 while(true)

2.结束循环的方式

​		方式一：循环条件部分返回false

​		方式二：在循环体中执行break

## 嵌套循环

### 嵌套循环的使用

1.嵌套循环：将一个循环结构A声明在另一个循环结构B的循环体中，就构成了嵌套循环

2.

外层循环：循环结构B

内层循环：循环结构A

3.说明

①内层循环结构遍历一遍，只相当于外层循环循环体执行了一次

②假设外层循环需要执行m次，内层循环需要执行n次。则内层循环的循环体一共执行了n * m次

**获取当前时间距离1970-01-01 00：00：00到此时此刻的毫秒数**

`long start = System.currentTimeMillis();`放在程序开头

`long end  = System.CurrentTimeMillis()`;放在程序结尾

可以用end - start算出程序运行时间

**根号/开方**

`Math.sqrt(i);`

## 特殊关键字的使用

### break

### continue

|          |       使用范围       | 循环中使用的作用（不同点） | 相同点                     |
| :------: | :------------------: | :------------------------: | -------------------------- |
|  break   | swit-case/循环结构中 |        结束当前循环        | 关键字后面不能声明执行语句 |
| continue |      循环结构中      |        结束当次循环        | 关键字后面不能声明执行语句 |

```java
public class speKeyword {
    public static void main(String[] args){
        for(int i = 1;i <= 10;i++){
            if(i % 4 == 0){
                /*break;//1,2,3*/
                continue;//1,2,3,5,6,7,9,10
            }
            System.out.println(i);
        }
    }
}
```

```java
public class speKeyword {
    public static void main(String[] args){
        for (int j = 1;j <= 4;j++){
            for(int i = 1;i <= 10;i++){
                if(i % 4 == 0){
                    /**break;默认跳出包裹此关键字最近的一层循环
                    //123
                    //123
                    //123*/
                    continue;
                    //123567910tia跳出包裹此关键字最近的一次循环
                    //123567910
                    //123567910
                    //123567910
                }
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
```

### 结束指定标识的一层循环

```java
public class speKeyword {
    public static void main(String[] args){
        label:for (int j = 1;j <= 4;j++){
            for(int i = 1;i <= 10;i++){
                if(i % 4 == 0){
                    break label;
                }
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
```

javac -encoding utf-8 FamAccount.java

### 循环结构是如何最后退出循环的，有哪些不同的情况请说明

①循环条件返回false

②在循环体内，一旦执行到break，跳出循环

衡量一个功能代码的优劣

1.正确性

2.可读性

3.健壮性

4.高效率与低存储：时间复杂度、空间复杂度（衡量算法的好坏）

## 数组

### 数组的概念

1.数组的理解：数组（Array），是多个相同类型数据按一定顺序排列的集合，并使用一个名字命名，并通过编号的方式对这些数据进行统一管理

2.数组相关的概念

> 数组名
>
> 元素
>
> 角标、下标、索引
>
> 数组的长度：元素的个数

3.数组的特点：数组是有序排列的

1）数组属于引用数据类型的变量

2）创建数组对象会在内存中开辟一整块连续的空间

3）数据的长度一旦确定，不能修改

4.数组的分类

1）按照维数：一维数组、二维数组...多维数组

2）按照数组元素的类型：基本数据类型元素的数组、引用数据类型元素的数组

5.一维数组的使用

1）一维数组的声明和初始化

```java
public class Array {
    public static void main(String [] args){
        //1.一维数组的声明和初始化
        int num;//声明
        num = 10;//初始化
        int id = 1001;//声明+初始化

        int[] ids;//声明
        //1.1静态初始化：数组的初始化和数组元素的赋值操作同时进行
        ids = new int[]{1001, 1002, 1003, 1004, 1005};
        //1.2动态初始化：数组的初始化和数组元素的赋值操作分开进行
        String[] names = new String[5];

        //总结：数组一旦初始化完成了，其长度就确定了
    }
}
```

2）如何调用数组的指定位置的元素

```java
//2.如何调用数组的指定位置的元素：通过角标的方式调用
//数组的角标（或索引）从0开始，到数组长度-1结束
names[0] = "kkk";
names[1] = "aaa";
names[2] = "bbb";
names[3] = "ccc";
names[4] = "ddd";
```

3）如何获取数组的长度

```java
//属性：length
System.out.println(names.length);
System.out.println(ids.length);
```

4）如何遍历数组

```java
for (int i = 0;i < names.length;i++){
    System.out.println(names[i]);
}
```

5）数组元素的默认初始化值

```java
 /*
 *5.数组元素的默认初始化值
 *   >数组元素是整型：0
 *   >数组元素是浮点型：0.0
 *   >数组元素是char型：0或'\u0000'，而非'0'
 *   >数组元素是Boolean型：false
 *
 *   >数组元素是引用数据类型：null
 */
 int[] arr = new int[4];
 for(int i = 0;i < arr.length;i++){
     System.out.println(arr[i]);
 }
```

6）数组的内存解析

### 学生成绩

```java
import java.util.Scanner;
public class Array {
    public static void main(String [] args){
        //1.使用scanner读取学生个数
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入学生个数：");
        int number = scan.nextInt();

        //2.创建数组，存储学生成绩：动态初始化
        int[] score = new int[number];
        int maxScore = 0;
        
        //3，给数组中的元素赋值，获取元素最大值：最高分
        for(int i = 0;i < score.length;i++){
            System.out.println("请输入第" + (i+1) + "个学生的成绩：");
                score[i] = scan.nextInt();
                if(maxScore < score[i]){
                    maxScore = score[i];
                }
        }

        //4.根据每个学生成绩与最高分的差值，得到每个学生的等级，并输出等级和成绩
        char level;
        for(int i = 0;i < score.length;i++){
            if(maxScore - score[i] <= 10){
                level = 'A';
            } else if (maxScore - score[i] <= 20) {
                level = 'B';
            }else{
                level = 'C';
            }
            System.out.println("第" + (i+1)+ "个学生的成绩是" + score[i] + ",等级是：" + level);
        }
    }
}
```

### 多维数组

> 从数组底层的运行机制来看，其实没有多维数组
>
> 多维数组的元素，就是数组

#### 二维数组

##### 1.理解

二维数组可以看作是一维数组（Array1）作为另一个一维数组（Array2）的元素存在

##### 2.二维数组的使用

①二维数组的声明和初始化

```java
int[] arr = new int{1, 2, 3};//一维数组
//静态初始化
int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8}};
//动态初始化1
String[][] arr2 = new String[3][2];
//动态初始2
String[][] arr3 = new String[3][];
//类型推断写法
int[] arr4 = {1, 2, 3, 4, 5};
```

②如何调用数组的指定位置的元素

```java
//输出arr1的2
System.out.println(arr1[0][1]);
//输出arr2的第二行第二列元素
System.out.println(arr2[1][1]);//null,没有元素
//输出arr3的元素
System.out.printfln(arr3[1][0]);//报错：NullPointerException,空指针
//防止报错，提前声明长度
arr3[1] = new String[4];
```

③如何获取数组的长度

```java
System,out.println(arr1.length);//3
System,out.println(arr1[1].length);//2
```

④如何遍历数组

```java
for(int i = 0;i < arr1.length;i++){
	for(int j = 0;j < arr1[i].length;j++){
		System.out.print(arr1[i][j] + " ");
	}
	System.out.println();
}
```

> 规定：二维数组分为外层数组的元素，内层数组的元素

```java
int[][] arr = new int[4][3];
外层元素：arr[0],arr[1]..
内层元素：arr[0][0],arr[1][2]..
```

⑤元素的默认初始化值

```java
int[][] arr = new int[4][3];
System.out.println(arr[0]);//地址值：[I@1b6d3586，[→一维的，I→int，@后面是地址值
System.out.println(arr[0][0]);//0
System.out.println(arr);//[[I@4554617c，两个[[,说明二维数组

针对于初始化方式一：比如：int[][] arr = new int[4][3];
	外层元素的初始化值为：地址值
	内层元素的初始化值为：与一维数组初始化情况相同
针对于初始化方式二：比如int[][] arr = new int[4][];
	外层元素的初始化值为：null
    内层元素的初始化值为：不能调用，否则报错
```

⑥数组的内存解析

#### 不同类型的一维数组元素的默认初始化值

> 整型：0
>
> 浮点型：0.0
>
> char：0
>
> boolean：false
>
> 引用类型：null

#### 杨辉三角（二维数组）

使用二维数组打印一个10行杨辉三角。

【提示】

1.第一行有1个元素，第n行有n个元素

2.每一行的第一个元素和最后一个元素都是1

3.从第三行开始，对于非第一个元素和最后一个元素的元素

即`yanghui[i][j] = yanghui[i - 1][j - 1] + yanghui[i - 1][j];`

```java
int[][] yangHui = new int[10][];
for(int i = 0;i < yangHui.length;i++){
    yangHui[i] = new int[i + 1];//给外层数组创建内层数组
    yangHui[i][0] = yangHui[i][i] = 1;//给首末元素赋值
    //给每行非首末元素赋值
    if(i > 1){
        for(int j = 1;j < yangHui[i].length - 1;j++){
            yangHui[i][j] = yangHui[i - 1][j] + yangHui[i - 1][j - 1];
        }
    }
}
for(int i = 0;i < yangHui.length;i++){
    for(int j = 0;j < yangHui[i].length;j++){
        System.out.print(yangHui[i][j] + "\t");
    }
    System.out.println();
}
```

拓展笔试题

创建一个长度为6的int型数组，要求数组元素的值在1-30之间，且是随即赋值。

同时，要求元素的值各不相同。

#### 数组中涉及的常见算法

>1.数组元素的赋值（杨辉三角、回形数等）
>
>2.求数值型数组中元素的最大值、最小值、平均数、总和等
>
>3.数组的复制、反转、查找（线性查找、二分法查找）
>
>4.数组元素的排序算法

##### 算法考查（2）

定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，

然后求出所有元素的最大值，最小值和值。

要求：所有随机数都是两位数

##### 查找（或搜索）

###### 线性查找：

```java
String[] arr = new String[]{"JJ","DD","MM","BB","GG","AA"};
String dest = "BB";
boolean = isFlag = true;
for(int i = 0;i < arr.length;i++){
    if(dest.equals(arr[i])){
        System.out.println("找到了指定元素，位置为" + i);
        isFlag = false;
        break;
    }
}
if(isFlag){
    System.out.println("很遗憾，没找到");
}
```

###### 二分法查找：

前提：所要查找的数组必须有序

```java
int[] arr2 = new int[]{-98,-34,2,34,54,,55,123,234,546};
int dest = -34;
int head = 0;//初始首索引
int end = arr2.length - 1;//初始末索引
boolean isFlag = true;
while(head <= end){
    int mid = (head + end) / 2;
    if(dest1 == arr2[mid]){
        System.out.println("找到了指定的元素，位置为：" + mid);
        isFlag = false;
        break;
    }else if(arr2[mid] > dest){
        end = mid - 1;
    }else{//arr2[mid] < dest 
        head = mid + ;
    }
}
if(isFlag){
    System.out.println("未找到指定元素");
}
```

##### 排序

通常来说，排序的目的是快速查找

###### 衡量排序算法的优劣

1.时间复杂度：分析关键字的比较次数和记录的移动次数

2.空间复杂度：分析排序算法中需要多少辅助内存

3.稳定性：若两个记录A和B的关键字值相等，但排序后A、B的先后次序保持不变，则称这种排序算法是稳定的。 	

###### 分类

内部排序和外部排序

**内部排序**：整个排序过程不需要借助于外部存储器（如磁盘等），所有排序操作都在内存中完成

**外部排序**：参与排序的数据非常多，数据量非常大，计算机无法把整个排序过程放在内存中完成，必须借助于外部存储器（如磁盘）。外部排序最常见的是多路归并排序。可以认为外部排序是由多次内部排序组成

###### 十大内部排序算法

**选择排序**：直接选择排序、<u>堆排序</u>

**交换排序**：冒<u>泡排序、快速排序</u>

**插入排序**：直接插入排序、折半插入排序、Shell排序

<u>**归并排序**：</u>

不常见 ↓

**桶式排序**：

**基数排序**：

> 详情见《附录：尚硅谷 _ 宋红康 _ 排序算法.pdf》

#### 算法五大特征

![image-20210628121905719](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210628121905719.png)

### Arrays工具类的使用

java.util.Arrays类即为操作数组的工具类，包含了用来操作数组（比如排序和搜索）的各种方法

| 序号 | 代码                              | 功能                                 |
| ---- | --------------------------------- | ------------------------------------ |
| 1    | boolean equals(int[] a,int[] b)   | 判断两个数组是否相等                 |
| 2    | String toString(int[] a)          | 输出数组信息                         |
| 3    | void fill(int[] a,int val)        | 将指定值填充到数组中                 |
| 4    | void sort(int[] a)                | 对数组进行排序                       |
| 5    | int binarySearch(int[] a,int key) | 对排序后的数组进行二分法检索指定的值 |

### 数组常见异常

ArrayExceptionTest

**1.数组角标越界的异常：ArrayIndexOutOfBoundsExcetion**

```java
合理范围：[0,arr.length - 1];
越界：arr[-1],arr[arr.length]
```

**2.空指针异常：NullPointerException**

情况1：

```java
int[] arr1 = new int[]{1,2,3};
arr1 = null;
System.out.println(arr1[0]);
```

情况2：

```java
int[][] arr2 = new int[4][];
System.out.println(arr2[0]);
```

情况3：

```java
String[] arr3 = new String[]{"AA","BB","CC"};
arr3[0] = null;
System.out.println(arr3[0].toString());
```



### 数据结构

1.数据与数据之间的逻辑关系：集合、一对一、一对多、多对多

2.数据的存储结构：

线性表（一对一）：顺序表（比如：数组）、链表、栈、队列

树形结构（一对多）：二叉树

图形结构（多对多）：

### 算法

排序算法：

搜索算法：

## 面向对象

1.Java类及类的成员：属性、方法、构造器；代码块、内部类

2.面向对象的三大特征：封装性、继承性、多态性 （抽象性）

3.其他关键字：this、super、static、final、abstract、interface、package、import ... ...

### 面向过程（POP) 与 面向对象（OOP）

二者都是一种编程思想，面向对象是相对于面向过程而言的。

面向过程，<u>强调的是功能行为，以函数为最小单位，考虑怎么做</u>

面向对象，将功能封装进对象，<u>强调具备了功能的对象，以类/对象为最小单位，考虑谁来做。</u>

面向对象更加强调运用人类在日常的思维逻辑中采用的思想方法与原则，如抽象、分类、继承、聚合、多态等。

**人把大象装进冰箱**

1.面向过程：强调的是功能行为，以函数为最小单位，考虑怎么做

①把冰箱门打开

②抬起大象，塞进冰箱

③把冰箱门关闭

2.面向对象：强调具备了功能的对象，以类/对象为最小单位，考虑谁来做。

```java
人{
	打开（冰箱门）{
		冰箱.开开（）；
	}
	抬起（大象）{
		大象.进入（冰箱）；
	}
	关闭（冰箱）{
		冰箱.闭合（）；
	}
}
冰箱{
	开开（）{}
	闭合（）{}
}
大象{
	进入（冰箱）{	
	}
}
```

程序员从面向过程的执行者转化成了面向对象的个指挥者



面向对象分析方法分析问题的思路和步骤：

根据问题需要，选择问题所针对的<u>现实世界中的实体</u>

从试题中寻找解决问题相关的属性和功能，这些属性和功能就形成了<u>概念世界中的类</u>

把抽象的实体用计算机语言进行描述，<u>形成计算机世界中类的定义</u>。即借助某种程序语言，把类构成计算机能够识别和处理的数据结构

将<u>类实例化成计算机世界中的对象。</u>对象是计算机世界中解决问题的最终工具



### 面向对象的两个要素（类、对象）

类（Class）和对象（Object）是面向对象的核心概念

#### 类

对一类事物的描述，是抽象的、概念上的定义



#### 对象

是实际存在的该类事物的每个个体，因而也称为实例（instancen）



![image-20210628171012578](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210628171012578.png)

类 = 抽象概念的人

对象 = 实实在在的某个人

面向对象程序设计的重点是<u>类的设计</u>

类的设计，<u>其实就是类的成员的设计</u>

“万事万物皆对象”

一、设计类，其实就是设计类的成员

属性：对应类中的成员变量

行为：对应类中的成员方法

Field = 属性 = 成员变量，Method = （成员）方法 = 函数

> 属性 = 成员变量 = field = 域、字段

> 方法 = 成员方法 = 函数 = method

> 创建类的对象 = 类的实例化 = 实例化类



二、类和对象的使用（面向对象思想落地的实现）：

1.创建类，设计类的成员

2.创建类的对象

3.通过 ”对象.属性“ 或 ”对象.方法“ 调用对象的结构



三、如果创建了一个类的多个对象，则每个对象都有独立的拥有一套类的属性（非static

意味着：如果我们修改一个对象的属性a，则不影响另一个对象属性a的值



四、对象的内存解析

![image-20210629144048656](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210629144048656.png)

### 类

#### 属性的使用

属性（成员变量） VS 局部变量

**1.相同点：**

​	①定义变量的格式：数据类型 变量名 = 变量值；

​	②先声明，后使用

​	③变量都有其对应的作用域

**2.不同点：**

<u>**①**在类中声明的位置不同</u>

​	**属性：**直接定义在类的一对{}内

​	**局部变量：**声明在方法内、方法形参、代码块内、构造器形参、构造器内部变量



```java
class User{
    //属性（或成员变量）
	String name;
	int age;
	boolean isMale;
    
    public void talk(String language){//language:形参，也是局部变量
        System.out.println("使用的是" + language);
    }
    
    public void eat(){
        String food = "烧饼";//局部变量
        System.out.println("我喜欢吃:" + food);
    }
}
```





<u>②关于权限修饰符的不同</u>

​	**属性：**可以在声明属性时，指明其权限，使用权限修饰符

​			常用的权限修饰符：private、public、缺省、protracted ——》封装性

​			目前。声明属性，都是用缺省

​			属性前面，什么都没写，就是默认缺省

```java
String name;
private String name;//非缺省
```

​	**局部变量：**不可以使用权限修饰符。

③默认初始化的情况：

属性：类的属性，根据其类型，都有默认初始化值

> 整型（byte、short、int、long）：0
>
> 浮点型（float、double）：0.0
>
> 字符型（char）：0	或 ‘\u0000’
>
> 布尔型（boolean）：false



> 引用数据类型（类、数组、接口）：null 

局部变量：没有默认初始化值

​		意味着，我们在调用局部变量之前，一定要显示赋值

​		特别地：形参在调用时，我们赋值即可

④在内存中加载的位置：

属性：加载到堆空间中（非static）

局部变量：加载到栈空间

#### 类中方法的声明和使用

方法：描述类应该具有的功能。

比如：Math类：sqrt() \ random() \ ...

​			Scanner类：nextXxx() ...

​			Arrays类：sort() \ binartSearch() \ toString() \ equals() \ ...

##### 1.举例：

public void eat(){}						无形参，无返回值

public void sleep(int hour){}	有形参，无返回值

public String getName(){}		无形参，有返回值

public String getNation(){}		有形参，有返回值



##### 2.方法的声明：

权限修饰符	返回值类型	方法名（形参列表）{

​									方法体

​							}

##### 3.说明：

​		①关于权限修饰符：默认方法的权限修饰符都是用public

​					Java规定的4种权限修饰符：private、public、缺省、protected

​		②返回值类型：有返回值 vs 无返回值

​				②.1	如果方法有返回值，则必须在方法声明时，指定返回值的类型。

​									同时，方法中，需要使用return关键字来返回指定类型的变量或常量：

​												"return 数据"

​							如果方法没有返回值，则方法声明时，使用void来表示。

​								通常，没有返回值的方法中，就不需要使用return。

​								但是，如果使用，只能    "return;"   表示结束此方法的意思。

​				②.2	我们定义方法该不该有返回值？

​							Ⅰ	题目要求

​							Ⅱ	凭经验：具体问题具体分析

​		③方法名：属于标识符，遵循标识符的规则和规范，“见名知意”

​		④形参列表：方法可以声明0个，1个，或多个形参

​			格式：数据类型1 形参1，数据类型2 形参2，... ...

​		⑤方法体：方法功能的实现

#### return关键字的使用

1.适用范围：使用在方法体中

2.作用：

①结束方法 

②针对有返回类型的方法，使用“return 数据”方法返回所要的数据

3.注意：return关键字后面不可以声明执行语句

#### 方法的使用

方法的使用中，可以调用当前类的属性或方法

​			特殊的：方法A中又调用了方法A ==》 递归

方法中，不可以定义方法

## 每日一考

#### 1.面向对象思想编程内容的三条主线分别是什么？

> ①类及类的成员：属性、方法、构造器；代码块、内部类
>
> ②面向对象的三大特征：封装、继承、多态
>
> ③其它关键字：this、super、abstract、interface、static、final、package、import



**面向对象的编程思想？**

（类、对象：面向对象的三大特征 ... ...）

#### 2.谈谈你对面向对象中类和对象的理解，并指出二者的关系

类：抽象、概念上的东西

对象：实实在在存在的一个个体

对象是由类派生出来的。

#### 3.面向对象思想的体现一：类和对象的创建和执行操作有哪三步？

①创建类

②类的实例化

③调用对象的结构：“ 对象.属性 ” 、 " 对象.方法 "

#### 4.

#### 5.类的方法内是否可以定义变量？是否可以调用属性？是否可以定义方法？是否可以调用方法？

是。是。否。是 

#### 6.对象的创建与对象的内存解析

典型代码：

```java
Person p1 = new Person();

Person p2 = new Person();

Person p3 = p1;//没有新创建一个对象，共用一个堆空间中的对象实体
```

内存解析：

编译完源程序后，生成一个或多个字节码文件

我们使用JVM中的类的加载器和解释器对生成的字节码文件进行解释运行。意味着，需要将字节码文件对应多个类加载到内存中，涉及到内存解析	

《JVM规范》

虚拟机栈，就是平时提到的栈结构。我们将局部变量存储到栈结构中

堆，将new出来的结构（比如：数组、对象）加载在堆空间中。对象的属性（非static）加载在堆空间中

方法区：类的加载信息、常量池、静态域

#### 7.匿名对象

特点：

举例：

# 面向对象上

### 如何理解“万事万物皆对象”

1.在Java语言范畴中，我们都将功能、结构等封装到类中，通过类的实例化，来调用具体的功能结构

> Scanner,String..
>
> 文件：File
>
> 网络资源：URL

2.涉及到Java语言与前端HTML、后端的数据库交互时，前后端的结构在Java层面交互时，都体现为类、对象。

### 内存解析的说明

1.引用类型的变量，只可能存储两类值：null 或 地址值（含变量类型）

### 匿名对象的使用

```java
package com.auguigu.exer;

public class oneTest {
    public static void main(String[] args) {
        new Student11().playGame();//玩游戏
        new Student11().sendMain();//发送邮件
        //上面两个是匿名对象，指向地址不一样
        new Student11().num = 100;
        new Student11().showNum();//0
    }
}

class Student11{
    int num;
    int id;
    public void playGame(){
        System.out.println("玩游戏");
    }
    public void sendMail(){
        System.out.println("发送邮件");
    }
    public void showNum(){
        System.out.println(num);
    }
}
```

1.理解：我们创建的对象，没有显式的赋给一个变量名。即为匿名对象

2.特征：匿名对象只能调用一次

3.使用：

### 方法

#### 方法的重载

##### 重载的概念

在同一个类中，允许存在一个以上的同名方法，只要他们的参数个数或者参数类型不同即可

##### 重载的特点

与返回值类型无关，只看参数列表，且参数列表必须不同。（参数个数或参数类型）。调用时，根据方法参数列表的不同来区别

##### 重载示例

```java
int add(int x,int y){return x + y;}//返回两个整数的和
int add(int x,int y,int z){return x + y + z;}//返回三个整数的和
double add(double x,double y){return x + y;}//返回两个小数的和
```

#### 方法的重载（overload）

1.定义：在同一个类中，允许存在一个以上的同名方法，
只要他们的参数个数或者参数类型不同即可
"两同一不同"：同一个类、相同方法名
            参数列表不同（参数个数不同，参数类型不同）
2.举例：
Arrays类中的重载的sort() / binarySearch()
3.判断是否是重载：
跟方法的权限修饰符、返回值类型、形参变量名、方法体都没有关系
4.在通过对象调用方法时，如何确认某一个指定方法：
      方法名 ---> 参数列表

##### 举例

```java
//以下四种方法构成重载
public void getSum(int i,int j){
    System.out.println("1");
}
public void getSum(String s,int i){
    System.out.println("2");
}
public void getSum(double d1,double d2){
    System.out.println("3");
}
public void getSum(int i,String s){
    System.out.println("4");
}
```



#### 可变形参的方法

JavaSE 5.0中提供了Varargs机制，允许直接定义能和多个实参相匹配的形参。从而，可以用一种更简单的方式，来传递个数可变的实参

```java
//JDK5.0以前：采用数组形参来定义方法，传入多个同一类型变量

public static void test(int a, String[] books);

//JDK5.0以后：采用可变个数形参来定义方法，传入多个同一类型变量

public static void test(int a, String ... books);
```

>可变个数形参的方法：
>1.jdk5.0新增的内容
>2.具体使用：
>  2.1 可变个数形参的格式：数据类型 ... 变量名
>  2.2 当调用可变个数形参的方法时，传入的参数个数可以是：0个，1个，2个...
>  2.3 可变个数参数的方法与本类中方法名相同，形参不同的方法之间构成重载
>  2.4 可变个数形参的方法与本类中方法名相同，形参类型也相同的数组之间不构成重载。换句话说，二者不能共存
>  2.5 可变个数形参在方法的形参中，必须声明在末尾 → public void show(int i,String ... strS)
>  2.6 可变个数形参在方法的形参中，最多只能声明一个可变形参

#### 关于变量的赋值

   如果变量是基本数据类型，此时赋值的是变量所保存的数据值
   如果变量时引用数据类型，此时赋值的是变量所保存的数据的地址值

#### *方法参数的值传递机制(难点，重点)

方法，必须由其所在类或对象调用才有意义。若方法含有参数：

》形参：方法声明时的参数

》实参：方法调用时实际传给形参的参数值

#### 方法的形参的传递机制：值传递

1.   形参：方法定义时，声明的小括号内的参数
     实参：方法调用时，实际传递给形参的值

2. 值传递机制：
     如果参数是基本数据类型，此时实参赋给形参的是实参真实存储的数据值
   如果参数是引用数据类型，此时实参赋给形参的是实参存储数据的地址值

#### 递归方法

递归方法：一个方法体内调用它自身

方法递归包含了一种隐式的循环，它会重复执行某段代码，但这种重复执行无须循环控制

递归一定要向已知方向递归，否则这种递归就变成了无穷递归，类似于死循环

```java
public int getSum(int n){
    if(n == 1){
        return 1;
    }else{
        return n + getSum(n -  1);
    }
}
```

例题1：已知有一个数列：f(0) = 1,f(1) = 4,f(n + 2) = 2 * f(n + 1) + f(n),
				其中，n是大于0的整数，求f(10)的值

```java
 public int f(int n){
     if(n == 0){
         return 1;
     }else if(n == 1){
         return 4;
     }else{
         return 2*f(n - 1) + f(n - 2);
     }
 }
```



例题2：斐波那契数列

输入一个数据n，计算斐波那契数列的第n个值

1		1		2		3		4		5		13		21		34		55

规律：一个数等于前两个数之和

要求：计算斐波那契数列的第n个值，并将整个数列打印出来



例题3：汉诺塔问题



例题4：快排

### 每日一考

**1.什么是方法的重载**

两同一不同：同一个类、相同方法名；参数列表不同

<u>如何调用确定的方法：方法名 → 参数列表</u>

**2.说明java方法中的参数传递机制的具体体现**

基本数据类型：数据值

引用数据类型：地址值（含变量的数据类型）

```java
Person p1 = new Person();
User u1 = p1;//编译错误
```

**3.成员变量和局部变量在声明的位置上、是否有默认初始化值上、是否能有权限修饰符修饰上、内存分配的位置上有何不同**



**4.谈谈return关键字的使用**

①结束方法

②针对于有返回值的方法，return + 返回数据

**5.内存解析**

![image-20210814005726440](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210814005726440.png)

1.内存结构：栈（局部变量）、堆（new出来的结构：对象（非static成员变量）、数组）

2.变量：成员变量 vs 局部变量（方法内、方法形参、构造器内、构造器形、代码块内）

## 封装与隐藏

我们程序设计追求”高内聚，低耦合“

高内聚：类的内部数据操作细节自己完成，不允许外部干涉；

低耦合：仅对外暴露少量的方法用于使用

隐藏对象的内部的复杂性，只对外公开简单的接口。便于外界调用，从而提高系统的可扩展性、可维护性。通俗的说，**把该隐藏的隐藏起来，该暴露的暴露出来。这就是封装性的设计思想。**

![image-20210825000050561](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210825000050561.png)

### 面向对象的特征一：封装与隐藏

#### 一、问题的引入：

当我们创建一个类的对象后，我们可以通过“对象.属性”的方式，对对象的属性进行赋值。
这里，赋值操作要受到属性的数据类型和存储范围的制约。
除此之外，没有其他制约条件。但是，在实际问题中，我们往往需要给属性赋值加入额外的限制条件
这个条件不能在属性声明时体现，只能通过方法进行限制条件的添加。（比如setLegs()）
同时，我们需要避免用户再使用“对象.属性”的方式对属性进行赋值。则需要将属性声明为私有的（private）
——》此时，针对于属性就体现了封装性。

#### 二、封装性的体现：

我们将类的属性（xxx）私有化（private），
同时，提供公共的（public）方法来获取（getXxx）和设置（setXxx）此属性的值
  拓展：封装性的体现：①如上，②不对外暴露的私有的方法，③单例模式

#### 三、封装性的体现，需要权限修饰符来配合

1.Java规定的4种权限（从小到大排列）：private、缺省（default）（默认）、protected、public
2.四种权限可以用来修饰类及类的内部结构：属性、方法、构造器、内部类
3.具体的，四种权限都可以用来修饰类的内部结构：属性、方法、构造器、内部类
          修饰类的话，只能使用：缺省、public
总结封装性：Java提供了4种权限修饰符来修饰类及类的内部结构，体现类及类的内部结构在被调用时可见性的大小

## 构造器（或构造方法）

类的结构之三：构造器（或构造方法、constructor）的使用
constructor：建设者

### 一、构造器的作用：

  1.创建对象
  2.初始化对象的信息

### 二、说明

  1.如果没有显式的定义类的构造器的话，则系统默认提供一个空参的构造器
  2.定义构造器的格式：权限修饰符 类名(形参列表){    构造体    }
  3.一个类中定义的多个构造器，彼此构成重载
  4.一旦我们显式的定义了类的构造器之后，系统就不再提供默认的空参构造器
  5.一个类中，至少会有一个构造器

### 练习

## 拓展：JavaBean

- JavaBean是一种Java语言写成的可重用组件。

- 所谓JavaBean，是符合如下标准的Java类：

> 类是公共的
>
> 有一个无参的公共的构造器
>
> 有属性，且有对应的get、set方法

- 用户可以使用JavaBean将功能、处理、值、数据库访问和其他任何可以用Java代码创造的对性进行打包,并且其他的开发者可以通过内部的JSP页面、servlet、其他JavaBean、applet程序或者应用来使用这些对象。用户可以认为JavaBean提供了一种随时随地的复制和粘贴功能，而不用关心任何改变。

## 拓展：UML类图

![image-20210903102514903](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210903102514903.png)

## 关键字：this

### this关键字的使用

**1.**this可以用来修饰、调用：属性、方法、构造器

**2.**this修饰属性和方法

​			this理解为：当前对象	或 当前正在创建的对象

​	**2.1**	在类的方法中，我们可以使用”this.属性“或”this.方法“的方式，调用当前对象属性或方法。但是，通常情况下，我们都选择省略”this.“。特殊情况下，如果方法的形参和类的属性同名时，我们必须显式地使用”this.变量”的方式，表明此变量是属性，而非形参。

​	**2.2**	在类的构造器中，我们可以使用”this.属性“或”this.方法“的方式，调用当前正在创建的对象属性或方法。但是，通常情况下，我们都选择省略”this.“。特殊情况下，如果构造器的形参和类的属性同名时，我们必须显式地使用”this.变量”的方式，表明此变量是属性，而非形参。

**3.**this调用构造器

```java
class Person{
    int age;
    String name;
    public Person(){
	    
	}
	public Person(String name){
		this();
		this.name = name;
	}
	public Person(int age){
		this();
		this.age = age;
	}
	public Person(String name,int age){
		this(age);
		this.name = name;
		//this.age = age;
	}
}
```

①	我们在类的构造器中，可以显式的使用“this（形参列表）”方式，调用指定的类的其他构造器

②	构造器中不能通过“ this(形参列表) ”‘方式调用自己

③	如果一个类中有n个构造器，则最多有n - 1个构造器中使用了“this（形参列表）”

④ 	规定：“this(形参列表)”必须声明在当前构造器首行

## 关键字：package、import的使用

```
public class PackageImportTest{
	
}
```

## MVC设计模式

MVC是常用的设计模式之一，将整个程序分为三个层次：**视图模型层，控制器层，与数据模型层。**这种将程序输入输出、数据处理，以及数据的展示分离开来的设计模式使程序结构变得灵活而且清晰，同时也描述了程序各个对象间的通信方式，降低了程序的耦合性

**模型层 model 主要处理数据**

> 数据对象封装 model.beam/domain
>
> 数据库操作类 model.dao
>
> 数据库 model.db

**控制层 controller 处理业务逻辑**

> 应用界面相关 controller.activity
>
> 存放fragment controller.fragment
>
> 显式列表的适配器 controller.adapter
>
> 服务相关的 controller.service
>
> 抽取的基类 controller.base

**视图层 view 显示数据**

> 相关工具类 view.utils
>
> 自定义view view.ui

## 每天一考

**1.构造器的作用是什么？使用中有哪些注意点（>=3）**

①创建对象	②初始化对象结构	

**2.关于类的属性的赋值，有几种赋值的方法。谈谈赋值的先后顺序**

默认初始化—显式初始化—构造器中初始化—对象.方法 或 对象.属性 给属性赋值

**3.this关键字可以用来调用哪些结构，简单说明一下其使用**

this：属性、方法、构造器

this：理解为当前对象，当前正在创建的对象

**4.Java中目前学习涉及到的四种权限修饰符都有什么？并说明各自的权限范围**

private	缺省	protected	public

**5.创建Circle类，提供私有的radius属性，提供相应的get和set方法，提供求圆面积的方法。**

```java
private double radius;
public void setRadius(double radius){
	this.radius = radius;
}
public double getRadius(){
	return radius;
}
public double findArea(){
	return 3.14*radius*radius;
}
```

1.为什么要引入封装性？

高内聚，低耦合。隐藏内部的复杂性，只对外暴露简单的接口

2.问题引入：

# 面向对象中

## 继承性

```java
对象A
对象B
public class 对象B extends 对象A{
	
}
此时，在对象A中定义过的变量，可以不在对象B中定义
```

![image-20210909193416816](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210909193416816.png)

### 练习1：

(1)定义一个ManKind类，包括

​	成员变量int sex和int salary； 

​	方法void manOrWoman()：根据sex的值显示“man”(sex==1)或者“woman”(sex==0)； 

​	方法void employeed()：根据salary的值显示“no job”(salary==0)或者“ job”(salary!=0)。

(2)定义类Kids继承ManKind，并包括

​	成员变量int yearsOld； 

​	方法printAge()打印yearsOld的值。

(3)定义类KidsTest，在类的main方法中实例化Kids的对象someKid，用该对象访问其父类的成员变量及方法。

## 方法的重写（override/overwrite)

定义：

在子类中可以根据需要对从父类中继承来的方法进行改造，也称为方法的重置、覆盖。在程序执行时，子类的方法将覆盖父类的方法

要求：

1.子类重写的方法必须和父类被重写的方法具有相同的方法名称、参数列表

2.子类重写的方法的返回值类型不能大于父类被重写的方法的返回值类型

3.子类重写的方法使用的访问权限不能小于父类被重写的方法的访问权限

> 子类不能重写父类中声明为private权限的方法

4.子类方法抛出的异常不能大于父类被重写方法的异常

注意：

子类与父类中同名同参数的方法必须同时声明为非static的（即为重写），或者同时声明为static的（不是重写）。因为static方法是属于类的，子类无法覆盖父类的方法。

```java
public class Person{
	public void eat(){
		sys("人要吃饭");
	}
}
```

```java
public class Student extends Person{
	public void eat(){
		sys("学生要多吃有营养的");//覆盖了person中的eat
	}
}
```

1.重写：子类继承父类后，可以对父类中同名同参数的方法，进行覆盖操作

2.应用：重写以后，当创建子类对象以后，通过子类对象调用父类中同名同参数的方法时，实际执行的是子类重写父类的方法

3.重写的规定：

​		方法的声明：权限修饰符 返回值类型 方法名（形参列表）throws 异常的类型{

​										//方法体

​								}

​		约定俗成：子类中的叫重写的方法，父类中的叫被重写的方法

​		①	子类重写的方法的方法名和形参列表与父类被重写的方法的方法名和形参列表相同

​		②	子类重写的方法的权限修饰符不小于父类被重写的方法的权限修饰符

​		》 特殊情况：子类不能重写父类中声明为private的方法

​		③	返回值类型：

​					》父类被重写的方法的返回值类型是void，则子类重写的方法的返回值类型只能是void

​					》父类被重写的方法的返回值类型是A类型，则子类重写的方法的返回值类型可以是A类或A类的子类

​					》父类被重写的方法的返回值类型是基本数据类型（比如：double），则子类重写的方法的返回值类型必须是相同的基本数据类型（必须也是double）

​		④	子类重写的方法抛出的异常类型不大于父类被重写的方法抛出的异常类型

*****************************************************************************************************************************************************************

子类和父类中的同名同参数的方法要么都声明为非static的（可以重写），要么都声明为static的（不是重写）

面试题：区分方法的重载与重写

## 关键字：super

## 虚拟方法调用

![image-20210913202701808](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210913202701808.png)

![image-20210913203149904](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210913203149904.png)

面试题：

多态是编译时行为还是运行时行为？

如何证明？

证明见：InterviewTest.java

是运行时行为

### 每日一考

**1.什么是多态性？什么是虚拟方法调用？**

对象的多态性：父类的引用指向子类的对象

```java
Person p = new Man();
p.eat();
```

调用方法时，编译时看左边，运行时看右边

**2.一个类可以有几个直接父类？一个父类可以有多少个子类？子类能获取直接父类的父类中的结构吗？子类能否获取父类中private权限的属性或方法？**

①只有一个

②多个

③可以，间接父类

④可以，把私有的放在公有的，通过获取公有的，进而获取私有的

**3.方法的重写（override/overwrite）的具体规则有哪些？**

方法名、形参列表相同

权限修饰符

返回值

抛出的异常

**4.super调用构造器，有哪些具体的注意点？**

`this(形参列表)`：本类重载的其他的构造器

`super(形参列表)`：调用父类中指定的构造器

## 子类对象实例化的全过程

**1.从结果上看：（继承性）**

​		子类继承父类以后，就获取了父类中声明的属性或方法

​		创建子类的对象，在堆空间中，就会加载所有父类中声明的属性

**2.从过程上来看：**

​		当我们通过子类的构造器创建子类对象时，我们一定会直接或间接的调用其父类的构造器，进而调用父类的父类的构造器，直到调用了java.lang.Object类中空参的构造器为止。正因为加载过所有的父类的结构，所以才可以看到内存中父类的结构，子类对象才可以考虑进行调用

图示：

![image-20210914111718126](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210914111718126.png)

明确：虽然创建子类对象时，调用了父类的构造器，但是自始至终就创建过一个对象，即为new的子类对象



**思考：**

1）为什么super(...)和this(...)调用语句不能同时在一个构造器中出现？

2）为什么super(...)和this(...)调用语句只能作为构造器中的第一句出现？

无论通过哪个构造器创建子类，需要保证先初始化父类。

目的：当子类继承父类后，“继承”父类中所有的属性和方法，因此子类有必要直到父类如何为对象进行初始化

## *

1.若子类重写了父类方法，就意味着子类里定义的方法彻底覆盖了父类里的同名方法，系统将不可能把父类里的方法转移到子类中：编译看左边，运行看右边

2.对于实例变量则不存在这样的现象，即使子类里定义了与父类完全相关的实例变量，这个实例变量依然不可能覆盖父类中定义的实例变量：编译运行都看左边

## Object类的使用

1.Object是所有Java类的根父类

2.如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类

```java
public class Person
等价于
public class Person extends Object
```

```java
method(Object obj){...}//可以接收任何类作为参数
Person p = new Person();
method(p);
```

3.Object类中的功能（属性、方法）就具有通用性

​	属性：无

​	方法：equals()	/	toString()	/	getClass()	/	hashCode()	/	clone()	/	finalize()	/				wait()	/	notify()	/	notifyAll()

4.Object类只声明了一个空参的构造器

### 面试题：==和equals的区别

#### 一、回顾 == 的使用：

== ： 运算符
1.可以使用在基本数据类型变量和引用数据类型变量中
2.如果比较的是基本数据类型变量：比较两个变量保存的数据是否相等（不一定类型要相同）
  如果比较的是引用数据类型变量：比较两个对象的地址值是否相同，即两个引用是否指向同一个对象实体（比如对象）

补充：==符号使用时，必须保证符号左右两边的变量类型一致

#### 二、`equals()`方法的使用：

1.是一个方法，而非运算符
2.只能适用于引用数据类型

3.Object类中equals()的定义：

```java
public boolean equals(Object obj){
	return (this == obj);
}
```

​	说明：Object类中定义的	equals()	和	==	的作用是相同的：比较

4.像String、Date、File、包装类等都重写了Object类中的	equal()	方法

重写后，比较的不是两个引用的地址是否相同，而是比较两个对象的“实体内容”是否相同 

```java
String str1 = new String("hello");
String str2 = new String("hello");

System.out.println(cust1.equals(cust2));//true
```

5.通常情况下，自定义的类如果使用equal()的话，也通常是比较两个对象的“实体内容”是否相同。那么，我们就需要对Object类中的equals()进行重写

（idea自动生成equals()快捷键：alt+insert）

​	重写的原则：比较两个对象的实体内容是否相同

```java
public boolean equals(Object obj){
	if(this == obj){
		return true;//说明地址相同
	}
	if(obj instanceof Customer){
		Customer cust = (Customer)obj;
		//比较两个对象的每个属性是否都相同
        //if(this.age == cust.age && this.name.equals(cust.name)){
        //    return true;
        //}else{
        //    return false;
        //}
        return this.age == cust.age && this.name.equals(cust.name);
	}else{
        return false;
    }
}
```

#### 答案

equals的3和5，大致描述==

![image-20210915190114206](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210915190114206.png)

## toString()方法

1.当我们输出一个对象的引用时，实际上就是调用当前对象的toString()

```java
main{
	Customer cust1 = new Customer("Tom",21);
	Syso(cust1.toString());
	syso(cust1);
	//以上输出是一样的，地址值
}
```

2.Object类中toString()的定义：

```java
public String toString(){
	return getClass().getName()+"@"+Integet.toHexString(hashCode());
}
```

```java
String str = new String("MM");
syso(str);//MM

Date date = new Date(324325332L);
syso(date);//具体时间
```

3.像String、Date、File、包装类等都重写了Object类中的toString()方法

使得在调用对象的toString()时，返回“实体内容”信息

4.重写toString

```java
public String toString(){
	return "Customer[name = " + name + ",age = " + age + "]";
    
    //可以自动生成↑
}
//重写后
main{
	Customer cust1 = new Customer("Tom",21);
	Syso(cust1.toString;//Customer[name = Tom , age = 21]
	syso(cust1);//地址值
```

## 单元测试

**Java中的	JUnit单元测试**

步骤：

1.在IDEA当中，在要进行单元测试的类前加上@Test，鼠标移上去有“add...”，或者快捷键ctrl + shift + t

2.创建Java类，进行单元测试

Java类的要求：①此类是public的	②此类提供公共的无参的构造器

3.此类中声明单元测试方法。

此时的单元测试方法：方法的权限是public，没有返回值，没有形参

4.此单元测试方法上需要声明注解：@Test，并在单元测试类中导入impor	org.junit.Test;

5.声明好单元测试方法以后，就可以在方法体内测试相关的代码

6.写完代码以后，左键双击单元测试方法名，右键：run



说明：

无异常，绿条

有异常，红条

## 包装类的使用

针对八种基本数据类型定义相应的引用类型——包装类（封装类）

有了类的特点，就可以调用类中的方法，Java才是真正的面向对象

| 基本数据类型 |  包装类   |
| :----------: | :-------: |
|     byte     |   Byte    |
|    short     |   Short   |
|     int      |  Integer  |
|     long     |   Long    |
|    float     |   Float   |
|    double    |  Double   |
|   boolean    |  Boolean  |
|     char     | Character |

前六种是数值型，父类都是Number类

### 包装类的使用

1.Java提供了八种基本数据类型对应的包装类，使得基本数据类型的变量具有类的特征

2.掌握的：基本数据类型、包装类、String三者之间的相互转换

![image-20210916191609411](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210916191609411.png)

基本数据类型——》包装类：调用包装类的构造器

#### 关于包装类使用的面试题

```java
Object o1 = true ? new Integer(1) : new Double(2.0);
syso(o1);
```

结果：1.0，三元运算符，类型提升到一样，因此1变成浮点型。

```java
Object o2;
if(true)
	o2 = new Integer(1);
else
	o2 = new Double(2.0);
syso(o2);
```

结果：1，不是三元运算符

```java
Integer i = new Integer(1);
Integer j = new Integer(1);
syso(i == j);//false,比较的是地址值，两个不同的对象，地址值不一样

//Integer内部定义了IntegerCache结构，IntegerCache中定义了Integer[],
//保存了从-128~127范围的整数。如果我们使用自动装箱的方式，给Integer赋值的范围在
//-128~127范围内时，可以直接使用数组中的元素，不用再new

Integer m = 1;
Integer n = 1;
syso(m == n);//true

//相当于new了一个Integer对象
Integer x = 128;
Integer y = 128;
syso(x == y);//false，因为Integer限制的范围是[-128,127]
```

### 练习

> 1.实例化Scanner，用于从键盘获取学生成绩
>
> 2.创建Vector对象：Vector v = new Vector();	相当于原来的数组
>
> 3.通过for(;;)或while(true)方式，给Vector中添加数组
>
> 3.1添加操作：v.addElement(Object obj)
>
> 3.2当输入是负数时，跳出循环
>
> 4.获取学生成绩的最大值
>
> 5.遍历vector，得到每个学生的成绩，并于最大成绩比较，得到每个学生等级

```java
//1.实例化Scanner，用于从键盘获取学生成绩
Scanner scan = new Scanner(System.in);

//2.创建Vector对象：Vector v = new Vector();	相当于原来的数组
Vector v = new Vector();

//3.通过for(;;)或while(true)方式，给Vector中添加数组
int maxScore = 0;
for(;;){
    syso("请输入学生成绩（以输入负数代表结束）：");
    int score = scan.nextInt();
    if(score < 0){
        break;
    }
    if(score > 100){
        syso("输入数据非法，请重新输入");
        continue;
    }
    //3.1添加操作：v.addElement(Object obj)
    //jdk5.0之前：
  //  Integer inScore = new Integer(score);
  //  v.addElement(inScore);//多态
    //jdk5.0之后：
    v.addElement(score);
    
    //4.获取学生成绩的最大值
    if(maxScore < score){
        maxScore = score;
    }
}

//5.遍历vector，得到每个学生的成绩，并于最大成绩比较，得到每个学生等级
char leval;
for(int i = 0;i < v.size();i++){
    Obect obj = v.elementAt(i);
    //jdk5.0之前：
    //Integer inScore = (Integer)obj;
    //int score = inScore.intValue();
    
    //jdk5.0之后：
    int score = (int)obj;
    if(maxScore - score <= 10){
        leval = 'A';
    }else if(maxScore - score <= 20){
        leval = 'B';
    }else if(maxScore - score <= 30){
        leval = 'C';
    }else{
        leval = 'D';
    }
    syso(成绩是...等级是...);
}
```

## 每日一考

**1.如何实现向下转型？需要注意什么问题？如何解决问题**

使用强转符：()

Man m = (Man)p;

可能ClassCastException异常

因此使用instanceof在进行向下转型前判断。

```java
if(p instanceof Man){
	Man m = (Man)p;
}
```

**2.== 和 equals() 有何区别？**

==：

equals()：

**3.class User{**

​	**String name;**

​	**int age;**

​		**//重写其equals()方法**

**}**

```java
3.class User{
	String name;
	int age;
	//重写其equals()方法
	public boolean equals(Object obj){
		if(obj == this){
            return true;
        }			
        if(obj objectof User){
            User u = (User)obj;
            return this.age = u.age && this.name.equals(u.name);
        }
        return false;
	}
}
```

**4.写出八种基本数据类型及其对应的包装类**

上面有

**5.基本数据类型、包装类与String三者之间如何转换**

自动装箱、自动拆箱

![image-20210917082604613](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210917082604613.png)

# 面向对象下

## 关键字：static

```java
public static void main(String[] args){
	Chinese c1 = new Chinese();
	c1.name = "xxx";
	c1.age = 18;
	
	Chinese c2 = new Chinese();
	c2.name = "yyy";
	c2.age = 20;
	
	c1.nation = "CHN";
	syso(c2.nation);//也是CHN
    c2.nation = "China";
    syso(c1.nation);//变成china了
	
}
class Chinese{
	String name;
	int age;
	static String nation;
}
```

**1.static：静态的**

**2.static可以用来修饰：属性、方法、代码块、内部类**

**3.使用static修饰属性：静态变量（或类变量）**

​		3.1属性：按是否使用static修饰，又分为：静态属性 vs 非静态属性（实例变量）

​			实例变量：我们创建了类的多个对象，每个对象都独立拥有一套类中的非静态属性。

​								但修改其中一个对象中的非静态属性时，不会导致其他对象中同样的属性值修改

​			静态变量：我们创建了类的多个对象，多个对象共享同一个静态变量。

​								但通过某一个对象，修改静态变量时，其他对象调用此静态变量，是修改过的

​		3.2 static修饰属性的其他说明：

​			① 静态变量随着类的加载而加载，可以通过“类.静态变量”的方式进行调用

​			② 静态变量的加载早于对象的创建

​					因此可以直接`Chinese.nation = "CHINA";`，放在new对象之前

​			③ 由于类只会加载一次，则静态变量在内存中也只会存在一次：存在方法去的静态域中

​			④						类变量				实例变量

​					类					yes						no

​					对象				yes						yes

​		3.3 静态属性举例：System.out;	Math.PI;

**4.使用static修饰方法：静态方法**

​		① 随着类的加载而加载，可以通过“类.静态方法”的方式进行调用	

```java
非静态方法：
	public void eat(){}
静态方法：
	public static void show(){}
```

​				静态方法可以不用new一个对象，直接调用，有类就能调用  `Xxx.show()`

​		②				 		 静态方法				非静态方法

​					类					yes						no

​					对象				yes						yes 

​		③ 静态方法中，只能调用静态的方法或属性，因为生命周期一致		

```java
static String nation;
public static void show(){
	syso(nation);//省略的是Chinese，而不是this
	syso(Chinese.nation);
}
```

​			非静态方法中，既可以调用非静态的方法或属性，也可以调用静态的

**5.static注意点：**

​		5.1在静态的方法内，不能使用this关键字、super关键字

​		5.2关于静态属性和静态方法的使用，从生命周期的角度去理解

**6.总结：**

开发中，如何确定一个属性是否要声明为static的？

​	》属性是可以被多个对象所贡共享的，不会随着对象的不同而不同

​	》类中的常量也常常声明为static

开发中，如何确定一个方法是否要声明为static的？

​	》操作静态属性的方法，通常设置为static的

​	》工具类中的方法，习惯上声明为static的。比如：Math、Arrays、Collections

> 共有的属性和方法可以声明为静态的，不需要随着对象的创建而频繁的加载

工具类就用静态方法，直接调用，不用new对象

### 单例（Singleton）设计模式

![image-20210917142515006](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210917142515006.png)

![image-20210917142631321](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210917142631321.png)

**1.所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个代码实例**

```java
//单例模式饿汉式实现，一上来就造
public class SingletonTest{
	public  static void main(String[] args){
		//要想调用类中的公共的getInstance方法
        //就需要让该方法成为静态的
        Bank bank = Bank.getInstance();
        Bank bank1 = Bank.getInstance();
        
        syso(bank == bank1);//地址值一样，返回true
    }
}

//饿汉式
class Bank{
	//1.私有化类的构造器
	privete Bank(){
        
    }
    
    //2.内部创建类的对象
    //4.要求此对象也必须声明为静态的
    private static Bank instance = new Bank();
    
    //3.提供公共的静态的方法，返回类的对象
    public static Bank getInstance(){
        return instance;
    }
}
```

```java
//单例模式懒汉式实现，啥时候用啥时候造
public class SingletonTest1{
	public  static void main(String[] args){
		Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();
        syso(order1 == order2);//true,地址值相同   
	}
    
    class Order{
        //1.私有化类的构造器
        private Order(){
            
        }
        
        //2.声明当前类的对象，没有初始化
        //4.此对象也必须声明为static
        private Order instance = null;//饿汉式写new，懒汉式写null
        
        //3.声明public、static的返回当前对象的方法
        public Order getInstane(){
            if(instance == null){
                instance = new Order();
            }
            return instance;
        }
    }
}	
```

**2.如何实现？**

饿汉式 vs 懒汉式

**3.区分饿汉式和懒汉式**

饿汉式（娃娃亲）：

​		坏处：对象加载时间过长。

​		好处：饿汉式是线程安全的。

懒汉式（相亲）：

​		好处：延迟对象的创建。

​		坏处：目前的写法不安全——》到多线程内容时，再修改

#### 单例模式的优点：

由于单例模式只生成一个实例，减少了系统性能开销，当一个对象的生产需要比较多的资源时，如读取配置、产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决。

#### 举例java.lang.Runtime

#### 单例设计模式-应用场景

网站计数器：

应用程序的日志应用：

数据库连接池：

读取项目中配置文件的类：

Application也是单例的典型应用

Task Manager(任务管理器)：

Recycle Bin(回收站)：

![image-20210917150409138](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20210917150409138.png)

## 理解main方法的语法

### main()方法的使用说明

1.main()方法作为程序的入口

2.main()方法也是一个普通的静态方法

一个文件只能有一个public类，可以有多个main方法

```java
public class MainTest{
	public static void main(String[] agrs){//入口
		Main.main(new String[100]);	
	}
}
class Main{
    //普通静态方法
    public static void main(String[] args){
        args = new String[100];
        for(int i = 0;i < args.length;i++){
            args[i] = "args_" + i;
            syso(args[i]);
        }
    }
}
```

3.main()方法可以作为我们与控制台交互的方式（之前：使用Scanner）

## 类成员之四：代码块（或初始化块）

```java
public class BlockTest{
	public static void main(String[] args){
        String desc = Person.desc;
        Person p1 = new Person();
        
    }
}
class Person{
    //属性
	String name;
	int age;
	static String desc = "我是个人";
	
    //构造器
	public Person(){
	
	}
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
    
    //static代码块
    static{
        syso("hello,static block");
            desc = "我是个好学的人";
    }
    //非static代码块
    {
        syso("hello,block");
        age = 1;
    }
    
    //方法
    @Override
    public String toString(){
        return "Person [name = " + name + ", age = " + age + "]";
    }
}
```

**1.代码块的作用：用来初始化类、对象**

**2.代码块如果有修饰的话，只能是static**

**3.分类：静态代码块 vs 非静态代码块**

**4.静态代码块**

> 内部可以有输出语句
>
> 随着类的加载而执行，而且只执行一次
>
> 作用：初始化类中静态的属性
>
> 如果一个类中定义了多个静态代码块，则按照声明的先后顺序执行
>
> 静态代码块的执行优先于非静态代码块
>
> 静态代码块内只能调用静态的属性、静态的方法，不能调用非静态的结构

**5.非静态代码块**

> 内部可以有输出语句
>
> 随着对象的创建而执行
>
> 每创建一个对象都执行一次非静态代码块
>
> 作用：可以在创建对象时，对对象的属性进行初始化
>
> 如果一个类中定义了多个非静态代码块，则按照声明的先后顺序执行
>
> 非静态代码块内可以调用静态的属性、静态的方法，或非静态的属性、非静态的方法

## 属性赋值的先后顺序（完结篇）

**对属性可以赋值的位置：**

①默认初始化

②显式初始化

③构造器中初始化

④有了对象以后，可以通过”对象.属性“或”对象.方法“的方式，进行赋值

⑤在代码块中赋值 

1		2 / 5		3		4

2和5看谁后赋值，显示谁

```java
public class OrderTest{
	public static void main(String[] args){
		Order order = new Order();
		syso(order.orderId);
	}
}
class Order{
	int orderId = 3;//显式赋值
	//代码块
	{
		orderId = 4； 
	}
}
```

## 关键字：final

final：最终的

1. **final**可以用来修饰的结构：类、方法、变量

2. **final**用来修饰一个类：此类不能被其他类继承

​			比如：String类、System类、StringBuffer类

```java
//到头了，没有子类的，俗称”太监类“。无法被继承
final class FinalA{

}
```

3. **final**用来修饰方法：表明此方法不可被重写，最终版！

​			比如：**Object**类中**getClass();**

```java
class AA{
    public final void show(){
        
    }
}
class BB extends AA{
    //cannot override the final method
    //试图重写，但是AA中的show被final修饰，终版，无法重写
    public void show(){
        
    }
}
```

4. **final**用来修饰变量：此时”变量“就称为是一个常量

​		4.1	**final**修饰属性：

​			可以考虑赋值的位置有：

​			显式赋值、代码块中初始化、构造器中初始化

```java
final int LEFT;

public class FinalTest{
    final int WIDTH = 10;//显式赋值
    final int LEFT;//代码块赋值
    final int RIGHT;//构造器中赋值
   // final int DOWN;//通过对象.方法进行赋值，不可取
    
    {
		LEFT = 1;
	}
    
    public FinalTest(){
        RIGHT = 2;
    }
    
    public FinalTest(int n){
        RIGHT = n;
    }
    
   // public void setDOWN(int DOWN){
   //     this.DOWN = down;
   // }
}
```

```java
public class FinalTest{
    final int width = 10;
    //width此时是final的，所以不能赋值
    public void doWidth(){
        width = 20;
    }
}
```

​		4.2	**final**修饰局部变量：

​					尤其是使用final修饰形参时，表明此形参是一个常量。

​					当我们调用此方法时，给常量形参赋一个实参。

​					一旦赋值以后，就只能在方法体内使用此形参，但不能进行重新赋值。

```java
public void show(){
	final int NUM = 10;//常量
	NUM += 20;//会报错
}
```

```java
public void show(final int num){
	num = 20;//报错，传入的参数是常量，只能进行调用，不能进行修改
}
```

### **static final：**

用来修饰属性：全局常量

## 每日一考

**1.static修饰的属性，相较于实例变量，有哪些特别之处（>=3点）**

随着类的加载而加载；早于对象的创建；只要权限允许，可以通过“对象.static”得到方式进行调用；存在于方法区的静态域中的；

**2.final可以用来修饰哪些结构，分别表示什么意思**

 显式、代码块、构造器

**3.代码实现单例模式的饿汉式**

```java
//单例模式饿汉式实现，一上来就造
public class SingletonTest{
	public  static void main(String[] args){
		//要想调用类中的公共的getInstance方法
        //就需要让该方法成为静态的
        Bank bank = Bank.getInstance();
        Bank bank1 = Bank.getInstance();
        
        syso(bank == bank1);//地址值一样，返回true
    }
}

//饿汉式
class Bank{
	//1.私有化类的构造器
	privete Bank(){
        
    }
    
    //2.内部创建类的对象
    //4.要求此对象也必须声明为静态的
    private static Bank instance = new Bank();
    
    //3.提供公共的静态的方法，返回类的对象
    public static Bank getInstance(){
        return instance;
    }
}
```

**4.代码实现单例模式的懒汉式**

```java
//单例模式懒汉式实现，啥时候用啥时候造
public class SingletonTest1{
	public  static void main(String[] args){
		Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();
        syso(order1 == order2);//true,地址值相同   
	}
    
    class Order{
        //1.私有化类的构造器
        private Order(){
            
        }
        
        //2.声明当前类的对象，没有初始化
        //4.此对象也必须声明为static
        private Order instance = null;//饿汉式写new，懒汉式写null
        
        //3.声明public、static的返回当前对象的方法
        public Order getInstane(){
            if(instance == null){
                instance = new Order();
            }
            return instance;
        }
    }
}	
```

**5.类的属性赋值的？先后顺序为何?**

默认初始化

显式初始化、代码块中初始化

构造器中初始化

通过“对象.属性”的方式或“对象.方法”的方式进行初始化

## 抽象类与抽象方法

随着继承层次中一个个新子类的定义，类变得越来越具体。而父类则更一般，更通用。类的设计应该保证父类和子类能够共享特征。有时将一个父类设计得非常抽象，以至于他没有具体的实例，这样的类叫做**抽象类**。

### abstract关键字的使用

1. abstract：抽象的

2. abstract可以用来修饰的结构：类、方法

3. abstract修饰类：抽象类

   >此类不能实例化
   >
   >抽象类中一定有构造器，便于子类实例化时调用（涉及：子类对象实例化的全过程）
   >
   >开发中，都会提供抽象类得子类，让子类对象实例化，完成相关操作

```java
public class AbstractTest{
	public static void main(String[] args){
        Person p1 = new Person();
        //报错：cannot instantiate the type Person
        //不能实例化Person
        //一旦Person类抽象了，就不可实例化
        p1.eat();
    }
}

abstract class Person{
    String name;
    int age;
    
    public Person(){
        
    }
    public Person(String name,int age){
        this.name = name;
        this.age =age;
    }
    
    public void eat(){
        syso("吃饭")
    }
    public void walk(){
        syso("走路")
    }
}

class Student extends Person{
    
}
```

4. abstract修饰方法：抽象方法

   > 抽象方法，只有方法的声明，没有方法体
   >
   > 包含抽象方法的类，一定是一个抽象类。反之，抽象类中可以没有抽象方法的。
   >
   > 若子类重写了父类中所有的抽象方法后，则子类方可实例化
   >
   > 若子类没用重写父类中的所有的抽象方法，则子类也是一个抽象类，需要使用abstract修饰

```java
//抽象方法
public abstract void eat();
```

```java
class Student extends Person{
    publci Studen(String name,int age){
    	super(name,age);
    }
    
    //Person类中含有抽象方法eat()，报错
    //重写父类的抽象方法
    //或者让此类变成抽象类
    public void eat(){
        
    }
}
```

#### abstract使用上的注意点：

1. abstract不能用来修饰：属性、构造器等结构
2. abstract不能用来修饰私有方法、静态方法、final的方法、final的类

final不能重写，abstract就是要重写，冲突

#### 思考

**问题一：为什么抽象类不可以使用final关键字声明？**

抽象类需要子类继承，final声明的不能被子类继承

**问题二：一个抽象类中可以定义构造器吗？**

可以

**问题三：是否可以这样理解：抽象类就是比普通类多定义了抽象方法，除了不能直接进行类的实例化操作外，并没有任何的不同？**

### 抽象类的匿名子类

```java
method(new Student());//匿名对象

Worker worker = new Worker();
method1(worker);//非匿名的类非	匿名的对象

method1(new Worker());//非匿名的类	匿名的对象

//创建了一匿名子类的对象：p（没有名字的子类）
Person p = new Person(){
  @Override  
};
method1(p);

syso("**********************************");

//创建匿名子类的匿名对象
method1(new Person(){
    @Override
});

public static void method1(Person p){
    p.eat();
    p.breath();
}
```

## 346.多态的应用：模板方法设计模式（TemplateMethod）

抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类在抽象类的基础上进行扩展、改造，但子类总体上会保留抽象类的行为方式。

### 解决的问题

》当功能内部一部分实现是确定的，一部分实现是不确定的。这时可以把不确定的部分暴露出去，让子类去实现。

》换句话说，在软件开发中实现一个算法时，整体步骤很固定、通用，这些步骤已经在父类中写好了。但是某些部分易变，易变部分可以抽象出来，供不同子类实现。这就是一种模板模式。

```java
public class TemplateTest{
	public static void main(String[] args){
        Template t = new SubTemplate();
        t.spendTiem();
    }
}

abstract class Template{
    
    //计算某段代码执行需要的时间
    public void spendTime(){
        long start = System.currentTimeMillis();//1970年至这条代码执行相距时间
        this.code();//不确定的部分，易变的部分
        long end = System.currentTimeMillis();
        syso("花费的时间为：" + (end - start));
    }
    
    public abstract void code();
}

class SubTemplate extends Template{
    @Override
    public void code(){
        for(int i = 2;i <= 1000;i++){
            boolean isFlag = true;
            for(int j = 2;j <= Math.sqrt(i);j++){
                if(i % j == 0){
                    isFlag = false;
                    break;
                }
            }
            if(isFlag){
                syso(i);
            }
        }
    }
}
```

## 接口

### 概述

一方面，有时必须从几个类中派生出一个子类，继承他们所有的属性和方法。但是，Java不支持多重继承。有了接口，就可以得到多重继承的效果。

另一方面，有时必须从几个类中抽取出一些共同的行为特征，而它们之间又没有is-a的关系，仅仅是具有相同的行为特征而已。

接口就是规范，定义的是一组规则，体现了现实世界中“如果你是/要...则必须能...”的思想。**继承是一个“是不是”的关系，而接口实现则是“能不能”的关系**

**接口的本质时契约，标准，规范，**就像我们的法律一样。制定好后大家都要遵守

### 接口的使用

1. 接口使用interface来定义

   

2. Java中，接口和类时并列的两个结构

   

3. 如何定义接口：定义接口中的成员

   3.1	JDK7及以前：

   ​		只能定义全局常量和抽象方法

   ​	>全局常量：public、static、final的

   ​	>抽象方法：public、abstract的

   3.2	JDK8：

   ​		除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法（略）

   

4. 接口中不能定义构造器！意味着接口不可以实例化

   

5. Java开发中，接口通过让类去实现（implements）的方式来使用

   如果实现类覆盖了接口中所有的抽象方法，则此实现类就可以实例化

   如果实现类没有覆盖接口中所有的抽象方法，则此实现类仍为一个抽象类

   

6. Java类可以实现多个接口（多实现）===》弥补了Java单继承性的局限性

   格式：class AA extends BB implements CC,DD,EE

   

7. 接口和类的关系叫实现，类和类的关系叫继承，接口和接口之间也叫继承，可以多继承

   

8. 接口的具体使用，体现多态性

   

9. 接口，实际上可以看作是一种规范

   

   **面试题：抽象类与接口有哪些异同？**

   

```java
public class InterfaceTest{
	public static void main(String[] args){
        syso(Flyable.MAX_SPEED);
        Flyable.MIN_SPEED = 2;//报错，是常量
        
        Plane plane = new Plane();
        plane.fly();
    }
}
interface Flyable{
	//全局常量
    public static final int MAX_SPEED = 7900;//第一宇宙速度
    int MIN_SPEED = 1;
    //接口中，定义的都是产量，因此书写时可以省略public static final
    
    //抽象方法
    public abstract void fly();
    void stop();//省略了public abstract
    
    //public Flyable(){
    //    
    //}
}

interface Attackable{
    void attack();
}

class Plane implements Flyable{
    
    @Override
    public void fly(){
        syso("通过引擎起飞")
    }
    
    @Override
    public void stop(){
        syso("驾驶员减速停止")
    }
}

abstract class Kite implement Flyable{
     @Override
    public void fly(){
        syso("通过引擎起飞")
    }
    //没有重写所有方法，因此不能实例化，要写abstract
}

class Bullet extends Object implements Flyable,Attackable,CC{
    public void attack(){
        
    }
    public void fly(){
        
    }
    public void stop(){
        
    }
    public void method1(){
        
    }
    public void method2(){
        
    }
}

interface AA{
	void method1();
}
interface BB{
	void method2();
}
interface CC extend AA,BB{

}
```

### 接口的使用

#### 应用场景：

安全代理：屏蔽对真实角色的直接访问。 

远程代理：通过代理类处理远程方法调用（RMI） 

延迟加载：先加载轻量级的代理对象，真正需要再加载真实对象

比如你要开发一个大文档查看软件，大文档中有大的图片，有可能一个图片有100MB，在打开文件时，不可能将所有的图片都显示出来，这样就可以使用代理模式，当需要查看图片时，用proxy来进行大图片的打开。 

#### 分类

静态代理（静态定义代理类） 

动态代理（动态生成代理类） 

JDK自带的动态代理，需要反射等知识

#### 面试题：排错

```java
interface A{
	int x = 0;
}
class B{
	int x = 1;
}
class C extends B implements A{
	public void Px(){
		syso(x);//编译不通过，x是不明确的
        syso(super.x);//1
        syso(A.x);//0,x是全局常量
        //如果A中是x1，B中是x2，则直接
        syso(x1);
        syso(x2);
	}
	public static void main(String[] args){
		new C().pX();
	}
}
```

### Java8中关于接口的改进

Java 8中，你可以为接口添加静态方法和默认方法。从技术角度来说，这是完全合法的，只是它看起来违反了接口作为一个抽象定义的理念。

**静态方法：**使用 static 关键字修饰。可以通过接口直接调用静态方法，并执行其方法体。我们经常在相互一起使用的类中使用静态方法。你可以在标准库中找到像Collection/Collections或者Path/Paths这样成对的接口和类。

**默认方法：**默认方法使用 default 关键字修饰。可以通过实现类对象来调用。我们在已有的接口中提供新方法的同时，还保持了与旧版本代码的兼容性。

比如：java 8 API中对Collection、List、Comparator等接口提供了丰富的默认方法。

## 类的成员之五：内部类

当一个事物的内部，还有一个部分需要一个完整的结构进行描述，而这个内部的完整的结构又只为外部事物提供服务，那么整个内部的完整结构最好使用内部类。

在Java中，允许一个类的定义位于另一个类的内部，前者称为内部类，后者称为外部类。

Inner class一般用在定义它的类或语句块之内，在外部引用它时必须给出完整的名称。

​	>Inner class的名字不能与包含它的外部类类名相同;

分类:	成员内部类(static成员内部类和非static内部类)

​			局部内部类(不谈修饰符)、匿名内部类

## 每日一考

**1.abstract能修饰哪些结构？修饰以后，有什么特点？**

类、方法。

类不能实例化，提供子类

抽象方法，只定义了一种功能的标准。具体的执行，需要子类去实现

**2.接口能否继承接口？抽象类是否能实现（implements）接口？抽象类是否能继承非抽象的类？**

能，能，能

**3.声明抽象类，并包含抽象方法。测试类中创建一个继承抽象类的匿名子类的对象**

```java
abstract AA{
	public abstract void m();
}
main(){
	AA a = new AA(){
		public void m(){
		
		}
	};
	a.m();
}
```

```java
class Person(){
	String name;
	public void eat(){}
}

main(){
    Person p = new Person(){
        public void eat(){}
    };
    p.eat();
}
```

**4.抽象类和接口有哪些共同点和区别？**

相同点：不能实例化

不同点：

​		抽象类：有构造器

​		接口：不能声明构造器

多继承	vs	单继承

**5.如何创建静态成员内部类和非静态成员内部类的对象？**

# 异常处理

## 异常概述与异常体系结构

使用计算机语言进行项目开发的过程中，很多问题不是靠代码可以避免的，比如：客户输入数据的格式、读取文件是否存在、网络是否始终保持通常等等

- **异常：在Java语言中，将程序执行中发生的不正常情况称为“异常”。** 

  **(开发过程中的语法错误和逻辑错误不是异常)** 

- **Java程序在执行过程中所发生的异常事件可分为两类：**

  **注意：**虽然error和exception都叫异常，但是因为error的异常我们不做处理，所以通常所说的异常指的是Exception

**Error：**Java虚拟机无法解决的严重问题。如：JVM系统内部错误、资源耗尽等严重情况。比如：StackOverflowError和OOM。一般不编写针对性的代码进行处理。

**Exception：**其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。

例如：

> 空指针访问
>
> 试图读取不存在的文件
>
> 网络连接中断
>
> 数组角标越界

- 对于这些错误，一般有**两种解决方法**：一是遇到错误就终止程序的运行。另一种方法是由程序员在编写程序时，就考虑到错误的检测、错误消息的提示，以及错误的处理。 

- 捕获错误最理想的是在编译期间，但有的错误只有在运行时才会发生。

  比如：除数为0，数组下标越界等 

  》 分类：编译时异常和运行时异常

  **注意：**红色是编译时出现异常，蓝色是运行出现异常

![image-20211027193433240](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211027193433240.png)

## Java异常处理的方式：

### 异常处理机制一：try-catch-finally

**自己处理**

在编写程序时，经常要在可能出现错误的地方加上检测的代码，如进行x/y运算时，要检测分母为0，数据为空，输入的不是数据，而是字符等。过多的if-else分支会导致程序的代码加长、臃肿，可读性差。因此采用异常处理机制。

**Java异常处理**

Java采用的异常处理机制，是将异常处理的程序代码集中在一起，与正常的程序代码分开，使得程序简洁、优雅，并易于维护。

### 异常处理机制二：throws+异常类型

**给别人处理，让别人try-catch-finally**

### 手动抛出异常

```java
package com.ThrowAble3;

public class StudentTest {
    public static void main(String[] args) {
        try {
            Student stu = new Student();
            stu.resister(0);
            System.out.println(stu);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

class Student{
    private int id;
    public void resister(int id) throws Exception {
        if(id > 0){
            this.id = id;
        }else{
//            System.out.println("数据非法");
            //手动抛出异常对象
//            throw new RuntimeException("数据非法");
            throw new Exception("数据非法");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
```



### 一、异常的处理：抓抛模型

#### 过程一：“抛”：

​          程序在正常执行的过程中，一旦出现异常，
​          就会在异常代码处生成一个对应异常类的对象。
​          并将此对象抛出。
​          一旦抛出对象以后，其后的代码就不再执行。
关于异常对象的产生：
​          ①系统自动生成的异常对象
​          ②手动生成一个异常对象，并抛出（throw）

#### 过程二：“抓”：

​          可以理解为异常的处理方式：
​              ①try-catch-finally
​              ②throws

### 二、try-catch-finally的使用:

1.finally是可选的
2.finally中声明的是一定会被执行的代码。即使catch中又出现异常了，try中有return语句，catch中有return语句等情况。
  注意：finally在程序终止的前提下依旧执行完
3.像数据库连接、输入输出流、网络编程Socket等资源，JVM是不能自动回收的。我们需要自己手动的进行资源的释放。此时的资源释放，就需要声明在finally中。

#### 格式：

​          try{
​              //可能出现异常的代码
​          }catch(异常类型1 变量名1){
​              //处理异常的方式1
​          }catch(异常类型2 变量名2){
​              //处理异常的方式2
​          }catch(异常类型3 变量名3){
​              //处理异常的方式3
​          }
 ... ...
 finally{
  //一定会执行的代码
 }

####  说明：

 1.finally是可选的。
 2.使用try将可能出现异常的代码包装起来，在执行过程中，一旦出现异常，就会生成一个对应异常类的对象，根据此对象的类型，去catch中进行匹配
 3.一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常处理。 一旦处理完成，就跳出当前try-catch结构（没有写finally的情况），继续执行其后的代码。
 4.catch中的异常类型，
 如果**没有子父类关系**，则谁声明在上，谁声明在下没有关系。
 如果满足子父类关系，则要求子类一定声明在父类的上面。
 否则，报错。
 **5.常用的异常对象处理的方式：**
  ①String getMessage()
  ②printStackTrace()
 6.在try结构中声明的变量，在出了try结构以后，就不能再调用
 **7.try-catch-finally结构可以嵌套**

 体会1：使用try-catch-finally处理编译时异常，使得程序在编译时就不再报错，当运行时仍可能报错。
      相当于我们使用try-catch-finally将一个编译时可能出现的异常，延迟到运行时出现
 体会2：开发中，由于运行时异常比较常见，所以我们通常就不针对运行时异常编写try-catch-finally了。
      针对于编译时异常，我们说一定要考虑吧异常的处理

### 异常处理的方式二：throws+异常类型

**1."throws + 异常类型"写在方法的声明处。**

指明此方法执行时，可能会抛出的异常类型。 一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，此对象满足throws后异常类型时，就会被抛出。异常代码后续的代码，就不再执行！
**2.体会：**

try-catch-finally：真正的将异常给处理掉了。
throws的方式只是将异常抛给了方法的调用者。并没有真正将异常处理掉。
**3.开发中如何选择使用try-catch-finally，还是使用throws？**
  3.1 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws。
      意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。
  3.2 执行的方法a中，先后又调用了另外的几个方法，这几个方法又是递进关系执行的。
      我们建议这几个方法使用throws的方式进行处理。
      而执行的方法a可以考虑使用try-catch-finally方式进行处理。

### 用户自定义异常类

#### 如何自定义异常类？

1.继承于现有的异常结构：RuntimeException、Exception
2.提供全局常量：serialVersionUID
3.提供重载的构造器

## 异常处理总结

![image-20211029084708241](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211029084708241.png)

​	 ![image-20211029084747745](C:\Users\Phoenix—C\AppData\Roaming\Typora\typora-user-images\image-20211029084747745.png)

