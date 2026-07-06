plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.4.0"
//    id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin.get() // or hardcode the same Kotlin version
}

group = "pro.udeedit.demo.simplegpstracker"  // Updated group name
version = "1.0.0-SNAPSHOT"

application {
    // Updated main class to the project package
    mainClass.set("pro.udeedit.demo.simplegpstracker.server.MainKt") // ApplicationKt
}

kotlin {
    jvmToolchain(21) // Update to JDK 17 for better compatibility // 17
}

dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.serialization.kotlinx.json)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
