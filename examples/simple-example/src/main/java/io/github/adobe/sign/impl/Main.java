package io.github.adobe.sign.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.impl.auth.BasicSignAuth;
import io.github.adobe.sign.impl.auth.FileCredentialLoader;

public class Main {

    public static void main(String[] args) throws ParseException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Options options = new Options();
        options.addOption(Option.builder("wf").longOpt("workflowName").hasArg(true).required(true)
                .desc("The name of the workflow").build())
                .addOption(Option.builder("c").hasArg(true).required(true).desc("The path to the cred file").build());


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        cmd = parser.parse(options, args);

        if (cmd.hasOption("wf") && cmd.hasOption("c")) {
            String workflowName = cmd.getOptionValue("wf");
            System.out.println(String.format("Retrieved the following workflow to invoke %s", workflowName));

            Constructor obj = Class.forName(workflowName).getDeclaredConstructor(CredentialLoader.class, SignAuth.class, SignLogger.class);
            obj.newInstance(new FileCredentialLoader(cmd.getOptionValue("c")), new BasicSignAuth(), null);

        }
    }

}
