package com.dalomao.rspchain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Package: com.dalomao.demo.rspchain</p>
 * <p>Description:责任链定义 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/5
 **/
public class ResponsibilityChain {
    private List<Responsibility> responsibilitys;
    private int index = 0;
    public ResponsibilityChain() {
        this.responsibilitys = new ArrayList<>();
    }

    /**
     * 责任链处理
     * @param request
     */
    public void process(Request request) {
        if (this.index < this.responsibilitys.size()) {
            //允许前置处理
            System.out.println("这里可以做一些前置处理");

            this.responsibilitys.get(index++).process(request, this);

            //允许后置处理
            System.out.println("这里可以做一些后置处理");
        }
    }

    /**
     * 注册责任链
     * @param res
     */
    public void register(Responsibility res) {
        this.responsibilitys.add(res);
    }
}
