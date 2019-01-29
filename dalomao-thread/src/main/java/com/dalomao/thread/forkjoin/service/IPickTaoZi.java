package com.dalomao.thread.forkjoin.service;


import com.dalomao.thread.forkjoin.vo.PanTao;

/**
 *
 * 摘桃子的接口
 */
public interface IPickTaoZi {
    boolean pick(PanTao[] src, int index);
}
