package io.github.adobe.sign.core.auth;

public interface SignAuth {
    
    public Authenticated authenticate(CredentialLoader credentials) throws SignAuthException;

    public Boolean isValidAuth() throws SignAuthException;

}
