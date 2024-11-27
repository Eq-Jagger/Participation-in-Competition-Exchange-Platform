package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录用户信息
 这个代码定义了一个自定义注解 @APPLoginUser，用于在参数级别标注需要登录用户信息的地方。
 注解的具体作用如下：

 目标元素：@Target(ElementType.PARAMETER) 指定此注解只能用于方法参数，表示该参数是与登录用户信息相关的。

 保留策略：@Retention(RetentionPolicy.RUNTIME) 指定注解在运行时保留。
          这意味着在运行时可以通过反射机制获取此注解的信息，以便在方法执行时进行额外的逻辑处理。

 用途：通常用于标识某个方法参数，告诉系统该参数应该包含登录用户的信息。
      在实际使用中，结合 Spring AOP 或拦截器机制，可以在控制器的方法参数上加上 @APPLoginUser，
    然后在请求处理时自动注入当前登录的用户信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface APPLoginUser {

}
