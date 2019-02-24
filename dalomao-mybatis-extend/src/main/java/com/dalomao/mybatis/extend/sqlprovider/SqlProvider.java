package com.dalomao.mybatis.extend.sqlprovider;

import java.util.Map;

/**
 * 单独sql写在这里
 * 这种用法比较少
 */
public class SqlProvider {
    
    public String areaSql(Map paramMap) {
        
        Map map = (Map)paramMap.get("paramMap");
        String sql = "select * from consult_configarea where areaName='"
                + map.get("areaName") + "'";
        return sql;
    }
}
