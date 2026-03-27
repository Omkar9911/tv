package com.streamflex.m3u.config;

import java.util.ArrayList;
import java.util.List;

public class PlaylistMeta {
  private String epgUrl;
  private String title;
  private String support;
  private String timezone = "Asia/Kolkata";
  private String lastUpdatedFormat = "dd MMM uuuu, hh:mm a z";
  private List<String> headerLines = new ArrayList<>();
  private boolean groupSections;
  private String groupHeaderTemplate = "# ========== {{group}} ==========";
  private List<String> groupOrder = new ArrayList<>();

  public String getEpgUrl() {
    return epgUrl;
  }

  public void setEpgUrl(String epgUrl) {
    this.epgUrl = epgUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSupport() {
    return support;
  }

  public void setSupport(String support) {
    this.support = support;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public String getLastUpdatedFormat() {
    return lastUpdatedFormat;
  }

  public void setLastUpdatedFormat(String lastUpdatedFormat) {
    this.lastUpdatedFormat = lastUpdatedFormat;
  }

  public List<String> getHeaderLines() {
    return headerLines;
  }

  public void setHeaderLines(List<String> headerLines) {
    this.headerLines = headerLines;
  }

  public boolean isGroupSections() {
    return groupSections;
  }

  public void setGroupSections(boolean groupSections) {
    this.groupSections = groupSections;
  }

  public String getGroupHeaderTemplate() {
    return groupHeaderTemplate;
  }

  public void setGroupHeaderTemplate(String groupHeaderTemplate) {
    this.groupHeaderTemplate = groupHeaderTemplate;
  }

  public List<String> getGroupOrder() {
    return groupOrder;
  }

  public void setGroupOrder(List<String> groupOrder) {
    this.groupOrder = groupOrder;
  }
}
