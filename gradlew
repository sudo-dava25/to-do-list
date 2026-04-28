#!/bin/sh
#
# Gradle start up script for UN*X
#

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

APP_HOME="`pwd -P`"

MAX_FD="maximum"

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

case "`uname`" in
  Linux* )
    thisDir="`dirname \"$0\"`"
    APP_HOME="`cd \"$thisDir\" > /dev/null && pwd`"
    ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

set -- \
        "-classpath" "$CLASSPATH" \
        org.gradle.wrapper.GradleWrapperMain \
        "$@"

exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "$@"
