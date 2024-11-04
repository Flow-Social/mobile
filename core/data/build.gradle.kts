@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

kotlin {
    jvmToolchain(21)
}
dependencies {
	implementation(project(":core:domain"))
	implementation(project(":core:domain"))
}
