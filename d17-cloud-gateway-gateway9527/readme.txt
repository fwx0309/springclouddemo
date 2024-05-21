1.路由配置文件方式：yml
    如果同一个服务有多个实例，最好使用eureka上的服务名（CLOUD-PROVIDER-PAYMENT payment8007,payment8006）实现用动态路由

2.配置类方式：org.fwx.config.GetwayConfig

3.路由、断言
    spring:
      application:
        name: cloud-gateway-gateway9527
      cloud:
        gateway:
          discovery:
            locator:
              enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
          routes:   # 路由配置 参考：https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-request-predicates-factories
            - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
              #uri: http://localhost:8006          #静态路由配置，匹配后提供服务的路由地址
              uri: lb://CLOUD-PROVIDER-PAYMENT #动态路由配置，匹配后提供服务的路由地址
              predicates:
                - Path=/payment/get/**         # 断言，路径相匹配的进行路由

            - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
              #uri: http://localhost:8006          #静态路由配置，匹配后提供服务的路由地址
              uri: lb://CLOUD-PROVIDER-PAYMENT #动态路由配置，匹配后提供服务的路由地址
              predicates:
                - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
                #- After=2020-02-21T15:51:37.485+08:00[Asia/Shanghai] # 时间断言
                #- Cookie=username,fwx       # 请cookie要有username属性并且值为fwx，例如：curl http://localhost:9527/payment/lb --cookie username=fwx
                #- Header=X-Request-Id, \d+  # 请求头要有X-Request-Id属性并且值为整数的正则表达式
                #- Method=GET                # 请求方式
                #- Host=**.atguigu.com       # 请求域名
                #- Query=username, \w+       # 请求参数要有username参数并且值为字母数字下划线的正则表达式

4.过滤filter建议用自定义配置类实现
    如：org.fwx.filter.LogGetWayFilter