package com.dalomao.springboot.core.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础查询类，封装共通树形
 */
@Getter
@Setter
public class BaseQueryVO implements Serializable {

    /**
     * 创建开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;


    /**
     * 创建开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
