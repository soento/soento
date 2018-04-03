package com.soento.core.annotation;

import com.soento.core.consts.AuthType;
import com.soento.core.lang.Privilege;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    String text() default "";

    String parent() default Privilege.ROOT;

    String type() default AuthType.DATA;

    String icon() default "";

    int sort() default 0;
}
