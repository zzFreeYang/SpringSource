package com.zzFree.ioc.beans;

/**
 * Set / get   可写和 可读 方法
1. BeanInfo


 */



public class Person {

    public String name; //Property
    public Integer age;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
