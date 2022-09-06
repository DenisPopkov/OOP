import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotest = "5.4.2"
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotest") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:$kotest") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:$kotest") // for kotest property test
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}