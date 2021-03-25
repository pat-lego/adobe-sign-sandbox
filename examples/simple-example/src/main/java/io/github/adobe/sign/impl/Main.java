package io.github.adobe.sign.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.reflections.Reflections;

import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.core.workflow.SignWorkflowResult;
import io.github.adobe.sign.impl.auth.BasicSignAuth;

public class Main {

    public static void main(String[] args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        Reflections reflections = new Reflections("io.github.adobe.sign.impl.workflow");

        Set<Class<?>> cliComponents = reflections.getTypesAnnotatedWith(io.github.adobe.sign.impl.CliComponent.class);

        for (Class cliComponent: cliComponents) {
            CliCommand command = (CliCommand) cliComponent.getDeclaredConstructor(SignAuth.class, SignLogger.class).newInstance(new BasicSignAuth(), null);
            System.out.println(command.execute(parser, SignWorkflowResult.class, args).success());
        }

    }
}
