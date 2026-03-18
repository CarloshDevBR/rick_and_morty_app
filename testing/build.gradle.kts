plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.detekt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":core"))

    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlinx.coroutines.test)
    api(libs.mockito.kotlin)

    // Paging Common
    implementation(libs.androidx.paging.common)
}
