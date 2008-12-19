#!/bin/bash
java -cp $(echo ${ZENCODER_FLIXENGINE_CLI_HOME}/*.jar | sed 's/ /:/g') tv.zencoder.flix.FlixEngineApiDriver $@
