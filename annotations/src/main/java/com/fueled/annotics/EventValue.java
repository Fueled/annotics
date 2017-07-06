package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hussein@fueled.com on 03/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface EventValue {

    String value() default "";

    boolean hidden() default false;

}
