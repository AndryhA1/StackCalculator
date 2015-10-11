package com.suhorukov.dubovik.calc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD )
public @interface PreCondition {

    int argSize() default 1;
    int minStackSize() default 0;
}
