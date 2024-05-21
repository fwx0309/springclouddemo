package org.fwx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fwx.entities.Payment;

/**
 * @ClassName PaymentDao
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 10:14
 * @Version 1.0
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
