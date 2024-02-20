package ru.nsu.fit.mockme.exceptions;

public class NotAnInterfaceException extends RuntimeException {
    public NotAnInterfaceException() {
        super("Class doesn't implement the interface");
    }
}
