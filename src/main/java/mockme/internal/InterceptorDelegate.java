package mockme.internal;

import mockme.internal.interceptors.MockmeInterceptor;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class InterceptorDelegate {

    public static final List<Object> lastMethodArgs = new ArrayList<>();
    public static Method lastCalledMethod = null;
    public static Callable<?> lastCallableObject;
    public static final Set<Method> originalMethods = new HashSet<>();

    @RuntimeType
    public static Object onEnter(@This Object mock,
                                 @FieldValue("interceptor") MockmeInterceptor interceptor,
                                 @Origin Method invokedMethod,
                                 @AllArguments Object[] arguments) {

        lastMethodArgs.addAll(List.of(invokedMethod.getParameterTypes()));
        lastCalledMethod = invokedMethod;
        lastCallableObject = () -> invokedMethod;

        originalMethods.add(invokedMethod);
        return interceptor.invoke(mock, invokedMethod, arguments);

    }

    @RuntimeType
    public static void onExit(@This Object mock,
                              @FieldValue("interceptor") MockmeInterceptor interceptor,
                              @Origin Method invokedMethod,
                              @AllArguments Object[] arguments) {
        lastMethodArgs.clear();
        lastCalledMethod = null;
        lastCallableObject = null;
    }

}
