package com.test.mytestdemo.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Student2 implements Externalizable {
    private static final long serialVersionUID = -7424420983806112577L;
    private String name;
    private int age;

    public Student2(){

    }
    public Student2(String name, int age){
        this.name = name;
        this.age = age;

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("person writeExternal...");
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        System.out.println("person readExternal...");
        name = (String) in.readObject();
        age = in.readInt();
    }
}
