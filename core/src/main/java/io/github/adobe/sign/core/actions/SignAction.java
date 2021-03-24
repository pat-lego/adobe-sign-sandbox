package io.github.adobe.sign.core.actions;

import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.core.metadata.SignMetadata;

public interface SignAction {

    public SignMetadata beforeAction(SignAuth signAuth, SignMetadata metadata, SignLogger logger) throws SignActionException;

    public SignMetadata doAction(SignAuth signAuth, SignMetadata metadata, SignLogger logger) throws SignActionException;

    public  SignMetadata postAction(SignAuth signAuth, SignMetadata metadata, SignLogger logger) throws SignActionException;

    public String getName();

}
