package com.fueled.annotics;

import android.support.annotation.NonNull;

import com.fueled.annotics.util.Utils;

/**
 * Created by hussein@fueled.com on 04/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class Annotics implements AnalyticsConsumer {

    private static Annotics instance;

    private final AnalyticsConsumer delegate;

    private boolean enabled = true;

    private Annotics(@NonNull AnalyticsConsumer analyticsConsumer) {
        this.delegate = analyticsConsumer;
    }

    @NonNull
    public AnalyticsConsumer getAnalyticsConsumer() {
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

    public static Annotics init(@NonNull AnalyticsConsumer analyticsConsumer) {
        instance = new Annotics(Utils.requireNonNull(analyticsConsumer,
                "AnalyticsConsumer must be non null."));

        return instance;
    }

    public static Annotics get() {
        return Utils.requireNonNull(instance,
                "You must call Annotics#init(AnalyticsConsumer) first.");
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
