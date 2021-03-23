package io.github.adobe.sign.impl.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import io.github.adobe.sign.core.auth.Authenticated;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.auth.SignAuthException;

public class TestBasicSignAuth {

    @Test
    public void testRefreshToken() throws SignAuthException {
        CredentialLoader loader = new FileCredentialLoader("src/test/resources/auth/actualSignCreds.properties");
        SignAuth basic = new BasicSignAuth();
        Authenticated auth = basic.refresh(loader);

        assertNotNull(auth);
        assertEquals(3600, auth.getExpiresIn());
        assertEquals("Bearer", auth.getTokenType());

        assertEquals(Boolean.TRUE, basic.isValidAuth(auth));
    }

    @Test
    public void testisValid() throws SignAuthException {
        SignAuth basic = new BasicSignAuth();

        Authenticated auth = new Authenticated();
        auth.setCreated(LocalDateTime.now().minusDays(1));
        auth.setExpiresIn(3600);

        assertEquals(Boolean.FALSE, basic.isValidAuth(auth));
    }
}
