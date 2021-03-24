package io.github.adobe.sign.impl.auth;


import java.io.FileInputStream;
import java.util.Properties;

import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.CredentialLoaderException;
import io.github.adobe.sign.core.auth.Credentials;
import io.github.adobe.sign.core.metadata.SignMetadata;

public class FileCredentialLoader implements CredentialLoader {

    private String credentialFile;

    public FileCredentialLoader(String credentialFile) {
        this.credentialFile = credentialFile;
    }

    @Override
    public Credentials loadCredentials(SignMetadata metadata) throws CredentialLoaderException {
        try(FileInputStream propertiesFile = new FileInputStream(this.credentialFile)) {
            Properties props = new Properties();
            Credentials creds = new Credentials();

            props.load(propertiesFile);

            String clientId = props.getProperty(CLIENT_ID);
            String clientSecret = props.getProperty(CLIENT_SECRET);
            String refreshToken = props.getProperty(REFRESH_TOKEN);
         
            creds.setClientId(clientId);
            creds.setClientSecret(clientSecret);
            creds.setRefreshToken(refreshToken);

            return creds;

        } catch (Exception e) {
            throw new CredentialLoaderException(e.getMessage(), e);
        }

    }
    
}
