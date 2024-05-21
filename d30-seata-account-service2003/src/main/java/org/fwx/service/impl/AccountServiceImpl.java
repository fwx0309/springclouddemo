package org.fwx.service.impl;

import org.fwx.dao.AccountDao;
import org.fwx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 14:34
 * @Version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money) {
        accountDao.decrease(userId,money);
    }
}
