package com.streamflex.m3u.config;

import java.util.ArrayList;
import java.util.List;

public class PlaylistConfig {
  private PlaylistMeta playlist = new PlaylistMeta();
  private List<Channel> channels = new ArrayList<>();

  public PlaylistMeta getPlaylist() {
    return playlist;
  }

  public void setPlaylist(PlaylistMeta playlist) {
    this.playlist = playlist;
  }

  public List<Channel> getChannels() {
    return channels;
  }

  public void setChannels(List<Channel> channels) {
    this.channels = channels;
  }
}

