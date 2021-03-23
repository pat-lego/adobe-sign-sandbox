package io.github.adobe.sign.core.actions;

public class SignActionException extends Exception {
    
    public SignActionException(String msg) {
        super(msg);
    }

    public SignActionException(String msg, Throwable t) {
        super(msg, t);
    }
}
