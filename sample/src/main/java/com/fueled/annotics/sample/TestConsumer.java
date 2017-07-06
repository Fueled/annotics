package com.fueled.annotics.sample;

import android.util.Log;

import com.fueled.annotics.AnalyticsConsumer;
import com.fueled.annotics.DataMap;
import com.fueled.annotics.EventData;
import com.fueled.annotics.UserData;

import java.util.Map;

/**
 * Created by hussein@fueled.com on 06/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class TestConsumer implements AnalyticsConsumer {

    private static final String TAG = TestConsumer.class.getSimpleName();

    @Override
    public void identify(String userId) {
        identify(userId, null);
    }

    @Override
    public void identify(String userId, UserData userData) {
        StringBuilder builder = new StringBuilder("\u21E2 IDENTIFY: ");
        builder.append(userId).append('(');

        appendDataMapToString(builder, userData);

        builder.append(')');

        Log.d(TAG, builder.toString());
    }

    @Override
    public void track(String event) {
        track(event, null);
    }

    @Override
    public void track(String event, EventData eventData) {
        StringBuilder builder = new StringBuilder("\u21E2 TRACK: ");
        builder.append(event).append('(');

        appendDataMapToString(builder, eventData);

        builder.append(')');

        Log.d(TAG, builder.toString());
    }

    @Override
    public void screen(String name) {
        screen(name, null);
    }

    @Override
    public void screen(String name, EventData eventData) {
        StringBuilder builder = new StringBuilder("\u21E2 SCREEN: ");
        builder.append(name).append('(');

        appendDataMapToString(builder, eventData);

        builder.append(')');

        Log.d(TAG, builder.toString());
    }

    @Override
    public void rest() {

    }

    private void appendDataMapToString(StringBuilder builder, DataMap dataMap) {
        if (dataMap == null) {
            return;
        }

        boolean first = true;

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (!first) {
                builder.append(", ");
            }
            first = false;

            builder.append(entry.getKey()).append('=');
            builder.append(entry.getValue());
        }
    }
}
