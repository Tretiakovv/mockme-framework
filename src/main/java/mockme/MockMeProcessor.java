package mockme;

import annotation.MockMe;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.reflect.Field;

@SupportedAnnotationTypes("annotation.MockMe")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MockMeProcessor {
    public static void process(Object testInstance) {
        Class<?> testClass = testInstance.getClass();
        for (Field field : testClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(MockMe.class)) {
                field.setAccessible(true);
                Class<?> mockTargetClass = field.getType();
                Object mockInstance = Mockme.mock(mockTargetClass);
                try {
                    field.set(testInstance, mockInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to set mock instance to field " + field.getName(), e);
                }
            }
        }
    }
}
