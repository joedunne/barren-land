# Barren Land Analysis
 
 Calculate the continuous fertile areas of a 600x300 meter farm given the input of barren
 areas which are defined by rectangles. Each fertile area is of no particular shape and does not
 contain any barren land.
 
 Output the area in square meters of each continous piece of land sorted from smallest area
 to greatest.
  
 ## Getting Started
 
 These instructions will get you a copy of the project up and running on your local machine 
 for development and testing purposes. 
 
 ### Prerequisites
 
 The following are needed or recommended.
 
 ```
 OpenJDK v8 or higher
 IntelliJ IDEA with Lombok plugin for development
 MacBook Pro - really just get one
 ```
 
 ## Running the tests
 
 Unit tests are run with the following gradle command
```
./gradlew clean test
```
Note that there are two tests that log stack traces, but this is expected as they are testing
invalid input.
 
 ## Running the Spring Boot Jar
 
 First build the bootJar
 ```
./gradlew clean bootJar
```
Then issue the java command with at least 256M of stack memory. The increased stack is needed
over the default JVM stack size due to the current recursion implementation.

The input string for the barren land areas can either be piped in via the command line, or entered
from the command prompt once the project is runnin.g
```
java -Xss256m -jar build/libs/barren-land-1.0.0.jar
```
or
````
echo "{\"0 292 399 307\"}" | java -Xss256m -jar build/libs/barren-land-1.0.0.jar
````

 ## Built With
 
 * [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) - The build script used
 * [Maven](https://maven.apache.org/) - Dependency Management
  
 ## Authors
 
 * **Joe Dunne** - *Initial work* 
 
 ## Acknowledgments
 
 * Hat tip to StackOverflow.com - I wouldn't do development without it
