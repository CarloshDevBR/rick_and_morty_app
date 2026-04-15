plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.detekt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
dependencies {
    implementation(project(":core"))

    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlinx.coroutines.test)

    // Mockito
    api(libs.mockito.kotlin)

    // Paging
    api(libs.androidx.paging.common)
}
