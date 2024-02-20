package ru.nsu.fit.mockme.internal.implementations;

import ru.nsu.fit.mockme.exceptions.NotAnInterfaceException;
import ru.nsu.fit.mockme.internal.interfaces.Mock;
import ru.nsu.fit.mockme.internal.proxies.MockInvocationHandler;

import java.lang.reflect.Proxy;

public class MockImpl<T> implements Mock<T> {

    private final T stub;

    @SuppressWarnings("unchecked")
    public MockImpl(Class<T> classToMock) throws NotAnInterfaceException {

        if (classToMock.getInterfaces().length == 0) {
            throw new NotAnInterfaceException();
        }

        this.stub = (T) Proxy.newProxyInstance(
                classToMock.getClassLoader(),
                classToMock.getInterfaces(),
                new MockInvocationHandler<>(classToMock)
        );

    }

    @Override
    public T mock() {
        return stub;
    }

}
