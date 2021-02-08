package com.adesormi.ankicardsgenerator;

import org.apache.commons.cli.*;

public class CommandLineHandler {

  private static final String CONFIGURATION_OPTION = "settings";
  private static final String WORKING_DIR_OPTION = "working_dir";

  private final CommandLineParser parser;
  private final Options options;

  public CommandLineHandler() {
    parser = new DefaultParser();
    options = createOptions();
  }

  public Input getInput(String[] args) {
    try {
      CommandLine line = parser.parse(options, args);
      validateOptions(line);

      String configurationFileStr = line.getOptionValue(CONFIGURATION_OPTION);
      String workingDirStr = line.getOptionValue(WORKING_DIR_OPTION);
      return new Input(configurationFileStr, workingDirStr);
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
      throw new InvalidArgsException("Need to provide option -c, --configuration");
    }

    if (!line.hasOption(WORKING_DIR_OPTION)) {
      throw new InvalidArgsException("Need to provide option -w, --working_dir");
    }
  }
}
