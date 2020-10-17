package com.dalomao.springboot.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dalomao.springboot.core.service.BaseService;

/**
 * 基础service实现类
 *
 * @param <M> mapper
 * @param <T> 实体类
 * @author maohw
 * @since 2019-09-08
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
