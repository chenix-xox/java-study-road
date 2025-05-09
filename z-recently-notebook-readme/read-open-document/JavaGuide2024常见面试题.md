## Java基础

- **Java 中的⼏种基本数据类型是什么？对应的包装类型是什么？各⾃占⽤多少字节呢？**

> 八种基本数据类型
>
> 整型：byte_1,short_2,int_4,long_8
>
> 浮点型：float_4,double_8
>
> 字符型：char_2
>
> 布尔型：boolean

- **String 、 StringBuffer 和 StringBuilder 的区别是什么? String 为什么是不可变的? **

> String是不可变的，StringBuffer和StringBuilder都是可变的。
>
> 因为String被final给修饰了，而StringBuffer和StringBuilder没有，他们都来自于一个公共的父类。
>
> StringBuffer和StringBuilder的区别，就是StringBuffer加了一个同步锁，是线程安全的，但同时也是性能稍差的。StringBuilder与之相反
>
> 在实际开发中，少量数据，直接使用String即可，最好是操作不频繁
>
> 如果是单线程，操作大量字符串数据，直接使用线程不安全的StringBuilder即可
>
> 多线程，就得考虑线程安全问题了，处理大量数据，用StringBuffer才对

- **String s1 = new String("abc"); 这段代码创建了⼏个字符串对象？**

> 可能是一个，也可能是两个
>
> 根据我的了解，jvm当中声明了一个叫字符串常量池的东西
>
> 每次创建字符串对象的时候，如果是常量池中没有的，就会创建两个字符串对象，一个放在常量池中去，一个在堆中
>
> 那如果字符串常量池中，有这个字符串，就只会创建一个字符串对象，只在堆中创建

- **== 与 equals?hashCode 与 equals ?**

> ==和equals的区别，==，对于基本数据类型，一般就是直接比较值，如果是引用数据类型，就会比较内存地址了，而equals适用于判断两个对象是否相等。
>
> 它存在于Object这个类中，而Object是所有类的父类，因此所有的类都有equals方法。
>
> 那么equals方法，也分为两种情况：
>
> 一是没有重写的，他比较对象，就跟 == 比较对象是一样的，比较的一般就是地址了
>
> 二是重写了equals方法的，他比较的就是两个对象中的属性是否相等。
>
> 重写equals要注意的就是必须重写hashCode方法
>
> 因为两个相等对象的，hashCode的值一定是相等的。
>
> 否则，就算两个对象，有相同的HashCode，也不一定其中的内容属性相等。

- **包装类型的缓存机制了解么？**

> ... 其中 整型包装类，有一个范围，是创建-127-128的缓存出局，Char是0-127，Boolean直接返回true or false
>
> Float和Double没有实现缓存机制
> 缓存机制的结果就是只要在缓存区将内就返回相同的对象，意味着，只要值相同，对象就相同

- **⾃动装箱与拆箱了解吗？原理是什么？**

> 自动装箱，就是将基本类型，通过对应的引用类型进行包装
>
> 比如： Integer i =  40；40就是一个基本数据类型，但我把他赋值给了Integer，就是进行了包装，也就是，自动装箱
>
> 那这个时候，我直接通过int n = i；就是把一个引用类型，赋值给了一个基本类型，他这个时候就会进行拆箱操作。
>
> Interg i = 40 等价于 Integer i = Integer.valueOf(40)
>
> int n = i 等价于 int n = i.intValue()。
>
> 频繁拆箱装箱很影响系统性能，要避免。

- **深拷⻉和浅拷⻉区别了解吗？什么是引⽤拷⻉？**

> 浅拷贝会在一个堆上，创建一个新对象，他会复制这个对象的引用地址
>
> 而深拷贝，会完全复制整个对象

- **谈谈对 Java 注解的理解，解决了什么问题？**
- **Exception 和 Error 有什么区别？**

> Exception是程序本身可以处理的异常，它可以通过catch来进行捕获
>
> 而Error是程序无法处理的异常，其实他也是能通过catch捕获的。只不过一般不建议
>
> 因为他的error一般都是需要线程终止的问题，比如Java虚拟机运行出错，还有虚拟机内存不够，之类的。

- **Java 反射？反射有什么缺点？你是怎么理解反射的（为什么框架需要反射）？**

> 反射就是赋予了我们在运行时，可以分析类，和执行类中方法的能力。
>
> 通过反射可以获得一个类的所有属性，和方法，随意调用
>
> 让我们的代码更加灵活，但同时也增加了安全问题，会无视泛型参数的安全检查
>
> 以及性能稍差
>
> 但对框架影响不大，以用作框架的动态代理了

- **Java 泛型了解么？什么是类型擦除？介绍⼀下常⽤的通配符？**

> 泛型，是用于提神代码的可读性和稳定性
>
> 编辑器可以进行泛型参数的检测。
>
> 必须按照泛型要求进行传参，传错会报错
>
> 一般可以用作泛型类、泛型接口、泛型方法
>
> 我个人会在项目中，使用EasyExcel，插入数据的时候，因为不确定数据的类型，会直接使用通配符，即<T>的泛型，动态指定数据类型

- **内部类了解吗？匿名内部类了解吗？**

> 内部类就是一个类里，又有一个class类

- **BIO,NIO,AIO 有什么区别?**

> 同步阻塞、同步非阻塞、异步



## Java集合

- **说说 List,Set,Map 三者的区别？三者底层的数据结构？** 

> List更像是一个数组，可以通过泛型，来规范放入的参数类型
>
> Set我个人更愿意将他理解成一个去重的List
>
> 以上两种，在Java集合内，是属于Collection父类的部分，都是直接存储元素的
>
> 而Map他是存储键值对，key和value的
>
> 三者的底层结构，List就是一个Object数组，子类还有LinkedList，是一个双向链表，在jdk1.6之前是循环链表，1.7就取消了
>
> Set虽然我个人认为是一个去重的List，但实际上，他的底层是基于HashMap来保存元素的。还有LinkedHashSet是基于LinkedHashMap
>
> Map分为HashMap和LinkedHashMap，Hashtable和TreeMap我不太了解，就不做赘述了
>
> HashMap底层主要是数组+链表组成，达到一个特定的阈值后，会将链表转换为红黑树
>
> LinkedHashMap，虽然我没有真正去了解其底层，应该是跟HashMap差不多的，但我会将它用作顺序 保存MyBatis的查询结果

- **有哪些集合是线程不安全的？怎么解决呢？**

> HashMap是线程不安全的，因为他扩容可能会导致死循环，和数据丢失。
>
> 比如：两个线程1，2同时进行put操作，线程1由于某些问题，被挂起，导致线程2先put，当线程1解决完问题，执行put，就会覆盖线程2的数据
>
> Hashtable，线程安全，但是有点重，建议使用ConcurrentHashMap，至于如何解决线程安全，了解不是很多，只知道concurrentHashMap在jdk1.7之前使用了分段锁，之后使用了别的方法..

- **⽐较 HashSet、LinkedHashSet 和 TreeSet 三者的异同** 

> 都是Set的实现类，能保证元素不重复，并且，线程都是不安全的
>
> 主要区别，就是底层数据结构的不同
>
> HashSet底层主要就是哈希表基于HashMap，LinkedHashSet底层就是链表+哈希表的方式。
>
> TreeSet是红黑树
>
> 由于数据结构的不同，因此使用场景也不同
>
> 比如HashSet可以用于不用保证元素插入和取出顺序的场景，而LinkedHashSet就可以用于保证元素插入和1取出顺序的场景
>
> TreeSet用于自定义排序规则

- **HashMap 和 Hashtable 的区别？HashMap 和 HashSet 区别？HashMap 和 TreeMap 区别？**

> HashMap和HashTable，前者线程不安全，后者线程安全，因为后者被synchronized修饰
>
> 由于保证了线程安全，因此Hashtable的性能不如HashMap
>
> 还有就是HashMap可以存null key 和 null value，Hashtable就不行
>
> -------------------
>
> HashMap和HashSet，HashSet底层就是基于HashMap实现的，但是hashMap存储的是键值对，hashSet仅仅存储对象元素。
>
> 然后他们添加元素的方式也不同，一个是add，一个是put
>
> 计算hashCode的方式也不同，一个是用key计算，一个是用整个成员对象来计算
>
> ---------------
>
> ...

- **HashMap 的底层实现** 
- **HashMap 的⻓度为什么是 2 的幂次⽅** 
- **ConcurrentHashMap 和 Hashtable 的区别？**

> 两者都是线程安全
>
> 前者在JDK1.7之前使用的是分段所的方式实现，后面改为了CAS操作代替分段锁。
>
> Hashtable主要是sychronized关键字保证线程安全的
>
> 多线程的操作下，concurrentHashMap的性能优于HashTable
>
> 一般不建议使用HashTable了，相对较慢

- **ConcurrentHashMap 线程安全的具体实现⽅式/底层具体实现**

> JDK1.8之前，使用分段锁，把数据分成一段一段的，给每一段配一把锁
>
> 当一个线程占用其中一段数据，占用了该数据的锁。也不影响其他数据段被访问
>
> JDK1.8之后几乎完全重写了ConcurrentHashMap方法，取消了分段锁
>
> 采用了Node+CAS+synchronized保证并发安全，数据结构类似HashMap，数组+链表,超过一定阈值从链表变为红黑树



## MySQL

### MySQL存储引擎

- MySQL ⽀持哪些存储引擎？默认使⽤哪个？
- MyISAM 和 InnoDB 有什么区别？

### MySQL事务

- 事务的四⼤特性了解么?
- 并发事务带来了哪些问题?不可重复读和幻读有什么区别？
- MySQL 事务隔离级别？默认是什么级别？
- MySQL 的隔离级别是基于锁实现的吗？
- InnoDB 对 MVCC 的具体实现

### MySQL 字段类型

- char 和 varchar 的区别是什么？ 
- varchar(100)和 varchar(10)的区别是什么？ 
- decimal 和 float/double 的区别是什么？存储⾦钱应该⽤哪⼀种？ 
- 为什么不推荐使⽤ text 和 blob？

### MySQL 索引

- ⭐MySQl有几种索引

- 为什么索引能提⾼查询速度?
- 聚集索引和⾮聚集索引的区别？⾮聚集索引⼀定回表查询吗?
- 索引这么多优点，为什么不对表中的每⼀个列创建⼀个索引呢？(使⽤索引⼀定能提⾼查
  询性能吗?)
- 索引底层的数据结构了解么？Hash 索引和 B+树索引优劣分析 5. B+树做索引⽐红⿊树好在哪⾥？
- 最左前缀匹配原则了解么？
- 什么是覆盖索引
- 如何查看某条 SQL 语句是否⽤到了索引？

### MySQL锁

- 表级锁和⾏级锁有什么区别？

- 哪些操作会加表级锁？哪些操作会加⾏级锁？请简单举例说⼀下。

- InnoDB 有哪⼏类⾏锁？

- 当前读和快照读有什么区别？

- MySQL 如何使⽤乐观锁和悲观锁？

  

  

### MySQL ⽇志

- MySQL ⽇志常⻅的⾯试题有： MySQL 中常⻅的⽇志有哪些？
- 慢查询⽇志有什么⽤？
- binlog 主要记录了什么？ redo log 如何保证事务的持久性？ ⻚修改之后为什么不直接刷盘呢？
- binlog 和 redolog 有什么区别？
- undo log 如何保证事务的原⼦性？
- ……







⭐redis有哪几种数据类型

Redis有以下几种常用的数据类型：

1. String（字符串）：最基本的数据类型，可以存储字符串、整数或浮点数。
2. Hash（哈希）：存储键值对的无序散列表。
3. List（列表）：按照插入顺序存储的字符串列表。
4. Set（集合）：无序且唯一的字符串集合。
5. Sorted Set（有序集合）：按照分数排序的字符串集合。
6. Bitmaps（位图）：可以进行位级操作的数据类型。
7. HyperLogLog（基数估算）：用于估算集合中不重复元素的数量。
8. Geospatial（地理空间）：存储地理位置信息的数据类型。

⭐热点排行榜使用Redis如何实现

使用Redis实现热点排行榜可以使用Sorted Set（有序集合）数据类型。具体实现步骤如下：

1. 将每个热点项作为Sorted Set中的一个成员，分数为热度值。
2. 当有新的热点项出现时，将其添加到Sorted Set中，并更新对应的热度值。
3. 当需要查询热点排行榜时，可以通过Sorted Set提供的命令获取指定范围内的成员，并按照分数排序返回。

⭐Spring生态组件 讲讲

Spring生态组件是指与Spring框架紧密集成的一系列组件，包括但不限于以下几个方面：

1. Spring Boot：用于简化Spring应用程序的开发和部署的框架，提供了自动配置、快速开发等特性。
2. Spring MVC：用于构建Web应用程序的MVC框架，提供了处理请求、路由、视图解析等功能。
3. Spring Data：用于简化数据访问层的开发，提供了对多种数据存储的支持，如关系型数据库、NoSQL数据库等。
4. Spring Security：用于提供身份验证和授权功能的安全框架，可以用于保护Web应用程序和REST API。
5. Spring Cloud：用于构建分布式系统的框架，提供了服务注册与发现、负载均衡、配置管理等功能。
6. Spring Integration：用于构建企业集成解决方案的框架，提供了消息传递、事件驱动等功能。

⭐Redis、Mysql数据类型 以及 优化策略

Redis和MySQL都有不同的数据类型和优化策略。

Redis的数据类型包括：String、Hash、List、Set、Sorted Set、Bitmaps、HyperLogLog、Geospatial等。每种数据类型都有不同的特点和适用场景。

MySQL的数据类型包括：整型、浮点型、字符型、日期时间型等。MySQL还支持存储大文本、二进制数据的BLOB类型。选择合适的数据类型可以提高数据库的性能和存储效率。

优化策略方面，Redis可以通过以下方式进行优化：

1. 合理选择数据结构和数据类型，根据实际需求选择最适合的数据类型。
2. 设置合理的过期时间，避免数据过期但一直占用内存。
3. 使用持久化机制，将数据保存到磁盘中，以防止数据丢失。
4. 使用集群模式，提高系统的可扩展性和容错性。

MySQL的优化策略包括：

1. 优化查询语句，使用合适的索引，减少全表扫描。
2. 避免使用SELECT *，只查询需要的字段。
3. 合理设计数据库结构，避免冗余和重复数据。
4. 配置合适的缓存大小和缓存策略，减少IO操作。
5. 定期清理无用数据，减少数据库的负担。

⭐Mysql优化

MySQL的优化可以从多个方面进行，包括但不限于以下几个方面：

1. 优化查询语句：使用合适的索引、避免全表扫描、减少查询字段等。
2. 优化数据库结构：合理设计表结构、避免冗余和重复数据、使用合适的数据类型等。
3. 配置合适的缓存：设置合适的缓存大小和缓存策略，减少IO操作。
4. 使用分区表：将大表按照某个字段进行分区，提高查询效率。
5. 优化数据库连接池：合理配置连接池大小、超时时间等参数。
6. 定期清理无用数据：删除过期数据、清理日志文件等，减少数据库负担。
7. 监控和调优：使用工具进行数据库性能监控和调优，及时发现和解决性能问题。

⭐SpringBoot自动装配

Spring Boot自动装配是指Spring Boot根据应用程序的依赖关系自动配置相应的Bean和组件，减少了开发者的配置工作。通过自动装配，开发者可以更快速地搭建和部署Spring Boot应用程序。

Spring Boot的自动装配是基于条件注解和自动配置类实现的。条件注解用于根据条件判断是否需要加载某个Bean或组件，自动配置类则定义了一系列自动配置的规则和逻辑。

开发者可以通过在应用程序中引入相关的依赖，Spring Boot会自动检测并根据依赖和条件进行相应的自动配置。如果需要对自动配置进行定制，可以通过编写自定义的配置类或使用属性配置来覆盖默认的自动配置。

⭐单机项目，使用Redission读写锁方案，保证数据强一致性

在单机项目中使用Redisson读写锁可以实现数据的强一致性。具体实现步骤如下：

1. 引入Redisson依赖，并进行相应的配置。
2. 在需要保证数据强一致性的代码块中，使用Redisson的读写锁进行加锁和解锁操作。
3. 在读操作前先获取读锁，读操作完成后释放读锁。
4. 在写操作前先获取写锁，写操作完成后释放写锁。
5. 通过读写锁的机制，保证同一时间只有一个线程能够进行写操作，避免数据的并发修改问题。

⭐查询本科树结构专业，使用最小生成树方案

如果要查询本科树结构专业中的最小生成树，可以使用Prim算法或Kruskal算法来实现。

Prim算法步骤如下：

1. 选择一个起始顶点作为树的根节点。
2. 将该顶点加入到最小生成树中。
3. 从与最小生成树相连的边中选择权重最小且未被访问过的边，将其加入到最小生成树中。
4. 重复步骤3，直到最小生成树包含了所有顶点。

Kruskal算法步骤如下：

1. 将图中的所有边按照权重从小到大排序。
2. 依次选择权重最小的边，如果该边的两个顶点不在同一棵树中，则将该边加入到最小生成树中。
3. 重复步骤2，直到最小生成树包含了所有顶点。

以上两种算法都可以找到本科树结构专业中的最小生成树，具体选择哪种算法取决于实际情况和需求。

















