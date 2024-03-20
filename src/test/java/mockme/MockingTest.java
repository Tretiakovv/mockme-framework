package mockme;

import annotation.MockMe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMeExtension.class)
public class MockingTest {

    @Test
    void shouldMockObjectSuccessfully() {

        Target mockTarget = Mockme.mock(Target.class);
        Mockme.when(mockTarget.doSomething(5)).thenReturn("Mocked!");
        Mockme.when(mockTarget.saySomething(0)).thenReturn(10);

        assertEquals("Mocked!", mockTarget.doSomething(5));
        assertEquals(10, mockTarget.saySomething(0));

    }

    @Test
    void shouldReturnLatestValue() {
        Target mockTarget = Mockme.mock(Target.class);
        Mockme.when(mockTarget.saySomething(0))
                .thenReturn(10).thenReturn(15).thenReturn(20);
        assertEquals(20, mockTarget.saySomething(0));
    }

    @MockMe
    Target gugugaga = new Target("GUGUGAGA");

    @Test
    void shouldReturnLatestValueMOCKITOBEAST() {
        Mockme.when(gugugaga.saySomething(0)).thenReturn(12);
        assertEquals(12, gugugaga.saySomething(0));
    }
}
