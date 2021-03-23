package io.github.adobe.sign.impl.actions;

import org.junit.jupiter.api.Test;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.actions.SignActionException;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.impl.auth.BasicSignAuth;
import io.github.adobe.sign.impl.auth.FileCredentialLoader;

public class TestTransientDocument {

    @Test
    public void testTransientDocument() throws SignActionException {
        CredentialLoader credentialLoader = new FileCredentialLoader("src/test/resources/auth/actualSignCreds.properties");
        SignAuth auth = new BasicSignAuth();

        SignAction action = new TransientDocument(credentialLoader);
        
        action.doAction(auth, null, null);
    }
    
}
