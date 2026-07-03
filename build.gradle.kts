plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
}

group = "pro.udeedit.demo.simplegpstracker"  // Updated group name
version = "1.0.0-SNAPSHOT"

application {
    // Updated main class to the project package
    mainClass.set("pro.udeedit.demo.simplegpstracker.server.ApplicationKt")
}

kotlin {
    jvmToolchain(17) // Update to JDK 17 for better compatibility // 21
}

dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
