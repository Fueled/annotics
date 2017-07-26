package com.fueled.annotics.mixpanel;

import android.support.annotation.NonNull;

import com.fueled.annotics.AnalyticsAdapter;
import com.fueled.annotics.EventData;
import com.fueled.annotics.UserData;
import com.fueled.annotics.util.Utils;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hussein@fueled.com on 26/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class MixpanelAdapter implements AnalyticsAdapter {

    private static final String DEFAULT_SCREEN_VIEW_EVENT_NAME = "Screen View";
    private static final String DEFAULT_SCREEN_NAME_PROPERTY_KEY = "Screen Name";

    private final MixpanelAPI mixpanelAPI;

    private String screenViewEventName;
    private String screenNamePropertyKey;

    public MixpanelAdapter(@NonNull MixpanelAPI mixpanelAdapter) {
        this.mixpanelAPI = Utils.requireNonNull(mixpanelAdapter, "MixpanelAPI must be non null.");
        this.screenViewEventName = DEFAULT_SCREEN_VIEW_EVENT_NAME;
        this.screenNamePropertyKey = DEFAULT_SCREEN_NAME_PROPERTY_KEY;
    }

    /**
     * Returns the name being used for screen view events.
     *
     * @return the name being used.
     * @see #screen(String)
     */
    public String getScreenViewEventName() {
        return screenViewEventName;
    }

    /**
     * Sets the name to be used for all screen view events.
     *
     * @param screenViewEventName the name to be used.
     * @see #screen(String)
     */
    public void setScreenViewEventName(String screenViewEventName) {
        this.screenViewEventName = screenViewEventName;
    }

    /**
     * Returns the key being used for screen name property.
     *
     * @return the key being used.
     * @see #screen(String)
     */
    public String getScreenNamePropertyKey() {
        return screenNamePropertyKey;
    }

    /**
     * Sets the key to be used for the screen name property, when tracking a new screen view event.
     *
     * @param screenNamePropertyKey the key to be used.
     * @see #screen(String)
     */
    public void setScreenNamePropertyKey(String screenNamePropertyKey) {
        this.screenNamePropertyKey = screenNamePropertyKey;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId) {
        mixpanelAPI.identify(userId);
        mixpanelAPI.getPeople().identify(userId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void identify(String userId, UserData userData) {
        mixpanelAPI.identify(userId);
        mixpanelAPI.getPeople().identify(userId);
        mixpanelAPI.getPeople().setMap(userData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String eventName) {
        mixpanelAPI.track(eventName);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void track(String eventName, EventData eventData) {
        mixpanelAPI.trackMap(eventName, eventData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String screenName) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(getScreenNamePropertyKey(), screenName);
        mixpanelAPI.trackMap(getScreenViewEventName(), properties);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void screen(String screenName, EventData eventData) {
        eventData.putValue(getScreenNamePropertyKey(), screenName);
        mixpanelAPI.trackMap(getScreenViewEventName(), eventData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void rest() {
        mixpanelAPI.reset();
    }
}
