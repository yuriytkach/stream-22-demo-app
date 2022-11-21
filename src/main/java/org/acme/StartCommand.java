package org.acme;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(
  name = "start",
  description = "Start",
  mixinStandardHelpOptions = true,
  subcommands = {
    GreetingCommand.class,
    ByeCommand.class
  }
)
public class StartCommand {
  // Do not implement Runnable nor Callable - that way a subcommand will be required
}
