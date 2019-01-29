package com.dalomao.thread.forkjoin.sunwukong.Impl;


import com.dalomao.thread.forkjoin.service.IPickTaoZi;
import com.dalomao.thread.forkjoin.service.IProcessTaoZi;
import com.dalomao.thread.forkjoin.vo.Color;
import com.dalomao.thread.forkjoin.vo.PanTao;
import com.dalomao.thread.forkjoin.vo.Size;

/**
 *
 */
public class WuKongPickImpl implements IPickTaoZi {

    private IProcessTaoZi processTaoZi;

    public WuKongPickImpl(IProcessTaoZi processTaoZi) {
        this.processTaoZi = processTaoZi;
    }

    @Override
    public boolean pick(PanTao[] src, int index) {
        if(src[index].getColor()== Color.RED&&
                src[index].getSize()== Size.BIG&&
                src[index].getYear()>=6000){
            processTaoZi.processTaoZi(src[index]);
            return true;
        }else{
            return false;
        }
    }
}
