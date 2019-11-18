#!/bin/bash

ARGS="$@"

MAIN_CLASS="com.huawei.startup.GenerationMain"

BIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
APP_HOME=`cd $BIN_DIR/.. && pwd`
echo "App home directory: $APP_HOME"
echo

CLASSPATH="$APP_HOME/lib/*"

COMMAND='java -classpath "$CLASSPATH" $MAIN_CLASS $ARGS'

echo "$COMMAND"
eval "$COMMAND"
