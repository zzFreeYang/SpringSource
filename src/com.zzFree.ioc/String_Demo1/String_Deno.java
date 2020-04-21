package com.zzFree.ioc.String_Demo1;

public class String_Deno {
    String A = "";
    String B = "1" ;


    void Demo1(){


        System.out.println("A:" + A);
        System.out.println("B:" + B);
        Demo2();
    }

    void Demo2(){
        System.out.println("=====Demo2执行========");

        System.out.println("------改变AB------");
        A = "A";
        B = "B" ;
        System.out.println("A:" + A);
        System.out.println("B:" + B);

     }

}
