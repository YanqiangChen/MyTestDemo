package com.test.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleClassVisitor extends ClassVisitor {

    private String className;

    public LifecycleClassVisitor(ClassVisitor cv) {
        /**
         * 参数1：ASM API版本，源码规定只能为4，5，6，7
         * 参数2：ClassVisitor 不能为 null
         */
        super(Opcodes.ASM7, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("1 ===========================================================");
        System.out.println("name:" + name + " superName:" + superName + " signature:" + signature + " interfaces:" + interfaces);

        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("2 ===========================================================");
        System.out.println("name:" + name + " desc:" + desc + " signature:" + signature + " exceptions:" + exceptions);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        // name为方法名，desc为描述（签名）, 处理方法
        return new LifecycleMethodVisitor(mv, className, name);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }



}