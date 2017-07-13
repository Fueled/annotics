package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hussein@fueled.com on 12/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
@Target({LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface SharedValue {

    String key();

    String value();

}