package org.acme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;

class CmdTest {

  @Test
  void shouldNotProcessOnMissingArgs() {
    final CommandLine commandLine = new CommandLine(new GreetingCommand());
    assertThatThrownBy(() -> commandLine.parseArgs(""))
      .isInstanceOf(CommandLine.MissingParameterException.class)
      .hasMessageContaining("required option: '--name=<name>'");
  }

  @Test
  void shouldProcess() {
    final CommandLine commandLine = new CommandLine(new StartCommand());
    final CommandLine.ParseResult parseResult = commandLine.parseArgs("hello", "-n", "Yuriy", "-a");
    assertThat(parseResult.hasSubcommand()).isTrue();

    final CommandLine.ParseResult subcommandParsedResult = parseResult.subcommand();
    assertThat(subcommandParsedResult.hasMatchedOption("-n")).isTrue();
    assertThat(subcommandParsedResult.hasMatchedOption("-a")).isTrue();
    assertThat(subcommandParsedResult.hasMatchedOption("-m")).isFalse();

    final int result = commandLine.execute("hello", "-n", "Yuriy", "-a");
    assertThat(result).isZero();
  }

}
