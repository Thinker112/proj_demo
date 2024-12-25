# 日志跟踪总监

## 1.打包方式

```
在根目录下执行
clean install -Dmaven.test.skip=true -pl log-trace -am -U -f pom.xml
```

## 2.使用方式

### 2.1 maven依赖

```xml

<dependency>
    <groupId>com.zhou.base.components</groupId>
    <artifactId>log-trace</artifactId>
    <version>${revision}</version>
</dependency>
```

### 2.2 logback-spring.xml配置

```xml

<property name="log.pattern"
          value="%d{yyyy/MM/dd-HH:mm:ss} %-5level [%-21thread][%X{traceId}] %logger{36} %line - %msg%n"/>

<logger name="com.zhou.base.components.log.trace.log" level="debug"/>
```

%X{traceId} 一定要加上

## 3说明

### 3.1.本组件在启动主线程中会打印出日志跟踪id

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/startup.png)

### 3.2.本组件在前端http调用时会打印出日志跟踪id

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/normal.png)

### 3.3.本组件在异步调用时，异步线程会打印出日志跟踪id

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/async.png)

### 3.4.本组件在@Scheduled定时任务执行线程中会打印出日志跟踪id

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/scheduler.png)

### 3.5.本组件会在@XxlJob中打印出日志跟踪id

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/xxljob-1.png)
![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/xxljob-2.png)

### 3.6.本组件会打印出请求的入参出参和耗时

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/request.png)

### 3.7.feign日志调用中会把traceid传递到下游

![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/feign.png)

原理
![](https://gitee.com/RenZhenGongZuo/base-components/raw/master/img/log-trace/feign1.png)
