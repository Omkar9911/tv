#!/bin/sh
# Apache Maven Wrapper
# https://maven.apache.org/wrapper/

set -euf

BASE_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
WRAPPER_DIR="$BASE_DIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
PROPS_FILE="$WRAPPER_DIR/maven-wrapper.properties"

if [ ! -f "$WRAPPER_JAR" ]; then
  mkdir -p "$WRAPPER_DIR"
  WRAPPER_URL=$(grep -E '^wrapperUrl=' "$PROPS_FILE" | cut -d= -f2-)
  curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR" 2>/dev/null || \
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
fi

exec java ${JAVA_OPTS:-} -classpath "$WRAPPER_JAR" -Dmaven.multiModuleProjectDirectory="$BASE_DIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
