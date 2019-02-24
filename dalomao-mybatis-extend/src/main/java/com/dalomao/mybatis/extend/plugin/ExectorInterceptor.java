package com.dalomao.mybatis.extend.plugin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dalomao.mybatis.extend.entity.ConsultConfigArea;
import com.dalomao.mybatis.redis.JedisUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 查询数据，若redis缓存有则直接取出来
 * 拦截Executor.class中参数类型为{MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}的query方法
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ExectorInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof Executor) {
            Map map = (Map)invocation.getArgs()[1];
            String cacheKey = map.containsKey("cacheKey") ? map.get("cacheKey").toString() : "";
            if (cacheKey != null && !"".equals(cacheKey)) {
                String cacheStr = JedisUtils.get(cacheKey);
                if (cacheStr != null && !"".equals(cacheStr)) {
                    JSONArray ja = JSONArray.parseArray(cacheStr);
                    List<ConsultConfigArea> list = new ArrayList<ConsultConfigArea>();
                    for (Object o : ja) {
                        list.add(JSONObject.parseObject(o.toString(), ConsultConfigArea.class));
                    }
                    return list;
                }
            }
        }

        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }

        return target;
    }
    
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        
    }
    
}
