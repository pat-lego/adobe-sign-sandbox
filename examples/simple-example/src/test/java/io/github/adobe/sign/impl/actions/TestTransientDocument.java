package io.github.adobe.sign.impl.actions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.actions.SignActionException;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.metadata.SignMetadata;
import io.github.adobe.sign.impl.auth.BasicSignAuth;
import io.github.adobe.sign.impl.auth.FileCredentialLoader;

public class TestTransientDocument {

    @Test
    public void testTransientDocument() throws SignActionException {
        SignMetadata metadata = new SignMetadata() {

            private Map<String, Object> map = new HashMap<String, Object>();

            @Override
            public final Map<String, Object> getMetadata() {
                return map;
            }

            @Override
            public final Object getValue(String key) {
               return map.get(key);
            }

            @Override
            public void put(String key, Object value) {
                map.put(key, value);
            }

            @Override
            public void addAll(Map<String, Object> map) {
               this.map.putAll(map);
            }

        };
        
        CredentialLoader credentialLoader = new FileCredentialLoader("src/test/resources/auth/actualSignCreds.properties");
        SignAuth auth = new BasicSignAuth();

        SignAction action = new TransientDocument(credentialLoader);
        
        metadata = action.doAction(auth, metadata, null);
        assertNotNull(metadata.getValue("TRANSIENT_ID"));
        System.out.println(metadata.getValue("TRANSIENT_ID"));
    }
    
}
