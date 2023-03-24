@echo off
echo "Starting project..."

set TagName=%1

call mvn clean
call mvn compile
call mvn test-compile
call mvn test -Dcucumber.filter.tags="@%TagName%"