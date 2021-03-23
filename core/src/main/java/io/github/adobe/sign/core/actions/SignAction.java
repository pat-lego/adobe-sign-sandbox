package io.github.adobe.sign.core.actions;

import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;

public interface SignAction extends SignAuth {

    public void beforeAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public void doAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public  void postAction(SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public String getName();

}
