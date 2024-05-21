1、openfeign使用
    a.启动类添加@EnableFeignClients
    b.服务接口org.fwx.service.PaymentFeignService
        接口类上添加：@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
        接口方法：对应服务提供controller接口
    c.自身工程的 org.fwx.controller.OrderFeignController 调用 org.fwx.service.PaymentFeignService