package io.github.adobe.sign.impl.workflow;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.core.workflow.SignWorkflowException;
import io.github.adobe.sign.impl.actions.TransientDocument;
import io.github.adobe.sign.impl.executor.BasicWorkflowExecutor;

public class SimpleWorkflow extends BasicWorkflowExecutor {

    SignAction transientDocument;

    public SimpleWorkflow(CredentialLoader credentialLoader, SignAuth auth, SignLogger logger) throws SignWorkflowException {
        super(auth, logger);
        // Initialize the transient workflow step
        this.transientDocument = new TransientDocument(credentialLoader);

        // Add the work item to the workflow
        this.addAction(this.transientDocument);

        // Run it
        this.invoke(null);
    }
    
}
