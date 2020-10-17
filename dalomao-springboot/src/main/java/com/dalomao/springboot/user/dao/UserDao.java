package com.dalomao.springboot.user.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dalomao.springboot.user.vo.UserQueryVO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 自定义分页查询
     *
     * @param page    分页变量
     * @param queryVO 查询条件
     * @return
     */
    Page selectPageSelf(@Param("page") Page page, @Param("vo") UserQueryVO queryVO);
}
