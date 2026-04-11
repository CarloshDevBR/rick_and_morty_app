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
detekt {
    config.setFrom(files("$rootDir/detekt.yml"))
    buildUponDefaultConfig = true
    basePath.set(projectDir)
}
dependencies {
    // Retrofit
    implementation(libs.converter.gson)

    // Paging Common
    implementation(libs.androidx.paging.common)

    // Javax Inject
    implementation(libs.javax.inject)
}