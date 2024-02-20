package ru.nsu.fit.mockme.internal.interfaces;

public interface OngoingStub<T> {

    OngoingStub<T> thenReturn(T value);
    OngoingStub<T> thenReturn(T... values);
    OngoingStub<T> thenThrow(Throwable throwable);
    OngoingStub<T> thenThrow(Throwable... throwables);

}
