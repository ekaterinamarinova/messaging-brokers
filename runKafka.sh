#!/usr/bin/env bash

gradle bootJar

jarVar=build/libs/messaging-0.0.1.jar

java -jar -Dspring.profiles.active=kafka ${jarVar} #messaging-0.0.1.jar