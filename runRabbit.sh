#!/usr/bin/env bash

gradle bootJar

jarVar=build/libs/producers-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=rabbit ${jarVar} #producers-0.0.1-SNAPSHOT.jar