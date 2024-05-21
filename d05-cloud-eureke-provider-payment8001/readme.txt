1、启用服务发现：
    启动类添加@EnableDiscoveryClient
    org.fwx.controller.PaymentController#discovery
    测试地址：http://localhost:8001/payment/discovery
2、关闭自我保护,7001和8001切换回单机版测试
    7001yml
        eureka:
          server:
            # 使用7001单节点"关闭自我保护"测试，如果是集群模式，集群版需要配置。这里对应测试的服务是8001,8001上也需要做对应的配置
            #关闭自我保护机制，保证不可用服务被及时踢除 (使用7001单节点测试)
            #enable-self-preservation: false
            #默认是10分钟，这里设置2秒 (使用7001单节点测试)
            #eviction-interval-timer-in-ms: 2000
    8001yml
        eureka:
          instance:
            # 使用8001单节点"关闭自我保护"测试，如果是集群模式，集群版需要各节点配置。这里对应测试的eureka服务是7001,7001上也需要做对应的配置
            # Eureka客户端向服务端发送心跳的时间间隔，单位为秒(默认是30秒)
            #lease-renewal-interval-in-seconds: 1
            #Eureka服务端在收到最后一次心跳后等待时间上限，单位为秒(默认是90秒)，超时将剔除服务
            #lease-expiration-duration-in-seconds: 2
