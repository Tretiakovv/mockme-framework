package mockme;

public class Mockme {

    static final RealMockme REAL_MOCKME = new RealMockme();
    public static <T> T mock(Class<T> t) {
        return REAL_MOCKME.mock(t);
    }

    public static <T> InvocationDetails<T> when(T methodCall) {
        return REAL_MOCKME.when(methodCall);
    }

}
