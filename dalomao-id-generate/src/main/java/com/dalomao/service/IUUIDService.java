package com.dalomao.service;

/**
 * Created by maohw on 2019/1/21.
 */
public interface IUUIDService {
    /**
     * 指定长度生成主键
     * 根据业务场景传入不同长度，这里不保证长度超出的结果
     * 主键长度越长，越适合并发的场景
     *
     * @param length 主键长度
     * @return 8位yyyyMMdd + redis当天自增数左补齐0
     */
    String generateId(int length);

    /**
     * 默认生成32位长度的主键id
     *
     * @return 8位yyyyMMdd + redis当天自增数左补齐0
     */
    String generateId();
}
