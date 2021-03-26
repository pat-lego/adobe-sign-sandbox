package io.github.adobe.sign.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import io.github.adobe.sign.core.workflow.SignWorkflowResult;

public class Main {

    private static final String W = "w";

    public static void main(String[] args) throws Exception {

        Reflections reflections = new Reflections("io.github.adobe.sign.impl");
        Set<Class<?>> cliComponents = reflections.getTypesAnnotatedWith(CliComponent.class);

        for (Class cliComponent : cliComponents) {
            CliCommand command = (CliCommand) cliComponent.getDeclaredConstructor().newInstance();
            if (command.getClass().getAnnotation(CliComponent.class).name().equals(getWorkflowName(args))) {
                System.out.println(command.execute(SignWorkflowResult.class, removeWorkflowName(args)).getMetadata()
                        .getValue("TRANSIENT_ID"));
            }
        }

    }

    /**
     * Get the workflow name from the CLI command
     * 
     * @param args The command line args sent into the appplication
     * @return The -w input as well as the argument that follows
     */
    public static String getWorkflowName(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(String.format("-%s", W))) {
                return args[i + 1];
            }
        }

        throw new IllegalArgumentException("Could not locate the workflow name from the CLI parameters");
    }

    /**
     * Remove the workflow argument from the cli args and only keep what is left
     * 
     * @param args CLI arguments
     * @return Array without the workflow arguments in it
     */
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
