package org.fwx.service;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName StorageService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:23
 * @Version 1.0
 */
public interface StorageService {
    void decrease(Long productId,Integer count);
}
