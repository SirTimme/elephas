<h1 align="center">Elephas</h1>

<h3 align="center">Showcase of a multi-project setup using gradle<br/>featuring code coverage and dockerized Teamcity pipeline</h3>

## Table of Contents
1. [Project Structure](#project-structure)
2. [Tests](#tests)
3. [Gradle Plugins](#gradle-plugins)
4. [Teamcity](#teamcity)
   1. [Docker setup](#docker-setup)
   2. [Workflow](#workflow)

## Project structure

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
PS C:\GitHub\SirTimme\elephas> gradle test

MainTest > mainPrintHelloWorld() "PASSED"
AdderTest > testAdd() "PASSED"
DividerTest > testDivide() "PASSED"
DividerTest > testWhenDiscNull() "PASSED"
MultiplierTest > testMultiply() "PASSED"
SubtracterTest > testSubtract() "PASSED"
```

## Gradle Plugins

Each subproject implements the `org.example.testing` plugin which itself contains a JUnit dependency for allowing testing.
The plugin looks the following:
```
plugins {
    id "java"
}

dependencies {
    testImplementation testlibs.junit.jupiter
}

test {
    useJUnitPlatform()
}
```

The plugin applies the `java` plugin to be able to modify the `gradle test` task and adds the JUnit dependency.\
Interesting is the `testlibs.junit.jupiter` statement where the JUnit dependency is defined. This is the gradle way of using a so-called `version catalog`.
The catalog is defined in the `settings.gradle` at the projectroot and looks like this:

```
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        testlibs {
            library("junit-jupiter", "org.junit.jupiter:junit-jupiter:5.9.1")
        }
    }
}
```

## Teamcity

### Docker setup

This repository contains a `docker-compose.yml` which configures all required services for a functioning teamcity instance.

```yaml
version: '3.8'

name: elephas
services:
  teamcity-database:
    image: postgres:${POSTGRES_VERSION}
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "5432:5432"
    volumes:
      - pg-data:/var/lib/postgresql/data

  teamcity-server:
    image: jetbrains/teamcity-server:${TEAMCITY_VERSION}
    ports:
      - "8111:8111"
    volumes:
      - server-data:/data/teamcity_server/datadir
      - server-logs:/opt/teamcity/logs
    depends_on:
      - teamcity-database

  teamcity-agent:
    image: jetbrains/teamcity-agent:${TEAMCITY_VERSION}
    privileged: true
    environment:
      SERVER_URL: ${SERVER_URL}
      DOCKER_IN_DOCKER: start
      AGENT_NAME: agent_47
    volumes:
      - agent-47-conf:/data/teamcity_agent/conf

volumes:
  pg-data:
  server-data:
  server-logs:
  agent-47-conf:
```

This file configures a `postgres database` for storing all data, a `teamcity-server` instance and one `teamcity-agent` for executing the jobs.

The following directory structure is needed for elephas to run:
```
/
├── docker-compose.yml
└── .env
```

The `.env` file needs these entries:

```
POSTGRES_DB=                                # the database name of your choice
POSTGRES_USER=                              # the database username of your choice
POSTGRES_PASSWORD=                          # the database password of your choice
SERVER_URL=http://teamcity-server:8111
TEAMCITY_VERSION=2023.05.2
POSTGRES_VERSION=15.3
```

### Workflow

When a commit gets pushed to this repo, the Teamcity pipeline starts and executes a fresh build of the project.\
As a second step the pipeline tests the project with coverage metrics using Jacoco. The results look the following:

![teamcity-result-overview](src/main/resources/assets/teamcity-result.png)

The `Code Coverage` tab provides a detailed overview over each class with their respective unit tests and code coverage:

![teamcity-code-coverage-tab](src/main/resources/assets/teamcity-coverage-tab.png)

It is even possible to inspect a specific class to look at the covered code lines:

![teamcity-coverage-specific-class](src/main/resources/assets/teamcity-coverage-class.png)

The green diamond says that both branches created by the if statement are covered.
