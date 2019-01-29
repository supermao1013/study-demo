## dalomao-id-generate
* 多种主键ID生成的方式，适用于单机、分布式环境
* 可以根据业务的实际情况使用不同的方式

## 类说明
 ### UUIDServiceRedisImpl
 * 使用redis的Incr方式自增主键
 * 允许生成指定长度的主键，适合单机、分布式下的高并发环境
 * redis官网介绍：Incr是同步的原子操作，可以保证唯一性

### UUIDServiceSnowflakeImpl
 * 使用Twitter的雪花算法方式
 * 1位标识+41位时间戳差值+10位数据机器位+毫秒内计数的12位序列，总共64位，刚好为一个Long类型
 * 适合单机、分布式下的高并发环境

### UUIDServiceUUIDImpl
 * 使用JDK自带的UUID方式
 * 为数字+字母的字符串形式，因此不适合排序
 * 适合单机下的高并发环境

### CustomIdUtils
 * 自定义的UUID生成方式，仿照雪花算法
 * 13位时间戳+毫秒内计数的5位序列，总共18位
 * 适合单机环境下的高并发环境