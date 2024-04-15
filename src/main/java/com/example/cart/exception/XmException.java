package com.example.cart.exception;

public class XmException extends RuntimeException{

    private ExceptionEnum exceptionNum;

    public XmException(ExceptionEnum exceptionNum) {
        this.exceptionNum = exceptionNum;
    }

    public XmException() {
    }
}
