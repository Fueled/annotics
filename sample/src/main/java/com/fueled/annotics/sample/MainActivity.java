package com.fueled.annotics.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hussein@fueled.com on 06/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TrackedClass trackedClass = new TrackedClass("Testing");
        trackedClass.updateCardInformation("4243-534343-5353-434", "12/18", "321");
        trackedClass.login("jane_doe@gmail.com", "test12345");
        trackedClass.updateAccount("Jane", "Doe");
    }

}
