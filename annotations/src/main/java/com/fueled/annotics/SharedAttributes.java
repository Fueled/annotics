/*
 * Created by hussein@fueled.com on 12/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used to specify shared static attributes between all events within a class. These attributes
 * will then be added to every event in the class as properties.
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
@Target({TYPE})
@Retention(RUNTIME)
public @interface SharedAttributes {

    /**
     * A list of shared attributes to be added to events in the annotated class.
     */
    SharedValue[] value();
}
