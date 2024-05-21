
1、服务注册：http方式调用服务
    项目结构：
        d01-cloud-provider-payment8001
        d02-cloud-consumer-order80
        d03-cloud-api-commons

2、服务注册：eureka微服务
    项目结构：
        eureka:d04-cloud-eureka-server7001、d07-cloud-eureka-server7002
        服务端：d05-cloud-eureke-provider-payment8001、d08-cloud-eureke-provider-payment8002
                新增测试不同服务名：d11-cloud-eureke-provider-payment8003
        客户端：d06-cloud-eureka-consumer-order80

3、服务注册：zookeeper
    项目结构：
        d09-cloud-zookeeper-provider-payment8004
        d10-cloud-zookeeper-consumer-order80

4.单机版eureka server,方便测试使用
    d13-cloud-eureka-server7003

5.OpenFeign服务接口调用
    Feign可以与Eureka和Ribbon组合使用以支持负载均衡
    项目结构：
        d13-cloud-eureka-server7003
        d11-cloud-eureke-provider-payment8003
        d12-cloud-feign-consumer-order80

6.Hystrix断路器
    服务降级、服务熔断、服务限流、服务监控hystrixDashboard
    项目结构：
        d13-cloud-eureka-server7003
        d14-cloud-eureke-provider-payment8005
        d15-cloud-feign-hystrix-consumer-order80
        d16-cloud-consumer-hystrix-dashboard9001

7.Gateway新一代网关
    route(路由)/predicate(断言)/filter(过滤)
    项目结构：
        d13-cloud-eureka-server7003
        d17-cloud-gateway-gateway9527
        d18-cloud-eureke-provider-payment8006
        d19-cloud-eureke-provider-payment8007

8.nacos本机案例，服务注册，配置中心
    项目结构：
    本机nacos
    d20-cloudalibaba-provider-payment9001、d21-cloudalibaba-provider-payment9002
    d22-cloudalibaba-consumer-nacos-order83
    d23-cloudalibaba-config-nacos-client3377(配置获取客户端)

9.nacos伪集群部署(192.168.2.100)
    a.下载地址：https://github.com/alibaba/nacos/releases/tag/1.1.4
    b.上传服务器
    c.执行nacos自带的mysql脚本建库建表
    d.修改nacos /opt/software/nacos/conf/application.properties 配置
        spring.datasource.platform=mysql

        db.num=1
        db.url.0=jdbc:mysql://192.168.2.100:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnec=true
        db.user=root
        db.password=root

    e.Linux服务器上nacos的集群配置cluster.conf，这个IP不能写127.0.0.1，必须是Linux命令hostname -i能够识别的IP
        192.168.2.100:3333
        192.168.2.100:4444
        192.168.2.100:5555

    f.编辑Nacos的启动脚本startup.sh，使它能够接受不同的启动端口
        修改内容：
            1)while getopts ":m:f:s:" opt
              while getopts ":m:f:s:p:" opt

            2)在 "s)   SERVER=$OPTARG;;" 后面添加 "p)    PORT=$OPTARG;;",如下：
                ......
                s)
                   SERVER=$OPTARG;;
                p)
                   PORT=$OPTARG;;
                ......

            3)nohup $JAVA ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &
              nohup $JAVA -Dserver.port=${PORT} ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &
    g.启动nacos
        ./startup.sh -p 3333
        ./startup.sh -p 4444
        ./startup.sh -p 5555

    h.Nginx的配置，由它作为负载均衡器
        a.nginx.conf修改内容：
            # nacos 负载均衡
            # 均衡策略：默认轮询、ip_hash(同一ip访问同一机器，决绝session问题)、weight(权重)、fair(根据服务器响应时间)
            upstream cluster {
                # ip_hash;
                server 192.168.2.100:3333 weight=1;
                server 192.168.2.100:4444 weight=1;
                server 192.168.2.100:5555 weight=1;
                # fair;
            }

            server {
                listen       1111;
                server_name  192.168.2.100;

                location / {
                        proxy_pass http://cluster;
                }
            }

        b.启动nginx:
            ./ngingx -c /usr/local/nginx/conf/nginx.conf

    I.访问测试
        http://192.168.2.100:1111/nacos/#/login
        用户名：nacos
        密码：nacos
    J.9001服务注册
        d20-cloudalibaba-provider-payment9001

10.sentinel http://localhost:8080/
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

11.sentinel 服务熔断 org.fwx.controller.CricleBreakeController.fallback
    项目结构：
        d25-cloudalibaba-provider-payment9003
        d26-cloudalibaba-provider-payment9004
        d27-cloudalibaba-consumer-nacos-order84

12.sentinel openfeign，注意yml中激活Sentinel对Feign的支持，启动类上添加@EnableFeignClients
    org.fwx.controller.CricleBreakeController.paymentSQL

    项目结构：
            d25-cloudalibaba-provider-payment9003
            d26-cloudalibaba-provider-payment9004
            d27-cloudalibaba-consumer-nacos-order84

13.seata 分布式事务
    下载地址：https://github.com/apache/incubator-seata/releases?page=2
    官网：https://seata.apache.org/zh-cn/
    安装配置：
        a.file.conf，修改 vgroup_mapping.my_test_tx_group
            ......
            service {
              #vgroup->rgroup
              #vgroup_mapping.my_test_tx_group = "default"
              vgroup_mapping.fsp_tx_group = "default"
            ......

        b.file.conf，修改 mode、db 里面的配置
            ......
            store {
              ## store mode: file、db
              #mode = "file"
              mode = "db"

              ......

              ## database store
              db {
                ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
                datasource = "dbcp"
                ## mysql/oracle/h2/oceanbase etc.
                db-type = "mysql"
                driver-class-name = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://192.168.2.100:3306/seata"
                user = "root"
                password = "root"
              ......

        c.registry.conf，修改 registry下的type和nacos 配置
            registry {
              # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
              #type = "file"
              type = "nacos"

              nacos {
                serverAddr = "localhost:8848"
                namespace = ""
                cluster = "default"
              }
              ......
        d.用 db_store.sql 中的 sql 建表

14.seata 项目，下订单->减库存->扣余额->改(订单)状态。测试路径：http://localhost:2001/order/create?productId=1&count=1&money=100&userId=1
     项目结构：
        d28-seata-order-service2001
        d29-seata-storage-service2002
        d30-seata-account-service2003
        sql(建表sql)
     注意事项：
        a.yml中seata的配置
        b.file.conf、registry.conf和seata中一致，yml中tx-service-group，和file.conf中vgroup_mapping，一定要保持一致
        c.主类排除 @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
        d.Config配置
        e.order项目中Feign远程调用storage和account项目


