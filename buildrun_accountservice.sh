#!/bin/bash

# Build and Run the accountservice
cd accountservice
mvn clean install
mvn exec:java -Dexec.mainClass="com.rabobank.accountservice.AccountServiceApplication"

