/*
 * Created by hussein@fueled.com on 07/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
package com.fueled.annotics;

import android.support.annotation.NonNull;

import com.fueled.annotics.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to combine multiple {@link AnalyticsAdapter} together, and to pass events
 * to all adapters.
 */
public class AnalyticsAdapterSet implements AnalyticsAdapter {

    private List<AnalyticsAdapter> adapters;

    public AnalyticsAdapterSet(@NonNull AnalyticsAdapter... adapters) {
        this.adapters = new ArrayList<>(Arrays.asList(adapters));
    }

    /**
     * Add a new adapter to the list of adapters to be notified when a new event occurs.
     *
     * @param adapter the adapter to be added.
     */
    public void addAnalyticsAdapter(@NonNull AnalyticsAdapter adapter) {
        adapters.add(Utils.requireNonNull(adapter, "AnalyticsAdapter must be non null."));
    }

    /**
     * Remove an adapter from the current list of adapters, which will stop it from receiving any
     * future events.
     *
     * @param adapter the adapter to be removed.
     */
    public void removeAnalyticsAdapter(@NonNull AnalyticsAdapter adapter) {
        adapters.remove(adapter);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.identify(userId);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId, UserData userData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.identify(userId, userData);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String event) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.track(event);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String event, EventData eventData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.track(event, eventData);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String name) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.screen(name);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String name, EventData eventData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.screen(name, eventData);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void rest() {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.rest();
        }
    }
}
