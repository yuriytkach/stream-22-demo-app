package org.acme;

import picocli.CommandLine;

@CommandLine.Command(name = "bye", description = "Say good bye", mixinStandardHelpOptions = true)
public class ByeCommand implements Runnable {

  @CommandLine.Option(names = { "-d", "--date" }, required = true, description = "Next seen date")
  private String date;

  @Override
  public void run() {
    System.out.printf("See you %s!%n", date);
  }
}
