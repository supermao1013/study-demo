package com.dalomao.springboot.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dalomao.springboot.core.service.impl.BaseServiceImpl;
import com.dalomao.springboot.user.dao.UserEntityDao;
import com.dalomao.springboot.user.entity.UserEntity;
import com.dalomao.springboot.user.event.UserCreateEvent;
import com.dalomao.springboot.user.service.IUserService;
import com.dalomao.springboot.user.vo.UserQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Date;

/**
 * <p>
 * 系统管理-用户信息表 服务实现类
 * </p>
 *
 * @author maohw
 * @since 2019-09-14
 */
@Service
@Transactional
@Slf4j
public class UserService extends BaseServiceImpl<UserEntityDao, UserEntity> implements IUserService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public IPage selectPage(Page page, UserQueryVO userQueryVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("login_name", userQueryVO.getLoginName());
        return super.page(page, queryWrapper);
    }

    @Override
    public void insertUser(UserEntity entity) {
        // 新增用户
        entity.setDelFlag("0");
        entity.setCreateTime(new Date());
        entity.setCreateUser("system");
        this.baseMapper.insert(entity);

        // 发布事件
        UserCreateEvent event = new UserCreateEvent();
        event.setUserId(entity.getId());
        event.setUserName(entity.getUserName());
        applicationEventPublisher.publishEvent(event);

        // 后续处理
    }

    /**
     * 监听：创建成功事件
     *
     * @param event
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void eventListener(UserCreateEvent event) {
        log.info("监听到事件：{}", JSON.toJSON(event));
    }
}
