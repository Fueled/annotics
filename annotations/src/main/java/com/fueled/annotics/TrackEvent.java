package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hussein@fueled.com on 03/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface TrackEvent {

    String value();

    EventType type() default EventType.TRACK;

}
