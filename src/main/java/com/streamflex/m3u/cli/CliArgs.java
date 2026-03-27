package com.streamflex.m3u.cli;

import java.util.HashMap;
import java.util.Map;

final class CliArgs {
  private final String configPath;
  private final String outputPath;
  private final boolean printToStdout;

  private CliArgs(String configPath, String outputPath, boolean printToStdout) {
    this.configPath = configPath;
    this.outputPath = outputPath;
    this.printToStdout = printToStdout;
  }

  static CliArgs parse(String[] args) {
    Map<String, String> flags = new HashMap<>();
    boolean print = false;
    boolean help = false;
    for (String arg : args) {
      if (arg == null || arg.isBlank()) {
        continue;
      }
      if (arg.equals("--help") || arg.equals("-h")) {
        help = true;
        continue;
      }
      if (arg.equals("--print")) {
        print = true;
        continue;
      }
      if (arg.startsWith("--") && arg.contains("=")) {
        int idx = arg.indexOf('=');
        String key = arg.substring(2, idx).trim();
        String value = arg.substring(idx + 1).trim();
        flags.put(key, value);
      }
    }

    String configPath = flags.getOrDefault("config", "channels.yml");
    String outputPath = flags.getOrDefault("output", "docs/playlist.m3u");

    if (help) {
      System.out.println("StreamFlex M3U Generator");
      System.out.println("Usage:");
      System.out.println("  java -jar streamflex-m3u-generator.jar --config=channels.yml --output=docs/playlist.m3u");
      System.out.println("  java -jar streamflex-m3u-generator.jar --config=channels.yml --print");
      return null;
    }

    if (configPath.isBlank()) {
      System.err.println("Missing --config=PATH");
      return null;
    }
    if (!print && outputPath.isBlank()) {
      System.err.println("Missing --output=PATH (or use --print)");
      return null;
    }

    return new CliArgs(configPath, outputPath, print);
  }

  String configPath() {
    return configPath;
  }

  String outputPath() {
    return outputPath;
  }

  boolean printToStdout() {
    return printToStdout;
  }
}
