package com.test.mytestdemo.annotation;

public class Test {
    @MyTest(name = "zhang")
    private String name;

    @MyTest(name = "zhang@example.com")
    private String email;


    @MyTest(name = "sayHelloWorld")
    public String sayHello(){
        return "";
    }

}
