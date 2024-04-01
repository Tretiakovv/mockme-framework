package mockme.internal.annotation;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MockMeExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        MockMeProcessor.process(testInstance);
    }
}
