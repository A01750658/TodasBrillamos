// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //kotlin("plugin.serialization") version "2.0.20"
}
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.2") // or your current version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // or your current version
    }
}
