# wework-msgaudit

企业微信-会话内容存档     
实时拉取企业微信聊天记录java版SDK

### 功能

- [x] 多企业
- [x] 音频转码
- [x] 自动分表

### 安装

```shell script
mvn install
```

### 配置

- mysql配置

```shell script
src/main/java/com/php127/wework/DataSource.java
```

- 把动态库拷贝到系统

```shell script
cp ./lib/libWeWorkFinanceSdk_Java.so /usr/lib/libWeWorkFinanceSdk_Java.so
```

### 数据表

- 运行后由程序自动创建数据表
- corplist 企业表 运行第一次后,再手工添加一个企业配置,注意:添加服务器白名单   
- message_xxxx 聊天记录分表 每个企业一个表

### 编译

```shell script
mvn compile
```

### 打包

```shell script
mvn package
```

### 运行

```shell script
java -jar target/wework-msgaudit-1.0.jar
```

### 进程守护

使用 supervisord 进行守护 请见 supervisord 文件夹      
work.ini 为java进程守护 运行目录与日志等请自行修改    
resert.ini 为重启进程守护 配合 /bin/restart.sh 进行使用      
/bin/restart.sh 是一个redis队列,当有企业变动时,推送队列到redis来重启进程      


### 使用docker运行

- Dockerfile
```shell script

FROM daocloud.io/library/java
COPY ./ /app/wework
COPY ./lib/libWeWorkFinanceSdk_Java.so /usr/lib/libWeWorkFinanceSdk_Java.so
WORKDIR /app/wework
RUN java -jar target/wework-msgaudit-1.0.jar

```

- docker.sh

```shell script

docker build -t wework .
docker run -it --rm --net=net --name wework-msgaudit wework
docker rmi wework

```
###  常见问题

- 无法运行?

请注意运行环境,必需在linux下,并且将 `libWeWorkFinanceSdk_Java.so` 拷贝到 `/usr/lib/` 下

- 可以运行无法拉取?

是否将服务器ip添加到企业微信后台的白名单中,密钥等是否配置正确


###  参与贡献

1. fork 当前库到你的名下
2. 在你的本地修改完成审阅过后提交到你的仓库
3. 提交 PR 并描述你的修改，等待合并

###  License

[MIT license](https://opensource.org/licenses/MIT)
