package com.streamflex.m3u.config;

import java.util.ArrayList;
import java.util.List;

public class Channel {
  private String name;
  private String groupTitle;
  private String tvgId;
  private String tvgName;
  private String tvgLogo;
  private String url;
  private String vlcUserAgent;
  private String extHttp;
  private List<String> kodiProps = new ArrayList<>();
  private List<String> extraLines = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGroupTitle() {
    return groupTitle;
  }

  public void setGroupTitle(String groupTitle) {
    this.groupTitle = groupTitle;
  }

  public String getTvgId() {
    return tvgId;
  }

  public void setTvgId(String tvgId) {
    this.tvgId = tvgId;
  }

  public String getTvgName() {
    return tvgName;
  }

  public void setTvgName(String tvgName) {
    this.tvgName = tvgName;
  }

  public String getTvgLogo() {
    return tvgLogo;
  }

  public void setTvgLogo(String tvgLogo) {
    this.tvgLogo = tvgLogo;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getVlcUserAgent() {
    return vlcUserAgent;
  }

  public void setVlcUserAgent(String vlcUserAgent) {
    this.vlcUserAgent = vlcUserAgent;
  }

  public String getExtHttp() {
    return extHttp;
  }

  public void setExtHttp(String extHttp) {
    this.extHttp = extHttp;
  }

  public List<String> getKodiProps() {
    return kodiProps;
  }

  public void setKodiProps(List<String> kodiProps) {
    this.kodiProps = kodiProps;
  }

  public List<String> getExtraLines() {
    return extraLines;
  }

  public void setExtraLines(List<String> extraLines) {
    this.extraLines = extraLines;
  }
}

