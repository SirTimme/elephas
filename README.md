<h1 align="center">Elephas</h1>

<h3 align="center">Sample project consisting of multiple modules featuring <br/> code coverage and custom plugins

## Table of Contents
1. [Project Structure](#project-structure)
2. [Tests](#tests)
3. [Gradle Plugin](#gradle-plugin)

## Project Structure

````
------------------------------------------------------------
Root project 'elephas'
------------------------------------------------------------

Root project 'elephas'
\--- Project ':modules'
     +--- Project ':modules:adder'
     +--- Project ':modules:divider'
     +--- Project ':modules:multiplier'
     \--- Project ':modules:subtracter'
````

## Tests

Each subproject contains a unit test which test their respective function (adding, subtracting, multiplying, dividing).

```shell
PS ~/git/elephas> gradle test

MainTest > mainPrintHelloWorld() "PASSED"
AdderTest > testAdd() "PASSED"
DividerTest > testDivide() "PASSED"
DividerTest > testWhenDiscNull() "PASSED"
MultiplierTest > testMultiply() "PASSED"
SubtracterTest > testSubtract() "PASSED"
```

## Gradle Plugin

Each subproject applies the `dev.sirtimme.test-convention` plugin. The plugin uses the `java` plugin to be able to modify the `gradle test` command and adds the JUnit dependency. The plugin looks the following:
```kt
plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}
```