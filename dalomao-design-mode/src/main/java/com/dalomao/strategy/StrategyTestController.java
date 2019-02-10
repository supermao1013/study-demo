package com.dalomao.strategy;

import com.dalomao.common.SpringContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>Package: com.dalomao.demo.strategy</p>
 * <p>
 *      策略模式
 *      定义一系列算法，并将每一个算法封装起来，将不变的代码固定，让算法独立于使用它的用户而独立变化
 * </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/1
 **/
@Controller
@RequestMapping("/strategy")
public class StrategyTestController {

    @RequestMapping("/test.json")
    @ResponseBody
    public String test() {
        String orderType = "computer";

        //获取所有类型为OrderHandler.class的类
        Map<String, OrderHandler> beansMap = SpringContextUtil.getApplicationContext().getBeansOfType(OrderHandler.class);

        //处理
        for (Map.Entry<String, OrderHandler> entry : beansMap.entrySet()) {
            if (entry.getValue().support(orderType)) {
                //调用对应的订单类型处理类
                entry.getValue().handler();
            }
        }

        return "strategy success";
    }

}
