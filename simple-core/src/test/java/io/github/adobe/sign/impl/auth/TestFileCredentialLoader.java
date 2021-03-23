package io.github.adobe.sign.impl.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.CredentialLoaderException;
import io.github.adobe.sign.core.auth.Credentials;

public class TestFileCredentialLoader {

    @Test
    public void testLoading() throws CredentialLoaderException {
        CredentialLoader loader = new FileCredentialLoader("src/test/resources/auth/signCreds.properties");
        Credentials creds = loader.loadCredentials();
        assertEquals("my-client-id", creds.getClientId());
        assertEquals("my-client-secret", creds.getClientSecret());
        assertEquals("90210", creds.getCode());
    }
    
}
