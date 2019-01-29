package com.dalomao.thread.forkjoin.bajie.impl;


import com.dalomao.thread.forkjoin.service.IPickTaoZi;
import com.dalomao.thread.forkjoin.service.IProcessTaoZi;
import com.dalomao.thread.forkjoin.vo.Color;
import com.dalomao.thread.forkjoin.vo.PanTao;
import com.dalomao.thread.forkjoin.vo.Size;

/**
 *
 */
public class BaJiePickImpl implements IPickTaoZi {

    private IProcessTaoZi processTaoZi;

    public BaJiePickImpl(IProcessTaoZi processTaoZi) {
        this.processTaoZi = processTaoZi;
    }

    @Override
    public boolean pick(PanTao[] src, int index) {
        if(src[index].getColor()== Color.RED&&
                src[index].getSize()== Size.BIG){
            processTaoZi.processTaoZi(src[index]);
            return true;
        }else{
            return false;
        }
    }
}
