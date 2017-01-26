#!/bin/bash
port=$1
filename=$2
if [ ! -e "blast.jar" ] ;then
    make jar
fi
java -jar blast.jar $port $filename
