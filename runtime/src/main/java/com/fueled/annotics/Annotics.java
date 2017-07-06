package com.fueled.annotics;

import android.support.annotation.NonNull;

import com.fueled.annotics.util.Utils;

/**
 * Created by hussein@fueled.com on 04/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class Annotics implements AnalyticsAdapter {

    private static Annotics instance;

    private final AnalyticsAdapter delegate;

    private boolean enabled = true;

    private Annotics(@NonNull AnalyticsAdapter analyticsAdapter) {
        this.delegate = analyticsAdapter;
    }

    @NonNull
    public AnalyticsAdapter getAnalyticsAdapter() {
        return delegate;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public static Annotics init(@NonNull AnalyticsAdapter analyticsAdapter) {
        instance = new Annotics(Utils.requireNonNull(analyticsAdapter,
                "AnalyticsAdapter must be non null."));

        return instance;
    }

    public static Annotics get() {
        return Utils.requireNonNull(instance,
                "You must call Annotics#init(AnalyticsAdapter) first.");
    }

    @Override
    public void identify(String userId) {
        delegate.identify(userId);
    }

    @Override
    public void identify(String userId, UserData userData) {
        delegate.identify(userId, userData);
    }

    @Override
    public void track(String event) {
        delegate.track(event);
    }

    @Override
    public void track(String event, EventData eventData) {
        delegate.track(event, eventData);
    }

    @Override
    public void screen(String name) {
        delegate.screen(name);
    }

    @Override
    public void screen(String name, EventData eventData) {
        delegate.screen(name, eventData);
    }

    @Override
    public void rest() {
        delegate.rest();
    }
}
