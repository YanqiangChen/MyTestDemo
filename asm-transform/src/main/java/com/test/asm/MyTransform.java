package com.test.asm;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 自定义Transfrom
 *
 * @author XGY
 * @since 17:31
 */
public class MyTransform extends Transform {

    /** 当前Transform名称 */
    @Override
    public String getName() {
        return com.test.asm.MyTransform.class.getSimpleName();
    }

    /** 输入文件类型，有CLASSES和RESOURCES */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    /**
     * 指Transform要操作内容的范围，官方文档Scope有7种类型：
     * <p>
     * EXTERNAL_LIBRARIES        只有外部库
     * PROJECT                   只有项目内容
     * PROJECT_LOCAL_DEPS        只有项目的本地依赖(本地jar)
     * PROVIDED_ONLY             只提供本地或远程依赖项
     * SUB_PROJECTS              只有子项目。
     * SUB_PROJECTS_LOCAL_DEPS   只有子项目的本地依赖项(本地jar)。
     * TESTED_CODE               由当前变量(包括依赖项)测试的代码
     * SCOPE_FULL_PROJECT        整个项目
     */
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    /** 指明当前Transform是否支持增量编译 */
    @Override
    public boolean isIncremental() {
        return false;
    }

    /** transform进行干预文件 */
    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException,
            InterruptedException, IOException {
        super.transform(transformInvocation);
        // inputs中是传过来的输入流，其中有两种格式，一种是jar包格式一种是目录格式。
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        // 获取到输出目录，最后将修改的文件复制到输出目录，这一步必须做不然编译会报错
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        // 循环遍历输入流
        for (TransformInput input : inputs) {
            // 处理Jar中的class文件
            for (JarInput jarInput : input.getJarInputs()) {
                File dest = outputProvider.getContentLocation(
                        jarInput.getName(),
                        jarInput.getContentTypes(),
                        jarInput.getScopes(),
                        Format.JAR);
                // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                FileUtils.copyFile(jarInput.getFile(), dest);
            }
            // 处理文件目录下的class文件
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                // 处理文件目录下的class文件
                handleDirectoryInput(directoryInput, outputProvider);
            }
        }
    }

    /** 临时文件集合 */
    private List<File> mTemporaryFiles = new ArrayList<>();

    /** 处理文件目录下的class文件 */
    private void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) throws IOException {
        // 列出目录所有文件（包含子文件夹，子文件夹内文件）
        File dir = directoryInput.getFile();

        // 判断是否为目录
        if (directoryInput.getFile().isDirectory()) {
            // 查找目录下面所有的文件
            mTemporaryFiles.clear();
            traverseToFindFiles(dir);
            // 遍历所有文件
            for (File file : mTemporaryFiles) {
                // 处理相应文件
                processingTheCorrespondingFile(file);
            }
        }
        // 判断是否为文件
        else if (dir.isFile()) {
            // 处理相应文件
            processingTheCorrespondingFile(dir);
        } else {
            return;
        }
        // Transform 拷贝文件到 transforms 目录
        File dest = outputProvider.getContentLocation(
                directoryInput.getName(),
                directoryInput.getContentTypes(),
                directoryInput.getScopes(),
                Format.DIRECTORY);
        // 将修改过的字节码copy到dest，实现编译期间干预字节码
        FileUtils.copyDirectory(directoryInput.getFile(), dest);
    }

    /** 处理相应文件 */
    private void processingTheCorrespondingFile(File file) throws IOException {
        // 获取当前文件名称
        String fileName = file.getName();
        // 判断当前文件是否符合要求
        if (checkClassFile(fileName)) {
            // 打印当前符合条件的文件名称
            System.out.println("符合条件的类：" + fileName);
            // 准备待分析的class,进行ASM处理
            FileInputStream fis = new FileInputStream(file);
            // 对class文件进行读取与解析
            ClassReader classReader = new ClassReader(fis);
            // 对class文件的写入
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            // 访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
            ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter);
            // 依次调用 ClassVisitor接口的各个方法
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
            // toByteArray方法会将最终修改的字节码以 byte 数组形式返回。
            byte[] bytes = classWriter.toByteArray();
            // 通过文件流写入方式覆盖掉原先的内容，实现class文件的改写。
//                FileOutputStream outputStream = new FileOutputStream( file.parentFile.absolutePath + File.separator + fileName)
            // 这个地址在javac目录下
            FileOutputStream outputStream = new FileOutputStream(file.getPath());
            // 写入流
            outputStream.write(bytes);
            // 关闭流
            outputStream.close();
        }
    }

    /** 遍历查找问题 */
    private void traverseToFindFiles(File dir) {
        // 获取所有目录
        File[] files = dir.listFiles();
        // 遍历所有目录节点
        for (File file : files) {
            // 判断是否为目录
            if (file.isDirectory()) {
                // 若是目录，则递归该目录下的文件
                traverseToFindFiles(file);
            }
            // 判断是否为文件
            else if (file.isFile()) {
                // 若是文件，载入集合
                mTemporaryFiles.add(file);
            }
        }
    }

    /** 检查class文件是否符合条件 */
    private boolean checkClassFile(String name) {
        return name.endsWith("Activity.class");
    }
}
