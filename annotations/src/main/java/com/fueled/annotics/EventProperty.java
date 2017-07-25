/*
 * Created by hussein@fueled.com on 03/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used to specify a custom name for a parameter inside a method that's being
 * tracked using the {@link TrackEvent} annotation.
 *
 * For example,
 * <pre><code>
 * @TrackEvent("EVENT_UPDATE_ACCOUNT")
 * public void updateAccount(@EventProperty("First Name") String firstName) {
 *  ...
 * }
 * </code></pre>
 */
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface EventProperty {

    /**
     * The name to be used for the parameter when added as a property to the event.
     * This is optional and by default the name of the parameter is used.
     */
    String value() default "";

}
