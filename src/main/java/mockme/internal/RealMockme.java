package mockme.internal;

import mockme.internal.itnerfaces.Functional;
import mockme.internal.itnerfaces.MockCreator;

import java.util.ArrayList;
import java.util.List;

public class RealMockme {

    private final List<InvocationDetails> invocationDetailsList = new ArrayList<>();

    private final MockCreator mockCreator = new ByteBuddyMockCreator();

    public <T> T mock(Class<T> mockTargetClass) {
        return mockCreator.createMock(mockTargetClass, invocationDetailsList);
    }

    public <T> MockStatic<T> mockStatic(Class<T> mockTargetClass) {
        return mockCreator.createStaticMock(mockTargetClass, invocationDetailsList);
    }

    public <T> InvocationDetails<T> when(T methodCall) {
        return invocationDetailsList.get(invocationDetailsList.size() - 1);
    }

    public <T> InvocationDetails<T> when(Functional functional) {

        try {
            functional.apply();
        } catch (Throwable t) {
            throw new RuntimeException(t.getCause());
        }

        return invocationDetailsList.get(invocationDetailsList.size() - 1);

    }

}
