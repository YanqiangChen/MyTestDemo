package com.test.mytestdemo.serializable.gson;

public class TestModel {
    String json="{\n" +
            "\"name\":\"张三\",\n" +
            "\"others\":{\"phone\":\"13888888888\",\n" +
            "\"address\":\"北京\"}\n" +
            "}";
    String name;
    Others others;

    public static class Others {
        String phone;
        String address;
    }
}
