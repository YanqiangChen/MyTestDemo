package com.test.mytestdemo.decoration;

public class Test {
    public void test(){
        ABpancakes aBpancakes;//创建一个抽象的煎饼实体类
        aBpancakes=new Pancakes();//创建一个具体的煎饼实体类
        aBpancakes=new EggDecorator(aBpancakes);//创建一个加鸡蛋的煎饼类
        aBpancakes=new EggDecorator(aBpancakes);//再创建一个加鸡蛋的煎饼类
        aBpancakes=new SausageDecorator(aBpancakes);//再创建一个加香肠的煎饼类
        System.out.println(aBpancakes.getDesc()+" 销售价格:"+aBpancakes.getPrice());

    }

}
