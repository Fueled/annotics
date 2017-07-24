/*
 * Created by hussein@fueled.com on 12/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specify a shared static value, to be added to all events within a class.
 *
 * For example,
 * <pre><code>
 * @SharedAttributes({
 *       @SharedValue(key = "SCREEN_NAME", value = "HOME")
 * })
 * public class TrackedClass {
 *  ...
 * }
 * </code></pre>
 */
@Target({LOCAL_VARIABLE})
@Retention(RUNTIME)
public @interface SharedValue {

    /**
     * The key to be used for this shared property.
     */
    String key();

    /**
     * The static value to used for this shared property.
     */
    String value();

}