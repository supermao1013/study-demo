package com.dalomao.service.impl;

import com.dalomao.service.IUUIDService;
import com.dalomao.util.RedisUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maohw on 2019/1/21.
 */
@Service("uuidServiceRedisImpl")
public class UUIDServiceRedisImpl implements IUUIDService{

    @Autowired
    private RedisUtilsService redisUtilsService;

    private final String  idKeyPrefix = "primary:id:key";

    private final int  expireTs = 24 * 60 * 60;

    private DateFormat df = new SimpleDateFormat("yyyyMMdd");

    /**
     * 指定长度生成主键
     * 根据业务场景传入不同长度，这里不保证长度超出的结果
     * 主键长度越长，越适合并发的场景
     *
     * @param length 主键长度
     * @return 8位yyyyMMdd + redis当天自增数左补齐0
     */
    public String generateId(int length) {
        if (length < 10) {
            throw new RuntimeException("生成的主键长度不能少于10");
        }

        String day = df.format(new Date());

        //若为新的一天，则为不同的key
        String redisKey = idKeyPrefix + ":" + day;
        long id = redisUtilsService.getIncr(redisKey, expireTs);

        //补齐长度
        int swallowLength = length - 6;

        return day + String.format("%1$0" + swallowLength + "d", id);
    }

    public String generateId() {
        return this.generateId(32);
    }
}
