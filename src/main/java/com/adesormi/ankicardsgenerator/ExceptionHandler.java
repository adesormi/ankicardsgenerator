package com.adesormi.ankicardsgenerator;

public class ExceptionHandler {

  private static final String HELP_MESSAGE =
      "\n>> Usage: ./ankicardsgenerator -c <config> -w <working_dir>\n"
          + "\nAll options need to be provided:"
          + "\n  > the configuration file:"
          + "\n      -c, --configuration <path-to-file>"
          + "\n  > the working directory containing the cards to format:"
          + "\n      -w, --working_dir <path-to-dir>\n";


  public void showHelp() {
    System.out.println(HELP_MESSAGE);
  }
}
