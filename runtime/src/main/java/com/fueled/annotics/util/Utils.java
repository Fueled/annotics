package com.fueled.annotics.util;

/**
 * Created by hussein@fueled.com on 04/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class Utils {

    private Utils() {

    }

    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }

        return object;
    }

}
