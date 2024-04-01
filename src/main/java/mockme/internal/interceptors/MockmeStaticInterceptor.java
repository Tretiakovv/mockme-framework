package mockme.internal.interceptors;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.lang.reflect.Method;

public class MockmeStaticInterceptor {

    @Advice.OnMethodEnter(skipOn = Object.class)
    private static Object enter(
            @Advice.Origin Method invokedMethod,
            @Advice.AllArguments Object[] arguments

    ) {
        return 0;
    }

    @Advice.OnMethodExit
    private static void exit(
            @Advice.Origin Method invokedMethod,
            @Advice.AllArguments Object[] arguments,
            @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object value

    ) {
        System.out.println(value);
    }

}
