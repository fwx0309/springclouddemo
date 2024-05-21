package org.fwx.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;

/**
 * @ClassName CustomerBlockHandler
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/20 19:26
 * @Version 1.0
 */
public class CustomerBlockHandler {

    public static CommonResult<Payment> handlerException1(BlockException exception){
        return new CommonResult<>(444,"按客户自定义的handlerException--1" + exception.getClass().getCanonicalName());
    }
    public static CommonResult<Payment> handlerException2(BlockException exception){
        return new CommonResult<>(444,"按客户自定义的handlerException--2" + exception.getClass().getCanonicalName());
    }
}
