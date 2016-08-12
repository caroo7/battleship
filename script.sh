#!/bin/bash

java -jar Server/target/server-executable.jar & java -jar Client/target/client-executable.jar & java -jar Client/target/client-executable.jar
