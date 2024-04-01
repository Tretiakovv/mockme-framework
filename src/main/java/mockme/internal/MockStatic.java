package mockme.internal;

public class MockStatic<T> implements AutoCloseable {

    public T object;

    public MockStatic(T object) {
        this.object = object;
    }

    @Override
    public void close() throws Exception {
        System.out.println("CLOSED");
    }

}
