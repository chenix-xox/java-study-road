# 1.	jQuery概述

## 1.1	JavaScript库

就是一个JS文件，对原生JS代码进行了封装，存放到里面。

比如jQuery，就是为了快速方便的操作DOM，里面基本都是函数（方法）

### 常见的JavaScript库

jQuery、prototype、YUI、Dojo、Ext JS、移动端的zepto

## 1.2	jQuery的概念

jQuery是一个快速的简洁的JavaScript库，宗旨是“Write Less，Do More”，提倡写更少的代码，做更多的事。

j就是JavaScript；Query 查询；意思就是查询js，把js中的DOM操作做了封装，我们可以快速的查询使用里面的功能

jQuery封装了JavaScript常用的功能代码，优化了DOM操作、事件处理、动画设计和Ajax交互。

学习jQuery的本质：就是学习调用这些函数

## 优点

轻量级。核心文件才几十kb，不会影响页面加载速度

跨浏览器兼容。基本兼容了现在主流浏览器

链式编程、隐式迭代

对事件、样式、动画支持，大大简化了DOM操作

支持插件扩展开发。有着丰富的第三方插件，例如：树形菜单、日期控件、轮播图等

免费、开源

# 2.	jQuery的基本使用

## 2.1	下载

版本：

1x：兼容IE 678等低版本浏览器，官网不再更新

2x：不兼容IE 678等低版本浏览器，官网不再更新

3x：不兼容IE 678等低版本浏览器，是官方主要更新维护的版本

## 2.2	jQuery的使用步骤

1.引入jQuery文件

2.使用即可

## 2.3	jQuery的入口函数

```javascript
$(function(){
	...//此处是页面DOM加载完成的入口
});
```

```javascript
$(document).ready(function(){
    ...//此处是页面DOM加载完成的入口
});
```

1.等着DOM结构渲染完毕即可执行内部代码，不必等到所有外部资源加载完毕，jQuery帮我们完成了封装

2.相当于原生js中的DOMContentLoaded

3.不同于原生js中的load时间是等页面文档、外部js文件、css文件、图片加载完毕才执行内部代码

## 2.4	jQuery的顶级对象$

1.$是jQuery的别称，在代码中可以使用jQuery代替$，但一般为了方便，通常都直接使用$

2.是jQuery的顶级对象，相当于原生JavaScript中的window，把元素利用$包装成jQuery对象，就可以调用jQuery的方法

## 2.5	jQuery对象和DOM对象

**1.用原生js获取的对象**

```javascript
var myDiv = document.querySelecor('div');
//myDiv就是DOM对象
console.dir(myDiv);
```

**2.jQuery获取的就是jQuery对象**

```javascript
$('div');//$('div')是一个jQuery对象
console.dir($('div'));
```

**3.jQuery对象本质是：利用$对DOM对象包装后产生的对象（伪数组形式存储）**

## 

**DOM对象与jQuery对象之间是可以相互转换的**

因为原生js比jQuery更大，原生的一些属性和方法jQuery没有给我们封装，要想使用这些属性和方法需要把jQuery对象转换为DOM对象才能使用

```javascript
<video src='mov.mp4' muted></video>//谷歌需要muted
```

1.DOM对象转换为jQuery对象：`$(DOM对象)`

​	(1)直接获取视频，得到的就是jQuery对象

​	`$('video');`

​	(2)已经使用原生js获取过来的DOM对象

​	`var myVideo = document.querySelector('video');`

​	`$(myVideo);`

​	`$(myVideo).play();`会报错，not function，因为jQuery中没有play方法

​	`myDiv.play();`

2.jQuery对象转换为DOM对象（两种方式）

```javascript
$('div')[index]	index是索引号
```

```javascript
$('div').get(index)	index是索引号
```

```javascript
$('video')[0].play()
$('video').get(0).play()
//可以使用
```

# 3.	jQuery常用API

## 1.	jQuery选择器

### 1.1	jQuery基础选择器

原生JS获取元素方式很多，很杂，而且兼容性情况不一致，因此jQuery给我们做了封装，使获取元素统一标准

```javascript
$("选择器")//里面选择器直接写CSS选择器即可，但是要加引号
```

```javascript
ID选择器：$("#id")
全选选择器：$('*')
类选择器：$(".class")
标签选择器：$("div")
并集选择器：$("div,p,li")
交集选择器：$("li.current")
```

### 1.2	jQuery层级选择器

```javascript
子代选择器：$("ul > li");//仅儿子辈
后代选择器：$("ul li");//所有子孙后代
```

### 知识铺垫

jQuery设计样式

```javascript
$('div').css('属性','值')
```

### 1.3	**隐式迭代

遍历内部DOM元素(伪数组形式存储)的过程就叫做**隐式迭代**

简单理解：给匹配到的所有元素进行循环遍历，执行相应的方法，而不用我们再进行循环。

### 1.4	筛选选择器

```javascript
:first	$("li:first")	获取第一个li元素
:last	$("li:last")	获取最后一个li元素
:eq(index)	$("li:eq(2)")	获取到的li元素中，选择索引号为2的元素，索引号index从0开始
:odd	$("li:odd")		获取到的li元素中，选择索引号为奇数的元素
:even	$("li:even")	获取到的li元素中，索引号为偶数的元素
```

### 1.5	**筛选方法

| 用法                           | 说明                                                 |
| ------------------------------ | ---------------------------------------------------- |
| $("li").parent();              | 查找父级                                             |
| $("ul").children("li")         | 相当于$("ul>li")，最近一级（亲儿子）                 |
| $("ul").find("li");            | 相当于$("ul li")，后代选择器                         |
| $(".first").siblings("li");    | 查找兄弟节点，不包括本身                             |
| $(".first").nextAll()          | 查找当前元素之后所有的同辈元素                       |
| $(".last").prevAll()           | 查找当前元素之前所有的同辈元素                       |
| $('div').hasClass("protected") | 检查当前的元素是否含有某个特定的类，如果有则返回true |
| $("li").eq(2);                 | 相当于$("li:eq(2)")                                  |

### 微博下拉菜单

```javascript
<ul>
	<li>
		<a>微博</a>
		<ul>
			<li>
				私信
			</li>
		</ul>
	</li>
	<li>
		<a>微博</a>
		<ul>
			<li>
				私信
			</li>
		</ul>
	</li>
	<li>
		<a>微博</a>
		<ul>
			<li>
				私信
			</li>
		</ul>
	</li>
</ul>

<script>
	$(function(){
    
   		 //鼠标经过
		$(".nav>li").mouseover(function(){
			//$(this) jQuery当前元素 不要引号
			//show()显示元素
			$(this).children("ul").show();
		})
    	
		//鼠标离开
    	$(".nav>li").mouseout(function(){
			//$(this) jQuery当前元素 不要引号
			//hide()隐藏元素
			$(this).children("ul").hide();
		})
	})
</script>
```

**重点记住：parent()	children() 	find() 	siblings()	eq()**

### 1.6	jQuery里面的排他思想

想要多选一的效果，排他思想：当前元素设置样式，其余的兄弟元素清楚样式

```javascript
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>

<script>
    $(function(){
    	//1.隐式迭代，给所有按钮都绑定了点击事件
    	$("button").click(function(){
            //2.当前的元素变化背景颜色
            $(this).css("background","pink");
            //3.其余的兄弟去掉背景颜色	隐式迭代
            $(this).siblings("button").css("background","");
        });
	})
</script>
```

### 案例：淘宝服饰精品案例分析

1.核心原理：鼠标经过左侧盒子某个小li，就让内容区盒子相应图片显示，其余的图片隐藏

2.需要得到当前小li的索引号，就可以显示对应索引号的图片

3.jQuery得到当前元素索引号 `$(this).index()`

4.中间对应的图片，可以通过 `eq(index)` 方法去选择

5.显示元素：show()	隐藏元素：hide()

### 1.7	链式编程

链式编程是为了节省代码量，看起来更加优雅

```javascript
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
<button>快速</button>
```

```javascript
<script>
    $(function(){
    	//1.隐式迭代，给所有按钮都绑定了点击事件
    	$("button").click(function(){
            //2.当前的元素变化背景颜色
            $(this).css("background","pink");
            //3.其余的兄弟去掉背景颜色	隐式迭代
            $(this).siblings().css("background","");
        });
	})
</script>
```

**链式编程方法**

```javascript
$(this).css('color','red').sibling().css('color','');
```

使用链式编程一定注意是哪个对象执行样式

## 2.	jQuery样式操作

### 2.1	操作css方法

jQuery可以使用css方法来修改简单元素样式；也可以操作类，修改多个样式

1.参数只写属性名，则是返回属性值

```javascript
$(this).css('color');
```

2.参数是属性名，属性值，逗号分隔，是设置一组样式，属性必须加引号，值如果是数字可以不用跟单位和引号

```javascript
$(this).css("color","red");
```

3.参数可以是对象形式，方便设置多组样式。属性名和属性值用冒号隔开，属性可以不用加引号

```javascript
$(this).css({
	width:400,
	height:400,
	backgroundColor:"red"
})
```

### 2.2	设置类样式方法

作用等同于以前的classList，可以操作类样式，注意操作类里面的参数不要加点

1.添加类

```javascript
$("div").addClass("current");
```

2.删除类

```javascript
$("div").removeClass("current");
```

3.切换类（添加或者移除）

```javascript
$("div").toggleClass("current");
```

### tab栏切换案例

```javascript
div	class = "tab"
	div	class = "tab_list"
		ul
			li	class = "current"
			li
			li
		ul
	div
	div class = "tab_con" display = "block"
		div	class = "item"
		div	class = "item"
		div	class = "item"
		div	class = "item"
		div	class = "item"
		div	class = "item"
	div
div
```

```javascript
//1.点击上部的li，当前li添加current类，其余兄弟移除类

$(".tab_list li").click(function(){

	//链式编程操作

	$(this).addClass("current").siblings().removeClass("current");

	//2.点击的同时，获取当前li的索引号

	var index = $(this).index();

	//3.让下部里面相应的索引好的item显示，其余item隐藏

	$(".tab_con .item").eq(index).show().siblings().hide();

});
```

### 2.3	类操作与className区别

原生JS中className会覆盖元素原先里面的类名

```javascript
var one = document.querySelector(".one");
one.className = "two";
```

jQuery里面类操作只是对指定类进行操作，不会影响原先的类名

```javascript
$(".one").addClass("two");//追加类名，不影响以前的
$(".one").removeClass("two");
```

## 3.	jQuery效果

jQuery给我们封装了很多的动画效果，最常见的如下：

显示隐藏：show()	hide()	toggle()

滑动：slideDown()	slideUp()	slideToggle()

淡入淡出：fadeln()	fadeOut()	fadeToggle()	fadeTo()

自定义动画：animate()

### 3.1	显示隐藏效果

#### 1.显示、隐藏、切换语法规范

```javascript
show([speed,[easing],[fn]]);
hide([speed,[easing],[fn]]);
toggle([speed,[easing],[fn]]);
```

#### 2.显示、隐藏、切换参数

（1）参数都可以省略，无动画直接显示

（2）speed：三种预定速度之一的字符串（”slow“，”normal“，or	“fast”）或动画时长的毫秒数值

（3）easing：（Optional）用来指定切换效果，默认是“swing”，可用参数“linear“

（4）fn：回调函数，在动画完成时执行的函数，每个元素执行一次

**一般情况，不加参数，直接显示隐藏**

### 3.2	滑动效果

```javascript
<button>下拉</button>
<button>上拉</button>
<button>切换</button>
```

#### 1.滑动语法规范

```javascript
slideDown([speed,[easing],[fn]]);
slideUp([speed,[easing],[fn]]);
slideToggle([speed,[easing],[fn]]);
```

#### 2.滑动参数

（1）参数都可以省略，无动画直接显示

（2）speed：三种预定速度之一的字符串（”slow“，”normal“，or	“fast”）或动画时长的毫秒数值

（3）easing：（Optional）用来指定切换效果，默认是“swing”，可用参数“linear“

（4）fn：回调函数，在动画完成时执行的函数，每个元素执行一次

### 3.3	事件切换

```javascript
hover([over,]out)
```

（1）over：鼠标移到元素上要触发的函数（相当于mouseenter）

（2）out：鼠标移出元素要触发的函数（相当于mouseleave）

**1.事件切换hover就是鼠标经过和离开的复合写法**

```javascript
$(".nav > li").hover(funtion(){
    $(this).children("ul").slideDown(200);                
},function(){
    $(this).children("ul").slideUp(200);
})
```

**2.事件切换hover 如果只写一个函数，那么鼠标经过和鼠标移开都会触发这个函数**

```javascript
$(".nav > li").hover(function(){
	$(this).children("ul").slideToggle(200);
})
```

### 3.4	动画队列及其停止排队方法

#### 1.动画或效果队列

动画或者效果一旦触发就会执行，如果多次触发，就造成多个动画或效果排队

#### 2.停止排队

```javascript
stop();
```

```javascript
$(".nav > li").hover(function(){
	//stop必须写在动画的前面
    $(this).children("ul").stop().slideToggle(200);
})
```

### 3.5	淡入淡出效果

#### 1.淡入、淡出、切换效果语法规范

```javascript
fadeIn([speed,[easing],[fn]]);
fadeOut([speed,[easing],[fn]]);
fadeTaggle([speed,[easing],[fn]]);
```

#### 2.淡入、淡出、切换效果参数

（1）参数都可以省略，无动画直接显示

（2）speed：三种预定速度之一的字符串（”slow“，”normal“，or	“fast”）或动画时长的毫秒数值

（3）easing：（Optional）用来指定切换效果，默认是“swing”，可用参数“linear“

（4）fn：回调函数，在动画完成时执行的函数，每个元素执行一次

#### 1.渐进方式调整到指定的不透明度

```javascript
fadeTo([[speed],opacity,[easing],[fn]]);
```

#### 2.效果参数

（1）opacity透明度必须写，取值0~1

（2）speed：三种预定速度之一的字符串（”slow“，”normal“，or	“fast”）或动画时长的毫秒数值，必须写

（3）easing：（Optional）用来指定切换效果，默认是“swing”，可用参数“linear“

（4）fn：回调函数，在动画完成时执行的函数，每个元素执行一次

```javascript
fadeTo(1000,0.5);
```

```\
<ul class="wrap">
	<li>
		<a><img></a>
	</li>
	<li>
		<a><img></a>
	</li>
	<li>
		<a><img></a>
	</li>
	<li>
		<a><img></a>
	</li>
</ul>
```

```javascript
$(function(){
	$("wrap li").hover(funtion(){
    	$(this).siblings().stop().fadeTo(400,0.5);                   
	},function(){
    	$(this).siblings().stop().fadeTo(400,1);  
	}
});
```

### 3.6	自定义动画 animate

#### 1.语法

```javascript
animate(params,[speed],[easing],[fn]);
```

#### 2.参数

（1）params：想要更改的样式属性，以对象形式传递，必须写。属性名可以不用带引号，如果是复合属性则需要采取驼峰命名法borderLeft。其余参数都可以省略。

（2）speed：三种预定速度之一的字符串（”slow“，”normal“，or	“fast”）或动画时长的毫秒数值，必须写

（3）easing：（Optional）用来指定切换效果，默认是“swing”，可用参数“linear“

（4）fn：回调函数，在动画完成时执行的函数，每个元素执行一次

```javascript
<button>动起来</button>
<div></div>//必须是absolute
```

```
$("button").click(function(){
	$("div").animate({
		left:200,
		top:300,
		opacity:.4,
		width:500
	}, 500);
})
```

### 案例：王者荣耀手风琴效果

```javascript
.small{
    position:absolute;
}
.big{
    display:none;
}
.king li.current .big{
    display:block;
}
.king li.current .small{
    display:none;
}

<div class="king">
	<ul>
		<li class="current">
			<a>
				<img class="small">
				<img class="big">
			</a>
		</li>
		<li>
			<a>
				<img class="small">
				<img class="big">
			</a>
		</li>
		<li>
			<a>
				<img class="small">
				<img class="big">
			</a>
		</li>
	</ul>
</div>
```

1.鼠标经过某个小li有两步操作

2.当前小li宽度变为224px，同时里面的小图片淡出，大图片淡入

3.其余兄弟小li宽度变为69px，小图片淡入，大图片淡出

```javascript
$(".king li").mouseenter(funtion(){
	//2.当前小li宽度变为224px，同时里面的小图片淡出，大图片淡入
    $(this).stop().animate({
    width:224
	},200).find(".samll").stop().fadeOut().siblings(".big").stop().fadeIn();
	//3.其余兄弟小li宽度变为69px，小图片淡入，大图片淡出
	$(this).siblings("li").stop().animate({
        width:69
    }).find(".small").stop().fadeIn().siblings(".big").stop().fadeOut(); 
})
```

## 4.jQuery属性操作

### 4.1	设置或获取元素固有属性值 prop()

所谓元素固有属性就是元素本身自带的属性，比如<a>元素里面的href，比如<input>里面的type

```javascript
//element.prop("属性名")	获取属性值
$("a".prop("href"));
```

#### 1.获取属性方法

```javascript
prop("属性")
```

#### 2.设置属性语法

```javascript
prop("属性"，"属性值")
```

### 4.2	设置或获取元素自定义属性值 attr()

#### 1.获取属性方法

```javascript
attr("属性")
```

#### 2.设置属性语法

```javascript
attr("属性"，"属性值")
```

### 4.3	数据缓存date()

data()方法可以在指定的元素上存取数据，并不会修改DOM元素结构。一旦页面刷新，之前存放的数据都会被移除

```
<span>123</span>
$<"span">.data("uname","andy");
```

#### 1.附加数据语法

```javascript
data("name","value")//向被选元素附加数据
```

#### 2.获取数据语法

```javascript
data("name");//向被选元素获取数据
```

### 案例：购物车案例模块-全选

1.全选思路：里面三个小的复选框按钮（j-checkbox）选中状态（checked）跟着全选按钮（checkall）走

2.因为checked是复选框的固有属性，此时我们需要利用prop()方法获取和设置该属性

3.把全选按钮状态赋值给3个小复选框就可以了

## 5.jQuery内容文本值

主要针对元素的内容还有表单的值操作

### 1.普通元素内容html()	（相当于原生innerHTML）

```javascript
html()//获取元素内容
html("内容")//设置元素的内容
```

### 2.普通元素文本内容text()	（相当于原生innerText）

```
text()//获取元素内容
text("内容")//设置元素的内容
```

### 3.表单的值val()	（相当于原生value）

### 案例：购物车案例模块-增减商品数量分析

### 案例：购物车案例模块-修改商品小计

## 6.jQuery元素操作

主要是遍历、创建、添加、删除元素操作

### 6.1	遍历元素

jQuery隐式迭代是对同一类元素做了同样的操作。如果想要给同一类元素做不同操作，就需要用到遍历

#### 语法1

```javascript
$("div").each(function(index,domEle){xxx;})
```

1.each()方法遍历匹配的每一个元素。主要用DOM处理。each每一个

2.里面的回调函数有两个参数：index是每个元素的索引号；demEle是每个DOM元素对象，不是jQuery对象

3.所以要想使用jQuery方法，需要给这个dom元素转换为jQuery对象$(domEle)

```javascript
<div>1</div>
<div>2</div>
<div>3</div>


var arr = ["red","green","blue"];
$("div").each(funtion(index,doEle){
	//回调函数第一个参数一定是索引号	可以自己指定索引号名称
    console.log(index);
	//回调函数第二个参数一定是dom元素对象	可以自己指定索引号名称
	console.log(domEle);
	$(domEle).css("color",arr[index]);
})
```

#### 语法2

```javascript
$.each(object,function(index,element){xxx;})
```

1.$.each()方法可用于遍历任何对象。主要用于数据处理，比如数组，对象

2.里面的函数有2个参数：index是每个元素的索引号；element遍历内容

```javascript
var arr = ["red","green","blue"];
$.each($("div"),function(index,ele){

})
$.each(arr,function(i,ele){
    console.log(i);
    console.log(ele);
})
$.each({
    name:"andy",
    age:18
    },function(i,ele){
    console.log(i);//输出的是name，age属性名
    console.log(ele;)//输出的是andy，18
})
```

### 购物车案例模块-计算总计和总额

### 6.2	创建元素

#### 语法：

```javascript
$("<li></li>");
```

动态的创造了一个li标签

```javascript
//1.创建元素
var li = $("<li>我是后来创建的li</li>");
//2.添加元素

//（1）内部添加
$("ul").append(li);//内部添加并放到最后面
$("ul").prepend(li);//内部添加并放到最前面

```

#### 1.内部添加

```javascript
element.append("内容");
```

把内容放到匹配元素内部的最后面，类似原生appendChild

```javascript
element.prepend("内容");
```

把内容放到匹配元素内部的最前面，类似原生appendChild

#### 2.外部添加

```javascript
element.after("内容")//把内容放到目标元素后面
element.before("内容")//把内容放到目标元素前面
```

```javascript
//1.创建元素
var div = $("<div>我是后来创建的div</div>");
//2.添加元素

//（2）外部添加
$(".text").before(div);
$(".text").after(div);
```

**内部添加元素，生成后，他们是父子关系**

**外部添加元素，生成后，他们是兄弟关系**

#### 3.删除元素

```javascript
element.remove()	//删除匹配的元素（本身）
element.empty()	//删除匹配的元素集合中的所有子节点
等同于：element.html("");
element.html("")	//清空匹配的元素内容
```

### 案例：购物车案例模块-清理购物车

### 案例：购物车案例模块-选中商品添加背景颜色

## 7.jQuery尺寸、位置操作

### 7.1	jQuery尺寸

| 语法                               | 用法                                                 |
| ---------------------------------- | ---------------------------------------------------- |
| width()/height()                   | 取得匹配元素宽度和高度值 只算width/height            |
| innerWidth()/innerHeight()         | 取得匹配元素宽度和高度值 包含padding                 |
| outerWidth()/outerHeight()         | 取得匹配元素宽度和高度值 包含padding、border         |
| outerWidth(true)/outerHeight(true) | 取得匹配元素宽度和高度值 包含padding、border、margin |

以上参数为空，则是获取相应值，返回的是数字型

如果参数为数字，则是修改相应值

参数可以不必写单位

### 7.2	jQuery位置

位置主要有三个：offset()、position()、scrollTop()/scrollLeft()

#### 1.offset()设置或获取元素偏移

①offset()方法设置或返回被选元素相对于文档的偏移坐标，跟父级没有关系

```javascript
<div class="father">
	<div class="son">
	</div>
</div>

//1.获取设置距离文档的位置（偏移）offset
console.log($(".son").offset());
console.log($(".son").offset().top);

$(".son").offset({
    top:200,
    left:200
});
```

②该方法有2个属性left、top。offset().top用于获取距离文档顶部的举例，offset().left用于获取距离文档左侧的距离

#### 2.获取距离带有定位父级位置（偏移）position	如果没有带定位的父级，则以文档为准。只能获取，不能设置偏移

```javascript
<div class="father">
	<div class="son">
	</div>
</div>

//2.获取距离带有定位父级位置（偏移）position	如果没有带定位的父级，则以文档为准
console.log($(".son").position());
```

#### 3.scrollTop()/scrollLeft()设置或获取元素被卷去的头部和左侧

①scrollTop()方法设置或返回被选元素被卷去的头部

```javascript
//页面滚动事件
$(window).scroll(function(){
    console.log($(document).scrollTop());
})
```

```javascript
//页面滚动事件
var boxTop = $(".container").offset().top;
$(window).scroll(function(){
    console.log($(document).scrollTop());
    if($(document).scrollTop() >= boxTop){
        $(".back").fadeIn();
    }else{
        $(".back").fadeOut();
    }
})
```

```javascript
//返回顶部
$(".back").click(function(){
    $(document).scrollTop(0);//返回页面顶部
})
```

### 案例：带有动画的返回顶部

1.核心原理：使用animate动画返回顶部

2.animate动画函数里面有个scrollTop属性，可以设置位置

3.但是是元素做动画，因此

```javascript
$("body,html").stop().animate({
	scrollTop:0
});//不能使文档，而是html和body元素做动画
```

### 案例：品优购电梯导航

1.当我们滚动到	今日推荐	模块，就让电梯导航显示出来

2.点击电梯导航页面可以滚动到相应内容区域

3.核心算法：因为电梯导航模块和内容区模块一一对应的

4.当我们点击电梯导航某个小模块，就可以拿到当前小模块的索引号

5.就可以把animate要移动的举例求出来：当前索引号内容区模块他的offset().top

6.然后执行动画即可



1.当我们点击电梯导航某个小li，当前小li添加current类，兄弟移除类名

2.当我们页面滚动到内容区域某个模块，左侧电梯导航，相对应的模块，也会添加current类，兄弟一处current类

3.触发的事件是页面滚动，因此这个功能要写到页面滚动事件里面

4.需要用到each，遍历内容区域大模块。each里面能拿到内容区域每一个模块元素和索引号

5.判断条件：被卷去的头部大于等于内容区域里面每个模块的offset().top

6.就利用这个索引号找到相应的电梯导航小li添加类

### 节流阀、互斥锁

# 4.jQuery事件

## 1.jQuery事件注册

### 单个事件注册

#### 语法

```javascript
element.事件(function(){})
```

```javascript
$("div").click(function(){事件处理程序})
```

其他事件和原生基本一致。

比如mouseover、mouseout、blur、focus、change、keydown、keyup、resize、scroll等

## 2.jQuery事件处理

### 2.1事件处理on()绑定事件

on()方法在匹配元素上绑定一个或多个事件的事件处理函数

#### 语法

```javascript
element.on(events,[selector],fn)
```

1.event：一个或多个用空格分隔的事件类型，如”click“或”keydown“

2.selector：元素的子元素选择器

3.fn：回调函数 即绑定在元素身上的侦听函数

```javascript
$("div").on({
	mouseenter:function(){
		$(this).css("background","skyblue");
	},
	click:function(){
		$(this).css("background","purple");
	},
	mouseleave:function(){
		$(this).css("background","blue");
	}
});
```

#### on()方法优势1：

可以绑定多个事件，多个处理事件处理程序

```javascript
$("div").on("mouseenter mouseleave",function(){
	$(this).toggleClass("current");
})
```

#### on()方法优势2：

可以事件委派操作。事件委派的定义就是，把原来加给子元素身上的事件绑定在父元素身上，就是把事件委托派给父元素。

```javascript
<ul>
	<li></li>
	<li></li>
	<li></li>
</ul>

$("ul").on("click","li",function(){
    
});
//click是绑定在ul身上的，但是触发而对象是ul里面的小li
```

#### on()方法优势3：

动态创建的元素，click()没有办法绑定事件，on()可以给动态生成的元素绑定事件

### 案例：发布微博案例

### 2.2事件处理off()解绑事件

off()方法可以移除通过on()方法添加的事件处理程序

```javascript
$("div").off();//解除div身上所有的事件
$("div").off("click");//这个是解除了div身上的点击事件
//事件委托解除
$("ul").off("click","li");//解除了事件委托
```

**如果有的事件只想触发一次，可以使用one()来绑定事件**

#### one()	只触发事件一次

```javascript
$("p").one("click",function(){
	alert(1);
})
```

###  2.3自动触发事件 trigger()

有些事件希望自动触发，比如轮播图自动播放功能跟点击右侧按钮一致。可以利用定时器自动触发右侧按钮点击事件，不必鼠标点击触发

```javascript
$(function(){
	$("div").on("click",function(){
		alert(1);
	});
})
```

#### 第一种写法：元素.事件()

```javascript
$("div").click();
```

#### 第二种写法：元素.trigger("type")

```
$("div").trigger("click");
```

#### 第三种写法：元素.triggerHandler("type")

这个不会触发元素的默认行为

比如输入框的光标闪烁

```javascript
$("input").on("focus",function(){
	$(this).val("hello world");
})
$("input").triggerHandler("focus");//不会有光标闪烁，其余两个会有光标闪烁
```

```javascript
$("div").triggerHandler("click");
```

## 3.jQuery事件对象

事件被触发，就会有事件对象的产生

```javascript
element.on(events,[selector],function(event){})
```

阻止默认行为：event.preventDefault()	或者	return false

阻止冒泡：event.stopPropagation()

```javascript
$(document).on("click",function(){
	console.log("点击了document");
});
$("div").on("click",function(event){
    console.log("点击了div");
    event.stopPropagation();
})
```

# 5.jQuery其他方法

## 1.jQuery对象拷贝

如果想要把某个对象拷贝（合并）给另外一个对象使用，此时可以使用$.extend()方法

### 语法：

```javascript
$.extend([deep],target,object1,[objectN]);
```

1.deep：如果设为true为深拷贝，默认为false浅拷贝

2.target：要拷贝的目标对象

3.object1：待拷贝到第一个对象的对象

4.objectN：待拷贝到第N个对象的对象

5.浅拷贝是把被拷贝的对象复杂数据类型中的地址拷贝给对象，修改目标对象会影响被拷贝对象

```javascript
$(function(){
	var targetObj={};
	var obj={
		id:1,
		name:"andy",
        msg:{
            age:10
        }
	};
	$.extend(targetObj,obj);
    //会覆盖targetObj原来的数据
})
```

```javascript
//浅拷贝把原来对象里面的复杂数据类型拷贝给目标对象
targetObj.msg.age = 20;
```

6.深拷贝，前面加true，完全克隆（拷贝对象，而不是地址），修改目标对象不会影响被拷贝对象

## 2.jQuery多库共存

#### 问题概述：

jQuery使用$作为标识符，随着jQuery的流行，其他js库也会用这个$作为标识符，这样一起使用会引起冲突

#### 客观需求：

需要一个解决方案，让jQuery和其他的js库不存在冲突，可以同时存在，这就叫做多苦共存

#### jQuery解决方案：

1.把里面的$符号，统一改为jQuery。比如jQuery("div")

2.jQuery变量规定新的名称：$.noConflict()	var xx = $.noConflict();

```javascript
var suibian = jQuery.noConflict();
suibian("div");
```

## 3.jQuery插件

jQuery功能比较有限，想要更复杂的特效效果，可以借助于jQuery插件完成

注意：这些插件也是依赖于jQuery来完成的，所以必须要先引入jQuery文件，因此也成为jQuery插件

### jQuery插件常用的网站：

1.jQuery插件库	www.jq22.com

2.jQuery之家	www.htmleaf.com

###  jQuery插件使用步骤：

1.引入相关文件。（jQuery文件和插件文件）

2.复制相关html、css、js（调用插件）

### jQuery插件演示：

1.瀑布流

2.图片懒加载（图片使用延迟加载可提高网页下载速度。他也能帮助减轻服务器负载）

​		当我们页面滑动到可视区域，再显示图片。

​		我们使用jQuery插件库EasyLazyload。注意，此时的js引入文件和js调用必须写到DOM元素（图片）最后面

3.全屏滚动（fullpage.js）

gitHub：https://github.com/alvarotrigo/fullPage.js

中文翻译网站：www.dowebok.com/demo/2014/77/

先引入jQuery，再引入fullpage

# bootstrap JS插件

bootstrap框架也是依赖于jQuery开发的，因此里面的js插件使用，也必须引入jQuery文件

# 案例：toDolist

1.文本框里面输入内容，按下回车，就可以生成待办事件

2.点击待办事项复选框，就可以把当前数据添加到已完成事项里面

3.点击已完成事项复选框，就可以把当前数据添加到待办事项

4、但是本页面内容刷新页面不会丢失



## 分析

1.刷新页面不会丢失，因此需要使用到本地存储localStorage

2.核心思路：不管按下回车，还是点击复选框，都是把本地存储的数据加载到页面中，这样保证刷新关闭页面不会丢失数据

3.存储的数据格式：`var todolist = [{title : 'xxx' ,done : falses}]`

```javascript
<script>
	var todolist = [{
		title:'我今天吃了八个馒头',
		done:false
	},{
		title:'我今天学习jq',
		done:false
	}];
	localStorage.setItem("todo",todolist);
	//Apption里面显示object
	//1.因为本地存储里面只能存储字符串的数据格式，
	//把我们的数组对象转换为字符串格式，JSON.stringify()
	localStorage.setItem("todo",JSON.stringify(todolist));
	var data = localStorage.getItem("todo");
	console.log(typeof data);//是string
	console.log(data[0].title)//没有
	//2.获取本地存储的数据，
	//需要把字符串转换为对象格式，JSON.parse()
	data = JSON.parse(todo);
	console.log(data);
</script>
```

4.注意点1：因为本地存储里面只能存储字符串的数据格式，把我们的数组对象转换为字符串格式，JSON.stringify(data);

5.注意点2：获取本地存储的数据，需要把字符串转换为对象格式，JSON.parse()，才能使用数据

## toDoList按下回车把新数据添加到本地存储里面

1.切记：页面中的数据，都要从本地存储里面获取，这样刷新页面不会丢失数据，所以要先把数据保存到本地存储里面

2.利用事件对象.keyCode判断用户按下回车键（13）

3.声明一个数组，保存数据

4.先要读取本地存储原来的数据（声明函数（）），放到这个数组里面

5.之后把最新从表单获取过来的数据，追加到数组里面

6.最后把数组存储给本地存储（声明函数savaDate（））
