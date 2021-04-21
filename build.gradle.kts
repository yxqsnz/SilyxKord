import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.4.32"
    application
}
tasks.getByName<Zip>("distZip").enabled = false
val distTar by tasks
distTar.enabled = false

val kordVersion = "0.7.x-SNAPSHOT"
group = "yxqsnz.night_labs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("dev.kord:kord-core:$kordVersion")
    implementation("com.beust:klaxon:5.5")
    testImplementation(kotlin("test-junit"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("SilyxKt")
}


val fatJar = task("fatJar", type = Jar::class) {
        manifest {
            attributes["Main-Class"] = "SilyxKt"
            attributes["Kord-Version"] = kordVersion

        }
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        println("Compilando o Silyx...Isso pode demorar")
         dependsOn(fatJar)
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}