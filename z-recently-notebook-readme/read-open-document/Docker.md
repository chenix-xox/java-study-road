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

  - -d：后台启动

  - --name：容器起名

  - -p [外部端口]:[容器内部端口]：端口映射

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

