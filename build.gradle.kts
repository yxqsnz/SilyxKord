import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.yxqsnz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("dev.kord:kord-core:0.7.x-SNAPSHOT")
    implementation("com.beust:klaxon:5.5")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "SilyxKt"
}



val fatJar = task("fatJar", type = Jar::class) {
        println("Compilando o Silyx...Isso pode demorar")
        manifest {
            attributes["Main-Class"] = "SilyxKt"
        }
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
            dependsOn(fatJar)
    }
}

