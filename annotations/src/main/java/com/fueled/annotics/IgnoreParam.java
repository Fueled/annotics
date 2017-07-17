package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author arun@fueled.com
 */

@Target({PARAMETER})
@Retention(RUNTIME)
public @interface IgnoreParam {
}
