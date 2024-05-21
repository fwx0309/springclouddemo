package org.fwx.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName AccountService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 14:31
 * @Version 1.0
 */
public interface AccountService {
    void decrease(Long userId,BigDecimal money);
}
