package org.example.testing

plugins {
    `java-library`
}

tasks.register("printClassPaths") {
    group = "diagnostics"

    logger.lifecycle("Compile class paths of sourceset 'intTest' of $project:")
    sourceSets["intTest"].compileClasspath.files.forEach {
        logger.lifecycle(it.absolutePath)
    }

    logger.lifecycle("Runtime class paths of sourceset 'intTest' of $project:")
    sourceSets["intTest"].runtimeClasspath.files.forEach {
        logger.lifecycle(it.absolutePath)
    }
}

sourceSets {
    create("intTest") {
        java {
            srcDir("src/intTest/java")
        }
        resources {
            srcDir("src/intTest/resources")
        }
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
    getByName("test") {
        compileClasspath += sourceSets["intTest"].output
        runtimeClasspath += sourceSets["intTest"].output
    }
}