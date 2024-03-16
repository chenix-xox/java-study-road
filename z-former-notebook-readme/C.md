# 小甲鱼

### 字符串处理函数

###### strlen（获取字符串长度）

###### strcpy（拷贝字符串）：

```c
#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
int main()
{
        char str1[] = "Original String";
        char str2[] = "New String";
        char str3[100];

        strcpy(str1, str2);
        strcpy(str3, "Copy Successful");

        printf("str1: %s\n", str1);
        printf("str2: %s\n", str2);
        printf("str3: %s\n", str3);

        return 0;
}
```

###### strncpy（受限的拷贝字符串）：

```c
#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996
int main()
{
        char str1[] = "To be or not to be";
        char str2[40];
        char str3[40];

        strncpy(str2, str1, sizeof(str2));
        strncpy(str3, str2, 5);
        str3[5] = '\0';

        printf("%s\n", str1);
        printf("%s\n", str2);
        printf("%s\n", str3);

        return 0;
}
```

###### strcat和strncat（连接字符串）

```c
#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
int main()
{
	char str1[] = "hello";
	char str2[] = "world";

	strcat(str1, " ");
	strcat(str1, str2);
	printf("%s\n", str1);
	return 0;
}
```

###### strcmp和strncmp（比较字符串）

比较两个字符串，

如果完全相等则返回值为0；

如果a>b，则返回值大于0；

如果a<b，则返回值小于0；

```c
#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)
int main()
{
	char str1[] = "world";
	char str2[] = "world";
	if (!strcmp(str1,str2))
	{
		printf("两者完全一致");
	}
	else {
		printf("两者不一致");
	}
	return 0;
}
```

## 指针

通常称地址为指针，而存放的值称为指针变量。

### 定义指针变量

类型名 *指针变量名

```c
char *pa;//定义一个指向字符型的指针变量
int *pb;//定义一个指向整型的指针变量
```

### 运算符

#### 取地址运算符(&)

如果需要获取某个变量的地址，可以使用取地址运算符（&）

```c
char *pa = &a;
int *pb = &f;
```

#### 取值运算符(*)

如果需要访问指针变量指向的数据，可以使用取值运算符（*）

```c
printf("%c,%d\n",*pa,*pb);
```

### 注意

避免访问未初始化的指针。

比如：

```c
#include <stdio.h>
int main()
{
	int *a;
	
	*a = 123;
	
	return 0;
}
```

定义了一个整型指针，可是没有赋值，所以是野指针。

### 指针和数组

#### 数组名的真实身份

数组名其实是数组第一个元素的地址。	

#### 指向数组的指针

##### 指针的运算

当指针指向数组元素的时候，我们可以对指针变量进行加减运算，这样做的意义相当于指向距离指针所在位置向前或向后第n个元素。

```c
#include<stdio.h>
int main()
{
	char a[] = "helloworld";
	char *p = a;
	printf("*p = %c,*(p+1) = %c,*(p+2) = %c\n", *p, *(p + 1), *(p + 2));
	return 0;
}
```

### 指针和数组的区别

```c
#include<stdio.h>
#include<string.h>
int main()
{
	char *str = "china no.1";
	int i, length;	
	length = strlen(str);
	for (i = 0; i < length; i++)
	{
		printf("%c", str[i]);
	}
	printf("\n");
	return 0;
}
```

等价于，好哥俩。

```c
#include<stdio.h>
#include<string.h>
int main()
{
	char str[] = "china no.1";
	int i, length;	
	length = strlen(str);
	for (i = 0; i < length; i++)
	{
		printf("%c", str[i]);
	}
	printf("\n");
	return 0;
}
```

#### 区别

数组名只是一个地址，而指针是一个左值。

### 指针数组和数组指针

举例：

```c
指针数组：int *p1[5];
数组指针：int (*p2)[5];
```

#### 指针数组

int *p1[5];

| 下标 | 0     | 1     | 2     | 3     | 4     |
| ---- | ----- | ----- | ----- | ----- | ----- |
| 元素 | int * | int * | int * | int * | int * |

指针数组是一个数组，每个数组元素存放一个指针变量。

```c
#include<stdio.h>
int main()
{
	char* p1[5] = {
		"1",
		"2",
		"3",
		"4",
		"5"
	};
	int i;
	for ( i = 0; i < 5; i++)
	{
		printf("%s", p1[i]);
	}
	return 0;
}
```

####  数组指针

int (*p2)[5];

| 下标 | 0    | 1    | 2    | 3    | 4    |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 元素 | int  | int  | int  | int  | int  |

数组指针是一个指针，它指向的是一个数组。	

# 黑马

## 函数

##### 随机数

```c
#include<stdlib.h>
#include<stdio.h>
#include<time.h>
//双色球 六红一篮
int main()
{
	int a[6] = { 0 };
	int value = 0;
	int flag = 0;
	int i, k, j;
	srand((size_t)time(NULL));//不写就不是随机
	for (i = 0; i < 6; i++)
	{
		value = rand() % 32 + 1;
		for (k = 0; k < flag; k++)
		{
			if (value == a[k])
			{
				i--;
				break;
			}
		}
		if (k == flag)
		{
			a[flag] = value;
			flag++;
		}
	}
	printf("红球是：");
	for (j = 0; j < 6; j++)
	{
		printf("%3d", a[j]);
	}
	printf("\n蓝球是：%d", rand() % 16 + 1);
}
```

## 	函数的定义

### 函数的定义格式

在不同函数中函数中的变量名可以重名，因为作用域不同

#### 1）函数名

理论上可以随意取名，最好见名知意，让用户看到这个函数名字就知道这个函数的功能。<u>注意，函数后面有个圆括号（），代表着各位函数不是普通变量。</u>

#### 2）形参列表

在定义函数时指定的形参，<u>在未出现函数调用时，它们并不占内存中的储存单元</u>，因此称为形式参数或虚拟参数，简称形参，表示它们并不是实际存在的数据，所以，形参里的变量不能赋值。

```c
void max(int a = 10,int b = 20)
{

}
//error，形参不能赋值
```

形参必须是 类型+变量形式

缺一不可。

#### 3）函数体

花括号{}里的内容即为函数体的内容，这里为函数功能实现的过程，这和以前的写代码没太大区别，以前我们写代码写在main（）函数里，现在只是把这些写到别的函数里。

#### 4）返回值

函数的返回值是通过函数中的return语句获得的，return后面的值也可以是一个表达式。 

```c
int sum = a + b;
return sum;
```

可以写成

```c
return a + b;
```

a）尽量保证return语句中表达式的值和函数返回类型是同一类型。

b）如果函数返回的类型和return语句中表达式的值不一致，则以函数返回类型为准，即<u>函数返回类型决定返回值的类型</u>。对数值型数据，可以自动进行类型转换。

**注意：**如果函数返回的类型和return语句中表达式的值不一致，而它又无法自动进行类型转换，程序则会报错。

c） return语句的另一个作用为中断return所在的执行函数，类似于break中断循环、switch语句一样。

```c
int max()
{
	return 1;//执行到此，函数已经被中断，所以下面的return 2无法被执行到
	return 2;//没有执行
}
```

d）如果函数带返回值，return后面必须跟着一个值，如果函数没有返回值，函数名字的前面必须洗一个void关键字，这时候我们写代码也可以通过return中断函数（也可以不用），只是这时，return后面不带内容（封号“；”除外）。

### 总结

```c
#include<stdlib.h>
#include<stdio.h>
#include<time.h>
//函数定义过程
//函数定义中 参数称为形参 形式参数
int add(int a, int b)
{
	int sum = a + b;
	return sum;
}
//void add(int a, int b)
//{
//	int sum = a + b;
//	return;//也可以不写
//}
void print()
{
	printf("hello world\n");
}
int main()
{
	//在函数调用过程中将实参传递给形参
	int a = 10;
	int b = 20;
	int c;
	c = add(a, b);//函数调用
	//在函数调用过程中传递的参数称为实参
	//实际参数 有具体的值
	//在函数调用结束 函数会在内存中销毁
	//会在栈区自动销毁
	printf("%d\n", c);
	print();//没有括号是 函数指针 有意义 但是不打印无法调用
}

```

```c
#include<stdlib.h>
#include<stdio.h>
#include<time.h>
void swap(int a,int b)
{
	printf("交换前：\n");
	printf("a = %d\n", a);
	printf("b = %d\n", b);
	int temp = a;
	a = b;
	b = temp;
	printf("交换后：\n");
	printf("a = %d\n", a);
	printf("b = %d\n", b);
}
int main()
{
	int a = 10;
	int b = 20;
	swap(a,b);
	return 0;
}

```

## 函数样式

### 无参函数调用

如果是调用无参函数 ，则不能加上“实参”，但括号不能省略。

无参函数一般用于打印。

### 有参函数调用

a）如果实参表列包含多个实参，则各参数用逗号隔开。

### 函数返回值

a）void类型不可以直接定义数字

### 函数声明

extern+类型+函数名....（其中，extern可以省略）

在vs里面右击声明里的函数名，可以转到定义

### 声明和定义的区别

声明变量不需要建立储存空间。如：extern int a（只是声明）

定义变量要建立储存空间。如：int b （即使声明也是定义）

### 主函数和exit函数

exit：终止程序执行过程

exit(0)

#### 防止头文件重复包含

【仅用于Windows】

```c
#pragram once

//声明语句
```

或

【可用于Linux】

```c
#ifndef_FUN_H__
#define_FUN_H__

//声明语句

#endif
```

##  指针

windows电脑在做数据存储是采用小端对齐

在x86中所有指针类型是4字节大小

在x64中所有指针类型是8字节大小

#### 野指针

指针变量指向一个位置的空间。

`int* p = 100;`

操作系统将0-255作为系统占用不允许访问操作。

#### 空指针

空指针是指内存地址编号为0的空间。

`int* p = NULL;`

#### 万能指针void

`void*`指针可以指向任何变量的内存空间：

万能指针可以接收任意类型变量的内存地址

在通过万能指针修改变量的值时，要找到变量对应的指针类型

#### const修饰的指针变量

```c
const int a  = 10;
a = 100;//错误
//const指定的对象无法修改
```

指针简介修改常量值

```c
const int a = 10;
int* p = &a;
*p = 100;
printf("%d\n",a);
//此时可修改 a = 100;
```

①const修饰指针类型

可以修改指针变量的值；

不可以修改指针指向内存空间的值。

```c
int a = 10;
int b = 20;
const int* p = &a;
p = &b;//ok
*p = 100;//no，此时const修饰的是*p
printf("%d\n",*p);
```

②const修饰指针变量。

可以修改指针指向内存空间的值；

不可以修改指针变量的值。

> const离谁近，就不能修改谁
>
> ```c
> int a = 10;
> int b = 20;
> int* const p = &a;
> p = &b;//no,此时const修饰的是p
> *p = 200;//yes
> printf("%d\n",a);
> ```

##### 只读指针

③const修饰指针类型 修饰指针变量

二级指针操作↓

```c
int a = 10;
int b = 20;

const int* const p = &a;
printf("%d\n",*p);//10

int** pp = &p;
* pp = &b;

printf("%d\n",*p);//20
//间接修改
//* pp
```

```c
int a = 10;
int b = 20;

const int* const p = &a;

int** pp = &p;
** pp = 100;
printf("%d\n",*p);//100
//间接修改
//** pp是变量的值
```

### 指针和数组

指针类型变量+1，等同于内存地址+sizeof

例如p+1，即p+sizeof（int）

0xff00→0xff04

```c
#include <stdio.h>
int main()
{
	int arr[] = { 1,2,3,4,5,6,7,8,9,10 };
	int* p;
	p = arr;
	printf("%p\n%p\n", arr, p);
	printf("%d\n", *p);
    //*p就是首位
	for (int i = 0; i < 10; i++)
	{
		printf("arr是%d\t", arr[i]);
		printf("p是%d\n", p[i]);
	}
	return 0;
}
```

所有的指针类型相减，相减的结果都是int类型

### 指针加减运算

#### 加法运算

<u>①指针计算不是简单的整数相加</u>

如果是一个`int*`，+1的结果是增加一个int的大小

如果是一个`char*`，+1的结果是增加一个char的大小

<u>指针操作数组时，下标允许是负数</u>

```c
int arr[]={1,2,3,4,5,6,7};
int* p;
p = &arr[3];
printf("%d\n",p[-2]);//*(p-2)
```

“+”：不能添加两个指针

两个指针相加是野指针，相乘也是野指针。

乘除取余，对指针都是非法操作，err。

两个指针可以进行<u>比较</u>操作。

也可以进行<u>逻辑判断</u>。

### 指针数组

指针数组，是数组。数组的每个元素都是指针类型。

### 多级指针

C语言语序有多级指针存在，在实际的程序中一级指针最常用，其次是二级指针。

二级指针就是指向一个一级指针变量的地址的指针。

三级指针基本用不着，但考试会考。

注意：二级指针加偏移量，相当于跳过了一个一位数组大小。

​			一级指针加偏移量，相当于跳过了一个元素。

### 指针和函数

值传递 形参不影响实参的值

```c
#include<stdio.h>
void swap(int a, int b)
{
	int temp = a;
	a = b;
	b = temp;
}
int main()
{
	int a = 10;
	int b = 20;
	swap(a, b);
	printf("%d\n%d\n", a, b);
	return 0;
}
```

地址传递 形参可以改变实参的值

```c
#include<stdio.h>
void swap(int* a, int* b)
{
	int temp = *a;
	*a = *b;
	*b = temp;
}
int main()
{
	int a = 10;
	int b = 20;
	swap(&a, &b);
	printf("%d\n%d\n", a, b);
	return 0;
}
```



# 零散

### 递归

1）函数在运行时调用自己的情况，叫做递归。

2）只递不归会导致程序崩溃

3）要在适当的时候终止递归（条件语句）

![image-20210108144505031](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20210108144505031.png)

