package com.dalomao.springboot.user.service.impl;

import com.dalomao.springboot.user.entity.UserEntity;
import com.dalomao.springboot.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testInsertUser() {
        UserEntity entity = new UserEntity();
        entity.setUserName("zhangsan");
        entity.setLoginName("zhangsan");
        userService.insertUser(entity);
    }
}
