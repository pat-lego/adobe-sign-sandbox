package io.github.adobe.sign.core.auth;

public interface CredentialLoader {

    public Credentials loadCredentials() throws CredentialLoaderException;
    
}
