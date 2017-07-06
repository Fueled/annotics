package com.fueled.annotics;

/**
 * Created by hussein@fueled.com on 04/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public interface AnalyticsConsumer {

    void identify(String userId);

    void identify(String userId, UserData userData);

    void track(String event);

    void track(String event, EventData eventData);

    void screen(String name);

    void screen(String name, EventData eventData);

    void rest();

}
