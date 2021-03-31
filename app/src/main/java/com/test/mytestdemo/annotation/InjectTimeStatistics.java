package com.test.mytestdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义时长统计注解
 *
 * @author XGY
 * @since 12:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface InjectTimeStatistics {

    String value();

}
