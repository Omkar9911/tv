package com.streamflex.m3u.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.streamflex.m3u.config.PlaylistConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class ConfigLoader {
  private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

  public PlaylistConfig load(Path path) throws IOException {
    if (!Files.exists(path)) {
      throw new IOException("Config file not found: " + path.toAbsolutePath());
    }
    return yamlMapper.readValue(Files.newInputStream(path), PlaylistConfig.class);
  }
}

