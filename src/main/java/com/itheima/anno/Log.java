package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //表示该注解写在方法上
@Retention(RetentionPolicy.RUNTIME) //表示运行期依然可以使用该注解
public @interface Log {
}
