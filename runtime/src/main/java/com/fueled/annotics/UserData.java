package com.fueled.annotics;


/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class UserData extends DataMap {

    @Override
    public UserData putValue(String key, Object value) {
        super.putValue(key, value);
        return this;
    }

}
