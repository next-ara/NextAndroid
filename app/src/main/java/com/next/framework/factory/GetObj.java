package com.next.framework.factory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:获取对象注解
 *
 * @author Afton
 * @time 2024/5/23
 * @auditor
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetObj {

    String objKey();
}