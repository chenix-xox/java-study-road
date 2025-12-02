## 特别鸣谢-参考文档（24.04.02）

[前言 | Docker — 从入门到实践 (gitbook.io)](https://yeasy.gitbook.io/docker_practice)



## 获取镜像

**格式：**`docker pull [选项] [Docker Registry 地址[:端口号]/]仓库名[:标签]`

### 获取

```bash
# 获取指定镜像
docker pull ubuntu:18.04
```

结果如下 👇

```bash
$ docker pull ubuntu:18.04
18.04: Pulling from library/ubuntu
92dc2a97ff99: Pull complete
be13a9d27eb8: Pull complete
c8299583700a: Pull complete
Digest: sha256:4bc3ae6596938cb0d9e5ac51a1152ec9dcac2a1c50829c74abd9c4361e321b26
Status: Downloaded newer image for ubuntu:18.04
docker.io/library/ubuntu:18.04
```



### 运行

```bash
# 运行指定服务
docker run -it --rm ubuntu:18.04 bash
```

结果如下 👇

```bash
$ docker run -it --rm ubuntu:18.04 bash

root@e7009c6ce357:/# cat /etc/os-release
NAME="Ubuntu"
VERSION="18.04.1 LTS (Bionic Beaver)"
ID=ubuntu
ID_LIKE=debian
PRETTY_NAME="Ubuntu 18.04.1 LTS"
VERSION_ID="18.04"
HOME_URL="https://www.ubuntu.com/"
SUPPORT_URL="https://help.ubuntu.com/"
BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"
PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"
VERSION_CODENAME=bionic
UBUNTU_CODENAME=bionic
```



### 退出

执行命令：`exit`



## 列出镜像

```bash
# 列出镜像
docker image ls
```



## 删除本地镜像

```bash
# 删除镜像
docker image rm [镜像名/镜像ID(仅前三位即可，除非前三位完全相同)]
```

结果如下 👇

```bash
$ docker image rm 5a2

Untagged: ubuntu:18.04
Untagged: ubuntu@sha256:0fedbd5bd9fb72089c7bbca476949e10593cebed9b1fb9edf5b79dbbacddd7d6
Deleted: sha256:5a214d77f5d747e6ed81632310baa6190301feeb875cf6bf9da560108fa09972
Deleted: sha256:824bf068fd3dc3ad967022f187d85250eb052f61fe158486b2df4e002f6f984e
```



### 通过docker image ls配合

```bash
$ docker image rm $(docker image ls -q redis)
```



## 在容器中启动nginx进行测试

> - 5000:80，意为将容器的80端口映射到本地部署docker机的5000端口
> - 一般来说是直接80:80，由于我本机的80端口正在使用，因此换成了5000端口
> - 不用提前pull镜像，docker run 发现没有的时候 会自动下载

```bash
docker run --name webserver -d -p 5000:80 nginx
```



## 查看容器运行

```bash
docker ps -a
```

运行结果如下 👇

```bash
$ docker ps -a
CONTAINER ID   IMAGE         COMMAND                  CREATED         STATUS                   PORTS                  NAMES
db738af6564d   nginx         "/docker-entrypoint.…"   3 minutes ago   Up 3 minutes             0.0.0.0:5000->80/tcp   webserver
d45f87a17283   hello-world   "/hello"                 4 hours ago     Exited (0) 4 hours ago                          silly_boyd
```



## 进入容器，操作文件

```bash
$ docker exec -it webserver bash
root@3729b97e8226:/# echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
root@3729b97e8226:/# exit
exit
```

这样就进入了webserver的容器内，改动了nginx欢迎界面的内容



## 查看容器改动

```bash
$ docker diff webserver
```







## 重新起航（25.01.12）

### 特别鸣谢-参考视频

尚硅谷3小时速通Docker教程 https://www.bilibili.com/video/BV1Zn4y1X7AZ



## Docker卸载

```bash
# 查看版本
docker --version

# 卸载
yum remove docker \
    docker-client \
    docker-client-latest \
    docker-common \
    docker-latest \
    docker-latest-logrotate \
    docker-logrotate \
    docker-selinux \
    docker-engine-selinux \
    docker-engine \
    docker-ce
```



## Docker安装（CentOS7）

```bash
sudo yum update -y
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum list docker-ce --showduplicates | sort -r
# 默认最新版
yum -y install  docker-ce docker-ce-cli containerd.io
```



## 镜像操作

- **检索**：`docker search`
- **下载**：`docker pull`
  - 指定版本：`docker pull nginx:latest`
  - 在dockerHub搜索镜像，找到指定版本下载
- **列表**：`docker images` = `docker image ls`
- **删除**：`docker rmi`



## 容器操作

<u>**注意：** 操作命令后面接的名称，必须是容器名称，而不是镜像名称。也可以用容器编号，一般前三位即可</u>

- **运行** ：`docker run`

  - 直接 docker run nginx，会前台一直跑应用，一旦ctrl+c，应用也停了。**（阻死行为）**

  - `-d`：后台启动

  - `--name`：容器名称

  - `-p [外部端口]:[容器内部端口]` ： 端口映射

  - `-v [卷名/目录路径/文件路径]:[容器内部目录路径or文件路径]` ： 容器内部内容 **挂载or卷映射** 到外部

  - `--network [docker网络名称]` ： 容器应用启动时加入到指定的docker网络中

  - `-e [环境变量名]=[环境变量值]` ： 设置容器的环境变量信息

  - ```bash
    -- 示例命令解释
    -- 使用nginx镜像，后台启动一个名为ngtest的容器，并将其80端口暴露给主机的81端口使用
    docker run -d --name ngtest -p 81:80 nginx
    ```

  - ```bash
    -- mysql启动示例（需先创建 /home/mysql/{conf,data,log,mysql-files} ，并创建 my.cnf）
    -p表示端口映射
    --restart=always表示容器退出时总是重启
    --name表示容器命名
    --privileged=true表示赋予容器权限修改宿主文件权利
    -v /home/mysql/log:/var/log/mysql表示容器日志挂载到宿主机
    -v /home/mysql/data:/var/lib/mysql表示容器存储文件挂载到宿主机
    -v /home/mysql/conf/my.cnf:/etc/mysql/my.cnf表示容器配置文件挂载到宿主机
    -v /home/mysql/conf:/etc/mysql表示容器配置文件夹挂载到宿主机
    -v /home/mysql/mysql-files:/var/lib/mysql-files (mysql 8.0.23安装需要)
    -e MYSQL_ROOT_PASSWORD=a12bCd3_W45pUq6表示设置mysql的root用户密码,建议用强密码
    -d 表示后台运行
    
    docker run \
    -p 3306:3306 \
    --restart=always \
    --name mysql \
    --privileged=true \
    -v /home/mysql/log:/var/log/mysql \
    -v /home/mysql/data:/var/lib/mysql \
    -v /home/mysql/conf:/etc/mysql \
    -e MYSQL_ROOT_PASSWORD=a12bCd3_W45pUq6 \
    -d mysql:8.3.0  
    
    -- 开放外网连接
    DROP USER 'root'@'localhost';
    CREATE USER 'root'@'%' IDENTIFIED BY '123456';
    GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
    
    -- 修改密码验证插件
    ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
    FLUSH PRIVILEGES;
    ```

    

- **查看** ：`docker ps` （查看所有运行中的容器）

  - -a：查看所有容器，包含不在运行中的

- **停止** ：`docker stop`

- **启动** ：`docker start`

- **重启** ：`docker restart`

- **状态** ：`docker stats`

- **日志** ：`docker logs`

- **进入** ：`docker exec`

  - `-it` 交互模式
  - 以控制台的方式，与mysql容器交互：`docker -it mysql /bin/bash`

- **删除** ：`docker rm`

  - docker rm -f [编号/名称] = 强制删除

### TODO

容器启动后，如何将忘记挂载的目录，重新挂载？



## 保存镜像

- **提交：** docker commit
  - docker commit -m "提交信息..." [容器] [打包的镜像名称:v版本]
  - 将xxx容器，打包为xxx镜像
- **保存：** dokcer save
  - docker save -o [文件名称（含后缀）] [镜像名称:版本号]
  - 将xxx镜像，保存为一个具体的文件
- **加载：** dokcer load
  - docker load -i [文件名称]
  - 加载保存的镜像文件，加载为镜像（docker images）



## 分享镜像

- **登录：** docker login
  - 登录dockerhub的账号密码
- **命名：** docker tag
  - docker tag [镜像名称:版本号] [上传的命名:版本号]
  - 上传最新版，最好不仅上传版本号的，还要上传lastest
- **推送：** docker push
  - docker push [上传的命名:版本号]



## 存储 - 目录挂载

> **小tips**
>
> docker rm $(docker ps -aq)
>
> - docker ps -aq: 列出所有容器id
> - 因此，如上命令的意思是，删除所有容器（如果存在运行中的，使用docker rm -f 强制删除即可）

不使用目录挂载的话，docker容器挂掉了或者炸了，数据配置丢失无法找回

因此需要将容器的文件挂载到本地

### 如何使用

docker run 命令中，添加 -v 参数

如： -v **[外部主机文件or目录]**:[容器内文件or目录]

如果外部主机，不存在对应的文件or目录，将自动创建

**注意：** 挂载外部主机文件的时候，容器内的文件将会同步生成。

也就是说，如果原本会生成的默认文件夹or默认文件的内容，都会根据外部主机的内容来（外部主机的新文件[夹]为空，那么容器内的文件[夹]也为空）



## 存储 - 卷映射

### 如何使用

docker run 命令中，添加 -v 参数

如： -v **[卷名]**:[容器内的目录]

不以 / 或 ./ 开始的名称，就表示 '卷'，docker会自动创建一个存储位置

将容器内部的初始化内容，与该存储位置保持一致

### 卷位置

docker统一的 卷存储位置：`/var/lib/docker/volumes/<volume-name>`

与其_data文件夹下内容一致

### 卷操作

- **查看：** docker volume ls
- **创建：** docker volume crea  te [卷名称]
- **查看卷详情：** docker volume inspect [卷名称]
  - 可以查看位置



## 网络 - 自定义网络

> - 轻松构建集群

个人可以考虑到的方式就是，容器1直接通过机器的IP，去访问容器2的内容（都暴露了端口到外部主机）

这就类似，你和同桌要说话，却要走出班级，再进来和他说话...



### docker网络机制

docker安装完毕后，使用ifconfig可以看到一个名为docker0的网卡

docker启动的应用，都加入了docker0的网络环境中

每个应用，docker都会分配IP



### 查看容器细节

- docker container inspect [容器名称]
  - 可以查看到，docker0的ip，是其网关地址（gateway）
  - docker为其分配的ip再IPAddress字段上



此时，发现docker启动的应用，共用同一个网关

那么容器1，想访问容器2的网络，可以指 访问容器2的 IP+容器2的端口（不是暴露到主机的端口，而是应用端口）



因为docker0，不支持通过外部主机域名进行访问

需要我们创建一个自定义网络后，容器名 = 稳定的域名

创建容器应用时，加入到对应的自定义网络中，就可以实现容器之间通过域名相互访问

**如：** 我创建了一个 mynet 的自定义网络

容器1和容器2启动时，都 --network mynet

此时容器1 想访问容器，就可以直接 访问 http://[容器2名称]:[容器端口]



### 自定义网络操作

- **创建自定义网络：** docker network create [自定义网络名称]
- **查看自定义网络：** docker network ls



## 网络 - Redis主从同步集群

> #### **需求**
>
> - **注意：官方主从配置较为麻烦，此处演示使用 bitnami的redis集群docker镜像**
> - **直接通过修改环境变量的方式实现集群功能，官方需修改配置文件**
>
> 创建redis01 & redis02，全部加入到同一个自定义网络 mynet 中
>
> 实现读写分离，写操作由redis01执行，读操作由reids02来执行、
>
> 分别暴露到外部主机的 6379 & 6380 端口
>
> 并将其/bitnami/redis/data 数据目录，分别挂载到外部主机的 /app/rd1 和 /app/rd2 目录下

### 创建redis01

```bash
docker run -d -p 6379:6379 \
-v /app/rd1:/bitnami/redis/data \
-e REDIS_REPLICATION_MODE=master \
-e REDIS_PASSWORD=123456 \
--network mynet --name redis01 \
bitnami/redis
```



### 创建Redis02

```BASH
docker run -d -p 6380:6379 \
-v /app/rd2:/bitnami/redis/data \
-e REDIS_REPLICATION_MODE=slave \
-e REDIS_MASTER_HOST=redis01 \
-e REDIS_MASTER_PORT_NUMBER=6379 \
-e REDIS_MASTER_PASSWORD=123456 \
-e REDIS_PASSWORD=123456 \
--network mynet --name redis02 \
bitnami/redis
```

redis02需注意，绑定主redis的host时，

如果是在同一个docker自定义网络下，直接使用主redis的容器名称即可

并且，主从密码需一致



## Docker Compose

> 批量管理容器

创建一个compose.yaml文件，里面包含若干个容器信息

**上线（将配置文件中的容器批量上线）：** docker compose up -d

**下线：** docker compose down

上线 与 启动的区别：

- 上线=第一次创建应用并启动
- 启动=之前启动过，暂时停止了，再启动

**指定启动a、b：** docker compose start a b 

**指定停止a、b：** docker compose stop a b 

**扩容：** docker compose scale a=3

- 解释：如果已经有a容器启动了，此时还会再启动2个a容器



## DockerCompose安装wordpress

### 以前的操作

1. 创建自定义网络

   `docker network create blog`

2. 创建数据库容器

   ```bash
   docker run \
   -p 13307:3306 \
   --restart=always \
   --name mysql_wordpress \
   --network blog \
   -v mysql-data:/var/lib/mysql \
   -v /app/myconf:/etc/mysql/conf.d \
   -e MYSQL_ROOT_PASSWORD=123456 \
   -e MYSQL_DATABASE=wordpress \
   -d mysql:8.0  
   ```

3. 创建wordpress博客系统

   ```bash
   docker run -d -p 8080:80 \
   -e WORDPRESS_DB_HOST=mysql \
   -e WORDPRESS_DB_USER=root \
   -e WORDPRESS_DB_PASSWORD=123456 \
   -e WORDPRESS_DB_NAME=wordpress \
   -v wordpress:/var/www/html \
   --restart always --name wordpress-app \
   --network blog \
   wordpress:latest
   ```



### 现在的方法 - 编写compose.yaml文件

> - 顶级元素
>    - name 名字（本次部署的应用名称）
>    - services 服务
>    - networks 网络
>    - volumes 卷
>    - configs 配置
>    - secrets 密钥
>
> **一般使用前四个即可**

#### 示例

```yaml
name: blog_test # 本次部署服务的应用名称
services: # 要启动的服务信息
  mysql_wordpress:  # 应用名称（随便写）
    container_name: mysql_wordpress # 容器名称，等同于 --name mysql_wordpress（不写默认按应用名称来）
    image: mysql:8.0 # 使用的镜像
    ports:  # 端口（可绑定多个）等同于 -p 13308:3306
      - "13307:3306"
    environment:  # 环境变量
      - MYSQL_ROOT_PASSWORD=123456 # 等同于 -e MYSQL_ROOT_PASSWORD=123456
      - MYSQL_DATABASE=wordpress # 等同于 -e MYSQL_DATABASE=wordpress
    volumes:  # 卷映射 & 路径映射
      - mysql-data:/var/lib/mysql # 卷映射需在顶级元素下声明，此处等同于 -v mysql-data:/var/lib/mysql
      - /app/myconf:/etc/mysql/conf.d # 等同于 -v /app/myconf:/etc/mysql/conf.d
    restart: always # 等同于 --restart always
    networks: # 网络
      - blog # 等同于 --network blog
  wordpress:  # 应用名称（随便写）
    container_name: wordpress-app
    image: wordpress:latest # 使用的镜像
    ports:
      - "8080:80"
    environment:
      - WORDPRESS_DB_HOST=mysql_wordpress
      - WORDPRESS_DB_USER=root
      - WORDPRESS_DB_PASSWORD=123456
      - WORDPRESS_DB_NAME=wordpress
    volumes:
      - wordpress:/var/www/html
    restart: always
    networks:
      - blog
    depends_on:
      - mysql_wordpress # mysql先启动，再启动此项

volumes:
  mysql-data: # 此处还能添加配置
  wordpress:

networks: # 类似 docker network create blog
  blog:

```



编写完毕后的 compose.yaml文件，直接上线

`docker compose -f compose.yaml up -d`

-f 指定文件名称，否则默认compose.yaml

**结果如下：**

```bash
[root@xxx tmp]# docker compose -f ./compose.yaml up  -d 
[+] Running 5/5
 ✔ Network blog_test_blog	      Created	0.0s
 ✔ Volume "blog_test_wordpress"   Created	0.0s
 ✔ Volume "blog_test_mysql-data"  Created   0.0s
 ✔ Container mysql_wordpress      Started   0.3s 
 ✔ Container wordpress-app        Started   0.7s 
```



## Docker Compose 其他

- 已经运行的compose.yaml，修改其中的部分配置，使用 docker compose up 重新启动

  被修改配置的那一个应用将会被 restart ，不影响compose.yaml文件内其他未修改的应用

- 使用 docker compose down 移除容器的时候，不会移除 卷（volume）

  下次启动相同compose.yaml时，依旧绑定原本的卷

  加入参数，移除容器的同时，移除镜像和卷

  `docker compose -f compose.yaml down -rmi -v`



## DockerFile - 构建自定义镜像

| 常见指令   | 作用               |
| ---------- | ------------------ |
| FROM       | 指定镜像基础环境   |
| RUN        | 运行自定义命令     |
| CMD        | 容器启动命令或参数 |
| LABEL      | 自定义标签         |
| EXPOSE     | 指定暴露端口       |
| ENV        | 环境变量           |
| ADD        | 添加文件到镜像     |
| COPY       | 复制文件到镜像     |
| ENTRYPOINT | 容器固定启动命令   |
| VOLUME     | 数据卷             |
| USER       | 指定用户和用户组   |
| WORKDIR    | 指定默认工作目录   |
| ARG        | 指定构建参数       |

相当于是构建了一套自己自定义的操作系统，里面包含哪些环境，默认有哪些文件...

比起直接保存镜像然后分享，这个更像是从头自定义一套镜像



## Docker Compose 一键启动开发环境（常见中间件）



## 常见安装记录

### 前情提要

- 配置daemon.json
- 国内镜像被墙，按如下配置还是在用原始镜像地址取pull...

```bash
sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://t3vham8h.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload
sudo systemctl restart docker
```



### FRPS 0.61.2

带前缀pull吧...