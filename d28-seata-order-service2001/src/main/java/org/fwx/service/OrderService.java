package org.fwx.service;

import org.apache.ibatis.annotations.Param;
import org.fwx.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;

import java.math.BigDecimal;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:43
 * @Version 1.0
 */
public interface OrderService {
    void create(Order order);
}
