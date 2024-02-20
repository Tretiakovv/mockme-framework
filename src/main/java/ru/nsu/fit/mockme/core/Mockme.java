package ru.nsu.fit.mockme.core;

import ru.nsu.fit.mockme.internal.implementations.MockImpl;
import ru.nsu.fit.mockme.internal.implementations.StubImpl;
import ru.nsu.fit.mockme.internal.interfaces.Stub;

import java.lang.reflect.Method;

public class Mockme {

    public static <T> T mock(Class<T> classToMock) {
        return new MockImpl<>(classToMock).mock();
    }

    public static <T> Stub when(Method method) {
        return new StubImpl<>(method);
    }

}
