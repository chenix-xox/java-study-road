## 特别鸣谢-参考文档

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

