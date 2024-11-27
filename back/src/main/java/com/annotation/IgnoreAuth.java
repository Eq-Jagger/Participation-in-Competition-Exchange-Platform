package com.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证

 这个代码定义了一个自定义注解 @IgnoreAuth，用于标识不需要进行 Token 验证的方法。
     注解的作用如下：
 目标元素：@Target(ElementType.METHOD) 指定此注解只能用于方法上，表明该方法可以跳过 Token 验证。
           一般在控制器方法上使用，用于标记不需要用户登录或身份验证的接口。
 保留策略：@Retention(RetentionPolicy.RUNTIME) 指定注解在运行时保留。
          这意味着在运行时可以通过反射机制检测该注解，通常用于在拦截器或过滤器中做验证控制。
 文档化：@Documented 表示使用该注解的方法会被包含在 Javadoc 文档中，
        方便在 API 文档中体现出此方法跳过 Token 验证的特性。

 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
