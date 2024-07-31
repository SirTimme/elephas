package org.example.testing

plugins {
    id("org.example.testing.java-convention")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.11.0-M2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    testLogging {
        events("started", "passed", "skipped", "failed")
    }
    useJUnitPlatform()
}