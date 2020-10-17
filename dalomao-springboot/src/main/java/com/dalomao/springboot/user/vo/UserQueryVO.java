package com.dalomao.springboot.user.vo;

import com.dalomao.springboot.core.vo.BaseQueryVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 前端查询vo
 * </p>
 *
 * @author maohw
 * @since 2019-09-14
 */
@Getter
@Setter
public class UserQueryVO extends BaseQueryVO implements Serializable {
    /**
     * 用户状态
     */
    private String status;

    /**
     * 登录账号
     */
    private String loginName;


}
