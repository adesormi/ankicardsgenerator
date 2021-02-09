package com.adesormi.ankicardsgenerator;

public class ExceptionHandler {

  public void showHelp() {
    System.out.println("\n>> Usage: ./ankicardsgenerator -c <config> -w <working_dir>\n");
    System.out.println("All options need to be provided:");
    System.out.println("  > the configuration file:");
    System.out.println("      -c, --configuration <path-to-file>");
    System.out.println("  > the working directory containing the cards to format:");
    System.out.println("      -w, --working_dir <path-to-dir>\n");
  }
}
