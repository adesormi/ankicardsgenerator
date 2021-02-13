package com.adesormi.ankicardsgenerator.cli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandLineHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineHandler.class);

  private static final String CONFIGURATION_OPTION = "configuration";
  private static final String WORKING_DIR_OPTION = "working_dir";

  private final CommandLineParser parser;
  private final Options options;

  public CommandLineHandler() {
    parser = new DefaultParser();
    options = createOptions();
  }

  public Input parseInput(String[] args) {
    LOGGER.info("Parsing input arguments");
    try {
      CommandLine line = parser.parse(options, args);
      validateOptions(line);
      return new Input(line.getOptionValue(CONFIGURATION_OPTION),
                       line.getOptionValue(WORKING_DIR_OPTION));
    } catch(ParseException e) {
      throw new InvalidArgsException();
    }
  }

  private Options createOptions() {
    Options options = new Options();
    options.addOption(new Option("c", CONFIGURATION_OPTION, true, "The configuration to use to generate Anki cards."));
    options.addOption(new Option("w", WORKING_DIR_OPTION, true, "The directory containing the cards to format."));
    return options;
  }

  private void validateOptions(CommandLine line) {
    if (!line.hasOption(CONFIGURATION_OPTION)) {
      throw new InvalidArgsException();
    }

    if (!line.hasOption(WORKING_DIR_OPTION)) {
      throw new InvalidArgsException();
    }
  }
}
