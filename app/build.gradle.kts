plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.rickandmorty"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val baseUrl = providers.gradleProperty("BASE_URL").get()
        val publicKey = providers.gradleProperty("PUBLIC_KEY").get()
        val privateKey = providers.gradleProperty("PRIVATE_KEY").get()

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "PUBLIC_KEY", "\"$publicKey\"")
        buildConfigField("String", "PRIVATE_KEY", "\"$privateKey\"")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
        create("staging") {
            initWith(getByName("debug"))

            applicationIdSuffix = ".staging"
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-rules-staging.pro"
            )
        }
        release {
            applicationIdSuffix = ".release"
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

dependencies {
    // Modules
    implementation(project(":feature"))
}
