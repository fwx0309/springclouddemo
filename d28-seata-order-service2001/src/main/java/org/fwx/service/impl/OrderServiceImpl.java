package org.fwx.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.fwx.dao.OrderDao;
import org.fwx.domain.Order;
import org.fwx.service.AccountService;
import org.fwx.service.OrderService;
import org.fwx.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:45
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        // 创建订单
        log.info("-----> 创建订单开始");
        orderDao.create(order);
        log.info("-----> 创建订单结束");

        // 扣减库存
        log.info("-----> 扣减库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("-----> 扣减库存结束");

        // 扣减账户
        log.info("-----> 扣减账户开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-----> 扣减账户结束");

        // 修改订单状态
        log.info("-----> 修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("-----> 修改订单状态结束");

        log.info("-----> 创建订单完成");
    }
}
