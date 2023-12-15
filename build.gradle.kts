import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("java")
}


group = "org.codevillage"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.tmatesoft.svnkit:svnkit:1.10.11")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.7.0.202309050840-r")
    implementation("com.github.javaparser:javaparser-core:3.25.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed") //, "standardOut", "standardError"

        showExceptions = true
        exceptionFormat = TestExceptionFormat.FULL;
        showCauses = true
        showStackTraces = true

        showStandardStreams = false
    }
}
