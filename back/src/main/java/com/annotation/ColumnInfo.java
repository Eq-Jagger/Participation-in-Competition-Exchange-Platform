package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
������붨����һ���Զ���ע�� @ColumnInfo������Ϊ����ֶ��ṩ�����Ԫ������Ϣ��
��Ҫ�����ֶε�ע�ͣ�comment�������ͣ�type������ע�����Ӧ��������ֶΣ���Ҫ�������£�

Ŀ��Ԫ�أ�@Target(ElementType.FIELD) ��ʾ��ע��ֻ����������ֶ��ϡ�
         ����ζ�ſ�������������ϼ��� @ColumnInfo��Ϊ���ֶθ��Ӷ������Ϣ��

�������ԣ�@Retention(RetentionPolicy.RUNTIME) ��ʾ��ע���������ʱ������
         ������������ʱͨ��������Ʒ���ע�����Ϣ��ͨ�����ڶ�̬���������Ԫ���ݡ�

ע�����ԣ�
String comment(): ���������ֶε�ע�ͻ�˵����Ϣ�����԰����������˽��ֶε����á�
String type(): ����ָ���ֶε��������ͻ����ݿ��е����ͣ�����Ϊ���������ṩ����������Ϣ��
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {
    String comment();
    String type();
}
