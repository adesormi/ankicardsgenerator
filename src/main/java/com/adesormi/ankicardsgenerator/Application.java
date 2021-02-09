package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cli.CommandLineHandler;
import com.adesormi.ankicardsgenerator.cli.Input;
import com.adesormi.ankicardsgenerator.configuration.Configuration;
import com.adesormi.ankicardsgenerator.configuration.ConfigurationHandler;
import com.adesormi.ankicardsgenerator.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    System.out.println();
    LOGGER.info("Starting Anki Cards Generator...");
    CommandLineHandler commandLineHandler = new CommandLineHandler();
    ExceptionHandler exceptionHandler = new ExceptionHandler();
    try {
      Input input = commandLineHandler.parseInput(args);
      Application.run(input);
    } catch (Exception e) {
      exceptionHandler.showHelp();
      LOGGER.info("Cards were not generated");
      System.exit(0);
    }
    LOGGER.info("Cards generated successfully\n");
  }

  private static void run(Input input) {
    Configuration configuration =
        new ConfigurationHandler().loadConfiguration(input.getConfigFilePath());
    FileReader fileReader = new FileReader(new CardReader(configuration.getCardFactory()));
    FileWriter fileWriter = new FileWriter(new CardWriter());
    Generator generator = new Generator(fileReader, configuration.getCardFormatter(), fileWriter);
    generator.generateCards(input.getWorkingDirPath());
  }
}
