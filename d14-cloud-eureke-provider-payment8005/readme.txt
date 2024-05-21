1.服务端，服务降级
    a.启动类配置 org.fwx.EurekaPaymentMain8005 @EnableCircuitBreaker
    b.服务方法 org.fwx.service.PaymentService#paymentInfo_TimeOut 添加 @HystrixCommand 注解
    c.编写降级后执行的方法 org.fwx.service.PaymentService#paymentInfo_TimeOutHandler
2.服务熔断
    a.启动类添加 @EnableCircuitBreaker  // 启用断路器
    b.org.fwx.service.PaymentService#paymentCircuitBreaker
3.9001 hystrix dashboard 服务监控，测试方法
    a.org.fwx.service.PaymentService#paymentCircuitBreaker
    b.启动类添加
        /**
         *此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
         *ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
         *只要在自己的项目里配置上下面的servlet就可以了
         */
        @Bean
        public ServletRegistrationBean getServlet() {
            HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
            ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
            registrationBean.setLoadOnStartup(1);
            registrationBean.addUrlMappings("/hystrix.stream");
            registrationBean.setName("HystrixMetricsStreamServlet");
            return registrationBean;
        }
    c.在http://localhost:9001/hystrix管理页面监控http://localhost:8005/hystrix.stream