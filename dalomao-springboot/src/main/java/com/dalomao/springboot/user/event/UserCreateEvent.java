package com.dalomao.springboot.user.event;

import lombok.Getter;
import lombok.Setter;

/**
 * 创建用户事件
 */
@Getter
@Setter
public class UserCreateEvent {
    private Long userId;

    private String userName;
}
