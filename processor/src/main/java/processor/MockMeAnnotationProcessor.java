package processor;

import annotation.MockMe;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("annotation.MockMe")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MockMeAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MockMe.class)) {
            MockMe myAnnotation = element.getAnnotation(MockMe.class);
            String value = myAnnotation.value();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Annotation value: " + value);
        }
        return true;
    }
}
