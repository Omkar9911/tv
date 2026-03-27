package com.streamflex.m3u.m3u;

import com.streamflex.m3u.config.Channel;
import com.streamflex.m3u.config.PlaylistConfig;
import com.streamflex.m3u.config.PlaylistMeta;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class M3uRenderer {
  public String render(PlaylistConfig config) {
    PlaylistMeta meta = config.getPlaylist();
    StringBuilder out = new StringBuilder(16_384);

    if (meta.getEpgUrl() != null && !meta.getEpgUrl().isBlank()) {
      out.append("#EXTM3U x-tvg-url=\"").append(meta.getEpgUrl()).append("\"\n");
    } else {
      out.append("#EXTM3U\n");
    }

    String lastUpdated = formatLastUpdated(meta);
    for (String line : meta.getHeaderLines()) {
      out.append(replaceVars(line, meta, lastUpdated)).append("\n");
    }
    if (!meta.getHeaderLines().isEmpty()) {
      out.append("\n");
    }

    if (meta.isGroupSections()) {
      Map<String, List<Channel>> groups = groupByTitle(config.getChannels());
      for (String group : orderedGroups(groups.keySet(), meta.getGroupOrder())) {
        out.append(renderGroupHeader(meta, group)).append("\n");
        for (Channel channel : groups.getOrDefault(group, List.of())) {
          appendChannel(out, channel);
          out.append("\n");
        }
      }
    } else {
      for (Channel channel : config.getChannels()) {
        appendChannel(out, channel);
        out.append("\n");
      }
    }

    return out.toString();
  }

  private static Map<String, List<Channel>> groupByTitle(List<Channel> channels) {
    Map<String, List<Channel>> groups = new LinkedHashMap<>();
    for (Channel c : channels) {
      String group = notBlank(c.getGroupTitle()) ? c.getGroupTitle().trim() : "Other";
      groups.computeIfAbsent(group, ignored -> new ArrayList<>()).add(c);
    }
    return groups;
  }

  private static List<String> orderedGroups(Set<String> groups, List<String> preferredOrder) {
    List<String> ordered = new ArrayList<>();
    for (String group : preferredOrder) {
      if (groups.contains(group)) {
        ordered.add(group);
      }
    }
    for (String group : groups) {
      if (!ordered.contains(group)) {
        ordered.add(group);
      }
    }
    return ordered;
  }

  private static String renderGroupHeader(PlaylistMeta meta, String group) {
    String template = meta.getGroupHeaderTemplate();
    if (!notBlank(template)) {
      template = "# ========== {{group}} ==========";
    }
    return template.replace("{{group}}", group);
  }

  private static void appendChannel(StringBuilder out, Channel c) {
    out.append("#EXTINF:-1");
    if (notBlank(c.getTvgId())) {
      out.append(" tvg-id=\"").append(escapeAttr(c.getTvgId())).append("\"");
    }
    if (notBlank(c.getTvgName())) {
      out.append(" tvg-name=\"").append(escapeAttr(c.getTvgName())).append("\"");
    }
    if (notBlank(c.getTvgLogo())) {
      out.append(" tvg-logo=\"").append(escapeAttr(c.getTvgLogo())).append("\"");
    }
    if (notBlank(c.getGroupTitle())) {
      out.append(" group-title=\"").append(escapeAttr(c.getGroupTitle())).append("\"");
    }
    out.append(",").append(c.getName() == null ? "" : c.getName()).append("\n");

    for (String prop : c.getKodiProps()) {
      if (notBlank(prop)) {
        out.append("#KODIPROP:").append(prop.trim()).append("\n");
      }
    }

    if (notBlank(c.getVlcUserAgent())) {
      out.append("#EXTVLCOPT:http-user-agent=").append(c.getVlcUserAgent().trim()).append("\n");
    }

    if (notBlank(c.getExtHttp())) {
      out.append("#EXTHTTP:").append(c.getExtHttp().trim()).append("\n");
    }

    for (String extra : c.getExtraLines()) {
      if (notBlank(extra)) {
        out.append(extra.trim()).append("\n");
      }
    }

    if (!notBlank(c.getUrl())) {
      throw new IllegalArgumentException("Channel URL is missing for: " + c.getName());
    }
    out.append(c.getUrl().trim()).append("\n");
  }

  private static String replaceVars(String line, PlaylistMeta meta, String lastUpdated) {
    String out = line;
    out = out.replace("{{lastUpdated}}", lastUpdated);
    if (meta.getTitle() != null) {
      out = out.replace("{{title}}", meta.getTitle());
    }
    if (meta.getSupport() != null) {
      out = out.replace("{{support}}", meta.getSupport());
    }
    return out;
  }

  private static String formatLastUpdated(PlaylistMeta meta) {
    ZoneId zoneId = ZoneId.of(meta.getTimezone() == null ? "Asia/Kolkata" : meta.getTimezone());
    ZonedDateTime now = ZonedDateTime.now(zoneId);
    DateTimeFormatter fmt =
        DateTimeFormatter.ofPattern(
            meta.getLastUpdatedFormat() == null ? "dd MMM uuuu, hh:mm a z" : meta.getLastUpdatedFormat(),
            Locale.ENGLISH);
    return fmt.format(now);
  }

  private static boolean notBlank(String s) {
    return s != null && !s.isBlank();
  }

  private static String escapeAttr(String s) {
    return s.replace("\"", "&quot;");
  }
}
