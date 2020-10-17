package com.dalomao.springboot.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dalomao.springboot.user.entity.UserEntity;
import com.dalomao.springboot.user.vo.UserQueryVO;

/**
 * <p>
 * 系统管理-用户信息表 服务类
 * </p>
 *
 * @author maohw
 * @since 2019-09-14
 */
public interface IUserService extends com.baomidou.mybatisplus.extension.service.IService<UserEntity> {

    /**
     * mybatisplus提供的分页
     *
     * @param page
     * @param userQueryVO
     * @return
     */
    IPage selectPage(Page page, UserQueryVO userQueryVO);

    /**
     * 新增用户
     *
     * @param userEntity
     */
    void insertUser(UserEntity userEntity);
}
