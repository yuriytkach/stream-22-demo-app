package org.acme;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import picocli.CommandLine;

@CommandLine.Command(name = "hello", description = "Greet the user!", mixinStandardHelpOptions = true)
public class GreetingCommand implements Callable<Integer> {

  @CommandLine.Option(names = { "-n", "--name" }, required = true, description = "User name")
  String name;

  @CommandLine.Option(names = {"-s", "--score"}, defaultValue = "0", description = "Your score")
  int score;

  @CommandLine.Option(names = {"-m", "--moon"}, defaultValue = "HAPPY",
    description = "Your mood. Possible value: ${COMPLETION-CANDIDATES}")
  Mood mood;

  @CommandLine.Option(names = {"-w", "--word"}, description = "Your words (can be specified with coma)", split = ",")
  List<String> words;

  @CommandLine.Option(names = {"-p", "--super-map"}, description = "Your map", split = ",")
  Map<String, String> map;

  @CommandLine.Option(names = {"-a", "--agree"}, description = "Do you agree?", negatable = true)
  private boolean agree;

  @Override
  public Integer call() {
    System.out.printf(
      "Hello %s!%nYour Score: %d%n. Your mood: %s%n. You say: %s%n. You agree: %s%n",
      name,
      score,
      mood,
      words == null ? "null" : String.join(" ", words),
      agree
    );
    System.out.printf("Map: %s%n", map);

    return agree ? 0 : 1;
  }

  // ================================================================================
  //  public static void main(String[] args) {
  //    final CommandLine commandLine = new CommandLine(new GreetingCommand());
  //    commandLine.execute(args);
  //  }
}
