#!/bin/sh

exec java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -javaagent:newrelic.jar -Dspring.profiles.active=$ENV -jar sample-extension.jar --server.port=80