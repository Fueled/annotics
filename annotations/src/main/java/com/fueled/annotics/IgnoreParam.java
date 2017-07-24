/*
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author arun@fueled.com
 */
package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicate to annotics that a specific parameter within a tracked method should not be added
 * as a property to the event. This should be used to hide sensitive information that should not
 * be tracked, such as user password or payment card number.
 *
 * For example,
 * <pre><code>
 * @TrackEvent("EVENT_LOGIN")
 * public void login(String email, @IgnoreParam String password) {
 *  ...
 * }
 * </code></pre>
 */
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface IgnoreParam {
}
