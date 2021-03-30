package io.github.adobe.sign.impl.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.core.metadata.SignMetadata;
import io.github.adobe.sign.core.workflow.SignWorkflow;
import io.github.adobe.sign.core.workflow.SignWorkflowException;
import io.github.adobe.sign.core.workflow.SignWorkflowResult;
public abstract class BasicWorkflowExecutor implements SignWorkflow {

    List<SignAction> workflow = new ArrayList<>();

    @Override
    public void addAction(SignAction signAction) throws SignWorkflowException {
        if (signAction != null) {
            workflow.add(signAction);
        }
    }

    @Override
    public void removeAction(SignAction signAction) throws SignWorkflowException {
        if (signAction != null && workflow.contains(signAction)) {
            workflow.remove(signAction);
        }

    }

    @Override
    public final List<String> getActionNames() {
        List<String> names = new ArrayList<>();
        for (SignAction action : workflow) {
            names.add(action.getName());
        }

        return names;
    }

    @Override
    public final List<SignAction> getActions() {
        return workflow;
    }

    @Override
    public SignWorkflowResult invoke(SignAuth signAuth, SignLogger signLogger, Map<String, Object> signWorkflow) {
        SignMetadata metadata = new SignMetadata(){

            private Map<String, Object> map = new HashMap<String, Object>();
            
            @Override
            public Map<String, Object> getMetadata() {
                return map;
            }

            @Override
            public Object getValue(String key) {
                return map.get(key);
            }

            @Override
            public void put(String key, Object value) {
                map.put(key, value);
            }

            @Override
            public void addAll(Map<String, Object> map) {
                if (map != null) {
                    map.putAll(map);
                }
            }

            @Override
            public <T> T getValue(String key, Class<T> type) {
                return type.cast(this.map.get(key));
            }

            @Override
            public <T> T getResult(Class<T> type) {
                return this.getValue("TRANSIENT_ID", type);
            }

            @Override
            public Object getResult() {
                return this.getValue("TRANSIENT_ID");
            }
            
        };
        
        SignWorkflowResult result = new SignWorkflowResult(){

            private Boolean result = Boolean.TRUE;
            private SignMetadata metadata;

            public void setSuccess(boolean result) {
                this.result = result;
            }

            @Override
            public Boolean getSuccess() {
               return result;
            }

            @Override
            public SignMetadata getMetadata() {
                return metadata;
            }

            @Override
            public void setMetadata(SignMetadata metadata) {
                this.metadata = metadata;
            }
            
        };
        result.setMetadata(metadata);
        
        try {
            for (SignAction action : workflow) {
                metadata = Optional.ofNullable(action.beforeAction(signAuth, metadata, signLogger)).orElse(metadata);
                metadata = Optional.ofNullable(action.doAction(signAuth, metadata, signLogger)).orElse(metadata);
                metadata = Optional.ofNullable(action.postAction(signAuth, metadata, signLogger)).orElse(metadata);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            return result;
        }
        
        return result;
    }

}
