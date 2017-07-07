package com.fueled.annotics.sample;

import android.app.Application;

import com.fueled.annotics.Annotics;

/**
 * Created by hussein@fueled.com on 06/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Annotics.init(new TestAdapter());
    }
}
