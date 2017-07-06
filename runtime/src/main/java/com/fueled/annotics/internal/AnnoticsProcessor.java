package com.fueled.annotics.internal;

import com.fueled.annotics.Annotics;
import com.fueled.annotics.EventData;
import com.fueled.annotics.EventType;
import com.fueled.annotics.EventValue;
import com.fueled.annotics.TrackEvent;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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
            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

            TrackEvent eventAnnotation = null;
            Annotation[][] parameterAnnotations = new Annotation[0][];

            if (codeSignature instanceof MethodSignature) {
                Method method = ((MethodSignature) codeSignature).getMethod();
                eventAnnotation = method.getAnnotation(TrackEvent.class);
                parameterAnnotations = method.getParameterAnnotations();
            } else if (codeSignature instanceof ConstructorSignature) {
                Constructor constructor = ((ConstructorSignature) codeSignature).getConstructor();
                eventAnnotation = (TrackEvent) constructor.getAnnotation(TrackEvent.class);
                parameterAnnotations = constructor.getParameterAnnotations();
            }

            trackEvent(joinPoint, eventAnnotation, parameterAnnotations);
        }

        return joinPoint.proceed();
    }

    private static void trackEvent(JoinPoint joinPoint, TrackEvent eventAnnotation,
                                   Annotation[][] parameterAnnotations) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        String eventName = (eventAnnotation != null) ? eventAnnotation.value() : codeSignature.getName();
        EventType eventType = (eventAnnotation != null) ? eventAnnotation.type() : EventType.TRACK;
        boolean trackParameters = eventAnnotation == null || eventAnnotation.trackParameters();

        String[] parameterNames = codeSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        EventData eventData = new EventData();

        for (int i = 0; i < parameterValues.length && trackParameters; i++) {
            EventValue annotation = getEventValueAnnotation(parameterAnnotations[i]);
            String valueName = parameterNames[i];
            boolean isHidden = false;

            if (annotation != null) {
                valueName = annotation.value().isEmpty() ? valueName : annotation.value();
                isHidden = annotation.hidden();
            }

            if (!isHidden) {
                eventData.putValue(valueName, Strings.toString(parameterValues[i]));
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

    private static EventValue getEventValueAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof EventValue) {
                return (EventValue) annotation;
            }
        }

        return null;
    }

    private static boolean isEnabled() {
        return Annotics.isInitialized() && Annotics.get().isEnabled();
    }
}
