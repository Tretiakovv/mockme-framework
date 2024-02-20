package ru.nsu.fit.mockme.internal.interfaces;

import java.lang.reflect.Method;

public interface Stub {

    <T extends Method> Stub when(T method);

    <R> Stub thenReturn(R value);

    <T> T apply();

}
