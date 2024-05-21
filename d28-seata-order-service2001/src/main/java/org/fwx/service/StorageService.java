package org.fwx.service;

import org.fwx.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName StorageService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:51
 * @Version 1.0
 */
@Service
@FeignClient(value = "seata-storage-service2002")
public interface StorageService {

    @PostMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
