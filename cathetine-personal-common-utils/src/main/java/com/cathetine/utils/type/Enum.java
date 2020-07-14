package com.cathetine.utils.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标注枚举类型值
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    /**
     *  枚举类型描述信息
     */
    String value() default "";

    /**
     *  枚举类型描述信息
     */
    String desc() default "";

    /**
     * 排序序号 按从小到大排序
     * @return
     */
    int order() default 0;

}
