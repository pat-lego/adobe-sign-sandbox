package io.github.adobe.sign.core.auth;

public interface SignAuth {

    public String REFRESH_URL = "https://api.na1.echosign.com/oauth/refresh";
    public String TOKEN_URL = "https://api.na1.echosign.com/oauth/token";

    public String CODE = "code";
    public String ACCESS_TOKEN = "access_token";
    public String REFRESH_TOKEN = "refresh_token";
    public String TOKEN_TYPE = "token_type";
    public String EXPIRES_IN = "expires_in";

    public String REFRESH_TOKEN_GT = "refresh_token";
    public String AUTHORIZATION_CODE_GT = "authorization_code";
    public String GRANT_TYPE = "grant_type";

    public Authenticated authenticate(CredentialLoader credentials) throws SignAuthException;

    public Authenticated refresh(CredentialLoader credentials, Authenticated authenticated) throws SignAuthException;

    public Boolean isValidAuth(Authenticated authenticated) throws SignAuthException;

}
