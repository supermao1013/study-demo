package com.dalomao.factory.method;

import java.util.Map;

/**
 * <p>Package: com.dalomao.demo.factory.method</p>
 * <p>Description:虚拟产品类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public abstract class AbstractMessage implements Message {

    /**
     * 生产产品所需要的原材料
     */
    private Map<String, Object> messageParam;

    @Override
    public Map<String, Object> getMessageParam() {
        return this.messageParam;
    }

    @Override
    public void setMessageParam(Map<String, Object> messageParam) {
        this.messageParam = messageParam;
    }
}
