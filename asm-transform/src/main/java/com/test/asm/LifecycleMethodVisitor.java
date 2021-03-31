package com.test.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleMethodVisitor extends MethodVisitor {

    /** 当前类名称 */
    private String className;
    /** 当前方法名称 */
    private String methodName;

    /** 当前是否为注解方法 */
    private boolean mInject;

    private static String mValue;

    public LifecycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM6, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        // 判断是否为指定注解类
//        if (Type.getDescriptor(InjectTimeStatistics.class).equals(desc)) {
//            System.out.println(desc);
//            mInject = true;
//        }
        // 也可以判断某个系列的注解
        if (desc.contains("InjectTimeStatistics")) {
            mInject = true;
        }
        return new MyAnnotationVisitor(super.visitAnnotation(desc, visible));
//        return super.visitAnnotation(desc, visible);
    }

    /** 方法执行前插入 */
    @Override
    public void visitCode() {
        super.visitCode();
        if (mInject) {
            mv.visitLdcInsn(className + " -> TAG");
            mv.visitLdcInsn("注解的值为:" + mValue);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(Opcodes.POP);
        }
    }

    /** 方法执行后插入 */
    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN && mInject) {
            mv.visitLdcInsn(className + " -> TAG");
            mv.visitLdcInsn("注解的值为:" + mValue);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(Opcodes.POP);
        }
        super.visitInsn(opcode);

    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }


    public static class MyAnnotationVisitor extends AnnotationVisitor {

        public MyAnnotationVisitor( AnnotationVisitor av) {
            super(Opcodes.ASM5,av);
        }

        /**
         * 读取注解的值
         */
        @Override
        public void visit(String name, Object value) {
            super.visit(name, value);
            mValue=value+"";
            System.out.println(name + " = " + value );
        }
        /*
         * 注解枚举的类型的值
         */
        @Override
        public void visitEnum(String name, String desc, String value) {
            super.visitEnum(name, desc, value);
            System.out.println("name ="+ name + ", desc="+desc +" , value=" + value);
//            MyAnnotation(id = 1 , message="message" , names = {"A" , "B" ,"C"} , color = Color.BLUE)

//            id = 1
//            message = message
//            Array:names
//            null = A
//            null = B
//            null = C
//            name =color, desc=Lcom/example/annotation/Color; , value=BLUE

        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String desc) {
            return super.visitAnnotation(name, desc);
        }
        /**
         * 注解数组类型的值
         */
        @Override
        public AnnotationVisitor visitArray(String name) {
            System.out.println("Array:" + name );
            return new MyArrayAnnotationVisitor(super.visitArray(name));
        }

    }

    public static class MyArrayAnnotationVisitor extends AnnotationVisitor{

        public MyArrayAnnotationVisitor(AnnotationVisitor av) {
            super(Opcodes.ASM5 , av);
        }
        /**
         * 读取数组的内容
         */
        @Override
        public void visit(String name, Object value) {
            super.visit(name, value);
            System.out.println(name + " = " + value );
        }


    }
}
