package io.github.adobe.sign.impl;

import java.util.Set;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.reflections.Reflections;

import io.github.adobe.sign.core.workflow.SignWorkflowResult;

public class Main {

    public static void main(String[] args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        Reflections reflections = new Reflections("io.github.adobe.sign.impl.workflow");

        Set<Class<?>> cliComponents = reflections.getTypesAnnotatedWith(CliComponent.class);

        for (Class cliComponent : cliComponents) {
            CliCommand command = (CliCommand) cliComponent.getDeclaredConstructor().newInstance();
            if (command.getClass().getAnnotation(CliComponent.class).name().equals("Simple Workflow")) {
                System.out.println(
                        command.execute(parser, SignWorkflowResult.class, args).getMetadata().getValue("TRANSIENT_ID"));
            }
        }

    }
}
