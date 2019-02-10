package com.dalomao.proxy.statical;

/**
 * <p>Package: com.dalomao.demo.proxy.statical</p>
 * <p>Description:苍老师实现类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: TODO</p>
 *
 * @author maohw
 * @version 1.0
 * @date 2018/12/2
 **/
public class TeacherCangImpl implements Girl {
    @Override
    public boolean dating(float length) {
        if (length >= 1.7F) {
            System.out.println("身高可以，可以约！");
        }

        System.out.println("身高不可以，不可约！");
        return false;
    }
}
