plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":core:domain"))
}
