package ru.nsu.fit.mockme.internal.implementations;

import ru.nsu.fit.mockme.internal.interfaces.Stub;
import ru.nsu.fit.mockme.internal.proxies.StubInvocationHandler;
import ru.nsu.fit.mockme.internal.utils.StubMethodStatus;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class StubImpl<T> implements Stub {

    private final Map<String, Object> returnMap;
    private final T mockedObject;

    @SuppressWarnings("unchecked")
    public StubImpl(Method method) {

        this.mockedObject = (T) method.getDeclaringClass();
        this.returnMap = new HashMap<>();

        initReturnMap();
        when(method);

    }

    @Override
    public <M extends Method> Stub when(M method) {
        returnMap.put(method.getName(), StubMethodStatus.NOT_STUBBED);
        return this;
    }

    @Override
    public <R> Stub thenReturn(R value) {

        Map.Entry<String, Object> foundEntry = returnMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(StubMethodStatus.NOT_STUBBED))
                .toList().get(0);

        foundEntry.setValue(value);
        returnMap.put(foundEntry.getKey(), foundEntry.getValue());
        return this;

    }

    @SuppressWarnings("unchecked")
    @Override
    public T apply() {
        return (T) Proxy.newProxyInstance(
                mockedObject.getClass().getClassLoader(),
                mockedObject.getClass().getInterfaces(),
                new StubInvocationHandler<>(mockedObject, returnMap)
        );
    }

    private void initReturnMap() {
        Method[] methods = mockedObject.getClass().getMethods();
        for (Method method : methods) {
            returnMap.put(method.getName(), method.getReturnType());
        }
    }

}
