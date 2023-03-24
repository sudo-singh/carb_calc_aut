#!/bin/bash

echo "Beginning test execution...."

tagName=$1

echo $tagName

mvn clean
mvn compile
mvn test-compile
mvn test -Dcucumber.filter.tags="@$tagName"