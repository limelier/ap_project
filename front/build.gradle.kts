plugins {
    kotlin("jvm") version "1.3.72"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("org.jetbrains.dokka") version "0.10.1"
}

javafx {
    modules("javafx.controls", "javafx.fxml")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.11.+")
    implementation("com.squareup.okhttp3", "okhttp", "4.7.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}