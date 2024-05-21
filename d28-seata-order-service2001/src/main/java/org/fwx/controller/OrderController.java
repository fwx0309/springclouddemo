package org.fwx.controller;

import org.fwx.domain.CommonResult;
import org.fwx.domain.Order;
import org.fwx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 17:05
 * @Version 1.0
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 创建订单
    // http://localhost:2001/order/create?productId=1&count=1&money=100&userId=1
    @PostMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
