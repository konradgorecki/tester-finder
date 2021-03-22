# Testers Finder
This project is a simple search engine focused on finding Testers in 2-Dimensional space
defined by testers Country of origin and devices owned by him/her. Focus of the application
is put on a correctness of its output and not on its speed or ability to process large
amounts of data.

## Technologies used

* Java 15
* SpringBoot 2.4.2
* Angular 11
* Angular Material 11
* Gradle 6.7.1
* Spock

## Setup
To run this project You need to have Java 15 installed / unpacked on your computer and
environment variable __JAVA_HOME__ set to point to its catalogue.

For Unix systems:
```
export JAVA_HOME = Path
```
For Windows system:
```
set JAVA_HOME "Path"
```

Application is setup is such a way, that Gradle will be downloaded from the internet.
On the next steps Gradle will download all necessary dependencies (npm included) and
build the application. As part of this automated build, frontend build (by npm install)
will be performed, so the instructions placed below are the only ones needed to run the
application.

To build the application run (standing in the projects main catalogue):
```
gradlew clean install
```

To start the application from the source code  run (standing in the projects main catalogue):
```
gradlew bootRun
```

## Configuration
As a data source this application uses _CSV_ files. Paths to these files should be
defined in the application.yml file that can be found here:
```
app/src/main/resources/application.yml
```