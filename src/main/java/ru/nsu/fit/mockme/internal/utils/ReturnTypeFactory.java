package ru.nsu.fit.mockme.internal.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnTypeFactory {

    private final Map<Class<?>, Object> factory;

    public ReturnTypeFactory() {
        this.factory = new HashMap<>();
        initFactory();
    }

    public Object getValue(Class<?> key) {
        return factory.get(key);
    }

    private void initFactory() {
        factory.put(Object.class, null);
        factory.put(boolean.class, false);
        factory.put(float.class, 0.0);
        factory.put(double.class, 0.0);
        factory.put(int.class, 0);
        factory.put(char.class, 0);
        factory.put(long.class, 0);
        factory.put(short.class, 0);
        factory.put(byte.class, 0);
    }

}
