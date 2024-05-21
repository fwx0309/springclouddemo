package org.fwx.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fwx.dao.StorageDao;
import org.fwx.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName StorageServiceImpl
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 16:24
 * @Version 1.0
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("storage-service decrease");
        storageDao.decrease(productId,count);
    }
}
