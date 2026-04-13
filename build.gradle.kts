import dev.detekt.gradle.Detekt
import org.gradle.kotlin.dsl.withType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.navigation.safe.args) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.detekt) apply false
}

tasks.withType<Detekt>().configureEach {
    reports {
        checkstyle.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        markdown.required.set(true)
    }
}
subprojects {
    plugins.withId("com.android.library") {
        tasks.named("preBuild") {
            dependsOn("detekt")
        }
    }
}
