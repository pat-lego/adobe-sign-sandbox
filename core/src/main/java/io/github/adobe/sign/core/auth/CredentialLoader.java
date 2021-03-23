package io.github.adobe.sign.core.auth;

public interface CredentialLoader {

    public String CLIENT_ID = "client_id";
    public String CLIENT_SECRET = "client_secret";
    public String REFRESH_TOKEN = "refresh_token";
    
    public Credentials loadCredentials() throws CredentialLoaderException;
    
}
