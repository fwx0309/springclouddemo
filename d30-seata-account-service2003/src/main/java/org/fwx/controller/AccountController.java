package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.domain.CommonResult;
import org.fwx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AccountController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 14:35
 * @Version 1.0
 */
@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        // 模拟超时异常,测试分布式事务
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("------->account-service中扣减账户余额开始");
        accountService.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
        return new CommonResult(200,"扣减账户余额成功！");
    }
}
