package com.dalomao.service.impl;

import com.dalomao.service.IUUIDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by maohw on 2019/1/29.
 * jdk uuid实现
 */
@Service("uuidServiceUUIDImpl")
public class UUIDServiceUUIDImpl implements IUUIDService {

    public String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
