package io.github.adobe.sign.core.auth;

public class SignAuthException extends Exception {
    public SignAuthException(String msg) {
        super(msg);
    }

    public SignAuthException(String msg, Throwable t) {
        super(msg, t);
    }
}
