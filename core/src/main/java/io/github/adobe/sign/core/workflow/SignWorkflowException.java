package io.github.adobe.sign.core.workflow;

public class SignWorkflowException extends Exception {
    public SignWorkflowException(String msg) {
        super(msg);
    }
    
    public SignWorkflowException(String msg, Throwable t) {
        super(msg, t);
    }
}
