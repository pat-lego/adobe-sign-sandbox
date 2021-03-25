package io.github.adobe.sign.core.workflow;

import io.github.adobe.sign.core.metadata.SignMetadata;

public interface SignWorkflowResult {
    
    public Boolean getSuccess();

    public void setSuccess(boolean result);

    public SignMetadata getMetadata();

    public void setMetadata(SignMetadata metadata);
}
