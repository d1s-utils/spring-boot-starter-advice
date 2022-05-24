import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("maven-publish")
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "dev.d1s"
version = "1.3.4-stable.1"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

val teabagsVersion: String by project
val springMockkVersion: String by project
val striktVersion: String by project

dependencies {
    api("dev.d1s.teabags:teabag-spring-web:$teabagsVersion")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("dev.d1s.teabags:teabag-testing:$teabagsVersion")
    testImplementation("dev.d1s.teabags:teabag-testing-spring-web:$teabagsVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.ninja-squad:springmockk:$springMockkVersion")
    testImplementation("io.strikt:strikt-jvm:$striktVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events.addAll(
            listOf(
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
            )
        )
    }
}

tasks.withType<KotlinCompile> {
    targetCompatibility = "11"
}

tasks.withType<Jar> {
    archiveClassifier.set("")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = false
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
    enabled = false
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    enabled = false
}

publishing {
    publications {
        create<MavenPublication>("spring-boot-starter-advice") {
            from(components["java"])
        }
    }
}

kotlin {
    explicitApi = org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.Warning
}