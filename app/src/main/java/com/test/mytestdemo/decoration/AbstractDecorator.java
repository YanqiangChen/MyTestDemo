package com.test.mytestdemo.decoration;

public class AbstractDecorator extends ABpancakes{

    //通过构造方法注入抽象的煎饼实体类
    private  ABpancakes aBpancakes;
    public AbstractDecorator(ABpancakes aBpancakes) {
        this.aBpancakes = aBpancakes;
    }

    @Override
    protected String getDesc() {
        return this.aBpancakes.getDesc();//获取描述
    }

    @Override
    protected int getPrice() {
        return this.aBpancakes.getPrice();//获取价格
    }
}
