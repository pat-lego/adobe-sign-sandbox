package io.github.adobe.sign.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.reflections.Reflections;

import io.github.adobe.sign.core.workflow.SignWorkflowResult;

public class Main {

    private static final String W = "w";
    public static void main(String[] args) throws Exception {

        Options options = new Options();
        options.addOption(Option.builder("w").hasArg().build());
        CommandLineParser parser = new DefaultParser();

        CommandLine cli = parser.parse(options, getWorkflowName(args), true);

        Reflections reflections = new Reflections("io.github.adobe.sign.impl");

        Set<Class<?>> cliComponents = reflections.getTypesAnnotatedWith(CliComponent.class);

        if (cli.hasOption("w")) {
            for (Class cliComponent : cliComponents) {
                CliCommand command = (CliCommand) cliComponent.getDeclaredConstructor().newInstance();
                if (command.getClass().getAnnotation(CliComponent.class).name().equals(cli.getOptionValue(W))) {
                    System.out.println(
                            command.execute(SignWorkflowResult.class, removeWorkflowName(args)).getMetadata().getValue("TRANSIENT_ID"));
                }
            }
        } else {
            System.err.println("System does not have the workflowName specified exiting now");
            System.exit(1);
        }
    }

    public static String[] getWorkflowName(String... args) {
        String[] result = new String[2];
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(String.format("-%s", W))) {
                result[0] = args[i];
                result[1] = args[i+1];
                break;
            }
        }

        return result;
    }

    public static List<String> removeWorkflowName(String... args) {
        List<String> argsList = new LinkedList<>(Arrays.asList(args));
        for (int i = 0; i < argsList.size() - 1; i++) {
            if (argsList.get(i).equals(String.format("-%s", W))) {
                argsList.remove(i);
                argsList.remove(i);
                break;
            }
        }

        return argsList;
    }
}
