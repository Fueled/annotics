package com.fueled.annotics;

import android.support.annotation.NonNull;

import com.fueled.annotics.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hussein@fueled.com on 07/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class AnalyticsAdapterSet implements AnalyticsAdapter {

    private List<AnalyticsAdapter> adapters;

    public AnalyticsAdapterSet(AnalyticsAdapter... adapters) {
        this.adapters = new ArrayList<>(Arrays.asList(adapters));
    }

    public void addAnalyticsAdapter(@NonNull AnalyticsAdapter adapter) {
        adapters.add(Utils.requireNonNull(adapter, "AnalyticsAdapter must be non null."));
    }

    public void removeAnalyticsAdapter(@NonNull AnalyticsAdapter adapter) {
        adapters.remove(adapter);
    }

    @Override
    public void identify(String userId) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.identify(userId);
        }
    }

    @Override
    public void identify(String userId, UserData userData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.identify(userId, userData);
        }
    }

    @Override
    public void track(String event) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.track(event);
        }
    }

    @Override
    public void track(String event, EventData eventData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.track(event, eventData);
        }
    }

    @Override
    public void screen(String name) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.screen(name);
        }
    }

    @Override
    public void screen(String name, EventData eventData) {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.screen(name, eventData);
        }
    }

    @Override
    public void rest() {
        for (AnalyticsAdapter adapter : adapters) {
            adapter.rest();
        }
    }
}
