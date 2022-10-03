import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    java

    // Plugin which checks for dependency updates with help/dependencyUpdates task.
    id("com.github.ben-manes.versions") version "0.42.0"

    // Plugin which can update Gradle dependencies, use help/useLatestVersions
    id("se.patrikerdes.use-latest-versions") version "0.2.18"

    id("org.jetbrains.dokka") version ("1.7.10")
}

group = "org.example"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://github.com/doyaaaaaken/kotlin-csv")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.apache.commons:commons-csv:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(kotlin("stdlib"))
    // To "prevent strange errors".
    implementation(kotlin("reflect"))
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.6.0") //for JVM platform

    // dokka - documentation generator
//    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    destinationDirectory.set(file("./"))
}