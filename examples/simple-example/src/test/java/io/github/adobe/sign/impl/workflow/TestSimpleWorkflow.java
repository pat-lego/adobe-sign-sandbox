package io.github.adobe.sign.impl.workflow;

import org.junit.jupiter.api.Test;

import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.workflow.SignWorkflowException;
import io.github.adobe.sign.impl.auth.BasicSignAuth;
import io.github.adobe.sign.impl.auth.FileCredentialLoader;

public class TestSimpleWorkflow {

    @Test
    public void testInitialize() throws SignWorkflowException {
        CredentialLoader fileCredentialLoader = new FileCredentialLoader("src/test/resources/auth/actualSignCreds.properties");
        SignAuth basic = new BasicSignAuth();
        SimpleWorkflow workflow = new SimpleWorkflow();
        workflow.invoke(new BasicSignAuth(), null, null);
    }
    
}
