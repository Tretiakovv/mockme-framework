package ru.nsu.fit.mockme.testing_data;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

// TODO implement varargs

public class InnerDataClass<T> implements Serializable {

    public T getInnerValue(T data) {
        return data;
    }

    public UUID getRandomUUID() {
        return UUID.randomUUID();
    }

    public double getRandomDouble() {
        return new Random().nextDouble();
    }

    public void doNothing() {}

}
