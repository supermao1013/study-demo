package com.dalomao.mybatis.extend.plugin;

import com.alibaba.fastjson.JSONArray;
import com.dalomao.mybatis.redis.JedisUtils;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 查询结果缓存redis
 * 拦截ResultSetHandler.class中参数类型为{Statement.class}的handleResultSets方法
 */
@Intercepts({@Signature(
        type = ResultSetHandler.class,
        method = "handleResultSets",
        args = {Statement.class})})
public class ResultSetCacheInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof DefaultResultSetHandler) {
            DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler)invocation.getTarget();
            Field boundSqlf = getField(resultSetHandler, "boundSql");
            boundSqlf.setAccessible(true);
            BoundSql boundSql = (BoundSql)boundSqlf.get(resultSetHandler);
            Map map = (Map)boundSql.getParameterObject();

            //根据参数决定是否缓存到redis
            if (map.get("isCache") != null
                    && Boolean.valueOf(map.get("isCache").toString())) {
                List<Object> results = (List<Object>)invocation.proceed();
                String resultStr = JSONArray.toJSONString(results);
                JedisUtils.set(map.get("cacheKey").toString(), resultStr);
                return results;
            }
        }
        
        return invocation.proceed();
    }
    
    private Field getField(Object obj, String name) {
        Field field = ReflectionUtils.findField(obj.getClass(), name);
        field.setAccessible(true);
        return field;
    }
    
    @Override
    public Object plugin(Object target) {
        if (target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }
    
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        
    }
    
}
