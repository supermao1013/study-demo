package com.dalomao.service;

/**
 * Created by maohw on 2019/1/21.
 */
public interface IUUIDService {

    /**
     * 默认生成32位长度的主键id
     *
     * @return 8位yyyyMMdd + redis当天自增数左补齐0
     */
    String generateId();
}
