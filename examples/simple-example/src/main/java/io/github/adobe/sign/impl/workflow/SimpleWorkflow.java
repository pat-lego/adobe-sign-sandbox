package io.github.adobe.sign.impl.workflow;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import io.github.adobe.sign.core.actions.SignAction;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.logger.SignLogger;
import io.github.adobe.sign.core.workflow.SignWorkflowException;
import io.github.adobe.sign.core.workflow.SignWorkflowResult;
import io.github.adobe.sign.impl.CliComponent;
import io.github.adobe.sign.impl.CliCommand;
import io.github.adobe.sign.impl.actions.TransientDocument;
import io.github.adobe.sign.impl.auth.FileCredentialLoader;
import io.github.adobe.sign.impl.executor.BasicWorkflowExecutor;

@CliComponent(name = "Simple Workflow")
public class SimpleWorkflow extends BasicWorkflowExecutor implements CliCommand {

    SignAction transientDocument;

    public SimpleWorkflow(SignAuth auth, SignLogger logger) throws SignWorkflowException {
        super(auth, logger);
    }

    @Override
    public Options getOptions() {
        return new Options()
                .addOption(Option.builder("c").hasArg(true).required(true).desc("The path to the cred file").build());
    }

    @Override
    public <T> T execute(CommandLineParser cli, Class<T> type, String... args) throws Exception {
        CommandLine parsedCli = null;
        if (args != null) {
            parsedCli = cli.parse(getOptions(), args);

            if (parsedCli != null && parsedCli.hasOption("c")) {

                CredentialLoader credentialLoader = new FileCredentialLoader(parsedCli.getOptionValue("c"));
                this.transientDocument = new TransientDocument(credentialLoader);

                // Add the work item to the workflow
                this.addAction(this.transientDocument);

                // Run it
                return type.cast(this.invoke(null));
            }
        }
        throw new IllegalArgumentException("Cannot have a null CLI string sent to be processed");
    }

}
