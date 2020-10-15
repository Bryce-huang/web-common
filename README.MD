# web公共包

旨在开箱即用，无需再重新编写一些通用web配置

# 使用


pom.xml 引入

```xml
<dependency>
    <groupId>cn.riris</groupId>
    <artifactId>web-common</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

配置swagger

```yaml
swagger-info:
  description: 123
  title: hrl
  name: 123
  version: '@version@'
  url: 123
  email: 1123
```

配置logback

```yaml
server:
  port: 12345
spring:
  application:
    name: app
  profiles:
    active: dev
logging:
  pod-name: ${hostname:dev} # 支持k8s
  file:
    path: 'C:\Users\bryce\Desktop\log'
    max-size: 100MB
    total-size-cap: 300GB
    max-history: 30
  config: 'classpath:logback-spring.xml' #此处不要更改
  level:
    root: info
```


