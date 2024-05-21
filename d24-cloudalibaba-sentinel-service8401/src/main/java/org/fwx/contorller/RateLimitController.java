package org.fwx.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RateLimitController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/20 19:21
 * @Version 1.0
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult<Payment> handleException(BlockException exception) {
        return new CommonResult<>(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "按url限流测试OK", new Payment(2020L, "serial002"));
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult<Payment> customerBlockHandler() {
        return new CommonResult<>(200, "按客户自定义限流测试OK", new Payment(2020L, "serial003"));
    }
}
