package com.test.mytestdemo.decoration;

public class SausageDecorator extends AbstractDecorator{
    public SausageDecorator(ABpancakes aBpancakes) {
        super(aBpancakes);
    }
    @Override
    protected String getDesc() {
        return super.getDesc()+"加一根香肠,";
    }
    @Override
    protected int getPrice() {
        return super.getPrice()+2;
    }
}
