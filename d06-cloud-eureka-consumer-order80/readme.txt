1.eureka：spring-cloud-starter-netflix-eureka-client，已集成ribbon负载均衡
    使用负载，在RestTemplate配置类上添加 @LoadBalanced
2.需改ribbon轮询策略，IRule子类
    新建 org.ribbonconfig.RibbonConfig 配置类，不能和启动类同一级包下
    启动类上配置：@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT111",configuration = RibbonConfig.class)
        name可以随意配置