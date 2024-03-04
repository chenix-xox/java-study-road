# 安装起始命令

> - git config --[global|system] -l
>   - global查看全局配置（自己配的，配置文件在C盘，只适用于当前电脑的登录用户）
>   - system查看系统配置（Git安装目录下的gitconfig）
> - git config --global user.name "xxx"
>   - 设置全局配置之user.name
>   - **user.name和user.email必须配置，作为用户识别标识**



# Git基本理论

## 工作区域

本地有三个工作区域：工作目录(Working Directory)、暂存区(Stage/Index）、资源库(Repository或Git Directory)。

加上远程的git仓库(Remote Directory)就可以分为四个工作区域。文件在这四个区域之间的转换关系如下：

<img src="D:\File\markdownPictures\image-20240223212544978.png" alt="image-20240223212544978" style="zoom:50%;float:left" />



### 工作区域名词解释

> - **（WorkSpace）工作目录：**项目代码的目录
> - **（Index / Stage）暂存区：**临时存放改动，只是一个文件，保存即将提交的文件列表信息
> - **（Repository）仓库区：**本地仓库，安全存放数据的地方，有提交的所有版本的数据。HEAD指向最新版本
> - **（Remote）远程仓库：**托管代码的服务器



## **本地  👉  远程**

> - **`git add files`**：**工作目录**  👉 **暂存区**
> - **`git commit`**：**暂存区** 👉 **本地仓库**
> - **`git push`**：**本地仓库** 👉 **远程仓库**



## **远程  👉  本地**

> - **`git pull`**：**远程仓库** 👉 **本地仓库**
> - **`git reset`**：**本地仓库** 👉 **暂存区**      (commit提交后的回滚)
> - **`git checkout`**：**暂存区** 👉 **工作目录**   (暂存区放回本地，检出)



# Git工作流程

1. 在工作目录中添加、修改文件
2. 将需要进行版本管理的文件放入暂存区
3. 暂存区域文件提交到git仓库

因此，git管理的文件有三种状态：已修改（modified）、已暂存（staged）、已提交（committed）



# Git项目搭建

## 仓库搭建

- 本地仓库搭建
  > 进入要作为仓库的目录
  >
  > $ **`git init`**
  >
  > 会生成**.git文件夹**

- 克隆远程仓库

  > #克隆一个项目和他的整个代码历史（版本信息）
  >
  > $ **`git clone [url]`**

  **注意：**克隆报错unable to get local issuer certificate

  运行`git config --global http.sslVerify false`

  

  

  

# Git文件操作

## 状态

- **Untracked**：没有进行任何git操作的文件
  - `git add`后变为**Staged**
- **Unmodify：**未改动状态（即仓库文件和本地文件一致）
  - 修改后变为**Modified**
  - `git rm`后变为**Untracked**
- **Modified：**文件已修改
  - `git add` 进入暂存，变为**Staged**
  - `git checkout` 放弃此次修改，变为**Unmodify**（是从本地仓库取出文件**覆盖**此次修改）
- **Staged：**暂存
  - `git commit`：同步到本地仓库，变为**Unmodify**（此时仓库文件再次和本地文件一致）
  - `git reset HEAD filename：`取消暂存，变为**Modified**（仓库文件与本地文件不一致）



### 查看文件状态

> #查看指定文件状态
>
> `git status [filename]`
>
> #查看所有文件状态
>
> `git status`



## 操作命令

- `git add .`：添加当前目录的所有文件到暂存区
- `git commit -m：`提交暂存区的东西到本地仓库
  - -m：提交信息，`-m "message"`
- `git reflog`：查看版本信息（精简）
- `git log`：查看详细版本信息



## 忽略文件

某些文件不想纳入版本控制，比如数据库文件，设计文件，临时文件..

主目录建立`.gitignore`文件，文件规则如下：

> - 忽略文件中的空行或以井号(#)开始的行将会被忽略
> - 可以使用Linux通配符。例如：星号(*)代表任意多个字符，问号(?)代表一个字符，方括号([abc])代表可选字符范围，大括号（{string1,string2,...})代表可选的字符串等
> - 如果名称的最前面有一个感叹号(!)，表示例外规则，将不被忽略
> - 如果名称的最前面是一个路径分隔符(/)，表示要忽略的文件在此目录下，而子目录中的文件不忽略
> - 如果名称的最后面是一个路径分隔符(/)，表示要忽略的是此目录下该名称的子目录，而非文件（默认文件或目录都忽略）

```bash
#为注释
*txt	#忽略所有	.txt结尾的文件
!lib.txt	#但lib.txt除外
/temp	#仅忽略项目根目录下的ToDo文件，不包括其它目录temp
build/	#忽略build/目录下的所有文件
doc/*.txt	#会忽略doc/notes.txt 但不包括doc/server/arch.txt
```



# 使用码云（Gitee）

1. 注册

2. 设置本机绑定SSH公钥，实现免密码登录

   ```bash
   # 进入 C:\Users\Administrator\.ssh 目录
   # 在git bash中输入如下命令，生成公钥，-t指定加密算法
   ssh-keygen -t rsa
   ```

3. 将公钥信息public key（.pub文件内容）添加到码云账户

4. 使用码云创建仓库

**ps：**自己搭建git服务器，使用GitLab



## 许可证

GPL-3.0



# IDEA集成Git

1. 新建项目，绑定git
   - 简单绑定：将远程仓库下载的文件拷贝放到新建的目录下面
2. 修改文件，使用IDEA操作git
3. 提交测试





# Git分支

- 主分支
- dev（开发分支）
- version（版本分支）
- ...

## 命令

- `git branch`：查看分支

  -r：查看远程分支

  -d [branch_name]：删除分支

  -v：查看分支版本

- `git branch [branch_name]`：新建分支命名为[branch_name]，但依然停留在当前分支

- `git checkout [branch_name]`：切换分支

  -b [branch_name]：新建分支，并切换到该分支 

- `git merge [branch]：`合并指定分支（branch）到当前分支



## 不同分支合并流程操作

假设场景：

克隆项目，默认存在master分支

创建dev分支，修改dev分支的项目文件（修改了2行代码），add commit push

**合并分支！**

切换到master分支，使用`git merge dev`，将dev分支合并到当前分支

合并显示插入2行，删除2行，即修改2行



**代码冲突分支合并**

产生冲突的原因：两个分支都对同一个文件的同一部分进行了不同的修改，Git无法决定替换掉哪一个，人为决定

例如：

master中：	hello = world；

dev中：	   hello = world1；

version1.0中：hello = world2；











## 注意事项

master主分支一般是非常稳定，用来发布新版本，不允许操作他工作。

一般新建dev分支进行工作，工作完成后，如果要发布，即dev分支稳定，可以合并到master分支







