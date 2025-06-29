<h1 align="center">Elephas</h1>

<h3 align="center">Sample project consisting of multiple modules featuring <br/> code coverage and custom plugins

## Table of Contents
1. [Project Structure](#project-structure)
2. [Tests](#tests)
3. [Gradle Plugin](#gradle-plugins)

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

## Gradle Plugins

### Java Conventions

Each subproject applies the `dev.sirtimme.java-convention` plugin. The plugin configures the appropiate java configurations. The plugin looks the following:

```kt
package dev.sirtimme.gradle

plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
```

### Test Conventions

Each subproject applies the `dev.sirtimme.test-convention` plugin. The plugin configures the necessary Gradle tasks to make the project JUnit compatible. The plugin looks the following:

```kt
package dev.sirtimme.gradle

tasks.named<Test>("test") {
    testLogging {
        events("started", "passed", "skipped", "failed")
    }
    useJUnitPlatform()
}
```