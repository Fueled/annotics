package com.fueled.annotics;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hussein@fueled.com on 05/07/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class DataMap implements Map<String, Object> {

    private Map<String, Object> delegate;

    public DataMap() {
        delegate = new LinkedHashMap<>();
    }

    private DataMap(Map<String, Object> map) {
        delegate = new LinkedHashMap<>(map);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return delegate.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return delegate.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ?> map) {
        delegate.putAll(map);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return delegate.keySet();
    }

    @NonNull
    @Override
    public Collection<Object> values() {
        return delegate.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return delegate.entrySet();
    }

    public DataMap putValue(String key, Object value) {
        put(key, value);
        return this;
    }

    public int getInt(String key, int defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Integer) {
            return (int) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (Exception ignored) {
            }
        }

        return defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Long) {
            return (long) value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return Long.parseLong((String) value);
            } catch (Exception ignored) {
            }
        }

        return defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Double) {
            return (double) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (Exception ignored) {
            }
        }

        return defaultValue;
    }

    public float getFloat(String key, float defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Float) {
            return (float) value;
        } else if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof String) {
            try {
                return Float.parseFloat((String) value);
            } catch (Exception ignored) {
            }
        }

        return defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Boolean) {
            return (boolean) value;
        } else if (value instanceof String) {
            return Boolean.valueOf((String) value);
        }

        return defaultValue;
    }

    public char getChar(String key, char defaultValue) {
        Object value = delegate.get(key);

        if (value instanceof Character) {
            return (char) value;
        } else if (value instanceof String && ((String) value).length() == 1) {
            return ((String) value).charAt(0);
        }

        return defaultValue;
    }

    public String getString(String key) {
        Object value = delegate.get(key);

        if (value instanceof String) {
            return (String) value;
        } else if (value != null) {
            return String.valueOf(value);
        }

        return null;
    }

    public DataMap getDataMap(Object key) {
        Object value = get(key);
        if (value instanceof DataMap) {
            return (DataMap) value;
        } else if (value instanceof Map) {
            //noinspection unchecked
            return new DataMap((Map<String, Object>) value);
        }

        return null;
    }
}
