package com.dalomao.mybatis.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Jedis工具类
 * 适合非动态切换redis机器
 */
public class JedisUtils {

    private static String OK = "OK";
    private static String NOK = "NOK";

    private static Properties prop = null;

    private static JedisPool pool;

    static {
        InputStream in = JedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        prop = new Properties();

        try {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("加载redis配置文件出错");
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.valueOf(prop.getProperty("redis.pool.maxTotal")));
        config.setMaxIdle(Integer.valueOf(prop.getProperty("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Integer.valueOf(prop.getProperty("redis.pool.maxWaitMillis")));
        config.setTestOnBorrow(Boolean.valueOf(prop.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(prop.getProperty("redis.pool.testOnReturn")));
        config.setTestWhileIdle(Boolean.valueOf(prop.getProperty("redis.pool.testWhileIdle")));
        pool = new JedisPool(
                config,
                prop.getProperty("redis.ip"),
                Integer.valueOf(prop.getProperty("redis.port")),
                100000,
                prop.getProperty("redis.password")
        );
    }

    /**
     * 通过key获取储存在redis中的value 并释放连接
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    public static String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 向redis存入key和value,并释放连接资源 如果key已经存在 则覆盖
     *
     * @param key key值
     * @param value value值
     * @return 成功-true 失败-返回false
     */
    public static Boolean set(String key, String value) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();//每次操作时向pool借用一个jedis对象，用完即还。
            flag = OK.equals(jedis.set(key, value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 向redis存入序列化的key和value,并释放连接资源 如果key已经存在 则覆盖
     *
     * @param keyBytes key序列化值
     * @param valueBytes value序列化值
     * @return 成功返回true 失败返回false
     */
    public static Boolean setSerializer(byte[] keyBytes, byte[] valueBytes) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.set(keyBytes, valueBytes));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过序列化key获取储存在redis中的序列化value值 并释放连接
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    public static byte[] getSerializer(byte[] key) {
        Jedis jedis = null;
        byte[] value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return value;
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public static Long del(String... keys) {
        Jedis jedis = null;
        Long count = 0L;
        try {
            jedis = pool.getResource();
            count = jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return count;
    }

    /**
     * 通过key向指定的value值追加值，若key不存在则创建
     *
     * @param key key值
     * @param str
     * @return 成功-返回添加后value的长度 失败-返回0L
     */
    public static Long append(String key, String str) {
        Jedis jedis = null;
        Long count = 0L;
        try {
            jedis = pool.getResource();
            count = jedis.append(key, str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return count;
    }

    /**
     * 判断key是否存在
     *
     * @param key key值
     * @return true-存在 false-不存在
     */
    public static Boolean exists(String key) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     *
     * @param key key值
     * @param value value值
     * @return 成功返回ture  失败返回false
     */
    public static Boolean setnx(String key, String value) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = jedis.setnx(key, value) == 1L;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 设置key value并制定这个键值的有效期
     * 若key不存在则创建，若key存在则覆盖
     *
     * @param key key值
     * @param value value值
     * @param seconds 超时时间，单位:秒
     * @return 成功-返回true 失败-返回false
     */
    public static Boolean setex(String key, String value, int seconds) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.setex(key, seconds, value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过key 和offset 从指定的位置开始将原先value替换
     * 下标从0开始,offset表示从offset下标开始替换
     * 如果替换的字符串长度过小则会这样
     * 原value: bigsea@zto.cn
     * 替换下标offest: 2
     * 替换str : abc
     * 结果为: bigabc@zto.cn
     *
     * @param key key值
     * @param offset 下标位置
     * @param str 替换的字符串
     * @return 返回替换后 value 的长度
     */
    public static Long setrange(String key, int offset, String str) {
        Jedis jedis = null;
        Long count = 0L;
        try {
            jedis = pool.getResource();
            count = jedis.setrange(key, offset, str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return count;
    }

    /**
     * 通过批量的key获取批量的value
     *
     * @param keys string数组 也可以是一个key
     * @return 成功返回value的集合, 失败返回null的集合 ,异常返回空
     */
    public static List<String> mget(String... keys) {
        Jedis jedis = null;
        List<String> values = null;
        try {
            jedis = pool.getResource();
            values = jedis.mget(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return values;
    }

    /**
     * 批量的设置key:value,原子操作
     * example:
     * obj.mset(new String[]{"key1","value1","key2","value2"})
     *
     * @param keysvalues key-value 组合
     * @return 成功返回true 失败返回false
     *
     */
    public static Boolean mset(String... keysvalues) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.mset(keysvalues));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 删除多个字符串key 并释放连接
     * 使用pipeline，同步调用，非原子性
     *
     * @param keys key集合
     * @return 成功返回true 失败返回false
     */
    public static Boolean mdel(List<String> keys) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();//从连接借用Jedis对象
            Pipeline pipe = jedis.pipelined();//获取jedis对象的pipeline对象
            for(String key : keys){
                pipe.del(key); //将多个key放入pipe删除指令中
            }
            pipe.sync(); //执行命令，pipeline对象的远程调用

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     * example:
     * obj.msetnx(new String[]{"key2","value1","key2","value2"})
     *
     * @param keysvalues
     * @return 成功返回true 失败返回false
     */
    public static Boolean msetnx(String... keysvalues) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = jedis.msetnx(keysvalues) == 1L;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 返回旧值，设置新值
     *
     * @param key key值
     * @param value 新值
     * @return 旧值 如果key不存在 则返回null
     */
    public static String getAndSet(String key, String value) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.getSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过下标 和key 获取指定下标位置的 value
     * example
     * value:zhangsan
     * start=0,end=5 || start=-8,end=-4
     * result:zhangs || zhang
     *
     * @param key key值
     * @param startOffset 开始位置 从0开始 负数表示从右边开始截取
     * @param endOffset 结束位置 包含该位置的字符 负数表示从右边开始截取
     * @return 如果没有返回null
     */
    public static String getrange(String key, int startOffset, int endOffset) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.getrange(key, startOffset, endOffset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在时则创建key并设置值为1
     *
     * @param key key值
     * @return 加值后的结果
     */
    public static Long incr(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key给指定的value加值,如果key不存在,则创建key并设置值为该value值
     *
     * @param key key值
     * @param integer 追加的值
     * @return 加值后的结果
     */
    public static Long incrBy(String key, Long integer) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.incrBy(key, integer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 对key的值做减1操作,如果key不存在,则创建并设置key为-1
     * 支持负数相减
     *
     * @param key key值
     * @return 减值后的结果
     */
    public static Long decr(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.decr(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 减去指定的值,如果key不存在,则创建并设置key为当前设置的值的负值
     * 支持负数相减
     *
     * @param key key值
     * @param integer 具体的值
     * @return 减值后的结果
     */
    public static Long decrBy(String key, Long integer) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.decrBy(key, integer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取value值的长度
     *
     * @param key key值
     * @return 失败返回null
     */
    public static Long strlen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.strlen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建,如果field不存在则创建
     *
     * @param key key值
     * @param field  字段
     * @param value 字段对应的值
     * @return 成功返回true 失败返回false
     */
    public static Boolean hset(String key, String field, String value) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();

            //0-表示存在key和field 1-表示不存在key或field 两者都表示设置成功
            Long result = jedis.hset(key, field, value);
            flag = (result == 0L || result == 1L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,则设置失败
     *
     * @param key key值
     * @param field 字段
     * @param value 字段对应的值
     * @return 成功返回true 失败返回false
     */
    public static Boolean hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        Boolean flag = null;
        try {
            jedis = pool.getResource();

            //1-field不存在，创建field并设置成功 0-表示field已存在，设置失败
            flag = jedis.hsetnx(key, field, value) == 1L;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过key同时设置 hash的多个field
     * 原子操作
     *
     * @param key key值
     * @param hash hash值（field-value组合）
     * @return 成功返回true 失败返回false
     */
    public static Boolean hmset(String key, Map<String, String> hash) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.hmset(key, hash));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key key值
     * @param field value值
     * @return 没有返回null
     */
    public static String hget(String key, String field) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key key值
     * @param fields 可以是 一个String 也可以是 String数组
     * @return List
     */
    public static List<String> hmget(String key, String... fields) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hmget(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key给指定的field的value加上给定的值
     * 若key不存在则创建，若field不存在则创建，并设置field的值为追加的值value
     *
     * @param key key值
     * @param field 字段
     * @param value 追加的值
     * @return 字段追加后的结果
     */
    public static Long hincrby(String key, String field, Long value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key和field判断是否存在
     *
     * @param key key值
     * @param field 字段
     * @return 存在-true 不存在-false
     */
    public static Boolean hexists(String key, String field) {
        Jedis jedis = null;
        Boolean res = false;
        try {
            jedis = pool.getResource();
            res = jedis.hexists(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回field的数量
     *
     * @param key key值
     * @return field数量，若key不存在则返回0
     */
    public static Long hlen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hlen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;

    }

    /**
     * 通过key 删除指定的 field
     *
     * @param key key值
     * @param fields 字段,可以是 一个 field 也可以是 一个数组
     * @return 删除field总数
     */
    public static Long hdel(String key, String... fields) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hdel(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 获取key的所有字段，只返回字段名称
     *
     * @param key key值
     * @return 所有字段名称集合，若key不存在则返回空集合，不是返回null
     */
    public static Set<String> hkeys(String key) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 获取key的所有字段的对应值，不返回字段名称
     *
     * @param key key值
     * @return 所有字段对应的value值,若key不存在则返回空集合,不是返回null
     */
    public static List<String> hvals(String key) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取所有的field和value
     *
     * @param key key值
     * @return 所有field和value值,若key不存在则返回空集合,不是返回null
     */
    public static Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        Map<String, String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key向list头部添加字符串
     *
     * @param key key值
     * @param strs 可以是一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public static Long lpush(String key, String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lpush(key, strs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key key值
     * @param strs 可以是一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public static Long rpush(String key, String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpush(key, strs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key在list指定的位置之前或者之后 添加字符串元素
     *
     * @param key key值
     * @param where LIST_POSITION枚举类型
     * @param pivot list里面的value
     * @param value 添加的value
     * @return
     */
    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.linsert(key, where, pivot, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key设置list指定下标位置的value
     * 如果下标超过list里面value的个数则报错
     *
     * @param key key值
     * @param index 下标位置，从0开始
     * @param value value值
     * @return 成功返回true 失败返回false
     */
    public static Boolean lset(String key, long index, String value) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.lset(key, index, value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 通过key从对应的list中从左边开始删除指定的count个 和 value相同的元素
     * 总共删除的数量为count个
     *
     * @param key key值
     * @param count 当count为0时删除全部
     * @param value value值
     * @return 返回被删除的个数，若没有value值，则返回0
     */
    public static Long lrem(String key, long count, String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lrem(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key保留list中从strat下标开始到end下标结束的value值
     *
     * @param key key值
     * @param start 开始位置，从0开始
     * @param end 结束位置
     * @return 成功返回OK
     */
    public static Boolean ltrim(String key, long start, long end) {
        Jedis jedis = null;
        Boolean flag = false;
        try {
            jedis = pool.getResource();
            flag = OK.equals(jedis.ltrim(key, start, end));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return flag;
    }

    /**
     * 从左边出栈一个值，出栈后该值从列表中删除
     *
     * @param key key值
     * @return 返回出栈的值，若key不存在则返回null
     */
    public static String lpop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 从右边出栈一个值，出栈后该值从列表中删除
     *
     * @param key key值
     * @return 返回出栈的值，若key不存在则返回null
     */
    public static String rpop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key从一个list的右边出栈一个value,然后从另一个list的左边入栈该value,并返回该value
     *
     * @param srckey 第一个list的key
     * @param dstkey 第二个list的key
     * @return 返回出栈和入栈的value，如果第一个list为空或者不存在则返回null
     */
    public static String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpoplpush(srckey, dstkey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return res;
    }

    /**
     * 通过key获取list中指定下标位置的value
     *
     * @param key key值
     * @param index 下标位置，从0开始
     * @return 如果没有返回null
     */
    public static String lindex(String key, long index) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lindex(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回list的长度
     *
     * @param key key值
     * @return 返回list长度，若key不存在则返回0
     */
    public static Long llen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取list指定下标位置的value
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     *
     * @param key key值
     * @param start 开始位置
     * @param end 结束位置
     * @return 结果集合，若key不存在则返回空集合，不是返回null
     */
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key向指定的set中添加value
     *
     * @param key key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public static Long sadd(String key, String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sadd(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key删除set中对应的value值
     *
     * @param key key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public static Long srem(String key, String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.srem(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key随机删除一个set中的value并返回该值
     *(适合抽奖)
     *
     * @param key key
     * @return 弹出的值
     */
    public static String spop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.spop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取set中的差集
     * 以第一个set为标准
     * example:
     * 集合A: a b c d e
     * 集合B: a b c f
     * 结果: d e
     *
     * @param keys
     *          可以是一个string，则返回set中所有的value
     *          也可以是string数组，则返回差集(第一个set和后面的set求差集)
     * @return 差集
     */
    public static Set<String> sdiff(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sdiff(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取set中的差集并存入到另一个key中
     * 以第一个set为标准
     *
     * @param dstkey 差集存入的key
     * @param keys 可以是一个string 则返回set中所有的value 也可以是string数组
     * @return 存入的数量
     */
    public static Long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sdiffstore(dstkey, keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取指定set中的交集
     *
     * @param keys 可以是一个string 也可以是一个string数组
     * @return 交集
     */
    public static Set<String> sinter(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sinter(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取指定set中的交集 并将结果存入新的set中
     *
     * @param dstkey 交集存入的key
     * @param keys 可以是一个string 也可以是一个string数组
     * @return 存入的数量
     */
    public static Long sinterstore(String dstkey, String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sinterstore(dstkey, keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回所有set的并集
     *
     * @param keys 可以是一个string 也可以是一个string数组
     * @return 并集
     */
    public static Set<String> sunion(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sunion(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回所有set的并集,并存入到新的set中
     *
     * @param dstkey 并集存入的key
     * @param keys 可以是一个string 也可以是一个string数组
     * @return 存入的数量
     */
    public static Long sunionstore(String dstkey, String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sunionstore(dstkey, keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key将set中的value移除并添加到第二个set中
     *
     * @param srckey 需要移除的
     * @param dstkey 添加的
     * @param member set中的value
     * @return 若找不到member元素则返回0，成功移除返回1
     */
    public static Long smove(String srckey, String dstkey, String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.smove(srckey, dstkey, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取set中value的个数
     *
     * @param key key
     * @return 元素个数，若key不存在则返回0
     */
    public static Long scard(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key判断value是否是set中的元素
     *
     * @param key key
     * @param member 元素
     * @return 是-true 否-false
     */
    public static Boolean sismember(String key, String member) {
        Jedis jedis = null;
        Boolean res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sismember(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取set中随机的value,不删除元素
     *
     * @param key key
     * @return 随机元素
     */
    public static String srandmember(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.srandmember(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取set中所有的value
     *
     * @param key key
     * @return 所有元素
     */
    public static Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
     *
     * @param key key
     * @param scoreMembers 成员
     * @return 成功添加的数量，若全部都为修改则返回0
     */
    public static Long zadd(String key, Map<String,Double> scoreMembers) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
     *
     * @param key key
     * @param score 分数
     * @param member 成员
     * @return 若为修改则返回0，若为新增则返回1
     */
    public static Long zadd(String key, double score, String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zadd(key, score, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key删除在zset中指定的value
     *
     * @param key key
     * @param members 可以是一个string 也可以是一个string数组
     * @return 删除的数量
     */
    public static Long zrem(String key, String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrem(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key增加该zset中value的score的值
     *
     * @param key key
     * @param score 分数
     * @param member 成员
     * @return 增加后的值
     */
    public static Double zincrby(String key, double score, String member) {
        Jedis jedis = null;
        Double res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zincrby(key, score, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 查看member的排名名次，分数越多的名次越靠后，名次从0开始计起
     *
     * @param key key
     * @param member 成员
     * @return 名次
     */
    public static Long zrank(String key, String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 和zrank相反，查看member的排名名次，分数越多的名次越靠前，名次从0开始计起
     *
     * @param key key
     * @param member 成员
     * @return 名次
     */
    public static Long zrevrank(String key, String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 查看key对应的所有成员和分数，按分数从小到大排序，可用于查询指定排名范围的成员
     * 当start为0 end为-1时返回全部
     *
     * @param key key
     * @param start 开始位置，从0开始
     * @param end 结束位置
     * @return 成员集合（只有成员，没有分数）
     */
    public static Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 和zrange相反，查看key对应的所有成员和分数，按分数从大到小排序，可用于查询指定排名范围的成员
     * 当start为0 end为-1时返回全部
     *
     * @param key key
     * @param start 开始位置，从0开始
     * @param end 结束位置
     * @return 成员集合（只有成员，没有分数）
     */
    public static Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回指定score内zset中的value，按分数从小到大排序
     *
     * @param key key
     * @param min 最小score
     * @param max 最大score
     * @return 成员集合（只有成员，没有分数）
     */
    public static Set<String> zrangeByScore(String key, String min, String max) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 和zrangeByScore相反，通过key返回指定score内zset中的value，按分数从大到小排序
     *
     * @param key key
     * @param max 最大score
     * @param min 最小score
     * @return 成员集合（只有成员，没有分数）
     */
    public static Set<String> zrevrangeByScore(String key, double max, double min) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrangeByScore(key, max, min);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 返回指定区间内zset中value的数量
     *
     * @param key key
     * @param min 最小score
     * @param max 最大score
     * @return value数量
     */
    public static Long zcount(String key, String min, String max) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zcount(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key返回zset中的value个数
     *
     * @param key key
     * @return value数量
     */
    public static Long zcard(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zcard(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key获取zset中value的score值
     *
     * @param key key
     * @param member 成员
     * @return score值
     */
    public static Double zscore(String key, String member) {
        Jedis jedis = null;
        Double res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zscore(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key删除给定区间内的元素
     *
     * @param key key
     * @param start 开始位置，从0开始
     * @param end 结束位置
     * @return 删除的元素数量
     */
    public static Long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zremrangeByRank(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key删除指定score内的元素
     *
     * @param key key
     * @param start 起始score值
     * @param end 末尾score值
     * @return 删除的value数量
     */
    public static Long zremrangeByScore(String key, double start, double end) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zremrangeByScore(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 返回满足pattern表达式的所有key keys(*) 返回所有的key
     * example：
     * *  返回所有的key
     * per*  返回所有以per开头的key
     *
     * @param pattern 表达式
     * @return 所有key
     */
    public static Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 通过key判断值的类型
     *
     * @param key key
     * @return 类型
     */
    public String type(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.type(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 使用pipeline批量获取过期时间
     * @param keyList 键列表
     * @return keyToResponse Map
     */
    public static Map<String, Response<Long>> pipelineTtl(List<String> keyList) {
        Jedis jedis = null;
        Map<String, Response<Long>> responseMap = new HashMap<>();
        try {
            jedis = pool.getResource();
            Pipeline p = jedis.pipelined();
            for (String key : keyList) {
                responseMap.put(key, p.ttl(key));
            }

            //同步查询
            p.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return responseMap;
    }

    /**
     * 返还到连接池
     *
     * @param jedis 连接对象
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
//        System.out.println(JedisUtils.setrange("name", 1, "ma"));
        System.out.println(JedisUtils.mget("name", "person", "age"));
//        JedisUtils.mset(new String[]{"sex", "男", "title", "工程师"});
//        System.out.println(JedisUtils.get("sex"));

//        List<String> keys = new ArrayList<>();
//        keys.add("name");
//        keys.add("title");
//        JedisUtils.mdel(keys);
//        System.out.println(JedisUtils.msetnx(new String[]{"name", "zhangsan", "title", "1"}));

//        System.out.println(JedisUtils.getAndSet("school", "xiada"));

//        System.out.println(JedisUtils.incr("height"));
//        System.out.println(JedisUtils.incrBy("weight", 5L));

//        System.out.println(JedisUtils.decr("age"));
//        System.out.println(JedisUtils.decr("age"));
//        System.out.println(JedisUtils.decrBy("age", 5L));
//        System.out.println(JedisUtils.decrBy("height", 5L));
//        System.out.println(JedisUtils.decrBy("height", 5L));
//        System.out.println(JedisUtils.get("age"));

//        System.out.println(JedisUtils.hsetnx("person", "age", "12"));
//        System.out.println(JedisUtils.hset("person", "name", "lisi"));

//        Map<String,String> hash = new HashMap<>();
//        hash.put("name", "dalomao");
//        hash.put("age", "29");
//        System.out.println(JedisUtils.hmset("person", hash));
//        System.out.println(JedisUtils.hmget("person", new String[] {"name", "age", "weg"}).size());
//        System.out.println(JedisUtils.hincrby("person", "weg", 5L));
//        System.out.println(JedisUtils.hexists("a", "a"));
//        System.out.println(JedisUtils.hlen("person"));
//        System.out.println(JedisUtils.hdel("person", new String[]{"a","name"}));
//        System.out.println(JedisUtils.hkeys("a"));
//        System.out.println(JedisUtils.hvals("person"));
//        System.out.println(JedisUtils.hgetAll("a"));
//        System.out.println(JedisUtils.lpush("person", new String[]{"a","b","c"}));
//        System.out.println(JedisUtils.rpush("person1", new String[]{"a","b","c"}));
//        System.out.println(JedisUtils.lset("person1", 2, "d"));
//        System.out.println(JedisUtils.lrem("person1", 5, "d"));
//        System.out.println(JedisUtils.ltrim("person", 0, 1));
//        System.out.println(JedisUtils.lrange("person", 0, -1));
//        System.out.println(JedisUtils.lpop("person2"));
//        System.out.println(JedisUtils.llen("person1"));
//        System.out.println(JedisUtils.sadd("person", new String[] {"a", "a", "b", "c", "d", "e"}));
//        System.out.println(JedisUtils.sadd("person1", new String[] {"a", "b", "c"}));
//        System.out.println(JedisUtils.sadd("person2", new String[] {"a", "b", "c", "f"}));
//        System.out.println(JedisUtils.sdiff(new String[]{"person", "person2"}));
//        System.out.println(JedisUtils.scard("p"));

//        Map<String,Double> scoreMembers = new HashMap<>();
//        scoreMembers.put("dalomao", 20D);
//        scoreMembers.put("maohw", 30D);
//        scoreMembers.put("supermao", 50D);
//        System.out.println(JedisUtils.zadd("person", scoreMembers));
//        System.out.println(JedisUtils.zadd("person", 60D, "supermao1"));
//        System.out.println(JedisUtils.zrevrank("person", "dalomao"));
//        System.out.println(JedisUtils.zrevrank("person", "maohw"));
//        System.out.println(JedisUtils.zrevrank("person", "supermao"));
//        System.out.println(JedisUtils.zrevrank("person", "supermao1"));
//        System.out.println(JedisUtils.zrangeByScore("person", "30", "60"));
//        System.out.println(JedisUtils.zrevrangeByScore("person", 60, 30));
//        try {
//            Jedis jedis = new Jedis("127.0.0.1", 6379);
//            jedis.auth("123456");
//            System.out.println(jedis.get("name"));
//            System.out.println(jedis.select(1));
//            jedis.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
//        System.out.println(new Random().nextInt(2));

        List<String> keyList = new ArrayList<>();
        keyList.add("key1");
        keyList.add("key2");
        keyList.add("key3");
        JedisUtils.pipelineTtl(keyList);
    }
}
