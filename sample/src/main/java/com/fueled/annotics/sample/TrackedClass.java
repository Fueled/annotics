package com.fueled.annotics.sample;

import com.fueled.annotics.EventType;
import com.fueled.annotics.EventValue;
import com.fueled.annotics.TrackEvent;

/**
 * Created by hussein@fueled.com on 06/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class TrackedClass {

    /**
     * Track screen view event. All parameters will be added as event properties.
     */
    @TrackEvent(value = "ACCOUNT_SCREEN", type = EventType.SCREEN_VIEW)
    public TrackedClass(String title) {

    }

    /**
     * Track a normal event, but hide all method parameters from the event.
     */
    @TrackEvent(value = "EVENT_UPDATE_CARD_INFO", trackParameters = false)
    public void updateCardInformation(String cardNumber, String expDate, String cvv) {

    }

    /**
     * Only specific parameters can also be hidden using the {@link EventValue} annotation and
     * specifying the {@link EventValue#hidden()} as {@code true}.
     */
    @TrackEvent("EVENT_LOGIN")
    public void login(String email, @EventValue(hidden = true) String password) {

    }

    /**
     * It's also possible to specify custom names for the parameters by using the
     * {@link EventValue} annotation and specifying a value for the name to be used.
     */
    @TrackEvent("EVENT_UPDATE_ACCOUNT")
    public void updateAccount(@EventValue("First Name") String firstName,
                              @EventValue("Last Name") String lastName) {
    }

}
