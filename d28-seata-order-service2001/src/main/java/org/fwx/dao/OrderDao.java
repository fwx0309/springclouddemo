package org.fwx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fwx.domain.Order;

import java.math.BigDecimal;

/**
 * @ClassName OrderDao
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 10:39
 * @Version 1.0
 */
@Mapper
public interface OrderDao {

    void create(Order order);

    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
