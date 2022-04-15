package com.spring.redis.util;

import java.lang.annotation.*;

/**
 * 日志收集的自定义注解 【可以根据实际情况去定义】
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 事件类型，英文名，如ADD_USER,DELETE_DICTIONARY
     */
    String eventType() default "";
    /**
     * 目标方法的参数意义，与目标方法的参数一起使用，数组长度需与参数长度一致
     */
    String[] detailArgs() default {};
}
