package com.example.demo.exceptions;

public class EventFullException extends RuntimeException {

    public EventFullException() {
        super("Evento cheio");
    }

    public EventFullException(String message) {
        super(message);
    }

}
