package org.fwx.service;

import org.fwx.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @ClassName AccountService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:54
 * @Version 1.0
 */
@Service
@FeignClient(value = "seata-account-service2003")
public interface AccountService {

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
