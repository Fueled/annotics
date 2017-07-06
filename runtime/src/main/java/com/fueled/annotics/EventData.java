package com.fueled.annotics;

/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class EventData extends DataMap {

    @Override
    public EventData putValue(String key, Object value) {
        super.putValue(key, value);
        return this;
    }
}
