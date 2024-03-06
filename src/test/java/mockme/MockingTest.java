package mockme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockingTest {

    @Test
    void shouldMockObjectSuccessfully() {

        Target mockTarget = Mockme.mock(Target.class);

        Mockme.when(mockTarget.doSomething(5)).thenReturn("Mocked!");
        Mockme.when(mockTarget.saySomething(0)).thenReturn(10);

        assertEquals("Mocked!", mockTarget.doSomething(5));
        assertEquals(10, mockTarget.saySomething(0));

    }

}
