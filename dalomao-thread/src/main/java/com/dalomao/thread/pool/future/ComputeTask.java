package com.dalomao.thread.pool.future;

import java.util.concurrent.Callable;

/**
 * <p>Package: com.dalomao.demo.thread.pool.future</p>
 * <p>Description:TODO </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2019/1/28
 **/
public class ComputeTask implements Callable<Integer> {
    private Integer result =0;
    private String taskName ="";

    public ComputeTask(Integer result, String taskName) {
        this.result = result;
        this.taskName = taskName;
        System.out.println(taskName+"子任务已经创建");
    }

    @Override
    public Integer call() throws Exception {
        for(int i=0;i<100;i++){
            result = result+i;
        }
        Thread.sleep(2000);
        System.out.println(taskName+"子任务已经完成");
        return  result;
    }
}
