plugins {
    id("org.example.testing.test-convention")
    id("org.example.testing.integrationtest")
}

dependencies {
    intTestImplementation(project(":modules:subtracter"))
}