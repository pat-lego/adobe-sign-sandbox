package io.github.adobe.sign.impl;

import java.util.List;

import org.apache.commons.cli.Options;

public interface CliCommand {
    
    public Options getOptions();

    public abstract <T> T execute(Class<T> type, List<String> args) throws Exception;
}
