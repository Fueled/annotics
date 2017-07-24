package com.fueled.annotics.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fueled.annotics.EventType;
import com.fueled.annotics.EventProperty;
import com.fueled.annotics.IgnoreParam;
import com.fueled.annotics.SharedAttributes;
import com.fueled.annotics.SharedValue;
import com.fueled.annotics.TrackEvent;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by hussein@fueled.com on 13/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class AnnoticsMetadata {

    private String methodName;
    private String[] parameterNames;
    private Object[] parameterValues;

    @Nullable private SharedAttributes sharedAttributes;
    @Nullable private TrackEvent eventAnnotation;
    @Nullable private Annotation[][] parameterAnnotations;

    @NonNull
    public String getEventName() {
        String eventName = methodName;

        if (eventAnnotation != null && !eventAnnotation.value().isEmpty()) {
            eventName = eventAnnotation.value();
        }

        return eventName;
    }

    @NonNull
    public EventType getEventType() {
        return (eventAnnotation != null) ? eventAnnotation.type() : EventType.TRACK;
    }

    public boolean trackParameters() {
        return eventAnnotation == null || eventAnnotation.trackParameters();
    }

    public int getSharedValuesCount() {
        return sharedAttributes != null ? sharedAttributes.value().length : 0;
    }

    @NonNull
    public SharedValue getSharedValue(int index) {
        return sharedAttributes.value()[index];
    }

    public int getParametersCount() {
        return parameterNames.length;
    }

    @NonNull
    public AnnoticsParameter getParameter(int index) {
        EventProperty annotation = getEventValueAnnotation(parameterAnnotations[index]);
        IgnoreParam isIgnored = getIgnoreValueAnnotation(parameterAnnotations[index]);

        AnnoticsParameter parameter = new AnnoticsParameter();
        parameter.name = parameterNames[index];
        parameter.value = parameterValues[index];

        if (annotation != null) {
            parameter.name = annotation.value().isEmpty() ? parameter.name : annotation.value();
        }

        if (isIgnored != null) {
            parameter.hidden = true;
        }

        return parameter;
    }

    private static EventProperty getEventValueAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof EventProperty) {
                return (EventProperty) annotation;
            }
        }

        return null;
    }

    private IgnoreParam getIgnoreValueAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof IgnoreParam) {
                return (IgnoreParam) annotation;
            }
        }

        return null;
    }

    @NonNull
    public static AnnoticsMetadata get(JoinPoint joinPoint) {
        AnnoticsMetadata metadata;

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        if (codeSignature instanceof ConstructorSignature) {
            metadata = get((ConstructorSignature) codeSignature);
        } else if (codeSignature instanceof MethodSignature) {
            metadata = get((MethodSignature) codeSignature);
        } else {
            metadata = new AnnoticsMetadata();
        }

        metadata.sharedAttributes = (SharedAttributes) codeSignature.getDeclaringType()
                .getAnnotation(SharedAttributes.class);

        metadata.methodName = codeSignature.getName();
        metadata.parameterNames = codeSignature.getParameterNames();
        metadata.parameterValues = joinPoint.getArgs();

        return metadata;
    }

    @NonNull
    private static AnnoticsMetadata get(ConstructorSignature signature) {
        AnnoticsMetadata annoticsMetadata = new AnnoticsMetadata();

        Constructor constructor = signature.getConstructor();

        annoticsMetadata.eventAnnotation = (TrackEvent) constructor.getAnnotation(TrackEvent.class);
        annoticsMetadata.parameterAnnotations = constructor.getParameterAnnotations();

        return annoticsMetadata;
    }

    @NonNull
    private static AnnoticsMetadata get(MethodSignature signature) {
        AnnoticsMetadata annoticsMetadata = new AnnoticsMetadata();

        Method method = signature.getMethod();

        annoticsMetadata.eventAnnotation = method.getAnnotation(TrackEvent.class);
        annoticsMetadata.parameterAnnotations = method.getParameterAnnotations();

        return annoticsMetadata;
    }
}
