package io.github.adobe.sign.impl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;

public interface CliCommand {
    
    public Options getOptions();

    public <T> T execute(CommandLineParser cli, Class<T> type, String... args) throws Exception;
}
