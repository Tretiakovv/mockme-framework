package mockme;

import mockme.interceptors.MockmeInterceptor;
import mockme.interceptors.MockmeStaticInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import org.objenesis.ObjenesisStd;

import java.util.List;

import static java.lang.reflect.Modifier.PRIVATE;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;

public class ByteBuddyMockCreator implements MockCreator {

    private final ObjenesisStd objenesis = new ObjenesisStd();

    @Override
    public <T> T createMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList) {

        ByteBuddy byteBuddy = new ByteBuddy();
        Class<? extends T> classWithInterceptor;

        classWithInterceptor = byteBuddy
                .subclass(mockTargetClass)
                .method(any())
                .intercept(MethodDelegation.to(InterceptorDelegate.class))
                .defineField("interceptor", MockmeInterceptor.class, PRIVATE)
                .implement(MockmeInterceptable.class)
                .intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        T mockTargetInstance = objenesis.newInstance(classWithInterceptor);

        ((MockmeInterceptable) mockTargetInstance).setInterceptor(new MockmeInterceptor(behaviourList));

        return mockTargetInstance;

    }

    @Override
    public <T> MockStatic<T> createStaticMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList) {

        Class<? extends T> classWithInterceptor;
        ByteBuddyAgent.install();

        classWithInterceptor = new ByteBuddy()
                .redefine(mockTargetClass)
                .visit(Advice.to(MockmeStaticInterceptor.class).on(isStatic()))
                .make()
                .load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
                .getLoaded();

        T mockTargetInstance = objenesis.newInstance(classWithInterceptor);

        return new MockStatic<>(mockTargetInstance);

    }

}
