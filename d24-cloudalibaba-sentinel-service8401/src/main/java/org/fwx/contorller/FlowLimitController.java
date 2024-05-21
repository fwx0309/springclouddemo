package org.fwx.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }

    /**
     * 降级压力测试
     * jmeter 1秒10次
     * 永远一秒钟打进来10个线程（大于5个了）调用testD，我们希望200毫秒处理完本次任务，
     * 如果超过200毫秒还没处理完，在未来1秒钟的时间窗口内，断路器打开(保险丝跳闸)微服务不可用，保险丝跳闸断电了
     * @return
     */
    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }

    /**
     * 降级异常测试
     * 设置异常比例数20%，或者异常数20个，两种策略都可以
     * 单独访问一次，必然来一次报错一次(int age  = 10/0)，调一次错一次；
     * 开启jmeter后，直接高并发发送请求，多次调用达到我们的配置条件了。
     * 断路器开启(保险丝跳闸)，微服务不可用了，不再报错error而是服务降级了。
     * @return
     */
    @GetMapping("/testE")
    public String testE()
    {
        int i = 10 / 0;
        log.info("testE 异常");
        return "------testE";
    }

    /**
     * 1、热点key数限流，超过设置的限流次数，触发限流
     *
     * 2、特殊的情况，我们期望p1参数当它是某个特殊值时，它的限流值和平时不一样
     * 假如当p1的值等于5时，它的阈值可以达到200
     *
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "------testHotKey";
    }

    /**
     * 触发热点key限流执行的方法
     */
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "------deal_testHotKey,o(╥﹏╥)o";
    }
}