







div的css设计参考

```css
{
    width:400px;
	height:200px;
	background-color:white;
	border:1px solid black;
	margin:100px auto;
	text-align:center;
	line-height:200px;
	font-size:18px;
}
```



# JavaScript

## 什么是脚本语言？

脚本语言的特点，不能独立运行，要依赖于网页

## JavaScript组成

1.ECMAScript

2.DOM 文档

3.BOM 浏览器

**注意：**所有的js代码在一个script标签中编写

属性：

​	1、type = 'text/javascript' 声明当前标签内写的文本格式（省略）

​	2、src = 'demo.js' 引入外部的.js文件

注意：

​	1、可以有多个script标签，多个script标签是自上而下顺序执行

```javascript
<script type = 'text/javascript'>
    alert("hello world");
</script>
<script>
    alert(2);
</script>
<script>
    alert(3);
</script>
<script src = 'demo.js'>
	alert(4);//无法执行，外部引入后，执行不到
</script>
```

## 输出语句

alert();警告框

document.write();在当前页面上输出内容，内容中有标签可以自动解析

console.log();在控制台中输入内容，一般情况下用于代码调试。

## 数据类型

### 基本数据类型

**数字，布尔值，字符串**

### 复合/引用数据类型

### 特殊数据类型 

​				**null 空**

​				**undefined**

​				**NaN（not a number）**

## 变量

**声明变量**

var 声明变量

如果声明变量的时候，没有值赋给这个变量，系统默认赋值成undefined

```javascript
var num = 10;
alert(num);
num  = "hello";
alert(num);
```

## 关键字

**typeof**

格式：typeof 常量/变量

功能：输出当前常量或变量的数据类型

```javascript
alert(typeof 100);//输出number
alert(typeof "hello");//输出string
alert(typeof true);//输出Boolean
alert(typeof undefined);//输出undefined
alert(typeof typeof undefined);//输出string，因为typeof undefined是string
```

## 算术运算符

### 自动数据类型转换

自动数据类型转换：不同数据类型之间是没有办法进行运算，将数据转成同一数据类型，再进行计算。

（1）其中有一个操作数必须是字符串，运算符必须是+号，别的数据类型转成字符串，进行字符串拼接。

（2）任何数据和字符串做+加法以外的操作，那么字符串要先转成数字再去进行运算。

①如果字符串是一个纯数字字符组成的字符串，转成对应的数字

```javascript
alert(100 - "20");//输出80
```

②如果字符串中含有除数字以外的别的字符，转成NaN，NaN和任何数据运算都是NaN

```javascript
alert(100 - "20a");//输出NaN
```

3.除字符串以外的数据，在进行算术运算的时候，先转成数字，再进行运算。

```javascript
alert(10 + true);//输出11，true=1
alert(10 + false);//输出10，false=0
alert(10 - null);//null=0
alert(10 - undefined);//undefined→NaN
alert(10 - NaN)
```

#### 强制类型转换

```
字符串转换数字类型：parseInt()、parseFloat()
parseInt();是把其他类型转换为整型
parseFloat();是把其他类型转为浮点型
Number(数据);是把其他类型转为数字
Boolean();是把其他类型转为布尔值
	①非0即真，非空即真
```

**NaN**:特殊的number类型结果，代表意外转换的数字，和任何东西都不等

```
alert(Boolean(100));//输出结果为true
alert(Boolean(-100));//输出结果为true
alert(Boolean(0));//输出结果为falese
```



###  注意

计算机是不会进行小数运算（天生bug

金融账户，只存储整数，单位是分

```javascript
alert(0.8 - 0.1);
//输出0.70000001，非常小的误差
```

在JS中，除数可以为0

```javascript
alert(10 / 0);//输出infinity，无穷大
alert(-10 / 0);//输出-infinity，无穷小
alert(infinity + 100);//输出infinity，无穷大+任何数，也是无穷大
```

## 转换

### Number

**注意：**只有纯数字字符组成的字符串转数字，才能转成数字，其他都是NaN

`Number("3.14aq");显示为NaN`

### parseInt

`parseInt(3.14)`显示为3
`parseInt("3.14aa")`显示为3
`parseInt("aa3.14aa")`显示为NaN
`parseInt(" ")`显示为NaN
**1.取整**
**2.将别的进制转成十进制，必须传入字符串**

#### 举例

52
二进制数：110100
八进制：64
十六进制：34

```javascript
var str1 = ："110100";
alert(parseInt(str1,2));
//显示为52
var str2 = "64";
alert(parseInt(str2,8));
//显示为52
```

### parseFloat

`parseFloat("3.14aa")`显示为3.14

### Boolean

将其他类型转换为布尔型值

代表空、否定的值会被转换为false，其余都是true，如“  、NaN、undefined、null、0”

## 关系运算符

**注意：**值，绝对是布尔值

### 自动数据类型转换

一、和其他运算符一样，当关系运算符操作非数值时要遵循以下规则：

<1>两个操作数都是数值，则数值比较

<2>两个操作数都是字符串，则比较两个字符串对应的字符编码值

​		ASIIC码表

​		（1）如果是两个单个字符进行比较，直接比较字符的ASIIC码值

​		（2）逐位进行比较，一比较出大小，直接出结果，就不比较后面的了。

<3>两个操作数有一个是数值，则将另一个转换为数值，再进行比较

二、在相等和不等的比较上，如果操作数是非数值，则遵循以下规则：

<1>一个操作数是布尔值，则比较之前将其转换为数值，false转换为0，true转换为1

<2>一个操作数是字符串，则比较之前将其转换为数值再比较

<3>一个操作数是NaN，则==返回false，!=返回true；并且NaN和自身不相等

```javascript
alert(NaN == NaN);
//显示false
```

|                   |  值   |
| :---------------: | :---: |
| null == undefined | true  |
|   'NaN' == NaN    | false |
|     5 == NaN      | false |
|     NaN ==NaN     | false |
|    false == 0     | true  |
|     true == 1     | true  |
|     true == 2     | false |
|  undefined == 0   | false |
|     null == 0     | false |
|   '100' == 100    | true  |
|   '100' === 100   | false |

<4>===恒等，必须数字和数据类型都相等，返回true，否则返回false

```javascript
alert("100" === 100);//false
alert(Number("100") === 100);//true
```

```javascript
alert(Number(null));//0
alert(Number(undefined));//NaN
alert(null == undefined);//true
```

## 逻辑运算符

### 与运算

短路操作：当表达式1为false时，表达式2就不执行了，就直接出结果为false

### 或运算

短路操作：当表达式1为true时，表达式2就不执行了，就直接出结果为true

### 非运算

**格式**

​			**!表达式**

规律：先将表达式的值自动数据类型转换为布尔值，然后，再取反。

**注意**：非空即真，非0即真

## 流程控制语句

### 顺序结构

### 分支结构/选择结构/条件结构

#### switch

**注意**

1、一般情况下不要省略break

2、一般情况下不要省略default

**if的多分支语句和switch语句的编写**

1.如果是匹配确定的结果，优先使用switch

2.需要判断的题目，只能用if语句

**通过省略break简化代码**

举例：

```javascript
var mouth = 4;
var year = 2000;
switch(mouth){
	case1:
	case3:
	case5:
	case7:
	case8:
	case10:
	case12:
		alert("31天");
		break;
	case2:
    	if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
    	alert("29天");
    	}else{
    	alert("28天");
    	}
    	break;
    default:
    	alert("30天");
    	break;
}
```

### 循环结构

**while**

```javascript
var i = 0;
while(i < 10){
	document.write("hello world</br>");
	i++;
}
```

**do_while**

```javascript
do{
	循环语句;
}while(循环条件);
```

**for**

## 函数

**语法：**

```javascript
函数声明：

	function 函数名(){

		函数体(具体要执行的代码);

}
```

```javascript
<script type = 'text/javascript'>
	function print(n){
		for(var i = 0;i < n;i++){
			document.write("hello world</br>");
		}
	}
	print(2);
</script>
```

n是形参

传参：用实参给形参赋值

```
调用函数：
	格式：函数名（实参1，实参2，..）;
```

```javascript
<script type = 'text/javascript'>
	function a1(num1 , a2 , num2){
		switch(a2){
			case "+":
				return num1 + num2;
				break;
			case "-":
				return num1 - num2;
				break;
			case "*":
				return num1 * num2;
				break;
			case "/":
				return num1 / num2;
				break;
		}
	}
	var num3 = a1(10 , "+" , 5);
	alert(num3);
</script>
```

### arguments

注意：每一个函数内部都有一个argumens，系统内置的

```javascript
function show(){
    alert(arguments);
}
show();
//显示object arguments
```

注意：arguments是用来存储实际传入的参数

​	属性：

​			arguments.length 输出当前里面存储的参数个数

```javascript
function show(){
    alert(arguments.length);
}
show(10, true, "hello", 40);
//显示4,参数个数为4
```

​	访问某一个数据：

​			arguments[下标];	

​		**注意：**下标是从0开始的

```javascript
function show(){
	var num = 0;
	for(var i = 0;i < arguments.length;i++){
		num += arguments[i];
	}
	return num;
}
var sum = show(10, 20, 30);
alert(sum);
//输出60，10+20+30	
```

### 函数的作用域

 任何程序在执行的时候都要占用内存空间。函数调用的时候也要去占用内存

**垃圾回收机制：**

调用函数的时候，系统会分配对应的**空间**给这个函数使用。

（**空间**大小一般情况由这个函数声明的变量和形参决定）

当函数使用完毕后，这个内存空间要释放，还给系统。

**例子：**

```javascript
var a = 2;
function show(){
	a++;
	alert(a);
}
show();
show();
alert(a);
//输出3，4，4
//因为a是在函数外面进行定义，在进入show函数的时候通过a++改变了a的值，然后第一次调用show函数的时候输出为3，第二次调用show函数的时候，再次执行a++改变a的值，输出为4.最后直接输出a，因为两次执行了show函数，导致最后的a为4
```

上述例子，声明再全局的变量叫做全局变量

```javascript
function show(){
	var a = 2;
	a++;
	alert(a);
}
show();
show();
alert(a);
//两次调用show函数，输出的值都是3，最后直接输出a，由于a在函数内，每次执行完函数都会销毁掉，所以代码内不存在变量a，所以会报错（a is not defined）
```

上述例子，变量随函数调用被创建，随着函数结束而被销毁掉

因此是局部变量

此为，内存管理机制。

### 内存管理机制

在函数中声明的变量和形参，会随着函数的调用被创建，随着函数的调用结束而被销毁。

在函数中声明的变量和形参，有效范围是当前函数（当前函数的大括号），局部作用域。

就近原则：离哪个作用域近，就使用哪个作用域内的同名变量

## 函数递归

满足以下三个特点就是递归：

1、函数自己调用自己

2、一般情况有参数

3、一般情况下有return

注意：递归可以解决循环能做的所有的事情，有些循环不容易解决的事情，递归也能轻松解决

### 方法

1.首先去找临界值，即无需计算，获得的值。

2.找这一次和上一次的关系

3.假设当前函数已经可以使用，调用自身计算上一次

```javascript
<script type="text/javascript">
function show(n){
	if(n == 1){
		return 1;
	}
	return show(n - 1) + n;
}
alert(show(100));
</script>
```

我的理解：n=100，而不是1，因此不知道`show(100)`是多少，因此执行`show(99) + 100`,但是又不知道`show(99)`是多少，因此执行`show(98) + 99`，此时`show(99)`还未释放，还在内存空间内，一直到`show(1)`，知道了`show(1)`的值之后，可以知道`show(2)`，依次晚上类推，得到最后结果。

本题的临界值就是`show(1)`.

## 拓展训练

### 斐波那契数列

兔子繁殖问题，设有一对新生兔子，从第四个月开始他们每个月月初都生一对兔子，新生的兔子从第四个月月初开始又每个月生一对兔子。

按此规律，并假定兔子没有死亡，n（n <= 20）个月月末共有多少对兔子？

|        | 一月兔 | 二月兔 | 三月兔 | 四月兔 | 总数 |
| ------ | :----- | ------ | ------ | ------ | ---- |
| 一月   | 1      | 0      | 0      | 0      | 1    |
| 二月   | 0      | 1      | 0      | 0      | 1    |
| 三月   | 0      | 0      | 1      | 0      | 1    |
| 四月   | 1      | 0      | 0      | 1      | 2    |
| 五月   | 1      | 1      | 0      | 1      | 3    |
| 六月   | 1      | 1      | 1      | 1      | 4    |
| 七月   | 2      | 1      | 1      | 2      | 6    |
| 八月   | 3      | 2      | 1      | 3      | 9    |
| 九月   | 4      | 3      | 2      | 4      | 13   |
| 十月   | 6      | 4      | 3      | 6      | 19   |
| 十一月 | 9      | 6      | 4      | 9      | 28   |
| 十二月 | 13     | 9      | 6      | 13     | 41   |

```javascript
function show(){
	if(n < 4){
	return 1;
	}
	return show(n - 1) + show(n - 3);
}
alert(show(20));
```

### 猴子吃桃

有一堆桃子不知数目，猴子第一天吃掉一半，觉得不过瘾，又多吃了一个。第二天照此办法，吃掉剩下桃子的一半另加一只，天天如此，到第num（num <= 10）天早上，猴子发现只剩一只桃子了，问这堆桃子原来有多少只？（思路：n为还剩n天吃完的桃子数）

```javascript
function peach(n){
	if(n == 1){
		return 1;
	}
	return (peach(n - 1) + 1) * 2;
}
alert(peach(4));
```

思路：（peach（10）/2）-1 = peach（9）

​	所以，peach（10） = （peach（9）+1）* 2

​	所以，peach（n） = （peach（n - 1）+1） * 2

### 小问题

输入一个n，打印n个hello world的运行结果

`print(n) = print(n - 1) + 一次输出`

```javascript
<script type="text/javascript">
	function print(n){
		if(n == 0){
			return;
		}
	document.write("hello world<br/>");
	return print(n - 1);
	}
	var num = prompt("请输入打印次数：");
	print(num);
</script>
```

思路：临界值为0，当执行到n=0时，停止执行。n=0之前，持续执行打印hello world。

## 数组

### 数组的概念

**声明数组：**

​	1、通过new创建数据

​				参数：传入任意的数据，存储到数组中。

`var arr = new Array(100,true,"hello");`

​	2、省略new运算符创建数组

`var arr = Array(100,true,"hello");`

​	3、数组常量进行赋值

`var arr = [100,true,"hello"];`

**注意：**第一和第二两种方法，如果传入参数只有一个，并且是数组的时候，直接声明那么长一个数组

`var arr = Array(10);`即声明长度为10的数组

**数组的属性：**

​	数组.length 返回值数组[元素]的个数

------

以下通过循环给数组每个元素赋值，随机数

`Math.random()`随机[0，1）

`document.write(Math.random());`

`document.write(parseInt(Math.random() * 10));`

↑随机[0,10)的整数部分。即0~9

下面利用数组打印，打印十个随机数↓：

```javascript
<script type="text/javascript">
	var arr = new Array(10);
	for(var i = 0;i < arr.length;i++){
		arr[i] = parseInt(Math.random() * 10);
	}
	for(var j = 0 ; j < arr.length ; j++){
		document.write(arr[j] + "</br>");
	}
</script>
```

### 数组的遍历

遍历就是把数组中的每一个元素从头到尾来一遍。

**方法：**

**for**循环

**for...in** 遍历 →快速遍历/快速枚举

```javascript
var arr = [1,3,5,7,9,10];
for(var i in arr){
	document.write(arr[i] + "</br>");
}
```

### 栈结构

push、pop 

**栈：**木盆

**结构：**从同一头进，从同一头出

**特点：**先进后出

#### 数组的两个方法形成栈结构

##### 	**push**

​			格式：`数组.push(参数1,参数2...);`

​			功能：给数组的末尾添加元素

​			返回值：插完元素以后数组的长度

```javascript
<script type="text/javascript">
	var arr = ["北京","上海","广州"];
	var res = arr.push("hello","world");
	alert(arr);
	alert(res);
</script>
//第一个alert显示北京，上海，广州，hello，world
//第二个alert显示5，因为插了元素之后的数组长度为5
```

##### 	pop

​			格式：`数组.pop();`

​			参数：没有参数

​			返回值：取下一个元素，返回的是取下的那一个元素

​			功能：从数组末尾取下一个元素

```javascript
<script type="text/javascript">
	var arr = ["北京","上海","广州"];
	var res = arr.pop();
	alert(res);
	alert(arr);
</script>
//第一个alert显示的是从原来的arr中取下的末尾元素
//所以第一个alert现实的是“广州”
//第二个alert现实的是原来的arr中，去掉末尾元素之后的元素
//第二个alert显示的是“北京，上海”
```

### 队列结构

push、shift、unshift

**结构：**从末尾进，从头部出

**特点：**先进先出



**push**



**shift()**

​	格式：`数组.shift()`

​	参数：没有参数

​	功能：从数组的头部取下一个元素

​	返回值：取下的元素

```javascript
<script type="text/javascript">
	var arr = ["1","2","3"];
	var res = arr.shift();
	alert(res);
	alert(arr);
</script>
//第一个alert显示1，从原来的arr中取出头部
//第二个alert显示2，3，原来的arr取出头部后剩下的元素
```

**unshift()**

​	格式：`数组.unshift(参数1，参数2...);`

​	功能：从数组的头部插入元素

​	返回值：插完元素后数组的长度

```javascript
<script type="text/javascript">
	var arr = ["1","2","3"];
	var res = arr.unshift("-1","-2");
	alert(res);
	alert(arr);
</script>
//第一个alert显示5，插入新元素后的数组长度
//第二个alert显示-1，0，1，2，3，是插入新元素后的新数组所有元素
```

### concat()

1、拷贝原数组，生成新数组。

2、合并数组

​		格式：`数组.concat(数组,数据,...);`

​		返回值：合并成的新数组，原数组不改变

​		**注意：**就算传入的是数组，数组中的元素要单独拆出来再进行合并

```javascript
<script type="text/javascript">
	var arr1 = ["1","2","3"];
	var arr2 = ["4","5","6"];
	var newArr = arr1.concat(arr2,"hello",true,arr1);
	alert(newArr);//合成后的新数组
	alert(newArr.length);//11
	alert(arr1);//不变
</script>
```

### slice

​		格式：`数组.slice(start,end);`	[start,end)

​		功能：可以基于当前数组获取指定区域元素[start,end)，提取出元素生成 新数组

​		返回值：生成的新数组，原数组就不会发生任何的改变

```javascript
<script type="text/javascript">
	var arr1 = [10,20,30,40,50];
	var arr2 = arr1.slice(2,5);
	alert(arr2);//显示30，40，50
	alert(arr2.length);//显示3
</script>
```

### splice

​		格式：`数组.splice(start,length,数据1,数据2..);`

​		参数：

​				start 开始截取的位置

​				length 截取的元素长度

​				第三个参数开始：在start位置，插入的元素

​				返回值：截取下来的元素组成的数组

​		功能：**增加/删除/修改**

#### 增加实例

```javascript
<script type="text/javascript">
	var arr1 = [1,2,3,4,5,6,7,8];
	arr1.splice(2,0,"hello","world");
	document.write(arr1);
</script>
//从2号元素start，只截取0个长度完成增加。
```

#### 删除实例

```javascript
<script type="text/javascript">
	var arr1 = [1,2,3,4,5,6,7,8];
	arr1.splice(2,2);
	document.write(arr1);
</script>
//从2号元素start，只截取2个长度，不插入元素，完成删除。
```

#### 修改实例

**注意：**是先执行删除，再执行增加。完成修改操作

```javascript
<script type="text/javascript">
	var arr1 = [1,2,3,4,5,6,7,8];
	arr1.splice(2,2,"3.14");
	document.write(arr1);
</script>
//从2号元素start，只截取2个长度，再插入新元素完成修改。
```

### join

​		格式：`数组.join(字符串);`

​		功能：将数组中的元素，用传入的拼接符，拼接成一个字符串

​		返回值：拼接好的字符串

```javascript
<script type="text/javascript">
	var arr = [1,2,3,4,5];
	var str = arr.join("+");
	document.write(str + "</br>");
	document.write(arr);
</script>
//str是1+2+3+4+5
//arr不变，依旧是1，2，3，4，5
```

### reverse

​		功能：逆序	

​		格式：`数组.reverse();`

```javascript
<script type="text/javascript">
	var arr = [1,2,3,4,5];
	arr.reverse()
	document.write(arr);
</script>
//显示逆序后的数组，5，4，3，2，1
```

### sort

​		格式：`数组.sort();`

​		功能：排序，默认是从小到大排序

```javascript
<script type="text/javascript">
	var arr = [3,4,2,1,5];
	arr.sort();
	document.write(arr);
</script>
//输出的是从小到大排序的1~5
```

**注意：**默认的从小到大排序是有问题的，是按照字符串排序，逐位比较

```javascript
<script type="text/javascript">
	var arr = [30,40,20,1,5];
	arr.sort();
	document.write(arr);
</script>
//输出的是1，20，30，40，5
```

如果要用数值大小排序，

```javascript
<script type="text/javascript">
	var arr = [30,40,20,1,5];
	arr.sort(function(value1,value2){
		return value1 - value2;
	})//倒序改成value2 - value1就行
	document.write(arr);
</script>
//输出的是1，5，20，30，40
```

### 引用

### 声明提升和省略var

内存分配，一次分配。

预编译：在所有代码运行之前，计算机将代码从头到尾看一遍。将这个程序需要运行的空间一次性分配好



声明提升：在当前作用域，声明变量和函数，会直接提升在整个代码的最前面运行。 



省略var声明变量，会将变量强制声明成全局变量。

```javascript
function show(){
	var num = 10;
	alert(num);
}
show();//10
alert(num);//报错
```

如果进行省略var，如下：

```javascript
function show(){
	num = 10;
	alert(num);
}
show();//10
alert(num);//10
```

省略var，直接去强制给一个变量赋值，这个变量会被JS强制声明成全局变量。

注意：属于语法错误。

## 二维数组

### 冒泡排序

```javascript
<script type="text/javascript">
	var arr = [9, 23, 54, 12, 65];
	for(var  i = 0; i < arr.length - 1 ; i++ ){
		for(var j = 0; j < arr.length - i - 1;j++){
			if(arr[j] > arr[j+1]){
				var temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
		}
	}
	alert(arr);
</script>
```

### 选择排序

```javascript
<script type="text/javascript">
	var  arr = [9, 20, 12, 34, 8];
	for(var i = 0;i < arr.length - 1;i++){
		for(var j = i+1 ; j < arr.length; j++){
			if(arr[i] > arr[j]){
				var temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}
	alert(arr);
</script>
```



#### 封装函数

## ECMA5严格模式

![image-20210404222503280](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210404222503280.png)

### 严格模式

写在哪个作用域下，在哪个作用域下生效

`"use strict"`

注意：尽量注意不要把严格模式写在全局

```javascript
<script>
	"use strict";
	console.log("已经进入严格模式");
</script>
```

1.全局变量声明时，必须加var

2.this无法指向全局对象

3.函数内重名属性

```javascript
function show(num1, num1, num2){
	alert(num1 + "," + num2);
}
show(10, 20, 30);
//输出结果为 20，30
//因为第二个num1把第一个num1覆盖了
```

```javascript
function show(num1, num1, num2){
	"use strict";
    alert(num1 + "," + num2);
}
show(10, 20, 30);
//直接报错，因为num1重复声明
```

4.aruguments对象不允许被动态改变

```javascript
function show(num1, num2){
    num1 = "hello";
	alert(num1 + "," + num2);
    alert(arguments[0] + "," + arguments[1]);
}
show(10, 20);
//输出两次alert都是hello，20
```

```javascript
function show(num1, num2){
	"use strict";
    num1 = "hello";
	alert(num1 + "," + num2);
    alert(arguments[0] + "," + arguments[1]);
}
show(10, 20);
//第一次alert是hello，20
//第二次alert10，20
//因为arguments对象不允许被动态改变
```

5.新增保留字：implements，interface，let，package，private，protected，public，static，yield

（在严格模式下，新增的保留字不可以作为变量名使用）

## ECMA5新增数组的方法

### indexOf

格式：`数组.index(item, start);`

参数：item 任意的数据

​			start 下标，可以不传入，默认是0

功能：在数组中查找item元素下标，从start开始去查找

返回值：-1 没有查找到

​				>=0 查找到的元素下标

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 20, 50, 60];
	var index = arr.indexOf(20);
	alert(index);
</script>
//输出为1，因为第一个20的下标为1
```

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 20, 50, 60];
	var index = arr.indexOf(20, 2);
	alert(index);
</script>
//输出为4，因为从下标2开始查找20，查找到的第一个20下标为4
```

```JavaScript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 20, 50, 60];
	var index = arr.indexOf(120, 2);
	alert(index);
</script>
//输出为-1，因为没有查找到
```

### forEach

forEach(ECMA5新增)循环

格式：

```javascript
数组.forEach(function(item, index, arr){
	/*
	item当前遍历到的元素
	index当前遍历到元素的下标
	arr数组本身
	*/
    document.write(item + "," + index + "," + arr + "</br>");
});
```

举例：

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 50];
	arr.forEach(function(item, index, arr){
	    document.write(item + ", " + index + ", " + arr + "</br>");
	});
</script>
```

输出结果

```javascript
10, 0, 10,20,30,40,50
20, 1, 10,20,30,40,50
30, 2, 10,20,30,40,50
40, 3, 10,20,30,40,50
50, 4, 10,20,30,40,50
```

### map

映射

功能：会遍历当前数组，然后调用参数中的方法，返回当前方法的返回值

**注意：**map不会改变原有数组，而是将函数执行一次之后的返回值组成一个新数组，返回回来

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 50];
	var newArr = arr.map(function(item, index, arr){
	    return item  * 1.3;
	});
	alert(newArr);
	alert(arr);
</script>
//第一个alert输出结果是13，26，39，52，65
//第二个alert输出结果是10，20，30，40，50
//因为遍历了要做的事情，映射关系
```

### filter

过滤

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 50];
	var newArr = arr.filter(function(item, index, arr){
	    //过滤条件
        return item  > 20;
	});
	alert(newArr);
	alert(arr);
</script>
//输出30，40，50
```

### some

某些，查找当前数组中是否有符合条件的元素

注意：在数组中查找是否有符合条件的元素。有返回true，没有返回false

短路操作：只要找到符合条件的元素，后面的循环就停止了

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 50];
	var newArr = arr.some(function(item, index, arr){
	    return item  > 20;
	});
	alert(newArr);
	alert(arr);
</script>
//第一个alert显示true
```

### every

每一个，查找当前数组中每一个元素是否符合条件。符合返回true，不符合返回false

短路操作：只要找到不符合条件的元素，后面的循环就停止了

### reduce

归并

```javascript
<script type="text/javascript">
	var arr = [10, 20, 30, 40, 50];
	var newArr = arr.reduce(function(prev, next, index, arr){
	    /*
	    	prev 第一次是下标为0的元素
	    		 第二次开始 上一次遍历return的值
	    	next 从下标1开始，当前遍历到的元素
	    	arr 数组本身
	    	*/
        alert(prev + ", " + next);
        return prev + next;
	});
	alert(newArr);
	alert(arr);
</script>
//输出结果是
//10，20
//30，30
//60，40
//100，50
//150
```

## 字符串

概念：所有带单引号或者双引号的都叫做字符串。

声明：

​	1、通过new运算符去声明字符串

​	2、省略new声明字符串

​	3、字符串常量赋值

```javascript
三种声明方法举例：
var str1 = new String(100);
alert(str1 + 20);
//输出10020
var str2 = String(100);
alert(str2 + 20);
//输出10020
var str3 = "100";
alert(str3);
//输出100
```

访问字符串中的字符：

​	`字符串.length` → 访问字符串中字符的个数

注意：中文 `utf-8`(三个字符表示一个汉字)

​					`gbk`（两个字符表示一个汉字）

​					在计数的时候都当作一个汉字计数

```javascript
var str = "hello北京";
alert(str.length);
//输出为7，计数时，当作一个汉字计数
```

访问字符串中单个字符：

​	`字符串.charAt(下标)`

或者

​	`字符串[下标]`

```javascript
var str = "hello北京";
alert(str.charAt(4));
alert(str[4]);
//两次输出结果都是o
str[4] = "x";//修改o为x，失败，字符串是只读，字符串一旦被声明就没有办法修改
//如果想要修改字符串，只能将原字符串销毁，重新生成新的字符串
```

```javascript
var str1 = new String(100);
alert(typeof str1);//object 对象 引用数据类型
var str2 = String(100);
alert(typeof str2);//string
var str3 = "100";
alert(typeof str3);//string
```

### 字符串的方法格式

`字符串.函数名()`

仅作了解。

|    格式     |            功能            |
| :---------: | :------------------------: |
|    big()    |    用大号字体显示字符串    |
|   blink()   | 显示闪动字符串（ie下无效） |
|   bold()    |     使用粗体显示字符串     |
|   fixed()   |   以打字机文本显示字符串   |
|  strike()   |   使用删除线来显示字符串   |
| fontcolor() |  使用指定颜色来显示字符串  |
| fontsize()  |  使用指定尺寸来显示字符串  |
|   link()    |     将字符串显示为链接     |
|    sub()    |     把字符串显示为下标     |
|    sup()    |     把字符串显示为上标     |

**注意：**用于`document.write()`，用特殊的样式输出该字符串

举例：

```javascript
document.write("hello".big() + "World".fontcolor("red"));
//输出大一点的hello和红色的world
```

### 字符串的方法-字符串的获取方法

#### charCodeAt

格式：`字符串.charCodeAt(下标)`

功能：访问字符串中对应下标字符的ASCII码值。

```javascript
var str = "hello";
alert(str.charCodeAt(1));
//输出为101，e的ascii码值
```

#### String.fromCharCode

格式： `String.fromCharCode(码值1,码值2...);`

功能：将传入的ASCII码值转成对应的字符

返回值：组成的字符串

```JavaScript
var str = String.fromCharCode(97, 98, 99, 100);
alert(str);
//输出abcd，分别是97~100对应的字符
```

#### indexOf-第二种用法

```JavaScript
var supStr = "abcabcabc";
var subStr = "abcd";
var index = supStr.indexOf(subStr,1);
alert(index);
//输出3
```

格式：`supStr.indexOf(subStr,start);`

参数：第一个参数，查找的字符串

​			start 那个那个下标开始去查找，如果不传入，默认从0开始

功能：在supStr中查找subStr第一次出现的位置，从start这个位置开始查找。

返回值：-1，说明没有查找到

#### lastIndexOf

格式：`supStr.lastIndexOf(subStr,start);`

功能：在supStr中查找subStr最后一次出现的位置

返回值：-1，没有查找到

#### search

格式：`supStr.search(subStr);`

参数：字符串/正则表达式

功能：在supStr中查找subStr第一次出现的位置

返回值：-1，没有查找到

```javascript
var supStr = "Abcabcabc";
var subStr = "abc";
var index = supStr.search(subStr);
alert(index);
//输出为3，因为第一个abc的a是大写
```

如果不想因为大小写影响查找，使用正则表达式。

将第二行的`subStr = "abc";`

改写为：`subStr = /abc/i;`

##### 正则表达式

修饰符：i 忽略大小写

​				g 全局匹配

### 提取字符串

#### subString

格式：`字符串.subString(start， end);`

功能：将字符串中[start， end) 提取这部分字符，生成一个新字符串

返回值：新生成的字符串

```javascript
var str = "hello";
var newStr = str.String(1, 4);
alert(newStr);
alert(str);
//第一个输出ell，从e开始，到o之前，不包含o
//第二个输出hello，不发生改变
```

**注意：**如果参数只有一个，则表示取到最后一个

#### subStr

格式：`字符串.subStr(start, length);`

返回值：新字符串

#### slice

是数组的方法，用法和subString相同

#### replace

格式：`supStr.replace(oldStr, newStr);`

功能：用newStr将oldStr，替换掉，生成新字符串

参数：

​			第一个参数传入的是字符串，只能替换一次

​			所以可以改成正则表达式

将"oldstr"→/oldstr/ig，既是全局匹配，又忽略大小写

返回值：替换成的新字符串。

#### split

字符串分割

格式：`字符串.split(分割符, length);`

参数：

​		第一个参数，用这个分割符对原字符串进行分割

​			第二个参数，控制返回的数组的元素个数，一般情况下不用

功能：用分割符对原字符串，进行字符串分割，将分割完毕以后的子串，放在数组中返回

返回值：数组

```javascript
var str = "how are you";
var arr = str.split(" ");
alert(arr);
alert(str);
//第一个alert输出how，are，you
//第二个alert输出原数组
//如果在str.split(" ")，变成str.split(" ",2)
//则仅显示两个元素，第二个参数是控制返回的数组元素个数	
```

**注意：**

1、相邻的两个分割符，会产生空字符串

2、分割符是空字符串""，直接将每一个字符，单独分割成子串，放在数组中返回

例如：

```javascript
var str = "how are you";
var arr = str.split("");
alert(arr);
//alert输出h,o,w, ,a,r,e, ,y,o,u
```

#### toLowerCase

转成全小写

#### toUpperCase

转成全大写

## 字符串练习

### 一

传入“HelloMyWorld”

返回”Hello my world“

```javascript
<script type="text/javascript">
	function wordOfStr(str){
		//把字符串分割成一个个元素组成的数组
		var arr = str.split("");
		//通过循环找到是大写字母的元素
		for(var i = 1;i < arr.length;i++){
			if(arr[i] >= "A" && arr[i] <= "Z"){
				//如果是大写，则变为小写，并加入一个空格
				arr[i] = arr[i].toLowerCase();
				arr.splice(i, 0, " ");
			}
		}
		return arr.join("");
	}
	var str = wordOfStr("HelloMyWorld");
	alert(str);
</script>
```

### 二、验证码

①纯数字验证码

②字母和数字组合验证码

①：

```javascript
<script type="text/javascript">
	function numTestCode(n){
		var arr = [];
		for(var i = 0;i < n;i++){
			var num = parseInt(Math.random() * 10);
			arr.push(num);
		}
		return arr.join("");
	}
	alert(numTestCode(6));
</script>
```

②：

a~z的ASCII码值为97~122

A~Z的ASCII码值为65~90

```javascript
<script type="text/javascript">
	function TestCode(n){
		var arr = [];
		for(var i = 0;i < n;i++){
			var num = parseInt(Math.random() * 123);
			if(num >=0 && num <= 9){
				arr.push(num);
			}else if(num >=97 && num <= 122 || num >= 65 && num <= 90){
				arr.push(String.fromCharCode(num));
			}else{
				i--;
			}
		}
		return arr.join("");
	}
	alert(TestCode(6));
</script>
```

### 三、统计字符串对象中的单词个数

```javascript
<script type="text/javascript">
	function countOfWord(str){
		var count = 0;
		for(var i = 0; i < str.length;i++){
			if(isABC(str[i]) && !isABC(str[i + 1])){
				count++;
			}
		}
		return count;
	}
//判断单个字符是否是字母
	function isABC(charStr){
		if(charStr >= "a"  && charStr <= "z" || charStr >= "A" && charStr <= "Z"){
			return true;
		}else{
			return false;
		}
	}
	alert(countOfWord("yes @she2321is**&&my@love"));
</script>
ody>
```

### 四、查找子串出现的次数

```javascript
<script type="text/javascript">
	function countOfStr(supStr, subStr){
		var arr= supStr.split(subStr);
		return arr.length - 1 ;
	}
	alert(countOfStr("abcabcabc", "abc"));
</script>
```

### 五、邮箱判断

已知邮箱用户名由数字字母下划线组成，域名是@1000phone.com，

判断一个字符串是否是邮箱，是就返回true，不是就返回false

```javascript
<script type="text/javascript">
function isEmail(email){
    //第一步，查找到“@”，区分哪部分是用户名，哪部分是域名
	var index = email.indexOf("@");
	if(index == -1){
    //如果查找不到“@”，说明不是一个邮箱，则返回false
		return false;
	}else{
    //取邮箱从“@”开始到末尾的一段字符串，判断域名是否正确    
		var endStr = email.substring(index);
		if(endStr != "@1000phone.com"){
			return false;
		}else{
    //取用户名，判断用户名是否是数字字母下划线组成的        
			var usename = email.substring(0, index);
			var isYes = true;
			for(var i = 0; i < usename.length;i++){
				if(!isDEF(usename[i])){
					isYes = false;
					break;
				}
			}
			return isYes;
		}
	}
}
alert(isEmail("mail@1000phone.com"));//true
alert(isEmail("ma&il@1000phone.com1"));//false
</script>
```

```javascript
//判断单个字符是否属于数字下划线
function isDEF(charStr){
	if(charStr >= "a" && charStr <= "z" || charStr >= "A" && charStr <= "Z" || charStr >= 0 && charStr <= 9 || charStr == "_"){
		return true;
	}else{
		return false;
	}
}
```

## 事件驱动函数

onclick

onblur — 失去交焦点

【注意】

通过id获取页面上对应的标签：`document.getElementById(id);`

标签之间的内容：`node.innerHTML;`

表单元素，获取其中内容：`node.value`

## 敏感词过滤

```javascript
<head>
	<meta charset="utf-8">
	<title></title>
	<script src="js/demo.js">
	</script>
	<style type="text/css">
		//设置下显示框的css样式
        #msg{
			width: 400px;
			height: 400px;
			border: 1px black solid;
		}
	</style>	
	<script type="text/javascript">
        //数组里放正则表达式输入的脏字
		var arr = [/靠/ig, /tmd/ig, /nm/ig];
			function btnclick(){
        //取到输入框里的内容
				var otxt = document.getElementById("txt1");
        //取到显示框里的内容
				var omsg = document.getElementById("msg");
        //取到输入框的内容赋给新的变量
				var oValue = otxt.value;
			for(var i = 0; i < arr.length; i++){
        //将变量内的所有元素，与脏字数组内相同的元素进行替换操作        
				oValue = oValue.replace(arr[i], "*");
			}
        //让显示框显示过滤之后的内容       
			omsg.innerHTML = oValue;
        //清空输入框内容        
			otxt.value = "";
		}
	</script>
</head>
	
<body>
	<textarea id="txt1" cols="30" rows="10"></textarea>
	<button onclick="btnclick();">发布</button>
	<div id="msg">	</div>
</body>
```

## 表单验证

```javascript
<head>
	<meta charset="utf-8">
	<title></title>
	<script src="js/demo.js">
	</script>
	<style type="text/css">
		#div{
			width: 200px; height: 200px; background-color: aliceblue; border: 1px black solid;  text-align: center;	
		}
	</style>
	<script type="text/javascript">
		function func(){
			var oUsename = document.getElementById("usename");
			var oUsenamespan = document.getElementById("usename_span");
			var oValue = oUsename.value;
			if(oValue.length < 6 || oValue.length > 18){
				oUsenamespan.innerHTML = "！长度应该是为6~18";
			}else if(!isABC(oValue[0])){
				oUsenamespan.innerHTML = "！第一个字符应该是字母"
			}else{
				for(var  i = 0; i < oValue.length;i++){
					if(isDEF(oValue[i])){
						oUsenamespan.innerHTML = "输入正确"
					}else{
						oUsenamespan.innerHTML = "包含非法字符"
					}
				}
			}
		}
	</script>
</head>	
<body>
	<div id="div">
		<input type="text" id="usename" onblur="func();"/>
		<span id="usename_span">6~18位字符，可使用字母、数字、下划线组成，需要以字母开头</span>
	</div>
</body>
```

## 认识对象

### 一、历史

编程语言：汇编、C语言 		——面向过程

编程语言：Java、C++、JavaScript、object-C、python			——面向对象

### 二、思想

面向过程编程思想：只考虑数学逻辑

面向对象编程思想：直接将生活逻辑映射到我们的程序

​		<1>分析有哪些实体

​		<2>设计实体属性和功能

​		<3>实体之间相互作用

### 三、语法

类：一类具有相同特征事物的抽象概念，比如人类

对象：具体某一个个体，唯一的实例

|   类 |      对象      |
| ---: | :------------: |
|   狗 | 你遇到的那一只 |
| 电脑 | 你桌上的那一台 |

### 四、代码

1、通过new运算符声明对象

2、那个省略new

3、对象常量赋值（使用大括号，代表对象）

| 对象属性 | 普通变量 |
| :------: | :------: |
| 对象方法 | 普通函数 |
|   婚后   |   婚前   |

```javascript
var obj1 = new Object();
var obj2 = Object();
var obj3 = {};
//新增属性，使用起来和普通变量没有任何区别
obj3.usename = '钢铁侠';
obj3.age = 18;
alert(obj3.usename);
alert(obj3.age);
```

#### 新增方法

使用起来和普通的变量没有任何区别

```javascript
<script>
    var obj3 = {};
    obj3.usename = '钢铁侠';
//[]填写的必须是字符串
	obj3['age'] = 18;
    obj3.show  = function(){
	alert("我的名字叫" + obj3.usename + "，今年" + obj3.age + "岁");
}
obj3.show();//和obj3['show']()是等价的
</script>
```

```javascript
<script>
	var obj3 = {
     usename: "钢铁侠",
     "age":18,
     show:function(){
		alert("我的名字叫" + obj3.usename + "，今年" + obj3.age + "岁")
    };
        alert(obj3.usename);
		alert(obj3['age']);
		obj3.show();
</script>
```

### 关键字 delete

删除对象的属性或者方法

```javascript
<script>
	var obj3 = {
     usename: "钢铁侠",
     "age":18,
     show:function(){
		alert("我的名字叫" + obj3.usename + "，今年" + obj3.age + "岁")
    };
        alert(obj3.usename);
		alert(obj3['age']);
		obj3.show();
		delete obj3.usename;
		delete obj3.show;
		alert(obj3.usename);
		obj3.show();
</script>
```

### 五、数据结构

基本数据类型（存储一个值）=> 数组（处理批量的数据）=> 对象（既可以存储数据又可以存储函数）

### 例子

```javascript
<script>
	var car = {
		speed:60,
		run:function(rode){
			return rode.length / car.speed;
		}
	};
	var kuahaidaqiao = {
		length:1000
	};
	var hours = car.run(kuahaidaqiao);
	alert("一共跑了" + hours.toFixed(2) + "个小时");
</script>
```

node.toFixed(n)

保留小数点后n位数

## Math对象

在JS中一切皆对象。

在JS中，很多关系数学运算的函数，直接一个Math对象提供

| 函数                 | 功能                               |
| -------------------- | ---------------------------------- |
| Math.random()        | 返回0-1之间的随机数                |
| Math.max(num1, num2) | 返回较大的数                       |
| Math.min(num1, num2) | 返回较小的数                       |
| Math.abs(num)        | 绝对值                             |
| Math.round()         | 四舍五入(成整数，只看小数点后一位) |
| Math.ceil(19.3)      | 向上取整                           |
| Math.floor(11.8)     | 向下取整                           |
| Math.pow(x, y)       | x的y次方                           |
| Math.sqrt(num)       | 开平方                             |
| Math.sin()           | 正弦（传入的参数是：弧度           |
| Math.cos()           | 余弦（传入的参数是：弧度           |
| Math.PI = 180弧度    | 1弧度 = Math.PI / 180              |

例如，求30°的正弦值

`Math.sin(30 * Math.PI / 180)`

## 日期对象

### 日期对象声明

1、没有传入参数，默认当前系统时间

`var d = new Date();`

2、传入参数

"2000-01-01"`var d = new Date("2000-01-01");`

"2000/01/01"`var d = new Date("2000/01/01");`

按照顺序，分别传入参数 年/月/日/时/分/秒/毫秒

`var d = new Date(2000, 0, 1, 8, 30, 50);`

显示为2000-01-01 8:30:50

注意：因为在国外的月份是0~11，分别对应国内1~12

直接传入毫秒数

`var d = new Date(1000);`

以1970年1月1日 0:0:0 为参照点去计算

所以显示1970-01-01 08:00:01

### 日期对象方法

| 方法                     | 功能                               | 显示                               |
| ------------------------ | ---------------------------------- | ---------------------------------- |
| box.toDateString()       | 以特定的格式显示星期几、月、日和年 | Fri Apr 日 年                      |
| box.toTimeString()       | 以特定的格式显示时、分、秒和时区   | xx:xx:xx GMT +0800（中国标准时间） |
| box.toLocaleDateString() | 以特定的格式显示星期几、月、日和年 | 年/月/日                           |
| box.toLocaleTimeString() | 以特定的格式显示时、分、秒和时区   | 12小时制（xx：xx：xx）             |
| box.toUTCString()        | 以特定的格式显示完整的UTC日期      | Fri，日 Apr 年 xx：xx：xx GMT      |

 组合拳：

```javascript
box.toLocaleDateString() + " " + box.toLocaleTimeString()
```

#### 重点记忆

set/get既能获取又能够赋值， get只能获取

#### Data.parse()

格式：Date.parse(日期对象)

功能：可以将日期对象转成毫秒数	

#### d.getTime()/d.setTime()

格式：日期对象.getTime/setTime

功能：将当前日期对象转成毫秒数

## 定时器

格式：`var timer = setInterval(函数， 毫秒数);`

功能：每隔对应的毫秒数，执行一次传入的函数

返回值：启动定时器时，系统分配的编号

`clearInterval(timer);`取消定时器

```javascript
var i = 0;
fucntion show(){
	document.write(i++ + "</br>");
}
var timer = setInterval(show, 1000);
//每一秒执行一次show函数
```

另一种写法

```javascript
var show = function
```

所以可以

```javascript
var i = 0;
var show  = fucntion(){
	document.write(i++ + "</br>");
}
var timer = setInterval(var show  = fucntion(){
	document.write(i++ + "</br>");
}, 1000);
```

一般情况下，没有名字的函数叫做匿名函数

```javascript
var timer = setInterval(匿名函数，毫秒数);
var timer = setInterval(function(){
	执行代码;
}, 毫秒数);
```

### 实时显示当前时间

```javascript
<head>
	<meta charset="utf-8">
	<title></title>
	<script src="js/demo.js">
	</script>
	<style type="text/css">
		#div1{
			width: 600px;
			height: 100px;	
            text-align: center;
			line-height: 100px;
			border: #000000 solid 1px;
			margin: 100px auto;
			font-size: 30px;
		}
	</style>
	<script type="text/javascript">
		setInterval(function(){
		var oDiv = document.getElementById("div1");
		oDiv.innerHTML = showTime();
	}, 1000)x
</script>
</head>
<body>
	<div id="div1">
		显示当前时间
	</div>
</body>
```

### 秒表

```javascript
<head>
	<meta charset="utf-8">
	<title></title>
	<script src="js/demo.js">
	</script>
	<style type="text/css">
		#div1{
			width: 100px;
			height: 200px;
			padding: 50px;
			background-color: orange;
			border: 1px black solid;
			margin: 0px auto;
			text-align: center;
		}
		#div span{
			font-size: 22px;
		}
		#div1 button{
			width: 100px;
			height: 30px;
			font-size: 16px;
			margin-top: 20px;
			background-color: black;
			color: white;
		}
	</style>
	<!-- <script type="text/javascript">
		setInterval(function(){
			var oDiv = document.getElementById("div1");
			oDiv.innerHTML = showTime();
		}, 1000)//实时显示时间
	</script> -->
	<script type="text/javascript">
		function $(id){
			return document.getElementById(id);
		}
		var i = 0;
		var timer = null;
		window.onload = function(){
			//获取按钮
			$("start").onclick = function(){
				timer = setInterval(function(){
					i++;
					$("sec").innerHTML = doubleNum(i % 60);
					$("min").innerHTML = doubleNum(parseInt(i / 60) % 60);
					$("hour").innerHTML = doubleNum(parseInt(i / 3600));
				}, 1000)
			}
			$("pause").onclick = function(){
				clearInterval(timer);
			}
			$("reset").onclick  = function(){
				clearInterval(timer);
				i = 0;
				$("hour").innerHTML = "00";
				$("min").innerHTML = "00";
				$("sec").innerHTML = "00";
			}
		}
	</script>
<body>
	<div id="div1">
		<span id="hour">00</span>
		<span>:</span>
		<span id="min">00</span>
		<span>:</span>
		<span id="sec">00</span>
		<br/>
		<button id = "start">开始</button>
		<button id = "pause">暂停</button>
		<button id = "reset">复位</button>
	</div>
</body>	
```

#### 合并开始和暂停

```javascript
<script type="text/javascript">
	function $(id){
		return document.getElementById(id);
	}
	var i = 0;
	var timer = null;
	var isRunning = false;
	window.onload = function(){
		//获取按钮
		function startFunc(){
			timer = setInterval(function(){
				i++;
				$("sec").innerHTML = doubleNum(i % 60);
				$("min").innerHTML = doubleNum(parseInt(i / 60) % 60);
				$("hour").innerHTML = doubleNum(parseInt(i / 3600));
			}, 1000)
		}
		function pauseFunc(){
			clearInterval(timer);
		}
		$("btn1").onclick = function(){
			if(!isRunning){
				$("btn1").innerHTML = "暂停";
				startFunc();
				isRunning = true;
			}else{
				$("btn1").innerHTML = "开始";
				pauseFunc();
				isRunning = false;
			}
		}
		$("reset").onclick  = function(){
			clearInterval(timer);
			i = 0;
			isRunning = false;
			$("btn1").innerHTML = "开始";
			$("hour").innerHTML = "00";
			$("min").innerHTML = "00";
			$("sec").innerHTML = "00";
		}
	}
</script>
```



## window.onload = function()

```javascript
window.onload = function(){
	写在这里代码，是整个页面加载完成以后运行的(固定格式)
}
```

## 通过id获取标签的方法进行简化

```javascript
function $(id){
	return document.getElementById(id);
}
var oStart = document.getElementById("start");
//和
var oStart = $("start");
//效果是一样的
```

```javascript
$("start").onclick = function(){
	alert("开始");
}
<button id = 'start'>开始</button>
//和
function btnClick(){
	alert("暂停");
}
<button id = 'pause' onclick = 'btnClick();'>暂停</button>
//效果一样
```

## 认识BOM

BOM：browse object model

浏览器对象【模型】

提起BOM就得提起JavaScript的构成，ECMAScript为JavaScript的核心，但是要在浏览器中使用JavaScript，那么JavaScript需要听从BOM的调遣

![image-20210420193514888](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210420193514888.png)

### 系统对话框

浏览器可以通过alert()、confirm()和prompt()方法可以调用系统对话框向用户显示信息

window方法，一般情况下window可以省略

#### alert() 弹出警告框

#### confirm() 弹出一个带确定和取消的提示框

返回值：

​				确定，返回true；

​				取消，返回false

#### prompt() 弹出一个带输入框的提示框

参数：

​			第一个参数：面板上显示的内容

​			第二个参数：输入框里的默认值（可以不填）

返回值：

​				确定，返回输入框中的内容

​				取消，返回null

### open方法

`open()`

​	第一个参数：跳转的url，打开一个新窗口，加载url

​	第二个参数：字符串，给打开的窗口起一个名字

​	第三个参数：一串特殊含义的字符串，可以控制打开窗口的属性

| 代码                      | 功能                                                         | 默认值 |
| ------------------------- | ------------------------------------------------------------ | ------ |
| directories=yes\|no\|1\|0 | 是否添加目录按钮                                             | YES    |
| fullscreen=yes\|no\|1\|0  | 是否使用全屏模式显示浏览器，处于全屏模式的窗口必须处于剧院模式 | NO     |
| height=pixels             | 窗口文档显示区的高度，以像素计                               |        |
| left=pixels               | 窗口的x坐标，以像素计                                        |        |
| location=yes\|no\|1\|0    | 是否显示地址字段                                             | YES    |
| menubar=yes\|no\|1\|0     | 是否显示菜单栏                                               | YES    |
| resizable=yes\|no\|1\|0   | 窗口可否调节尺寸                                             | YES    |
| scrollbars=yes\|no\|1\|0  | 是否显示滚动条                                               | YES    |
| status=yes\|no\|1\|0      | 是否添加状态栏                                               | YES    |
| titlebar=yes\|no\|1\|0    | 是否显示标题栏                                               | YES    |
| toolbar=yes\|no\|1\|0     | 是否显示浏览器工具栏                                         | YES    |
| top=pixels                | 窗口的y坐标                                                  |        |
| widrh=pixels              | 窗口的文档显示区的宽度，以像素计                             |        |

```javascript
open('https://www.baidu.com','baidu','width=400, height=300px');
```

### history对象

掌管的是，当前窗口（不是浏览器）历史记录（只要加载的url不一样，就会产生历史记录）

属性：

​		`history.length` 输出当前窗口历史纪录的条数

方法

​		`history.back();`返回上一条历史记录

​		`history.forward();`前进到下一条历史记录

​		`history.go();`

参数：0，刷新当前页面

​			正整数，前进n条记录

​			负整数，后退n条记录

### location对象

url（不叫网址，也不叫域名）：统一资源定位符。

中文版本：**协议：//主机名:端口号/路径/?查询字符串#锚点**

英文版本：**protocol://hostname:port/pathname/?search#hash**

#### **location.protocol**

查看协议

​		file：本地磁盘文件访问

​		http：

​		https：（证书认证协议）

#### **location.hostname**

主机名 

IP（在全球范围内找到当前网络的地址）

域名 就是 IP 的 别称

#### location.port

端口号（默认是隐藏的）

是当前电脑中使用网络的软件，随机给他分配一个编号 （范围：0~65535）

hostname:port 可以直接定位到当前使用网络的进程

| 进程   | 默认端口号 |
| ------ | ---------- |
| 浏览器 | 8080       |
| http   | 80         |
| https  | 443        |

#### location.pathname

路径

#### location.search

查询字符串（前后端交互）

#### location.hash

锚点

location     obect

location.href    string

## location对象

location === document.location为true



#### location地址栏

属性：

方法：

`onclick = "location.assign('https://www.baidu.com')"`

##### location.assign(url)

在当前窗口跳转这个url，会产生历史记录

##### location.replace(url)

在当前窗口替换成新的url ，不会产生历史记录

##### location.reload()

刷新当前窗口

##### loacation.reload(true)

不经过浏览器缓存强制刷新

## 认识DOM

document object model（文档对象模型）

### 节点类型

`<div id = 'div1'>div文本</div>`

元素节点	<div></div>

属性节点	id = 'div1'

文本节点	div文本

### 元素节点的获取

`document.getElementById(id)`

功能：通过id获取符合条件的元素，id必须是唯一的

返回值：就是符合条件的一个节点

`node.getElementByTagName(标签名)`

功能：从node节点开始，通过标签名获取符合条件的元素节点

返回值：伪数组/类数组

`node.getElementByClassName(class名字)`

**注意：**IE8以下不兼容

功能：从node节点开始，通过class名字获取符合条件的元素节点

返回值：伪数组/类数组

`document.getElementByName(name属性的值)`

功能：通过name属性的值获取符合条件的元素节点

`document.querySelector()`

返回值：一个元素节点，找到符合条件的第一个元素节点

```javascript
//id = ol1
var node = document.querySelector("#ol1");
//tagName = 'li'
var node = document.querySelector('li');
//class = box
var node = document.querySelector(".box");
//ol下 class = box的节点
var node = document.querySelector("ol .box");
//name = 'hello'
var node = document.querySelector("[name=hello]");
```

`document.quertSelectorAll()`

返回值：是一个伪数组

**注意：**一般使用在表单元素

```javascript
<script type="text/javascript">
	onload = function(){
		var oLis = document.getElementById("ol1");
		var  aLis2 = oLis.getElementsByTagName("li");
		for(var i = 0;i < aLis2.length;i++){
			aLis2[i].style.backgroundColor = 'red';
		}
	}
</script>
//让id名为ol1的标签下所有li标签的背景色变为红色
```

### 元素节点的属性

表单用.value

innerHTML双标签

```javascript
<div id="div1" class="class1">
	div文本
	<em>em文本</em>
	<strong>strong文本</strong>
</div>
```

#### innerHTML

获取标签间内容，会解析标签

①把标签内所有内容，包括子标签显示

```javascript
alert(oDiv.innerHTML);
//div文本<em>em文本</em><strong>strong文本</strong>
```

②替换掉标签内所有内容，以h2显示

```javascript
oDiv.innerHTML = "<h2>hello world</h2>";//helloworld
//变成了↓
<div id="div1" class="class1">
	<h2>hello world</h2>//带标签
</div>
```

#### innerText

获取标签间纯文本，不会解析标签，设置纯文本

①显示标签内，包括子标签的**纯文本**内容。

```javascript
alert(oDiv.innerText);//div文本 em文本 strong文本
```

②替换掉标签内所有内容，以**纯文本**显示

```javascript
oDiv.innerText = "<h2>hello world</h2>";//<h2>hello world</h2>
//变成了↓
<div id="div1" class="class1">
	<h2>hello world</h2>//纯文本
</div>
```

#### outerHTML

从外标签开始到外标签结束

①包括外标签，全部输出

```javascript
alert(oDiv.outerHTML);
//<div id="div1" class="class1">div文本<em>em文本</em><strong>strong文本</strong></div>
```

②包括外标签，全部修改

```javascript
oDiv.outerHTML = "<h2>hello world</h2>"//hello world
//变成了↓
<h2>hello world</h2>
```

#### outerText

替换掉标签所有内容，包括外标签，以**纯文本**显示

```javascript
oDiv.outerHTML = "<h2>hello world</h2>"//hello world
//变成了↓
<h2>hello world</h2>
```

### 自定义ByClassName的方法

```javascript
function elementsByClassName(node, classStr){
//1、获取node这个节点下所有的子节点
var nodes = node.getElementsByTagName("*");
var arr = [];//存放符合条件的节点
for(var i = 0;i<nodes.length;i++){
	if(nodes[i].className === classStr){
			arr.push(node[i]);
		}
	}
	return arr;
}
```

### 获取当前有效样式

`oDiv.currentStyle['height'];`//IE兼容

`getComputedStyle(oDiv)['height'];`//谷歌，火狐兼容

#### 如何做到跨浏览器兼容？

```javascript
function getStyle(node, cssStyle){
	return node.currentStyle ? node.currentStyle[cssStyle] : getComputedStyle(node)[cssStyle];
}
```

### 改变字体颜色和大小的案例

**题目：**写一个定时器，每一秒修改一次div内文本颜色和文字大小，最开始这个文字是默认大小，大小开始增大，当增大6次以后，文字开始缩小，缩小6次，文字再开始增大

**①颜色随机**

`rgba(255, 255, 255, 0);`

`parseInt(Math.random() * 256);`

```javascript
function randomColor(){
	var str = "rgba(" + parseInt(Math.random() * 256) +"," + parseInt(Math.random() * 256) +"," + parseInt(Math.random() * 256) +", 1)";
	return str;
}
```

**②取出上一次的字体大小**

```javascript
iCur = parseInt(getStyle(oDiv, "font-size"));
```

**③变化字体大小，重新赋值回去**

```javascript
oDiv.style.fontSize = iCur + speed + 'px';
```

**④结果**

```javascript
<script type="text/javascript">
	onload = function(){
		var oDiv = document.getElementById("div1");
		var speed = 5;//每一次大小变化
		var count = 0;//计数
		setInterval(function(){
			oDiv.style.color = randomColor();
			//1、将字体上一次的字体大小取出来
			var iCur = parseInt(getStyle(oDiv, "font-size"));
			//2、变化字体大小，重新赋值回去
			oDiv.style.fontSize = iCur + speed + 'px';
			count++;
			if(count % 6 == 0){
				speed *= -1;
			}
		}, 500);
	}
</script>
```

**自己思考过程**

```javascript
<script type="text/javascript">
	onload = function(){
		var oDiv = document.getElementById("div1");
		var speed = 5;
		var i = 0;
		var isYes = true;
		setInterval(function(){
			oDiv.style.color = randomColor();
			
			//1、将字体上一次的字体大小取出来
			var iCur = parseInt(getStyle(oDiv, "font-size"));
			if(!isYes){
				oDiv.style.fontSize = iCur - speed + 'px';
				
				i--;
				if(i < -6 ){
					isYes = true;
					i = 0;
				}
			}else{
				oDiv.style.fontSize = iCur + speed + 'px';
				i++;
				if(i > 6){
					isYes = false;
					i = 0;
				}
			}
		}, 500);
	}
</script>
```

**总结：**用true和false判断，过于麻烦，可以直接使speed为负数，进行字体缩小

`speed *= -1;`

### Attribute方法

#### getAttribute

1、class的访问

```javascript
<.. class = "class1">
node.className;//显示class1
node.getAttribute("class");//显示class1
```

2、支持自定义属性

```javascript
<.. xxx = "xxx1">
//xxx为自定义
node.xxx;//显示undefined
node.getAttribute("xxx");/显示xxx1
```

#### setAttribute

修改属性

```javascript
oDiv.zzz = 'ooo';
alert(oDiv.zzz);//显示的是ooo，添加成功了，但是不在行内
oDiv.setAttribute('class', 'box5');//修改
oDiv.setAttribute('zzz', 'ooo');//在行内
```

#### removeAttribute

删除属性

```javascript
oDiv.className = '';//只是将class属性值赋值为空值
oDiv.removeAttribute("class");//移除class属性
```

### 属性节点的获取

```JavaScript
var oDiv = document.getElementById('div1');
<div id = 'div1' title = 'hello' class = 'box' style = '..'>
    div文本
</div>
```

**获取行间属性的值，只能访问标签行间的css样式**

`oDiv.id`

`oDiv.title`

**访问class，通过className访问**

`oDiv.className`

**设置行间属性的值**

直接赋值，oDiv.id =  'div2'

**获取css样式的值**

oDiv.style.width

oDiv.style.height

..

如果css样式带“-”，去掉”-“，第二个单词首字母大写

例如：oDiv.style.backgroundColor

### 文本节点的获取

通过元素节点的子节点获取

### 获取子节点

|          | nodeType | nodeName | nodeValue |
| :------: | :------: | :------: | :-------: |
| 元素节点 |    1     |  标签名  |   null    |
| 属性节点 |    2     |  属性名  |  属性值   |
| 文本节点 |    3     |  #text   | 文本内容  |

| 包含文本节点    | 只获取元素节点         |
| --------------- | :--------------------- |
| childNodes      | children               |
| firstChild      | firstElementChild      |
| lastChild       | lastElementChild       |
| nextSibling     | nextElementSibling     |
| previousSibling | previousElementSibling |

```javascript
<div id="div1" class="class1"><em>em文本</em>div文本<strong>strong文本</strong></div>
```

#### childNodes

访问当前节点下所有的子节点

```javascript
alert(oDiv.childNodes);
//[object NodeList]
```

```javascript
alert(oDiv.childNodes[0]);//[object HTMLElement]元素节点
alert(oDiv.childNodes[1]);//[object Text]文本节点
alert(oDiv.childNodes[2]);//[object HTMLElement]元素节点
//分别对应em元素节点，“div文本”文本节点，strong元素节点
```

```javascript
alert(oDiv.childNodes[0].nodeName);//EM
alert(oDiv.childNodes[1].noteValue);//div文本
alert(oDiv.childNodes[2].noteValue);//null
```

#### firstChild

访问子节点中的首位

```javascript
alert(oDiv.firstChild.nodeName);//#text
```

#### firstElementChild

访问子节点中元素节点首位

```javascript
alert(oDiv.firstElementChild.nodeName);//EM
```

#### lastChild

访问子节点中的最后一位

```javascript
alert(oDiv.lastChild.nodeName);//#text
```

#### lastElementChild

访问子节点中元素节点最后一位

```javascript
alert(oDiv.lastElementChild.nodeName);//STRONG
```

#### nextSibling

访问当前节点兄弟节点的下一节点

#### previousSibling

访问当前节点兄弟节点的上一节点

#### 小结

空格、回车、换行 看不见，但是，是字符

如果想将纯空白的文本节点剔除

```javascript
<div id="div1" class="class1">
	<em>em文本</em>
	div文本
	<strong>strong文本</strong>
	</div>
```

`childNodes.length`显示为5

`children.length`显示为2，因为只显示元素节点的数量

`firstElementChild.nodeName`显示为EM，第一个元素节点

`lastElementChild.nodeName`显示为STRONG，最后一个元素节点

`firstElementChild.nextSibling.nodeName`显示div文本，包含文本节点

`firstElementChild.nextElementSibling.nodeName`显示STRONG，仅元素节点

### 属性节点Attributes

```javascript
<div id = 'div1' title = 'hello' class = 'box'>
    div文本
</div>
```

attributes 获取当前元素节点上所有的属性节点

```javascript
alert(oDiv.attributes);
//[object NameNodeMap](属性节点集合对象(map代表集合))
```

集合：

①无序

②不重复

**获取其中的某一个属性节点**

```javascript
alet(oDiv.attributes.getNameItem("title"));//object Attr(属性节点)
alet(oDiv.attributes.getNameItem("title").nodeName);//title
alet(oDiv.attributes.getNameItem("title").nodeType);//2
alet(oDiv.attributes.getNameItem("title").nodeValue);//hello
```

过于繁琐因此简化成

```javascript
alet(oDiv.attributes.getNameItem("title"));//object Attr(属性节点)
alet(oDiv.attributes.["title"].nodeName);//title
alet(oDiv.attributes.["title"].nodeType);//2
alet(oDiv.attributes.["title"].nodeValue);//hello
```

### DOM节点操作 

`document.write()`

会覆盖页面上原有的内容

#### createElement()

功能：创建节点

格式：`document.createElement();`

参数：标签名

返回值：创建好的这个节点

#### appendChild()

功能：将新节点追加到子节点列表的末尾

格式：`node1.appendChild(node2);`

功能：将node2节点插入到node1节点子节点的末尾

#### createTextNode()

功能：创建文本节点

注意：纯文本，标签也是以文本形式显示

格式：`document.createTextNode(文本);`

#### insertBefore()

功能：将新节点插入在前面

格式：`box1.parentNode.insertBefore(box2, box1);`

**注意：**box1.parentNode为box1的父节点

功能：将box2插入到box1前面

#### replaceChild()

功能：将新节点替换旧节点

格式：`box1.parentNode.replaceChild(box2, box1);`

**注意：**box1.parentNode为box1的父节点

功能：用box2替换掉box1

#### cloneNode()

功能：复制节点

格式：`node.cloneNode();`

格式2：`node.cloneNode(true);`//复制节点本身和子节点

返回值：复制出来的新节点

#### removeChild()

功能：移除节点

格式：`box.parentNode.removeChild(box);`

通过box的父节点，移除box节点

功能：将box节点从页面上删除

### 节点操作案例

增、删、赋值实例

## 快速找到点击按钮的下标this

### this关键字

概念：只要封装函数，任何一个函数系统都会内置一个叫做this的变量

this变量存储的是地址，是当前函数主人的地址。

**注意：**this永远指向当前函数的主人。函数的主任要通过当前函数的上下文去判断

this类似于现实生活中，用到的“我”

```javascript
var person = {
	username:"钢铁侠",
	sex:"boy",
	show:function(){
		alert(person.username);
		alert(this.username);
	}
};
person.show();
//两次alert都显示钢铁侠
```

```javascript
function show(){
	alert(this);
}
show();
window.show();
//两次都显示object window
//全局函数，如果函数没有主人，默认指向window
```

```javascript
<head>
	<script>
    	window.onload = function(){
    var oBtn = document.getElementId("btn1");
    btn1.onclick = function(){
        alert(this);//[object HTMLButtonElement]
    }
}
    </script>
</head>
<body>
<button id = "btn1">按钮1</button>
<button>按钮2</button>
<button>按钮3</button>
</body>
```

```javascript
<script type="text/javascript">
	window,onload = function(){
	var  oBtns = document.getElementsByTagName("button");
	//希望点击按钮，输出当前按钮的下标
	for(var i = 0; i < oBtns.length;i++){
		oBtns[i].index = i;
		oBtns[i].onclick = function(){
		alert(this.index);
		}
	}
}
</script>
<body>
    <button>按钮1</button>
	<button>按钮2</button>
	<button>按钮3</button>
    </body>
```

## 选项卡案例

```javascript
<head>
	<meta charset="utf-8">
	<title></title>
	<style type="text/css">
		#div1 button{width: 100px; height: 30px; background-color: gray; color: white; font-size: 18px;}
		#div1 .active{background-color: orange; color: blue;}
		#div1 div{width: 340px; height: 300px; border: 1px solid black; display: none;}
	</style>
	<script type="text/javascript">
		window.onload = function(){
			var oDiv = document.getElementById('div1');
			var aBtns = oDiv.getElementsByTagName("button");
			var aDivs = oDiv.getElementsByTagName("div");
			for(var i = 0;i < aBtns.length;i++){
				aBtns[i].index = i;
				aBtns[i].onclick = function(){
					for(var j = 0; j < aBtns.length; j++){
						//取消所有按钮样式
						aBtns[j].className = '';
						//取消所有div样式
						aDivs[j].style.display = 'none';
					}
					aDivs[this.index].style.display = 'block';
					this.className = 'active';
				}
			}
		}
	</script>
</head>
<body>
	<div id="div1">
		<button class="active">html5</button>
		<button>javascript</button>
		<button>css</button>
		<!-- 行内使该div为可见 -->
		<div style="display: block;">
			hello
		</div>
		<div>
			world
		</div>
		<div>
			hello world
		</div>
	</div>
</body>
```

## offset系列方法

### offsetWidth

格式：`node.offsetWidth`

功能：计算width+boder * 2+padding * 2的宽，不包括margin

### offsetheight

与offsetWidth同理

### offsetLeft

眼睛能看到实际距离第一个有定位的父节点的距离

```javascript
<style type="text/css">
	*{padding: 0px; margin: 0px;}
	#div1{
    	margin: 30px; 
		width: 200px; 
		height: 200px; 
		background-color: orange;}
	#div2{
    	margin: 20px; 
		width: 100px; 
		height: 100px; 
		background-color: red;}
</style>
```

```JavaScript
getstyle(oDiv2, 'left');//auto
//因为没有设置left值
```

`oDiv2.offsetLeft`是到网页最左边的距离

如果在div1设置一个position：relative

则，`oDiv2.offsetLeft`是到div1左边的距离

### offsetTop	

与offsetTop同理

## 文档碎片

创建10w个节点，添加到页面上

```javascript
console.time("test1");
for(var i =0; i< 100000;i++){
    var newDiv = document.createElement("div");
    document.body.appendChild(newDiv);
}
console.timeEnd("test1");
//120毫秒
```

### 文档碎片操作

先创建好10w个节点，将10w节点插入到一个节点上，最后将这1个节点添加到页面上→效率更高

先创建一个节点，再创建十万个节点，把这十万个节点插入到这一个节点上（内存里运行，比在页面上插入代码效率搞得多）

```javascript
cosole.time("test1");
var node = documen.createElement("div");
for(var i =0; i< 100000;i++){
    var newDiv = document.createElement("div");
    node.appendChild(newDiv);
}
document.body.appendChild(node);
cosole.timeEnd("test1");
//78毫秒
```

## 数组和对象的遍历方法

### 数组遍历

**for循环**

```javascript
var arr = [10, 20, 30, 40, 50];
for(var i = 0; i< arr.length;i++){
	document.write("for," + i + "," + arr[i] + "<br>");
}
```

**for..in快速遍历**

```javascript
var arr = [10, 20, 30, 40, 50];
for(var i in arr){
	document.write("forin," + i + "," + arr[i] + "<br>");
}
```

**forEach**

```javascript
var arr = [10, 20, 30, 40, 50];
arr.forEach(function(iten, index, arr){
	document.write("forEach," + index + "," + iten + "<br>");
})
```

### 对象遍历

**注意：**对象只能通过for..in遍历

**for..in**

```javascript
var person = {
	username: "钢铁侠",
	age: 19,
	sex: "男"
	}
for(var i in person){
	document.write("对象遍历：" + i + person[i] + "<br>");
}
```

## 认识事件和事件类型

### 什么是事件

事件是发生并得到处理的操作，即：事情来了，然后处理

如：

电话铃响起（事件发生）→需要接电话（处理）

学生举手请教问题（有事了）→需要解答（处理）

按钮被点击了，然后对应一个函数来处理

### 事件绑定方式

1、内联模式

```javascript
function btnClick(){
    函数体
}
<button onclick = 'btnClick();'>内联模式</button>
```

2、外联模式/脚本模式

```javascript
window.onload = function(){
    var oBtn = document.getElementById("btn1");
    oBtn.onclick = function(){
        函数体
    }
}
<button id = 'btn1'>外联模式</button>
```

**绑定事件格式：**

元素节点.on + 事件类型 = 匿名函数

例如：oBtn.onclick = function()

click 事件类型

onclick 事件处理的函数

### 事件类型-鼠标事件

可以绑定在任意的元素节点上

|  事件类型   |         操作         |
| :---------: | :------------------: |
|    click    |         单击         |
|  dblclick   |         双击         |
|  mouseover  |       鼠标移入       |
|  mouseout   |       鼠标移出       |
|  mousedown  |       鼠标按下       |
|   mouseup   |       鼠标抬起       |
|  mousemove  | 鼠标移动（不断触发） |
| mouseenter  |       鼠标移入       |
| mouseleaver |       鼠标移出       |

mouse**over/out**和mouse**enter/leave**r区别

**注意：**经过子节点会重复触发

mouseover

mouseout

**注意：**经过子节点不会重复触发**（IE8以后才有）**

mouseenter

mouseleave

### 事件类型-键盘事件

（表单元素，全局window）

| 事件类型 |                  操作                   |
| :------: | :-------------------------------------: |
| keydown  | 键盘按下 （如果按下不松手，会一直触发） |
|  keyup   |                键盘抬起                 |
| keypress |        键盘按下（只支持字符键）         |

### 事件类型-HTML事件

#### ①window事件

| 事件类型 | 功能                                                         |
| -------- | ------------------------------------------------------------ |
| load     | 当页面加载完成以后会触发的事件                               |
| unload   | 当页面解构的时候触发（刷新页面，关闭当前页面） 只有IE浏览器兼容 |
| scroll   | 页面滚动                                                     |
| resize   | 窗口大小发生变化的时候触发                                   |

#### ②表单事件

| 事件类型 | 功能                             |
| -------- | -------------------------------- |
| blur     | 失去焦点                         |
| focus    | 获取焦点                         |
| select   | 在输入框内选中文本时触发         |
| change   | 对输入框文本进行修改，且失去焦点 |

**特殊：**

**注意：**必须添加在form元素上

| 事件类型 | 功能                             |
| -------- | -------------------------------- |
| submit   | 当我们点击submit上的按钮才能触发 |
| reset    | 当我们点击reset上的按钮才能触发  |

## 事件对象和事件对象属性

**事件绑定**：元素节点.on + 事件类型 = 匿名函数

**注意：**系统会在事件绑定一旦完成的时候，生成一个事件对象

**注意：**触发事件的时候，系统会自动去调用事件绑定的函数，将事件对象当作第一个参数传入

```javascript
<script>
function show(){
    //function show(ev)
	alert(arguments.length);
    //alert(ev);//[object MouseEvent]
    //这种通过形参拿事件对象的方式在IE8一下不兼容
    //IE8以下，window.event	
    //所以可以
    //var e = ev || window.event
	alert(arguments[0]);//[object MouseEvent],鼠标事件对象
	alert("hello world");
}
window.onload = function(){
	var oBtn = document.getElementById('btn1');
	oBtn.onclick = show;
}
</script>
<button id = 'btn1'>按钮</button>
```

**固定写法，事件获取方式：**

```javascript
window.onload = function(){
	var oBtn = document.getElementById('btn1');
	oBtn.onclick = function(?){
		var e = ? || window.event;
		alert(e);
	}
}
```

### 鼠标事件对象属性

#### button

| 值   | 说明                                 |
| ---- | ------------------------------------ |
| 0    | 表示主鼠标按键（常规一般市鼠标左键） |
| 1    | 表示中间的鼠标按钮（鼠标滚轮按钮）   |
| 2    | 表示次鼠标按钮（常规一般市鼠标右键） |

```javascript
<script>
onload = function(){
	document.onmousedown = function(ev){
		var e = ev || window.event;
		alert(e.button)//左0，中1，右2
	}
}
</script>
```

### 获取鼠标当前位置

（原点位置不一样）

#### clientX clientY

原点：可视窗口的左上角为原点

#### pageX pageY

原点：整个页面的左上角（包含滚出去的滚动距离）

#### screenX screenY

原点：电脑屏幕的左上角

### 跟随鼠标提示框案例

#### 需要用到的事件类型

mouseover：让提示框显示

mouseout：让提示框隐藏

mousemove：让提示框跟随鼠标移动，通过clientX clientY，知道鼠标位置 

```css
<style type="text/css">
	a{
		display: block;font-size: 40px; margin: 100px; width: 130px;
	}
	#msg{
		width: 600px; height: 150px;  color: black;font-size: 40px; display: none; position: absolute;
	}
</style>
```

```javascript
<script>
	var arr  = [111,222,333,444];
	window.onload = function(){
		var aAs = document.getElementsByTagName('a');
		var oMsg = document.getElementById('msg');
		for(var i = 0; i < aAs.length; i++){
			aAs[i].index = i;
			//鼠标移入显示
			aAs[i].onmouseover = function(){
				oMsg.style.display = 'block'
				oMsg.innerHTML = arr[this.index];
			}
			//鼠标移出隐藏
			aAs[i].onmouseout = function(){
				oMsg.style.display = 'none'
			}
			aAs[i].onmousemove = function(ev){
				var e = ev || window.event;
				oMsg.style.left = e.clientX + 20 +  'px';
				oMsg.style.top = e.clientY + 20 +'px'
			}
		}
	}
</script>
```

```html
<body>
	<a href="#">1111111</a>
	<a href="#">2222222</a>
	<a href="#">3333333</a>
	<a href="#">4444444</a>
	<div id="msg"></div>
</body>
```

### 鼠标事件对象属性-2

#### shiftKey

按下shift键，为true，默认为false

#### altKey

#### ctrlKey

#### metaKey

windows系统，按下windows（开始）键，为true

macOS系统，按下command键，为true

**注意：**和别的操作进行组合，形成一些快捷键操作

### 键盘事件对象

#### 键码

**keyCode**

**which**

为了浏览器兼容

格式：`var which = e.which || e.keyCode;`

**注意：**只在keydown下支持。

```javascript
onload = function(){
    document.onkeydown = function(ev){
        var e = ev || window.event;
        var which = e.which || e.keyCode;
        alert(which);
    }
}
```

返回值：键码返回的是**大写字母**的ASCII码值，不区分大小写

#### 字符码

**charCode**

**which**

格式：var which = e.which || e.charCode

**注意：**只在keypress下支持，只支持字符键，不支持功能键

```javascript
onload = function(){
    document.onkeydown = function(ev){
        var e = ev || window.event;
        var which = e.which || e.charCode;
        alert(which);
    }
}
```

返回值：字符码区分大小写，返回当前按下键对应字符的ASCII码值

## 回车+CTRL组合按下按钮

```javascript
document.onkeydown = function(ev){
	var e = ev || window.event;
	var which = e.which || e.charCode;
	if(e.ctrlKey &&  which == 13){
		aBtns[0].onclick();
	}
}
```

## 事件冒泡和目标对象

### target

目标对象/触发对象	事件对象的属性	

IE8以下不兼容

IE8以下兼容`window.event.srcElement;`代表触发对象

```javascript
window.onload = function(){
    var oLi = document.getElementById('li1');
    oLi.onclick = function(ev){
	var e = ev || window.event;
	var target = e.target || window.event.srcElement;
    alert(target.innerHTML);//显示111
	}
}
<body>
    <li>111</li>
	<li>122</li>
	<li>133</li>
    </body>
```

```javascript
window.onload = function(){
    var oUl = document.getElementById('ul1');
    oUl.onclick = function(ev){
	var e = ev || window.event;
	var target = e.target || window.event.srcElement;
    alert(this.tagName);//UL，因为this指当前主人
    alert(target.innerHTML);//我所点的li的内容，taget是我所触发的对象。
    //就好比，我隔着纸打你，打的是纸，疼的是你
	}
}
<body>
<ul id='ul1'>
    <li>111</li>
	<li>122</li>
	<li>133</li>
	</ul>
    </body>
```

### 事件冒泡

浏览器上事件天生的一个特点：事件流

**事件冒泡：由里向外逐级触发**

```javascript
<style type="text/css">
	div{padding: 50px;}
	#div1{background-color: red;}
	#div2{background-color: blue;}
	#div3{background-color: orange;}
</style>
<script type="text/javascript">
	onload = function(){
		var aDivs = document.getElementsByTagName('div');
		for(var i = 0;i < aDivs.length ; i++){
			aDivs[i].onclick = function(){
				alert(this.id)
            //如果点到div3，会依次显示div3，div2，div1
            //如果点到div2，会依次显示div2，div1
            //如果点到div1，显示div1
			}
		}
	}
</script>
<body>
	<div id="div1">
		<div id="div2">
			<div id="div3">		
			</div>
		</div>
	</div>
</body>
```

**事件捕获：由外向里逐级触发**

**阻止事件冒泡：**浏览器兼容问题

事件对象的属性和方法：

方法1：

`cancelBubble = true;`

```javascript
<script type="text/javascript">
	onload = function(){
		var aDivs = document.getElementsByTagName('div');
		for(var i = 0;i < aDivs.length ; i++){
			aDivs[i].onclick = function(ev){
                var e = ev || window.event;
				alert(this.id)
                e.cancelBubble = true;
                //alert仅显示一个div.id
			}
		}
	}
</script>
```

方法2：

`stopPropagation();`

与cancelBubble同理

因为存在跨浏览器兼容问题，所以要封装跨浏览器兼容的阻止事件冒泡

```javascript
function stopBubble(e){
	if(e.stopPropagation){
		e.stopPropagation();
	}else{
		e.cancelBubble = true;
	}
}
```

## 事件练习

10个20px的鼠标路径

```css
<style type="text/css">
	*{margin: 0px; padding: 0px;}
	div{width: 20px; height: 20px; position: absolute; background: red;}
</style>
```

```javascript
<script type="text/javascript">
	onload = function(){
		var aDivs = document.getElementsByTagName('div');
		document.onmousemove = function(ev){
			var e = ev || window.event;
			for(var i = aDivs.length - 1; i > 0;i--){
				aDivs[i].style.left = aDivs[i - 1].offsetLeft + 'px';
				aDivs[i].style.top = aDivs[i - 1].offsetTop + 'px';
			}
			aDivs[0].style.left = e.clientX + 'px';
			aDivs[0].style.top = e.clientY + 'px'
		}
	}
</script>
```

```html
10个div
```



## 阻止默认行为

### 阻止网页右键菜单

官方的右键菜单

```javascript
window.onload = funtion(){
    document.oncontextmenu = function(){
        return false;
    }
}
```

### 阻止链接默认行为

**1、简陋的阻止a链接默认行为的方式（return false）**

​	缺点：运行到了return，后续内容就执行不到

```javascript
window.onload = function(){
	var a1 = document.getElementById('a1');
	a1.onclick = function(){
		return false;
	}
}
```

```javascript
window.onload = function(){
	var a1 = document.getElementById('a1');
	a1.onclick = function(){
	return confirm("are you  sure");
	}
}
```

//确定要离开当前网页吗

//confirm('确定吗？');

**2、规范的写法**

`preventDfault();`//w3c，阻止默认行为，放哪里都可以

`window.event.returnValue = false;`//IE，阻止默认行为

为了浏览器兼容

编写一个跨浏览器阻止超链接默认行为的函数

```javascript
function preDef(e){
	if(e.preventDefault){
        e.preventDefault();
    }else{
        window.event.returnValue = false;
    }
}
```

```javascript
window.onload = function(){
	var a1 = document.getElementById('a1');
	a1.onclick = function(ev){
		var e = ev || window.event;
		preDef(e);
	}
}
```

## 拖拽

拖拽三剑客：	伪代码（代码草稿）

**mousedown**

记录鼠标按下位置和拖拽物体相对距离

```javascript
var offsetX = e.clientX - node.offsetLeft;
var offsetY = e.clientY - node.offsetTop;
```

**mousemove**

```javascript
node.style.left = e.clientX - offsetX + 'px';
node.style.top = e.clientY - offsetY + 'px';
```

**mouseup**

取消拖拽

```javascript
<script>
	window.onload = function(){
		var oDiv = document.getElementById('div1');
		oDiv.onmousedown = function(ev){
			var e = ev || window.event;
			//计算鼠标到div框的距离为 鼠标距离减去div框到屏幕边缘的距离
			var offsetX = e.clientX - oDiv.offsetLeft;
			var offsetY = e.clientY - oDiv.offsetTop;
			
			document.onmousemove = function(ev){
				var e = ev || window.event;
				//使div框到边缘的距离为 鼠标到边缘的距离减去鼠标到div框的距离
				oDiv.style.left = e.clientX - offsetX + 'px';
				oDiv.style.top = e.clientY - offsetY + 'px';
			}
	
		}
		oDiv.onmouseup = function(){
            //取消mousemove
			document.onmousemove = null;
		}
	}
</script>
```

拖拽函数封装

```javascript
function drag(node){
	node.onmousedown = function(ev){
	var e = ev || window.event;
	//计算鼠标到div框的距离为 鼠标距离减去div框到屏幕边缘的距离
	var offsetX = e.clientX - node.offsetLeft;
	var offsetY = e.clientY - node.offsetTop;
	
	document.onmousemove = function(ev){
		var e = ev || window.event;
		//使div框到边缘的距离为 鼠标到边缘的距离减去鼠标到div框的距离
		node.style.left = e.clientX - offsetX + 'px';
		node.style.top = e.clientY - offsetY + 'px';
	}
	
}
	node.onmouseup = function(){
    //取消mousemove
	document.onmousemove = null;
	}
}
```

### 输出当前窗口的宽高

```javascript
var windowWidth = document.documentElement.clientWidth || document.body.clientWith;
```

```javascript
var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
```

## 事件委托

委托：

A委托B去买饭

A发布任务 委托方

B执行任务 代理方

### 事件委托实现步骤

1、找到当前节点的父节点或者祖先节点

2、将事件添加到你找到的这个父节点或者祖先节点上

3、找到触发对象，判断触发对象是否是想要的触发对象，如果是，进行后续的操作

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		
		<script src="js/demo.js"></script>
		<script>
			onload = function(){
				var oUl = document.getElementById('ul1');
				var aLis = document.getElementsByTagName('li');
				for(var i = 0;i < aLis.length;i++){
					aLis[i].onclick = function(){
						this.style.backgroundColor = 'red';
					}
				}
				var oBtn = document.getElementById('btn1')
				var i = 6
				oBtn.onclick = function(){
					var newNode = document.createElement("li");
					newNode.innerHTML = i++*111;
					oUl.appendChild(newNode)
				}
				//li委托ul让li变成红色
				oUl.onclick = function(ev){
					var e = ev || window.event;
					var target = e.target || window.even.srcElement;
					if(target.nodeName.toLowerCase() == 'li'){
						target.style.backgroundColor = 'red'
					}
				}
			}
		</script>
	</head>
	<body>
		<ul id='ul1'>
			<button id='btn1'>新增节点</button>
			<li>111</li>
			<li>222</li>
			<li>333</li>
			<li>444</li>
			<li>555</li>
		</ul>
	</body>
</html>
```

## 事件监听器

### **1、传统事件绑定**

<1>重复添加，覆盖。			如果创建两个点击事件，第一个点击事件就会被第二个点击事件覆盖

<2>不能精确的删除事件上的某一个函数

### **2、事件监听器**（事件绑定的另外一种方式）

#### **addEventListener()**

用于向指定元素添加时间句柄，IE8及以下不支持，火狐和谷歌支持。

格式：`node.addEventListener()`

参数：

**第一个参数	事件类型**

**第二个参数	绑定函数**

**第三个参数	布尔值**

true	事件捕获

false 事件冒泡（默认）

#### **removeEventListener()**

格式：`node.removeEventListener()`

参数：

**第一个参数	事件类型**

**第二个参数	移除函数**

**第三个参数	布尔值**

true	事件捕获

false 事件冒泡（默认）

### 事件监听器的兼容

#### IE事件处理函数

##### attachEvent()



##### detachEvent()

## 动态生成表格

```javascript
<head>
		<meta charset="utf-8">
		<title></title>
		<style>
			#t1 tr td{
				width: 50px;
				height: 30px;
			}
			.box0{
				background-color: cornflowerblue;
			}
			.box1{
				background-color: darkseagreen;
			}
		</style>
		<script>
			onload = function(){
				var oRow = document.getElementById('row');
				var oCol = document.getElementById('col');
				var oBtn = document.getElementById('btn1');
				var oT1 = document.getElementById('t1');
				//给表格上的删除键添加事件委托
				oT1.onclick = function(ev){
					var e = ev || window.event;
					var target = e.target || window.event.srcElement;
					if(target.nodeName.toLowerCase() == 'button'){
						oT1.removeChild(target.parentNode.parentNode)
					}
				}
				oBtn.onclick = function(){
					if(!oRow.value || !oCol.value){
						alert('请输入对应的行和列')
					}else{
						//行
						for(var i = 0;i < oRow.value;i++){
							var oTr = document.createElement('tr')
							oTr.className = 'box' + i%2;
							for(var j = 0;j < oCol.value;j++){
								var oTd = document.createElement('td')
								oTr.appendChild(oTd)
							}
							var oClose = document.createElement('td');
							oClose.innerHTML = "<button>删除</button>";
							oTr.appendChild(oClose)
							oT1.appendChild(oTr)
						}
					}
				}
			}
		</script>
		
	</head>
	<body>
		<input placeholder="行" id='row'>
		<input placeholder="列" id='col'>
		<button id='btn1'>生成</button>
		<table id='t1' border="1px">
			
		</table>
	</body>
```

## 放大镜

## 正则表达式

### 声明正则表达式

super string

正则表达式是一个描述字符模式的对象，ECMAScript的RegExp 类 表示正则表达式，而string和正则表达式都定义了进行强大的【模式匹配】和【文本检索】与【替换】的函数

**1、通过new去声明正则表达式**

第一个参数：正则表达式的主体 字符串

第二个参数：修饰符

i	忽略大小写

g	全局匹配

```javascript
var box1 = new RegExp('hello', 'ig')
```

【注意】修饰符没有顺序

**2、省略new去声明正则表达式**

**3、通过常量去赋值**

```javascript
var box1 = /hello/gi;
```

### 正则表达式方法

**正则表达式对象只有两个方法**

#### test 

格式：`正则.test(字符串);`

功能：在字符串中匹配这个正则是否存在

返回值：如果匹配成功返回true，匹配失败返回false

```javascript
var str = 'how are you';
var box = /are/;
alert(box.test(str));//返回true
```

#### exec

格式：`正则.exec(字符串);`

功能：在字符串中匹配这个正则是否存在

返回值：返回匹配到的串，

匹配成功，返回一个装有字符串的数组

匹配失败，返回null

### 字符串中可以使用正则的方法

**字符串的方法**

#### match

格式：`字符串.match(正则);`

功能：在字符串中匹配这个正则是否存在

返回值：

匹配成功，返回装有匹配到子串的数组

匹配失败，返回null

```javascript
var box = /are/ig;
var str = 'how aRe Are ARe you';
alert(str.match(box));
//如果box = /are/i; 输出are
//如果box = /are/ig; 输出aRe,Are,ARe
//如果box = /are/ig; 输出null
```

#### replace

格式：字符串.replace(oldStr, newStr);

功能：用newStr将oldStr替换

返回值：替换成功的新字符串

```javascript
var box1 = 'how are Are ARe you';
var box2 = box1.replace(/are/ig, '*');
alert(box2)//输出how * * * you
```

#### search

格式：字符串.split(分割符/正则);

功能：用分割符将原字符串进行分割

返回值：分割剩下的子串组成的数据

#### split

格式：字符串.search(子串/正则);

功能：找到符合条件的子串第一次出现的位置

返回值：

如果找到，返回>=0的下标

否则，返回-1

### 元字符

元字符：在正则表达式中有特殊含义的字符

有特殊的功能，可以控制匹配模式的方式

#### 字符类：单个字符和数字

**.				匹配单个的任意字符**

```javascript
var str = 'g_ogle';
var box = /g.ogle/;
alert(box.test(str));//输出true，不论str中的_换成任意单一字符，都可以返回true
```

**[范围]				匹配单个范围内的字符**

**[0-9]	匹配单个数字**

**[a-zA-Z0-9]	匹配单个数字字母下划线**

**\w	匹配单个数字字母下划线**

**\W	匹配单个非数字字母下划线**

**\d	匹配单个数字**

**\D	匹配单个非数字**

**[^范围] 	匹配任意一个除括号范围内的字符**

**[^0-9]	匹配任意一个非数字字符**

```javascript
var str = 'g_ogle';
var box = /g[0-9]ogle/;
alert(box.test(str));//输出false，因为str中，对应位置是_，不是0-9中的任意一个数字，如果str是g1ogle，输出true
```

#### 字符类：重复字符

**x	（任意的单个字符）**

**x?	匹配0个或1个x**

```javascript
var str = 'google';
var box = /goog?le/;
alert(box.test(str));//返回true，如果str为goole，也返回true，如果str为gooxle，返回false
```

**x+	匹配至少一个x字符**

```javascript
var str = 'google';
var box = /goog\d+le/;//至少一个数字字符
alert(box.test(str));//返回false，如果str为goog1le，返回true
```

**x*	匹配任意个x字符**

**x{m, n}	匹配最少m个，最多n个x字符，包括n**

**x{n}	必须匹配n个x字符**

**(xyz)+	小括号括起来的部分是当作单个字符处理**

#### 字符类：空白字符

![image-20210514210707871](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210514210707871.png)

\s	匹配任意单个的空白字符、空格、制表符和换行符

\S	匹配任意单个非空白字符

#### 字符类：锚字符

^：行首匹配，必须以这个正则开头

$：行尾匹配，必须以这个正则结尾

比如要判断一个网址是不是中国的网址(.com .cn)，就可以使用行尾匹配

或者要求一个用户名开头不能为数字的，，可以适用行首匹配

## 正则练习

替代字符	|

```javascript
var str = 'google';
var box = /google|baidu|bing/;
alert(box.test(str));//只要str是三种之一，都是true
```

 修饰符

i	忽略大小写

g	全局匹配

m	换行匹配

【注意】如果在字符串，遇到换行，重新开始计算行首

```javascript
var box = /\d/mg;
var str = '1、google\n2、baidu\n3、bing';
alert(str.replace(box,'*'));
```

### 常用正则

#### 检查邮政编码

共六位，第一位不能是0

```javascript
var pattern = /[1-9][0-9]{5}/;
var str = '224000';
alert(pattern.test(str));
```

#### 检查文件压缩包

```javascript
\d\w_表示所有数字字母下划线
\.表示匹配，后面是一个选择
检查文件是否是压缩包 zip rar gz
转义字符：
\.	代表本来.字符的意思
\* 代表本来*字符的意思
var box = /^\w+\.(zip|rar|gz)$/;
```

####  验证是否是正确的手机号码

```javascript
var box = /^1\d{10}$/;
```

#### 验证是否是正确的身份证号码

```javascript
var box = /^[1-9]\d{16}(\d|x)/;
```

#### 验证是否是纯中文的字符串

```javascript
var box = /^[\u4e00-\u9fa5]+$/;
```

![image-20210515185839552](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210515185839552.png)

## 表单验证和密码强度判断

```javascript
<script type="text/javascript">
	window.onload = function(){
		var oUser = document.getElementById('usename');
		var oU_span = document.getElementById('usename_span')
		//添加失去焦点事件
		oUser.onblur = function(){
			var oValue = oUser.value;
			//1、判断用户名长度是否符合要求
			if(oValue.length < 6 || oValue.length > 18){
				oU_span.innerHTML = '！长度应该是6-18'
				oU_span.style.color = 'red'
			}else if(!/[a-zA-Z]/.test(oValue[0])){
				oU_span.innerHTML = '！邮箱地址必须以英文字母开头'
				oU_span.style.color = 'red'
			}else if(/\W/.test(oValue)){
				oU_span.innerHTML = '！邮箱地址需由数字、字母、下划线组成'
				oU_span.style.color = 'red'
			}else{
				oU_span.innerHTML = '√该用户名可注册'
				oU_span.style.color = 'green'
			}
		}
	}
</script>
```

## 本地存储技术

### localStorage

**（IE8以下不兼容）**

![image-20210516195317739](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210516195317739.png)

1、永久存储

2、最大可以存储5M	客户端的一个微型数据库

3、只能存储string

### cookie

1、可以设置过期时间

2、最大可以存4KB

3、每一个域名下面最多可以存储50条数据	

### sessionStorage

**（结合后台使用）**

关闭窗口就没了！

### localStorage	对象

#### setItem(name, value);

#### getItem(name);

```javascript
if(!window.localStorage){
	alert('当前页面不支持localStroage')
}else{
	/* localStorage.setItem('a', '1');
	localStorage.b = '2';
	localStorage['c'] = '3' */
	alert(localStorage.getItem('b'))
	alert(localStorage.c)
	alert(localStorage['a'])
}
```

#### removeItem(name);

```javascript
onload = function(){
	var oBtn = document.getElementById('btn1')
	oBtn.onclick = function(){
		localStorage.removeItem('a')
	}
}
```

## localStorage实战

滑动条，记录上一次滑动的位置

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<style type="text/css">
			#slide{
				width: 600px;
				height: 30px;
				border: black 1px solid;
				margin: 100px auto;
				position: relative;
			}
			#block{
				width: 30px;
				height: 30px;
				background-color: blue;
				position: absolute;
				left: 0px;
				top: 0px;
			}
			#full{
				width: 0px;
				height: 30px;
				left: 0px;
				top: 0px;
				background-color: orange;
				position: absolute;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				var oSlide = document.getElementById('slide')
				var oFull = document.getElementById('full')
				var oBlock = document.getElementById('block')
				
				if(!window.localStorage){
						alert('该浏览器不支持localstorage')
					}else{
						//取出本地存储中的值
						var l = localStorage.getItem('slide')?localStorage.getItem('slide') : 0
						oBlock.style.left = l + 'px'
						//填充部分要跟随滑块，进行填充
						oFull.style.width = l + 'px'
				}
				oBlock.onmousedown = function(ev){
					var e = ev || window.event
					var offsetX = e.clientX - oBlock.offsetLeft
					document.onmousemove = function(ev){
						var e = ev || window.event
						var l = e.clientX - offsetX
						if(l <= 0){
							l = 0
						}
						if(l >= 570){
							l = 570
						}
						oBlock.style.left = l + 'px'
						//填充部分要跟随滑块，进行填充
						oFull.style.width = l + 'px'
						//对滑块当前的位置，进行本地存储
						if(!window.localStorage){
							alert('该浏览器不支持localstorage')
						}else{
							localStorage.setItem('slide', l);
						}
					}
				}
				
				document.onmouseup = function(){
					document.onmousemove = null
				}
				}
		</script>
	</head>
	<body>
		<div id="slide">
			<div id='full'></div>
			<div id='block'></div>
		</div>
	</body>
</html>
```

## 强制改变this指向的函数

### this复习

没一个函数都有一个内置的变量this，指向当前函数的主人，函数的主人通过上下文关系判断

【注意】this指向当前函数的主人

### 常见this

#### 1、全局函数

this指向window

```javascript
function show(){

​	alert(this);

}

show();
```

#### 2、this=>person

```javascript
var person = {
	username:'kk',
	show:function(){
		alert(person.username);
		alert(this.username)
	}
}
person.show();
```

#### 3、

```javascript
window.onload = function(){
	var oBtn = document.getElementById('btn1');
	oBtn.onclick = function(){
		alert(this.innerHTML)
	}
}
```

### 强制数据类型转换

#### call

格式：函数名.call();

参数：

第一个参数：传入该函数this指向的对象，传入什么强制指向什么

第二个参数开始：将原函数的参数往后顺延一位

```javascript
<script>
    function show(x, y){
    alert(this);//call
    alert(x + ',' + y);//20,40
}
show.call('call', 20, 40)
</script>
```

#### apply

格式：函数名.apply()

参数：

第一个参数：传入该函数this指向的对象，传入什么强制指向什么

第二个参数：数组		数组，放入我们原有所有的参数

```javascript
<script>
    function show(x, y){
    alert(this);
    alert(x + ',' + y);
}
show.apply('this', [20, 40])
</script>
```

##### 小技巧

```javascript
Math.min(10, 20, 30, 40, 50);

Math.max(10, 20, 30, 40, 50);
```

可以用

```javascript
var arr = [10, 20, 30, 40, 50];
Math.min.apply(null, arr)
```

#### bind方法 预设this指向

bind 预设this指向

返回值是改变后的，而不是像apply和call直接执行

```javascript
<script>
    function show(x, y){
    alert(this);
    alert(x + ',' + y);
}
var res = show.bind('bind')
res(40, 50)
//或者
//show.bind('bind')(40, 50)
</script>
```

```javascript
<script>
	function show(){
		alert(this)
	}
    window.onload = function(){
			var oBtn = document.getElementById('btn1')
			
			/* oBtn.onclick = function(){
				show.apply('call')
			} */
			oBtn.onclick = show.bind('bind')
		}
</script>
```

## Let关键字

ECMA6新增

let关键字是用来声明变量	比之var更过分，只要遇到大括号就形成作用域

var关键字声明变量	遵从垃圾回收机制 将变量或者形参所在函数的大括号作为作用域处理

```javascript
function show(){
	var num = 10;
	alert(num);
}
show();
alert(num);//访问不到函数里的num
//var声明的变量只能在大括号内使用，出了大括号就用不到了
```

```javascript
if(1){
	var num = 10;
}
alert(num);//10
```

```javascript
<script>
	if(1){
		let num = 10
	}
	alert(num)//报错，let遇到大括号就形成作用域，出了作用域，无法调用大括号以内的声明的变量
</script>
```

【注意】

let关键字声明的变量，所在作用域叫做块级作用域，因为作用范围更小

var声明的变量，所在作用域叫做局部作用域

```javascript
<script type="text/javascript">
	onload = function(){
		var aBtns = document.getElementsByTagName('button')
		for(let i = 0;i < aBtns.length;i++){
			aBtns[i].onclick = function(){
				alert(i)
                //如果是var i，三个按钮显示的都是3
                //let i，每个按钮显示当前下标
			}
		}
	}
</script>
```

## const关键字

const声明变量，变量值只能在声明的时候确定，后续是没有办法修改的

【注意】const声明常量（变量没有办法更改）

const一般用在，必须声明一个变量方便使用，但是有不能变动的一个变量上

const是 不能改的变量 = 常量

## 箭头函数

新潮的函数写法

【注意】适当的省略函数中的function和return关键字

各种形式的箭头函数↓

### 1、无参数，无返回值

```javascript
function show(){
	alert('hello world');
}

var show = () => {
    alert('hello world')
}
```

### 2、有一个参数，无返回值

```javascript
function xxx(num){
	alert(num);
}

var xxx = num => {
	alert(num);
}
```

### 3、有一个参数，有返回值

```javascript
function add(x){
	return x + 10;
}

var add = x => {
	return x + 10;
}
```

### 4、多个参数，有返回值

```javascript
function show(x, y){
	alert(x + y);
}

var show = (x, y) => {
	alert(x + y);
}
```

【箭头函数需要注意的部分】

1、箭头函数，不能用new

2、箭头函数，如果返回值是一个对象，一定要加()

```javascript
function show(){
	return {
		uesername:'xxx'
	}
}

var show = () => ({

});
```

3、箭头函数中的this，指向的是<u>**上一层**</u>函数的主人

```js
var person = {
	username:'ironman'
	show:function(){
		alert(person.username);//ironman
		alert(this.username);//ironman
	}
	show:() => {
		alert(person.username);//ironman
		alert(this);//window
	}
}
```



## 箭头函数和ECMA5数组方法结合

### filter

```javascript
var arr = [10, 20, 30, 40, 50];

var newArr = arr.filter(function(item){
	return item > 20;
})

var newArr = arr.filter(item => item > 20);
alert(newArr);
```

```javascript
var arr = [10, 20, 30, 40, 50];

var newArr = arr.map(item => item * 1.3);
alert(newArr);
```

## 解构和ECMA6字符串

### 解构

#### 中括号解构

原方法

```javascript
var x = 10, y = 20, z = 30;
```

中括号解构

```javascript
var [x, y, z] = [10, 20, 30]
var [x, [y, z]] = [10, [20, 30]]
var [x, [y, z]] = [10, [20, ]]//alert(z),是undefined
```

#### 大括号解构

```javascript
var {name, age, sex} = {
	age: 18,
	name: 'ironman',
	sex: '男'
}
//是变量而不是对象
```

### 解构的好处

1、交换两个数

```javascript
var [x, y] = [10, 20];
[x, y] = [y, x];
```

2、函数可以返回多个值

```javascript
function show(){
	return ['结果1'， '结果2', '结果3'];
}
var [a, b, c] = show();
alert(a + '，' + c);
```

3、函数定义参数，和传入参数的顺序改变

【注意】参数可以附带默认值

```javascript
//传统
function showSelf(name, age, sex){
	alert(name + age + sex)
}
showSelf('小明', 18, '男');

//解构传参
function showSelf({name, age, sex}){
	alert(name + age + sex)
}
showSelf({
    age:18,
    name:'小明',
    sex:'男'
})

//默认值
function showSelf({name, age, sex = '女'}){
	alert(name + age + sex)
}
showSelf({
    age:18,
    name:'小明',
    sex:'男'
})
```

4、快速取出数组中的某一个元素

```javascript
var arr = [10, 20, 30, 40, 50];
var{0:first, 4:last} = arr;
alert(first);//10
alert(last == arr[4]);//true
```

### ECMA6新字符串

传统字符串：所有单引号，双引号括起来的都叫做字符串

ECMA6字符串：反引号		``

```javascript
var str = 'hello \
world';
alert(str);
```

```javascript
var str = `hello
word`;
alert(str)
```

1、ECMA6字符串，想怎么写就怎么写，换行，代码缩进，都能在字符串中体现出来

2、${变量/表达式/函数调用}

```javascript
function showSelf({name, age, sex = '女'}){
	alert(`我叫${name}，今年${Math.max(age, 20, 30)}岁，是一位${sex}性`);
}
showSelf({
    age:18,
    name:'小明',
    sex:'男'
})
```

## ES6新增数组方法和合并对象

### ES6新增数组方法

#### Array.from()	

将伪数组转成真数组

批量获取节点，都是伪数组

```javascript
var aLis = document.getElementsByTagName('li')
aLis = Array.from(aLis);//此时，alis伪数组转化成了真数组
alert(aLis.length);//4
aLis.push('hello');
alert(aLis);//[object HTMLLIElement],[object HTMLLIElement],[object HTMLLIElement],[object HTMLLIElement],hello
```

#### find()

功能：在数组中查找复合条件的元素，只要找到第一个符合条件的元素，就终止遍历

返回值：找到的元素

```javascript
var arr = [10, 20, 30, 40];
var res = arr.find(function(item, index, arr){
	return item > 20
})
alert(res);//30


alert(arr.find(item => item > 20));
```

#### findIndex()

返回值：找到的元素的下标

#### copyWithin

```javascript
var arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
arr.copyWithin(3, 7, 9)
alert(arr)//1,2,3,8,9,6,7,8,9
//原先4，5的部分变成了8， 9
```

参数：

第一个参数：从哪个下标开始

第二个参数和第三个参数是：范围[范围， end)

就是从下标三开始，用第七位到第九位（不包括第九位）元素，从下标三开始覆盖

### 合并对象Object.assign

```javascript
var obj1 = {
	a:10
}
var obj2 = {
	b:20
	c:30
}
var obj3 = {
	d:40
	f:['hello', 'world', true]
}
Object.assign(obj1, obj2, obj3);

```

【注意】将所有传入的对象，都合并到第一个对象中 

浅拷贝，如果合并完成后，修改obj2中的b为100，合并后的obj1不会有变化

如果是obj.f.push('xxx'),

obj1会有变化

浅拷贝：只拷贝地址

深拷贝：将复合数据类型重新生成一份，进行拷贝

## 集合和英汉词典案例

### 集合

1、不重复

2、无序

**集合遍历**

for..of 遍历集合

#### Set

```javascript
let imgs = new Set();
imgs.add(100);
imgs.add(100);
imgs.add('hello')
imgs.add('hello')
imgs.add(true)
imgs.add(new String('world'))
imgs.add(new String('world'))

console.log(imgs)

for(let item of imgs.keys()){
    //for(let item of imgs.values())
	//for(let item of imgs.entries())
    cosole.log(item);		
}
```

数组变集合

```javascript
var set = new Set([10, 20, 30, 40, 50, 40, 30, 20, 10])
```

集合变数组		将数据结构展开成数组

```javascript
var arr = [...set];
alert(arr)

var arr = [10, 20, 30, 40, 50, 40, 30, 20, 10];
arr = [...new Set(arr)]
```



#### Map

map映射

```javascript
let map = new Map();

//添加数据
map.set('张三','打渔的');
map.set('李四','种地的');
map.set('王五','挖煤的');

console.log(map)
alert(map.get('王五'));
```

map遍历 通过for of

```javascript
for(let [key,value] of map){
	console.log(key, value)
}
```

### 集合案例-英汉词典

数组

for循环

for...in

forEach

for...of

对象

for...in

set		for...of

map		for...of

## 面向对象回顾

```javascript
var car = {
	speed:60,
	run:function(road){
		return road.length / this.speed;
	}
};

var kuaihai = {
	length:1000
};

var hours = car.run(kuahai);

alert(hours);
```

### 语法

**类：**一类具有相同特征事物的抽象概念

**对象：**具体的某一个实例，唯一的某一个实体

### 数据结构

基本数据类型（只能存储单个的值）

==》数组（处理批量数据）

==》对象（既能存储数据，都能存储函数）

## 构造函数

### 工厂方法

1、创建了一个有名字和性别的人

```;
var p1 = new Object();
p1.name = 'blue';
p1.sex = '男';
p1.showName = function(){
	alert('我的名字是：' + this.name);
}
p1.showSex = function(){
	alert('我的性别是：' + this.sex);
}

p1.showName();
p1.showSexa();
```

2、如果现在想要创建一个叫red的女人

所以要封装创建对象的函数

```javascript
function createPerson(name, sex){
    //创建空对象
	var obj = new Object();
    
    //对空对象的属性和方法进行增加
	obj.name = name;
	obj.sex = sex;
	obj.showName = function(){
		alert('我的名字是：' + this.name);
	}
	obj.showSex = function(){
		alert('我的性别是：' + this.sex);
	}
    
    //对象报录
	return obj;
}


var p1 = createPerson('blue', '男')
p1.showName();
p1.showSex();

var p2 = createPerson('red', '女')
p2.showName();
p2.showSex();
```

#### 工厂模式：

1、原料（创建新对象

2、加工（添加属性和方法

3、出厂

### 使用new运算符

通过new调用函数，我们他叫做构造函数，构造函数可以构造对象

【注意】构造函数一般情况下首字母大写

## prototype原型

```javascript
var arr1 = [10, 20, 30, 40, 50];
var arr2 = [1, 2, 3, 4, 5];
//想要给数组添加一个方法，可以对数组中每一个元素求和

arr1.sum = function(){
    var res = 0;
    for(var i = 0;i < this.length;i++){
        res += this[i];
    }
    return res;
}
arr2.sum = function(){
    var res = 0;
    for(var i = 0;i < this.length;i++){
        res += this[i];
    }
    return res;
}

alert(arr1.sum());
alert(arr2.sum());
alert(arr1.sum == arr2.sum)//false,因为是两种不一样的函数
```

prototype 原型对象

概念：每一个函数上，都有一个原型对象prototype



用在构造函数上，我们可以给构造函数的原型prototype，添加方法

1、如果我们将方法添加到构造函数的原型prototype对象上

2、构造函数构造出来的对象共享原型上所有的方法

```javascript
Array.prototype.sum = function(){
    var res = 0;
    for(var i = 0;i < this.length;i++){
        res += this[i];
    }
    return res;
}

alert(arr1.sum());
alert(arr2.sum());
alert(arr1.sum == arr2.sum);//true
```

## 混合法

```javascript
function Person(name, sex){
	this.name = name;
	this.sex = sex;
}

Person.prototype.showName = function(){
	alert('my name is' + this.name);
}
Person.prototype.showSex = function(){
	alert('my sex is' + this.sex);
}

var p1 = new Person('blue', 'boy');
p1.showName();
p1.showSex();
var p2 = new Person('red', 'girl');
p2.showName();
p2.showSex();

alert(p1.showName == p2.showName);//true
```



## 原型详解

### __ proto __和prototype关系

构造函数构造出来的对象，有一个属性`__proto__`，指向构造出这个对象的构造函数的原型

```javascript
function Dog({name, type, age}){
	this.name = name;
	this.type = type;
	this.age = age;
}
Dog.prototype = {
	run:function(){
		alert(this.name + '会飞快的跑')
	},
	showSelf:function(){
		alert('这是一个${this.type}的，${this.age}岁的，叫${this.name}的小狗')
	}
}
			
var xiaobai = new Dog({
	name:'小白',
	type:'比熊',
	age:3
});
/* xiaobai.run();
xiaobai.showSelf(); */
alert(xiaobai.__proto__==Dog.prototype)
```

### instanceof关键字

功能：判断一个对象是否是构造函数构造出来的

```javascript
alert(xiaobai instanceof Dog);//true
alert(xiaobai instanceof Object);//true
```



## 继承和多态

面向对象，继承、封装（封装构造函数的过程）、多态

### 继承

```javascript
function Dog({name, type, age}){
     //this = new Object();
    //添加属性
    //xiaohua.name = name,
    //xiaohua.type = type,
    //xiaohua.age = age
	this.name = name;
	this.type = type;
	this.age = age;
}
function Teddy({name, type, age, color}){
    //1、继承父一级构造函数所有的属性
    //构造函数的伪装
    Dog.call(this,{
        name:name,
        type:type,
        age:age
    })
    this.color = color;
    //return this;
}

var xiaohua = new Teddy({
    name:'小花',
    type:'泰迪',
    age:10,
    color:'红色'
})
alert(xiaohua.color);
```

错误写法：Teddy.prototype = Dog.prototype

正确写法：

```javascript
for(var funcName in Dog.prototype){
	Teddy.prototype[funcName] = Dog.prototype[funcName];
}
```

### 多态

继承和多态同一件事情的两种完全不同的侧重：

继承：侧重的是从父一级构造函数，继承到的属性和方法

多态：侧重的是，子一级，自己重写和新增的属性和方法

面向对象是一个编程思想，支撑面向对象编程思想的语法是类（ECMA6之前没有这个概念）和对象，构造函数充当类的对象，构造函数和对象实现面向对象程序的时候，体现出构继承，封装和多态的特点

## ECMA6的class语法

## 运动和游戏开发

### 认识运动

动画：图像

原理：能够识别的最小的时间间隔是18帧

只要让动画的切换时间间隔大于18帧，一般情况下电影院里放映的电影是24帧



### 简单的运动

让一个div从左到右开始运动

> 1、停不下来（用if）
>
> 2、当速度取某些值的时候停不下来的问题（用大于等于）
>
> 3、到达目的值以后，点击还会继续往前运动（用if..else）将运动和停止分开
>
> 4、重复点击按钮，速度加快   
>
> 保证只有一个定时器，每次启动定时器之前，先将上一次定时器关闭

#### 运动框架

1、if..else，将运动和停止分开

2、每次启动定时器之前，先将上一次定时器关闭

### 分享菜单和淡入淡出案例

问题：连续点击，速度变快 

原因：每点击一次就开一个定时器，点击几次就有几个定时器同时工作

解决：保证每次只有一个定时器工作，先cearlnterval()

一、分享菜单

```javascript
<script type="text/javascript">
	//鼠标移入 left：-100 => 0
	//鼠标移出 left： 0 => -100
	window.onload = function(){
		var oDiv = document.getElementById('div1')
		oDiv.onmousemove = function(){
			startMove(0);
		}
		oDiv.onmouseout = function(){
			startMove(-100)
		}
	}
	
	var timer = null;
	
	function startMove(iTarget){
		var oDiv = document.getElementById('div1')
		var speed = 5;
		if(oDiv.offsetLeft > iTarget){
			speed = -Math.abs(speed)
		}else{
			speed = Math.abs(speed)
		}
		clearInterval(timer);
		timer = setInterval(function(){
			if(oDiv.offsetLeft == iTarget){
				clearInterval(timer);
			}else{
				oDiv.style.left = oDiv.offsetLeft + speed + 'px'
			}
		}, 30)
	}
	//因为封装的两个函数差不多，所以通过传形参的方式来执行函数
		function endMove(){
		var oDiv = document.getElementById('div1')
		var speed = -5;
		clearInterval(timer);
		timer = setInterval(function(){
			if(oDiv.offsetLeft == -100){
				clearInterval(timer);
			}else{
				oDiv.style.left = oDiv.offsetLeft + speed + 'px'
			}
		}, 30)
	} */
</script>
```

二、淡入淡出效果

分享实例

# AJAX

Ajax —> Asynchronous JavaScript and XML（异步JavaScript和XML）

节省用户操作，时间，提高用户体验。减少数据请求，传输获取数据

ajax：异步的JavaScript和数据传输

（1）使用Ajax 使用Ajax获取某一文本文件的内容

（2）Ajax过程详解 创建对象XMLHttpRequest() ActiveXObject('Microsoft.XMLHTTP')

## xml数据传输格式

（大型门户网站：新浪、网易、凤凰网）

​	优点：

​			1、种类丰富

​			2、传输量非常大

​	缺点：
​			1、解析麻烦

​			2、不太适合轻量级数据

## json数据传输格式（字符串）

95%的移动端应用

​	优点：

​			1、轻量级数据

​			2、解析比较轻松

​	缺点：

​			1、数据种类比较少

​			2、传输数据量比较小

## 同步

阻塞，当前程序运行，必须等前一个程序运行完毕以后，才能运行

## 异步

非阻塞，当前程序运行，和前面程序的运行没有任何关系

