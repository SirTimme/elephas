package dev.sirtimme.gradle

tasks.named<Test>("test") {
    testLogging {
        events("started", "passed", "skipped", "failed")
    }
    useJUnitPlatform()
}