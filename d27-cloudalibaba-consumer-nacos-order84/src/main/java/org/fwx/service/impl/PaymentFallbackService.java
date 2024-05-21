package org.fwx.service.impl;

import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.service.PaymentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentFallbackService
 * @Description PaymentFallbackService类实现了PaymentService接口，提供服务降级时的处理逻辑。
 * @Author Fwx
 * @Date 2024/5/20 22:07
 * @Version 1.0
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        // 生成一个包含降级信息和错误支付数据的CommonResult对象
        return new CommonResult<>(446,"服务降级返回，---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}

