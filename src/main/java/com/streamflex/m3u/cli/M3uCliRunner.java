package com.streamflex.m3u.cli;

import com.streamflex.m3u.config.PlaylistConfig;
import com.streamflex.m3u.io.ConfigLoader;
import com.streamflex.m3u.m3u.M3uRenderer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class M3uCliRunner implements CommandLineRunner {
  private final ConfigLoader configLoader;
  private final M3uRenderer renderer;

  public M3uCliRunner(ConfigLoader configLoader, M3uRenderer renderer) {
    this.configLoader = configLoader;
    this.renderer = renderer;
  }

  @Override
  public void run(String... args) throws Exception {
    CliArgs cliArgs = CliArgs.parse(args);
    if (cliArgs == null) {
      return;
    }

    PlaylistConfig config = configLoader.load(Path.of(cliArgs.configPath()));
    String m3u = renderer.render(config);

    if (cliArgs.printToStdout()) {
      System.out.print(m3u);
      return;
    }

    Path out = Path.of(cliArgs.outputPath());
    if (out.getParent() != null) {
      Files.createDirectories(out.getParent());
    }
    Files.writeString(out, m3u, StandardCharsets.UTF_8);
    System.out.println("Wrote M3U: " + out.toAbsolutePath());
  }
}
