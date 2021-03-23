package io.github.adobe.sign.core.auth;

public class CredentialLoaderException extends Exception {
    public CredentialLoaderException(String msg) {
        super(msg);
    }

    public CredentialLoaderException(String msg, Throwable t) {
        super(msg, t);
    }
}
