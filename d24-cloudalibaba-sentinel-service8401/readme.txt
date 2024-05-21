sentinel http://localhost:8080/
    a.下载地址 https://github.com/alibaba/Sentinel/releases
    b.监控 d24-cloudalibaba-sentinel-service8401
        限流、降级、热点key、系统规则、@SentinelResource、服务熔断功能
    c.sentinel规则持久化-->nacos
        1)yum配置：
        spring:
          cloud:
            sentinel:
              datasource:
                ds1:
                  nacos:
                    server-addr: localhost:8848
                    dataId: ${spring.application.name}
                    groupId: DEFAULT_GROUP
                    data-type: json
                    rule-type: flow
        2)在nacos中新建配置
            Data ID:cloudalibaba-sentinel-service
            GROUP: DEFAULT_GROUP
            配置格式：JSON
            配置内容：
                [
                    {
                        "resource": "/rateLimit/byUrl",
                        "limitApp": "default",
                        "grade": 1,
                        "count": 1,
                        "strategy": 0,
                        "controlBehavior": 0,
                        "clusterMode": false
                    }
                ]
            *内容解释
                resource：资源名称；
                limitApp：来源应用；
                grade：阈值类型，0表示线程数，1表示QPS；
                count：单机阈值；
                strategy：流控模式，0表示直接，1表示关联，2表示链路；
                controlBehavior：流控效果，0表示快速失败，1表示Warm Up，2表示排队等待；
                clusterMode：是否集群。