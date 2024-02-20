package ru.nsu.fit.mockme.internal.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class StubInvocationHandler<T> implements InvocationHandler {

    private final Map<String, Object> returnMap;
    private final T stubbedObject;

    public StubInvocationHandler(T stubbedObject, Map<String, Object> returnMap) {
        this.returnMap = returnMap;
        this.stubbedObject = stubbedObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(stubbedObject, args);
        return returnMap.get(method.getName());
    }

}
