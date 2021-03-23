package io.github.adobe.sign.impl.auth;


import java.io.FileInputStream;
import java.util.Properties;

import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.CredentialLoaderException;
import io.github.adobe.sign.core.auth.Credentials;

public class FileCredentialLoader implements CredentialLoader {

    private String credentialFile;

    public FileCredentialLoader(String credentialFile) {
        this.credentialFile = credentialFile;
    }

    @Override
    public Credentials loadCredentials() throws CredentialLoaderException {
        try(FileInputStream propertiesFile = new FileInputStream(this.credentialFile)) {
            Properties props = new Properties();
            Credentials creds = new Credentials();

            props.load(propertiesFile);

            String clientId = props.getProperty(CLIENT_ID);
            String clientSecret = props.getProperty(CLIENT_SECRET);
            String code = props.getProperty(CODE);
            String redirectUri = props.getProperty(REDIRECT_URI);

            creds.setRedirectUri(redirectUri);
            creds.setClientId(clientId);
            creds.setClientSecret(clientSecret);
            creds.setCode(code);

            return creds;

        } catch (Exception e) {
            throw new CredentialLoaderException(e.getMessage(), e);
        }

    }
    
}
