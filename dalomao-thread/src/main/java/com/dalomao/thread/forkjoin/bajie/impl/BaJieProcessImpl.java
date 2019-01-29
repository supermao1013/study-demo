package com.dalomao.thread.forkjoin.bajie.impl;


import com.dalomao.thread.forkjoin.service.IProcessTaoZi;
import com.dalomao.thread.forkjoin.vo.PanTao;

/**
 *
 */
public class BaJieProcessImpl implements IProcessTaoZi {
    @Override
    public void processTaoZi(PanTao taoZi) {
        //看看桃子，一口吃了
        eat();
    }

    //看看桃子，一口吃了
    private void eat(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
