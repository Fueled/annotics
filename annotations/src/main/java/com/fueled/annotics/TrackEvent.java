/*
 * Created by hussein@fueled.com on 03/07/2017.
 * Copyright (c) 2017 Fueled.
 */
package com.fueled.annotics;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotates methods in a class to indicate to annotics that you would like this method to be
 * tracked as an event.
 *
 * For example,
 * <pre><code>
 * @TrackEvent(value = "EVENT_UPDATE_CARD_INFO", trackParameters = false)
 * public void updateCardInformation(String cardNumber, String expDate, String cvv) {
 *  ...
 * }
 * </code></pre>
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface TrackEvent {

    /**
     * The name you want to use for this event.
     */
    String value();

    /**
     * The type of the event. It can be one of {@link EventType}.
     * This is optional and by default {@link EventType#TRACK} is used.
     */
    EventType type() default EventType.TRACK;

    /**
     * Indicates whether you would like the method parameters to be tracked and added as properties
     * to the event. This is optional and is set to {@code true} by default.
     */
    boolean trackParameters() default true;

}
