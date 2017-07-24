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

    /**
     * Return the analytics adapter used to handle all analytics events.
     */
    @NonNull
    public AnalyticsAdapter getAnalyticsAdapter() {
        return delegate;
    }

    /**
     * Specify whether event tracking should be enabled or not.
     *
     * @param enabled True if tracking is enabled, false otherwise.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Indicates whether event tracking should be enabled or not.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Indicates whether {@code Annotics} has been initialized or not.
     * @return True if initialized, false otherwise.
     */
    public static boolean isInitialized() {
        return instance != null;
    }

    /**
     * Initialize and return a new instance of {@code Annotics} using the
     * specified {@link AnalyticsAdapter}.
     *
     * @param analyticsAdapter the analytics adapter that will be consuming all events.
     * @return the new {@code Annotics} instance.
     */
    public static Annotics init(@NonNull AnalyticsAdapter analyticsAdapter) {
        instance = new Annotics(Utils.requireNonNull(analyticsAdapter,
                "AnalyticsAdapter must be non null."));

        return instance;
    }

    /**
     * Initialize and return a new instance of {@code Annotics} using the
     * specified list of {@link AnalyticsAdapter}.
     *
     * @param adapters the analytics adapters that will be consuming all events.
     * @return the new {@code Annotics} instance.
     */
    public static Annotics init(@NonNull AnalyticsAdapter... adapters) {
        return init(new AnalyticsAdapterSet(adapters));
    }

    /**
     * Returns the current {@code Annotics} instance. {@link Annotics#init(AnalyticsAdapter)}
     * must be called first to initialize {@code Annotics}.
     */
    public static Annotics get() {
        return Utils.requireNonNull(instance,
                "You must call Annotics#init(AnalyticsAdapter) first.");
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId) {
        delegate.identify(userId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId, UserData userData) {
        delegate.identify(userId, userData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String event) {
        delegate.track(event);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String event, EventData eventData) {
        delegate.track(event, eventData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String name) {
        delegate.screen(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String name, EventData eventData) {
        delegate.screen(name, eventData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void rest() {
        delegate.rest();
    }
}
