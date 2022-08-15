#!/bin/bash
if [ ! -f ./pom.xml ]
then
    echo "No pom file: ./pom.xml"
    exit 1
fi
if [ ! -f ./target/type-utils.jar ]
then
    echo "No jar file: ./target/type-utils.jar"
    exit 2
fi
if [ ! -d ./artifacts ]
then
    mkdir artifacts
fi
cp target/type-utils.jar artifacts/type-utils.jar
cp pom.xml artifacts/pom.xml