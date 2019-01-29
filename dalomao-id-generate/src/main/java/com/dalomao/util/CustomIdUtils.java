package com.dalomao.util;

/**
 * Created by maohw on 2019/1/29.
 * 模仿雪花算法：13位时间戳+自定义长度的毫米内计算序列
 */
public class CustomIdUtils {
    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;

    /** 毫秒内序列值 */
    private static long sequence = 0L;

    /** 毫秒内序列值长度，可自定义 */
    private static int sequenceSize = 5;

    /**
     * 获取主键
     * @return 返回13位时间戳（毫秒）+自定义长度的毫米内计算序列
     */
    public synchronized static long getId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1;
            //毫秒内序列溢出
            if (Long.toString(sequence).length() > sequenceSize) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //拼接
        String nowStr = "";
        int zeroSize = sequenceSize - Long.toString(sequence).length();
        for (int i=0; i<zeroSize; i++) {
            nowStr += "0";
        }
        nowStr = Long.toString(timestamp) + nowStr + Long.toString(sequence);

        return Long.parseLong(nowStr);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(CustomIdUtils.getId());
    }
}
