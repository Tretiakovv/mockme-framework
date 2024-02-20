package ru.nsu.fit.mockme.internal.proxies;

import ru.nsu.fit.mockme.internal.utils.ReturnTypeFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MockInvocationHandler<T> implements InvocationHandler {

    private final ReturnTypeFactory factory;
    private final T classToMock;

    public MockInvocationHandler(Class<T> classToMock) {
        try {
            this.factory = new ReturnTypeFactory();
            this.classToMock = classToMock.getConstructor().newInstance();
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(classToMock, args);
        return factory.getValue(method.getReturnType());
    }

}
