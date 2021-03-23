package io.github.adobe.sign.core.actions;

import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;

public interface SignAction {

    public SignActionMetadata beforeAction(SignAuth signAuth, SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public SignActionMetadata doAction(SignAuth signAuth, SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public  SignActionMetadata postAction(SignAuth signAuth, SignActionMetadata metadata, SignLogger logger) throws SignActionException;

    public String getName();

}
