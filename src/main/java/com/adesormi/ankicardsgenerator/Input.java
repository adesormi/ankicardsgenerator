package com.adesormi.ankicardsgenerator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Input {

  private final Path configFilePath;
  private final Path workingDirPath;

  public Input(String configFileStr, String workingDirStr) {
    this.configFilePath = getConfigFilePath(configFileStr);
    this.workingDirPath = getCardsDirPath(workingDirStr);
  }

  public Path getConfigFilePath() {
    return configFilePath;
  }

  public Path getWorkingDirPath() {
    return workingDirPath;
  }

  private Path getConfigFilePath(String configFileStr) {
    Path file = Paths.get(configFileStr);
    if (!file.toFile().isFile()) throw new InvalidArgsException();
    return file;
  }

  private Path getCardsDirPath(String workingDirStr) {
    Path dir = Paths.get(workingDirStr);
    if (!dir.toFile().isDirectory()) throw new InvalidArgsException();
    return dir;
  }
}
