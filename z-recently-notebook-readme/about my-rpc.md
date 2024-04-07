## RPC设计

>   **最简单的RPC：**
>
> - 服务提供端 Server 向注册中心注册服务
> - 服务消费者 Client 通过注册中心拿到服务相关信息
> - 然后再通过网络请求服务提供端 Server

### 所需

- **注册中心：**负责服务地址的注册与查找

  可使用的：Zookeeper、Nacos 或 Redis.. 甚至可以使用文件！

- **网络传输：**调用远程方法，就需要发送网络请求传递<u>目标的信息</u>到**服务提供端**

  > **可使用的：**
  >
  > Socket：Java中最原始最基础的网络通信方式，**阻塞IO**，性能低且功能单一
  >
  > NIO：同步非阻塞的IO模型，但麻烦
  >
  > **（推荐）**Netty：基于NIO的client-server（客户端服务器）框架	

- **序列化和反序列化：**网络传输数据必须是二进制的，因此需要将Java对象进行**序列化**；接收到二进制数据后，解析（**反序列化**）为Java对象

  > **可使用的：**
  >
  > （不推荐）实现`java.io.Serializable`接口（JDK自带）：不支持跨语言调用且性能较差
  >
  > 常用序列化方式：hessian、kryo（推荐）、protostuff

- **动态代理：** 我们给某一个对象提供一个代理对象，并由代理对象来代替真实对象做一些事情

  > **举个例子：**我们真实对象调用方法的时候，我们可以通过代理对象去做一些事情比如安全校验、日志打印等等。但是，这个过程是完全对真实对象屏蔽的。

  RPC的主要目的就是调用远程方法西像本地方法一样简单，不关心方法调用的细节，如网络传输..

  就可以用到动态代理，让代理对象来传输网络请求

- **负载均衡：**避免单个服务器响应同一个请求，造成服务器宕机、崩溃等问题

- **传输协议：**设计一个私有的传输协议，作为客户端和服务端交流的基础

  > 简单来说：**通过设计协议，我们定义需要传输哪些类型的数据， 并且还会规定每一种类型的数据应该占多少字节。这样我们在接收到二进制数据之后，就可以正确的解析出我们需要的数据。**这有一点像密文传输的感觉。
  >
  > 通常一些标准的 RPC 协议包含下面这些内容：
  >
  > 1. **魔数** ： 通常是 4 个字节。这个魔数主要是为了筛选来到服务端的数据包，有了这个魔数之后，服务端首先取出前面四个字节进行比对，能够在第一时间识别出这个数据包并非是遵循自定义协议的，也就是无效数据包，为了安全考虑可以直接关闭连接以节省资源。 
  > 2.  **序列化器编号** ：标识序列化的方式，比如是使用 Java 自带的序列化，还是 json，kryo 等序列化方式。 
  > 3. **消息体长度** ： 运行时计算出来。 
  > 4. ...

- **所需技术：** **<u>Netty + kryo + Zookeeper</u>**

  - **Java**

    动态代理机制；

    序列化机制以及各种序列化框架的对比，比如 hession2、kryo、protostuff；

    线程池的使用；

    `CompletableFuture` 的使用；

    ......

  - **Netty**

    使用 Netty 进行网络传输；

    `ByteBuf` 介绍；

    Netty 粘包拆包；

    Netty 长连接和心跳机制；

    ......

  - **Zookeeper**

    基本概念；

    数据结构；

    如何使用 Netflix 公司开源的 Zookeeper 客户端框架 Curator 进行增删改查；

    ......



## Socket网络通信

> -com.xxx
>
> ​	-remoting
>
> ​		-transport
>
> ​			-socket

### Socket套接字

可以通过Socket发送和接收数据

操作套接字和读写文件很像

套接字 Socket = `IP地址:端口号`

互联网通信至少需要一对套接字：

1. 服务端的Server Socket（java.net包 - ServerSocket类）
2. 客户端的Client Socket（java.net包 - Socket类）



### Socket网络通信过程

1. 开启服务端，监听客户端请求
2. 客户端请求，服务端与客户端建立连接
3. 两端之间传递数据
4. 关闭资源

**对应到服务端和客户端的话，是下面这样的。**👇

#### **服务器端：**

1. 创建 `ServerSocket` 对象并且绑定地址（ip）和端口号(port)：`server.bind(new InetSocketAddress(host, port))`
2. 通过 `accept()`方法监听客户端请求
3. 连接建立后，通过输入流读取客户端发送的请求信息
4. 通过输出流向客户端发送响应信息
5. 关闭相关资源

#### **客户端：**

1. 创建`Socket` 对象并且连接指定的服务器的地址（ip）和端口号(port)：`socket.connect(inetSocketAddress)`
2. 连接建立后，通过输出流向服务器端发送请求信息
3. 通过输入流获取服务器响应的信息
4. 关闭相关资源



### Socket网络通信实战

#### 实体类

```java
@Data
@AllArgsConstructor
public class Message implements Serializable {
    private String content;
}
```

#### 服务端

```java
public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) {
        //1.创建 ServerSocket 对象并且绑定一个端口
        try (ServerSocket server = new ServerSocket(port);) {
            Socket socket;
            //2.通过 accept()方法监听客户端请求
            while ((socket = server.accept()) != null) {
                logger.info("client connected");
                try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                   //3.通过输入流读取客户端发送的请求信息
                    Message message = (Message) objectInputStream.readObject();
                    logger.info("server receive message:" + message.getContent());
                    message.setContent("new content");
                    //4.通过输出流向客户端发送响应信息
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                } catch (IOException | ClassNotFoundException e) {
                    logger.error("occur exception:", e);
                }
            }
        } catch (IOException e) {
            logger.error("occur IOException:", e);
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}
```

#### 客户端

```java
public class HelloClient {

    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    public Object send(Message message, String host, int port) {
        //1. 创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //2.通过输出流向服务器端发送请求信息
            objectOutputStream.writeObject(message);
            //3.通过输入流获取服务器响应的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        HelloClient helloClient = new HelloClient();
        Message message = (Message) helloClient.send(new Message("客户端发消息来噜！"), "127.0.0.1", 6666);
        System.out.println("client receive message:" + message.getContent());
    }
}
```

#### 注意：

- `ServerSocket` 的 `accept()` 方法是阻塞方法，
- 也就是说 `ServerSocket` 在调用 `accept()`等待客户端的连接请求时会阻塞，
- 直到收到客户端发送的连接请求才会继续往下执行代码。

很明显，服务端的代码片段中有一个很严重的问题：**只能同时处理一个客户端的连接，如果需要管理多个客户端的话，就需要为我们请求的客户端单独创建一个线程。**

[<img src="https://s21.ax1x.com/2024/04/07/pFLkzQI.png" alt="pFLkzQI.png" style="zoom:50%;" />](https://imgse.com/i/pFLkzQI)

> 对应Java代码应该如下：
>
> ```java
> new Thread(() -> {
>    // 创建 socket 连接
> }).start();
> ```
>
> 但是，这样会导致一个很严重的问题：**资源浪费**。
>
> 我们知道线程是很宝贵的资源，如果我们为每一次连接都用一个线程处理的话，就会导致线程越来越多，最后达到了极限之后，就无法再创建线程处理请求了。处理的不好的话，甚至可能直接就宕机掉了。
>
> 很多人就会问了：那有没有改进的方法呢？
>
> 当然有！ 比较简单并且实际的改进方法就是使用**线程池**。线程池还可以让线程的创建和回收成本相对较低，并且我们可以指定线程池的可创建线程的最大数量，这样就不会导致线程创建过多，机器资源被不合理消耗。
>
> ```java
> ThreadFactory threadFactory = Executors.defaultThreadFactory();
> ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100), threadFactory);
> threadPool.execute(() -> {
>      // 创建 socket 连接
>  });
> ```
>
> **但是，即使你再怎么优化和改变。也改变不了它的底层仍然是同步阻塞的 BIO 模型的事实，因此无法从根本上解决问题。**
>
> **为了解决上述的问题，Java 1.4 中引入了 NIO ，一种同步非阻塞的 I/O 模型。** 由于使用同步非阻塞的 I/O 模型 **NIO** 来进行网络编程真的太麻烦了。你可以使用基于 NIO 的网络编程框架 Netty ，它将是你最好的选择

#### 使用新建线程or线程池示例代码

```java
public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Server started on port: " + port);
            while (true) {
                try {
                    Socket socket = server.accept();
                    logger.info("Client connected");

                    // 为每个客户端连接创建一个新的线程
                    /*new Thread(() -> {
                        handleClient(socket);
                    }).start();*/
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    ExecutorService threadPool
                        = new ThreadPoolExecutor(
                        10,
                        100,
                        1,
                        TimeUnit.MINUTES,
                        new ArrayBlockingQueue<>(100), threadFactory
                    );
                    threadPool.execute(() -> {
                        // 创建 socket 连接
                        handleClient(socket);
                    });
                } catch (IOException e) {
                    logger.error("IOException occurred while accepting connection:", e);
                }
            }
        } catch (IOException e) {
            logger.error("IOException occurred while creating ServerSocket:", e);
        }
    }

    private void handleClient(Socket socket) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            // 读取客户端发送的请求信息
            Message message = (Message) objectInputStream.readObject();
            logger.info("Server received message: " + message.getContent());
            // 修改消息内容并发送回客户端
            message.setContent("New content from server");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception occurred while handling client:", e);
        } finally {
            try {
                socket.close(); // 在线程结束时关闭Socket
                logger.info("Client disconnected");
            } catch (IOException e) {
                logger.error("Error closing socket:", e);
            }
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}
```

















## 学习过程中的记录

**serialVersionUID 有什么作用？**

- 序列化号 `serialVersionUID` 属于版本控制的作用。
- 反序列化时，会检查 `serialVersionUID` 是否和当前类的 `serialVersionUID` 一致。
- 如果 `serialVersionUID` 不一致则会抛出 `InvalidClassException` 异常。
- 强烈推荐每个序列化类都手动指定其 `serialVersionUID`，如果不手动指定，那么编译器会动态生成默认的 `serialVersionUID`。



**serialVersionUID 不是被 static 变量修饰了吗？为什么还会被“序列化”？**

- `static` 修饰的变量是静态变量，位于方法区，本身是不会被序列化的。
-  `static` 变量是属于类的而不是对象。
- 你反序列之后，`static` 变量的值就像是默认赋予给了对象一样，看着就像是 `static` 变量被序列化，实际只是假象罢了。



**如果有些字段不想进行序列化怎么办？**

对于不想进行序列化的变量，可以使用 `transient` 关键字修饰。

`transient` 关键字的作用是：阻止实例中那些用此关键字修饰的的变量序列化；

当对象被反序列化时，被 `transient` 修饰的变量值不会被持久化和恢复。

关于 `transient` 还有几点注意：

- `transient` 只能修饰变量，不能修饰类和方法。
- `transient` 修饰的变量，在反序列化后变量值将会被置成类型的默认值。例如，如果是修饰 `int` 类型，那么反序列后结果就是 `0`。
- `static` 变量因为不属于任何对象(Object)，所以无论有没有 `transient` 关键字修饰，均不会被序列化。