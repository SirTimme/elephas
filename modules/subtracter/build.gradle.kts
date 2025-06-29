plugins {
    id("dev.sirtimme.gradle.java-convention")
    id("dev.sirtimme.gradle.test-convention")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.platform:junit-platform-suite-engine:1.11.0-M2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}