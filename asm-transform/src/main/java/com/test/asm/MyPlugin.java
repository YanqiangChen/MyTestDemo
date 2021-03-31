package com.test.asm;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("自定义插件‘MyPlugin’加载成功");
        // 注册 Transform, AppExtension 依赖 gradle，所以该模块需要导入 gradle
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new MyTransform());


    }
}
