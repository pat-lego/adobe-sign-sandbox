package io.github.adobe.sign.impl.actions;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.actions.SignActionException;
import io.github.adobe.sign.core.actions.SignActionMetadata;
import io.github.adobe.sign.core.auth.Authenticated;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuthException;
import io.github.adobe.sign.core.logger.SignLogger;

public class TransientDocument implements SignAction {

    @Override
    public Authenticated authenticate(CredentialLoader credentials) throws SignAuthException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean isValidAuth() throws SignAuthException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void beforeAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
    
}
