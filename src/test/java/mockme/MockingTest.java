package mockme;

import mockme.internal.annotation.MockMe;
import mockme.internal.annotation.MockMeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMeExtension.class)
public class MockingTest {
    @MockMe
    Target mockAnnotationTarget;

    @Test
    void shouldMockObjectSuccessfully_WithAnnotation() {
        Mockme.when(mockAnnotationTarget.doSomething(5)).thenReturn("Mocked!");
        Mockme.when(mockAnnotationTarget.saySomething(0)).thenReturn(10);

        assertEquals("Mocked!", mockAnnotationTarget.doSomething(5));
        assertEquals(10, mockAnnotationTarget.saySomething(0));
    }

    /*
    @Test
    @SuppressWarnings("uchecked")
    void shouldMockStaticMethods() {
        try(MockStatic<Target> ignored = Mockme.mockStatic(Target.class)){
            Mockme.when(Target::returnStaticInt).thenReturn(100_000);
            assertEquals(100_000, Target.returnStaticInt());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     */

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
}
