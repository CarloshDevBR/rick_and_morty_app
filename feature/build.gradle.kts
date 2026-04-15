plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.navigation.safe.args)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.example.feature"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "com.example.feature.CustomTestRunner"

        val baseUrl = providers.gradleProperty("BASE_URL").get()
        val publicKey = providers.gradleProperty("PUBLIC_KEY").get()
        val privateKey = providers.gradleProperty("PRIVATE_KEY").get()

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "PUBLIC_KEY", "\"$publicKey\"")
        buildConfigField("String", "PRIVATE_KEY", "\"$privateKey\"")
    }

    detekt {
        config.setFrom(files("$rootDir/detekt.yml"))
        buildUponDefaultConfig = true
        basePath.set(projectDir)
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

    // Fragment Testing
    debugImplementation(libs.androidx.fragment.testing.manifest)
    androidTestImplementation(libs.androidx.fragment.testing)

    // Hilt Testing
    androidTestImplementation(libs.hilt.android.testing)

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

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
