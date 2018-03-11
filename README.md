# BANANA BEST BANK 

Banana Best Bank REST spring boot application.
Application allows check balance, increase and decrease balance and generates one-time passwords using 
aerogear-otp-java library `https://github.com/aerogear/aerogear-otp-java`. Token is valid about 40s. 

### App startup
The application can be started by issuing the following command in the command line:

on mac/linux:
```bash
./gradlew clean bootRun
```
on windows:
```bash
.\gradlew clean bootRun
```
Ensure you have correct JAVA_HOME path.

## Stopping the Service
To stop the service (when it is running with `gradle bootRun`) use Control-C.


## IntelliJ Idea development
1. Install Lombok, MapStruct plugins under File -> Settings -> Plugins -> Browse repositories... search for the Lombok, MapStruct and install them all.
2. Check the Enable annotation processing checkbox under File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors.


## Test runs
Tests can be run by the following command in the command line:

on mac/linux:
```bash
./gradlew clean test
```
on windows:
```bash
.\gradlew clean test
```
Ensure you have correct JAVA_HOME path.

## API

- `GET /balance/user/<userId>`
- `GET /history/user/<userId>`
- `POST /balance/increase/user/<userId>`
- `POST /balance/decrease/user/<userId>`
- `POST /tokens/user/<userId>`  

 