## 特别鸣谢 - 教程视频

https://www.bilibili.com/video/BV1S142197x7



## 依赖相关

担心不同的微服务组件可能存在版本不兼容问题

直接使用如下依赖，仅需要指定springCloud的版本即可

内部包含springCloud各个组件的兼容版本

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>${spring.cloud.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

