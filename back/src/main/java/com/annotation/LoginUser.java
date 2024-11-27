package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录用户信息
 这个代码定义了一个自定义注解 @LoginUser，用于在方法参数上标记当前登录的用户信息。
 该注解的主要功能和作用如下：

 目标元素：@Target(ElementType.PARAMETER) 表示该注解只能用于方法的参数上，
          通常用于控制器方法的参数，标识此参数应该包含登录用户的信息。

 保留策略：@Retention(RetentionPolicy.RUNTIME) 指定该注解会在运行时保留，
         以便在运行时通过反射机制获取该注解信息，通常用于注入当前登录用户的信
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
