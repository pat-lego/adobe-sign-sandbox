package io.github.adobe.sign.core.auth;

public class Credentials {
    private String clientId;
    private String clientSecret;
    private Integer expires;
    private String tokenType;
    private String code;
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    public Integer getExpires() {
        return expires;
    }
    public void setExpires(Integer expires) {
        this.expires = expires;
    }
    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getRedirectUri() {
        return redirectUri;
    }
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
    
}
