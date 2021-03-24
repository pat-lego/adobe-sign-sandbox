package io.github.adobe.sign.impl.workflow;

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

public class BasicWorkflowExecutor implements SignWorkflow {

    List<SignAction> workflow = new ArrayList<>();

    private SignAuth signAuth;
    private SignLogger signLogger;

    public BasicWorkflowExecutor(SignAuth auth, SignLogger logger) {
        this.signAuth = auth;
        this.signLogger = logger;
    }

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
    public SignWorkflowResult invoke(Map<String, Object> signWorkflow) {
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
                map.putAll(map);
            }
            
        };
        
        try {
            for (SignAction action : workflow) {
                metadata = Optional.ofNullable(action.beforeAction(this.signAuth, metadata, this.signLogger)).orElse(metadata);
                metadata = Optional.ofNullable(action.doAction(this.signAuth, metadata, this.signLogger)).orElse(metadata);
                metadata = Optional.ofNullable(action.postAction(this.signAuth, metadata, this.signLogger)).orElse(metadata);
            }
        } catch (Exception e) {
            return () ->  { return Boolean.FALSE; };
        }
        
        return () ->  { return Boolean.TRUE; };
    }

}
