package com.fueled.annotics.google;

import android.support.annotation.NonNull;

import com.fueled.annotics.AnalyticsConsumer;
import com.fueled.annotics.DataMap;
import com.fueled.annotics.EventData;
import com.fueled.annotics.UserData;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Map;

/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class GoogleConsumer implements AnalyticsConsumer {

    private Tracker delegate;

    public GoogleConsumer(@NonNull Tracker tracker) {
        this.delegate = tracker;
    }

    @Override
    public void identify(String userId) {
        delegate.set("&uid", userId);
    }

    @Override
    public void identify(String userId, UserData userData) {
        identify(userId);
    }

    @Override
    public void track(String event) {
        delegate.send(new HitBuilders.EventBuilder().setAction(event).build());
    }

    @Override
    public void track(String event, EventData eventData) {
        delegate.send(populateHitBuilder(
                new HitBuilders.EventBuilder().setAction(event),
                eventData
        ).build());
    }

    @Override
    public void screen(String name) {
        delegate.setScreenName(name);
        delegate.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void screen(String name, EventData eventData) {
        delegate.setScreenName(name);
        delegate.send(populateHitBuilder(
                new HitBuilders.ScreenViewBuilder(),
                eventData
        ).build());
    }

    @Override
    public void rest() {
        identify(null);
    }

    private <T extends HitBuilders.HitBuilder> HitBuilders.HitBuilder<T> populateHitBuilder(
            HitBuilders.HitBuilder<T> builder, DataMap eventData) {
        for (Map.Entry<String, Object> entry : eventData.entrySet()) {
            builder.set(entry.getKey(), entry.getValue().toString());
        }

        return builder;
    }
}
