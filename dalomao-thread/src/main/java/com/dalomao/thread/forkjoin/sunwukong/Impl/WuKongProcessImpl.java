package com.dalomao.thread.forkjoin.sunwukong.Impl;


import com.dalomao.thread.forkjoin.service.IProcessTaoZi;
import com.dalomao.thread.forkjoin.vo.PanTao;

/**
 *
 */
public class WuKongProcessImpl implements IProcessTaoZi {
    @Override
    public void processTaoZi(PanTao taoZi) {
        //看看桃子，放到口袋里
        inBag();
    }

    private void inBag(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
