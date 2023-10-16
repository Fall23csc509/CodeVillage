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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}