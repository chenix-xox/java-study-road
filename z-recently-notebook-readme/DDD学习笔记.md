## MVC工程

- **common**

- **dao**

- **domain**

- **service**

- **web**

  **应用启动**的位置，但包含了各种**配置、监听、启动、接口、任务**...多种内容

  而在DDD中，单独使用一个app层存放应用启动的文件
  
  拆到DDD中，分成了 app、api、trigger 个位置



## DDD工程

- **api**

  对外接口，统一提供参数标准(如封装的rpc包，及dto参数...)

- **app（应用启动层）**

  你就应该只负责工程的 **应用启动（Application.java） & 配置（config）** 

  过去在mvc架构中启动、配置、接口、监听、任务...都放在web中，耦合度过高

- **domain（领域层）**

  包含当前系统自身的业务实现（IXxxService & XxxServiceImpl）

  涉及到外部系统的，依照依赖倒置的思想，

  告诉其他地方我需要啥（IXxxService），在**基础设施层**实现对**外部交互**的具体业务逻辑

  包含业务相关的实体（请求参数，响应参数，均定义为实体 XxxEntity）

  

  依赖倒置的思想，domain层和外部的交互通过调用infrastructure层的 port（RPC、APP..） & repository（仓储）

  根据领域所需的对象创建entity，可能和其他层有重复，但不会百分百重复，随着需求迭代和增加，细分的对象全部都有差距

  比如和MVC的后台架构对比举例，从头到尾，一个对象贯穿了crud、从controller -> service -> mapper的全部映射

  DDD中，domain中包含entity作为数据载体，在领域之间传递属性。valObj标识不可变的值对象常量等。aggregate聚合根聚合多个entity和valobj形成一个完整的业务单元，跨层传输使用（domain->infrastructure），当然也可以使用entity

- **infrastructure（基础设施层）**

  调外部、操作数据库、Redis... 都是操作外部，都在基础设施（原本都在service）

  DAO（数据库交互，包含PO与数据库字段一一对应的实体）

  adapter适配器，包含port和仓储，作为一个中间通道，适配两端数据交互的中间一层

  gateway做和外部系统交互的内容，如rpc、其它系统api等。DTO做和其它系统交互的实体

- **trigger（触发层）**

  有点抽象，就是所有的程序，调用我，都算是一种触发

  触发我的，放在这里。（触发的方式，可能是任何方式。mq、http、rpc...）

  像曾经的controller接口的实现、listener、job...全部在本层（触发层）

  而像rpc那种代理形式的打包，全部丢在api层去暴露（避免将本层与接口无关的内容也提供出去了）

- **types**

  SDK资源、公共资源、枚举、异常定义...类似MVC中的common 基础的

六边形架构、菱形架构



**实体对象与值对象？**

- 改变数据库、缓存、配置、持久化等操作的，称之为实体对象
- 没有用户身份、角色、没有唯一ID的，也不改变数据库与缓存的则为值对象