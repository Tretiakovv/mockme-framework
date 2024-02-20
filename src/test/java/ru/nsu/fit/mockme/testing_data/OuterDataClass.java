package ru.nsu.fit.mockme.testing_data;

public class OuterDataClass<T> {

    private InnerDataClass<T> innerClass;

    public OuterDataClass(InnerDataClass<T> innerClass) {
        this.innerClass = innerClass;
    }

    public T getValueFromNestedCall(T data) {
        return innerClass.getInnerValue(data);
    }

}
