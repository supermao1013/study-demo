package com.dalomao.mybatis;

import com.alibaba.fastjson.JSON;
import com.dalomao.mybatis.extend.dao.CommonMapper;
import com.dalomao.mybatis.extend.entity.ConsultConfigArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis测试
 */
@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

    @Autowired
    private CommonMapper commonMapper;

    /**
     * 首页
     * @return String
     */
    @RequestMapping("/index.htm")
    public String index() {
        return "/mybatis/index";
    }

    @RequestMapping("/query.json")
    @ResponseBody
    public String query() {
        Map<String, Object> param = new HashMap<>();
        param.put("areaCode", "101");

        List<ConsultConfigArea> list = commonMapper.queryAreaByAreaCode(param);

        return JSON.toJSONString(list);
    }

    @RequestMapping("/cacheQuery.json")
    @ResponseBody
    public String cacheQuery() {
        Map<String, Object> param = new HashMap<>();
        param.put("areaCode", "101");
        param.put("isCache", true);
        param.put("cacheKey", JSON.toJSONString(param));

        List<ConsultConfigArea> list = commonMapper.queryAreaByAreaCode(param);

        return JSON.toJSONString(list);
    }
}
