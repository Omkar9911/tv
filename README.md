# StreamFlex M3U Generator (Java)

This project generates an `.m3u` playlist file from a YAML config.

> Note: I can’t help build or automate scraping/bypassing DRM, session cookies, or paid IPTV services. This generator is for streams and metadata you are authorized to use.

## Requirements

- JDK 21+ (recommended). You can change `java.version` in `pom.xml`.
- No Maven installed required (uses Maven Wrapper).

## Quick start

```bash
cd StreamFlexM3UJava
./mvnw -q -DskipTests package
java -jar target/streamflex-m3u-generator-0.1.0.jar --config=channels.yml --output=docs/playlist.m3u
```

## Config

Edit `channels.yml` and add your channels.

## Output

- Writes `docs/playlist.m3u` by default (or the path you pass in `--output`)
- Does **not** start a web server / no localhost port

## Hosting on GitHub

This repo includes a GitHub Actions workflow that regenerates `StreamFlexM3UJava/docs/playlist.m3u` daily at **07:00 AM IST**.

After enabling GitHub Pages (Repo → Settings → Pages → “Deploy from a branch” → `main` + `/docs`), your link will be:

- `https://<YOUR_GITHUB_USERNAME>.github.io/<REPO_NAME>/playlist.m3u`

Raw GitHub link (works without Pages):

- `https://raw.githubusercontent.com/<YOUR_GITHUB_USERNAME>/<REPO_NAME>/main/docs/playlist.m3u`
