package mockme;

import mockme.interceptors.MockmeInterceptor;

public interface MockmeInterceptable {

    void setInterceptor(MockmeInterceptor interceptor);
}
