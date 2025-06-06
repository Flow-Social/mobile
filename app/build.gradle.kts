import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.googleGmsGoogleServices)
    alias(libs.plugins.firebaseCrashlytics)
}

android {
    namespace = "me.floow.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.floow.app"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

//        val secretsFile = project.rootProject.file("secrets.properties")
//        val secretsProperties = Properties()
//        secretsProperties.load(secretsFile.inputStream())

//        val googleClientId = secretsProperties.getProperty("googleClientId") ?: ""
        buildConfigField(
            type = "String",
            name = "GOOGLE_CLIENT_ID",
            value = "BUILD_TYPE" // mock
        )
    }

    flavorDimensions += "data"

    productFlavors {
        create("production") {
            dimension = "data"

            buildConfigField(
				name = "USE_MOCK_DATA",
				value = "false",
                type = "boolean"
			)
        }

        create("mock") {
            dimension = "data"
			applicationIdSuffix = ".mock"
            buildConfigField(
                name = "USE_MOCK_DATA",
                value = "true",
                type = "boolean"
            )
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:uikit"))
    implementation(project(":core:domain"))
    implementation(project(":core:api"))
    implementation(project(":core:database"))
    implementation(project(":core:data"))
    implementation(project(":core:auth"))

    implementation(project(":feature:login"))
    implementation(project(":feature:chats"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:chatssearch"))

    implementation(libs.androidx.core.splashscreen)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.appcompat)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.compose)
    implementation(project(":core:mock"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
