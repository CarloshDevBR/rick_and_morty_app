plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.android.navigation.safe.args)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 36

    signingConfigs {
        create("release") {
            storeFile =
                file("/Users/carloshi/StudioProjects/rick_and_morty_app/rick_and_morty_app_store.jks")
            storePassword = "Pokmon0?"
            keyAlias = "rick-and-morty-app"
            keyPassword = "Pokmon0?"
        }
    }

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

        buildConfigField(
            "String",
            "BASE_URL",
            "\"$baseUrl\""
        )
        buildConfigField(
            "String",
            "PUBLIC_KEY",
            "\"$publicKey\""
        )
        buildConfigField(
            "String",
            "PRIVATE_KEY",
            "\"$privateKey\""
        )
    }

    detekt {
        config.setFrom(files("$rootDir/detekt.yml"))
        buildUponDefaultConfig = true
        basePath.set(projectDir)
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
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
    implementation(project(":core"))
    implementation(project(":testing"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.kotlinx.serialization.json)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Okhttp3 Interceptor
    implementation(libs.logging.interceptor)

    // ROOM
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Paging
    implementation(libs.androidx.paging.runtime)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Glide
    implementation(libs.glide)
    ksp(libs.glideKsp)

    // Facebook Shimmer
    implementation(libs.shimmer)
}
