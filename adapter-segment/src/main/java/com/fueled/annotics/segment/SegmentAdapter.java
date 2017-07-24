package com.fueled.annotics.segment;

import android.support.annotation.NonNull;

import com.fueled.annotics.AnalyticsAdapter;
import com.fueled.annotics.DataMap;
import com.fueled.annotics.EventData;
import com.fueled.annotics.UserData;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;
import com.segment.analytics.ValueMap;

import java.util.Map;

/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class SegmentAdapter implements AnalyticsAdapter {

    private Analytics delegate;

    public SegmentAdapter(@NonNull Analytics analytics) {
        this.delegate = analytics;
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
        delegate.identify(userId, getTraits(userData), null);
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
        delegate.track(event, getProperties(eventData));
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
        delegate.screen(name, getProperties(eventData));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void rest() {
        delegate.reset();
    }

    private Traits getTraits(DataMap dataMap) {
        return populateValueMap(new Traits(), dataMap);
    }

    private Properties getProperties(DataMap dataMap) {
        return populateValueMap(new Properties(), dataMap);
    }

    private <T extends ValueMap> T populateValueMap(T valueMap, DataMap dataMap) {
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            valueMap.put(entry.getKey(), entry.getValue());
        }

        return valueMap;
    }
}
