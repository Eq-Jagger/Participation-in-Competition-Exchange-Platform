package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
这个代码定义了一个自定义注解 @ColumnInfo，用于为类的字段提供额外的元数据信息，
主要包括字段的注释（comment）和类型（type）。该注解可以应用于类的字段，主要作用如下：

目标元素：@Target(ElementType.FIELD) 表示该注解只能用于类的字段上。
         这意味着可以在类的属性上加上 @ColumnInfo，为该字段附加额外的信息。

保留策略：@Retention(RetentionPolicy.RUNTIME) 表示该注解会在运行时保留。
         这允许在运行时通过反射机制访问注解的信息，通常用于动态处理或生成元数据。

注解属性：
String comment(): 用于描述字段的注释或说明信息，可以帮助开发者了解字段的作用。
String type(): 用于指定字段的数据类型或数据库中的类型，可以为后续处理提供数据类型信息。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {
    String comment();
    String type();
}
