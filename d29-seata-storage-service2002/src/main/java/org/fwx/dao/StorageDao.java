package org.fwx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName StorageDao
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:18
 * @Version 1.0
 */
@Mapper
public interface StorageDao {
    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
