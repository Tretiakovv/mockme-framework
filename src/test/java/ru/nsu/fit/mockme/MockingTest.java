package ru.nsu.fit.mockme;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.mockme.core.Mockme;
import ru.nsu.fit.mockme.exceptions.NotAnInterfaceException;
import ru.nsu.fit.mockme.testing_data.ErrorObject;
import ru.nsu.fit.mockme.testing_data.implementations.MockableImpl;
import ru.nsu.fit.mockme.testing_data.interfaces.Mockable;

import static org.junit.jupiter.api.Assertions.*;

public class MockingTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowNotAnInterfaceException() {
        assertThrows(NotAnInterfaceException.class,
                () -> Mockme.mock(ErrorObject.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldMockObjectSuccessfully() {
        Mockable mockable = Mockme.mock(MockableImpl.class);
        assertEquals(0.0, mockable.getRandomDouble(), 0.1);
        assertNull(mockable.getRandomUUID());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnRedefinedValue() {
        // TODO — Как мне передать метод как параметр?
        Mockable mockable = Mockme.mock(MockableImpl.class);
        Mockme.when(mockable::getRandomDouble).thenReturn(15.0).thenReturn(32.0);
    }

}
