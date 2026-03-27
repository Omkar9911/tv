package com.streamflex.m3u.m3u;

import com.streamflex.m3u.config.Channel;
import com.streamflex.m3u.config.PlaylistConfig;
import com.streamflex.m3u.config.PlaylistMeta;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class M3uRendererTest {
  @Test
  void rendersBasicM3u() {
    PlaylistMeta meta = new PlaylistMeta();
    meta.setEpgUrl("https://example.com/epg.xml.gz");
    meta.setHeaderLines(List.of("# Header {{lastUpdated}}"));

    Channel ch = new Channel();
    ch.setName("Example");
    ch.setGroupTitle("Entertainment");
    ch.setTvgLogo("https://example.com/logo.png");
    ch.setUrl("https://example.com/live.m3u8");

    PlaylistConfig cfg = new PlaylistConfig();
    cfg.setPlaylist(meta);
    cfg.setChannels(List.of(ch));

    String out = new M3uRenderer().render(cfg);
    assertTrue(out.startsWith("#EXTM3U x-tvg-url=\"https://example.com/epg.xml.gz\""));
    assertTrue(out.contains("#EXTINF:-1 tvg-logo=\"https://example.com/logo.png\" group-title=\"Entertainment\",Example"));
    assertTrue(out.contains("https://example.com/live.m3u8"));
  }
}

