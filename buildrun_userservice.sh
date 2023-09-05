#!/bin/bash

# Run the userservice
cd userservice
mvn clean install
mvn exec:java -Dexec.mainClass="com.rabobank.userservice.UserServiceApplication"



