package com.dalomao.spring.extend.factorypostprocessor;

/**
 * Created by maohw on 2018/8/29.
 */
public class BeanFactoryPostProcessorVO {
    private String userName;
    private Integer age;
    private String school;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
