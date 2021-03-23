package io.github.adobe.sign.core.workflow;

import java.util.List;
import java.util.Map;

import io.github.adobe.sign.core.actions.SignAction;

public interface SignWorkflow {

    public void addAction(SignAction signAction) throws SignWorkflowException;

    public void removeAction(SignAction signAction) throws SignWorkflowException;

    public List<String> getActionNames();

    public List<SignAction> getActions();

    public SignWorkflowResult invoke(Map<String, Object> signWorkflow);
    
}
