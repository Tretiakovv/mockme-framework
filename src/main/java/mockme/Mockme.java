package mockme;

import mockme.internal.InvocationDetails;
import mockme.internal.MockStatic;
import mockme.internal.RealMockme;
import mockme.internal.itnerfaces.Functional;

public class Mockme {

    static final RealMockme REAL_MOCKME = new RealMockme();

    public static <T> T mock(Class<T> t) {
        return REAL_MOCKME.mock(t);
    }

    public static <T> MockStatic<T> mockStatic(Class<T> t) {
        return REAL_MOCKME.mockStatic(t);
    }

    public static <T> InvocationDetails<T> when(T methodCall) {
        return REAL_MOCKME.when(methodCall);
    }

    public static <T> InvocationDetails<T> when (Functional functional) {
        return REAL_MOCKME.when(functional);
    }

}
