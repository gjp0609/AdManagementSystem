package me.rainbow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guojinpeng
 * @date 18.1.4 14:37
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    String name();

    String memo() default "";

//    String format() default "";

    /**
     * 内容替换，
     */
    String[] replace() default {};

//    String group() default "";

//    /**
//     * 在表格中的先后顺序，默认0无序
//     */
//    int order() default 0;
}
