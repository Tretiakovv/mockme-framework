package ru.nsu.fit.mockme.testing_data.implementations;

import ru.nsu.fit.mockme.testing_data.interfaces.Mockable;

import java.util.Random;
import java.util.UUID;

public class MockableImpl implements Mockable {

    @Override
    public UUID getRandomUUID() {
        return UUID.randomUUID();
    }

    @Override
    public double getRandomDouble() {
        return new Random().nextDouble();
    }

}
