package com.fueled.annotics.internal;

import com.fueled.annotics.Annotics;
import com.fueled.annotics.EventData;
import com.fueled.annotics.EventType;
import com.fueled.annotics.SharedValue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
@Aspect
public class AnnoticsProcessor {

    @Pointcut("execution(@com.fueled.annotics.TrackEvent * *(..))")
    public void method() {
    }

    @Pointcut("execution(@com.fueled.annotics.TrackEvent *.new(..))")
    public void constructor() {
    }

    @Around("method() || constructor()")
    public Object trackAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        if (isEnabled()) {
            trackEvent(AnnoticsMetadata.get(joinPoint));
        }

        return joinPoint.proceed();
    }

    private static void trackEvent(AnnoticsMetadata metadata) {
        String eventName = metadata.getEventName();
        EventType eventType = metadata.getEventType();
        boolean trackParameters = metadata.trackParameters();

        EventData eventData = new EventData();

        for (int i = 0; i < metadata.getSharedValuesCount(); i++) {
            SharedValue value = metadata.getSharedValue(i);
            eventData.putValue(value.key(), value.value());
        }

        for (int i = 0; i < metadata.getParametersCount() && trackParameters; i++) {
            AnnoticsParameter parameter = metadata.getParameter(i);

            if (!parameter.hidden) {
                eventData.putValue(parameter.name, Strings.toString(parameter.value));
            }
        }

        switch (eventType) {
            case SCREEN_VIEW:
                Annotics.get().screen(eventName, eventData);
                break;
            case TRACK:
            default:
                Annotics.get().track(eventName, eventData);
                break;
        }
    }

    private static boolean isEnabled() {
        return Annotics.isInitialized() && Annotics.get().isEnabled();
    }
}
