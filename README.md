# wework-msgaudit

企业微信-会话内容存档       
实时拉取企业微信聊天记录java版SDK    
[官方文档](https://open.work.weixin.qq.com/api/doc/90000/90135/91774)

### 功能

- [x] 多企业
- [x] 音频转码
- [x] 自动分表

### 要求

1. jdk >= 8.0
2. mvn
3. mysql >= 5.6

### 安装

```shell script
mvn install
```

### 配置

- mysql配置

```shell script
src/main/java/com/php127/wework/DB.java
```

- 把动态库拷贝到系统

```shell script
cp ./lib/libWeWorkFinanceSdk_Java.so /usr/lib/libWeWorkFinanceSdk_Java.so
```

## 数据

### 数据表

- 运行后由程序自动创建数据表
- corplist 企业表 运行第一次后,再手工添加一个企业配置 (注意:添加服务器白名单)  
- message_xxxx 聊天记录分表 一个企业一个表

#### 企业表 ( corplist )

|字段|说明|
|---|---|
|id|主健(自增id)|
|corpid|企业id|
|secret|会话存档secret|
|corpname|企业名称|
|prikey|私钥 注意:去掉头尾|
|limits|一页拉取条数|
|timeout|拉取超时(秒)|
|status|状态|
|update|是否更新|

> 注意: 私钥请去掉头尾,注意格空

#### 聊天记录表 ( message_wwbxxxxxx7aed6f )

|字段|说明|
|---|---|
|id|主健(自增id)|
|msgid | 消息ID|
|publickey_ver | 密钥版本|
|seq | 消息序号,最大值为2^64 -1|
|action |消息动作,目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型|
|msgfrom |消息发送方id(同一企业内容为userid,非相同企业和机器人消息均为external_userid)|
|tolist |消息接收方ID列表(多个接收ID以逗号分隔)|
|msgtype |消息类型|
|msgtime |消息发送时间戳(utc时间,ms单位)|
|text |文本消息|
|sdkfield |附件ID|
|msgdata |原始消息数据 json格式|
|status |1.未加载媒体/2.正在加载媒体/3.媒体加载完成/4.媒体加载失败|
|media_code |媒体错误码|
|media_path |媒体文件路径|
|roomid |群聊消息的群id(如果是单聊则为空)|
|created |创建时间|

#### 媒体文件

聊天中产生的图片,语音,视频,doc文档等文件 默认下载到 `msgfile/coprid_name` 目录下   
您可以在nginx上添加一个静态站点指向 `msgfile` 目录 即可访问资源

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
java -jar target/wework-msgaudit-2.0.jar
```

### 进程守护

使用 supervisord 进行守护 请见 supervisord 文件夹  
详细配置请参考 [supervisord官方文档](http://supervisord.org/)   
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
- 注意,注意,注意,重要的事说三遍?

```text
  请不要在mac下运行,libWeWorkFinanceSdk_Java.so是linux环境的   
  请不要在mac下运行,libWeWorkFinanceSdk_Java.so是linux环境的   
  请不要在mac下运行,libWeWorkFinanceSdk_Java.so是linux环境的   
```

- 无法运行?

    请注意运行环境,必需在linux下,并且将 `libWeWorkFinanceSdk_Java.so` 拷贝到 `/usr/lib/` 下

- 可以运行无法拉取?

    是否将服务器ip添加到企业微信后台的白名单中,密钥等是否配置正确
  
- 解密失败?

  请生成2048bit && PKCS#8 [在线生成](http://www.metools.info/code/c80.html)  
  如果您使用的是 PKCS#1 请转换为  PKCS#8
  > 另外加密有版本区分,旧的公钥必需使用旧的私钥才能解密  
  > 如果实在是无法解密,请使用新的密钥对新的聊天记录进行解密


###  参与贡献


1. fork 当前库到你的名下
2. 在你的本地修改完成审阅过后提交到你的仓库
3. 提交 PR 并描述你的修改，等待合并


### 联系我们

QQ交流群 [825443653](https://jq.qq.com/?_wv=1027&k=It3u9hrp)

###  License

[MIT license](https://opensource.org/licenses/MIT)
