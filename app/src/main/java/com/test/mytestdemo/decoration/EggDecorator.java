package com.test.mytestdemo.decoration;

public class EggDecorator extends AbstractDecorator{
    public EggDecorator(ABpancakes aBpancakes) {
        super(aBpancakes);
    }
    @Override
    protected String getDesc() {
        return super.getDesc()+"加一个鸡蛋,";
    }
    @Override
    protected int getPrice() {
        return super.getPrice()+1;
    }
}
