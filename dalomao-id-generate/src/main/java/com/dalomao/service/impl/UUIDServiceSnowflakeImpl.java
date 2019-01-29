package com.dalomao.service.impl;

import com.dalomao.service.IUUIDService;
import com.dalomao.util.SnowflakeIdWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maohw on 2019/1/22.
 */
@Service("uuidServiceSnowflakeImpl")
public class UUIDServiceSnowflakeImpl implements IUUIDService {

    @Autowired
    private SnowflakeIdWorkerService snowflakeIdWorkerService;

    public String generateId() {
        return Long.toString(snowflakeIdWorkerService.nextId());
    }
}
