package mockme.internal.itnerfaces;

import mockme.internal.InvocationDetails;
import mockme.internal.MockStatic;

import java.util.List;

public interface MockCreator {
    <T> T createMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList);
    <T> MockStatic<T> createStaticMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList);
}
