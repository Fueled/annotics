package com.fueled.annotics;

/**
 * Created by hussein@fueled.com on 04/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public interface AnalyticsAdapter {

    /**
     * Link a user to all upcoming events by using a unique {@code userId} to identify the user with.
     *
     * @param userId Unique id to be used to identify the user from.
     */
    void identify(String userId);

    /**
     * Link a user to all upcoming events by using a unique {@code userId} to identify the user with.
     * This also lets you specify custom attributes for the user, such as email, name and so on.
     *
     * @param userId   Unique id to be used to identify the user from.
     * @param userData Contains other user info such as email, name and so on.
     */
    void identify(String userId, UserData userData);

    /**
     * Record a user event using the specified {@code eventName}.
     *
     * @param eventName the name to be used to record this event.
     */
    void track(String eventName);

    /**
     * Record a user event using the specified {@code eventName}. This also let's you specify
     * custom properties with the event.
     *
     * @param eventName the name to be used to record this event.
     * @param eventData {@link EventData} to add extra information to this call.
     */
    void track(String eventName, EventData eventData);

    /**
     * Record a screen view event using the specified {@code screenName}.
     *
     * @param screenName the name to be used for the screen viewed.
     */
    void screen(String screenName);

    /**
     * Record a screen view event using the specified {@code screenName}. This also let's you specify
     * custom properties with the event.
     *
     * @param screenName the name to be used for the screen viewed.
     * @param eventData  {@link EventData} to add extra information to this call.
     */
    void screen(String screenName, EventData eventData);

    /**
     * Resets any information being stored about the user by the analytics adapter.
     */
    void rest();

}
