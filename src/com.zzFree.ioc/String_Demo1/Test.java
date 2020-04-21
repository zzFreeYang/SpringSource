package com.zzFree.ioc.String_Demo1;

public class Test {
    public static void main(String[] args) {
        Test t1 = new Test();
        String result = "a";
        t1.addNumber();
        System.out.println("result is: "+ result);
    }
     public void addNumber(){
       String tesultv2 = "B";
        System.out.println("resultv2 is " + tesultv2);
    }

    }

