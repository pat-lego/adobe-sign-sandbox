package io.github.adobe.sign.core.workflow;

import java.util.List;
import java.util.Map;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;

public interface SignWorkflow {

    public void addAction(SignAction signAction) throws SignWorkflowException;

    public void removeAction(SignAction signAction) throws SignWorkflowException;

    public List<String> getActionNames();

    public List<SignAction> getActions();

    public SignWorkflowResult invoke(SignAuth signAuth, SignLogger signLogger, Map<String, Object> signWorkflow);
    
}
